package com.huupham.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.Department;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class DepartmentDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Department> getCities() {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Department";
			Query query = session.createQuery(sql);
			List<Department> cities = query.getResultList();

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
	public Department getDepartmentById(int id) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Department where id = " + id;
			Query query = session.createQuery(sql);
			Department department = (Department) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return department;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

}
