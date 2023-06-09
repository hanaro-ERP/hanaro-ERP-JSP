package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DTO.LoanContractDTO;
import DTO.LoanRepaymentDTO;
import util.DatabaseUtil;
import util.LoanUtil;

public class LoanRepaymentDAO {

	// insert a new loan repayment
	public int insertLoanRepayment(LoanRepaymentDTO loanRepayment) {
		String SQL = "INSERT INTO loanrepayments (lr_id, lc_id, a_id, trade_datetime, trade_amount) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanRepayment.getLoanRepaymentId());
			pstmt.setInt(2, loanRepayment.getLoanContractId());
			pstmt.setInt(3, loanRepayment.getAccountId());
			pstmt.setTimestamp(4, loanRepayment.getTradeDatetime());
			pstmt.setBoolean(5, loanRepayment.isAgent());
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
		loanRepayment.setBalance(rs.getLong("balance"));
		loanRepayment.setAccountNumber(rs.getString("account_number"));
	}

	// Update a loan repayment
	public int updateLoanRepayment(LoanRepaymentDTO loanRepayment) {
		String SQL = "UPDATE loanrepayments SET lc_id = ?, a_id = ?, trade_datetime = ?, trade_amount = ? WHERE lr_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanRepayment.getLoanContractId());
			pstmt.setInt(2, loanRepayment.getAccountId());
			pstmt.setTimestamp(3, loanRepayment.getTradeDatetime());
			pstmt.setLong(4, loanRepayment.getTradeAmount());
			pstmt.setInt(5, loanRepayment.getLoanRepaymentId());
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
	
	public int getRepaymentCountByContractId(int contractId) {
		int cnt = 0;

		String query = "SELECT count(*) AS cnt FROM loanRepayments where lr_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, contractId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					cnt = rs.getInt("cnt");
				}
			}
			System.out.println(pstmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public List<LoanRepaymentDTO> getLoanRepaymentByDTO(LoanContractDTO loanContractDTO, int page) {	
		StringBuilder queryBuilder = new StringBuilder("SELECT lr.*, a.account_number, lc.balance"
				+ " FROM loanRepayments lr");

		queryBuilder.append(" JOIN accounts a ON lr.a_id = a.a_id");
		queryBuilder.append(" JOIN loanContracts lc ON lr.lc_id = lc.lc_id");
		queryBuilder.append(" WHERE 1=1");

		if (loanContractDTO.getLoanContractId() != 0) {
			queryBuilder.append(" AND lr.lc_id = ");
			queryBuilder.append(loanContractDTO.getLoanContractId());
		}		
		queryBuilder.append(" ORDER BY lr.trade_datetime DESC");
		queryBuilder.append(" LIMIT 10 OFFSET ");
		queryBuilder.append((page-1)*10);
		
		try (Connection conn = DatabaseUtil.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
			
			LoanUtil loanUtil = new LoanUtil();
			List<LoanRepaymentDTO> loanRepaymentDTOList = new ArrayList<>();	
			
			try (ResultSet rs = pstmt.executeQuery()) {				
				while (rs.next()) {
					LoanRepaymentDTO loanRepaymentDTO = new LoanRepaymentDTO();
					fillLoanRepaymentDTOFromResultSet(loanRepaymentDTO, rs);
					loanRepaymentDTO.setCustomerName(loanContractDTO.getCustomerName());
					loanRepaymentDTO.setEmployeeName(loanContractDTO.getEmployeeName());

					String tradeAmountString = loanUtil.convertMoneyUnit(loanRepaymentDTO.getTradeAmount());
					loanRepaymentDTO.setTradeAmountString(tradeAmountString);
					String balanceString = loanUtil.convertMoneyUnit(loanRepaymentDTO.getBalance());
					loanRepaymentDTO.setBalanceString(balanceString);

					loanRepaymentDTOList.add(loanRepaymentDTO);
				}
				return loanRepaymentDTOList;
			}
			catch (Exception e) {
				e.printStackTrace();
			} 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}