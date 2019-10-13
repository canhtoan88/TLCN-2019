package com.huupham.entities;
// Generated Oct 13, 2019, 8:16:52 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Video generated by hbm2java
 */
@Entity
@Table(name = "video", catalog = "db247hostel")
public class Video implements java.io.Serializable {

	private int id;
	private Hostel hostel;
	private String url;

	public Video() {
	}

	public Video(int id, Hostel hostel, String url) {
		this.id = id;
		this.hostel = hostel;
		this.url = url;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idHostel", nullable = false)
	public Hostel getHostel() {
		return this.hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}

	@Column(name = "url", nullable = false, length = 250)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
