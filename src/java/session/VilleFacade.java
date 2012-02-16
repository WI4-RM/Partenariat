/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Ville;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fingon
 */
@Stateless
public class VilleFacade extends AbstractFacade<Ville> {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VilleFacade() {
        super(Ville.class);
    }

    public List<Ville> findByIdVille(int idVille) {
        return em.createNamedQuery("Ville.findByIdVille").setParameter("idVille", idVille).getResultList();
    }

    public List<Ville> findByIdpays(int idPays) {
        return em.createNamedQuery("Ville.findByIdpays").setParameter("idPays", idPays).getResultList();
    }
    
     public List<Ville> findByVille(String ville) {
        return em.createNamedQuery("Ville.findByVille").setParameter("ville", ville).getResultList();
    }

    public void deleteVille(int idVille) {
        em.createNamedQuery("Ville.deleteVille").setParameter("idVille", idVille).executeUpdate();
    }
    
}
