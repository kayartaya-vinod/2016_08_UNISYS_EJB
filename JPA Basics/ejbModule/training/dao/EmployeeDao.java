package training.dao;

import javax.ejb.Remote;

import training.entity.Employee;
import training.entity.Laptop;
import training.entity.Skill;

@Remote
public interface EmployeeDao {
	public Employee getEmployee(Integer employeeId) throws DaoException;

	public void assignNewLaptop(Integer employeeId, Laptop laptop) throws DaoException;

	public void assignLaptop(Integer employeeId, String serialNumber) throws DaoException;

	// this method should ideally be part of SkillDao
	public void createSkill(Skill skill) throws DaoException;
}
