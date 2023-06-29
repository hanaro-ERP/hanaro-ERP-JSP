package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import DTO.CustomerDTO;
import DTO.LoanProductDTO;
import util.DatabaseUtil;

public class LoanSubscriptionDAO {

	public int registerSubscription(CustomerDTO customerDTO, LoanProductDTO loanProductDTO) {
		String SQL = "INSERT INTO loans (loan_name, loan_type, loan_job, collateral, income, "
				+ "min_duration, max_duration, min_amount, max_amount, min_interest_rate, max_interest_rate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, loanProductDTO.getLoanName());
			pstmt.setString(2, loanProductDTO.getLoanType());
			pstmt.setString(3, loanProductDTO.getJob());
			pstmt.setString(4, loanProductDTO.getCollateral());
			pstmt.setLong(5, loanProductDTO.getIncome());
			pstmt.setInt(6, loanProductDTO.getMinDuration());
			pstmt.setInt(7, loanProductDTO.getMaxDuration());
			pstmt.setLong(8, loanProductDTO.getMinAmount());
			pstmt.setLong(9, loanProductDTO.getMaxAmount());
			pstmt.setFloat(10, loanProductDTO.getMinRate());
			pstmt.setFloat(11, loanProductDTO.getMaxRate());
			return pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}	
}
