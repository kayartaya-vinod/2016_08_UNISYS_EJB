package training.web;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import training.dao.DaoException;
import training.dao.ProductDao;

@WebServlet("/productlist")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ProductDao dao;
	
	@PostConstruct
	public void setup(){
		System.out.println(">>>> Inside the post-construct of ProductListServlet, dao is an instanceof " + 
				dao.getClass());
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setAttribute("products", dao.getAllProducts());
			request.getRequestDispatcher("/WEB-INF/pages/show-products.jsp")
				.forward(request, response);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

}











