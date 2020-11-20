package com.csci201.marketplace.user.service;

import com.csci201.marketplace.Store.Store;
import com.csci201.marketplace.item.Item;
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

    //get by name for profile
    public User get(String name) { return userDAO.get(name); }

    //get by email and password, login functionality
    public User get(String name, String password) {
        return userDAO.get(name, password);
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
    public int approve(String name, int itemID) {
        //store method to accept purchase of item with itemID and buyer with name
        return itemID;
    }

    public List<Item> getReqs(String name) {
        //get all buying requests for the seller of the current name
        List<Item> requests = new ArrayList<>();
        return requests;
    }
}
