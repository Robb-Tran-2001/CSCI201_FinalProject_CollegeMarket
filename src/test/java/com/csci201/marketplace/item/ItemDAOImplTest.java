package com.csci201.marketplace.item;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.websocket.EncodeException;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import com.csci201.marketplace.item.dao.ItemDAOImpl;
import com.csci201.marketplace.item.model.Item;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import javax.sql.DataSource;
//import org.springframework.test.context.junit.jupiter.*;

//import com.csci201.marketplace.item.Item;
//import com.csci201.marketplace.item.ItemDAO;

@RunWith(SpringRunner.class)
//@ContextConfiguration("classpath:application.properties")
//@TestPropertySource("classpath:application.properties")
//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={com.csci201.marketplace.item.dao.ItemDAOImpl.class,
					     com.csci201.marketplace.user.service.UserService.class,
					     com.csci201.marketplace.user.dao.UserDAO.class,
					     com.csci201.marketplace.user.dao.UserDemoDAO.class},
				properties={"spring.config.name=classpath:application.properties"})
//@PropertySource(value = "classpath:application.properties") 
//@ContextConfiguration(classes={com.csci201.marketplace.item.ItemService.class,
//							   com.csci201.marketplace.item.ItemDAO.class,
//							   com.csci201.marketplace.item.ItemDAOImpl.class,
//							   com.csci201.marketplace.user.service.UserService.class,
//		 					   com.csci201.marketplace.user.dao.UserDAO.class,
//		 					   com.csci201.marketplace.user.dao.UserDemoDAO.class},
//					  properties={"spring.config.name=myapp"})
//@JdbcTest
//@Sql({"../resources/data-mysql.sql", "../resources/schema-mysql.sql"})
public class ItemDAOImplTest {
	@Autowired 
	private ItemDAOImpl itemDao;
	
	private Item xbox = new Item(0, "XBOX", "This is a XBOX", 7.);
	private int xboxId;
	private Item ps5 = new Item(0, "PS5", "This is a PS5", 12.);
	private int ps5Id;
	private Item xboxUpdated = new Item(0, "XBOX", "This is an expensive XBOX", 107.);


//	private static DataSource ds = 
//	private DataSource dataSource = null;
	@Autowired
	private static JdbcTemplate jdbcTemplate;

//	@Autowired
//	public void setDataSource(DataSource ds) {
//		dataSource = ds;
//		jdbcTemplate = new JdbcTemplate(ds);
//	}

	
	@Test
	public void test_get_null() {
		//System.out.println(dao.get("500"));
		assertEquals("No items exist with id 500, so get() should return null", null, itemDao.get(500));
	}
	
	@Test
	public void test_add() {
		String sql = "SELECT COUNT(*) FROM Items";
		int rc0 = jdbcTemplate.queryForObject(sql, Integer.class);
		
		// Test XBOX add 
		xboxId = itemDao.add(xbox);
		int rc1 = jdbcTemplate.queryForObject(sql, Integer.class);
		assertEquals("After adding an item, rowCount of Item table should increase by 1", rc0 + 1, rc1);
		
		// Test PS5 add
		ps5Id = itemDao.add(ps5);
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
						xbox.getItemId(), itemDao.get(xboxId).getItemId()); 
		assertEquals("Item [xbox] returns the correct item: T/F", true, item_isEqual(xbox, itemDao.get(xboxId)));
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
		
		itemDao.delete(xboxId);
		int rc1 = jdbcTemplate.queryForObject(sql, Integer.class);
		assertEquals("After deletion of item [XBOX], get() should return null", null, itemDao.get(xboxId));
		assertEquals("After deletion of item [XBOX], DB rowCount should decrease by 1", rc0, rc1 - 1);
	}
	
	@Test
	public void test_update() throws IOException, EncodeException {
		
		assertEquals("Since item with ID 3002 doesn't exist, should return false", 
						false, itemDao.update(new Item(3002, 14, "Non-existent item", 1.99)));
		
		assertEquals("Since item [xboxUpdated] does exist, should return true", true, itemDao.update(xboxUpdated));
		assertEquals("Item [xboxUpdated] update should change DB xbox to match xboxUpdated", true, item_isEqual(xboxUpdated, itemDao.get(xboxId)));

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
		
		List<Item> list = itemDao.listAll();
		
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
			&& (a.getPrice() == b.getPrice());
	}

}
