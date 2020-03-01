package co.vinod.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import co.vinod.config.AppConfig5;
import co.vinod.entity.Product;

public class TestHibernateTemplate {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig5.class);
		
		HibernateTemplate ht = ctx.getBean(HibernateTemplate.class);
		Product p1 = ht.get(Product.class, 1);
		System.out.println(p1);
		System.out.println("Category of p1 = " + p1.getCategory().getCategoryName());
		
		System.out.println("---------------------");
		String hql = "from Product where unitPrice between ?0 and ?1";
		List<Product> list = (List<Product>) ht.find(hql, 50.0, 500.0);
		for(Product p: list) {
			System.out.println(p);
		}
		
		ctx.close();
		
	}
}
