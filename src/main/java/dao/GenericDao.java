package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;


public abstract class GenericDao<T> {

	public T getById(Class clazz, int id) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			T t = (T) session.get(clazz, id);
			return t;
		}
	}

	public void add(T t) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction tx1 = session.beginTransaction();
			session.save(t);
			tx1.commit();
		}
	}

	public void update(T t) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction tx1 = session.beginTransaction();
			session.update(t);
			tx1.commit();
		}
	}

	public void delete(Class clazz, int id) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction tx1 = session.beginTransaction();
			session.delete(getById(clazz, id));
			tx1.commit();
		}
	}


	public List<T> getAll(Class clazz) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			List<T> all = (List<T>) session.createCriteria(clazz).list();
			return all;
		}
	}
}
