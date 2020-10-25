package com.csci201.marketplace.item;

import java.util.*;


public class ItemDAO {
	private static ItemDAO instance;
	private static List<Item> items = new ArrayList<Item>();
	
	static {
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
	
	public boolean update(Item item) {
		String id = item.getItemid();
		
		int counter = 0;
		
		for (Item i : items) {
			if (i.getItemid().equals(id)) {
				items.set(counter, item);
				return true;
			}
			counter++;
		}
		
		return false;
	}
}
