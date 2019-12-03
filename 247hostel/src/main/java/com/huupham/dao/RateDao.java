package com.huupham.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.Rate;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class RateDao {

	@SuppressWarnings("rawtypes")
	public Rate getRateById(int id) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Rate where id = " + id;
			Query query = session.createQuery(sql);
			Rate rate = (Rate) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return rate;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public Rate getRateByIdUserAndIdHostel(int idUser, int idHostel) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Rate where idUser = " + idUser + " and idHostel = " + idHostel;
			Query query = session.createQuery(sql);
			Rate rate = (Rate) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return rate;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Rate> getRatesByIdHostel(int idHostel) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Rate where idHostel = " + idHostel;
			Query query = session.createQuery(sql);
			List<Rate> rates = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return rates;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public float getRateAvgByIdHostel(int idHostel) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Rate where idHostel = " + idHostel;
			Query query = session.createQuery(sql);
			List<Rate> rates = query.getResultList();

			// Get rate avg
			int rateAvg = 0; // trung bình đánh giá
			int rateSum = 0; // tổng sao đánh giá
			int rateCount = 0; // số lần đánh giá
			for (Rate rate : rates) {
				rateCount++;
				rateSum += rate.getRate();
			}
			if (rateSum > 0 && rateCount != 0) {
				rateAvg = Math.round(rateSum / rateCount * 10) / 10;
			}

			// Commit data
			session.getTransaction().commit();

			return rateAvg;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return 0;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int getRateCountByIdHostel(int idHostel) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Rate where idHostel = " + idHostel;
			Query query = session.createQuery(sql);
			List<Rate> rates = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return rates.size();
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return 0;
		}
	}

	public Rate saveRate(Rate rate) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Insert data
			session.save(rate);

			// Commit data
			session.getTransaction().commit();
			
			// Get rate save
			Rate rateSave = getRateByIdUserAndIdHostel(rate.getUser().getId(), rate.getHostel().getId());

			return rateSave;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public Rate updateRate(Rate rate) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Update data
			session.update(rate);

			// Commit data
			session.getTransaction().commit();
			
			// Get rate update
			Rate rateUpdate = getRateById(rate.getId());

			return rateUpdate;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public boolean deleteRate(Rate rate) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Delete data
			session.delete(rate);

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
	public List<Rate> getRates() {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Rate order by id desc";
			Query query = session.createQuery(sql);
			List<Rate> rates = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return rates;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

}
