package com.csci201.marketplace.user.dao;


import com.csci201.marketplace.user.model.User;

public interface UserDAO {
	public User get(int id);
	public User get(int id, String password); //for login
	public boolean delete(int id);
	public int update(User user);
	public int add(User user);
}
