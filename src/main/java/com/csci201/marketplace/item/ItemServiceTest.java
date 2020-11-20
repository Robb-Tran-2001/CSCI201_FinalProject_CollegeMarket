package com.csci201.marketplace.item;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.EncodeException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.item.service.ItemService;

@SpringBootApplication(scanBasePackageClasses = {com.csci201.marketplace.user.service.UserService.class,
												 com.csci201.marketplace.user.dao.UserDAO.class,
												 com.csci201.marketplace.user.dao.UserDemoDAO.class,
												 com.csci201.marketplace.item.service.ItemService.class,
												 com.csci201.marketplace.item.dao.ItemDAOImpl.class})
public class ItemServiceTest implements CommandLineRunner {
	
	@Autowired
	private ItemService itemService;

	
	private Item xbox = new Item(1, "XBOX", "This is a XBOX", 7., "www.ebay.com/pic1 www.microsoft.com/pic2");
	private int xboxId;
	private Item ps5 = new Item(1, "PS5", "This is a PS5", 12., "www.ebay.com/pic2 www.microsoft.com/pic3");
	private int ps5Id;
	private Item xboxUpdated = new Item(1, "XBOXupdated", "This is an expensive XBOX", 107., "www.bestbuy.com/pic1 www.microsoft.com/pic5");


	public static void main(String[] args) {
		SpringApplication.run(ItemServiceTest.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		test_get_null();
		test_add();
		test_get_valid();
		test_delete();
		test_update();
		test_listAll();
	}
	
	private void test_get_null() {
		System.out.println("\n========= START Test GET null ==========");
		
		if(itemService.get(500) == null) System.out.println("No items exist with id 500, so get() should return null: TRUE");
		else System.out.println("No items exist with id 500, so get() should return null: FALSE");
		System.out.println("========== END Test GET null ===========\n");
	}
	
	private void test_add() {
		System.out.println("========= START Test ADD ==========");

		int rc0 = itemService.listAll().size();
		
		// Test XBOX add 
		xboxId = itemService.add(xbox);
		int rc1 = itemService.listAll().size();
		if(item_isEqual(itemService.get(xboxId), xbox)) System.out.println("Added xbox: TRUE");
		else System.out.println("Added xbox: FALSE");
		if(rc0 + 1 == rc1) System.out.println("Added xbox and size increased: TRUE");
		else System.out.println("Added xbox and size increased: FALSE");
		
		// Test PS5 add
		rc0 = itemService.listAll().size();
		ps5Id = itemService.add(ps5);
		rc1 = itemService.listAll().size();
		if(item_isEqual(itemService.get(ps5Id), ps5)) System.out.println("Added ps5: TRUE");
		else System.out.println("Added ps5: FALSE");
		if(rc0 + 1 == rc1) System.out.println("Added ps5 and size increased: TRUE");
		else System.out.println("Added ps5 and size increased: FALSE");
		
		// Update member Item ID's
		xbox.setItemId(xboxId);
		ps5.setItemId(ps5Id);
		xboxUpdated.setItemId(xboxId);
		
		System.out.println("========== END Test ADD ===========\n");

	}
	
	private void test_get_valid() {
		System.out.println("========= START Test GET valid ==========");

		if(item_isEqual(itemService.get(xboxId), xbox)) System.out.println("Xbox exists, so get() should return xbox: TRUE");
		else System.out.println("Xbox exists, so get() should return xbox: FALSE");
		if(itemService.get(xboxId).getItemId() == xboxId) System.out.println("Xbox is correct item_id: TRUE");
		else System.out.println("Xbox is correct item_id: FALSE");
		
		System.out.println("========== END Test GET valid ===========\n");

	}
	
	private void test_delete() {
		System.out.println("========= START Test DELETE ==========");
		
		int rc0 = itemService.listAll().size();
		itemService.delete(xboxId);
		int rc1 = itemService.listAll().size();
		if(rc0 - 1 == rc1) System.out.println("Deleted xbox and size decreased: TRUE");
		else System.out.println("Deleted xbox and size decreased: FALSE");
		if(itemService.get(xboxId) == null) System.out.println("After deletion of Xbox, get() should return null: TRUE");
		else System.out.println("After deletion of Xbox, get() should return null: FALSE");
		
		System.out.println("========== END Test DELETE ===========\n");
	}

	private void test_update() throws IOException, EncodeException {
		System.out.println("========= START Test UDPATE ==========");
		// Add Xbox back in 
		xboxId = itemService.add(xbox);
		xbox.setItemId(xboxId);
		xboxUpdated.setItemId(xboxId);
		
		List<Item> list = itemService.listAll();
		System.out.println("DB list size: " + list.size());
		for(Item i : list) System.out.println(i.getItemId() + ": " + i.getName());


		if(itemService.update(xboxUpdated) == true) System.out.println("Since xbox exists, should return true: TRUE");
		else System.out.println("Since xbox exists, should return true: FALSE");
		if(item_isEqual(itemService.get(xboxId), xboxUpdated)) System.out.println("Xbox has been updated: TRUE");
		else System.out.println("Xbox has been updated: FALSE");
		
		
		list = itemService.listAll();
		System.out.println("DB list size: " + list.size());
		for(Item i : list) System.out.println(i.getItemId() + ": " + i.getName());
		System.out.println("========== END Test UPDATE ===========\n");
	}
	
	private void test_listAll() {
		System.out.println("========= START Test LIST ALL ==========");

		List<Item> compare_list = new ArrayList<Item>();
		compare_list.add(ps5);
		compare_list.add(xboxUpdated);
		List<Item> list = itemService.listAll();
		
		// Compare sizes
		if(compare_list.size() == list.size()) System.out.println("List sizes match: TRUE");
		else System.out.println("List sizes match: FALSE");
		
		// Compare each item
		for (int i = 0; i < list.size(); i++) {
			if(item_isEqual(compare_list.get(i), list.get(i))) System.out.println("Item " + list.get(i).getItemId() + ": " + list.get(i).getName() + " matches: TRUE");
			else System.out.println("Item " + list.get(i).getItemId() + ": " + list.get(i).getName() + " matches: FALSE");
		}
		
		System.out.println("========== END Test LIST ALL ===========\n");
	}
	
	private boolean item_isEqual(Item a, Item b) {
		if(a == null || b == null) return false;
		return (a.getItemId() == b.getItemId()) 
			&& (a.getSellerId() == b.getSellerId())
			&& (a.getBuyerId() == b.getBuyerId())
			&& (a.getName().equals(b.getName()))
			&& (a.getDescription().equals(b.getDescription()))
			&& (a.getPrice() == b.getPrice()) 
			&& (a.getPictures().equals(b.getPictures()));
	}
	
	private void printItem(Item a) {
		System.out.println("=======================\n" + 
						   "Item:     " + a.getName() + "\n" +
						   "ItemID:   " + a.getItemId() + "\n" +
						   "SellerID: " + a.getSellerId() + "\n" +
						   "BuyerID:  " + a.getBuyerId() + "\n" +
						   "Desc:     " + a.getDescription() + "\n" +
						   "Price:    " + a.getPrice() + "\n" +
						   "Pictures: " + a.getPictures() + "\n" +
						   "=======================" );
	}
}
