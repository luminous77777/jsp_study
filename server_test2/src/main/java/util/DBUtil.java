package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;

public class DBUtil {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mariadb://np.dev-lumi.com:3306/sample","sample","1234");
		} catch (SQLException | ClassNotFoundException e) {
			e.getStackTrace();
		}
		return connection;
		
	}

}
