package util;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import net.sf.log4jdbc.sql.jdbcapi.DriverSpy;

public class HikariCPUtil {
	private static HikariDataSource dataSource;
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:log4jdbc:mariadb://np.dev-lumi.com:3306/pbl");
		config.setUsername("sample");
		config.setPassword("1234");
//		config.setDriverClassName("org.mariadb.jdbc.Driver");
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(5);
		config.setIdleTimeout(30000);
		config.setConnectionTimeout(30000);
		config.setPoolName("MyHikariCP");
		
		dataSource = new HikariDataSource(config);
	}
	public static DataSource getDataSource() {
		return dataSource;
	}
	public static void main(String[] args) {
		System.out.println(dataSource);
	}
}
