/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Profil;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fingon
 */
@Stateless
public class ProfilFacade extends AbstractFacade<Profil> {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfilFacade() {
        super(Profil.class);
    }

    public List<Profil> findByIdprofil(int id){
        return em.createNamedQuery("Profil.findByIdprofil").setParameter("idprofil", id).getResultList();
    }
     public List<Profil> findByNom(String name){
        return em.createNamedQuery("Profil.findByNom").setParameter("nom", name).getResultList();
    }
     public List<Profil> findByPrenom(String firstName){
        return em.createNamedQuery("Profil.findByPrenom").setParameter("prenom", firstName).getResultList();
    }
}
