package com.csci201.marketplace.item;

import com.csci201.marketplace.item.*;
import com.csci201.marketplace.item.api.*;
import com.csci201.marketplace.item.dao.*;
import com.csci201.marketplace.item.model.*;
import com.csci201.marketplace.item.service.*;

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
	public static ItemService itemService;
	
//	public static ItemDAO it = ItemDAO.getInstance();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		ApplicationContext context = SpringApplication.run(SpringBootApplication.class, args);
		ItemService itemService = context.getBean(ItemService.class);
		
		
		printItems();
		Item a = new Item();
		a.setItemId(115);
		a.setSellerId(10);
		a.setName("printer");
		a.setPrice(49.99);
		itemService.add(a);
		printItems();
		
		
	}
	
	public static void printItems() {
		itemService.getAll();
		List<Item> list = itemService.listAll();
		for(Item i : list) {
			System.out.println("#" + i.getItemId() + "  " + i.getName() + "\t$" + i.getPrice());
		}
	}
}
