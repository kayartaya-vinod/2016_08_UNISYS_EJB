package training.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import training.ejb.session.AddressBookDao;
import training.ejb.session.AddressBookDaoHome;

@WebServlet("/AddContactServlet")
public class AddContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			Integer id;
			String name, email, phone;
			
			id = new Integer(request.getParameter("id"));
			name = request.getParameter("name");
			email = request.getParameter("email");
			phone = request.getParameter("phone");
			
			// get the local jndi service reference
			Context ctx = new InitialContext();
			String homeJndi = "java:global/AddressBookEjbs/AddressBookDao!training.ejb.session.AddressBookDaoHome";
			
			AddressBookDaoHome home = (AddressBookDaoHome) ctx.lookup(homeJndi);
			AddressBookDao dao = home.create();
			dao.createContact(id, name, email, phone);
			
			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("./")
				.include(request, response);
			out.println("<script>");
			out.println("alert('New contact added to db!');");
			out.println("</script>");
			out.close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}












