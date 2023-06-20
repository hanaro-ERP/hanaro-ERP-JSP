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

	// Create a new customer
	public int createCustomer(CustomerDTO customer) {
		String SQL = "INSERT INTO customers (c_id, e_id, b_id, customer_name, grade, age, gender, phone_number, address, job_code, country, disability, risk, credit) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, customer.getCustomerId());
			pstmt.setInt(2, customer.getEmployeeId());
			pstmt.setInt(3, customer.getBankId());
			pstmt.setString(4, customer.getCustomerName());
			pstmt.setString(5, customer.getGrade());
			pstmt.setInt(6, customer.getAge());
			pstmt.setBoolean(7, customer.isGender());
			pstmt.setString(8, customer.getPhoneNumber());
			pstmt.setString(9, customer.getAddress());
			pstmt.setString(10, customer.getJobCode());
			pstmt.setString(11, customer.getCountry());
			pstmt.setBoolean(12, customer.isDisability());
			pstmt.setFloat(13, customer.getRisk());
			pstmt.setInt(14, customer.getCredit());
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
		customer.setCustomerName(rs.getString("customer_name"));
		customer.setGrade(rs.getString("grade"));
		customer.setAge(rs.getInt("age"));
		customer.setGender(rs.getBoolean("gender"));
		customer.setPhoneNumber(rs.getString("phone_number"));
		customer.setAddress(rs.getString("address"));
		customer.setJobCode(rs.getString("job_code"));
		customer.setCountry(rs.getString("country"));
		customer.setDisability(rs.getBoolean("disability"));
		customer.setRisk(rs.getFloat("risk"));
		customer.setCredit(rs.getInt("credit"));
	}

	// Update a customer
	public int updateCustomer(CustomerDTO customer) {
		String SQL = "UPDATE customers SET e_id = ?, b_id = ?, customer_name = ?, grade = ?, age = ?, gender = ?, "
				+ "phone_number = ?, address = ?, job_code = ?, country = ?, disability = ?, risk = ?, credit = ? "
				+ "WHERE c_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, customer.getEmployeeId());
			pstmt.setInt(2, customer.getBankId());
			pstmt.setString(3, customer.getCustomerName());
			pstmt.setString(4, customer.getGrade());
			pstmt.setInt(5, customer.getAge());
			pstmt.setBoolean(6, customer.isGender());
			pstmt.setString(7, customer.getPhoneNumber());
			pstmt.setString(8, customer.getAddress());
			pstmt.setString(9, customer.getJobCode());
			pstmt.setString(10, customer.getCountry());
			pstmt.setBoolean(11, customer.isDisability());
			pstmt.setFloat(12, customer.getRisk());
			pstmt.setInt(13, customer.getCredit());
			pstmt.setInt(14, customer.getCustomerId());
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
}