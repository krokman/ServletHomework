package dao;

import model.Order;
import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

public class OrderDaoHib extends GenericDao<Order> implements OrderDao {
	public Integer getByUserId(int id) {
		try (Session session = HibernateSessionFactoryUtil
				.getSessionFactory()
				.openSession()) {
			return session
					.createSQLQuery("SELECT * FROM ORDERS WHERE USER_ID=" + id)
					.getFirstResult();

		}
	}
}