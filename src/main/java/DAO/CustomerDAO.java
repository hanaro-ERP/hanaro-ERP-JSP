package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.CustomerDTO;
import DTO.CustomerSearchDTO;
import util.CustomerUtil;
import util.DatabaseUtil;

public class CustomerDAO {

	CustomerUtil customerUtil = new CustomerUtil();
	DatabaseUtil databaseUtil = new DatabaseUtil();

	public int getCustomerCount(CustomerSearchDTO customerSearchDTO) {
		int cnt = 0;
		StringBuilder queryBuilder = new StringBuilder("SELECT count(*) AS cnt FROM customers c ");
		queryBuilder.append("JOIN employees e ON c.e_id = e.e_id ");
		queryBuilder.append("JOIN banks b ON c.b_id = b.b_id ");
		queryBuilder.append("WHERE 1=1");

		if (customerSearchDTO.getCustomerName() != null) {
			queryBuilder.append(" AND c.c_name LIKE ?");
		}
		if (customerSearchDTO.getJobCode() != null) {
			queryBuilder.append(" AND c.job_code = ?");
		}
		if (customerSearchDTO.getEmployeeName() != null) {
			queryBuilder.append(" AND e.e_name LIKE ?");
		}
		if (customerSearchDTO.getBankName() != null) {
			queryBuilder.append(" AND b.b_name = ?");
		}
		if (customerSearchDTO.getIdentification1() != null) {
			queryBuilder.append(" AND c.identification LIKE ?");
		}
		if (customerSearchDTO.getIdentification2() != null) {
			queryBuilder.append(" AND c.identification LIKE ?");
		}
		if (customerSearchDTO.getPhoneNumber1() != null) {
			queryBuilder.append(" AND c.phone_no LIKE ?");
		}
		if (customerSearchDTO.getPhoneNumber2() != null) {
			queryBuilder.append(" AND c.phone_no LIKE ?");
		}
		if (customerSearchDTO.getPhoneNumber3() != null) {
			queryBuilder.append(" AND c.phone_no LIKE ?");
		}
		if (customerSearchDTO.getGender() != null) {
			queryBuilder.append(" AND c.gender = ?");
		}
		if (customerSearchDTO.getCustomerGrades() != null) {
			queryBuilder.append(databaseUtil.getListQuery("c.grade", customerSearchDTO.getCustomerGrades()));
		}
		if (customerSearchDTO.getCustomerCredits() != null) {
			queryBuilder.append(databaseUtil.getListQuery("c.credit", customerSearchDTO.getCustomerCredits()));
		}
		if (customerSearchDTO.getCustomerAges() != null) {
			queryBuilder.append(databaseUtil.getAgeQuery(customerSearchDTO.getCustomerAges()));
		}
		if (customerSearchDTO.getCountry() != null) {
			queryBuilder.append(" AND c.country = ?");
		}
		if (customerSearchDTO.getCity() != null) {
			queryBuilder.append(" AND c.address LIKE ?");
		}
		
		try (Connection conn = DatabaseUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
			int parameterIndex = 1;

			if (customerSearchDTO.getCustomerName() != null) {
				pstmt.setString(parameterIndex++, "%" + customerSearchDTO.getCustomerName() + "%");
			}
			if (customerSearchDTO.getJobCode() != null) {
				pstmt.setString(parameterIndex++, customerSearchDTO.getJobCode());
			}
			if (customerSearchDTO.getEmployeeName() != null) {
				pstmt.setString(parameterIndex++, "%" + customerSearchDTO.getEmployeeName() + "%");
			}
			if (customerSearchDTO.getBankName() != null) {
				pstmt.setString(parameterIndex++, customerSearchDTO.getBankName());
			}		
			if (customerSearchDTO.getIdentification1() != null) {
				pstmt.setString(parameterIndex++, "%" + customerSearchDTO.getIdentification1() + "%-%");
			}
			if (customerSearchDTO.getIdentification2() != null) {
				pstmt.setString(parameterIndex++, "%-%" + customerSearchDTO.getIdentification2() + "%");
			}
			if (customerSearchDTO.getPhoneNumber1() != null) {
				pstmt.setString(parameterIndex++, "%" + customerSearchDTO.getPhoneNumber1() + "%-%");
			}
			if (customerSearchDTO.getPhoneNumber2() != null) {
				pstmt.setString(parameterIndex++, "%-%" + customerSearchDTO.getPhoneNumber2() + "%-%");
			}
			if (customerSearchDTO.getPhoneNumber3() != null) {
				pstmt.setString(parameterIndex++, "%-%" + customerSearchDTO.getPhoneNumber3() + "%");
			}
			if (customerSearchDTO.getGender() != null) {
				pstmt.setBoolean(parameterIndex++, customerSearchDTO.getGender());
			}
			if (customerSearchDTO.getCountry() != null) {
				pstmt.setString(parameterIndex++, customerSearchDTO.getCountry());
			}
			if (customerSearchDTO.getCity() != null) {
				String temp = customerSearchDTO.getCity() + "%";
				if (customerSearchDTO.getDistrict() != null) 
					temp = customerSearchDTO.getCity() + " " + customerSearchDTO.getDistrict();
				pstmt.setString(parameterIndex++, temp);
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					cnt = rs.getInt("cnt");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	
	// insert a new customer
	public int insertCustomer(CustomerDTO customer, int e_id, int b_id) {
		String SQL = "INSERT INTO customers (e_id, b_id, c_name, identification, grade, age, gender, phone_no, address, job_code, country, credit, risk, guarantor) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			// pstmt.setInt(1, customer.getCustomerId());
			pstmt.setInt(1, e_id);
			pstmt.setInt(2, b_id);
			pstmt.setString(3, customer.getCustomerName());
			pstmt.setString(4, customer.getIdentification());
			pstmt.setString(5, customer.getGrade());
			pstmt.setInt(6, customer.getAge());
			pstmt.setBoolean(7, customer.isGender());
			pstmt.setString(8, customer.getPhoneNumber());
			pstmt.setString(9, customer.getAddress());
			pstmt.setString(10, customer.getJobCode());
			pstmt.setString(11, customer.getCountry());
			pstmt.setString(12, customer.getCredit());
			pstmt.setInt(13, customer.getRisk());
			pstmt.setString(14, customer.getSuretyName());

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	public int getCustomerIdByCustomerName(String customerName) {
		int cId = -1;
		String SQL = "SELECT c_id FROM customers WHERE c_name = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, customerName);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					cId = rs.getInt("c_id");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cId;
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
		customer.setSuretyName(rs.getString("guarantor"));
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
	
	public List<CustomerDTO> getCustomersByName(String name) {
		String SQL = "SELECT * FROM customers WHERE c_name LIKE ?";
		List<CustomerDTO> customers = new ArrayList<>();
		
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, name + "%");
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					CustomerDTO customer = new CustomerDTO();
					fillCustomerDTOFromResultSet(customer, rs);
					customer.setStrGender(customerUtil.convertBinaryToGender(rs.getBoolean("gender")));
					
					customers.add(customer);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}

//	Get some customers
	public List<CustomerDTO> getCustomersByDTO(CustomerSearchDTO customerSearchDTO, int page) {
		StringBuilder queryBuilder = new StringBuilder("SELECT c.*, e.e_name, b.b_name FROM customers c ");
		queryBuilder.append("JOIN employees e ON c.e_id = e.e_id ");
		queryBuilder.append("JOIN banks b ON c.b_id = b.b_id ");
		queryBuilder.append("WHERE 1=1");

		if (customerSearchDTO.getCustomerName() != null) {
			queryBuilder.append(" AND c.c_name LIKE ?");
		}
		if (customerSearchDTO.getJobCode() != null) {
			queryBuilder.append(" AND c.job_code = ?");
		}
		if (customerSearchDTO.getEmployeeName() != null) {
			queryBuilder.append(" AND e.e_name LIKE ?");
		}
		if (customerSearchDTO.getBankName() != null) {
			queryBuilder.append(" AND b.b_name = ?");
		}
		if (customerSearchDTO.getIdentification1() != null) {
			queryBuilder.append(" AND c.identification LIKE ?");
		}
		if (customerSearchDTO.getIdentification2() != null) {
			queryBuilder.append(" AND c.identification LIKE ?");
		}
		if (customerSearchDTO.getPhoneNumber1() != null) {
			queryBuilder.append(" AND c.phone_no LIKE ?");
		}
		if (customerSearchDTO.getPhoneNumber2() != null) {
			queryBuilder.append(" AND c.phone_no LIKE ?");
		}
		if (customerSearchDTO.getPhoneNumber3() != null) {
			queryBuilder.append(" AND c.phone_no LIKE ?");
		}
		if (customerSearchDTO.getGender() != null) {
			queryBuilder.append(" AND c.gender = ?");
		}
		if (customerSearchDTO.getCustomerGrades() != null) {
			queryBuilder.append(databaseUtil.getListQuery("c.grade", customerSearchDTO.getCustomerGrades()));
		}
		if (customerSearchDTO.getCustomerCredits() != null) {
			queryBuilder.append(databaseUtil.getListQuery("c.credit", customerSearchDTO.getCustomerCredits()));
		}
		if (customerSearchDTO.getCustomerAges() != null) {
			queryBuilder.append(databaseUtil.getAgeQuery(customerSearchDTO.getCustomerAges()));
		}
		if (customerSearchDTO.getCountry() != null) {
			queryBuilder.append(" AND c.country = ?");
		}
		if (customerSearchDTO.getCity() != null) {
			queryBuilder.append(" AND c.address LIKE ?");
		}
		queryBuilder.append(" LIMIT 20 OFFSET ?");
		
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
			int parameterIndex = 1;

			if (customerSearchDTO.getCustomerName() != null) {
				pstmt.setString(parameterIndex++, "%" + customerSearchDTO.getCustomerName() + "%");
			}
			if (customerSearchDTO.getJobCode() != null) {
				pstmt.setString(parameterIndex++, customerSearchDTO.getJobCode());
			}
			if (customerSearchDTO.getEmployeeName() != null) {
				pstmt.setString(parameterIndex++, "%" + customerSearchDTO.getEmployeeName() + "%");
			}
			if (customerSearchDTO.getBankName() != null) {
				pstmt.setString(parameterIndex++, customerSearchDTO.getBankName());
			}
			if (customerSearchDTO.getIdentification1() != null) {
				pstmt.setString(parameterIndex++, "%" + customerSearchDTO.getIdentification1() + "%-%");
			}
			if (customerSearchDTO.getIdentification2() != null) {
				pstmt.setString(parameterIndex++, "%-%" + customerSearchDTO.getIdentification2() + "%");
			}
			if (customerSearchDTO.getPhoneNumber1() != null) {
				pstmt.setString(parameterIndex++, "%" + customerSearchDTO.getPhoneNumber1() + "%-%");
			}
			if (customerSearchDTO.getPhoneNumber2() != null) {
				pstmt.setString(parameterIndex++, "%-%" + customerSearchDTO.getPhoneNumber2() + "%-%");
			}
			if (customerSearchDTO.getPhoneNumber3() != null) {
				pstmt.setString(parameterIndex++, "%-%" + customerSearchDTO.getPhoneNumber3() + "%");
			}
			if (customerSearchDTO.getGender() != null) {
				pstmt.setBoolean(parameterIndex++, customerSearchDTO.getGender());
			}
			if (customerSearchDTO.getCountry() != null) {
				pstmt.setString(parameterIndex++, customerSearchDTO.getCountry());
			}
			if (customerSearchDTO.getCity() != null) {
				String temp = customerSearchDTO.getCity() + "%";
				if (customerSearchDTO.getDistrict() != null)
					temp = customerSearchDTO.getCity() + " " + customerSearchDTO.getDistrict();
				pstmt.setString(parameterIndex++, temp);
			}
			pstmt.setInt(parameterIndex++, (page-1)*20);
			
			List<CustomerDTO> findCustomers = new ArrayList<>();
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					CustomerDTO customer = new CustomerDTO();
					customer.setCustomerId(rs.getInt("c_id"));
					customer.setCustomerName(rs.getString("c_name"));
					customer.setAge(rs.getInt("age"));
					customer.setStrGender(customerUtil.convertBinaryToGender(rs.getBoolean("gender")));
					customer.setCredit(rs.getString("credit"));
					customer.setJobCode(rs.getString("job_code"));
					customer.setGrade(rs.getString("grade"));
					customer.setRisk(rs.getInt("risk"));
					
					findCustomers.add(customer);
				}
			}
			return findCustomers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}