package com.huupham.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.Hostel;
import com.huupham.models.RangePrice;
import com.huupham.models.RangeSpace;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class HostelDao {

	// Lấy những nhà trọ mới nhất
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Hostel> getHostels(int page, int count, int isCensored) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel order by id desc";
			
			if(isCensored != -2) {
				sql = "from Hostel where isCensored = " + isCensored + " order by id desc";
			}
			
			System.out.println(sql);
			
			Query query = session.createQuery(sql);
			query.setFirstResult((page - 1) * count); // page >= 1
			query.setMaxResults(count);
			List<Hostel> hostels = query.list();

			// Commit data
			session.getTransaction().commit();

			return hostels;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	// Lấy những nhà trọ mới nhất đã được phê duyệt
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Hostel> getNewHostels_IsCensored(int page, int count) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel where isRented = 0 and isCensored = 1 order by id desc";
			Query query = session.createQuery(sql);
			query.setFirstResult((page - 1) * count); // page >= 1
			query.setMaxResults(count);
			List<Hostel> hostels = query.list();

			// Commit data
			session.getTransaction().commit();

			return hostels;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public Hostel getHostelById(int id) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel where id = " + id;
			Query query = session.createQuery(sql);
			Hostel hostel = (Hostel) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return hostel;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Hostel> searchHostels(int idCity, int idDistrict, int idStreet, RangePrice rangePrice,
			RangeSpace rangeSpace, int page, int count) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			String queryCity = "";
			if (idCity != 0) {
				queryCity = " where idCity = " + idCity;
			} else {
				queryCity = " where id > 0";
			}

			String queryDistrict = "";
			if (idDistrict != 0) {
				queryDistrict = " and idDistrict = " + idDistrict;
			}

			String queryStreet = "";
			if (idStreet != 0) {
				queryStreet = " and idStreet = " + idStreet;
			}

			String queryRangePrice = "";
			if (rangePrice != null) {
				queryRangePrice = " and (price >= " + rangePrice.getMinPrice() * 1000000 + ")" + " and (price < "
						+ rangePrice.getMaxPrice() * 1000000 + ")";
			}

			String queryRangeSpace = "";
			if (rangeSpace != null) {
				queryRangeSpace = " and (space >= " + rangeSpace.getMinSpace() + ")" + " and (space <= "
						+ rangeSpace.getMaxSpace() + ")";
			}

			// Query data
			String sql = "from Hostel" + queryCity + queryDistrict + queryStreet + queryRangePrice + queryRangeSpace
					+ " and isRented = 0 and isCensored = 1 order by id desc";

//			System.out.println(sql);

			Query query = session.createQuery(sql);
			query.setFirstResult((page - 1) * count);
			query.setMaxResults(count);

			List<Hostel> hostels = query.list();

			// Commit data
			session.getTransaction().commit();

			return hostels;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Hostel> searchHostels2(RangePrice rangePrice, RangeSpace rangeSpace, int page, int count) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			String queryRangePrice = "";
			if (rangePrice != null) {
				queryRangePrice = " where (price >= " + rangePrice.getMinPrice() * 1000000 + ")" + " and (price < "
						+ rangePrice.getMaxPrice() * 1000000 + ")";
			}
			else {
				queryRangePrice = " where id > 0";
			}

			String queryRangeSpace = "";
			if (rangeSpace != null) {
				queryRangeSpace = " and (space >= " + rangeSpace.getMinSpace() + ")" + " and (space <= "
						+ rangeSpace.getMaxSpace() + ")";
			}

			// Query data
			String sql = "from Hostel" + queryRangePrice + queryRangeSpace
					+ " and isRented = 0 and isCensored = 1 order by id desc";

//			System.out.println(sql);

			Query query = session.createQuery(sql);
			query.setFirstResult((page - 1) * count);
			query.setMaxResults(count);

			List<Hostel> hostels = query.list();

			// Commit data
			session.getTransaction().commit();

			return hostels;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Hostel> getHostelsByIdUser(int idUser, int page, int count) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel where idUser = " + idUser + " order by id desc";
			Query query = session.createQuery(sql);
			query.setFirstResult((page - 1) * count);
			query.setMaxResults(count);

			List<Hostel> hostels = query.list();

			// Commit data
			session.getTransaction().commit();

			return hostels;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Hostel getNewestHostelSaveByIdUser(int idUser) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel where idUser = " + idUser + " order by id desc";
			Query query = session.createQuery(sql);
			query.setFirstResult(0);
			query.setMaxResults(1);
			List<Hostel> hostels = query.list();

			// Commit data
			session.getTransaction().commit();

			return hostels.get(0);
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public Hostel saveHostel(Hostel hostel) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Insert data
			session.save(hostel);

			// Commit data
			session.getTransaction().commit();
			
			// Get hostel save
			Hostel hostelSave = getNewestHostelSaveByIdUser(hostel.getUser().getId());

			return hostelSave;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public Hostel updateHostel(Hostel hostel) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Update data
			session.update(hostel);

			// Commit data
			session.getTransaction().commit();
			
			// Get hostel update
			Hostel hostelUpdate = getHostelById(hostel.getId());

			return hostelUpdate;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public boolean deleteHostel(Hostel hostel) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Delete data
			session.delete(hostel);

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
	public int getCountHostelByIdUser(int idUser) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel where idUser = " + idUser;
			Query query = session.createQuery(sql);
			List<Hostel> hostels = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return hostels.size();
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return 0;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int getCountHostelIsNotRentedByIdUser(int idUser) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel where idUser = " + idUser + " and isRented = 0";
			Query query = session.createQuery(sql);
			List<Hostel> hostels = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return hostels.size();
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return 0;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int getCountHostelIsRentedByIdUser(int idUser) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel where idUser = " + idUser + " and isRented = 1";
			Query query = session.createQuery(sql);
			List<Hostel> hostels = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return hostels.size();
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return 0;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int getCountHostelIsCensoredByIdUser(int idUser) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel where idUser = " + idUser + " and isCensored = 1";
			Query query = session.createQuery(sql);
			List<Hostel> hostels = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return hostels.size();
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return 0;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int getCountHostelIsWaittingCensorByIdUser(int idUser) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel where idUser = " + idUser + " and isCensored = 0";
			Query query = session.createQuery(sql);
			List<Hostel> hostels = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return hostels.size();
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return 0;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int getCountHostelIsNotCensoredByIdUser(int idUser) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel where idUser = " + idUser + " and isCensored = -1";
			Query query = session.createQuery(sql);
			List<Hostel> hostels = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return hostels.size();
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return 0;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Hostel> getHostels() {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel";
			Query query = session.createQuery(sql);
			List<Hostel> hostels = query.list();

			// Commit data
			session.getTransaction().commit();

			return hostels;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

}
