package com.csci201.marketplace.user.api;

import com.csci201.marketplace.user.dao.UserDAO;
import com.csci201.marketplace.user.model.User;
import com.csci201.marketplace.user.service.UserService;
import org.springframework.stereotype.Repository;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Repository
public class UserResource { //interacts with user service
	private UserService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> list()
	{
		return UserService.listAll();
	}
	
	@GET
	@Path("{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("user_id") int id) //get another user's profile
	{
		User user = service.get(id);
		if (user == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		else 
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("user_id") int id, String password) //log in to your profile
	{
		User user = service.get(id, password);
		if (user == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		else
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signup(User user) throws URISyntaxException {
		int userID = service.add(user);
		if(userID == 0) return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		URI uri = new URI("/users/" + userID);
		return Response.created(uri).build();
	}
	
	@PUT
	@Path("{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response put(User user) throws URISyntaxException {
		int row = service.update(user);
		if (row != 0)
			return Response.ok().build();
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("{user_id}")
	public Response delete(@PathParam("user_id") int id) {
		boolean bool = service.delete(id);
		
		if (bool) {
			return Response.ok().build();
		}
		
		return Response.notModified().build();
	}
	
}
