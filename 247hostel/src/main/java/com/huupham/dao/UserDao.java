package com.huupham.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.User;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class UserDao {

	public String getUserInfo(User user) {

		return (user.getId() + "|" + user.getAuthorization().getId() + "|" + user.getFullname() + "|" + user.getEmail()
				+ "|" + user.getPhone() + "|" + user.getAddress() + "|" + user.getPassword() + "|" + user.getBirthday()
				+ "|" + user.getTimeRegister() + "|" + user.isGender());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> getUsers(int page, int count) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from User order by id desc";
			Query query = session.createQuery(sql);
			query.setFirstResult((page - 1) * count);
			query.setMaxResults(count);
			List<User> users = query.list();

			// Commit data
			session.getTransaction().commit();

			return users;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public User getUserById(int id) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from User where id = " + id;
			Query query = session.createQuery(sql);
			User user = (User) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return user;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from User where phone = '" + username + "' or email = '" + username + "'";
			Query query = session.createQuery(sql);
			User user = (User) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return user;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public User checkLogin(String username, String password) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from User where phone = '" + username + "' or email = '" + username + "' and password = '"
					+ password + "'";
			Query query = session.createQuery(sql);
			User user = (User) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return user;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean checkExistsEmail(String email) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from User where email = '" + email + "'";
			Query query = session.createQuery(sql);
			User user = (User) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			if (user != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean checkExistsPhone(String phone) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from User where phone = '" + phone + "'";
			Query query = session.createQuery(sql);
			User user = (User) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			if (user != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return false;
		}
	}

	public User saveUser(User user) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Insert data
			session.save(user);

			// Commit data
			session.getTransaction().commit();

			// Get user save
			User userSave = null;
			if (!user.getEmail().trim().equals("")) {
				userSave = getUserByUsername(user.getEmail());
			} else if (!user.getPhone().trim().equals("")) {
				userSave = getUserByUsername(user.getPhone());
			}

			return userSave;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public User updateUser(User user) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Update data
			session.update(user);

			// Commit data
			session.getTransaction().commit();

			// Get user update
			User userUpdate = getUserById(user.getId());

			return userUpdate;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Delete data
			session.delete(user);

			// Commit data
			session.getTransaction().commit();

			return true;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return false;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> getUsers() {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from User order by id desc";
			Query query = session.createQuery(sql);
			List<User> users = query.list();

			// Commit data
			session.getTransaction().commit();

			return users;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> getUsersRegisterByTime(int time, int timeType) {
		// TODO Auto-generated method stub

		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
//		int day = localDate.getDayOfMonth();

//		System.out.println(localDate);
//		System.out.println(year);
//		System.out.println(month);
//		System.out.println(day);

		String sql = "";
		switch (timeType) {
		case -1:
			// select all
			sql = "from User order by id desc";
			break;

		case 0:
			// select by date
			sql = "from User where timeRegister like '" + year + "-" + month + "-" + time + "%' order by id desc";
			break;

		case 1:
			// select by month
			sql = "from User where timeRegister like '" + year + "-" + time + "-%' order by id desc";
			break;

		case 2:
			// select by year
			sql = "from User where timeRegister like '" + time + "-%' order by id desc";
			break;

		default:
			// select all
			sql = "from User order by id desc";
			break;
		}

//		System.out.println(sql);

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
//			String sql = "from User order by id desc";
			Query query = session.createQuery(sql);
			List<User> users = query.list();

			// Commit data
			session.getTransaction().commit();

			return users;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Integer> getUsersRegisterCountByTime(int timeType) {
		// TODO Auto-generated method stub

		List<Integer> usersRegisterCountByTime = new ArrayList();

		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();

//		System.out.println(localDate);
//		System.out.println(year);
//		System.out.println(month);
//		System.out.println(day);

		switch (timeType) {
		case -1:
			// select all
			usersRegisterCountByTime.add(getUsers().size());
			break;

		case 0:
			// select by date
			for (int i = 1; i <= day; i++) {
				usersRegisterCountByTime.add(getUsersRegisterByTime(i, timeType).size());
			}
			break;

		case 1:
			// select by month
			for (int i = 1; i <= month; i++) {
				usersRegisterCountByTime.add(getUsersRegisterByTime(i, timeType).size());
			}
			break;

		case 2:
			// select by year
			for (int i = (year - 5); i <= year; i++) {
				usersRegisterCountByTime.add(getUsersRegisterByTime(i, timeType).size());
			}
			break;

		default:
			// select all
			usersRegisterCountByTime.add(getUsers().size());
			break;
		}

//		System.out.println(usersRegisterCountByTime);

		return usersRegisterCountByTime;
	}

}
