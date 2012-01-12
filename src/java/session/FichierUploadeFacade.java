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

    public List<FichierUploade> findByIdpays(int idPays){
        //FIXME
        //return em.createNamedQuery("FichierUploade.findByIdpays").setParameter("idPays", idPays).getResultList();
        return new ArrayList<FichierUploade>();
    }

    public List<FichierUploade> findByNom(String nom){
        return em.createNamedQuery("FichierUploade.findByNom").setParameter("nom", nom).getResultList();
    }
}
