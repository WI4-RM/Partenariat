/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.FichierUploade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fingon
 */
@Stateless
public class FichierUploadeFacade extends AbstractFacade<FichierUploade> {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public FichierUploadeFacade() {
        super(FichierUploade.class);
    }

    public List<FichierUploade> findByIdrubrique(int idRub){
        //FIXME Probleme d'acces a la bdd : impossible d'acceder au champ idrubrique
        //return em.createNamedQuery("FichierUploade.findByIdrubrique").setParameter("idRubrique", idRub).getResultList();
        return new ArrayList<FichierUploade>();
    }
}
