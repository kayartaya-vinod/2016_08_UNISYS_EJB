package training.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import training.dao.DaoException;
import training.dao.EmployeeDao;
import training.entity.Employee;
import training.entity.Laptop;
import training.entity.Skill;

@Stateful
public class EmployeeDaoBean implements EmployeeDao {

	@PostConstruct
	public void pc() {
		// replacement for ejb 2.1's ejbCreate() method
		System.out.println(">>>> EmployeeDaoBean's post-construct called");
		System.out.println("Hashcode is " + this.hashCode());
	}

	@PreDestroy
	public void pd() {
		// replacement for ejb 2.1's ejbRemove() method
		System.out.println(">>>> EmployeeDaoBean's pre-destroy called");
		System.out.println("Hashcode is " + this.hashCode());
	}
	
	@PrePassivate // only applicable for Stateful beans
	public void pp(){
		System.out.println(">>>> EmployeeDaoBean's pre-passivate called");
		System.out.println("Hashcode is " + this.hashCode());
	}
	
	@PostActivate // only applicable for Stateful beans
	public void pa(){
		System.out.println(">>>> EmployeeDaoBean's post-activate called");
		System.out.println("Hashcode is " + this.hashCode());
	}

	@PersistenceContext(unitName = "northwind_pu")
	EntityManager em;

	@Override
	public Employee getEmployee(Integer employeeId) throws DaoException {
		System.out.println(">>>> Serving a client, hashcode is " + hashCode());
		try {
			return em.find(Employee.class, employeeId);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void assignNewLaptop(Integer employeeId, Laptop laptop) throws DaoException {
		Employee e1 = em.find(Employee.class, employeeId);
		if (e1 != null) {
			// e1.setLaptop(laptop);
			// em.merge(e1);
			laptop.setOwner(e1);
			em.persist(laptop);
		}
	}

	@Override
	public void assignLaptop(Integer employeeId, String serialNumber) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createSkill(Skill skill) throws DaoException {
		em.persist(skill);
	}

}
