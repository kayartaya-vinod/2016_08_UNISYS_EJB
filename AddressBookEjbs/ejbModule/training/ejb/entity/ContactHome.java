package training.ejb.entity;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

// An object of Home interface is repsonsible for creating an
// object of Remote interface (on the client VM)
public interface ContactHome extends EJBHome {
	
	public Contact create(Integer id, String name, String email, String phone)
			throws CreateException, RemoteException;
	
	public Contact findByPrimaryKey(Integer id) 
			throws FinderException, RemoteException;
	
	public Collection findAll() 
			throws FinderException, RemoteException;
}
















