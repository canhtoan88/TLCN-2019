package com.huupham.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.Authorization;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class AuthorizationDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Authorization> getAuthorizations() {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Authorization";
			Query query = session.createQuery(sql);
			List<Authorization> authorizations = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return authorizations;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public Authorization getAuthorizationById(int id) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Authorization where id = " + id;
			Query query = session.createQuery(sql);
			Authorization authorization = (Authorization) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return authorization;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

}
