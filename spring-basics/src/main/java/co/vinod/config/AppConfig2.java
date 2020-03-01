package co.vinod.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import co.vinod.dao.ProductDaoJdbcImpl;

@PropertySource({ "classpath:jdbc.properties" })
@Configuration
public class AppConfig2 {
	
	// dependency injection by Spring
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	
	@Bean
	public ProductDaoJdbcImpl jdbcDao() {
		ProductDaoJdbcImpl dao = new ProductDaoJdbcImpl();
		// dependency injection
		dao.setDriverClassName(driverClassName);
		dao.setUrl(url);
		dao.setUsername(username);
		dao.setPassword(password);
		return dao;
	}
}
