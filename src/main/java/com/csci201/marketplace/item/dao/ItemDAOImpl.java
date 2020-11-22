package com.csci201.marketplace.item.dao;

/**
 * DAO Implementation for Items
 */

import java.io.IOException;
import java.util.*;

import javax.sql.DataSource;
import javax.websocket.EncodeException;

import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.item.model.ItemSimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;


@Repository("ItemDAOImpl")
public class ItemDAOImpl extends JdbcDaoSupport implements ItemDAO {
	
	private List<Item> items = new ArrayList<Item>();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ItemDAOImpl(DataSource ds) {
		setDataSource(ds);
		this.jdbcTemplate = getJdbcTemplate();
		this.getAll();
	}
	
	/**
	 * Retrieve all items from DB
	 */
	@Override
	public void getAll() {
		String SQL = "SELECT * FROM Items";
		List<Item> it = jdbcTemplate.query(SQL, new ItemMapper());
		items = it;
	}
	
	/**
	 * Retrieve all items from DB and return list of Items
	 */
	@Override
	public List<Item> listAll() {
		this.getAll();
		return new ArrayList<Item>(items);
    }
	
	/**
	 * Return list of available (and user filtered) simplified Items
	 */
	@Override
	public List<ItemSimple> listAllSimple(String un) { //show all available items for sale
		String SQL;
		Object[] args;
		if(un != "") {
			// Registered User has username
			args = new Object[1];
			args[0] = un;
			SQL = "SELECT Items.name, Items.price, Items.item_id, Items.description, Users.name AS seller_name\n"
					+ "	FROM Items \n"
					+ "    INNER JOIN Users ON Items.seller_id = Users.user_id\n"
					+ "	WHERE buyer_id IS NULL OR buyer_id = 0 \n"
					+ "		AND Users.name != ?;";
			return jdbcTemplate.query(SQL, args, new ItemSimpleMapper());
		} else {
			// Guest user has empty string as username
			SQL = "SELECT Items.name, Items.price, Items.item_id, Items.description, Users.name AS seller_name\n"
					+ "	FROM Items \n"
					+ "    INNER JOIN Users ON Items.seller_id = Users.user_id\n"
					+ "	WHERE buyer_id IS NULL OR buyer_id = 0;";
			return jdbcTemplate.query(SQL, new ItemSimpleMapper());
		}
		
	}
	
	/**
	 * Return item after itemId lookup 
	 */
	@Override
	public Item get(int id) {
		this.getAll();
		
		// Search in item list 
		for (Item item : items) {
			if (item.getItemId() == id) {
				return item;
			}
		}
		
		// DB search 
		String SQL = "SELECT * FROM Items WHERE Items.item_id = " + id;
		List<Item> it = jdbcTemplate.query(SQL, new ItemMapper());
		if(it.isEmpty()) return null;
		else return it.get(0);
	}
	
	/**
	 * Add new item to store and DB
	 */
	@Override
	public int add(Item item) {
		items.add(item);
		
		// Insert NOT NULL fields into DB
		jdbcTemplate.update("INSERT INTO Items (seller_id, name, price)"
				+ "VALUES (?, ?, ?)", 
				item.getSellerId(), item.getName(), item.getPrice());
		
		// Update Item ID of added Item 
		String SQL = "SELECT * FROM Items ORDER BY Items.item_id DESC LIMIT 1";
		List<Item> it = jdbcTemplate.query(SQL, new ItemMapper());
		Item i = it.get(0);
		item.setItemId(i.getItemId());
		
		// Add extra fields 
		if(item.getBuyerId() != 0) {
			String update = "UPDATE Items i SET i.buyer_id = ? WHERE i.item_id = ?";
			jdbcTemplate.update(update, item.getBuyerId(), item.getItemId());
		} 
		if(item.getDescription() != null) {
			String update = "UPDATE Items i SET i.description = ? WHERE i.item_id = ?";
			jdbcTemplate.update(update, item.getDescription(), item.getItemId());
		}
		
		return item.getItemId();
	}
	
	
	/**
	 * Delete item by itemId
	 * Returns false if not found
	 * NOT USED 
	 */
	@Override
	public boolean delete(int id) {
		// Find in item list
		for (Item item : items) {
			if (item.getItemId() == id) {
				// If found, remove and update DB
				items.remove(item);
				jdbcTemplate.update("DELETE FROM Items i WHERE i.item_id = ?", id);
				return true;
			}
		}
		
		return false;
	}

	
	/**
	 * Update Item in list and DB
	 */
	@Override
	public boolean update(Item item) throws IOException, EncodeException {
		int id = item.getItemId();
		
		int counter = 0;
		
		for (Item i : items) {
			if (i.getItemId() == id) {
				// Found item in item list
				items.set(counter, item);
				
				// Update in DB
				jdbcTemplate.update("UPDATE Items i SET i.seller_id = ?, "
						+ "i.name = ?, "
						+ "i.price = ? "
						+ "WHERE i.item_id = ?",
						item.getSellerId(),
						item.getName(),
						item.getPrice(),
						item.getItemId());
				// Update extra fields 
				if(item.getBuyerId() != 0) {
					jdbcTemplate.update("UPDATE Items i SET i.buyer_id = ? WHERE i.item_id = ?",
							item.getBuyerId(),
							item.getItemId());
				}
				if(item.getDescription() != null) {
					jdbcTemplate.update("UPDATE Items i SET i.description = ? WHERE i.item_id = ?",
							item.getDescription(),
							item.getItemId());
				}
				
				return true;
			}
			counter++;
		}
		
		return false;
	}
}
