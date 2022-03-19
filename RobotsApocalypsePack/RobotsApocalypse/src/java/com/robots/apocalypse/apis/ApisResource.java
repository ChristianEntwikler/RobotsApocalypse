/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robots.apocalypse.apis;

import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 *
 * @author cenebeli
 */

@Path("api")
public interface ApisResource {
    
    @POST
    @Path("/insertSurvivor")
    @Consumes({"application/json"})
    public Response InsertSurvivors(InputStream is, @Context HttpHeaders headers, @Context HttpServletRequest servlets);
    
    @POST
    @Path("/updateLocation")
    @Consumes({"application/json"})
    public Response UpdateLocation(InputStream is, @Context HttpHeaders headers, @Context HttpServletRequest servlets);
   
    @POST
    @Path("/flagInfected")
    @Consumes({"application/json"})
    public Response FlagInfected(InputStream is, @Context HttpHeaders headers, @Context HttpServletRequest servlets);
    
    @GET
    @Path("/listRobots")
    @Produces({"application/json"})
    public Response ListRobots(@Context HttpHeaders headers, @Context HttpServletRequest servlets);
    
    @POST
    @Path("/searchRobots")
    @Consumes({"application/json"})
    public Response SearchRobots(InputStream is, @Context HttpHeaders headers, @Context HttpServletRequest servlets);
    
    @GET
    @Path("/percentageInfected")
    @Produces({"application/json"})
    public Response PercentageInfected(@Context HttpHeaders headers, @Context HttpServletRequest servlets);
    
    @GET
    @Path("/percentageNoninfected")
    @Produces({"application/json"})
    public Response PercentageNoninfected(@Context HttpHeaders headers, @Context HttpServletRequest servlets);
    
    @GET
    @Path("/listInfected")
    @Produces({"application/json"})
    public Response ListInfected(@Context HttpHeaders headers, @Context HttpServletRequest servlets);
    
    @GET
    @Path("/listNoninfected")
    @Produces({"application/json"})
    public Response ListNoninfected(@Context HttpHeaders headers, @Context HttpServletRequest servlets);

}
