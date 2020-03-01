package co.vinod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.vinod.dao.DaoException;
import co.vinod.dao.ProductDao;
import co.vinod.entity.Product;

@Service
public class ProductManager {

	@Autowired
	ProductDao dao;

	@Transactional(rollbackFor = { DaoException.class , RuntimeException.class})
	public void bulkUpdatePrice(Integer... ids) throws Exception {
		for (Integer id : ids) {
			Product p = dao.getProduct(id);
			System.out.println("Units in stock = " + p.getUnitsInStock());
			p.setUnitsInStock(p.getUnitsInStock() - 10);
			System.out.println("Units in stock = " + p.getUnitsInStock());
			dao.updateProduct(p);
		}
	}
}
