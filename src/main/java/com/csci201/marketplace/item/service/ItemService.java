package com.csci201.marketplace.item.service;

/**
 * Spring wrapper for Item DAO
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.EncodeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.csci201.marketplace.item.dao.ItemDAOImpl;
import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.item.model.ItemSimple;
import com.csci201.marketplace.pushnotif.model.Message;
import com.csci201.marketplace.pushnotif.websocket.PushEndpoint;

@Service
public class ItemService {
	private final ItemDAOImpl itemDAOImpl;
	
	@Autowired
	public ItemService(@Qualifier("ItemDAOImpl") ItemDAOImpl idao) {
		itemDAOImpl = idao;
	}
	
	public ItemDAOImpl getInstance() {
		return itemDAOImpl;
	}
	
	public void getAll() {
		itemDAOImpl.getAll();
	}
	
	public List<Item> listAll() {
		return itemDAOImpl.listAll();
    }
	
	public List<ItemSimple> listAllSimple(String un) {
		return itemDAOImpl.listAllSimple(un);
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
	
}
