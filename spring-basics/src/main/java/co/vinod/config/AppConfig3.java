package co.vinod.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import co.vinod.dao.ProductDaoJdbcImpl;

@PropertySource({ "classpath:jdbc.properties" })
@Configuration
public class AppConfig3 {
	
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
	public BasicDataSource ds1() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(driverClassName);
		bds.setUsername(username);
		bds.setPassword(password);
		bds.setUrl(url);
		
		bds.setInitialSize(10);
		bds.setMaxTotal(100);
		bds.setMaxIdle(25);
		bds.setMinIdle(5);
		bds.setMaxWaitMillis(50);
		return bds;
	}


	@Bean
	public BasicDataSource ds2() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(driverClassName);
		bds.setUsername(username);
		bds.setPassword(password);
		bds.setUrl("jdbc:h2:tcp://localhost/~/spring-training-hpe-2");
		
		return bds;
	}
	@Bean
	public ProductDaoJdbcImpl jdbcDao() { 
		ProductDaoJdbcImpl dao = new ProductDaoJdbcImpl();
		// manual wiring
		// dao.setDataSource(ds);
		return dao;
	}
}
