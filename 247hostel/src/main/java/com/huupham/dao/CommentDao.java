package com.huupham.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.huupham.entities.Comment;
import com.huupham.webconfig.HibernateConfig;

@Repository
public class CommentDao {

	@SuppressWarnings("rawtypes")
	public Comment getCommentById(int id) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Comment where id = " + id;
			Query query = session.createQuery(sql);
			Comment comment = (Comment) query.getSingleResult();

			// Commit data
			session.getTransaction().commit();

			return comment;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Comment> getCommentsByIdHostel(int idHostel) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Comment where idHostel = " + idHostel + " order by id desc";
			Query query = session.createQuery(sql);
			List<Comment> comments = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return comments;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public Comment saveComment(Comment comment) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Insert data
			session.save(comment);

			// Commit data
			session.getTransaction().commit();

			// Get rate save
			Comment commentSave = getCommentById(comment.getId());

			return commentSave;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public Comment updateComment(Comment comment) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Update data
			session.update(comment);

			// Commit data
			session.getTransaction().commit();

			// Get rate update
			Comment commentUpdate = getCommentById(comment.getId());

			return commentUpdate;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

	public boolean deleteComment(Comment comment) {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Delete data
			session.delete(comment);

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
	public List<Comment> getComments() {
		// TODO Auto-generated method stub

		SessionFactory factory = HibernateConfig.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			// Start transaction
			session.getTransaction().begin();

			// Query data
			String sql = "from Comment order by id desc";
			Query query = session.createQuery(sql);
			List<Comment> comments = query.getResultList();

			// Commit data
			session.getTransaction().commit();

			return comments;
		} catch (Exception e) {

			e.printStackTrace();

			// Rollback data
			session.getTransaction().rollback();

			return null;
		}
	}

}
