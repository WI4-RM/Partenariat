/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Destination;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fingon
 */
@Local
public interface DestinationFacadeLocal {

    void create(Destination destination);

    void edit(Destination destination);

    void remove(Destination destination);

    Destination find(Object id);

    List<Destination> findAll();

    List<Destination> findRange(int[] range);

    int count();
    
}
