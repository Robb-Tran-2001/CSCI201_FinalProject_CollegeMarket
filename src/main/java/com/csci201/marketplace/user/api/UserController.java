package com.csci201.marketplace.user.api;

import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.item.service.ItemService;
import com.csci201.marketplace.user.model.User;
import com.csci201.marketplace.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Repository
@RestController
@RequestMapping("api/user/")
public class UserController { //interacts with user service
	private final UserService uservice;
	private final ItemService iservice;

	@Autowired
	public UserController(UserService uservice, ItemService iservice)
	{
		this.uservice = uservice;
		this.iservice = iservice;
	}

	@GetMapping(path = "profile/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> list() {
		return uservice.listAll();
	}

	@GetMapping(path = "profile/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity getProfile(@PathVariable("name") String name) //get another user's profile
	{
		User user = uservice.getProf(name);
		if (user == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
		else
			return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	//POST http://placeholder/api/user/login send JSON of username and password
	@PostMapping(path = "login")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity login(@RequestBody User user) //log in to your profile
	{
		User user2 = uservice.getMProf(user.getName(), user.getPassword());
		if (user2 == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(user2);
		else
			return ResponseEntity.status(HttpStatus.OK).body(user2);
	}

	//POST http://placeholder.com/api/user/signup send JSON of username and password
	@PostMapping(path = "signup")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces()
	public ResponseEntity signup(@RequestBody User user) throws URISyntaxException {
		int userID = uservice.add(user);
		if(userID == 0) return new ResponseEntity(HttpStatus.CONFLICT);; //409
		return ResponseEntity.status(HttpStatus.OK).body(user); //200
	}

	//PUT http://placeholder.com/api/user/password
	@PutMapping(path = "password")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity changePassword(User user) {
		int row = uservice.update(user.getName(), user.getPassword());
		if (row != 0)
			return ResponseEntity.status(HttpStatus.OK).body(user);  //code 200 ok
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(user); //code 304 unmodified
	}
	
//	@DeleteMapping(path = "deleted")
//	public Response delete(int id) {
//		boolean bool = uservice.delete(id);
//		if (bool) {
//			return Response.ok().build();
//		}
//		return Response.notModified().build();
//	}

	//POST http://placeholder.com/api/user/approve
	@PostMapping(path = "approve")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity approve(String seller, String buyer, int itemID) {
		boolean success = iservice.delete(itemID);
		if(!success) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		return ResponseEntity.status(HttpStatus.OK).build();  //accepted code 200/202
	}

	//GET http://placeholder.com/api/user/name
	@GetMapping(path = "{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity getRequests(@PathVariable("name") String name) {
		List<User> users = uservice.listAll();
		User seller = uservice.getProf(name);

		List<Item> items = iservice.listAll();
		List<Item> requests = new ArrayList<>();
		for(Item it : items) {
			if(it.getSellerId() == seller.getUserID())
				requests.add(it);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}

