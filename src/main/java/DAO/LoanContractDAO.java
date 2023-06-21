package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.LoanContractDTO;
import util.DatabaseUtil;

public class LoanContractDAO {

	// insert a new loan contract
	public int insertLoanContract(LoanContractDTO loanContract) {
		String SQL = "INSERT INTO loancontract (lc_id, l_id, c_id, e_id, start_date, muturity_date, payment_method, balance, payment_date, "
				+ "delinquent_amount, guarantor_id, interest_rate) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanContract.getLoanContractId());
			pstmt.setInt(2, loanContract.getLoanId());
			pstmt.setInt(3, loanContract.getCustomerId());
			pstmt.setInt(4, loanContract.getEmployeeId());
			pstmt.setTimestamp(5, loanContract.getStartDate());
			pstmt.setTimestamp(6, loanContract.getMuturityDate());
			pstmt.setString(7, loanContract.getPaymentMethod());
			pstmt.setLong(8, loanContract.getBalance());
			pstmt.setDate(9, loanContract.getPaymentDate());
			pstmt.setLong(10, loanContract.getDelinquentAmount());
			pstmt.setInt(11, loanContract.getGuarantorId());
			pstmt.setLong(12, loanContract.getInterestRate());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Update a loan contract
	public int updateLoanContract(LoanContractDTO loanContract) {
		String SQL = "UPDATE loanContract SET l_id = ?, c_id = ?, e_id = ?, start_date = ?,"
				+ " muturity_date = ?, payment_method = ?, balance = ?, payment_date = ?,"
				+ " delinquent_amount = ?, guarantor_id = ?, interest_rate = ? WHERE lc_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanContract.getLoanId());
			pstmt.setInt(2, loanContract.getCustomerId());
			pstmt.setInt(3, loanContract.getEmployeeId());
			pstmt.setTimestamp(4, loanContract.getStartDate());
			pstmt.setTimestamp(5, loanContract.getMuturityDate());
			pstmt.setString(6, loanContract.getPaymentMethod());
			pstmt.setLong(7, loanContract.getBalance());
			pstmt.setDate(8, loanContract.getPaymentDate());
			pstmt.setLong(9, loanContract.getDelinquentAmount());
			pstmt.setInt(10, loanContract.getGuarantorId());
			pstmt.setLong(11, loanContract.getInterestRate());
			pstmt.setInt(12, loanContract.getLoanContractId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete a loan contract
	public int deleteLoanContract(int loanContractId) {
		String SQL = "DELETE FROM loanContract WHERE lc_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanContractId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Fill a LoanContractDTO from a ResultSet
	private void fillLoanContractDTOFromResultSet(LoanContractDTO loanContract, ResultSet rs) throws SQLException {
		loanContract.setLoanContractId(rs.getInt("lc_id"));
		loanContract.setLoanId(rs.getInt("l_id"));
		loanContract.setCustomerId(rs.getInt("c_id"));
		loanContract.setEmployeeId(rs.getInt("e_id"));
		loanContract.setStartDate(rs.getTimestamp("start_date"));
		loanContract.setMuturityDate(rs.getTimestamp("muturity_date"));
		loanContract.setPaymentMethod(rs.getString("payment_method"));
		loanContract.setBalance(rs.getLong("balance"));
		loanContract.setPaymentDate(rs.getDate("payment_date"));
		loanContract.setDelinquentAmount(rs.getLong("delinquent_amount"));
		loanContract.setGuarantorId(rs.getInt("guarantor_id"));
		loanContract.setInterestRate(rs.getLong("interest_rate"));
	}

	// 모든 데이터 가져오기
	public List<LoanContractDTO> getLoanContracts() {
		String SQL = "SELECT * FROM loanContract";
		List<LoanContractDTO> loanContracts = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					LoanContractDTO loanContract = new LoanContractDTO();
					fillLoanContractDTOFromResultSet(loanContract, rs);
					loanContracts.add(loanContract);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loanContracts;
	}

	// loanContractId 에 따라 데이터 가져오기
	public LoanContractDTO getLoanContractByLoanContractId(int loanContractId) {
		LoanContractDTO loanContract = new LoanContractDTO();
		String SQL = "SELECT * FROM loanContract WHERE lc_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanContractId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					fillLoanContractDTOFromResultSet(loanContract, rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loanContract;
	}
	
	// loanId에 따라 상품 테이블에서 대출 구분, 상품 이름 가져오기
	public List<String> getLoanProductInformation(int loanId) {
		//LoanContractDTO loanContract = new LoanContractDTO();
		List<String> loanInfomationList = new ArrayList<>();
		//String loanType, loanName;
		String SQL = "select loan_name, loan_type from loans where l_id= ?";
		
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					// loanType, loanName 순서로 들어감
					loanInfomationList.add(rs.getString("loan_type"));
					loanInfomationList.add(rs.getString("loan_name"));
					//fillLoanContractDTOFromResultSet(loanContract, rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loanInfomationList;
	}
	
	// 고객 테이블에서 고객 이름 가져오기
	public String getCustomerName(int customerId) {
		String customerName = "";
		String SQL = "select c_name from customers where c_id= ?";
		
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, customerId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					customerName = rs.getString("c_name");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerName;	
	}
}
