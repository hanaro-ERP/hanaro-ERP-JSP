package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.LoanRepaymentDTO;
import util.DatabaseUtil;

public class LoanRepaymentDAO {

	// Create a new loan repayment
	public int createLoanRepayment(LoanRepaymentDTO loanRepayment) {
		String SQL = "INSERT INTO loanrepayments (lr_id, lc_id, a_id, trade_datetime, trade_amount, agent) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanRepayment.getLoanRepaymentId());
			pstmt.setInt(2, loanRepayment.getLoanContractId());
			pstmt.setInt(3, loanRepayment.getAccountId());
			pstmt.setTimestamp(4, loanRepayment.getTradeDatetime());
			pstmt.setLong(5, loanRepayment.getTradeAmount());
			pstmt.setBoolean(6, loanRepayment.isAgent());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Read a loan repayment by loanRepaymentId
	public LoanRepaymentDTO getLoanRepaymentByLoanRepaymentId(int loanRepaymentId) {
		LoanRepaymentDTO loanRepayment = new LoanRepaymentDTO();
		String SQL = "SELECT * FROM loanrepayments WHERE lr_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanRepaymentId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					fillLoanRepaymentDTOFromResultSet(loanRepayment, rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loanRepayment;
	}

	// Fill a LoanRepaymentDTO from a ResultSet
	private void fillLoanRepaymentDTOFromResultSet(LoanRepaymentDTO loanRepayment, ResultSet rs) throws SQLException {
		loanRepayment.setLoanRepaymentId(rs.getInt("lr_id"));
		loanRepayment.setLoanContractId(rs.getInt("lc_id"));
		loanRepayment.setAccountId(rs.getInt("a_id"));
		loanRepayment.setTradeDatetime(rs.getTimestamp("trade_datetime"));
		loanRepayment.setTradeAmount(rs.getLong("trade_amount"));
		loanRepayment.setAgent(rs.getBoolean("agent"));
	}

	// Update a loan repayment
	public int updateLoanRepayment(LoanRepaymentDTO loanRepayment) {
		String SQL = "UPDATE loanrepayments SET lc_id = ?, a_id = ?, trade_datetime = ?, trade_amount = ?, agent = ? WHERE lr_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanRepayment.getLoanContractId());
			pstmt.setInt(2, loanRepayment.getAccountId());
			pstmt.setTimestamp(3, loanRepayment.getTradeDatetime());
			pstmt.setLong(4, loanRepayment.getTradeAmount());
			pstmt.setBoolean(5, loanRepayment.isAgent());
			pstmt.setInt(6, loanRepayment.getLoanRepaymentId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete a loan repayment
	public int deleteLoanRepayment(int loanRepaymentId) {
		String SQL = "DELETE FROM loanrepayments WHERE lr_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanRepaymentId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Get all loan repayments
	public List<LoanRepaymentDTO> getLoanRepayments() {
		String SQL = "SELECT * FROM loanrepayments";
		List<LoanRepaymentDTO> loanRepayments = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					LoanRepaymentDTO loanRepayment = new LoanRepaymentDTO();
					fillLoanRepaymentDTOFromResultSet(loanRepayment, rs);
					loanRepayments.add(loanRepayment);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loanRepayments;
	}
}