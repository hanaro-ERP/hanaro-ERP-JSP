package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.AccountDTO;
import util.DatabaseUtil;

public class AccountDAO {

	// insert a new account
	public int insertAccount(AccountDTO account) {
		String SQL = "INSERT INTO accounts (a_id, c_id, account_type, account_open_date, account_balance) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, account.getAccountId());
			pstmt.setInt(2, account.getCustomerId());
			pstmt.setString(3, account.getAccountType());
			pstmt.setTimestamp(4, account.getAccountOpenDate());
			pstmt.setLong(5, account.getAccountBalance());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Read an account by accountId
	public AccountDTO getAccountByAccountId(int accountId) {
		AccountDTO account = new AccountDTO();
		String SQL = "SELECT * FROM accounts WHERE a_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, accountId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					fillAccountDTOFromResultSet(account, rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}

	// Fill an AccountDTO from a ResultSet
	private void fillAccountDTOFromResultSet(AccountDTO account, ResultSet rs) throws SQLException {
		account.setAccountId(rs.getInt("a_id"));
		account.setCustomerId(rs.getInt("c_id"));
		account.setAccountType(rs.getString("account_type"));
		account.setAccountOpenDate(rs.getTimestamp("account_open_date"));
		account.setAccountBalance(rs.getLong("account_balance"));
	}

	// Update an account
	public int updateAccount(AccountDTO account) {
		String SQL = "UPDATE accounts SET c_id = ?, account_type = ?, account_open_date = ?, account_balance = ? "
				+ "WHERE a_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, account.getCustomerId());
			pstmt.setString(2, account.getAccountType());
			pstmt.setTimestamp(3, account.getAccountOpenDate());
			pstmt.setLong(4, account.getAccountBalance());
			pstmt.setInt(5, account.getAccountId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete an account
	public int deleteAccount(int accountId) {
		String SQL = "DELETE FROM accounts WHERE a_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, accountId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Get all accounts
	public List<AccountDTO> getAccounts() {
		String SQL = "SELECT * FROM accounts";
		List<AccountDTO> accounts = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					AccountDTO account = new AccountDTO();
					fillAccountDTOFromResultSet(account, rs);
					accounts.add(account);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}
}