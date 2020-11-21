package com.csci201.marketplace.item.api;


import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.websocket.EncodeException;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.item.model.ItemSimple;
import com.csci201.marketplace.item.service.ItemService;
import com.csci201.marketplace.pushnotif.model.Message;
import com.csci201.marketplace.pushnotif.websocket.PushEndpoint;
import com.csci201.marketplace.user.api.*;
import com.csci201.marketplace.user.dao.*;
import com.csci201.marketplace.user.model.*;
import com.csci201.marketplace.user.service.UserService;

@Repository
@RestController
@RequestMapping("/api")
public class ItemResource {
	
	private ItemService iservice;
	private UserService uservice;
	
	@Autowired
	public ItemResource(ItemService is, UserService us) {
		this.iservice = is;
		this.uservice = us;
	}
	
	
	@GetMapping(path = "/items")
//    @Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
    public List<ItemSimple> list() {
		List<ItemSimple> list = iservice.listAllSimple();
        return list;
//        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }
	
	@GetMapping(path = "/items/{id}")
	@ResponseBody
	public Item get(@PathVariable("id") String id) {
		Item item = iservice.get(Integer.parseInt(id));
		return item;
//		if (item == null) {
//			return Response.status(Response.Status.NOT_FOUND).build();
//		}
//		else {
//			return Response.ok(item, MediaType.APPLICATION_JSON).build();
//		}
	}
	
	@PostMapping(path = "/sell")
	@Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public int add(@RequestBody SellItemForm form) throws URISyntaxException {
		// Parse new Item from JSON 
		int sellerId = uservice.getID(form.getUsername());
		Item item = new Item(sellerId, form.getName(), form.getPrice());
		item.setDescription(form.getDescription());
		return Integer.valueOf(iservice.add(item));
		
		// Add item to DB
//		String itemID = Integer.toString(iservice.add(item));
//		URI uri = new URI("/items/" + itemID);
//		return Response.created(uri).build();
	}
	
//	@PUT
//	@Path("{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response put(Item item) throws URISyntaxException, IOException, EncodeException {
//		boolean bool = iservice.update(item);
//		
//		if (bool) {
//			return Response.ok().build();
//		}
//		
//		return Response.notModified().build();
//	}
	
	
	@PostMapping(path = "/buy")
    @Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public boolean buy_item(@RequestBody BuyJson json) throws URISyntaxException, IOException, EncodeException {	
		
		System.out.println("enters buy_item function in ItemResource");
		
		//String id = "100";
		String username = json.getUsername();
		String itemId = json.getItemId();
		
		Item item = iservice.get(Integer.parseInt(itemId));
		
		if (item.isSold()) {
			// broadcast and return here
			Message temp = new Message();
			temp.setMsg("This has already been sold!");
			PushEndpoint.send_user_msg(username, temp);
			return false;
//			return Response.notModified().build();
		}
		
		// NEED TO PASS IN BUYER ID 
		item.setBuyerId(uservice.getID(username));
		iservice.update(item);
		boolean bool = iservice.update_sell(item, username);
		return bool;
		
		
//		if (bool) {
//			return Response.ok().build();
//		}
//		
//		return Response.notModified().build();
	}
	
//	@DeleteMapping(path = "/delete/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//	@ResponseBody
//	public Boolean delete(@PathVariable("id") int id) {
//		Boolean bool = iservice.delete(id);
//		return bool;
////		return new ResponseEntity<Boolean>(bool, HttpStatus.OK);
//		
////		if (bool) {
////			return Response.ok().build();
////		}
////		
////		return Response.notModified().build();
//	}
}
