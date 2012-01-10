/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Rubrique;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fingon
 */
@Stateless
public class RubriqueFacade extends AbstractFacade<Rubrique> {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RubriqueFacade() {
        super(Rubrique.class);
    }
    
    public List<Rubrique> findByIdPays(int idPays){
        return em.createNamedQuery("Rubrique.findByIdPays").setParameter("idPays", idPays).getResultList();
    }

    public List<Rubrique> findByNom(String nom){
        return em.createNamedQuery("Rubrique.findByNom").setParameter("nom", nom).getResultList();
    }

    public List<Rubrique> findByIdrubrique(int id){
        return em.createNamedQuery("Rubrique.findByIdrubrique").setParameter("idrubrique", id).getResultList();
    }

    public Integer findMaxId(){
        return (Integer)em.createNamedQuery("Rubrique.findMaxId").getResultList().get(0);
    }
}
