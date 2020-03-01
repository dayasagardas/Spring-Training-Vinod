package co.vinod.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@ComponentScan(basePackages = {"co.vinod.dao"})
@PropertySource({ "classpath:jdbc.properties" })
@Configuration
public class AppConfig4 {
	
	// dependency injection by Spring
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	
//	public AppConfig4() {
//		System.out.println("inside AppConfig4() -> username is " + username);
//	}
//	
//	@PostConstruct
//	public void init() {
//		System.out.println("inside init() -> username is " + username);
//	}
	
	@Bean
	public BasicDataSource dataSource() {
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

}
