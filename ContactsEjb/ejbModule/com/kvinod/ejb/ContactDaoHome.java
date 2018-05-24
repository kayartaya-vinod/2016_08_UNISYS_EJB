package com.kvinod.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface ContactDaoHome extends EJBHome {
	public ContactDao create() throws CreateException, RemoteException;
}
