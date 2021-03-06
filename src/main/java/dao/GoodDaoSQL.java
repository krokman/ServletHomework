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

public class GoodDaoSQL {
	final static Logger logger = Logger.getLogger(GoodDaoSQL.class);
	private Connection connection;

	public GoodDaoSQL() {
		connection = DbUtil.getConnection();
	}

	public void addGood(Good good) {
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

	public Good getGoodById(int id) {
		Good good = new Good();
		try {
			logger.trace("getting good request");
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM GOODS WHERE id=?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				good.setId(resultSet.getInt("ID"));
				good.setName(resultSet.getString("NAME"));
				good.setDescription(resultSet.getString("DESCRIPTION"));
				good.setPrice(resultSet.getString("PRICE"));
			}
		} catch (SQLException e) {
			logger.error("wrong request data" + e);
		}
		return good;
	}

	public void deleteGood(int id) {
		try {
			logger.trace("deleting good request");
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM GOODS WHERE id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("wrong request data" + e);
		}
	}

	public void updateGood(Good good) {
		try {
			logger.trace("updating good request");
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE GOODS SET NAME=?, DESCRIPTION=?, PRICE=? WHERE id=?");
			preparedStatement.setString(1, good.getName());
			preparedStatement.setString(2, good.getDescription());
			preparedStatement.setString(3, good.getPrice());
			preparedStatement.setString(4, good.getName());
			preparedStatement.setInt(4, good.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("wrong request data" + e);
		}
	}

	public List<Good> getAllGoods() {
		List<Good> goodList = new ArrayList<>();
		try {
			logger.trace("getting goods request");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM GOODS");
			while (resultSet.next()) {
				goodList.add(new Good(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getString("DESCRIPTION"),
						resultSet.getString("PRICE")));
			}
		} catch (SQLException e) {
			logger.error("wrong request data" + e);
		}
		return goodList;
	}
}
