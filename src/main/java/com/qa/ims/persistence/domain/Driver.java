package com.qa.ims.persistence.domain;

public class Driver {

	private Long driver_id;
	private String firstName;
	private String surname;


	public Driver(String firstName, String surname) {
		this.setFirstName(firstName);
		this.setSurname(surname);
	}

	public Driver(Long driver_id, String firstName, String surname) {
		this.setId(driver_id);
		this.setFirstName(firstName);
		this.setSurname(surname);
	}

	public Long getId() {
		return driver_id;
	}

	public void setId(Long driver_id) {
		this.driver_id = driver_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Driver ID: " + driver_id + " First name: " + firstName + " Surname: " + surname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((driver_id == null) ? 0 : driver_id.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		if (getFirstName() == null) {
			if (other.getFirstName() != null)
				return false;
		} else if (!getFirstName().equals(other.getFirstName()))
			return false;
		if (driver_id == null) {
			if (other.driver_id != null)
				return false;
		} else if (!driver_id.equals(other.driver_id))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}
