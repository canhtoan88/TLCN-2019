package com.huupham.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.Employee;
import com.huupham.entities.User;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class EmployeeDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Employee> getEmployees(int page, int count) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Employee order by id desc";
			Query query = session.createQuery(sql);
			query.setFirstResult((page - 1) * count);
			query.setMaxResults(count);
			List<Employee> employees = query.list();

			// Commit data
			session.getTransaction().commit();

			return employees;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Employee where id = " + id;
			Query query = session.createQuery(sql);
			Employee employee = (Employee) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return employee;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public Employee getEmployeeByUser(User user) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Employee where idUser = " + user.getId();
			Query query = session.createQuery(sql);
			Employee employee = (Employee) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return employee;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Insert data
			session.save(employee);

			// Commit data
			session.getTransaction().commit();

			// Get employee save
			Employee employeeSave = null;

			return employeeSave;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Update data
			session.update(employee);

			// Commit data
			session.getTransaction().commit();

			// Get user update
			Employee employeeUpdate = getEmployeeById(employee.getId());

			return employeeUpdate;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public boolean deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Delete data
			session.delete(employee);

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
