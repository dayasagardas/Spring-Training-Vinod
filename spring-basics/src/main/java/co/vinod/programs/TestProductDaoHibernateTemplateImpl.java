package co.vinod.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import co.vinod.config.AppConfig5;
import co.vinod.dao.DaoException;
import co.vinod.dao.ProductDao;
import co.vinod.entity.Product;

public class TestProductDaoHibernateTemplateImpl {

	public static void main(String[] args) throws DaoException {

		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig5.class);

		ProductDao dao = ctx.getBean("dao", ProductDao.class);

		System.out.println("dao is an instanceof " + dao.getClass());
		Product p1 = dao.getProduct(1);
		System.out.println(p1);

		long pc = dao.count();
		System.out.println("There are " + pc + " products.");

		Double min = 50.0;
		Double max = 500.0;
		List<Product> list = dao.getProductsByPriceRange(min, max);
		System.out.println("There are " + list.size() + " products between $" + min + " and $" + max);

		min = 500.0;
		max = 50.0;
		list = dao.getProductsByPriceRange(min, max);
		System.out.println("There are " + list.size() + " products between $" + min + " and $" + max);

		try {
			p1.setUnitPrice(p1.getUnitPrice()+1);
			dao.updateProduct(p1);
			System.out.println("Product price updated!");
		} catch (DaoException e) {
			e.printStackTrace();
			System.err.println("Update failed!");
		}
		
		ctx.close();
	}
}
