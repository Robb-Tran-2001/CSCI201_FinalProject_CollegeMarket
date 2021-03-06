package com.csci201.marketplace.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User { //User interacts with mapper
	private transient int userID;
	private String name;
	private String password;

	//itemID = key, bid to buy = value
//	private transient Set<Integer> toBuy;
//	private transient Set<Integer> forSale;

	
	public User(int id, String name, String password, int userID)
	{
		this.userID = id;
		this.name = name;
		this.password = password;
		//Store.getUsers().add(this);
	}

	public User(@JsonProperty("name")String name, @JsonProperty("password") String password)
	{
		//this.userID = userID;
		this.name = name;
		this.password = password;
		//Store.getUsers().add(this);
	}

    public User() {

    }

    public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public Set<Integer> getForSale() {
//		return forSale;
//	}
//
//	public void setForSale(Set<Integer> forSale) {
//		this.forSale = forSale;
//	}
//
//	public Set<Integer> getToBuy() {
//		return toBuy;
//	}
//
//	public void setToBuy(Set<Integer> toBuy) {
//		this.toBuy = toBuy;
//	}
	
}