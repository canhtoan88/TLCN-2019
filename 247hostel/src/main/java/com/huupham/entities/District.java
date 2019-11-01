package com.huupham.entities;
// Generated Oct 20, 2019 3:14:09 PM by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * District generated by hbm2java
 */
@Entity
@Table(name = "district", catalog = "db247hostel")
public class District implements java.io.Serializable {

	private Integer id;
	private City city;
	private String name;
	private Set<Hostel> hostels = new HashSet<Hostel>(0);
	private Set<Street> streets = new HashSet<Street>(0);

	public District() {
	}

	public District(City city, String name) {
		this.city = city;
		this.name = name;
	}

	public District(City city, String name, Set<Hostel> hostels, Set<Street> streets) {
		this.city = city;
		this.name = name;
		this.hostels = hostels;
		this.streets = streets;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCity", nullable = false)
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	public Set<Hostel> getHostels() {
		return this.hostels;
	}

	public void setHostels(Set<Hostel> hostels) {
		this.hostels = hostels;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	public Set<Street> getStreets() {
		return this.streets;
	}

	public void setStreets(Set<Street> streets) {
		this.streets = streets;
	}

}
