package com.csci201.marketplace.item.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.csci201.marketplace.item.model.Item;

public class ItemMapper implements RowMapper<Item> {
	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		Item item = new Item();
		item.setItemId(rs.getInt("item_id"));
		item.setSellerId(rs.getInt("seller_id"));
		item.setBuyerId(rs.getInt("buyer_id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPrice(rs.getFloat("price"));
		item.setPictures(rs.getString("images_json"));
		
		return item;
	}
}
