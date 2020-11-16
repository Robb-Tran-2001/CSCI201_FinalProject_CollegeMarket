package com.csci201.marketplace.user.dao;

import com.csci201.marketplace.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import static com.csci201.marketplace.user.model.User.encoder;

@Repository("demoDAO")
public class UserDemoDAO implements UserDAO {
    private static List<User> users = new ArrayList<User>();
    private static JdbcTemplate jdbcTemplateObject = null;

    private static DataSource dataSource = null;

    @Autowired
    public void setDataSource(DataSource ds) {
        dataSource = ds;
        jdbcTemplateObject = new JdbcTemplate(ds);
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
    public User get(int id)
    {
        for(User user : users)
            if(user.getUserID() == id) return user;
        String SQL = "SELECT * FROM Users WHERE User.user_id = " + id;
        List<User> li = jdbcTemplateObject.query(SQL, new UserMapper());
        return li.get(0);
    }

    @Override //get by email and password, login functionality
    public User get(String email, String password) {
        for(User user : users)
            if(email.matches(user.getEmail()) && encoder.matches(password, user.getPassword())) return user;
        String SQL = "SELECT * FROM Users WHERE email = " + email + "User.password=" + encoder.encode(password);
        List<User> li = jdbcTemplateObject.query(SQL, new UserMapper());
        return li.get(0);
    }
    
    @Override // get userID by username
    public int getID(String username) {
    	for(User user : users) {
    		if(username.matches(user.getEmail())) return user.getUserID();
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
                        "    email, " +
                        "    password) " +
                        "VALUES (?, ?, ?, ?)";
        Object[] params = {user.getUserID(), user.getName(), user.getEmail(), user.getPassword()};
        int[] types = new int[] {
                Types.INTEGER,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR
        };
        int row = jdbcTemplateObject.update(sql, params, types);
        return row;
    }
}
