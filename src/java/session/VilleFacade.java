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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author lolo
 */
@Stateless
public class VilleFacade {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    public void create(Ville ville) {
        em.persist(ville);
    }

    public void edit(Ville ville) {
        em.merge(ville);
    }

    public void remove(Ville ville) {
        em.remove(em.merge(ville));
    }

    public Ville find(Object id) {
        return em.find(Ville.class, id);
    }

    public List<Ville> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Ville.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Ville> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Ville.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Ville> rt = cq.from(Ville.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
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

}
