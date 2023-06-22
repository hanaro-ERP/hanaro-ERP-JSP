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
	
	public String getGradeQuery(String type, String[] selectedGrades) {
		StringBuilder query = new StringBuilder(" AND (");
		
		boolean isFirst = true;
		for (String grade : selectedGrades) {
			if (isFirst) {
				isFirst = false;
			} else {
				query.append(" OR "); // 첫 번째 조건이 아니면 OR 연산자 추가
            }
			if (grade.equals("")) {
				query.append("1=1");
				break;
			} else {
				String temp = String.format("c.%s = \'%s\'", type, grade);
				query.append(temp);
			}
        }
		query.append(")");
		return query.toString();
	}
	
	public String getAgeQuery(String[] selectedAges) {
		StringBuilder query = new StringBuilder(" AND (");
		
		boolean isFirst = true;
		for (String age : selectedAges) {
			if (isFirst) {
				isFirst = false;
			} else {
				query.append(" OR "); // 첫 번째 조건이 아니면 OR 연산자 추가
            }
			if (age.equals("") && selectedAges.length < 2) {
				query.append("1=1");
				break;
			} else if (age.equals("10대")) {
				query.append("(c.age < 20)");
			} else if (age.equals("20대")) {
				query.append("(c.age >= 20 AND c.age < 30)");
			} else if (age.equals("30대")) {
				query.append("(c.age >= 30 AND c.age < 40)");
			} else if (age.equals("40대")) {
				query.append("(c.age >= 40 AND c.age < 50)");
			} else if (age.equals("50대")) {
				query.append("(c.age >= 50 AND c.age < 60)");
			} else if (age.equals("60대")) {
				query.append("(c.age >= 60 AND c.age < 70)");
			} else if (age.equals("70대")) {
				query.append("(c.age >= 70 AND c.age < 80)");
			} else if (age.equals("80대 이상")) {
				query.append("(c.age >= 80)");
			} else {
				System.out.println(selectedAges[0]);
				System.out.println(selectedAges[1]);
				String temp1 = selectedAges[0];
				String temp2 = selectedAges[1];

				if (temp1 == "") {
					temp1 = "0";
				}
				if (temp2 == "") {
					temp2 = "999";
				}

				String temp = String.format("(c.age >= %s and c.age <= %s)", temp1, temp2);
				query.append(temp);
				break;
			}
        }
		query.append(")");
		return query.toString();
	}
	
//	Get some customers
	public List<CustomerDTO> getCustomersByDTO(CustomerSearchDTO customerSearchDTO) {
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
			queryBuilder.append(getGradeQuery("grade", customerSearchDTO.getCustomerGrades()));
		}
		if (customerSearchDTO.getCustomerCredits() != null) {
			queryBuilder.append(getGradeQuery("credit", customerSearchDTO.getCustomerCredits()));
		}
		if (customerSearchDTO.getCustomerAges() != null) {
			queryBuilder.append(getAgeQuery(customerSearchDTO.getCustomerAges()));
		}
		if (customerSearchDTO.getCountry() != null) {
			queryBuilder.append(" AND c.country = ?");
		}
		if (customerSearchDTO.getCity() != null) {
			queryBuilder.append(" AND c.address LIKE ?");
		}
		
		System.out.println(queryBuilder);
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
			System.out.println(pstmt.toString());
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