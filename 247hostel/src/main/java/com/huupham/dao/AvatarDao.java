package com.huupham.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.Avatar;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class AvatarDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Avatar> getAvatarsByIdUser(int idUser) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Avatar where idUser = " + idUser + " order by id desc";
			Query query = session.createQuery(sql);
			List<Avatar> avatars = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return avatars;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	// Get newest avatar of user
	@SuppressWarnings({ "rawtypes" })
	public Avatar getAvatarByIdUser(int idUser) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Avatar where idUser = " + idUser + " order by id desc";
			Query query = session.createQuery(sql);
			Avatar avatar = (Avatar) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return avatar;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public Avatar saveAvatar(Avatar avatar) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Insert data
			session.save(avatar);

			// Commit data
			session.getTransaction().commit();
			
			// Get avatar save
			Avatar avatarSave = getAvatarByIdUser(avatar.getUser().getId());

			return avatarSave;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public boolean updateAvatar(Avatar avatar) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Update data
			session.update(avatar);

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

	public boolean deleteAvatar(Avatar avatar) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Delete data
			session.delete(avatar);

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
