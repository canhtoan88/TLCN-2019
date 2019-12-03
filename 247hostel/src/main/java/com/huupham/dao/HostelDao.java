package com.huupham.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
	
	private RangePrice rangePrice1 = new RangePrice(0, 2000000);
	private RangePrice rangePrice2 = new RangePrice(2000000, 3000000);
	private RangePrice rangePrice3 = new RangePrice(3000000, 4000000);
	private RangePrice rangePrice4 = new RangePrice(4000000, 6000000);
	private RangePrice rangePrice5 = new RangePrice(6000000, 10000000);
	private RangePrice rangePrice6 = new RangePrice(10000000, 999999999);
	
	private List<RangePrice> rangePrices = null;	

	public HostelDao() {
		super();
		// TODO Auto-generated constructor stub
		
		rangePrices = new ArrayList<>();
		rangePrices.add(rangePrice1);
		rangePrices.add(rangePrice2);
		rangePrices.add(rangePrice3);
		rangePrices.add(rangePrice4);
		rangePrices.add(rangePrice5);
		rangePrices.add(rangePrice6);
	}

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
			
//			System.out.println(sql);
			
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
			String sql = "from Hostel order by id desc";
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Hostel> getHostelsByRented(boolean isRented) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel where isRented = " + isRented + " order by id desc";
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Hostel> getHostelsByRangePrice(int rangePrice) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Hostel order by id desc";
			
			if(rangePrice != 0) {
				if(rangePrices.get(rangePrice - 1) != null) {
					sql = "from Hostel where price >= "+rangePrices.get(rangePrice - 1).getMinPrice()
							+" and price < "+rangePrices.get(rangePrice - 1).getMaxPrice()+" order by id desc";
				}
			}
			
//			System.out.println(sql);
			
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Hostel> getHostelsPostByTime(int time, int timeType) {
		// TODO Auto-generated method stub

		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
//		int day = localDate.getDayOfMonth();

//		System.out.println(localDate);
//		System.out.println(year);
//		System.out.println(month);
//		System.out.println(day);

		String sql = "";
		switch (timeType) {
		case -1:
			// select all
			sql = "from Hostel order by id desc";
			break;

		case 0:
			// select by date
			sql = "from Hostel where timestamp like '" + year + "-" + month + "-" + time + "%' order by id desc";
			break;

		case 1:
			// select by month
			sql = "from Hostel where timestamp like '" + year + "-" + time + "-%' order by id desc";
			break;

		case 2:
			// select by year
			sql = "from Hostel where timestamp like '" + time + "-%' order by id desc";
			break;

		default:
			// select all
			sql = "from Hostel order by id desc";
			break;
		}

//		System.out.println(sql);

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
//			String sql = "from Hostel order by id desc";
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Integer> getHostelsPostCountByTime(int timeType) {
		// TODO Auto-generated method stub

		List<Integer> hostelsPostCountByTime = new ArrayList();

		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();

//		System.out.println(localDate);
//		System.out.println(year);
//		System.out.println(month);
//		System.out.println(day);

		switch (timeType) {
		case -1:
			// select all
			hostelsPostCountByTime.add(getHostels().size());
			break;

		case 0:
			// select by date
			for (int i = 1; i <= day; i++) {
				hostelsPostCountByTime.add(getHostelsPostByTime(i, timeType).size());
			}
			break;

		case 1:
			// select by month
			for (int i = 1; i <= month; i++) {
				hostelsPostCountByTime.add(getHostelsPostByTime(i, timeType).size());
			}
			break;

		case 2:
			// select by year
			for (int i = (year - 5); i <= year; i++) {
				hostelsPostCountByTime.add(getHostelsPostByTime(i, timeType).size());
			}
			break;

		default:
			// select all
			hostelsPostCountByTime.add(getHostels().size());
			break;
		}

//		System.out.println(hostelsPostCountByTime);

		return hostelsPostCountByTime;
	}

}
