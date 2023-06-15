package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseUtil;

public class EmployeeDAO {
	private Connection conn;
	
    public EmployeeDAO(int employeeId) {
    	conn = DatabaseUtil.getConnection();
    }
    public String returnPasswordFromDatabase(int employeeId) {
    	String query = "SELECT password FROM employees WHERE e_id = ?;";
    	try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                resultSet.close();
                statement.close();
                return storedPassword;
            }
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
}
