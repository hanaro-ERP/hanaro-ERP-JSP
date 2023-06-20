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
		String SQL = "INSERT INTO loancontract (lc_id, l_id, c_id, e_id, muturity_date, payment_method, balance, payment_date, delinquency_start, "
				+ "delinquent_day, delinquent_amount, guarantor_id, has_collateral, collateral_value) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanContract.getLoanContractId());
			pstmt.setInt(2, loanContract.getLoanId());
			pstmt.setInt(3, loanContract.getCustomerId());
			pstmt.setInt(4, loanContract.getEmployeeId());
			pstmt.setTimestamp(5, loanContract.getMuturityDate());
			pstmt.setString(6, loanContract.getPaymentMethod());
			pstmt.setLong(7, loanContract.getBalance());
			pstmt.setDate(8, loanContract.getPaymentDate());
			pstmt.setDate(9, loanContract.getDelinquencyStart());
			pstmt.setInt(10, loanContract.getDelinquentDay());
			pstmt.setLong(11, loanContract.getDelinquentAmount());
			pstmt.setInt(12, loanContract.getGuarantorId());
			pstmt.setBoolean(13, loanContract.isHasCollateral());
			pstmt.setLong(14, loanContract.getCollateralValue());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Read a loan contract by loanContractId
	public LoanContractDTO getLoanContractByLoanContractId(int loanContractId) {
		LoanContractDTO loanContract = new LoanContractDTO();
		String SQL = "SELECT * FROM loancontract WHERE lc_id = ?";
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

	// Fill a LoanContractDTO from a ResultSet
	private void fillLoanContractDTOFromResultSet(LoanContractDTO loanContract, ResultSet rs) throws SQLException {
		loanContract.setLoanContractId(rs.getInt("lc_id"));
		loanContract.setLoanId(rs.getInt("l_id"));
		loanContract.setCustomerId(rs.getInt("c_id"));
		loanContract.setEmployeeId(rs.getInt("e_id"));
		loanContract.setMuturityDate(rs.getTimestamp("muturity_date"));
		loanContract.setPaymentMethod(rs.getString("payment_method"));
		loanContract.setBalance(rs.getLong("balance"));
		loanContract.setPaymentDate(rs.getDate("payment_date"));
		loanContract.setDelinquencyStart(rs.getDate("delinquency_start"));
		loanContract.setDelinquentDay(rs.getInt("delinquent_day"));
		loanContract.setDelinquentAmount(rs.getLong("delinquent_amount"));
		loanContract.setGuarantorId(rs.getInt("guarantor_id"));
		loanContract.setHasCollateral(rs.getBoolean("has_collateral"));
		loanContract.setCollateralValue(rs.getLong("collateral_value"));
	}

	// Update a loan contract
	public int updateLoanContract(LoanContractDTO loanContract) {
		String SQL = "UPDATE loancontract SET l_id = ?, c_id = ?, e_id = ?, muturity_date = ?, payment_method = ?, balance = ?, "
				+ "payment_date = ?, delinquency_start = ?, delinquent_day = ?, delinquent_amount = ?, guarantor_id = ?, "
				+ "has_collateral = ?, collateral_value = ? WHERE lc_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanContract.getLoanId());
			pstmt.setInt(2, loanContract.getCustomerId());
			pstmt.setInt(3, loanContract.getEmployeeId());
			pstmt.setTimestamp(4, loanContract.getMuturityDate());
			pstmt.setString(5, loanContract.getPaymentMethod());
			pstmt.setLong(6, loanContract.getBalance());
			pstmt.setDate(7, loanContract.getPaymentDate());
			pstmt.setDate(8, loanContract.getDelinquencyStart());
			pstmt.setInt(9, loanContract.getDelinquentDay());
			pstmt.setLong(10, loanContract.getDelinquentAmount());
			pstmt.setInt(11, loanContract.getGuarantorId());
			pstmt.setBoolean(12, loanContract.isHasCollateral());
			pstmt.setLong(13, loanContract.getCollateralValue());
			pstmt.setInt(14, loanContract.getLoanContractId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete a loan contract
	public int deleteLoanContract(int loanContractId) {
		String SQL = "DELETE FROM loancontract WHERE lc_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, loanContractId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Get all loan contracts
	public List<LoanContractDTO> getLoanContracts() {
		String SQL = "SELECT * FROM loancontract";
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
}