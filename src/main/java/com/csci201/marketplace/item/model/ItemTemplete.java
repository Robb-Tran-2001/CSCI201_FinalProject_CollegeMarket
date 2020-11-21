package com.csci201.marketplace.item.model;

import com.csci201.marketplace.user.model.User;

public class ItemTemplete {
	private Item item;
	private String name;
	private User buyer;
	
	public ItemTemplete(Item item, String name, User buyer) {
		this.item = item;
		this.name = name;
		this.buyer = buyer;	
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public String getName() {
		return this.name;
	}
	
	public User getBuyer() {
		return this.buyer;
	}
}
