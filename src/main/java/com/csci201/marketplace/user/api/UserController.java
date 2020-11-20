package com.csci201.marketplace.user.api;

import com.csci201.marketplace.item.model.Item;
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

	@GetMapping(path = "profile/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfile(@PathVariable("name") String name) //get another user's profile
	{
		User user = service.getProf(name);
		if (user == null)
			return Response.status(Response.Status.NOT_FOUND).build(); //404 not found user
		else 
			return Response.ok(user, MediaType.APPLICATION_JSON).build(); //200 and user json returned
	}

	//POST http://placeholder/api/user/login send JSON of username and password
	@PostMapping(path = "login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String username, String hash) //log in to your profile
	{
		User user = service.getMProf(username, hash);
		if (user == null)
			return Response.status(Response.Status.UNAUTHORIZED).build(); //401 unauthorized access
		else
			return Response.ok(user, MediaType.APPLICATION_JSON).build(); //200 ok
	}

	//POST http://placeholder.com/api/user/signup send JSON of username and password
	@PostMapping(path = "signup")
	@Produces(MediaType.APPLICATION_JSON)
	public Response signup(@RequestBody String username, String hash) throws URISyntaxException {
		int userID = service.add(username, hash);
		if(userID == 0) return Response.status(Response.Status.CONFLICT).build(); //409, taken
		URI uri = new URI("{name}");
		return Response.created(uri).build(); //create URI for the user code 201
	}

	//GET http://placeholder.com/api/user/profile/userid
	@GetMapping(path = "profile")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changePassword(String username, String hash) {
		int row = service.update(username, hash);
		if (row != 0)
			return Response.ok().build(); //code 200 ok
		return Response.notModified().build(); //code 304 unmodified
	}
	
//	@DeleteMapping(path = "deleted")
//	public Response delete(int id) {
//		boolean bool = service.delete(id);
//		if (bool) {
//			return Response.ok().build();
//		}
//		return Response.notModified().build();
//	}

	//POST http://placeholder.com/api/user/approve
	@PostMapping(path = "approve")
	@Produces(MediaType.APPLICATION_JSON)
	public Response approve(@RequestBody String seller, String buyer, int itemID) {
		int success = service.approve(seller, buyer, itemID);
		if(success == 0) return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		return Response.ok().build(); //accepted code 200/202
	}

	//GET http://placeholder.com/api/user/name
	@PostMapping(path = "{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequests(@RequestBody String name) {
		List<Item> success = service.getReqs(name);
		return Response.ok(success, MediaType.APPLICATION_JSON).build();
	}
}
