/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.FichierUploade;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fingon
 */
@Local
public interface FichierUploadeFacadeLocal {

    void create(FichierUploade fichierUploade);

    void edit(FichierUploade fichierUploade);

    void remove(FichierUploade fichierUploade);

    FichierUploade find(Object id);

    List<FichierUploade> findAll();

    List<FichierUploade> findRange(int[] range);

    int count();
    
}
