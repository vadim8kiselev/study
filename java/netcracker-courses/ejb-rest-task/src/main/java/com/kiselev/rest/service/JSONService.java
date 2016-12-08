package com.kiselev.rest.service;

import com.kiselev.ejb.dao.DAOLocal;
import com.kiselev.ejb.model.entities.EntityBean;
import com.kiselev.rest.RESTServiceLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/json/entity")
@Stateless
public class JSONService implements RESTServiceLocal<EntityBean> {

    @EJB
    DAOLocal<EntityBean> dao;

    @GET
    @Path("/get/id{id}")
    @Produces("application/json")
    public EntityBean getById(@PathParam("id") Long id) {
        return dao.getObject(id);
    }

    @GET
    @Path("/get")
    @Produces("application/json")
    public List<EntityBean> get() {
        return dao.getObjects();
    }

    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response post(EntityBean entity) {

        String result = "Entity saved : " + entity;
        return Response.status(201).entity(result).build();

    }

}