package training.ejb.session;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;

import training.ejb.entity.Contact;
import training.ejb.entity.ContactHome;

// This must provide implementations for methods from both
// local and remote interfaces. Since these methods are invoked by
// the application server (and not the client) no need to throw RemoteException
public class AddressBookDaoBean implements SessionBean{
	
	private static final long serialVersionUID = 1L;
	// good practice
	public AddressBookDaoBean() {
	}
	// biz.logic functions from Remote
	public void createContact(Integer id, String name, 
			String email, String phone){
		
		// this should be a client to the entity-bean and create a contact
		try {
			Context ctx = new InitialContext();
			ContactHome home = (ContactHome) ctx.lookup("java:global/AddressBookEjbs/Contact!training.ejb.entity.ContactHome");
			Contact c = home.create(id, name, email, phone);
			System.out.println("Contact saved to db: " + c.getName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Collection allContacts(){
		try {
			Context ctx = new InitialContext();
			ContactHome home = (ContactHome) ctx.lookup("java:global/AddressBookEjbs/Contact!training.ejb.entity.ContactHome");
			Collection list = home.findAll();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// callback methods from javax.ejb.SessionBean interface
	public void ejbActivate() throws EJBException, RemoteException {
	}
	public void ejbPassivate() throws EJBException, RemoteException {
	}
	public void ejbRemove() throws EJBException, RemoteException {
	}
	public void setSessionContext(SessionContext context) throws EJBException, RemoteException {
	}
	
	// ejb standard methods
	public void ejbCreate() {
		System.out.println(">>>> Inside AddressBookDaoBean.ejbCreate method");
	}
	public void ejbPostCreate(){
		System.out.println(">>>> Inside AddressBookDaoBean.ejbPostCreate method");
	}
}
