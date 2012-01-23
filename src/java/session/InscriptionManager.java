/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Compte;
import entity.Profil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author fingon
 */
@Stateless
@LocalBean
public class InscriptionManager {
    
    private static int hashIterations = 100;
    
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;
    
    @EJB
    private CompteFacade compteFacade;
/**
     * create a new account and profil for user with the following parameters
     * 
     * @param name
     * @param username
     * @param email
     * @param password
     * @param promo
     * @return true if user is created correctly
     */
    public boolean createUser(String name,String username, String email, String password, int promo){
        try {
            //create profile
            Profil profil = new Profil();
           // profil.setIdprofil(1);
            profil.setNom(name);
            profil.setPrenom(username);
            profil.setPromo(promo);

            em.persist(profil);
            
            //compte
            Compte compte = new Compte();
            compte.setEmail(email);
            compte.setProfilIdprofil(profil);
            
            //set-up hash and salt
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[8];
            random.nextBytes(salt); //salt generation
            
            byte[] hash = calculateHash(hashIterations,password,salt);
            String finalHash = new BASE64Encoder().encode(hash);
            String finalSalt = new BASE64Encoder().encode(salt);
            
            compte.setPasswordHash(finalHash);
            compte.setSalt(finalSalt);
            
            em.persist(compte);

            return true;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CompteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return false;
    }    
    
    
/**
     * create a strong hash of the password
     * @param hashIterations
     * @param password
     * @param salt
     * @return hash of password with salt with hashIteration
     */
    private byte[] calculateHash(int hashIterations, String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(salt);
            byte[] input = digest.digest(password.getBytes("UTF-8"));
            for (int i=0; i< hashIterations; i++) {
                digest.reset();
                input = digest.digest(input);
            }
            
            return input;
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CompteFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CompteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * try to match login and password with a legitimate user 
     * @param login login which is actualy email
     * @param password password in plaintext
     * @return true if the hash of the provided password matchs the one in db
     */
    public boolean connect(String login, String password){
        try {
            List<Compte> LCompte =  compteFacade.findByEmail(login);
            if (LCompte.isEmpty())
                return false;
            
             Compte userAccount = LCompte.get(0);
            String passwordHashDB = userAccount.getPasswordHash();
            String salt = userAccount.getSalt();
            
            byte[] bdigest = new BASE64Decoder().decodeBuffer(passwordHashDB);
            byte[] bsalt = new BASE64Decoder().decodeBuffer(salt);
            
            byte[] submittedHash = calculateHash(hashIterations, password, bsalt);
            
            return Arrays.equals(bdigest, submittedHash);
        } catch (IOException ex) {
            //Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       // return false;
          
    }
    
    

    /** admin connection
     * 
     * @param username
     * @param password
     * @return true if the user is admin, with valid password
     */
    public boolean connectAdmin(String username, String password) {
        if (connect(username, password)){
            List<Compte> LCompte =  compteFacade.findByEmail(username);
            if (LCompte.isEmpty())
                return false;
            
             Compte userAccount = LCompte.get(0);
             return userAccount.getIsAdministrator();
        }
        else
            return false;
    }

}
