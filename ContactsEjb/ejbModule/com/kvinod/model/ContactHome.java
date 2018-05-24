package com.kvinod.model;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

public interface ContactHome extends EJBHome {
	public Contact create(Integer id, String firstname, String lastname, String email, String mobile)
			throws CreateException, RemoteException;

	public Contact findByPrimaryKey(Integer id) throws FinderException, RemoteException;

	public Collection findAll() throws FinderException, RemoteException;

}
