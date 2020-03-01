package co.vinod.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import co.vinod.entity.Product;

@Repository("dao") // qualified for @ComponentScan
public class ProductDaoHibernateTemplateImpl implements ProductDao {

	@Autowired
	private HibernateTemplate template;

	@Override
	public void addProduct(Product product) throws DaoException {
		template.persist(product); // FIRES an SQL INSERT COMMAND
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		template.merge(product);
	}

	@Override
	public Product getProduct(Integer productId) throws DaoException {
		return template.get(Product.class, productId);
	}

	@Override
	public void deleteProduct(Integer productId) throws DaoException {
		Product p = getProduct(productId);
		if (p == null) {
			throw new DaoException("Invalid id for deletion");
		}
		p.setDiscontinued(true);
		updateProduct(p); // soft delete
		// template.delete(p); // hard delete
	}

	@Override
	public List<Product> getAllProducts() throws DaoException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		return (List<Product>) template.findByCriteria(dc);
	}

	@Override
	public List<Product> getProductsByPriceRange(Double min, Double max) throws DaoException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.ge("unitPrice", min));
		dc.add(Restrictions.le("unitPrice", max));
		// dc.add(Restrictions.between("unitPrice", min, max));
		return (List<Product>) template.findByCriteria(dc);
	}

	@Override
	public long count() throws DaoException {
		
		// select discontinued, count(*), avg(unit_price) from products
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		
//		ProjectionList plist = Projections.projectionList()	
//				.add(Projections.groupProperty("discontiuned"))
//				.add(Projections.rowCount())
//				.add(Projections.avg("unitPrice"));
//		
//		dc.setProjection(plist);
		
		dc.setProjection(Projections.rowCount());
		
		return (long) template.findByCriteria(dc).get(0);
	}

}
