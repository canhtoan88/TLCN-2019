package com.huupham.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.Video;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class VideoDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Video> getVideosByIdHostel(int idHostel) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Video where idHostel = " + idHostel;
			Query query = session.createQuery(sql);
			List<Video> videos = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return videos;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public Video getVideoById(int id) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Video where id = " + id;
			Query query = session.createQuery(sql);
			Video video = (Video) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return video;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public Video saveVideo(Video video) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Insert data
			session.save(video);

			// Commit data
			session.getTransaction().commit();
			
			// Get video save
			List<Video> videos = getVideosByIdHostel(video.getHostel().getId());

			return videos.get(videos.size() - 1);
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public Video updateVideo(Video video) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Update data
			session.update(video);

			// Commit data
			session.getTransaction().commit();
			
			// Get video update
			Video videoUpdate = getVideoById(video.getId());

			return videoUpdate;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public boolean deleteVideo(Video video) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Delete data
			session.delete(video);

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
