package com.csci201.marketplace.item.api;

import com.csci201.marketplace.item.Test;
import com.csci201.marketplace.item.api.*;
import com.csci201.marketplace.item.dao.*;
import com.csci201.marketplace.item.model.*;
import com.csci201.marketplace.item.service.*;

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

@Path("/items")
public class ItemResource {
	
	private ItemService itemService = Test.itemService;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> list() {
        return itemService.listAll();
    }
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Item item = itemService.getItemById(id);
		
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
		int itemID = itemService.add(item);
		URI uri = new URI("/items/" + itemID);
		return Response.created(uri).build();
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response put(Item item) throws URISyntaxException {
		boolean bool = itemService.update(item);
		
		if (bool) {
			return Response.ok().build();
		}
		
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		boolean bool = itemService.delete(id);
		
		if (bool) {
			return Response.ok().build();
		}
		
		return Response.notModified().build();
	}
}
