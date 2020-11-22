package com.csci201.marketplace.item.api;

/**
 * REST Controller for Item backend 
 * Source for REST Controller : https://www.tutorialspoint.com/spring_boot/spring_boot_building_restful_web_services.htm
 */

import java.io.IOException;

import java.net.URISyntaxException;
import java.util.List;

import javax.websocket.EncodeException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.item.model.ItemSimple;
import com.csci201.marketplace.item.service.ItemService;
import com.csci201.marketplace.user.service.UserService;
import com.csci201.marketplace.pushnotif.websocket.MessageController;

@Repository
@RestController
@RequestMapping("/api")
public class ItemResource {

	private ItemService iservice;
	private UserService uservice;
	
	@Autowired
	private MessageController msg_controller;

	@Autowired
	public ItemResource(ItemService is, UserService us) {
		this.iservice = is;
		this.uservice = us;
	}

	
	/**
	 * http://localhost:8080/api/items?name=
	 * Return list of items available not sold by user for Users
	 * 
	 * @param name		Username of User
	 * @return list of ItemSimple
	 */
	@GetMapping(path = "/items", params = {"name"})
	@ResponseBody
    public ResponseEntity<List<ItemSimple>> list(@RequestParam String name) {
		if(name == null) name = "";
		List<ItemSimple> list = iservice.listAllSimple(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	/**
	 * http://localhost:8080/api/items
	 * Return list of items available for guests
	 * 
	 * @return list of ItemSimple
	 */
	// List for guests
	@GetMapping(path = "/items")
	@ResponseBody
    public ResponseEntity<List<ItemSimple>> list() {
		List<ItemSimple> list = iservice.listAllSimple("");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

	/**
	 * http://localhost:8080/api/items/{id}
	 * Return full Item object by itemId
	 * 
	 * @param id		itemId requested
	 * @return Item
	 */
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

	/**
	 * http://localhost:8080/api/sell
	 * Adds new Item to store 
	 * 
	 * @param form 		SellItemForm contains data on new Item to add
	 * @return int itemId
	 * @throws URISyntaxException
	 */
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
	
	/**
	 * http:localhost:8080/buy
	 * Sends request to buy an item 
	 * 
	 * @param json 		BuyJson contains itemId to be bought and buyer Username
	 * @return boolean
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws EncodeException
	 */
	@PostMapping(path = "/buy")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Boolean> buy_item(@RequestBody BuyJson json) throws URISyntaxException, IOException, EncodeException {	
		// Parse input JSON 
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

		// Fail if item doesn't exist, item is sold already, buyer is seller of item
		if (item == null || item.isSold() || item.getSellerId() == buyerId) {
			return new ResponseEntity<> (Boolean.FALSE, HttpStatus.BAD_REQUEST);
		}

		// Update buyerId of Item and update DB  
		item.setBuyerId(uservice.getID(username));
		Boolean bool = iservice.update(item);
		if(bool) {
			// Send push notification of buy request
			try {
				msg_controller.send(username,  item.getName());
			} catch (Exception e) {
				System.out.println("Push notification failed");
				e.printStackTrace();
			}
			return new ResponseEntity<> (Boolean.TRUE, HttpStatus.OK);
		}
		else return new ResponseEntity<> (Boolean.FALSE, HttpStatus.BAD_REQUEST);

	}
}	