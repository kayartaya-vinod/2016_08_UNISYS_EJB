package training.ejb.session;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

// During deployment, an object of this interface will be created
// and published in the JNDI tree (which should provide a mechanism
// to create a business object)
public interface AddressBookDaoHome extends EJBHome {
	public AddressBookDao create() throws RemoteException, CreateException;
}
