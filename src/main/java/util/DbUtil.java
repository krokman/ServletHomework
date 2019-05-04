package util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	final static Logger logger = Logger.getLogger(DbUtil.class);
	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection != null) {
			logger.trace("connection already installed");
			return connection;
		} else {
			try {
				logger.trace("getting new connection to DB");
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?serverTimezone=UTC",
						"root", "root");
			} catch (SQLException | ClassNotFoundException e) {
				logger.error("cant get connection to DB" + e);
			}
		}
		return connection;
	}
}
