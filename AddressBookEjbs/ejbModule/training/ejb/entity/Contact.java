package training.ejb.entity;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface Contact extends EJBObject {
	
	public Integer getId() throws RemoteException;

	public void setId(Integer id) throws RemoteException;

	public String getName() throws RemoteException;

	public void setName(String name) throws RemoteException;

	public String getEmail() throws RemoteException;

	public void setEmail(String email) throws RemoteException;

	public String getPhone() throws RemoteException;

	public void setPhone(String phone) throws RemoteException;

}
