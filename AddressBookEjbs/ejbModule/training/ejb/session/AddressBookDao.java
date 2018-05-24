package training.ejb.session;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.EJBObject;

// A Remote interface must extend javax.ejb.EJBObject
// A local interface must extend javax.ejb.EJBLocalObject
// Every method in the remote interface must throw java.rmi.RemoteException
public interface AddressBookDao extends EJBObject {

	public void createContact(Integer id, String name, 
			String email, String phone) throws RemoteException;
	
	public Collection allContacts() throws RemoteException;
}













