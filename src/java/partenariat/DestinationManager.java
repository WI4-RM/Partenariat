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
import entity.Profil;
import entity.Ville;
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

     public boolean createDestination(int idVille, int idPays, int idProfil, String type, String organisme, String com){
        try {
            //create destination
            Profil profil = profilFacade.findByIdprofil(idProfil).get(0);
            Ville ville = villeFacade.findByIdVille(idVille).get(0);
            Destination destination = new Destination();
            destination.setVille(ville);
            destination.setProfil(profil);
            destination.setType(type);
            destination.setOrganisme(organisme);
            destination.setCommentaire(com);
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
}
