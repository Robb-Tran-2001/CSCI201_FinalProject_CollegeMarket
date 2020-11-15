package com.csci201.marketplace.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
//@ComponentScan("com.csci201.marketplace")
//@ComponentScan(classes = {ItemService.class, ItemDAO.class})
//@ContextConfiguration(classes = {ItemService.class, ItemDAO.class})
public class Test {
	
//	@Qualifier("ItemService")
	@Autowired
	public static ItemDAO itemDao;
	
//	public static ItemDAO it = ItemDAO.getInstance();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		ApplicationContext context = SpringApplication.run(SpringBootApplication.class, args);
		ItemDAO itemDao = context.getBean(ItemDAO.class);
		
		
		printItems();
		Item a = new Item();
		a.setItemId(115);
		a.setSellerId(10);
		a.setName("printer");
		a.setPrice(49.99);
		itemDao.add(a);
		printItems();
		
		
	}
	
	public static void printItems() {
		itemDao.getAll();
		List<Item> list = itemDao.listAll();
		for(Item i : list) {
			System.out.println("#" + i.getItemId() + "  " + i.getName() + "\t$" + i.getPrice());
		}
	}
}
