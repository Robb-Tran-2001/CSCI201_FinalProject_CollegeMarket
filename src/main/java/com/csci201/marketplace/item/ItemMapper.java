/*
 * Create a static instance of DataSource object in main driver (server class) 
 * 
 * 
 * 
 * 
 * @Configuration
public class JpaConfig {
      
    @Bean
    public DataSource getDataSource() 
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:file:C:/temp/test");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }
}
 */

package com.csci201.marketplace.item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class ItemMapper implements RowMapper<Item> {
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		Item item = new Item();
		item.setItemId(rs.getInt("item_id"));
		item.setSellerId(rs.getInt("seller_id"));
		item.setBuyerId(rs.getInt("buyer_id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPrice(rs.getFloat("price"));
		item.setPictures(rs.getString("images_json"));
//		String picsUrls = rs.getString("images_json");
//		
//		String[] pics = picsUrls.split(" ");
//		List<String> picList = new ArrayList<>();
//		for(String s : pics) {
//			picList.add(s);
//		}
//		item.setPictures(picList);
		
		return item;
	}
}
