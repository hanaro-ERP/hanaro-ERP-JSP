package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.CustomerDTO;
import DTO.CustomerSearchDTO;
import DTO.LoanProductDTO;
import DTO.LoanSearchDTO;
import util.DatabaseUtil;
import util.LoanUtil;

public class LoanProductDAO {

	DatabaseUtil databaseUtil = new DatabaseUtil();
	
	// insert a new loan
	public int insertLoan(LoanProductDTO loan) {
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
	public LoanProductDTO getLoanByLoanId(int loanId) {
		LoanProductDTO loan = new LoanProductDTO();
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
	private void fillLoanDTOFromResultSet(LoanProductDTO loan, ResultSet rs) throws SQLException {
		loan.setLoanId(rs.getInt("l_id"));
		loan.setDuration(rs.getInt("duration"));
		loan.setAmount(rs.getLong("amount"));
		loan.setInterestRate(rs.getFloat("interest_rate"));
	}

	// Update a loan
	public int updateLoan(LoanProductDTO loan) {
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
	public List<LoanProductDTO> getLoans() {
		String SQL = "SELECT * FROM loans";
		List<LoanProductDTO> loans = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					LoanProductDTO loan = new LoanProductDTO();
					fillLoanDTOFromResultSet(loan, rs);
					loans.add(loan);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loans;
	}
	
	//	Get some loans
	public List<LoanProductDTO> getLoansByDTO(LoanSearchDTO loanSearchDTO) {
		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM loans ");
		queryBuilder.append("WHERE 1=1");
		
		if (loanSearchDTO.getType() != null) {
			queryBuilder.append(" AND loan_type = ?");
		}
		if (loanSearchDTO.getName() != null) {
			queryBuilder.append(" AND loan_name LIKE ?");
		}		
		if (loanSearchDTO.getJobs() != null) {
			queryBuilder.append(databaseUtil.getListQuery("loan_job", loanSearchDTO.getJobs()));
		}
		if (loanSearchDTO.getCollaterals() != null) {
			queryBuilder.append(databaseUtil.getListQuery("collateral", loanSearchDTO.getCollaterals()));
		}
		if (loanSearchDTO.getPeriods() != null) {
			queryBuilder.append(databaseUtil.getListRangeQuery("duration", loanSearchDTO.getPeriods()));
		}
		if (loanSearchDTO.getIncomes() != null) {
			queryBuilder.append(databaseUtil.getListRangeQuery("income", loanSearchDTO.getIncomes()));
		}
		if (loanSearchDTO.getLimits() != null) {
			queryBuilder.append(databaseUtil.getListRangeQuery("amount", loanSearchDTO.getLimits()));
		}
		
		try (Connection conn = DatabaseUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
			int parameterIndex = 1;
			
			if (loanSearchDTO.getType() != null) {
				pstmt.setString(parameterIndex++, loanSearchDTO.getType());
			}
			if (loanSearchDTO.getName() != null) {
				pstmt.setString(parameterIndex++, "%" + loanSearchDTO.getName() + "%");
			}
			
			System.out.println(pstmt.toString());
			
			List<LoanProductDTO> findLoanProducts = new ArrayList<>();
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					LoanProductDTO loanProduct = new LoanProductDTO();
					loanProduct.setLoanId(rs.getInt("l_id"));
					loanProduct.setLoanType(rs.getString("loan_type"));
					loanProduct.setLoanName(rs.getString("loan_name"));
					loanProduct.setMinDuration(rs.getInt("min_duration"));
					loanProduct.setMaxDuration(rs.getInt("max_duration"));
					loanProduct.setMinAmount(rs.getLong("min_amount"));
					loanProduct.setMaxAmount(rs.getLong("max_amount"));
					loanProduct.setMinRate(rs.getFloat("min_interest_rate"));
					loanProduct.setMaxRate(rs.getFloat("max_interest_rate"));
					loanProduct.setJob(rs.getString("loan_job"));
					loanProduct.setCollateral(rs.getString("collateral"));
					loanProduct.setIncome(rs.getLong("income"));

					findLoanProducts.add(loanProduct);
				}
			}
			return findLoanProducts;
			} catch (Exception e) {
				e.printStackTrace();
		}
	    return null;
	}
}
