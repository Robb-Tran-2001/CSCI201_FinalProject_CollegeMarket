package com.csci201.marketplace.item.service;

import com.csci201.marketplace.item.*;
import com.csci201.marketplace.item.api.*;
import com.csci201.marketplace.item.dao.*;
import com.csci201.marketplace.item.model.*;


import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Named ("ItemService")
public class ItemService {

	@Autowired
	ItemDAO itemDao;
	
	public void getAll() {
		itemDao.getAll();
	}
	
	public List<Item> listAll() {
		return itemDao.listAll();
	}
	
	public Item getItemById(int id) {
		return itemDao.get(id);
	}
	
	public int add(Item item) {
		return itemDao.add(item);
	}
	
	public boolean delete(int id) {
		return itemDao.delete(id);
	}
	
	public boolean update(Item item) {
		return itemDao.update(item);
	}

}
