package com.csci201.marketplace.user.api;

import com.csci201.marketplace.user.model.User;
import com.csci201.marketplace.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Repository
@RestController
@RequestMapping("api/user/")
public class UserController { //interacts with user service
	private final UserService service;

	@Autowired
	public UserController(UserService service)
	{
		this.service = service;
	}

	@GetMapping(path = "all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> list()
	{
		return service.listAll();
	}

	@GetMapping(path = "{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathVariable("user_id") int id) //get another user's profile
	{
		User user = service.get(id);
		if (user == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		else 
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
	}

	@GetMapping(path = "login/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathVariable("login") String email, String password) //log in to your profile
	{
		User user = service.get(email, password);
		if (user == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		else
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
	}


	
	@PostMapping(path = "signup")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signup(@RequestBody @PathVariable("signup") User user) throws URISyntaxException {
		int userID = service.add(user);
		if(userID == 0) return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		URI uri = new URI("/users/" + userID);
		return Response.created(uri).build();
	}
	
	@PutMapping
	@Produces(MediaType.APPLICATION_JSON)
	public Response put(User user) throws URISyntaxException {
		int row = service.update(user);
		if (row != 0)
			return Response.ok().build();
		return Response.notModified().build();
	}
	
	@DeleteMapping(path = "{user_id}/deleted")
	public Response delete(@PathVariable("user_id") int id) {
		boolean bool = service.delete(id);
		if (bool) {
			return Response.ok().build();
		}
		return Response.notModified().build();
	}
}
