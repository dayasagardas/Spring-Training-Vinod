package co.vinod.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import co.vinod.entity.Category;
import co.vinod.entity.Product;

@EnableTransactionManagement
@EnableAspectJAutoProxy // loads the AspectJ runtime, which in turn scans all Aspect beans,
// and creates a proxy based on pointcut expressions
@ComponentScan(basePackages = { "co.vinod.dao", "co.vinod.aspects", "co.vinod.service" })
@PropertySource({ "classpath:jdbc.properties" })
@Configuration
public class AppConfig5 {

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
	public HibernateTransactionManager txMgr(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

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

	// HibernateTemplate depends on SessionFactory
	// Spring provides a supporting class for SessionFactory:
	// LocalSessionFactoryBean

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(dataSource); // manual wiring
		lsfb.setAnnotatedClasses(Product.class, Category.class);

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		props.setProperty("hibernate.show_sql", "false");
		props.setProperty("hibernate.format_sql", "true");
		lsfb.setHibernateProperties(props);

		return lsfb;
	}

	@Bean
	public HibernateTemplate ht(SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}
}
