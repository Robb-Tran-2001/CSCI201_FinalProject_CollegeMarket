package com.csci201.marketplace.item.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuyJson {
	@JsonProperty("username")
	private String username;
	@JsonProperty("itemid")
	private int itemid;
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String s) {
		this.username = s;
	}
	
	public int getItemId() {
		return this.itemid;
	}
	
	public void setItemId(int i) {
		this.itemid = i;
	}
}