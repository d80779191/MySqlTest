package tw.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlTest {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=Big5", "root", "");
			System.out.println("Connected to the database");
			Statement statement = conn.createStatement();
//			statement.executeUpdate("CREATE TABLE Securities " + "(userName VARCHAR(40), passeord VARCHAR(40), userAgent VARCHAR(40), id VARCHAR(40), token VARCHAR(40), name VARCHAR(40), lastTime VARCHAR(40))");
//			String qry1 = "INSERT INTO Securities VALUES ('Allen','12345','User-Agent','G7AZCY057006X78','xxx','iPhone6+','1516929949311')";
//			statement.executeUpdate(qry1);
			String qry2 = "SELECT * FROM Securities where userName='Allen'";
			ResultSet rs = statement.executeQuery(qry2);
			ResultSetMetaData rm = rs.getMetaData();
			int cnum = rm.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= cnum; i++) {
					System.out.print(rm.getColumnName(i) + ":" + rs.getObject(i) + " ");
				}
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connected failed:SQLException");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("Connected failed:InstantiationException");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Connected failed:IllegalAccessException");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Connected failed:ClassNotFoundException");
			e.printStackTrace();
		}
	}

}
