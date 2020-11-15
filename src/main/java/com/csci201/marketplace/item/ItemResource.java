package com.csci201.marketplace.item;


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

import com.csci201.marketplace.pushnotif.model.Message;
import com.csci201.marketplace.pushnotif.websocket.PushEndpoint;

@Path("/items")
public class ItemResource {
	
	private ItemDAO dao = ItemDAO.getInstance();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> list() {
        return dao.listAll();
    }
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") String id) {
		Item item = dao.get(id);
		
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
		String itemID = dao.add(item);
		URI uri = new URI("/items/" + itemID);
		return Response.created(uri).build();
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response put(Item item) throws URISyntaxException, IOException, EncodeException {
		boolean bool = dao.update(item);
		
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
		
		Item item = new Item(dao.get(id));
		
		if (item.isSold()) {
			// broadcast and return here
			Message temp = new Message();
			temp.setMsg("This has already been sold!");
			PushEndpoint.send_user_msg(username, temp);
			return Response.notModified().build();
		}
		
		item.setSold(true);
		
		boolean bool = dao.update_sell(item, username);
		
		//dao.send_sold_msg(item);
		
		if (bool) {
			return Response.ok().build();
		}
		
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		boolean bool = dao.delete(id);
		
		if (bool) {
			return Response.ok().build();
		}
		
		return Response.notModified().build();
	}
}
