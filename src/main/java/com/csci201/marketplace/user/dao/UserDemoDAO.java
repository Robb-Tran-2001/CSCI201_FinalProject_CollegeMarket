package com.csci201.marketplace.user.dao;

import com.csci201.marketplace.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository("UserDemoDAO")
public class UserDemoDAO extends JdbcDaoSupport implements UserDAO {
    private static List<User> users = new ArrayList<User>();

    @Autowired
    private JdbcTemplate jdbcTemplateObject;
//    private static JdbcTemplate jdbcTemplateObject = null;

//    private static DataSource dataSource = null;
//
//    @Autowired
//    public void setDataSource(DataSource ds) {
//        dataSource = ds;
//        jdbcTemplateObject = new JdbcTemplate(ds);
//    }

    @Autowired
	public UserDemoDAO(DataSource ds) {
		setDataSource(ds);
		this.jdbcTemplateObject = getJdbcTemplate();
		this.returnAll();
	}
    
    @Override
    public List<User> selectAll() {
        return new ArrayList<User>(users);
    }

    @Override
    public void returnAll() {
        String SQL = "SELECT * FROM Users";
        List<User> users = jdbcTemplateObject.query(SQL, new UserMapper());
    }

     //get by ID
    public User getProfile(String name)
    {
        for(User user : users)
            if(user.getName().matches(name)) return user;
        String SQL = "SELECT * FROM Users WHERE Users.name = ?";
        List<User> li = null;
        try {
            li = jdbcTemplateObject.query(SQL, new Object[]{name}, new UserMapper());
        } catch (DataAccessException e) {
            System.out.println("Data access exception while getting profile, name = " + name);
        }
        if(li != null && li.size() != 0) {
            users.addAll(li);
            return li.get(0);
        }
        return null;
    }

    @Override //get by name and password, login functionality
    public User getMyProfile(String name, String password) {
        for(User user : users)
            if(name.matches(user.getName()) && password.matches(user.getPassword())) return user;
        String SQL = "SELECT * FROM Users WHERE name = ? AND password = ?";
        List<User> li = null;
        try {
            jdbcTemplateObject.query(SQL, new Object[]{name, password}, new UserMapper());
        } catch (DataAccessException e) {
            System.out.println("Data access exception while getting My profile, name = " + name);
        }
        if(li != null && li.size() != 0) {
            users.addAll(li);
            return li.get(0);
        }
        return null;
    }
    
//    @Override // get userID by username
//    public User getID(String username) {
//    	for(User user : users) {
//    		if(username.matches(user.getName())) return user.getUserID();
//    	}
//    	return -1;
//    }

    @Override //delete by ID
    public boolean delete(int id)
    {
        for(User user : users) {
            if(user.getUserID() == id)
            {
                users.remove(user);
                String deleteQuery = "DELETE FROM Users WHERE id = ?";
                try {
                    jdbcTemplateObject.update(deleteQuery, id);
                } catch (DataAccessException e) {
                    System.out.println("Data access exception deleting by ID = " + id);
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override //update by User
    public int update(String name, String password) {
        int counter = 0;
        for (User us : users) {
            if (us.getName().matches(name)) {
                us.setPassword(password);
                int row = update(us);
                return row;
            }
            counter++;
        }
        return 0;
    }

    //for updating password, helper function for the one above
    private int update(User us) {
        String SQL = "UPDATE Users SET password = ? WHERE NAME = ?";
        int row = 0;
        try {
            row = jdbcTemplateObject.update(SQL, us.getPassword(), us.getName());
        } catch (DataAccessException e) {
            System.out.println("Data access exception, can't update password for user named " + us.getName());
        }
        return row;
    }

    //sign up functionality
    @Override //add by User
    public int add(String name, String password) {
        for(User us : users)
            if(us.getName().matches(name)) return 0;
        User temp = new User(name, password);
        int row = create(temp);
        if(row != 0) {
            temp.setUserID(row);
            users.add(temp);
            System.out.println("Added to dao");
        }
        return row;
    }

    /*
     * Helper function for insertion
     */
    private int create(User user) //create user
    {
        String sql =
                "INSERT INTO Users (name, " +
                        "    password) " +
                        "VALUES (?, ?)";
        Object[] params = {user.getName(), user.getPassword()};
        int[] types = new int[] {
                Types.VARCHAR,
                Types.VARCHAR
        };
        int row = 0;
        try {
            row = jdbcTemplateObject.update(sql, params, types);
        } catch (DataAccessException e) {
            System.out.println("Duplicate insertion, user already exists with name " + user.getName());
        }
        return row;
    }
}
