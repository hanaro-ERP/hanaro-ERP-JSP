package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.CustomerDTO;
import DTO.CustomerSearchDTO;
import DTO.EmployeeDTO;
import DTO.LoanProductDTO;
import DTO.LoanSearchDTO;
import util.DatabaseUtil;
import util.LoanUtil;

public class LoanProductDAO {

	DatabaseUtil databaseUtil = new DatabaseUtil();
	
	// insert a new loan
	public int insertLoanProduct(LoanProductDTO loanProductDTO) {
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
			System.out.println(pstmt.toString());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	public int updateLoanProduct(LoanProductDTO loanProductDTO, int l_id) {
	    String SQL = "UPDATE loans SET loan_name = ?, loan_type = ?, loan_job = ?, collateral = ?, income = ?, "
	                + "min_duration = ?, max_duration = ?, min_amount = ?, max_amount = ?, min_interest_rate = ?, max_interest_rate = ? "
	                + "WHERE l_id = ?";
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
	        pstmt.setInt(12, l_id);
	        System.out.println(pstmt.toString());
	        return pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return -1; // Database operation failed
	}
	
	public int getLoanIdByLoanName(String loanName) {
		int lId = -1;
	    String SQL = "SELECT l_id FROM loans WHERE loan_name = ?";
	    try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
	        pstmt.setString(1, loanName);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	            	lId = rs.getInt("l_id");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return lId;
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
	private void fillLoanDTOFromResultSet(LoanProductDTO loanProduct, ResultSet rs) throws SQLException {
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
	
	public int getLoanCount(LoanSearchDTO loanSearchDTO) {
		int cnt = 0;
		StringBuilder queryBuilder = new StringBuilder("SELECT count(*) AS cnt FROM loans ");
		queryBuilder.append("WHERE 1=1");
		
		if (loanSearchDTO.getType() != null) {
			queryBuilder.append(" AND loan_type = ?");
			if (loanSearchDTO.getType().equals("신용대출") && loanSearchDTO.getJobs() != null) {
				queryBuilder.append(databaseUtil.getListQuery("loan_job", loanSearchDTO.getJobs()));
			} else if (loanSearchDTO.getType().equals("담보대출") && loanSearchDTO.getCollaterals() != null) {
				queryBuilder.append(databaseUtil.getListQuery("collateral", loanSearchDTO.getCollaterals()));
			}
		}
		if (loanSearchDTO.getName() != null) {
			queryBuilder.append(" AND loan_name LIKE ?");
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
	
	//	Get some loans
	public List<LoanProductDTO> getLoansByDTO(LoanSearchDTO loanSearchDTO, int page) {
		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM loans ");
		queryBuilder.append("WHERE 1=1");
		
		if (loanSearchDTO.getType() != null) {
			queryBuilder.append(" AND loan_type = ?");
			if (loanSearchDTO.getType().equals("신용대출") && loanSearchDTO.getJobs() != null) {
				queryBuilder.append(databaseUtil.getListQuery("loan_job", loanSearchDTO.getJobs()));
			} else if (loanSearchDTO.getType().equals("담보대출") && loanSearchDTO.getCollaterals() != null) {
				queryBuilder.append(databaseUtil.getListQuery("collateral", loanSearchDTO.getCollaterals()));
			}
		}
		if (loanSearchDTO.getName() != null) {
			queryBuilder.append(" AND loan_name LIKE ?");
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
		queryBuilder.append(" LIMIT 20 OFFSET ?");

		try (Connection conn = DatabaseUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
			int parameterIndex = 1;
			
			if (loanSearchDTO.getType() != null) {
				pstmt.setString(parameterIndex++, loanSearchDTO.getType());
			}
			if (loanSearchDTO.getName() != null) {
				pstmt.setString(parameterIndex++, "%" + loanSearchDTO.getName() + "%");
			}
			pstmt.setInt(parameterIndex++, (page-1)*20);

			System.out.println(pstmt.toString());
			
			List<LoanProductDTO> findLoanProducts = new ArrayList<>();
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					LoanProductDTO loanProduct = new LoanProductDTO();
					fillLoanDTOFromResultSet(loanProduct, rs);

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
