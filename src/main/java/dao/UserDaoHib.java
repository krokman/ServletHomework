package dao;

import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDaoHib {
	final static Logger logger = Logger.getLogger(UserDaoHib.class);

	public User findById(int id) {
		return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
	}

	public void save(User user) {
		logger.debug("adding user");
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(user);
		tx1.commit();
		session.close();
	}

	public void update(User user) {
		logger.debug("updating user");
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(user);
		tx1.commit();
		session.close();
	}

	public void delete(User user) {
		logger.debug("deleting user");
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(user);
		tx1.commit();
		session.close();
	}


	public List<User> findAll() {
		logger.debug("getting all user");
		List<User> users = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
		return users;
	}
}