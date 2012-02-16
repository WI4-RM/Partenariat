/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Compte;
import entity.Profil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    public boolean createUser(String name, String username, String email, String password, int promo) {
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

            byte[] hash = calculateHash(hashIterations, password, salt);
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
     *
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
            for (int i = 0; i < hashIterations; i++) {
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
     *
     * @param login login which is actualy email
     * @param password password in plaintext
     * @return true if the hash of the provided password matchs the one in db
     */
    public boolean connect(String login, String password) {
        try {
            List<Compte> LCompte = compteFacade.findByEmail(login);
            if (LCompte.isEmpty()) {
                return false;
            }

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

    /**
     * admin connection
     *
     * @param username
     * @param password
     * @return true if the user is admin, with valid password
     */
    public boolean connectAdmin(String username, String password) {
        if (connect(username, password)) {
            List<Compte> LCompte = compteFacade.findByEmail(username);
            if (LCompte.isEmpty()) {
                return false;
            }

            Compte userAccount = LCompte.get(0);
            return userAccount.getIsAdministrator();
        } else {
            return false;
        }
    }

    /**
     * reset password, create a new one and email it
     *
     * @param email in whixh new password is sent
     * @return true if operation succeded
     */
    public boolean resetPassword(String email) {

        List<Compte> LComptes = compteFacade.findByEmail(email);
        if (LComptes.isEmpty()) {
            return false;
        }
        Compte compte = LComptes.get(0);

        String password = new BigInteger(130, new SecureRandom()).toString(32);

        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[8];
            random.nextBytes(salt); //salt generation

            byte[] hash = calculateHash(hashIterations, password, salt);
            String finalHash = new BASE64Encoder().encode(hash);
            String finalSalt = new BASE64Encoder().encode(salt);

            compte.setPasswordHash(finalHash);
            compte.setSalt(finalSalt);
            
            String message = "your new password is now " + password;
            InscriptionManager.postMail(email, "password reset",message);
            
            return true;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static void postMail(String email, String subject, String message) {
        try {
            boolean debug = false;


            Properties props = new Properties();
            props.put("mail.smtp.host", "localhost");
            props.put("mail.smtp.user", "localhost.localdomain");
            props.put("mail.debug", "true");

            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(debug);

            // create a message
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("no-reply@admin.com"));

            InternetAddress[] addressTo = new InternetAddress[1];
            addressTo[0] = new InternetAddress(email);

            msg.setRecipients(Message.RecipientType.TO, addressTo);

            msg.setSubject(subject);
            msg.setContent(message, "text/plain");
            Transport.send(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Try to replace oldPassword with newPassword in the account that own the email
     * @param email email of the user
     * @param oldPassword 
     * @param newPassword
     * @return true if successful
     */
    public boolean changePassword(String email, String oldPassword,String newPassword){
                List<Compte> LComptes = compteFacade.findByEmail(email);
        if (LComptes.isEmpty()) {
            return false;
        }
        Compte compte = LComptes.get(0);

        if (! this.connect(email, oldPassword)) //try old password
            return false;

        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[8];
            random.nextBytes(salt); //salt generation

            byte[] hash = calculateHash(hashIterations, newPassword, salt);
            String finalHash = new BASE64Encoder().encode(hash);
            String finalSalt = new BASE64Encoder().encode(salt);

            compte.setPasswordHash(finalHash);
            compte.setSalt(finalSalt);

            
            return true;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
