package com.csci201.marketplace.user.dao;

import java.util.List;

import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.user.model.User;

/**
 * Only for returning items when listing all available items
 *
 */
public class Request {

	private int itemId;
	private String name;
	private float price;
	private String description;
	private String buyerName;
	
	public Request() {}
	
	public Request(String name, double price) {
		this.name = name;
		this.price = (float)price;
	}

	public Request(int itemId, String name, double price) {
		this.itemId = itemId;
		this.name = name;
		this.price = (float)price;
	}
	
	public Request(Item item, String buyername) {
		this.itemId = item.getItemId();
		this.name = item.getName();
		this.price = item.getPrice();
		this.description = item.getDescription();
		this.buyerName = buyername; //do it in service
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
	
	public String getbuyerName() {
		return buyerName;
	}

	public void setbuyerId(String buyerName) {
		this.buyerName = buyerName;
	}


}
