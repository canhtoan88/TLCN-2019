package com.huupham.entities;
// Generated Oct 20, 2019 3:14:09 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Comment generated by hbm2java
 */
@Entity
@Table(name = "comment", catalog = "db247hostel")
public class Comment implements java.io.Serializable {

	private int id;
	private Date timestamp;
	private Hostel hostel;
	private User user;
	private String content;
	private Set<ReplyComment> replyComments = new HashSet<ReplyComment>(0);

	public Comment() {
	}

	public Comment(int id, Hostel hostel, User user, String content) {
		this.id = id;
		this.hostel = hostel;
		this.user = user;
		this.content = content;
	}

	public Comment(int id, Hostel hostel, User user, String content, Set<ReplyComment> replyComments) {
		this.id = id;
		this.hostel = hostel;
		this.user = user;
		this.content = content;
		this.replyComments = replyComments;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "content", nullable = false, length = 250)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
	public Set<ReplyComment> getReplyComments() {
		return this.replyComments;
	}

	public void setReplyComments(Set<ReplyComment> replyComments) {
		this.replyComments = replyComments;
	}

}