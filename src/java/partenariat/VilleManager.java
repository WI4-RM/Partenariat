/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package partenariat;

import entity.Ville;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import session.PaysFacade;
import session.VilleFacade;

/**
 *
 * @author lolo
 */
@Stateless
@LocalBean
public class VilleManager {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    @EJB
    VilleFacade villeFacade;

    @EJB
    PaysFacade paysFacade;


     public boolean createVille(String nomVille, int idPays, float x, float y, int zoom){
        try {
            //create ville
            Ville ville = new Ville();
            ville.setVille(nomVille);
            ville.setX(x);
            ville.setY(y);
            ville.setZoomLevel(zoom);
            ville.setPaysIdpays(paysFacade.findByIdpays(idPays).get(0));
            villeFacade.create(ville);
           
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }
}
