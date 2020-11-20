package com.csci201.marketplace.item.api;

public class SellItemForm {

	private String name;
	private float price;
	private String description;
	private String username;
	
	public String getName() {
		return this.name;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getUsername() {
		return this.username;
	}
	
}
