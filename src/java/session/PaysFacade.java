/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Pays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fingon
 */
@Stateless
public class PaysFacade extends AbstractFacade<Pays> {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PaysFacade() {
        super(Pays.class);
    }

    public List<Pays> findByFirstLetter(String lettre){
        return em.createNamedQuery("Pays.findByFirstLetter").setParameter("lettre", lettre).getResultList();
    }

    public List<Pays> findAllOrderedByName(){
        return em.createNamedQuery("Pays.findAllOrderedByName").getResultList();
    }

    public List<Pays> findAllOrderedById(){
        return em.createNamedQuery("Pays.findAllOrderedById").getResultList();
    }

    public Integer findMaxId(){
        return (Integer)em.createNamedQuery("Pays.findMaxId").getResultList().get(0);
    }

    public List<Pays> findByNom(String nom){
        return em.createNamedQuery("Pays.findByNom").setParameter("nom", nom).getResultList();
    }

    /*public void insertNewPays(String nom){
        Integer idPays = findMaxId();
        idPays++;
        em.createNativeQuery("Pays.insertNewPays", Class<session.PaysFacade>).setParameter("idPays", idPays).setParameter("nom", nom).getResultList();
    }*/
    
}
