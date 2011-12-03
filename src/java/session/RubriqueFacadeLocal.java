/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Rubrique;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fingon
 */
@Local
public interface RubriqueFacadeLocal {

    void create(Rubrique rubrique);

    void edit(Rubrique rubrique);

    void remove(Rubrique rubrique);

    Rubrique find(Object id);

    List<Rubrique> findAll();

    List<Rubrique> findRange(int[] range);

    int count();
    
}
