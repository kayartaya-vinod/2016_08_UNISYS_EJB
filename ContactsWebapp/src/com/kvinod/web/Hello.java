package com.kvinod.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kvinod.ejb.ContactDao;
import com.kvinod.ejb.ContactDaoHome;

public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Context ctx = new InitialContext();
			ContactDaoHome home = (ContactDaoHome) ctx
					.lookup("java:global/ContactsApp/ContactsEjb/ContactDao!com.kvinod.ejb.ContactDaoHome");

			ContactDao dao = home.create();
			String msg = dao.helloContact("Vinod");
			PrintWriter out = response.getWriter();
			out.println("Message from ejb:- " + msg);
			out.close();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
