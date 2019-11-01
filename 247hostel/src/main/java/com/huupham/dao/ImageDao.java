package com.huupham.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.Image;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class ImageDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Image> getImagesByIdHostel(int idHostel) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Image where idHostel = " + idHostel;
			Query query = session.createQuery(sql);
			List<Image> images = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return images;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public Image getImageById(int id) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Image where id = " + id;
			Query query = session.createQuery(sql);
			Image image = (Image) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return image;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public Image saveImage(Image image) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Insert data
			session.save(image);

			// Commit data
			session.getTransaction().commit();
			
			// Get image save
			List<Image> images = getImagesByIdHostel(image.getHostel().getId());

			return images.get(images.size() - 1);
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public Image updateImage(Image image) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Update data
			session.update(image);

			// Commit data
			session.getTransaction().commit();
			
			// Get image update
			Image imageUpdate = getImageById(image.getId());

			return imageUpdate;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public boolean deleteImage(Image image) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Delete data
			session.delete(image);

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
