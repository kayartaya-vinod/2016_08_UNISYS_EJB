package com.kvinod.web;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestingMdb")
public class TestingMdb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String destinationName = "queue/vinod";
		Context ctx = null;
		ConnectionFactory cf = null;
		Connection connection = null;

		try {
			ctx = new InitialContext();
			cf = (ConnectionFactory) ctx.lookup("ConnectionFactory");

			Queue queue = (Queue) ctx.lookup(destinationName);

			connection = cf.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer publisher = session.createProducer(queue);

			connection.start();

			TextMessage message = session.createTextMessage("Hello AS 7 !");
			publisher.send(message);

			System.out.println("Message sento to the JMS Provider");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
