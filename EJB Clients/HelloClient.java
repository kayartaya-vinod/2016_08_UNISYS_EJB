package training.programs;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import training.ejb.HelloService;

public class HelloClient {

	public static void main(String[] args) throws Exception {
		// (ctx) create a reference to remote/jboss JNDI service object
		Properties props = new Properties();
		props.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		Context ctx = new InitialContext(props); // remote JNDI reference
		
		// (service) get a proxy (stub) for the service object 
		String ejbname = "ejb:/ejb-basics/HelloServiceImpl!training.ejb.HelloService";
		HelloService service = (HelloService)ctx.lookup(ejbname);
		System.out.println("Got an instanceof " + service.getClass());
		
		// call the service functions
		String msg = service.sayHello("Vinod");
		System.out.println("Message from server: " + msg);
	}
}