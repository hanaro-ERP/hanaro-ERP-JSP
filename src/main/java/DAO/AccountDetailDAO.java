package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.AccountDetailDTO;
import util.DatabaseUtil;

public class AccountDetailDAO {

	// insert a new account detail
	public int insertAccountDetail(AccountDetailDTO accountDetail) {
		String SQL = "INSERT INTO accountsdetails (a_id, account_amount, account_type, account_location) "
				+ "VALUES (?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, accountDetail.getAccountId());
			pstmt.setLong(2, accountDetail.getAccountAmount());
			pstmt.setString(3, accountDetail.getAccountType());
			pstmt.setString(4, accountDetail.getAccountLocation());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Read an account detail by accountId
	public AccountDetailDTO getAccountDetailByAccountId(int accountId) {
		AccountDetailDTO accountDetail = new AccountDetailDTO();
		String SQL = "SELECT * FROM accountsdetails WHERE a_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, accountId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					fillAccountDetailDTOFromResultSet(accountDetail, rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountDetail;
	}

	// Fill an AccountDetailDTO from a ResultSet
	private void fillAccountDetailDTOFromResultSet(AccountDetailDTO accountDetail, ResultSet rs) throws SQLException {
		accountDetail.setAccountId(rs.getInt("a_id"));
		accountDetail.setAccountAmount(rs.getLong("account_amount"));
		accountDetail.setAccountType(rs.getString("account_type"));
		accountDetail.setAccountLocation(rs.getString("account_location"));
	}

	// Update an account detail
	public int updateAccountDetail(AccountDetailDTO accountDetail) {
		String SQL = "UPDATE accountsdetails SET account_amount = ?, account_type = ?, account_location = ? "
				+ "WHERE a_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setLong(1, accountDetail.getAccountAmount());
			pstmt.setString(2, accountDetail.getAccountType());
			pstmt.setString(3, accountDetail.getAccountLocation());
			pstmt.setInt(4, accountDetail.getAccountId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete an account detail
	public int deleteAccountDetail(int accountId) {
		String SQL = "DELETE FROM accountsdetails WHERE a_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, accountId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Get all account details
	public List<AccountDetailDTO> getAccountDetails() {
		String SQL = "SELECT * FROM accountsdetails";
		List<AccountDetailDTO> accountDetails = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					AccountDetailDTO accountDetail = new AccountDetailDTO();
					fillAccountDetailDTOFromResultSet(accountDetail, rs);
					accountDetails.add(accountDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountDetails;
	}
}