package training.programs;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestMdb {

	public static void main(String[] args) throws Exception {

		String destinationName = "queue/vinod";
		Context ic = null;
		ConnectionFactory cf = null;
		Connection connection = null;

		try { 
			ic = getContext();

			cf = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
			
			Queue queue = (Queue) ic.lookup(destinationName);

			connection = cf.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer publisher = session.createProducer(queue);

			connection.start();

			TextMessage message = session.createTextMessage("Hello AS 7 !");
			publisher.send(message);

			System.out.println("Message sento to the JMS Provider");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {

			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}

			}
		}
	}

	static Context getContext() throws NamingException {
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		props.setProperty(Context.PROVIDER_URL, "remote://localhost:4447");
		props.setProperty(Context.SECURITY_PRINCIPAL, "vinodkumar");
		props.setProperty(Context.SECURITY_CREDENTIALS, "secret");
		
		Context ctx = new InitialContext(props);
		return ctx;
	}
}
