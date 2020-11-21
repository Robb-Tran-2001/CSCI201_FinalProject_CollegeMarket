package com.csci201.marketplace.item.api;

public class BuyJson {
	private String username;
	private int itemid;
	
	public String getUsername() {
		return this.username;
	}
	
	public int getItemId() {
		System.out.println("Item ID inside POJO: " + itemid);
		return this.itemid;
	}
}