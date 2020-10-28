package com.csci201.marketplace.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item {

	private int itemId;
	private int sellerId;
	private int buyerId;
	private String name;
	private String description;
	private float price;
	private List<String> pictures = null;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public Item() {}
	
	public Item(String name, float price, List<String> pictures, String description, int itemid) {
		this.name = name;
		this.price = price;
		this.pictures = pictures;
		this.description = description;
		this.itemId = itemid;
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

	public void setPrice(float price) {
		this.price = price;
	}

	public List<String> getPictures() {
		return pictures;
	}
	
	public String getPicturesString() {
		StringBuilder s = new StringBuilder("");
		for(String i : pictures) s.append(i + " ");
		return s.toString();
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
