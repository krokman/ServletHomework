package dao;

import model.Good;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class GoodDaoHib {
	final static Logger logger = Logger.getLogger(GoodDaoHib.class);

	public Good findById(int id) {
		return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Good.class, id);
	}

	public void save(Good good) {
		logger.debug("adding good");
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(good);
		tx1.commit();
		session.close();
	}

	public void update(Good good) {
		logger.debug("updating good");
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(good);
		tx1.commit();
		session.close();
	}

	public void delete(Good good) {
		logger.debug("deleting good");
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(good);
		tx1.commit();
		session.close();
	}


	public List<Good> findAll() {
		logger.debug("getting all good");
		List<Good> users = (List<Good>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Good").list();
		return users;
	}
}
