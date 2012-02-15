/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.FichierUploade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

/**
 *
 * @author charles
 */
@Stateless
@Path("entity.fichieruploade")
public class FichierUploadeFacadeREST extends AbstractFacade<FichierUploade> {
    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    public FichierUploadeFacadeREST() {
        super(FichierUploade.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(FichierUploade entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(FichierUploade entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public FichierUploade find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<FichierUploade> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<FichierUploade> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @java.lang.Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
