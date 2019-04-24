import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		} else {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?serverTimezone=UTC",
						"root", "root");

			} catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e){
				e.printStackTrace();
			}
			return connection;
		}
	}
}
