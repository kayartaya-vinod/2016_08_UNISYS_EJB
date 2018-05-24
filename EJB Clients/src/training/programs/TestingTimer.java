package training.programs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import training.ejb.MailScheduler;

public class TestingTimer {
	
	static String ejbname = "ejb:/Ejb_Advanced/MailSchedulerBean!training.ejb.MailScheduler";

	
	public static void main(String[] args) throws Exception {
		
		Context ic = getContext();
		MailScheduler ms = (MailScheduler) ic.lookup(ejbname);
		
		String dtStr = "01/09/2016 15:41:44";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date dt = sdf.parse(dtStr);
		
		ms.sendMailOn(dt);
		ms.sendMailAfter(10000);
		System.out.println("Mail scheduled!");
	}
	
	static Context getContext() throws NamingException {
		Properties props = new Properties();
		props.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		Context ctx = new InitialContext(props);
		return ctx;
	}
}





