package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBLayer {
	static Connection _connection;

	private DBLayer() {
	}

	public static synchronized Connection getConnection() throws SQLException {
		if (_connection == null) {
			String url = "jdbc:postgresql://localhost:5433/stats_collector";
			Properties props = new Properties();
			props.setProperty("user", "postgres");
			props.setProperty("password", "a");
			_connection = DriverManager.getConnection(url, props);
		}
		return _connection;
	}

	public static synchronized void InsertData(String table_name, String values) {
		Statement stmt = null;
		try {
			stmt = getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String query = "INSERT INTO " + table_name + " VALUES " + values;

		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
