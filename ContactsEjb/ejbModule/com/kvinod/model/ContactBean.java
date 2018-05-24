package com.kvinod.model;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

public abstract class ContactBean implements EntityBean {

	private static final long serialVersionUID = 1L;

	// CMP properties

	public abstract void setId(Integer id);

	public abstract Integer getId();

	public abstract void setFirstname(String firstname);

	public abstract String getFirstname();

	public abstract void setLastname(String lastname);

	public abstract String getLastname();

	public abstract void setEmail(String email);

	public abstract String getEmail();

	public abstract void setMobile(String mobile);

	public abstract String getMobile();

	public ContactBean() {
	}

	public Integer ejbCreate(Integer id, String firstname, String lastname, String email, String mobile)
			throws CreateException {
		System.out.println(">>>>>> Inside ejbCreate, parameters are: ");
		System.out.println("id = " + id);
		System.out.println("firstname = " + firstname);
		System.out.println("lastname = " + lastname);
		System.out.println("email = " + email);
		System.out.println("mobile = " + mobile);
		
		setId(id);
		setFirstname(firstname);
		setLastname(lastname);
		setEmail(email);
		setMobile(mobile);
		return null;
	}

	public void ejbPostCreate(Integer id, String firstname, String lastname, String email, String mobile)
			throws CreateException {
	}
	
	public Integer ejbFindByPrimaryKey(Integer id){
		return id;
	}
	
	// ejb-entity-bean standard methods

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
	}

	@Override
	public void ejbLoad() throws EJBException, RemoteException {
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
	}

	@Override
	public void ejbRemove() throws RemoveException, EJBException, RemoteException {
	}

	@Override
	public void ejbStore() throws EJBException, RemoteException {
	}
	EntityContext ctx;
	@Override
	public void setEntityContext(EntityContext ctx) throws EJBException, RemoteException {
		this.ctx = ctx;
	}

	@Override
	public void unsetEntityContext() throws EJBException, RemoteException {
		this.ctx = null;
	}

}
