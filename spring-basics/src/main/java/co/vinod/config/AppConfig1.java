package co.vinod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import co.vinod.dao.ProductDaoDummyImpl;

// this is a configuration class, defines functions that return
// beans, and these functions are automatically called by 
// Spring and collects them and stores them in the container
@Configuration
public class AppConfig1 {
	
	public AppConfig1() {
		System.out.println("AppConfig1() called");
	}

	@Scope("singleton")
	@Lazy
	@Bean(name = { "dummyDao" })
	public ProductDaoDummyImpl dummyDao() {
		System.out.println("dummyDao() called");
		return new ProductDaoDummyImpl();
	}
	
	// @Bean
	public String greet() {
		for(int i=0; i<=10; i++) {
			System.out.println("inside the greet().for loop");
			this.dummyDao();
		}
		
		System.out.println("greet() called");
		return "Hello, World!";
	}

}




