package com.kvinod.ejb;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.kvinod.model.Contact;
import com.kvinod.model.ContactHome;

public class ContactDaoBean implements SessionBean {

	private static final long serialVersionUID = 1L;

	public String helloContact(String name) throws RemoteException {
		System.out.println(">>>>> ContactDaoBean.helloContact() method got " + name + " as argument");

		// try {
		// Context ctx = new InitialContext();
		// ContactHome ch = (ContactHome)
		// ctx.lookup("java:global/ContactsApp/ContactsEjb/Contact!com.kvinod.model.ContactHome");
		// Contact c = ch.create(1, "Vinod", "Kumar", "vinod@vinod.co",
		// "9731424784");
		// System.out.println("New contact created!");
		// } catch (NamingException | CreateException e) {
		// throw new RuntimeException(e);
		// }

		try {
			Context ctx = new InitialContext();
			ContactHome ch = (ContactHome) ctx
					.lookup("java:global/ContactsApp/ContactsEjb/Contact!com.kvinod.model.ContactHome");
			Collection list = ch.findAll();
			System.out.println("New contact created!");
			Iterator it = list.iterator();
			String out = "";
			while(it.hasNext()){
				Contact c = (Contact) it.next();
				String str = String.format("%d - %s %s (%s, %s)\n", 
					c.getId(), c.getFirstname(), c.getLastname(), c.getEmail(), c.getMobile());
				out += str;
			}
			return out;
		} catch (NamingException | FinderException e) {
			throw new RuntimeException(e);
		}

	}

	// ejb-session-bean standard methods

	SessionContext context;

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
	}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {
	}

	@Override
	public void setSessionContext(SessionContext context) throws EJBException, RemoteException {
		this.context = context;
	}

	public void ejbCreate() {
		System.out.println(">>>>> ContactDaoBean.ejbCreate() method");
	}

}
