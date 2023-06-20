package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.CustomerDTO;
import util.DatabaseUtil;

public class CustomerDAO {

	// insert a new customer
	public int insertCustomer(CustomerDTO customer) {
		String SQL = "INSERT INTO customers (c_id, e_id, b_id, c_name, identification, grade, age, gender, phone_number, address, job_code, country, credit) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, customer.getCustomerId());
			pstmt.setInt(2, customer.getEmployeeId());
			pstmt.setInt(3, customer.getBankId());
			pstmt.setString(4, customer.getCustomerName());
			pstmt.setString(5, customer.getIdentification());
			pstmt.setString(6, customer.getGrade());
			pstmt.setInt(7, customer.getAge());
			pstmt.setBoolean(8, customer.isGender());
			pstmt.setString(9, customer.getPhoneNumber());
			pstmt.setString(10, customer.getAddress());
			pstmt.setString(11, customer.getJobCode());
			pstmt.setString(12, customer.getCountry());
			pstmt.setString(13, customer.getCredit());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Read a customer by customerId
	public CustomerDTO getCustomerByCustomerId(int customerId) {
		CustomerDTO customer = new CustomerDTO();
		String SQL = "SELECT * FROM customers WHERE c_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, customerId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					fillCustomerDTOFromResultSet(customer, rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	// Fill a CustomerDTO from a ResultSet
	private void fillCustomerDTOFromResultSet(CustomerDTO customer, ResultSet rs) throws SQLException {
		customer.setCustomerId(rs.getInt("c_id"));
		customer.setEmployeeId(rs.getInt("e_id"));
		customer.setBankId(rs.getInt("b_id"));
		customer.setCustomerName(rs.getString("c_name"));
		customer.setIdentification(rs.getString("identification"));
		customer.setGrade(rs.getString("grade"));
		customer.setAge(rs.getInt("age"));
		customer.setGender(rs.getBoolean("gender"));
		customer.setPhoneNumber(rs.getString("phone_no"));
		customer.setAddress(rs.getString("address"));
		customer.setJobCode(rs.getString("job_code"));
		customer.setCountry(rs.getString("country"));
		customer.setCredit(rs.getString("credit"));
	}

	// Update a customer
	public int updateCustomer(CustomerDTO customer) {
		String SQL = "UPDATE customers SET e_id = ?, b_id = ?, customer_name = ?, grade = ?, age = ?, gender = ?, "
				+ "phone_number = ?, address = ?, job_code = ?, country = ?, disability = ?, risk = ?, credit = ? "
				+ "WHERE c_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, customer.getCustomerId());
			pstmt.setInt(2, customer.getEmployeeId());
			pstmt.setInt(3, customer.getBankId());
			pstmt.setString(4, customer.getCustomerName());
			pstmt.setString(5, customer.getIdentification());
			pstmt.setString(6, customer.getGrade());
			pstmt.setInt(7, customer.getAge());
			pstmt.setBoolean(8, customer.isGender());
			pstmt.setString(9, customer.getPhoneNumber());
			pstmt.setString(10, customer.getAddress());
			pstmt.setString(11, customer.getJobCode());
			pstmt.setString(12, customer.getCountry());
			pstmt.setString(13, customer.getCredit());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete a customer
	public int deleteCustomer(int customerId) {
		String SQL = "DELETE FROM customers WHERE c_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, customerId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Get all customers
	public List<CustomerDTO> getCustomers() {
		String SQL = "SELECT * FROM customers";
		List<CustomerDTO> customers = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					CustomerDTO customer = new CustomerDTO();
					fillCustomerDTOFromResultSet(customer, rs);
					customers.add(customer);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	// Get some customers
	public List<CustomerDTO> getEmployeesByInfo(EmployeeDTO employeeDTO) {
		StringBuilder queryBuilder = new StringBuilder("SELECT e.*, b.b_name FROM employees e ");
		queryBuilder.append("JOIN banks b ON e.b_id = b.b_id ");
		queryBuilder.append("WHERE 1=1 ");

		if (employeeDTO.getEmployeeName() != null) {
			queryBuilder.append("AND e.e_name = ?");
		}
		if (employeeDTO.getDepartment() != null) {
			queryBuilder.append("AND e.department = ?");
		}
		if (employeeDTO.getPosition() != null) {
			queryBuilder.append("AND e.position = ?");
		}
		if(employeeDTO.getBankLocation() != null) {
			queryBuilder.append("AND b.b_name = ?");
		}

		try (Connection conn = DatabaseUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
			int parameterIndex = 1;

			if (employeeDTO.getEmployeeName() != null) {
				pstmt.setString(parameterIndex++, employeeDTO.getEmployeeName());
			}
			if (employeeDTO.getDepartment() != null) {
				pstmt.setString(parameterIndex++, employeeDTO.getDepartment());
			}
			if (employeeDTO.getPosition() != null) {
				pstmt.setString(parameterIndex++, employeeDTO.getPosition());
			}
			if(employeeDTO.getBankLocation() != null) {
				pstmt.setString(parameterIndex++, employeeDTO.getBankLocation());
			}

			List<EmployeeDTO> findEmployees = new ArrayList<>();
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					EmployeeDTO employee = new EmployeeDTO();
					employee.setEmployeeId(rs.getInt("e_id"));
					employee.setEmployeeName(rs.getString("e_name"));
					employee.setPhoneNumber(rs.getString("e_phone_no"));
					employee.setDepartment(rs.getString("department"));
					employee.setPosition(rs.getString("position"));
					employee.setAdmin(rs.getBoolean("admin"));

					// bankName 가져오기
					String bankName = rs.getString("b_name");
					employee.setBankLocation(bankName);

	                findEmployees.add(employee);
	            }
	        }
	        return findEmployees;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}