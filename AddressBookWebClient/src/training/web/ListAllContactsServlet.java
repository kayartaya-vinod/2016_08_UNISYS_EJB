package training.web;

import java.io.IOException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import training.ejb.session.AddressBookDao;
import training.ejb.session.AddressBookDaoHome;

@WebServlet("/ListAllContactsServlet")
public class ListAllContactsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Context ctx = new InitialContext();
			String homeJndi = "java:global/AddressBookEjbs/AddressBookDao!training.ejb.session.AddressBookDaoHome";
			
			AddressBookDaoHome home = (AddressBookDaoHome) ctx.lookup(homeJndi);
			AddressBookDao dao = home.create();
			Collection contacts = dao.allContacts();
			
			request.setAttribute("contacts", contacts);
			request.getRequestDispatcher("/WEB-INF/pages/list-contacts.jsp")
				.forward(request, response);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
