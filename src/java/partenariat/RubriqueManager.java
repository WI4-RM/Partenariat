/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package partenariat;

import entity.Rubrique;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lolo
 */

@Stateless
@LocalBean
public class RubriqueManager {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;
    @EJB
    private session.RubriqueFacade rubriqueFacade ;
    @EJB
    private session.ProfilFacade profilFacade ;
    @EJB
    private session.PaysFacade paysFacade ;

     public boolean createRubrique(String titre, String contenu, int idPays){
        try {
            //create rubrique
            Rubrique rubrique = new Rubrique();
            rubrique.setNom(Util.verificationTailleString(titre, 45));
            rubrique.setTexte(contenu);
            rubrique.setProfilIdprofil(profilFacade.findAll().get(0));    //FIXME mettre l'id de l'utilisateur connecte
            Date date = new Date();
            date.getTime();
            rubrique.setDate(date);
            rubrique.setPaysIdpays(paysFacade.findByIdpays(idPays).get(0));

            em.persist(rubrique);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }

     public boolean updateText(int idRub, String texte, int idPays){
        try {
            Rubrique rubriqueAncienne = rubriqueFacade.findByIdrubrique(idRub).get(0);
            createRubrique(rubriqueAncienne.getNom(), texte, idPays);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
     }
}
