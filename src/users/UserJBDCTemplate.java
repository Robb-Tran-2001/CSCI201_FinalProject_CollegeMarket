package users;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserJBDCTemplate implements UserDAO {
	DataSource datasource;
	private JdbcTemplate jdbcTemplateObject;
}
