package co.vinod.productservice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductResource {

	@Value("${category-service.endpoint}")
	String ProductServiceEndpoint;

	@Autowired
	RestTemplate template;

	@Autowired
	ProductDao dao;

	// --> http://localhost:3001/api/products
	// --> http://localhost:3001/api/products?_page=2
	// --> http://localhost:3001/api/products?_limit=20
	// --> http://localhost:3001/api/products?_page=2&_limit=20
	
	@PreAuthorize("hasAnyRole('view_products', 'SYSTEMADMIN')")
	@GetMapping
	public ResponseEntity<Iterable<Product>> getAll(@RequestParam(defaultValue = "0") Integer _page,
			@RequestParam(defaultValue = "10") Integer _limit) {

		Page<Product> model = dao.findAll(PageRequest.of(_page, _limit));
		return ResponseEntity.ok(model);
	}

	// --> http://localhost:3001/api/products/between/price/50/and/500
	@PreAuthorize("hasAnyRole('view_products', 'SYSTEMADMIN')")
	@GetMapping("/between/price/{min}/and/{max}")
	public ResponseEntity<Iterable<Product>> getByPrice(@PathVariable Double min, @PathVariable Double max) {
		return ResponseEntity.ok(dao.productsByPrice(min, max));
	}

	@PreAuthorize("hasAnyRole('view_products', 'SYSTEMADMIN')")
	@GetMapping("/disontinued")
	public ResponseEntity<Iterable<Product>> getDiscontinuedProducts() {
		return ResponseEntity.ok(dao.findByDiscontinued(true));
	}

	@PreAuthorize("hasAnyRole('view_one_product', 'SYSTEMADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getById(@PathVariable Integer id) {
		log.info("got the request for product with id {}", id);
		Product p = dao.findById(id).get();

		// String uri = ProductServiceEndpoint + "/" + p.getProductId();
		String uri = ProductServiceEndpoint + "/{id}";

		Map<String, Object> m = new LinkedHashMap<>();
		m.put("id", p.getCategoryId());

		Object resp = template.exchange(uri, HttpMethod.GET, null, Object.class, m).getBody();
		log.info("got the category for product with id {} from {}", id, uri);
		Map<String, Object> out = new LinkedHashMap<>();
		out.put("product", p);
		out.put("category", resp);

		log.info("returning product with id {} along with category", id);
		return ResponseEntity.ok(out);
	}

	@PreAuthorize("hasAnyRole('add_product', 'SYSTEMADMIN')")
	@PostMapping
	public ResponseEntity<Product> addNew(@RequestBody Product product) {
		product.setId(null); // to ensure generation of new primary key value
		product = dao.save(product);
		return ResponseEntity.ok(product);
	}

	@PreAuthorize("hasAnyRole('update_product', 'SYSTEMADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product product) {
		product.setId(id); // ignore the id present in the product
		product = dao.save(product);
		return ResponseEntity.ok(product);
	}

	@PreAuthorize("hasAnyRole('delete_product', 'SYSTEMADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		try {
			Product c = dao.findById(id).get();
			dao.deleteById(id);
			return ResponseEntity.ok(c);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product found for id::" + id);
		}
	}
}
