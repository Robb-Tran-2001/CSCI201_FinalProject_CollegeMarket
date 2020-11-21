package com.csci201.marketplace.user.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApproveJson {
	@JsonProperty("username")
	private String username; //seller
	@JsonProperty("itemid")
	private Integer itemid;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getItemId() {
		return itemid;
	}
	public void setItemId(Integer itemid) {
		this.itemid = itemid;
	}
	
}
