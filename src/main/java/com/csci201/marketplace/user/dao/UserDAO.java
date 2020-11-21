package com.csci201.marketplace.user.dao;


import com.csci201.marketplace.user.model.User;
import java.util.List;

public interface UserDAO {
	public List<User> selectAll();
	public void returnAll();
	public User getProfile(String name);
	public User getMyProfile(String name, String password); //for login
	public boolean delete(int id);
	public int update(String name, String password);
	public int add(String name, String password);
}
