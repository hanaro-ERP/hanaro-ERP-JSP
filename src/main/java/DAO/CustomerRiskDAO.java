package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.CustomerRiskDTO;
import util.DatabaseUtil;

public class CustomerRiskDAO {

	// insert a new customer risk
	public int insertCustomerRisk(CustomerRiskDTO customerRisk) {
		String SQL = "INSERT INTO customerrisk (c_id, risk_result, job_code, loan_count, recent_transaction, country, age, disability, modified_at) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, customerRisk.getCustomerId());
			pstmt.setInt(2, customerRisk.getRiskResult());
			pstmt.setString(3, customerRisk.getJobCode());
			pstmt.setInt(4, customerRisk.getLoanCount());
			pstmt.setDate(5, customerRisk.getRecentTransaction());
			pstmt.setString(6, customerRisk.getCountry());
			pstmt.setInt(7, customerRisk.getAge());
			pstmt.setBoolean(8, customerRisk.isDisability());
			pstmt.setTimestamp(9, customerRisk.getModifiedAt());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Read a customer risk by customerId
	public CustomerRiskDTO getCustomerRiskByCustomerId(int customerId) {
		CustomerRiskDTO customerRisk = new CustomerRiskDTO();
		String SQL = "SELECT * FROM customerrisk WHERE c_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, customerId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					fillCustomerRiskDTOFromResultSet(customerRisk, rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerRisk;
	}

	// Fill a CustomerRiskDTO from a ResultSet
	private void fillCustomerRiskDTOFromResultSet(CustomerRiskDTO customerRisk, ResultSet rs) throws SQLException {
		customerRisk.setCustomerId(rs.getInt("c_id"));
		customerRisk.setRiskResult(rs.getInt("risk_result"));
		customerRisk.setJobCode(rs.getString("job_code"));
		customerRisk.setLoanCount(rs.getInt("loan_count"));
		customerRisk.setRecentTransaction(rs.getDate("recent_transaction"));
		customerRisk.setCountry(rs.getString("country"));
		customerRisk.setAge(rs.getInt("age"));
		customerRisk.setDisability(rs.getBoolean("disability"));
		customerRisk.setModifiedAt(rs.getTimestamp("modified_at"));
	}

	// Update a customer risk
	public int updateCustomerRisk(CustomerRiskDTO customerRisk) {
		String SQL = "UPDATE customerrisk SET risk_result = ?, job_code = ?, loan_count = ?, recent_transaction = ?, "
				+ "country = ?, age = ?, disability = ?, modified_at = ? WHERE c_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, customerRisk.getRiskResult());
			pstmt.setString(2, customerRisk.getJobCode());
			pstmt.setInt(3, customerRisk.getLoanCount());
			pstmt.setDate(4, customerRisk.getRecentTransaction());
			pstmt.setString(5, customerRisk.getCountry());
			pstmt.setInt(6, customerRisk.getAge());
			pstmt.setBoolean(7, customerRisk.isDisability());
			pstmt.setTimestamp(8, customerRisk.getModifiedAt());
			pstmt.setInt(9, customerRisk.getCustomerId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete a customer risk
	public int deleteCustomerRisk(int customerId) {
		String SQL = "DELETE FROM customerrisk WHERE c_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, customerId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Get all customer risks
	public List<CustomerRiskDTO> getCustomerRisks() {
		String SQL = "SELECT * FROM customerrisk";
		List<CustomerRiskDTO> customerRisks = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					CustomerRiskDTO customerRisk = new CustomerRiskDTO();
					fillCustomerRiskDTOFromResultSet(customerRisk, rs);
					customerRisks.add(customerRisk);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerRisks;
	}
}