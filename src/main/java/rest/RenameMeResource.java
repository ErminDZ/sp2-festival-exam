package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.FestivalDTO;
import dtos.GuestDTO;
import dtos.MovieDTO;
import entities.Guest;
import entities.Movie;
import utils.EMF_Creator;
import facades.FacadeExample;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("xxx")
public class RenameMeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
       
        long count = FACADE.getRenameMeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @Path("shows")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllShows(){
        return Response.ok(GSON.toJson(FACADE.getAllShows())).build();
    }

    @Path("guest")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllGuest(){
        return Response.ok(GSON.toJson(FACADE.getAllGuest())).build();
    }

    @Path("festival")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllFestival(){
        return Response.ok(GSON.toJson(FACADE.getAllFestival())).build();
    }

    //RolesAllowed not added for easier testing
    @Path("assignShow/{id}")
    //@RolesAllowed("admin")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response assignShow(@PathParam("id") long id, String movie) {
        MovieDTO g = GSON.fromJson(movie, MovieDTO.class);
        GuestDTO mEdited = FACADE.assignShow(id, g.getId());
        return Response.ok(GSON.toJson(mEdited)).build();
    }

    //RolesAllowed not added for easier testing
    @Path("createShow")
    //@RolesAllowed("admin")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createShow(String Movie) {
        MovieDTO m = GSON.fromJson(Movie, MovieDTO.class);
        MovieDTO mo = FACADE.createShow(m);
        return Response.ok(mo).build();
    }

    //RolesAllowed not added for easier testing
    @Path("createGuest")
    //@RolesAllowed("admin")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createGuest(String Guest) {
        GuestDTO g = GSON.fromJson(Guest, GuestDTO.class);
        GuestDTO gu = FACADE.createGuest(g);
        return Response.ok(gu).build();
    }
    //RolesAllowed not added for easier testing
    @Path("createFestival")
    //@RolesAllowed("admin")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createFestival(String Festival) {
        FestivalDTO f = GSON.fromJson(Festival, FestivalDTO.class);
        FestivalDTO fe = FACADE.createFestival(f);
        return Response.ok(fe).build();
    }

    @Path("deleteshow/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteShow(@PathParam("id") long id) {
        return Response.ok(GSON.toJson(FACADE.deleteShow(id))).build();
    }
}
