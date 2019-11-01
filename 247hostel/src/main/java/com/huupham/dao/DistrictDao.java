package com.huupham.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.District;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class DistrictDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<District> getDistrictsByIdCity(int idCity) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from District where idCity = " + idCity;
			Query query = session.createQuery(sql);
			List<District> districts = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return districts;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public District getDistrictById(int id) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from District where id = " + id;
			Query query = session.createQuery(sql);
			District district = (District) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return district;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

}
