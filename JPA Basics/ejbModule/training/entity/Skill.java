package training.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	private Integer id;
	private String name;
	
	@ManyToMany //(cascade=CascadeType.ALL)
	@JoinColumn(table="EMPLOYEES_SKILLS", 
		name="SKILL_ID", 
		referencedColumnName="EMPL_ID")
	private Set<Employee> employees = new HashSet<Employee>();
	
	public Skill() {
	}

	public Skill(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	// helper method to add an employee to a skill
	public void addEmployee(Employee e){
		employees.add(e);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	
}
