package com.excilys.computer_database.persistence.model;

import java.time.LocalDate;

/**
 * This class describe a computer with all its parameters. id, name, introduced
 * date, discontinued date, and company id. This is a simple POJO.
 * 
 * @author rlarroque
 *
 */
public class Computer {

	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company company;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Computer(String name, LocalDate introduced, LocalDate discontinued, Company company) {
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	public Computer(String name) {
		this.name = name;
	}

	public Computer() {
	}

	@Override
	public String toString() {

		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Computer other = (Computer) obj;

		if (company == null) {
			
			if (other.company != null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
			return false;
		}

		if (discontinued == null) {
			
			if (other.discontinued != null) {
				return false;
			}
		} else if (!discontinued.equals(other.discontinued)) {
			return false;
		}

		if (id != other.id) {
			return false;
		}

		if (introduced == null) {

			if (other.introduced != null) {
				return false;
			}
		} else if (!introduced.equals(other.introduced)) {
			return false;
		}

		if (name == null) {

			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}

		return true;
	}

}