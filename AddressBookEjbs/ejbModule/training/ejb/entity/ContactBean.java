package training.ejb.entity;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

public abstract class ContactBean implements EntityBean {

	private static final long serialVersionUID = 1L;
	
	// default constructor for best practice
	public ContactBean() {
	}

	// abstract properties representing table columns
	public abstract Integer getId();
	public abstract void setId(Integer id);
	public abstract String getName();
	public abstract void setName(String name);
	public abstract String getEmail();
	public abstract void setEmail(String email);
	public abstract String getPhone();
	public abstract void setPhone(String phone);
	
	// ejb standard functions
	public Integer ejbCreate(Integer id, String name, String email, String phone){
		setId(id);
		setName(name);
		setPhone(phone);
		setEmail(email);
		// cmp 2.0 requires that this function returns null;
		return null;
	}
	public void ejbPostCreate(Integer id, String name, String email, String phone){
	}

	// callback functions due to javax.ejb.EntityBean
	public void ejbActivate() throws EJBException, RemoteException {}
	public void ejbLoad() throws EJBException, RemoteException {}
	public void ejbPassivate() throws EJBException, RemoteException {}
	public void ejbRemove() throws RemoveException, EJBException, RemoteException {}
	public void ejbStore() throws EJBException, RemoteException {}
	public void setEntityContext(EntityContext context) throws EJBException, RemoteException {}
	public void unsetEntityContext() throws EJBException, RemoteException {}
}






















