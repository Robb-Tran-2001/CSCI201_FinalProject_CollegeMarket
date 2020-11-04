package com.csci201.marketplace.user.model;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class User { //User interacts with mapper
	private transient int userID;
	private String name;
	private String password;
	private String email;
	
	//itemID = key, bid to buy = value
	private transient Set<Integer> toBuy;
	private transient Set<Integer> forSale;
	
	
	public User(int userID, String name, String password, String email)
	{
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.email = email;
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

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public Set<Integer> getForSale() {
		return forSale;
	}

	public void setForSale(Set<Integer> forSale) {
		this.forSale = forSale;
	}

	public Set<Integer> getToBuy() {
		return toBuy;
	}

	public void setToBuy(Set<Integer> toBuy) {
		this.toBuy = toBuy;
	}
	
}