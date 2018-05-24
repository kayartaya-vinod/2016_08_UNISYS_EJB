package training.web;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/SendMessage")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String msg = request.getParameter("msg");
		
		try {
			// jndi resources
			ConnectionFactory factory;
			Queue queue;
			
			// local jndi service
			Context ctx = new InitialContext();
			factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
			queue = (Queue) ctx.lookup("queue/training");
			
			Connection conn = factory.createConnection();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			TextMessage tm = session.createTextMessage(msg);
			MessageProducer sender = session.createProducer(queue);
			
			conn.start();
			sender.send(tm);
			conn.stop();
			
			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("./")
				.include(request, response);
			out.println("<script>");
			out.println("alert('Message sent to the JMS Message Provider');");
			out.println("</script>");
			out.close();
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}

}

















