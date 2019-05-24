package dao;

import model.Order;

public interface OrderDao extends GenericDaoImpl<Order> {
	 Integer getByUserId(int id);
}
