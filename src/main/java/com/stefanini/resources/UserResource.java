package com.stefanini.resources;

import com.stefanini.dto.UserCreationDTO;
import com.stefanini.exceptions.BadRequestException;
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
    public Response createUser(UserCreationDTO userDTO) throws BadRequestException {
        try {
            return Response.status(Response.Status.CREATED).entity(usuarioService.userCreate(userDTO)).build();
        }catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
