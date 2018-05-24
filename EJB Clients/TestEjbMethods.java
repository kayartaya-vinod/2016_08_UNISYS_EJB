package training.programs;

import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.kvinod.TimerService;

public class TestEjbMethods {
	// ejb:/{app-name}/{module-name}/{distinct-name}/{bean-name}!{interface-name}
	static String ejbname = "ejb:/timer-example/TimerServiceBean!com.kvinod.TimerService";

	public static void main(String[] args) throws Exception {
		testTimer();
	}
	
	static void testTimer() throws Exception {
		Context ctx = getContext();
		TimerService service = (TimerService) ctx.lookup(ejbname);
		service.createTimer(10000);
		Date dt = new Date();
		dt.setTime(dt.getTime() + 15000);
		service.executeOn(dt);
		System.out.println("Timer service created!!");
	}

	static Context getContext() throws NamingException {
		Properties props = new Properties();
		props.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		Context ctx = new InitialContext(props);
		return ctx;
	}
}
