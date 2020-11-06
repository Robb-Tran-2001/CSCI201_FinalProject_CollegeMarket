package com.csci201.marketplace.item;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAO extends JdbcDaoSupport {
	
	@Autowired 
	DataSource dataSource;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	private static ItemMapper itemMapper;
//	private static ItemDAO instance;
	private static List<Item> items = new ArrayList<Item>();
//	private static DataSource dataSource;
//	private static JdbcTemplate jdbcTemplateObject;
	
	static {
		items.add(new Item(100, "PS5", "This is a PS5.", 2., "www.amazon.com/pic1 www.sony.com/pic2"));
		items.add(new Item(100, "pencil", "Cool pencil.", 534.25, "www.pencils.com/pic1 www.writing.com/pic2"));
	}
	
//	@Autowired
//	public static void setDataSource(DataSource ds) {
//		dataSource = ds;
//		jdbcTemplateObject = new JdbcTemplate(ds);
//	}
	
//	public static ItemDAO getInstance() {
//		if (instance == null) {
//			return new ItemDAO();
//		}
//		
//		return instance;
//	}
	
	public void getAll() {
		String SQL = "SELECT * FROM Items";
		List<Item> it = jdbcTemplate.query(SQL, new ItemMapper());
		items = it;
	}
	
	public List<Item> listAll() {
        return new ArrayList<Item>(items);
    }
	
	public Item get(int id) {
		//int counter = 0;
		
		for (Item item : items) {
			if (item.getItemId() == id) {
				return item;
			}
			//counter++;
		}
		
		
		String SQL = "SELECT * FROM Items WHERE Items.itemId = " + id;
		List<Item> it = jdbcTemplate.query(SQL, new ItemMapper());
		return it.get(0);
	}
	
	public int add(Item item) {
//		int new_id = items.size() + 1;
//		
//		item.setItemid(Integer.toString(new_id));
		
		items.add(item);
		
		jdbcTemplate.update("INSERT INTO Items (item_id, seller_id, buyer_id, name, description, price, images)"
				+ "VALUES (?, ?, ?, ?, ?, ?)", 
				item.getItemId(), item.getSellerId(), null, item.getName(), item.getDescription(), item.getPrice(), item.getPictures());
		
		return item.getItemId();
	}
	
	public boolean delete(int id) {
		int counter = 0;
		boolean removed = false;
		
		for (Item item : items) {
			if (item.getItemId() == id) {
				items.remove(counter);
				removed = true;
			}
			counter++;
		}
		
		jdbcTemplate.update("DELETE FROM Item i WHERE i.item_id = ?", id);
		
		return removed;
	}
	
	public boolean update(Item item) {
		int id = item.getItemId();
		
		int counter = 0;
		boolean updated = false;
		
		for (Item i : items) {
			if (i.getItemId() == id) {
				items.set(counter, item);
				updated = true;
			}
			counter++;
		}
		
		jdbcTemplate.update("UPDATE Items i SET i.seller_id = ?, "
											+ "i.buyer_id = ?, "
											+ "i.name = ?, "
											+ "i.description = ?, "
											+ "i.price = ?, "
											+ "i.images_json = ? "
											+ "WHERE i.item_id = ?",
											item.getSellerId(),
											item.getBuyerId(),
											item.getName(),
											item.getDescription(),
											item.getPrice(),
											item.getPictures(),
											item.getItemId());
		
		return updated;
	}
}
