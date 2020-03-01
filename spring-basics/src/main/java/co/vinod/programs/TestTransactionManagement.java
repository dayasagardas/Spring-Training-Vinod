package co.vinod.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import co.vinod.config.AppConfig5;
import co.vinod.service.ProductManager;

public class TestTransactionManagement {

	public static void main(String[] args) throws Exception {
		
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig5.class);
		
		ProductManager pm = ctx.getBean(ProductManager.class);
		
		pm.bulkUpdatePrice(6, 4, 3); // initial prices --> 120, 53, 13
		
		ctx.close();
	}
}
