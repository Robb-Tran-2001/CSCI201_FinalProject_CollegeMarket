package com.baeldung.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.websocket.EncodeException;

import org.junit.Test;

import com.baeldung.item.Item;
import com.baeldung.item.ItemDAO;

public class ItemDAOTest {
	ItemDAO dao = ItemDAO.getInstance();

	@Test
	public void test_get() {
		//System.out.println(dao.get("500"));
		assertEquals("No items exist with id 500, so get() should return null", null, dao.get("500"));
		assertEquals("Item with id 100 exists, so get(\"100\") must return the item with id 100", "100", dao.get("100").getItemid());
	}
	
	@Test
	public void test_add_and_delete() {
		dao.add(new Item("XBOX", (float) 7., 
				new ArrayList<String>(Arrays.asList("www.ebay.com/pic1", "www.microsoft.com/pic2")), "This is a XBOX.", "20"));
		
		assertEquals("Either get() or add() is wrong", "20", dao.get("20").getItemid());
		
		dao.delete(20);
		
		assertEquals("After deletion of an item, get() of that item's id should return null", null, dao.get("20"));
	}
	
	@Test
	public void test_update() throws IOException, EncodeException {
		Item item = dao.get("302");
		
		assertEquals("Since item with 302 exists, should return true", true, dao.update(item));
		
		assertEquals("Since item with 450 doesn't exist, should return false", false, dao.update(new Item("XBOX", (float) 7., 
				new ArrayList<String>(Arrays.asList("www.ebay.com/pic1", "www.microsoft.com/pic2")), "This is a XBOX.", "450")));
	}
	
	@Test
	public void test_listAll() {
		Item item1 = new Item("PS5", (float) 2., 
				new ArrayList<String>(Arrays.asList("www.amazon.com/pic1", "www.sony.com/pic2")), "This is a PS5.", "100");
		
		Item item2 = new Item("your mom", (float) 534.25, 
				new ArrayList<String>(Arrays.asList("www.pencils.com/pic1", "www.writing.com/pic2")), "Cool pencil.", "302");
		
		List<Item> compare_list = new ArrayList<Item>();
		compare_list.add(item1);
		compare_list.add(item2);
				
		List<Item> list = dao.listAll();
		
		for (int i = 0; i < list.size(); i++) {
			assertEquals("Items returned by listAll() have wrong ids", compare_list.get(i).getItemid(), list.get(i).getItemid());
		}
	}

}
