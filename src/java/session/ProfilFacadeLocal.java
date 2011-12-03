/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Profil;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fingon
 */
@Local
public interface ProfilFacadeLocal {

    void create(Profil profil);

    void edit(Profil profil);

    void remove(Profil profil);

    Profil find(Object id);

    List<Profil> findAll();

    List<Profil> findRange(int[] range);

    int count();
    
}
