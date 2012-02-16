
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package partenariat;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import session.DestinationFacade;
import session.PaysFacade;
import session.ProfilFacade;
import entity.Destination;
import entity.DestinationPK;
import entity.Profil;
import java.util.Date;
import session.VilleFacade;


/**
*
* @author lolo
*/
@Stateless
@LocalBean
public class DestinationManager {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    @EJB
    PaysFacade paysFacade;

    @EJB
    ProfilFacade profilFacade;

    @EJB
    VilleFacade villeFacade;

    @EJB
    DestinationFacade destinationFacade;
    /**
     * 
     * @param idVille
     * @param idPays
     * @param idProfil
     * @param type
     * @param organisme
     * @param com
     * @return true if destination is properly created
     */
     public boolean createDestination(int idVille, int idPays, int idProfil, String type, String organisme, String com){
        try {
            //create destination
            Destination destination = new Destination();
            DestinationPK destPK = new DestinationPK(idVille, idProfil);
            destination.setDestinationPK(destPK);
            destination.setCommentaire(com);
            destination.setProfil(null);
            destination.setVille(villeFacade.findByIdVille(idVille).get(0));
            Profil profil = profilFacade.findByIdprofil(idProfil).get(0);
            destination.setProfil(profil);
            destination.setType(type);
            destination.setOrganisme(organisme);
            Date date = new Date();
            date.getTime();
            destination.setDate(date);
            em.persist(destination);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }
     /**
      * 
      * @param idVille
      * @param idPays
      * @param i
      * @param type
      * @param organ
      * @param com
      * @param dateD
      * @param dateA 
      */
    public void createDestination(int idVille, int idPays, int i, String type, String organ, String com, Date dateD, Date dateA) {
        createDestination(idVille, idPays, i, type, organ, com);
    }
}
