package training.dao;

import java.util.List;

import javax.ejb.Remote;

import training.entity.Category;

@Remote
public interface CategoryDao {
	// CRUD operations
	public void createCategory(Category category) throws DaoException;

	public Category getCategory(Integer categoryId) throws DaoException;

	public void updateCategory(Category category) throws DaoException;

	public void deleteCategory(Integer categoryId) throws DaoException;

	// QUERIES

	public List<Category> getAllCategories() throws DaoException;
}










