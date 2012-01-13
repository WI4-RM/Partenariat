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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author lolo
 */
@Stateless
public class DestinationFacade {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    public void create(Destination destination) {
        em.persist(destination);
    }

    public void edit(Destination destination) {
        em.merge(destination);
    }

    public void remove(Destination destination) {
        em.remove(em.merge(destination));
    }

    public Destination find(Object id) {
        return em.find(Destination.class, id);
    }

    public List<Destination> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Destination.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Destination> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Destination.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Destination> rt = cq.from(Destination.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
