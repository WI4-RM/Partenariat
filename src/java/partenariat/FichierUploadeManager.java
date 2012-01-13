/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package partenariat;

import entity.FichierUploade;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Pays;
import entity.Profil;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import session.PaysFacade;
import session.ProfilFacade;

/**
 *
 * @author lolo
 */
@Stateless
@LocalBean
public class FichierUploadeManager {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    @EJB
    private PaysFacade paysFacade;

    @EJB
    private ProfilFacade profilFacade;

     public boolean createFichier(String name, int idPays, int idProfil, int taille){
        try {
            //create fichier
            FichierUploade fichierUploade = new FichierUploade();
            Date date = new Date();
            date.getTime();
            Pays pays = paysFacade.findByIdpays(idPays).get(0);
            Profil profil = profilFacade.findByIdprofil(idProfil).get(0);

            fichierUploade.setDate(date);
            fichierUploade.setNom(name);
            fichierUploade.setPaysIdpays(pays);
            fichierUploade.setProfilIdprofil(profil);
            fichierUploade.setTaille(taille);

            em.persist(fichierUploade);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }
}
