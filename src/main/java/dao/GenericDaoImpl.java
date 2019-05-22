package dao;

import model.Good;

import java.util.List;

public interface GenericDaoImpl<T> {
	T getById(Class<T> clazz, int id);

	void add(T t);

	public void delete(Class<T> clazz, int id);

	void update(T good);

	List<T> getAll(Class<T> clazz);
}
