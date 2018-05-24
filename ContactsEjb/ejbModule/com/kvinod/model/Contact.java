package com.kvinod.model;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface Contact extends EJBObject {
	public void setId(Integer id) throws RemoteException;

	public Integer getId() throws RemoteException;

	public void setFirstname(String firstname) throws RemoteException;

	public String getFirstname() throws RemoteException;

	public void setLastname(String lastname) throws RemoteException;

	public String getLastname() throws RemoteException;

	public void setEmail(String email) throws RemoteException;

	public String getEmail() throws RemoteException;

	public void setMobile(String mobile) throws RemoteException;

	public String getMobile() throws RemoteException;

}
