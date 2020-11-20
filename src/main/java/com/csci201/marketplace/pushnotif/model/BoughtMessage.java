package com.csci201.marketplace.pushnotif.model;

public class BoughtMessage {
	private String buyer;
	private String itemID;
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
}
