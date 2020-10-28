package users;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class User {
	private transient int userID;
	private int age;
	private String mName;
	private String mPassword;
	
	//itemID = key, bid to buy = value
	private transient HashMap<Integer, Double> mBids;

	//when successfully sold something
	public void acceptBid() {

	}
	
	//adding something up for sale
	public void addItem(Integer itemID) {
		//add a new sql entry of the item
	}
	
	//adding a new buyer for the item
	public void buyRequest() {
		//update mBuyers
		
	} 
	
	//
	public void buyAccept() {
		
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	
}