package com.csci201.marketplace.pushnotif.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;
import javax.websocket.EncodeException;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.*;

import com.csci201.marketplace.item.dao.ItemDAOImpl;
import com.csci201.marketplace.item.model.Item;

@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
@SpringBootTest
//@JdbcTest
//@Sql({"../resources/data-mysql.sql", "../resources/schema-mysql.sql"})
public class ItemDAOTest {
	@Autowired 
	ItemDAOImpl dao;
	
	private Item xbox = new Item(0, "XBOX", "This is a XBOX", 7., "www.ebay.com/pic1 www.microsoft.com/pic2");
	private int xboxId;
	private Item ps5 = new Item(0, "PS5", "This is a PS5", 12., "www.ebay.com/pic2 www.microsoft.com/pic3");
	private int ps5Id;
	private Item xboxUpdated = new Item(0, "XBOX", "This is an expensive XBOX", 107., "www.bestbuy.com/pic1 www.microsoft.com/pic5");


//	private static DataSource ds = 
	private DataSource dataSource = null;
	private JdbcTemplate jdbcTemplate = null;

	@Autowired
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplate = new JdbcTemplate(ds);
	}

	
	@Test
	public void test_get_null() {
		//System.out.println(dao.get("500"));
		assertEquals("No items exist with id 500, so get() should return null", null, dao.get(500));
	}
	
	@Test
	public void test_add() {
		String sql = "SELECT COUNT(*) FROM Items";
		int rc0 = jdbcTemplate.queryForObject(sql, Integer.class);
		
		// Test XBOX add 
		xboxId = dao.add(xbox);
		int rc1 = jdbcTemplate.queryForObject(sql, Integer.class);
		assertEquals("After adding an item, rowCount of Item table should increase by 1", rc0 + 1, rc1);
		
		// Test PS5 add
		ps5Id = dao.add(ps5);
		int rc2 = jdbcTemplate.queryForObject(sql, Integer.class);
		assertEquals("After adding another item, rowCount of Item table should increase by 2", rc0 + 2, rc2);
		
		// Update member Item ID's
		xbox.setItemId(xboxId);
		ps5.setItemId(ps5Id);
		xboxUpdated.setItemId(xboxId);
	}
	
	@Test 
	public void test_get_valid() {
		assertEquals("Item [xbox] with id " + xboxId + " exists, so get(" + xboxId + ") must return the item with id " + xboxId, 
						xbox.getItemId(), dao.get(xboxId).getItemId()); 
		assertEquals("Item [xbox] returns the correct item: T/F", true, item_isEqual(xbox, dao.get(xboxId)));
	}
	
	@Test
	public void test_delete() {
//		int add1Id = dao.add(new Item(15, "XBOX", "This is a XBOX", 7., "www.ebay.com/pic1 www.microsoft.com/pic2"));
////		dao.add(new Item("XBOX", (float) 7., 
////				new ArrayList<String>(Arrays.asList("www.ebay.com/pic1", "www.microsoft.com/pic2")), "This is a XBOX.", "20"));
//		
//		assertEquals("Added item (XBOX) ID: " + add1Id + "\n"
//					+ "Either get() or add() is wrong", add1Id, dao.get(add1Id).getItemId());
		
		String sql = "SELECT COUNT(*) FROM Items";
		int rc0 = jdbcTemplate.queryForObject(sql, Integer.class);
		
		dao.delete(xboxId);
		int rc1 = jdbcTemplate.queryForObject(sql, Integer.class);
		assertEquals("After deletion of item [XBOX], get() should return null", null, dao.get(xboxId));
		assertEquals("After deletion of item [XBOX], DB rowCount should decrease by 1", rc0, rc1 - 1);
	}
	
	@Test
	public void test_update() throws IOException, EncodeException {
		
		assertEquals("Since item with ID 3002 doesn't exist, should return false", 
						false, dao.update(new Item(3002, 14, "Non-existent item", 1.99)));
		
		assertEquals("Since item [xboxUpdated] does exist, should return true", true, dao.update(xboxUpdated));
		assertEquals("Item [xboxUpdated] update should change DB xbox to match xboxUpdated", true, item_isEqual(xboxUpdated, dao.get(xboxId)));

//		Item item = dao.get("302");
//		assertEquals("Since item with 302 exists, should return true", true, dao.update(item));
//		assertEquals("Since item with 450 doesn't exist, should return false", false, dao.update(new Item("XBOX", (float) 7., 
//				new ArrayList<String>(Arrays.asList("www.ebay.com/pic1", "www.microsoft.com/pic2")), "This is a XBOX.", "450")));
	}
	
	@Test
	public void test_listAll() {
		List<Item> compare_list = new ArrayList<Item>();
		compare_list.add(xboxUpdated);
		compare_list.add(ps5);
		
		List<Item> list = dao.listAll();
		
		for (int i = 0; i < list.size(); i++) {
			assertEquals("Items returned by listAll() match", true, item_isEqual(compare_list.get(i), list.get(i)));
//			assertEquals("Items returned by listAll() match", compare_list.get(i).getItemId(), list.get(i).getItemId());
		}
		
		
//		Item item1 = new Item("PS5", (float) 2., 
//				new ArrayList<String>(Arrays.asList("www.amazon.com/pic1", "www.sony.com/pic2")), "This is a PS5.", "100");
//		
//		Item item2 = new Item("your mom", (float) 534.25, 
//				new ArrayList<String>(Arrays.asList("www.pencils.com/pic1", "www.writing.com/pic2")), "Cool pencil.", "302");
//		
//		List<Item> compare_list = new ArrayList<Item>();
//		compare_list.add(item1);
//		compare_list.add(item2);
//				
//		List<Item> list = dao.listAll();
//		
//		for (int i = 0; i < list.size(); i++) {
//			assertEquals("Items returned by listAll() have wrong ids", compare_list.get(i).getItemId(), list.get(i).getItemId());
//		}
	}
	
	
	private boolean item_isEqual(Item a, Item b) {
		return (a.getItemId() == b.getItemId()) 
			&& (a.getSellerId() == b.getSellerId())
			&& (a.getBuyerId() == b.getBuyerId())
			&& (a.getName().equals(b.getName()))
			&& (a.getDescription().equals(b.getDescription()))
			&& (a.getPrice() == b.getPrice()) 
			&& (a.getPictures().equals(b.getPictures()));
	}

}
