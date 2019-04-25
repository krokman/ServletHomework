import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class UserDao {
	private Connection connection;

	UserDao() {
		connection = DbUtil.getConnection();
	}

	void addUser(User user) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into table_users(nickname, password) values(?,?)");
			preparedStatement.setString(1, user.getNickName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void deleteUser(String nickname) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from table_users where table_users.nickname=?");
			preparedStatement.setString(1, nickname);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	User getUserByNickname(String nickname) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from table_users where nickname=?");
			preparedStatement.setString(1, nickname);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				user.setNickName(rs.getString("nickname"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
