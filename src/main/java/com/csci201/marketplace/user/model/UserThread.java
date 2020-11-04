package com.csci201.marketplace.user.model;

import com.csci201.marketplace.Store.Store;

public class UserThread extends Thread {
	private User user;
	//have users network and talk to one another

	//adding something up for sale
	public void sell(int item) {
		//add a new sql entry of the item
		Store.getSellers().get(this).add(item);
	}

	//wanting to buy something
	public void buyRequest(int item) {
		//update item sql entry
		Store.getBuyers().get(item).add(user);
	}

	//when successfully sold something
	public void acceptBid(int item, User buyer) {
		//delete item sql entry
		Store.getSellers().get(this).remove(item);
	}

	//successuflly bought something
	public void buyAccept(int item, User seller) {
		//delete from buyer
		Store.getBuyers().remove(item);
	}



	public void run() {
		
	}
	
}
