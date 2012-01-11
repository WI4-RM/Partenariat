/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package partenariat;

import entity.Pays;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import session.PaysFacade;

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
    private PaysFacade paysFacade;

     public boolean createPays(String name){
        try {
            //create pays
            Pays pays = new Pays();
            pays.setNom(name);

            em.persist(pays);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }
}
