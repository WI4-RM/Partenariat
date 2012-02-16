/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package partenariat;

import entity.Pays;
import java.util.ArrayList;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import session.PaysFacade;
import session.ProfilFacade;

/**
 *
 * @author lolo
 */
@Stateless
@LocalBean
public class PaysManager {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    @EJB
    PaysFacade paysFacade;

    @EJB
    ProfilFacade profilFacade;

    @EJB
    RubriqueManager rubriqueManager;
    /**
     * 
     * @param nomPays
     * @param idProfil
     * @param x
     * @param y
     * @param z
     * @return true if pays is properly created
     */
     public boolean createPays(String nomPays, int idProfil, float x, float y, int z){
        try {
            //create pays
            Pays pays = new Pays();
            pays.setNom(Util.verificationTailleString(nomPays, 45));
            pays.setX(x);
            pays.setY(y);
            pays.setZoomLevel(z);
            System.out.println(z);
            em.persist(pays);

            pays = paysFacade.findByNom(nomPays).get(0);
            int idPays = pays.getIdpays();
            ArrayList<String> liste = new ArrayList<String>();
            liste.add("Généralités");
            liste.add("Langue");
            liste.add("Nourriture");
            Iterator iter = liste.iterator();
            while (iter.hasNext()){
               rubriqueManager.createRubrique((String) iter.next(), "", idPays);
            }
            //paysFacade.update();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }
}
