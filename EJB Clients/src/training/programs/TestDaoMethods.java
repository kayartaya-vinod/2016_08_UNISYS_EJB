package training.programs;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.dao.CategoryDao;
import training.dao.DaoException;
import training.dao.EmployeeDao;
import training.dao.ProductDao;
import training.entity.Category;
import training.entity.Employee;
import training.entity.Laptop;
import training.entity.Product;
import training.entity.Skill;

public class TestDaoMethods {
	// ejb:/{app-name}/{module-name}/{distinct-name}/{bean-name}!{interface-name}
	static String ejbname = "ejb:/jpa-basics/CategoryDaoBean!training.dao.CategoryDao";
	static String ejbname1 = "ejb:/jpa-basics/ProductDaoBean!training.dao.ProductDao";
	static String ejbname2 = "ejb:/jpa-basics/EmployeeDaoBean!training.dao.EmployeeDao?stateful";

	public static void main(String[] args) throws Exception {
		// testGetCategory();
		testAddNewCategory();
		// testUpdateCategory();
		// testGetAllCategories();

		// testGetAllProducts();
		// testGetProductsByPage();
		// testGetProductsByPrice();
		// testGetEmployee();
		// testAssignNewLaptop();
		// testCreateSkill();
		// testLifeCycleMethods();
	}

	static void testLifeCycleMethods() throws Exception {
		Context ctx = getContext();
		EmployeeDao dao = (EmployeeDao) ctx.lookup(ejbname2);
		EmployeeDao dao1 = (EmployeeDao) ctx.lookup(ejbname2);

		System.out.println("dao==dao1 is " + (dao==dao1));
//		dao1.getEmployee(1);
//		dao.getEmployee(2);

	}

	static void testCreateSkill() throws Exception{
		Context ctx = getContext();
		EmployeeDao dao = (EmployeeDao) ctx.lookup(ejbname2);

		Skill s1 = new Skill(10, "Java / JavaEE");
		Skill s2 = new Skill(20, "Perl & Python");
		
		Employee e1 = dao.getEmployee(3);
		Employee e2 = dao.getEmployee(4);
		Employee e3 = dao.getEmployee(5);
		Employee e4 = dao.getEmployee(6);
		
		s1.addEmployee(e1);
		s1.addEmployee(e3);
		s1.addEmployee(e4);
		
		s2.addEmployee(e2);
		s2.addEmployee(e3);
		dao.createSkill(s1);
		dao.createSkill(s2);
		System.out.println("done!");
	}

	static void testAssignNewLaptop() throws Exception {
		Context ctx = getContext();
		EmployeeDao dao = (EmployeeDao) ctx.lookup(ejbname2);

		Laptop lt = new Laptop("AS41000450", "Apple", "MacbookPro");
		dao.assignNewLaptop(4, lt);
		System.out.println("Done!");

	}

	static void testGetEmployee() throws Exception {
		Context ctx = getContext();
		EmployeeDao dao = (EmployeeDao) ctx.lookup(ejbname2);
		Employee e1 = dao.getEmployee(4);
		Employee m1 = e1.getManager();

		System.out.println("Name = " + e1.getFirstName() + " " + e1.getLastName());
		System.out.println("Manager = " + m1.getFirstName() + " " + m1.getLastName());
		System.out.println("Phone = " + e1.getAddress().getPhone());
		System.out.println("Manager's phone = " + m1.getAddress().getPhone());
		Laptop lt = e1.getLaptop();
		System.out.println("Laptop = " + lt.getMake() + "/" + lt.getModel());
		
		System.out.printf("The laptop '%s %s (%s)' is currently owned by %s\n",
				lt.getMake(), lt.getModel(), lt.getSerialNumber(),
				lt.getOwner().getFirstName());
	}

	static void testGetProductsByPrice() throws Exception {
		Context ctx = getContext();
		ProductDao dao = (ProductDao) ctx.lookup(ejbname1);
		for (Product p : dao.getProductsByPrice(50.0, 100.0)) {
			System.out.printf("%d) %s ($%.2f)\n", p.getProductId(), p.getProductName(), p.getUnitPrice());
		}
	}

	static void testGetProductsByPage() throws Exception {
		Context ctx = getContext();
		ProductDao dao = (ProductDao) ctx.lookup(ejbname1);
		for (Product p : dao.getProductsByPage(5)) {
			System.out.printf("%d) %s (%s)\n", p.getProductId(), p.getProductName(), p.getCategory().getCategoryName());
		}
	}

	static void testGetAllProducts() throws Exception {
		Context ctx = getContext();
		ProductDao dao = (ProductDao) ctx.lookup(ejbname1);
		for (Product p : dao.getAllProducts()) {
			System.out.printf("%s (%s)\n", p.getProductName(), p.getCategory().getCategoryName());
		}
	}

	static void testGetAllCategories() throws Exception {
		Context ctx = getContext();
		CategoryDao dao = (CategoryDao) ctx.lookup(ejbname);

		List<Category> list = dao.getAllCategories();
		System.out.printf("There are %d categories\n", list.size());
		for (Category c : list) {
			System.out.printf("%d - %s (%s)\n", c.getCategoryId(), c.getCategoryName(), c.getDescription());
			saveCategoryPicture(c);
		}
	}

	static void saveCategoryPicture(Category c) {
		if (c == null || c.getPicture() == null) {
			return;
		}
		String filename = "images/" + c.getCategoryId() + ".jpg";
		try {
			FileOutputStream file = new FileOutputStream(filename);
			file.write(c.getPicture());
			file.close();
		} catch (Exception e) {
		}
	}

	static void testUpdateCategory() throws Exception {
		Context ctx = getContext();
		CategoryDao dao = (CategoryDao) ctx.lookup(ejbname);
		Category c1 = dao.getCategory(99);
		c1.setDescription("Pens and pencils");
		dao.updateCategory(c1);
		System.out.println("Data updated!");
	}

	static void testAddNewCategory() throws Exception {
		try {
			Category c1 = new Category(97, "Computer spares", "RAM, HDD, etc..");
			Context ctx = getContext();
			CategoryDao dao = (CategoryDao) ctx.lookup(ejbname);
			dao.createCategory(c1);
			System.out.println("New category added to db!");
		} catch (EJBException e) {
			e.getCause().printStackTrace();
		}
	}

	static void testGetCategory() throws NamingException, DaoException {
		Context ctx = getContext();

		CategoryDao dao = (CategoryDao) ctx.lookup(ejbname);
		int catId = 1;
		Category c = dao.getCategory(catId);
		if (c != null) {
			System.out.println("Name = " + c.getCategoryName());
			System.out.println("Desc = " + c.getDescription());

			System.out.printf("There are %d products in this category\n", c.getProducts().size());

			for (Product p : c.getProducts()) {
				System.out.printf("\t%s, $%.2f\n", p.getProductName(), p.getUnitPrice());
			}
		}
	}

	static Context getContext() throws NamingException {
		Properties props = new Properties();
		props.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		Context ctx = new InitialContext(props);
		return ctx;
	}
}
