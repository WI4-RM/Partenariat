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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaysFacade() {
        super(Pays.class);
    }
    
    
    public List<Pays> findByPays(String name){
        return em.createNamedQuery("Pays.findByNom").setParameter("nom", name).getResultList();
    }

    public List<Pays> findByIdpays(int idPays) {
        return em.createNamedQuery("Pays.findByIdpays").setParameter("idpays", idPays).getResultList();
    }

    public List<Pays> findByNom(String nomPays) {
        return findByPays(nomPays);
    }

    public List<Pays> findAllOrderedById(){
        return em.createNamedQuery("Pays.findAllOrderedById").getResultList();
    }

    public List<Pays> findByFirstLetter(String lettre){
        return em.createNamedQuery("Pays.findByFirstLetter").setParameter("lettre", lettre).getResultList();
    }

    public List<Pays> findAllOrderedByName(){
        return em.createNamedQuery("Pays.findAllOrderedByName").getResultList();
    }
    
    public void deletePays(int idpays){
         em.createNamedQuery("Pays.deletePays").setParameter("idpays", idpays).executeUpdate();
    }
}
