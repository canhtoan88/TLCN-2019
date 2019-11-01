package com.huupham.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.Street;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class StreetDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Street> getStreetsByIdDistrict(int idDistrict) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Street where idDistrict = " + idDistrict;
			Query query = session.createQuery(sql);
			List<Street> streets = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return streets;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public Street getStreetById(int id) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Street where id = " + id;
			Query query = session.createQuery(sql);
			Street street = (Street) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return street;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

}
