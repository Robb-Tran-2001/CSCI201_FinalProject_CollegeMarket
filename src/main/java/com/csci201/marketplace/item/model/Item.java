package com.csci201.marketplace.item.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csci201.marketplace.Store.Store;

public class Item {

	private int itemId;
	private int sellerId;
	private int buyerId;
	private String name;
	private String description;
	private float price;
	
	public Item() {}
	
	public Item(int sellerId, String name, double price) {
		this.sellerId = sellerId;
		this.buyerId = 0;
		this.name = name;
		this.price = (float)price;
	}

	public Item(int sellerId, String name, String description, double price) {
		this.sellerId = sellerId;
		this.buyerId = 0;
		this.name = name;
		this.description = description;
		this.price = (float)price;
	}

	public Item(int itemId, int sellerId, String name, double price) {
		this.itemId = itemId;
		this.sellerId = sellerId;
		this.buyerId = 0;
		this.name = name;
		this.price = (float)price;
	}

	public Item(int itemId, int sellerId, int buyerId, String name, String description, double price) {
		this.itemId = itemId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.name = name;
		this.description = description;
		this.price = (float)price;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = (float)price;
	}

	public boolean isSold() {
		return buyerId != 0;
	}

}
