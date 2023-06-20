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

	// Create a new employee
	public int createEmployee(EmployeeDTO employee) {
		String SQL = "INSERT INTO employees (e_id, b_id, e_name, department, position, password, admin) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, employee.getEmployeeId());
			pstmt.setInt(2, employee.getBankId());
			pstmt.setString(3, employee.getEmployeeName());
			pstmt.setString(4, employee.getDepartment());
			pstmt.setString(5, employee.getPosition());
			pstmt.setString(6, employee.getPassword());
			pstmt.setBoolean(7, employee.isAdmin());
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
		employee.setAdmin(rs.getBoolean("is_admin"));
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
					loanContract.setMuturityDate(rs.getTimestamp("muturiy_date"));
					loanContract.setPaymentMethod(rs.getString("payment_method"));
					loanContract.setBalance(rs.getLong("balance"));
					loanContract.setPaymentDate(rs.getDate("payment_date"));
					loanContract.setDelinquencyStart(rs.getDate("delinquency_start"));
					loanContract.setDelinquentDay(rs.getInt("delinquent_day"));
					loanContract.setDelinquentAmount(rs.getLong("delinquent_amount"));
					loanContract.setGuarantorId(rs.getInt("guarantor"));
					loanContract.setHasCollateral(rs.getBoolean("has_collateral"));
					loanContract.setCollateralValue(rs.getLong("collateral_value"));
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

}