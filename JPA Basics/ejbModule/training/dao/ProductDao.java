package training.dao;

import java.util.List;

import javax.ejb.Remote;

import training.entity.Product;

@Remote
public interface ProductDao {
	// CRUD operations (similar to CategoryDao; not done)
	
	// Queries
	public List<Product> getAllProducts() throws DaoException;
	public List<Product> getProductsByPrice(Double min, Double max) throws DaoException;
	public List<Product> getProductsByPage(Integer pageNum) throws DaoException;
	public List<String> getProductNames() throws DaoException;
	public int getProductCount() throws DaoException;
	public Double getAverageProductPrice() throws DaoException;
}












