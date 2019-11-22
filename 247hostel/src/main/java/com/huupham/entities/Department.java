package com.huupham.entities;
// Generated Oct 20, 2019 3:14:09 PM by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Department generated by hbm2java
 */
@Entity
@Table(name = "department", catalog = "db247hostel")
public class Department implements java.io.Serializable {

	private int id;
	private String name;
	private Set<Employee> employees = new HashSet<Employee>(0);

	public Department() {
	}

	public Department(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Department(int id, String name, Set<Employee> employees) {
		this.id = id;
		this.name = name;
		this.employees = employees;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}