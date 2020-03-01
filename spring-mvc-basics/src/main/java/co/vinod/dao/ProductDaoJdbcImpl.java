package co.vinod.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.Setter;

// @Configuration
// @Controller or @RestController // Presentation layer
// @Service // Service layer
@Repository // DAO layer
// @Component
@Setter
public class ProductDaoJdbcImpl implements ProductDao {

	// dependencies of this class
	private String driverClassName;
	private String url;
	private String username;
	private String password;

	@Autowired(required = false)
	// @Qualifier("ds2")
	private DataSource dataSource; // DB connection pool

	private Connection createConnection() throws ClassNotFoundException, SQLException {
		if (dataSource != null) {
			return dataSource.getConnection();
		}
		Class.forName(driverClassName);
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public long count() {
		String sql = "select count(*) from products";
		try (Connection conn = this.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			rs.next();
			return rs.getLong(1);
		} catch (Exception ex) {
			// converting checked exceptions into unchecked ones.
			throw new RuntimeException(ex);
		}
	}

}
