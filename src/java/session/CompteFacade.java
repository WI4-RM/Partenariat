/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Compte;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author fingon
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;
    
    private static int hashIterations = 100; // iteration for hash algorithm

    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }
    
    public List<Compte> findByEmail(String email ){
        return em.createNamedQuery("Compte.findByEmail").setParameter("email", email).getResultList();
       
    }
    
    
}
