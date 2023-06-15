package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "rkddkwl123!";
    private static final String URL = "jdbc:mysql://hanaroerpprojectinstance.cueixcjf2n4d.ap-northeast-2.rds.amazonaws.com:3306/hanaroErpDatabase";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}