package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;


public abstract class GenericDao<T> {

	public T getById(Class clazz, int id) {
		return (T)HibernateSessionFactoryUtil.getSessionFactory().openSession().get(clazz, id);
	}

	public void add(T t) {

		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(t);
		tx1.commit();
		session.close();
	}

	public void update(T t) {

		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(t);
		tx1.commit();
		session.close();
	}

	public void delete(Class clazz, int id) {

		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(getById(clazz,id));
		tx1.commit();
		session.close();
	}


	public List<T> getAll(Class clazz) {
		List<T> all = (List<T>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createCriteria(clazz).list();
		return all;
	}
}
