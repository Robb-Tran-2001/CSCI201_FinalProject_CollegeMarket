package com.csci201.marketplace.user.service;

import com.csci201.marketplace.item.model.Item;
import com.csci201.marketplace.user.dao.UserDAO;
import com.csci201.marketplace.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService { //implements DAO, interacts with USER

    private final UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("demoDAO") UserDAO udao)
    {
        userDAO = udao;
    }

    public List<User> listAll() {
        return userDAO.selectAll();
    }

    public void getAll() {
        userDAO.returnAll();
    }

    public int getID(String username) {
        User temp = userDAO.getProfile(username);
        return temp.getUserID();
    }

    //get by name for profile
    public User getProf(String name) { return userDAO.getProfile(name); }

    //get by email and password, login functionality
    public User getMProf(String name, String password) {
        return userDAO.getMyProfile(name, password);
    }

    //update user's password
    public int update(String name, String password) {
        return userDAO.update(name, password);
    }

    //sign up functionality
    public int add(String name, String hash) {
        return userDAO.add(name, hash);
    }

    //approve the buyer's request
    public int approve(String seller, String name, int itemID) {
        //store method to accept purchase of item with itemID and buyer with name
        return itemID;
    }

    public List<Item> getReqs(String name) {
        //get all buying requests for the seller of the current name
        List<Item> requests = new ArrayList<>();
        return requests;
    }
}
