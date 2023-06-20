package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.LoanDTO;
import util.DatabaseUtil;

public class LoanDAO {

	// insert a new loan
	public int insertLoan(LoanDTO loan) {
		String SQL = "INSERT INTO loans (l_id, duration, amount, interest_rate) VALUES (?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loan.getLoanId());
			pstmt.setInt(2, loan.getDuration());
			pstmt.setLong(3, loan.getAmount());
			pstmt.setFloat(4, loan.getInterestRate());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Read a loan by loanId
	public LoanDTO getLoanByLoanId(int loanId) {
		LoanDTO loan = new LoanDTO();
		String SQL = "SELECT * FROM loans WHERE l_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					fillLoanDTOFromResultSet(loan, rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loan;
	}

	// Fill a LoanDTO from a ResultSet
	private void fillLoanDTOFromResultSet(LoanDTO loan, ResultSet rs) throws SQLException {
		loan.setLoanId(rs.getInt("l_id"));
		loan.setDuration(rs.getInt("duration"));
		loan.setAmount(rs.getLong("amount"));
		loan.setInterestRate(rs.getFloat("interest_rate"));
	}

	// Update a loan
	public int updateLoan(LoanDTO loan) {
		String SQL = "UPDATE loans SET duration = ?, amount = ?, interest_rate = ? WHERE l_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loan.getDuration());
			pstmt.setLong(2, loan.getAmount());
			pstmt.setFloat(3, loan.getInterestRate());
			pstmt.setInt(4, loan.getLoanId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete a loan
	public int deleteLoan(int loanId) {
		String SQL = "DELETE FROM loans WHERE l_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Get all loans
	public List<LoanDTO> getLoans() {
		String SQL = "SELECT * FROM loans";
		List<LoanDTO> loans = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					LoanDTO loan = new LoanDTO();
					fillLoanDTOFromResultSet(loan, rs);
					loans.add(loan);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loans;
	}
}
