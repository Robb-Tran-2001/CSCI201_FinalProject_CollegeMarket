package com.csci201.marketplace.item;

import java.util.*;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class ItemDAO {
	private static ItemDAO instance;
	private static List<Item> items = new ArrayList<Item>();
	private static DataSource dataSource;
	private static JdbcTemplate jdbcTemplateObject;
	
	static {
		items.add(new Item("PS5", (float) 2., new ArrayList<String>(Arrays.asList("www.amazon.com/pic1", "www.sony.com/pic2")), "This is a PS5.", 100));
		items.add(new Item("pencil", (float) 534.25, new ArrayList<String>(Arrays.asList("www.pencils.com/pic1", "www.writing.com/pic2")), "Cool pencil.", 302));
	}
	
	@Autowired
	public static void setDataSource(DataSource ds) {
		dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(ds);
	}
	
	public static ItemDAO getInstance() {
		if (instance == null) {
			return new ItemDAO();
		}
		
		return instance;
	}
	
	public void getAll() {
		String SQL = "SELECT * FROM Items";
		List<Item> it = jdbcTemplateObject.query(SQL, new ItemMapper());
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
		List<Item> it = jdbcTemplateObject.query(SQL, new ItemMapper());
		return it.get(0);
	}
	
	public int add(Item item) {
//		int new_id = items.size() + 1;
//		
//		item.setItemid(Integer.toString(new_id));
		
		items.add(item);
		
		jdbcTemplateObject.update("INSERT INTO Items (item_id, seller_id, buyer_id, name, description, price, images)"
				+ "VALUES (?, ?, ?, ?, ?, ?)", 
				item.getItemId(), item.getSellerId(), null, item.getName(), item.getDescription(), item.getPrice(), item.getPicturesString());
		
		return item.getItemId();
	}
	
	public boolean delete(int id) {
		int counter = 0;
		
		for (Item item : items) {
			if (item.getItemId() == id) {
				items.remove(counter);
				return true;
			}
			counter++;
		}
		
		return false;
	}
	
	public boolean update(Item item) {
		int id = item.getItemId();
		
		int counter = 0;
		
		for (Item i : items) {
			if (i.getItemId() == id) {
				items.set(counter, item);
				return true;
			}
			counter++;
		}
		
		return false;
	}
}
