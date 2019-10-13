package com.huupham.entities;
// Generated Oct 13, 2019, 8:16:52 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * VipHostel generated by hbm2java
 */
@Entity
@Table(name = "vip_hostel", catalog = "db247hostel")
public class VipHostel implements java.io.Serializable {

	private int id;
	private Date timestamp;
	private Hostel hostel;
	private int point;

	public VipHostel() {
	}

	public VipHostel(int id, Hostel hostel, int point) {
		this.id = id;
		this.hostel = hostel;
		this.point = point;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Version
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp", nullable = false, length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idHostel", nullable = false)
	public Hostel getHostel() {
		return this.hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}

	@Column(name = "point", nullable = false)
	public int getPoint() {
		return this.point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
