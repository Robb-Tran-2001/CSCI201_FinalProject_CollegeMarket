package com.csci201.marketplace.user.api;

import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.item.service.ItemService;
import com.csci201.marketplace.user.dao.Request;
import com.csci201.marketplace.user.model.User;
import com.csci201.marketplace.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

//https://www.youtube.com/watch?v=vtPkZShrvXQ&list=PLkk4sAgb9rVilFxbso9RBqTHiM8fLwsBQ&index=26&t=2180s&ab_channel=freeCodeCamp.org
//REST API tutorial


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

	/*
	 * Unused in the frontend, for testing
	 */
	@GetMapping(path = "profile/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> list() {
		return uservice.listAll();
	}
	
	/*
	 * Unused in the frontend, for testing
	 */
	@GetMapping(path = "profile/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<User> getProfile(@PathVariable("name") String name) //get another user's profile
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
	public ResponseEntity<User> login(@RequestBody User user) //log in to your profile
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
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<User> signup(@RequestBody User user) {
		int userID = uservice.add(user);
		if(userID == 0) return new ResponseEntity<User>(HttpStatus.CONFLICT); //409
		return ResponseEntity.status(HttpStatus.OK).body(user); //200
	}

	//PUT http://placeholder.com/api/user/password
	@PutMapping(path = "password")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<User> changePassword(@RequestBody User user) {
		int row = uservice.update(user.getName(), user.getPassword());
		if (row != 0)
			return ResponseEntity.status(HttpStatus.OK).body(user);  //code 200 ok
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(user); //code 304 unmodified
	}


	//Delete http://placeholder.com/api/user/approve
	@PostMapping(path = "approve")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<User> approve(@RequestBody ApproveJson req) {
		String username = req.getUsername();
		uservice.getProf(username);
		//int sellerid = uservice.getID(username);
		int id = req.getItemId();
		boolean success = iservice.delete(id);
		if(!success)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		return ResponseEntity.status(HttpStatus.OK).build();  //accepted code 200/202
	}

	//GET http://placeholder.com/api/user/name
	@GetMapping(path = "{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Request>> getRequests(@PathVariable("name") String name) {
		User seller = uservice.getProf(name);
		if(seller == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		List<Item> items = iservice.listAll();
		List<Item> requests = new ArrayList<>();
		for(Item it : items) {
			if(it.getSellerId() == seller.getUserID() && it.getBuyerId() != 0)
				requests.add(it);
		}
		List<Request> requestsForReal = new ArrayList<>();
		for(Item it : requests) {
			String buyername = uservice.getNameFromID(it.getBuyerId());
			requestsForReal.add(new Request(it, buyername));
		}
		return ResponseEntity.status(HttpStatus.OK).body(requestsForReal);
	}
}

