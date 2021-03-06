package com.csci201.marketplace.user.dao;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.csci201.marketplace.user.model.User;
import org.springframework.jdbc.core.RowMapper;


//query returned by SELECT * FROM Users FULL OUTER JOIN Items WHERE Users.user_id = Items.seller_id
//to get every item im selling
public class UserMapper implements RowMapper { //mapper deals with database
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserID(rs.getInt("user_id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		return user;
	}
}
