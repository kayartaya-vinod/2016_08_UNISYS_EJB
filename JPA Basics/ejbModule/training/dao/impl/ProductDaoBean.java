package training.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import training.dao.DaoException;
import training.dao.ProductDao;
import training.entity.Product;

@Stateless
public class ProductDaoBean implements ProductDao {

	@PersistenceContext(unitName = "northwind_pu")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() throws DaoException {
		return em.createQuery("select p from Product p").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsByPrice(Double min, Double max) throws DaoException {
		return em.createQuery(
			"select p from Product p where p.unitPrice between ? and ?")
			.setParameter(1, min)
			.setParameter(2, max)
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsByPage(Integer pageNum) throws DaoException {
		int pageSize = 10, offset;
		offset = (pageNum - 1) * pageSize;
		return em.createQuery("SELECT p FROM Product p")
				.setMaxResults(pageSize)
				.setFirstResult(offset)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getProductNames() throws DaoException {
		return em.createQuery("select p.productName from Product p")
				.getResultList();
	}

	@Override
	public int getProductCount() throws DaoException {
		Long count = (Long)em.createQuery("select count(p) from Product p")
			.getSingleResult();
		return count.intValue();
	}

	@Override
	public Double getAverageProductPrice() throws DaoException {
		return (Double)em.createQuery(
				"select avg(p.unitPrice) from Product p")
				.getSingleResult();
	}

}















