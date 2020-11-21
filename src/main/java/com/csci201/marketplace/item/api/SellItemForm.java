package com.csci201.marketplace.item.api;

public class SellItemForm {

	private String name;
	private float price;
	private String description;
	private String username;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String s) {
		this.name = s;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public void setPrice(float f) {
		this.price = f;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String s) {
		this.description = s;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String s) {
		this.username = s;
	}
	
}
