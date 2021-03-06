package com.huupham.entities;
// Generated Oct 20, 2019 3:14:09 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "db247hostel")
public class User implements java.io.Serializable {

	private Integer id;
	private Authorization authorization;
	private String fullname;
	private String phone;
	private String email;
	private String address;
	private String password;
	private Date birthday;
	private Date timeRegister;
	private boolean gender;
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Conversation> conversationsForIdUser1 = new HashSet<Conversation>(0);
	private Set<Conversation> conversationsForIdUser2 = new HashSet<Conversation>(0);
	private Set<Hostel> hostels = new HashSet<Hostel>(0);
	private Set<Message> messages = new HashSet<Message>(0);
	private Set<Rate> rates = new HashSet<Rate>(0);
	private Set<Avatar> avatars = new HashSet<Avatar>(0);
	private Set<Employee> employees = new HashSet<Employee>(0);
	private Set<ReplyComment> replyComments = new HashSet<ReplyComment>(0);

	public User() {
	}

	public User(Authorization authorization, String fullname, String password, Date timeRegister, boolean gender) {
		this.authorization = authorization;
		this.fullname = fullname;
		this.password = password;
		this.timeRegister = timeRegister;
		this.gender = gender;
	}

	public User(Authorization authorization, String fullname, String phone, String email, String address,
			String password, Date birthday, Date timeRegister, boolean gender, Set<Comment> comments,
			Set<Conversation> conversationsForIdUser1, Set<Conversation> conversationsForIdUser2, Set<Hostel> hostels,
			Set<Message> messages, Set<Rate> rates, Set<Avatar> avatars, Set<Employee> employees,
			Set<ReplyComment> replyComments) {
		this.authorization = authorization;
		this.fullname = fullname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.password = password;
		this.birthday = birthday;
		this.timeRegister = timeRegister;
		this.gender = gender;
		this.comments = comments;
		this.conversationsForIdUser1 = conversationsForIdUser1;
		this.conversationsForIdUser2 = conversationsForIdUser2;
		this.hostels = hostels;
		this.messages = messages;
		this.rates = rates;
		this.avatars = avatars;
		this.employees = employees;
		this.replyComments = replyComments;
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
	@JoinColumn(name = "idAuthorization", nullable = false)
	public Authorization getAuthorization() {
		return this.authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	@Column(name = "fullname", nullable = false, length = 50)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", length = 250)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timeRegister", nullable = false, length = 19)
	public Date getTimeRegister() {
		return this.timeRegister;
	}

	public void setTimeRegister(Date timeRegister) {
		this.timeRegister = timeRegister;
	}

	@Column(name = "gender", nullable = false)
	public boolean isGender() {
		return this.gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByIdUser1")
	public Set<Conversation> getConversationsForIdUser1() {
		return this.conversationsForIdUser1;
	}

	public void setConversationsForIdUser1(Set<Conversation> conversationsForIdUser1) {
		this.conversationsForIdUser1 = conversationsForIdUser1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByIdUser2")
	public Set<Conversation> getConversationsForIdUser2() {
		return this.conversationsForIdUser2;
	}

	public void setConversationsForIdUser2(Set<Conversation> conversationsForIdUser2) {
		this.conversationsForIdUser2 = conversationsForIdUser2;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Hostel> getHostels() {
		return this.hostels;
	}

	public void setHostels(Set<Hostel> hostels) {
		this.hostels = hostels;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Rate> getRates() {
		return this.rates;
	}

	public void setRates(Set<Rate> rates) {
		this.rates = rates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Avatar> getAvatars() {
		return this.avatars;
	}

	public void setAvatars(Set<Avatar> avatars) {
		this.avatars = avatars;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<ReplyComment> getReplyComments() {
		return this.replyComments;
	}

	public void setReplyComments(Set<ReplyComment> replyComments) {
		this.replyComments = replyComments;
	}

}
