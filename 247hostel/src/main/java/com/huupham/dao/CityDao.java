package com.huupham.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.City;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class CityDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<City> getCities() {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from City";
			Query query = session.createQuery(sql);
			List<City> cities = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return cities;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public City getCityById(int id) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from City where id = " + id;
			Query query = session.createQuery(sql);
			City city = (City) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return city;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

}
