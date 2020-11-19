package com.csci201.marketplace.Store;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.csci201.marketplace.user.model.User;
import com.csci201.marketplace.user.model.UserThread;
import com.csci201.marketplace.item.*;
import com.csci201.marketplace.item.dao.ItemDAOImpl;
import com.csci201.marketplace.item.model.Item;

public class Store {
    private static Map<User, Set<Item>> sellers = new HashMap<User, Set<Item>>();
    private static Map<Item, Queue<User>> buyers = new HashMap();
    private static Set<Item> items = new HashSet();
    private static Queue<String> actions = new Queue<String>();
    
    private List<User> allUser = new ArrayList<>();
    
    public static void main(String[] args) {
    	/*
    	check if a user is created -> add buyer or seller
    	check if user wants to sell item -> add to items, add to seller's items
    	check if user (buyer) wants to buy item
    	check if user (seller) wants to approve sale
    	*/
		ItemDAOImpl dao = ItemDAOImpl.getInstance();
		
    	ExecutorService executor = Executors.newCachedThreadPool();
    	while(true) {
    		/* receive the item in question, the buyer (optional), 
    		 * the seller (optional), and the action (buy-item, sell-item, approve-item)
    		 * 
    		 * Case 1: Add item for sale: seller = non-null, buyer = null
    		 * Case 2: Request to buy item:  seller = null, buyer = non-null
    		 * Case 3: Approve sale: seller = non-null, buyer = non-null
    		 * 
    		 */
    		
    		//action item format ex. -> "sell-" + item.getName()
    		
    		if (!actions.isEmpty()) {
    			//obtain item from string
    			String currentAction = actions.remove();
    			String itemName = getItemNameFromAction(currentAction);
    			
    			if (currentAction.contains("sell")) {
    				executor.execute(new UserThread("sell", getItemFromName(itemName), null, getUserFromId(getItemFromName(itemName).getSellerId())));
    			}
    			else if (currentAction.contains("buy")) {
    				executor.execute(new UserThread("buy", getItemFromName(itemName), getUserFromId(getItemFromName(itemName).getBuyerId()), null));
    			}
    			else if (currentAction.contains("approve")) {
    				executor.execute(new UserThread("approve", getItemFromName(itemName), getUserFromId(getItemFromName(itemName).getBuyerId()), getUserFromId(getItemFromName(itemName).getSellerId())));
    			}
    			else if (currentAction.contains("adduser")){
    				allUser.add(getUserFromId(Integer.parseInt(itemName)));
    			}
    		}
    	}
    	
    	executor.shutdown();
    	while(!executor.isTerminated()) {
			Thread.yield();
		}
    }

    public static Map<User, Set<Item>> getSellers() {
        return sellers;
    }

    public static Map<Item, Queue<User>> getBuyers() {
        return buyers;
    }
    
    public static Item getItemFromName(String name) {
        for(Item i: items) {
        	if (i.getName().equals(name)) {
        		return i;
        	}
        }
        return null;
    }
    
    public static String getItemNameFromAction(String action) {
        int index = action.indexOf('-');
        return action.substring(index + 1);
    }
    
    public static User getUserFromId(int id) {
        for (User u: allUser) {
        	if (u.getUserID() == id) {
        		return u;
        	}
        }
    }
    
}
