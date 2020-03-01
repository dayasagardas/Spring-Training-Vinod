package co.vinod.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.vinod.entity.Product;

@Transactional(readOnly = true, rollbackFor = { DaoException.class })
public interface ProductDao {

	// CRUD operations

	@Transactional(readOnly = false)
	default void addProduct(Product product) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	default void updateProduct(Product product) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	default Product getProduct(Integer productId) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Transactional(readOnly = false)
	default void deleteProduct(Integer productId) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	// QUERIES

	default List<Product> getAllProducts() throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	default List<Product> getProductsByPriceRange(Double min, Double max) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	default long count() throws DaoException {
		throw new DaoException("Method not implemented!");
	}

}
