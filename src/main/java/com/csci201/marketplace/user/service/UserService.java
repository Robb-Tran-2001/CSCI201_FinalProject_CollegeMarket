package com.csci201.marketplace.user.service;

import com.csci201.marketplace.user.dao.UserDAO;
import com.csci201.marketplace.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
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

    //get by ID
    public User get(int id)
    {
        return userDAO.get(id);
    }

    //get by email and password, login functionality
    public User get(String email, String password) {
        return userDAO.get(email, password);
    }

    //delete by ID
    public boolean delete(int id)
    {
        return userDAO.delete(id);
    }

    //update by User
    public int update(User user) {
        return userDAO.update(user);
    }

    //sign up functionality
    //add by User
    public int add(User user) {
        return userDAO.add(user);
    }

}
