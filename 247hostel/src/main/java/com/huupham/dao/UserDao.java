package com.huupham.dao;

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
		
		return (user.getId() +"|"+ user.getAuthorization().getId() +"|"+ user.getFullname() 
			 +"|"+  user.getEmail() +"|"+ user.getPhone() +"|"+ user.getAddress() 
			  +"|"+ user.getPassword() +"|"+ user.getBirthday() +"|"+ user.getTimeRegister() 
			   +"|"+ user.isGender());
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
			if(!user.getEmail().trim().equals("")) {
				userSave = getUserByUsername(user.getEmail());
			}
			else if(!user.getPhone().trim().equals("")) {
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

}
