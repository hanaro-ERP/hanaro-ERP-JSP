package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.BankDTO;
import DTO.CustomerDTO;
import DTO.EmployeeDTO;
import DTO.LoanContractDTO;
import util.DatabaseUtil;

public class EmployeeDAO {

	// insert a new employee
	public int insertEmployee(EmployeeDTO employee) {
		String SQL = "INSERT INTO employees (e_id, b_id, e_name, department, position, password, e_phone_no, admin, salt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, employee.getEmployeeId());
			pstmt.setInt(2, employee.getBankId());
			pstmt.setString(3, employee.getEmployeeName());
			pstmt.setString(4, employee.getDepartment());
			pstmt.setString(5, employee.getPosition());
			pstmt.setString(6, employee.getPassword());
			pstmt.setString(7, employee.getPhoneNumber());
			pstmt.setBoolean(8, employee.isAdmin());
			pstmt.setString(9, employee.getSalt());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Read an employee by EmployeeId
	public EmployeeDTO getEmployeeByEmployeeId(int employeeId) {
		EmployeeDTO employee = new EmployeeDTO();
		String SQL = "SELECT * FROM employees WHERE e_id = ?";
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
		employee.setEmployeeId(rs.getInt("e_id"));
		employee.setBankId(rs.getInt("b_id"));
		employee.setEmployeeName(rs.getString("e_name"));
		employee.setDepartment(rs.getString("department"));
		employee.setPosition(rs.getString("position"));
		employee.setPassword(rs.getString("password"));
		employee.setPhoneNumber(rs.getString("e_phone_no"));
		employee.setAdmin(rs.getBoolean("admin"));
		employee.setSalt(rs.getString("salt"));
	}

	// Update an employee
	public int updateEmployee(EmployeeDTO employee) {
		String SQL = "UPDATE employees SET b_id = ?, e_name = ?, department = ?, position = ?, password = ?, admin = ? WHERE e_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, employee.getBankId());
			pstmt.setString(2, employee.getEmployeeName());
			pstmt.setString(3, employee.getDepartment());
			pstmt.setString(4, employee.getPosition());
			pstmt.setString(5, employee.getPassword());
			pstmt.setBoolean(6, employee.isAdmin());
			pstmt.setInt(7, employee.getEmployeeId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete an employee
	public int deleteEmployee(int employeeId) {
		String SQL = "DELETE FROM employees WHERE e_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, employeeId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Get all employees
	public List<EmployeeDTO> getEmployees() {
		String SQL = "SELECT * FROM employees";
		List<EmployeeDTO> employees = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					EmployeeDTO employee = new EmployeeDTO();
					fillEmployeeDTOFromResultSet(employee, rs);
					employees.add(employee);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	// Get password by EmployeeId
	public String getPasswordByEmployeeId(int employeeId) {
		String SQL = "SELECT password FROM employees WHERE e_id = ?";
		String password = null;
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, employeeId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					password = rs.getString("password");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

	// Get loan contracts by EmployeeId
	public List<LoanContractDTO> getLoanContractByEmployeeId(int employeeId) {
		String SQL = "SELECT * FROM loancontract WHERE e_id = ?";
		List<LoanContractDTO> loanContracts = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, employeeId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					LoanContractDTO loanContract = new LoanContractDTO();
					loanContract.setLoanContractId(rs.getInt("lc_id"));
					loanContract.setLoanId(rs.getInt("l_id"));
					loanContract.setCustomerId(rs.getInt("c_id"));
					loanContract.setEmployeeId(rs.getInt("e_id"));
					loanContract.setStartDate(rs.getTimestamp("start_date"));
					loanContract.setMuturityDate(rs.getTimestamp("muturiy_date"));
					loanContract.setPaymentMethod(rs.getString("payment_method"));
					loanContract.setBalance(rs.getLong("balance"));
					loanContract.setPaymentDate(rs.getDate("payment_date"));
					loanContract.setDelinquentAmount(rs.getLong("delinquent_amount"));
					loanContract.setGuarantorId(rs.getInt("guarantor"));
					loanContract.setInterestRate(rs.getLong("interest_rate"));
					loanContracts.add(loanContract);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loanContracts;
	}

	// Get customers by EmployeeId
	public List<CustomerDTO> getCustomerByEmployeeId(int employeeId) {
		String SQL = "SELECT * FROM customers WHERE e_id = ?";
		List<CustomerDTO> customers = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, employeeId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					CustomerDTO customer = new CustomerDTO();
					// Fill customer from result set...
					customers.add(customer);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}

	// Get bank by EmployeeId
	public BankDTO getBankByEmployeeId(int employeeId) {
		String SQL = "SELECT * FROM banks WHERE b_id = (SELECT b_id FROM employees WHERE e_id = ?)";
		BankDTO bank = new BankDTO();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, employeeId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					// Fill bank from result set...
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bank;
	}
	
	public List<EmployeeDTO> getEmployeesByDTO(EmployeeDTO employeeDTO) {
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
		
		queryBuilder.append(" ORDER BY e.e_id ASC "); // 정렬 조건

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