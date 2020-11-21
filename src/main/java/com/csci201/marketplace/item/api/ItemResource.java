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
	@ResponseBody
    public ResponseEntity<List<ItemSimple>> list() {
		List<ItemSimple> list = iservice.listAllSimple();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

	@GetMapping(path = "/items/{id}")
	@ResponseBody
	public ResponseEntity<Item> get(@PathVariable("id") String id) {
		Item item = iservice.get(Integer.parseInt(id));

		if (item == null) {
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		else {
	        return new ResponseEntity<>(item, HttpStatus.OK);
		}
	}

	@PostMapping(path = "/sell")
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Integer> add(@RequestBody SellItemForm form) throws URISyntaxException {
		// Parse new Item from JSON 
		Item item;
		try{
			int sellerId = uservice.getID(form.getUsername());
			item = new Item(sellerId, form.getName(), form.getPrice());
			item.setDescription(form.getDescription());
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(Integer.valueOf(iservice.add(item)), HttpStatus.OK);
	}
	
	@PostMapping(path = "/buy")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Boolean> buy_item(@RequestBody BuyJson json) throws URISyntaxException, IOException, EncodeException {	
		Item item;
		String username;
		int itemId;
		int buyerId;
		try {
			username = json.getUsername();
			itemId = json.getItemId();

			item = iservice.get(itemId);
			buyerId = uservice.getID(username);
		} catch(Exception e) {
			return new ResponseEntity<> (Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}

		if (item == null || item.isSold() || item.getSellerId() == buyerId) {
			return new ResponseEntity<> (Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}

		// NEED TO PASS IN BUYER ID 
		item.setBuyerId(uservice.getID(username));
		iservice.update(item);
		Boolean bool = iservice.update_sell(item, username);
		if(bool) return new ResponseEntity<> (Boolean.TRUE, HttpStatus.OK);
		else return new ResponseEntity<> (Boolean.FALSE, HttpStatus.BAD_REQUEST);

	}
}	