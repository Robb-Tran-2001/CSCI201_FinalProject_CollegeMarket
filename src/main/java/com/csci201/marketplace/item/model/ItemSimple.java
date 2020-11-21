package com.csci201.marketplace.item.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csci201.marketplace.Store.Store;


/**
 * Only for returning items when listing all available items
 *
 */
public class ItemSimple {

	private int itemId;
	private String name;
	private float price;
	private String description;
	
	public ItemSimple() {}
	
	public ItemSimple(String name, double price) {
		this.name = name;
		this.price = (float)price;
	}

	public ItemSimple(int itemId, String name, double price) {
		this.itemId = itemId;
		this.name = name;
		this.price = (float)price;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = (float)price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String s) {
		this.description = s;
	}

}
