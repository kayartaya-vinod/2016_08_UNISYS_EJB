package training.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Laptop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String serialNumber;
	private String make;
	private String model;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="owner_id", unique = true) // unique=true makes it one-to-one
	private Employee owner;

	public Laptop() {
	}

	public Laptop(String serialNumber, String make, String model) {
		this.serialNumber = serialNumber;
		this.make = make;
		this.model = model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

}
