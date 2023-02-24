package com.stefanini.resources;

import com.stefanini.dto.UserCreationDTO;
import com.stefanini.dto.UserResponseDTO;
import com.stefanini.exceptions.BadRequestException;
import com.stefanini.exceptions.UserAlreadyExistsException;
import com.stefanini.exceptions.UserDoesntExistsException;
import com.stefanini.exceptions.UserException;
import com.stefanini.services.UserServices;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {

    @Inject
    private UserServices usuarioService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserCreationDTO userDTO) {
        try {
            return Response.status(Response.Status.CREATED).entity(usuarioService.userCreate(userDTO)).build();
        }catch (UserException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam(value = "id")  Long id) {
        try {
            usuarioService.userDelete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        }catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarUser(UserCreationDTO userDTO){
        try {
            return Response.status(Response.Status.OK).entity(usuarioService.userUpdate(userDTO)).build();
        }catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        try {
            return Response.status(Response.Status.OK).entity(usuarioService.listAll()).build();
        }catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listId(@PathParam(value = "id")  Long id) {
        try {
            return Response.status(Response.Status.OK).entity(usuarioService.findById(id)).build();
        }catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
