package dao;

import model.User;
import org.apache.log4j.Logger;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	final static Logger logger = Logger.getLogger(UserDao.class);
	private Connection connection;

	public UserDao() {
		connection = DbUtil.getConnection();
	}

	public void addUser(User user){
		try{
			logger.trace("adding user request");
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO USERS(NICKNAME, PASSWORD, EMAIL, ROLE) VALUES(?,?,?,?)");
			preparedStatement.setString(1,user.getNickname());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3,user.getEmail());
			preparedStatement.setString(4,user.getRole());
			preparedStatement.executeUpdate();

		}catch (SQLException e){
			logger.error("wrong request data" + e);
		}
	}
	public void deleteUser(String nickname){
		try{
			logger.trace("deleting user request");
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM USERS WHERE NICKNAME=?");
			preparedStatement.setString(1,nickname);
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			logger.error("wrong request data" + e);
		}
	}
	public void updateUser(User user){
		try{
			logger.trace("updating user request");
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE USERS SET NICKNAME=?, PASSWORD=?, EMAIL=?, ROLE=? WHERE NICKNAME=?");
			preparedStatement.setString(1,user.getNickname());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(5,user.getNickname());
			preparedStatement.setString(4,user.getRole());
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			logger.error("wrong request data" + e);
		}
	}
	public User getUserByNickname(String nickname){
		User user = new User();
		try{
			logger.trace("getting user request");
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
			logger.error("wrong request data" + e);
		}
		return user;
	}
	public List<User> getAllUsers(){
		List<User> userList = new ArrayList<>();
		try{
			logger.trace("getting users request");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
			while(resultSet.next()){
				userList.add(new User(resultSet.getString("NICKNAME"), resultSet.getString("PASSWORD"),
						resultSet.getString("EMAIL"),resultSet.getString("ROLE")));
			}
		}catch (SQLException e){
			logger.error("wrong request data" + e);
		}
		return userList;
	}

}
