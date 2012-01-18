/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Destination;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fingon
 */
@Stateless
public class DestinationFacade extends AbstractFacade<Destination> {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Destination> findByProfilIdprofil(int id){
        return em.createNamedQuery("Destination.findByProfilIdprofil").setParameter("profilIdprofil", id).getResultList();
    }
    public DestinationFacade() {
        super(Destination.class);
    }
    
}
