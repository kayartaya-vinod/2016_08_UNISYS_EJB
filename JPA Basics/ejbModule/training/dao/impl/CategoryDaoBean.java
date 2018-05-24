package training.dao.impl;
 
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import training.dao.CategoryDao;
import training.dao.DaoException;
import training.entity.Category;

// @TransactionManagement(TransactionManagementType.BEAN)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class CategoryDaoBean implements CategoryDao {

	@Resource
	UserTransaction tx;

	// the unitName corresponds to the name of the persistence-unit
	// defined in the /META-INF/persistence.xml
	@PersistenceContext(unitName = "northwind_pu")
	private EntityManager em;

	public CategoryDaoBean() {
		System.out.println("Inside the constructor of CategoryDaoBean, em is " + em);
	}

	// this method is automatically invoked after all
	// dependency injections are done
	@PostConstruct
	public void init() {
		// just some arbitrary function
		System.out.println("Inside the @PostConstruct method of CategoryDaoBean, em is " + em);
	}

	// @Override
	// public void createCategory(Category category) throws DaoException {
	// try {
	// tx.begin();
	// try {
	// em.persist(category);
	// tx.commit();
	// } catch (Exception e) {
	// tx.rollback();
	// throw new DaoException(e);
	// }
	// } catch (Exception e) {
	// throw new DaoException(e);
	// }
	// }

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void createCategory(Category category) throws DaoException {
		em.persist(category);
	}

	@Override
	public Category getCategory(Integer categoryId) throws DaoException {
		try {
			Category c = em.find(Category.class, categoryId);

			System.out.printf("There are %d products in this category\n", c.getProducts().size());
			//
			// for (Product p : c.getProducts()) {
			// System.out.printf("\t%s, $%.2f\n",
			// p.getProductName(), p.getUnitPrice());
			// }
			return c;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updateCategory(Category category) throws DaoException {
		try {
			em.merge(category);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void deleteCategory(Integer categoryId) throws DaoException {
		try {
			Category c = new Category();
			c.setCategoryId(categoryId);
			em.remove(c);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategories() throws DaoException {
		try {
			String eql = "select c from Category c";
			Query qry = em.createQuery(eql);
			return qry.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
