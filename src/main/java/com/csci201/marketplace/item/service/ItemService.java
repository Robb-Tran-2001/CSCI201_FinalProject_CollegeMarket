package com.csci201.marketplace.item.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.EncodeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.csci201.marketplace.item.dao.ItemDAOImpl;
import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.pushnotif.model.Message;
import com.csci201.marketplace.pushnotif.websocket.PushEndpoint;

@Service
public class ItemService {
	private final ItemDAOImpl itemDAOImpl;
	
	@Autowired
	public ItemService(@Qualifier("ItemDAOImpl") ItemDAOImpl idao) {
		itemDAOImpl = idao;
	}
	
//	public static ItemDAO getInstance() {
//		if (instance == null) {
//			return new ItemDAO();
//		}
//		
//		return instance;
//	}
	
	public void getAll() {
		itemDAOImpl.getAll();
	}
	
	public List<Item> listAll() {
		return itemDAOImpl.listAll();
    }
	
	public List<Item> listAllSimple() {
		return itemDAOImpl.listAllSimple();
	}
	
	public Item get(int id) {
		return itemDAOImpl.get(id);
	}
	
	
	public int add(Item item) {
		return itemDAOImpl.add(item);
	}
	
	
	public boolean delete(int id) {
		return itemDAOImpl.delete(id);
	}

	
	public boolean update(Item item) throws IOException, EncodeException {
		return itemDAOImpl.update(item);
	}
	
	public boolean update_sell(Item item, String username) throws IOException, EncodeException {
		return itemDAOImpl.update_sell(item, username);
	}
	
	public void send_sold_msg(Item item, String username) throws IOException, EncodeException {
		itemDAOImpl.send_sold_msg(item, username);
	}
}
