package dao;

import model.Good;
import org.apache.log4j.Logger;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GoodDao {
	final static Logger logger = Logger.getLogger(GoodDao.class);
	private Connection connection;

	public GoodDao() {
		connection = DbUtil.getConnection();
	}

	public void addUser(Good good) {
		try {
			logger.trace("adding good request");
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO GOODS(NAME, DESCRIPTION, PRICE) VALUES(?,?,?)");
			preparedStatement.setString(1, good.getName());
			preparedStatement.setString(2, good.getDescription());
			preparedStatement.setString(3, good.getPrice());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error("wrong request data" + e);
		}
	}

	public Good getGoodByName(String name) {
		Good good = new Good();
		try {
			logger.trace("getting good request");
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM GOODS WHERE NAME=?");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				good.setName(resultSet.getString("NAME"));
				good.setDescription(resultSet.getString("DESCRIPTION"));
				good.setPrice(resultSet.getString("PRICE"));
			}
		} catch (SQLException e) {
			logger.error("wrong request data" + e);
		}
		return good;
	}

	public List<Good> getAllGoods() {
		List<Good> goodList = new ArrayList<>();
		try {
			logger.trace("getting goods request");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM GOODS");
			while (resultSet.next()) {
				goodList.add(new Good(resultSet.getString("NAME"), resultSet.getString("DESCRIPTION"),
						resultSet.getString("PRICE")));
			}
		} catch (SQLException e) {
			logger.error("wrong request data" + e);
		}
		return goodList;
	}
}
