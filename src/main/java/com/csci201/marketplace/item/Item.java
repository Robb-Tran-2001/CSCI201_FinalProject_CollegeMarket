package com.csci201.marketplace.item;


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
	private String pictures;
	
	public Item() {}
	
	public Item(int sellerId, String name, double price, int itemid) {
		this.sellerId = sellerId;
		this.buyerId = -1;
		this.name = name;
		this.price = (float)price;
		this.itemId = itemid;
		Store.getItems().add(this);
	}

	public Item(int sellerId, String name, String description, double price, String images) {
		this.sellerId = sellerId;
		this.buyerId = -1;
		this.name = name;
		this.description = description;
		this.price = (float)price;
		this.pictures = images;
		Store.getItems().add(this);
	}

	public Item(int itemId, int sellerId, int buyerId, String name, String description, double price, String images) {
		this.itemId = itemId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.name = name;
		this.description = description;
		this.price = (float)price;
		this.pictures = images;
		Store.getItems().add(this);
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
//		Test.it.update(this);
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
//		Test.it.update(this);
	}
	
	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
//		Test.it.update(this);
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
//		Test.it.update(this);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
//		Test.it.update(this);
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = (float)price;
//		Test.it.update(this);
	}

	public String getPictures() {
		return pictures;
	}
	
//	public List<String> getPictures() {
//		return pictures;
//	}
	
//	public String getPicturesString() {
//		StringBuilder s = new StringBuilder("");
//		for(String i : pictures) s.append(i + " ");
//		return s.toString();
//	}

	public void setPictures(String s) {
		this.pictures = s;
//		Test.it.update(this);
	}
	
//	public void setPictures(List<String> pictures) {
//		this.pictures = pictures;
//	}

	public boolean isSold() {
		return buyerId != -1;
	}
	
//	public Map<String, Object> getAdditionalProperties() {
//		return this.additionalProperties;
//	}
//
//	public void setAdditionalProperty(String name, Object value) {
//		this.additionalProperties.put(name, value);
//	}

}
