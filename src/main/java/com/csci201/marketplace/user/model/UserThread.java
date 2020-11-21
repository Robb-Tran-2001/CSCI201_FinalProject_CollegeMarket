package com.csci201.marketplace.user.model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;

import com.csci201.marketplace.Store.*;
import com.csci201.marketplace.item.dao.ItemDAOImpl;
import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.item.service.ItemService;

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
		Store.getSellers().get(this.seller).add(item);
	}

	//wanting to buy something
	public void buyRequest() {
		Store.getBuyers().get(item).add(buyer);
	}

	//when successfully sold something
	public void acceptBid() {
		Store.getSellers().get(this.seller).remove(item);
	}

	//Successfully process sale after sale is approved
	public void processSale() {
		Store.getBuyers().remove(item);
	}



	public void run() {
		/*
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url("jdbc:mysql://localhost:3306/marketplace?createDatabaseIfNotExist=true");
		dataSourceBuilder.username("root");
		dataSourceBuilder.password("root");
		DataSource ds = dataSourceBuilder.build();
		ItemService itemservice = new ItemService(new ItemDAOImpl(ds));
		*/

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
