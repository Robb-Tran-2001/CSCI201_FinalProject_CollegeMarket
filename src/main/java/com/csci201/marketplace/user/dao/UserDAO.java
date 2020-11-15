package com.csci201.marketplace.user.dao;


import com.csci201.marketplace.user.model.User;
import java.util.List;

public interface UserDAO {
	public List<User> selectAll();
	public void returnAll();
	public User get(int id);
	public User get(String email, String password); //for login
	public boolean delete(int id);
	public int update(User user);
	public int add(User user);
}
