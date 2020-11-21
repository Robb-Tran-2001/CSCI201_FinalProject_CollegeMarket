package com.csci201.marketplace.user.dao;

import com.csci201.marketplace.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
//import static com.csci201.marketplace.user.model.User.encoder;

@Repository("UserDemoDAO")
public class UserDemoDAO extends JdbcDaoSupport implements UserDAO {
    private List<User> users = new ArrayList<User>();
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
        List<User> li = jdbcTemplateObject.query(SQL, new UserMapper());
    }

     //get by ID
    public User get(String name)
    {
        for(User user : users)
            if(user.getName().matches(name)) return user;
        String SQL = "SELECT * FROM Users WHERE User.name = " + name;
        List<User> li = jdbcTemplateObject.query(SQL, new UserMapper());
        return li.get(0);
    }

    @Override //get by username and password, login functionality
    public User get(String name, String password) {
        for(User user : users)
            if(name.matches(user.getName()) && password.matches(user.getPassword())) return user;
        String SQL = "SELECT * FROM Users WHERE name = " + name + "User.password=" + password;
        List<User> li = jdbcTemplateObject.query(SQL, new UserMapper());
        return li.get(0);
    }
    
    @Override // get userID by username
    public int getID(String username) {
    	for(User user : users) {
    		if(username.matches(user.getName())) return user.getUserID();
    	}
    	return -1;
    }

    @Override //delete by ID
    public boolean delete(int id)
    {
        for(User user : users) {
            if(user.getUserID() == id)
            {
                users.remove(user);
                String deleteQuery = "DELETE FROM Users WHERE id = ?";
                jdbcTemplateObject.update(deleteQuery, id);
                return true;
            }
        }
        return false;
    }

    @Override //update by User
    public int update(User user) {
        int id = user.getUserID();
        int counter = 0;
        for (User us : users) {
            if (us.getUserID() == id) {
                users.set(counter, user);
                int row = create(user);
                return row;
            }
            counter++;
        }
        return 0;
    }

    //sign up functionality
    @Override //add by User
    public int add(User user) {
        for(User us : users)
            if(user.getUserID() == us.getUserID()) return 0;
        users.add(user);
        int row = create(user);
        return row;
    }

    /*
     * Helper function for update and insert
     */
    private int create(User user) //create user
    {
        String sql =
                "INSERT INTO Users (user_id, " +
                        "    name, " +
                        "    password) " +
                        "VALUES (?, ?, ?, ?)";
        Object[] params = {user.getUserID(), user.getName(), user.getPassword()};
        int[] types = new int[] {
                Types.INTEGER,
                Types.VARCHAR,
                Types.VARCHAR
        };
        int row = jdbcTemplateObject.update(sql, params, types);
        return row;
    }
}
