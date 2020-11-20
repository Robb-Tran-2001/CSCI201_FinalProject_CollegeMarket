package com.csci201.marketplace.item.dao;

import java.io.IOException;
import java.util.*;

import javax.sql.DataSource;
import javax.websocket.EncodeException;

import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.pushnotif.model.*;
import com.csci201.marketplace.pushnotif.websocket.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.stereotype.Component;
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
	
	@Override
	public void getAll() {
		String SQL = "SELECT * FROM Items";
		List<Item> it = jdbcTemplate.query(SQL, new ItemMapper());
		items = it;
	}
	
	@Override
	public List<Item> listAll() {
		this.getAll();
		return new ArrayList<Item>(items);
    }
	
	@Override
	public Item get(int id) {
		this.getAll();
		//int counter = 0;
				
		for (Item item : items) {
			if (item.getItemId() == id) {
				return item;
			}
			//counter++;
		}
		
		String SQL = "SELECT * FROM Items WHERE Items.item_id = " + id;
		List<Item> it = jdbcTemplate.query(SQL, new ItemMapper());
		if(it.isEmpty()) return null;
		else return it.get(0);
	}
	
	
	@Override
	public int add(Item item) {
		items.add(item);
		
		jdbcTemplate.update("INSERT INTO Items (seller_id, name, price)"
				+ "VALUES (?, ?, ?)", 
				item.getSellerId(), item.getName(), item.getPrice());
		
		// Update Item ID of added Item 
		String SQL = "SELECT * FROM Items ORDER BY Items.item_id DESC LIMIT 1";
		List<Item> it = jdbcTemplate.query(SQL, new ItemMapper());
		Item i = it.get(0);
		item.setItemId(i.getItemId());
		
		// Add extra fields too 
		if(item.getBuyerId() != 0) {
			String update = "UPDATE Items i SET i.buyer_id = ? WHERE i.item_id = ?";
			jdbcTemplate.update(update, item.getBuyerId(), item.getItemId());
		} 
		if(item.getDescription() != null) {
			String update = "UPDATE Items i SET i.description = ? WHERE i.item_id = ?";
			jdbcTemplate.update(update, item.getDescription(), item.getItemId());
		}
		if(item.getPictures() != null) {
			String update = "UPDATE Items i SET i.images_json = ? WHERE i.item_id = ?";
			jdbcTemplate.update(update, item.getPictures(), item.getItemId());
		}
		
		return item.getItemId();
	}
	
	
	@Override
	public boolean delete(int id) {
		for (Item item : items) {
			if (item.getItemId() == id) {
				items.remove(item);
				jdbcTemplate.update("DELETE FROM Items i WHERE i.item_id = ?", id);
				return true;
			}
		}
		
		return false;
	}

	
	@Override
	public boolean update(Item item) throws IOException, EncodeException {
		int id = item.getItemId();
		
		int counter = 0;
		
		for (Item i : items) {
			if (i.getItemId() == id) {
				
				
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
				if(item.getDescription() != null) {
					jdbcTemplate.update("UPDATE Items i SET i.images_json = ? WHERE i.item_id = ?",
							item.getPictures(),
							item.getItemId());
				}
				
				return true;
			}
			counter++;
		}
		
		return false;
	}
	
	@Override
	public boolean update_sell(Item item, String username) throws IOException, EncodeException {
		int id = item.getItemId();
		
		int counter = 0;
		
		for (Item i : items) {
			if (i.getItemId() == id) {
				
				if (i.isSold() == false && item.isSold() == true) {
					// some broadcast notification here
					System.out.println("Enters the push notification if");
					send_sold_msg(i, username);
				}
				
				
				items.set(counter, item);
				return true;
			}
			counter++;
		}
		
		return false;
	}
	
	@Override
	public void send_sold_msg(Item item, String username) throws IOException, EncodeException {
		System.out.println("Enters sold message()");
		
		Message message = new Message();
		
		String temp = item.getName() + " was just sold by " + username + "! Better luck next time";
		
		System.out.println("\tMessage: " + temp);
		
		message.setMsg(temp);
		
//		Message message1 = new Message();
//		message1.setMsg("henlo friend");
		
		PushEndpoint.broadcast(message);
	}
}
