package dao;

import model.User;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	private Connection connection;

	public UserDao() {
		connection = DbUtil.getConnection();
	}

	public void addUser(User user){
		try{
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO USERS(NICKNAME, PASSWORD, EMAIL, ROLE) VALUES(?,?,?,?)");
			preparedStatement.setString(1,user.getNickname());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3,user.getEmail());
			preparedStatement.setString(4,user.getRole());
			preparedStatement.executeUpdate();

		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void deleteUser(String nickname){
		try{
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM USERS WHERE NICKNAME=?");
			preparedStatement.setString(1,nickname);
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateUser(User user){
		try{
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE USERS SET NICKNAME=?, PASSWORD=?, EMAIL=?, ROLE=? WHERE NICKNAME=?");
			preparedStatement.setString(1,user.getNickname());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(5,user.getNickname());
			preparedStatement.setString(4,user.getRole());
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public User getUserByNickname(String nickname){
		User user = new User();
		try{
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM USERS WHERE NICKNAME=?");
			preparedStatement.setString(1,nickname);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				user.setNickname(resultSet.getString("NICKNAME"));
				user.setPassword(resultSet.getString("PASSWORD"));
				user.setEmail(resultSet.getString("EMAIL"));
				user.setRole(resultSet.getString("ROLE"));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	public List<User> getAllUsers(){
		List<User> userList = new ArrayList<>();
		try{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
			while(resultSet.next()){
				userList.add(new User(resultSet.getString("NICKNAME"), resultSet.getString("PASSWORD"),
						resultSet.getString("EMAIL"),resultSet.getString("ROLE")));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return userList;
	}

}
