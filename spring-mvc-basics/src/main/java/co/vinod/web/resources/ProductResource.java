package co.vinod.web.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.vinod.dao.DaoException;
import co.vinod.dao.ProductDao;
import co.vinod.entity.Product;
import co.vinod.entity.ProductList;

// If you use @Controller, then each handler function
// should be annotated with @ResponseBody to instruct
// Spring to use the ContentNegotiatingViewResolver
@RestController
@RequestMapping("/api/products")
public class ProductResource {
	
	@Autowired
	ProductDao dao;
	
	@GetMapping(produces = "application/json")
	public List<Product> getAllAsJson() throws DaoException {
		return dao.getAllProducts();
	}
	
	@GetMapping(produces = "application/xml")
	public ProductList getAllAsXml() throws DaoException {
		return new ProductList(dao.getAllProducts());
	}
	
	@GetMapping("/{id}")
	public Product getById(@PathVariable Integer id) throws DaoException {
		return dao.getProduct(id);
	}

}








