package dao;

import model.Good;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodDao {
	private Connection connection;

	public GoodDao() {
		connection = DbUtil.getConnection();
	}

	public void addUser(Good good){
		try{
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO GOODS(NAME, DESCRIPTION, PRICE) VALUES(?,?,?)");
			preparedStatement.setString(1,good.getName());
			preparedStatement.setString(2,good.getDescription());
			preparedStatement.setString(3,good.getPrice());

			preparedStatement.executeUpdate();

		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	public Good getGoodByName(String name){
		Good good = new Good();
		try{
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM GOODS WHERE NAME=?");
			preparedStatement.setString(1,name);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				good.setName(resultSet.getString("NAME"));
				good.setDescription(resultSet.getString("DESCRIPTION"));
				good.setPrice(resultSet.getString("PRICE"));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return good;
	}
	public List<Good> getAllGoods(){
		List<Good> goodList = new ArrayList<>();
		try{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM GOODS");
			while(resultSet.next()){
				goodList.add(new Good(resultSet.getString("NAME"), resultSet.getString("DESCRIPTION"),
						resultSet.getString("PRICE")));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return goodList;
	}
}
