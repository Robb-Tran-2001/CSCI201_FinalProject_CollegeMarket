package com.baeldung.item;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item {

	private String name;
	private float price;
	private List<String> pictures = null;
	private String description;
	private String itemid;
	private boolean sold = false;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public Item() {}
	
	public Item(String name, float price, List<String> pictures, String description, String itemid) {
		this.name = name;
		this.price = price;
		this.pictures = pictures;
		this.description = description;
		this.itemid = itemid;
	}
	
	public Item(Item item) {
		this.name = item.name;
		this.price = item.price;
		this.pictures = item.pictures;
		this.description = item.description;
		this.itemid = item.itemid;
		this.sold = item.sold;
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

	public void setPrice(float price) {
		this.price = price;
	}

	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

}
