package co.vinod.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import co.vinod.config.AppConfig4;
import co.vinod.dao.ProductDao;

public class GetProductCount {

	public static void main(String[] args) throws Exception {
		// Create a spring container
		AnnotationConfigApplicationContext ctx;
		
		ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
		
		ProductDao dao = ctx.getBean(ProductDao.class);
		long pc = dao.count();
		System.out.println("There are " + pc + " products.");
		
		ProductDao dao1 = ctx.getBean(ProductDao.class);
		
		System.out.println("dao == dao1 is: " + (dao==dao1));
		
		ctx.close();
	}
}
