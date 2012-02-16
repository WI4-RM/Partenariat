/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Compte;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author fingon
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;
    
    private static int hashIterations = 100; // iteration for hash algorithm

    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }
    
    public List<Compte> findByEmail(String email ){
        return em.createNamedQuery("Compte.findByEmail").setParameter("email", email).getResultList();
    }
    
    public  Compte findTheCompteByEmail(String email) {
        List<Compte> LCompte = findByEmail(email);
        if (LCompte.isEmpty()) {
            return null;
        }

        return LCompte.get(0);
    }
    
    public List<Compte> findByIdprofil(int idprofil){
        return em.createNamedQuery("Compte.findByIdprofil").setParameter("idprofil", idprofil).getResultList();
    }
    
    public List<Compte> findByIdCompte(int idCompte){
        return em.createNamedQuery("Compte.findByIdcompte").setParameter("idcompte", idCompte).getResultList();
    }
    
//    public void destroyCompteEtProfil(int idCompte){
//            List<Compte> lCompte = this.findByIdCompte(idCompte);
//            if (lCompte.isEmpty())
//                throw new NullPointerException("aucun compte trouvé");
//            Profil profil = lCompte.get(0).getProfilIdprofil();
//        this.remove(lCompte.get(0));
//        
//    }


}
