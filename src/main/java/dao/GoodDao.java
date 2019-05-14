package dao;

import model.Good;

import java.util.List;

public interface GoodDao {
	Good getGoodById(int id);

	void addGood(Good good);

	void deleteGood(int id);

	void updateGood(Good good);

	List<Good> getAllGoods();
}
