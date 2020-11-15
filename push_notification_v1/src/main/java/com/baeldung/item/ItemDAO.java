package com.baeldung.item;


import java.io.IOException;
import java.util.*;

import javax.websocket.EncodeException;

import com.baeldung.websocket.*;
import com.baeldung.model.*;


public class ItemDAO {
	private static ItemDAO instance;
	private static List<Item> items = new ArrayList<Item>();
	
	static {
		System.out.println("Hello there!");
		items.add(new Item("PS5", (float) 2., new ArrayList<String>(Arrays.asList("www.amazon.com/pic1", "www.sony.com/pic2")), "This is a PS5.", "100"));
		items.add(new Item("pencil", (float) 534.25, new ArrayList<String>(Arrays.asList("www.pencils.com/pic1", "www.writing.com/pic2")), "Cool pencil.", "302"));
	}
	
	public static ItemDAO getInstance() {
		if (instance == null) {
			return new ItemDAO();
		}
		
		return instance;
	}
	
	public List<Item> listAll() {
        return new ArrayList<Item>(items);
    }
	
	public Item get(String id) {
		//int counter = 0;
		
		for (Item item : items) {
			if (item.getItemid().equals(id)) {
				return item;
			}
			//counter++;
		}
		
		return null;
	}
	
	public String add(Item item) {
//		int new_id = items.size() + 1;
//		
//		item.setItemid(Integer.toString(new_id));
		
		items.add(item);
		
		return item.getItemid();
	}
	
	public boolean delete(int id) {
		int counter = 0;
		
		for (Item item : items) {
			if (Integer.parseInt(item.getItemid()) == id) {
				items.remove(counter);
				return true;
			}
			counter++;
		}
		
		return false;
	}
	
	public boolean update(Item item) throws IOException, EncodeException {
		String id = item.getItemid();
		
		int counter = 0;
		
		for (Item i : items) {
			if (i.getItemid().equals(id)) {
				
//				if (i.isSold() == false && item.isSold() == true) {
//					// some broadcast notification here
//					System.out.println("Enters the push notification if");
//					send_sold_msg(i);
//				}
//				
				
				items.set(counter, item);
				return true;
			}
			counter++;
		}
		
		return false;
	}
	
	public boolean update_sell(Item item, String username) throws IOException, EncodeException {
		String id = item.getItemid();
		
		int counter = 0;
		
		for (Item i : items) {
			if (i.getItemid().equals(id)) {
				
				if (i.isSold() == false && item.isSold() == true) {
					// some broadcast notification here
					System.out.println("Enters the push notification if");
					send_sold_msg(i, username);
				}
				
				
				items.set(counter, item);
				return true;
			}
			counter++;
		}
		
		return false;
	}
	
	public void send_sold_msg(Item item, String username) throws IOException, EncodeException {
		System.out.println("Enters sold message()");
		
		Message message = new Message();
		
		String temp = item.getName() + " was just sold by " + username + "! Better luck next time";
		
		System.out.println("\tMessage: " + temp);
		
		message.setMsg(temp);
		
//		Message message1 = new Message();
//		message1.setMsg("henlo friend");
		
		PushEndpoint.broadcast(message);
	}
}
