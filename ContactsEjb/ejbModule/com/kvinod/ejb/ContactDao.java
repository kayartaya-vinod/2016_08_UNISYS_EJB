package com.kvinod.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface ContactDao extends EJBObject {

	public String helloContact(String name) throws RemoteException;

}
