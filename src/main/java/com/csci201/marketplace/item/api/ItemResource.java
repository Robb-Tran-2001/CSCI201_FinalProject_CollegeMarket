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
import org.springframework.stereotype.Repository;

import com.csci201.marketplace.Store.Store;
import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.item.service.ItemService;
import com.csci201.marketplace.pushnotif.model.Message;
import com.csci201.marketplace.pushnotif.websocket.PushEndpoint;
import com.csci201.marketplace.user.api.*;
import com.csci201.marketplace.user.dao.*;
import com.csci201.marketplace.user.model.*;
import com.csci201.marketplace.user.service.UserService;

@Repository
@Path("/items")
public class ItemResource {
	
//	private ItemDAO dao = ItemDAO.getInstance();
	
	private ItemService iservice;
	private UserService uservice;
	
	@Autowired
	public ItemResource(ItemService is, UserService us) {
		this.iservice = is;
		this.uservice = us;
	}
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> list() {
        return iservice.listAll();
    }
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") String id) {
		Item item = iservice.get(Integer.parseInt(id));
		
		if (item == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		else {
			return Response.ok(item, MediaType.APPLICATION_JSON).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Item item) throws URISyntaxException {
		String itemID = Integer.toString(iservice.add(item));
		URI uri = new URI("/items/" + itemID);
		return Response.created(uri).build();
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response put(Item item) throws URISyntaxException, IOException, EncodeException {
		boolean bool = iservice.update(item);
		
		if (bool) {
			return Response.ok().build();
		}
		
		return Response.notModified().build();
	}
	
	@PUT
	@Path("/{username}/sell/{id}")
	public Response sell_item(@PathParam("id") String id, @PathParam("username") String username) throws URISyntaxException, IOException, EncodeException {	
		
		System.out.println("enters the ting");
		
		//String id = "100";
		
		Item item = iservice.get(Integer.parseInt(id));
		
		if (item.isSold()) {
			// broadcast and return here
			Message temp = new Message();
			temp.setMsg("This has already been sold!");
			PushEndpoint.send_user_msg(username, temp);
			return Response.notModified().build();
		}
		
		// NEED TO PASS IN BUYER ID 
		item.setBuyerId(uservice.getID(username));
		iservice.update(item);
		boolean bool = iservice.update_sell(item, username);
		
		//dao.send_sold_msg(item);
		
		if (bool) {
			//UPDATE store.java
			//add action to be threaded
			Store.addAction("sell-" + item.getName());
			//add item to sellers set of items
			User seller = Store.getUserFromId(item.getSellerId());
			Store.getSellers().get(seller).add(item);
			//add item to item-set
			Store.getItems().add(item);
			
			return Response.ok().build();
		}
		
		return Response.notModified().build();
	}
	

	@PostMapping(path = "/buy")
    @Produces(MediaType.APPLICATION_JSON)
	public Response buy_item(@RequestBody BuyJson json) throws URISyntaxException, IOException, EncodeException {	
		
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
			return Response.notModified().build();
		}
		
		// NEED TO PASS IN BUYER ID 
		item.setBuyerId(uservice.getID(username));
		iservice.update(item);
		boolean bool = iservice.update_sell(item, username);
		
		
		if (bool) {
			//UPDATE store.java
			//add action to be threaded
			Store.addAction("buy-" + item.getName());
			//add item to sellers set of items
			User buyer = Store.getUserFromId(item.getBuyerId());
			Store.getBuyers().get(item).add(buyer);
			return Response.ok().build();
		}
		
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		boolean bool = iservice.delete(id);
		
		if (bool) {
			return Response.ok().build();
		}
		
		return Response.notModified().build();
	}
}







