package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.EmployeeDTO;
import util.DatabaseUtil;

public class Extra {

	// 지금 EmployeeDAO에서 읽어오는 건 전체 칼럼을 다 읽어오잖아요?
	// 그런데 읽어오고 싶은 칼럼들만 가져오고 싶을 땐
	// 이렇게 칼럼들을 가변인자로 적어서 SQL문을 동적으로 작성할 수있도록 메소드를 짜봤음
	// 나름 객체지향적이고 코드 자체는 간결해질 수 있지만
	// 너무 효율성이 구리지 않나 하는 생각이 들었음
	// 멋은 없지만 그냥 요구사항 별로 필요한 쿼리문에 대한 DAO 메소드만 작성하는 나을지도...
	// ORM 어필까지는 그냥 포기하고 DAO에 쿼리만 다 때려박는 거에만 만족해야 되나 싶음 ㅜㅜ

	// Read an employee by employeeId with specific columns
	public EmployeeDTO getEmployeeByEmployeeIdWithColumns(int employeeId, String... columns) {
		EmployeeDTO employee = new EmployeeDTO();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT ");

		if (columns.length > 0) {
			for (int i = 0; i < columns.length; i++) {
				queryBuilder.append(columns[i]);
				if (i != columns.length - 1) {
					queryBuilder.append(", ");
				}
			}
		} else {
			queryBuilder.append("*"); // If no specific columns are provided, select all columns
		}

		queryBuilder.append(" FROM employees WHERE employeeId = ?");

		String SQL = queryBuilder.toString();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, employeeId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					fillEmployeeDTOFromResultSet(employee, rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	// Fill an EmployeeDTO from a ResultSet
	private void fillEmployeeDTOFromResultSet(EmployeeDTO employee, ResultSet rs) throws SQLException {
		employee.setEmployeeId(rs.getInt("employeeId"));
		employee.setEmployeeName(rs.getString("employeeName"));
		employee.setDepartment(rs.getString("department"));
		employee.setPosition(rs.getString("position"));
		employee.setPassword(rs.getString("password"));
		employee.setAdmin(rs.getBoolean("admin"));
	}
}
