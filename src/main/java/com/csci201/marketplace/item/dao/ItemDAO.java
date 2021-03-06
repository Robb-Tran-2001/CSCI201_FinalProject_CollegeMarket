package com.csci201.marketplace.item.dao;

import java.io.IOException;
import java.util.List;

import javax.websocket.EncodeException;

import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.item.model.ItemSimple;

public interface ItemDAO {
	public void getAll();
	public List<Item> listAll();
	public List<ItemSimple> listAllSimple(String un);
	public Item get(int id);
	public int add(Item item);
	public boolean delete(int id);
	public boolean update(Item item) throws IOException, EncodeException ;
}
