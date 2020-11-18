package com.csci201.marketplace.user.model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.csci201.marketplace.Store.Store;
import com.csci201.marketplace.item.*;

public class UserThread extends Thread {
	private Lock lock;
	private Condition acceptSale;
	private String action;
	private Item item;
	private User buyer;
	private User seller;
	//have users network and talk to one another
	
	public UserThread(String action, Item item, User buyer, User seller) {
		this.action = action;
		this.item = item;
		this.buyer = buyer;
		this.seller = seller;
		
		lock = new ReentrantLock();
		acceptSale = lock.newCondition();
	}

	//adding something up for sale
	public void sell() {
		//add a new sql entry of the item
		Store.getSellers().get(this.seller).add(item);
	}

	//wanting to buy something
	public void buyRequest() {
		//update item sql entry
		Store.getBuyers().get(item).add(buyer);
	}

	//when successfully sold something
	public void acceptBid() {
		//delete item sql entry
		Store.getSellers().get(this.seller).remove(item);
	}

	//Successfully process sale after sale is approved
	public void processSale() {
		//delete from buyer
		Store.getBuyers().remove(item);
	}



	public void run() {
		ItemDAO dao = ItemDAO.getInstance();
		
		try {
			if (action.equals("sell")) {
				sell();
			}
			else if (action.equals("buy")) {
				try {
					lock.lock();
					System.out.println("Waiting for seller to process " + this.item.getName() + ".");
					acceptSale.await();
					synchronized(this) {
						buyRequest();
						processSale();
					}
				}
				catch(InterruptedException ie) {
					System.out.println("interrupted " + ie.getMessage());
				}
			}
			else if (action.equals("approve")) {
				acceptBid();
				System.out.println("Seller has approved the sale of" + this.item.getName() + " to " + this.buyer.getName() + ".");
				synchronized(this) {
					acceptSale.signal();
					//alert losers
					for (User u: Store.getBuyers().get(item)) {
						dao.send_sold_msg(item, u.getName());
					}
				}
				
			}
			else
				throw new Exception();
		}
		catch(Exception e) {
			System.out.println("Malformatted action");
		}
		
		
		
			
	}
	
}
