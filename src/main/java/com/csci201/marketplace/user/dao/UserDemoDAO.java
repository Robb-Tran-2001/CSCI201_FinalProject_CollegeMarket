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
        List<User> li = jdbcTemplateObject.query(SQL, new UserMapper());
    }

     //get by ID
    public User getProfile(String name)
    {
        for(User user : users)
            if(user.getName().matches(name)) return user;
        String SQL = "SELECT * FROM Users WHERE name = \"" + name + "\"";
        List<User> li = jdbcTemplateObject.query(SQL, new UserMapper());
        if(li.size() != 0) users.addAll(li);
        return li.get(0);
    }

    @Override //get by name and password, login functionality
    public User getMyProfile(String name, String password) {
        for(User user : users)
            if(name.matches(user.getName()) && password.matches(user.getPassword())) return user;
        String SQL = "SELECT * FROM Users WHERE name = " + name + "User.password=" + password;
        List<User> li = jdbcTemplateObject.query(SQL, new UserMapper());
        if(li.size() != 0) users.addAll(li);
        return li.get(0);
    }
    

    @Override // get userID by username
    public int getID(String username) {
    	int id = this.get(username).getUserID();
    	return id;
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

    //for updating password
    private int update(User us) {
        String SQL = "update User set password = ? where name = ?";
        int row = jdbcTemplateObject.update(SQL, us.getPassword(), us.getName());
        return row;
    }

    //sign up functionality
    @Override //add by User
    public int add(String name, String password) {
        for(User us : users)
            if(us.getName().matches(name)) return 0;

        int row = create(new User(name, password));
        String SQL = "SELECT * FROM Users WHERE name = " + name + "User.password=" + password;
        List<User> li = jdbcTemplateObject.query(SQL, new UserMapper());
        users.add(li.get(0));
        return row;
    }

    /*
     * Helper function for insertion
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
