package com.csci201.marketplace.item.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.csci201.marketplace.item.model.Item;

public class ItemMapperSimple implements RowMapper<Item> {
	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		Item item = new Item();
		item.setItemId(rs.getInt("item_id"));
		item.setName(rs.getString("name"));
		item.setPrice(rs.getFloat("price"));
		
		return item;
	}
}
