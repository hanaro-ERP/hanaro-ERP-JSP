package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import DTO.AccountDTO;
import DTO.AccountSearchDTO;
import DTO.CreditScoringDTO;
import DTO.BankDTO;
import util.DatabaseUtil;
import util.DepositUtil;
import util.EncryptUtil;

public class AccountDAO {

	EncryptUtil encryptUtil = new EncryptUtil();

	// insert a new account
	public int insertAccount(AccountDTO account) {
		String SQL = "INSERT INTO accounts (c_id, account_type, account_number, account_open_date, account_balance) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, account.getCustomerId());
			pstmt.setString(2, account.getAccountType());
			pstmt.setString(3, encryptUtil.encrypt(account.getAccountNumber()));
			pstmt.setTimestamp(4, account.getAccountOpenDate());
			pstmt.setLong(5, account.getAccountBalance());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	public int getAccountIdByCustomerId(int cId) {
		int aId = -1;
		String SQL = "SELECT a_id FROM accounts WHERE c_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, cId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					aId = rs.getInt("a_id");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aId;
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

	public int getAccountCount(AccountSearchDTO accountSearchDTO) {
		int cnt = 0;
		StringBuilder queryBuilder = new StringBuilder("SELECT count(*) AS cnt FROM accounts a ");
		queryBuilder.append("JOIN customers c ON c.c_id = a.c_id ");
		queryBuilder.append("JOIN employees e ON c.e_id = e.e_id ");
		queryBuilder.append("WHERE 1=1");

		if (!accountSearchDTO.getAccountNumber().equals("")) {
			queryBuilder.append(" AND a.account_number = ?");
		}
		if (!accountSearchDTO.getCustomerName().equals("")) {
			queryBuilder.append(" AND c.c_name LIKE ?");
		}
		if (accountSearchDTO.getIdentification1() != "" && accountSearchDTO.getIdentification2() != "") {
			queryBuilder.append(" AND c.identification = ?");
		}
		if (!accountSearchDTO.getDepositType().equals("전체")) {
			queryBuilder.append(" AND a.account_type = ?");
		}
		if (!accountSearchDTO.getAccountOpenDate()[0].equals("전체")) {
			queryBuilder.append(" AND a.account_open_date LIKE ?");
		}
		if (accountSearchDTO.getDepositBalance()[0].equals("전체")) {
		} else if (accountSearchDTO.getDepositBalance()[0].equals("~2천만원")) {
			queryBuilder.append(" AND a.account_balance <= 20000000");
		} else if (accountSearchDTO.getDepositBalance()[0].equals("~3천만원")) {
			queryBuilder.append(" AND a.account_balance <= 30000000");
		} else if (accountSearchDTO.getDepositBalance()[0].equals("~5천만원")) {
			queryBuilder.append(" AND a.account_balance <= 50000000");
		} else if (accountSearchDTO.getDepositBalance()[0].equals("~1억원")) {
			queryBuilder.append(" AND a.account_balance <= 100000000");
		} else if (accountSearchDTO.getDepositBalance()[0].equals("1억원 이상")) {
			queryBuilder.append(" AND a.account_balance >= 100000000");
		} else {
			queryBuilder.append(" AND a.account_balance between ? and ?");
		}
		
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
			int parameterIndex = 1;
			
			if (!accountSearchDTO.getAccountNumber().equals("")) {
				pstmt.setString(parameterIndex++, encryptUtil.encrypt(accountSearchDTO.getAccountNumber()));
			}
			if (!accountSearchDTO.getCustomerName().equals("")) {
				pstmt.setString(parameterIndex++, "%" + accountSearchDTO.getCustomerName() + "%");
			}
			if (accountSearchDTO.getIdentification1() != "" && accountSearchDTO.getIdentification2() != "") {
				String identification = accountSearchDTO.getIdentification1() + "-" + accountSearchDTO.getIdentification2();
				String EncryptedIdentification = encryptUtil.encrypt(identification);
				pstmt.setString(parameterIndex++, EncryptedIdentification);
			}
			if (!accountSearchDTO.getDepositType().equals("전체")) {
				pstmt.setString(parameterIndex++, accountSearchDTO.getDepositType());
			}
			if (!accountSearchDTO.getAccountOpenDate()[0].equals("전체")) {
				if (accountSearchDTO.getAccountOpenDate()[1].equals(""))
					pstmt.setString(parameterIndex++, accountSearchDTO.getAccountOpenDate()[0] + "-%");
				else if (accountSearchDTO.getAccountOpenDate()[2].equals(""))
					pstmt.setString(parameterIndex++, accountSearchDTO.getAccountOpenDate()[0] + "-"
							+ accountSearchDTO.getAccountOpenDate()[1] + "-%");
				else
					pstmt.setString(parameterIndex++,
							accountSearchDTO.getAccountOpenDate()[0] + "-" + accountSearchDTO.getAccountOpenDate()[1]
									+ "-" + accountSearchDTO.getAccountOpenDate()[2] + "%");
			}
			if (accountSearchDTO.getDepositBalance().length == 2) {
				if (accountSearchDTO.getDepositBalance()[0].equals(""))
					pstmt.setString(parameterIndex++, "0");
				else
					pstmt.setString(parameterIndex++, accountSearchDTO.getDepositBalance()[0] + "0000");
				if (accountSearchDTO.getDepositBalance()[1].equals(""))
					pstmt.setString(parameterIndex++, "99999999999999");
				else
					pstmt.setString(parameterIndex++, accountSearchDTO.getDepositBalance()[1] + "0000");
			}
			
			System.out.println(pstmt);

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
	
	public List<AccountDTO> getAccountListByDTO(AccountSearchDTO accountSearchDTO, int page) {
		StringBuilder queryBuilder = new StringBuilder("SELECT a.*, c.c_name, e.e_name FROM accounts a ");
		queryBuilder.append("JOIN customers c ON c.c_id = a.c_id ");
		queryBuilder.append("JOIN employees e ON c.e_id = e.e_id ");
		queryBuilder.append("WHERE 1=1");
		
		if (!accountSearchDTO.getAccountNumber().equals("")) {
			queryBuilder.append(" AND a.account_number = ?");
		}
		if (!accountSearchDTO.getCustomerName().equals("")) {
			queryBuilder.append(" AND c.c_name LIKE ?");
		}
		if (accountSearchDTO.getIdentification1() != "" && accountSearchDTO.getIdentification2() != "") {
			queryBuilder.append(" AND c.identification = ?");
		}
		if (!accountSearchDTO.getDepositType().equals("전체")) {
			queryBuilder.append(" AND a.account_type = ?");
		}
		if (!accountSearchDTO.getAccountOpenDate()[0].equals("전체")) {
			queryBuilder.append(" AND a.account_open_date LIKE ?");
		}
		if (accountSearchDTO.getDepositBalance()[0].equals("전체")) {				
		} else if (accountSearchDTO.getDepositBalance()[0].equals("~2천만원")) {
			queryBuilder.append(" AND a.account_balance <= 20000000");					
		} else if (accountSearchDTO.getDepositBalance()[0].equals("~3천만원")) {
			queryBuilder.append(" AND a.account_balance <= 30000000");					
		} else if (accountSearchDTO.getDepositBalance()[0].equals("~5천만원")) {
			queryBuilder.append(" AND a.account_balance <= 50000000");					
		} else if (accountSearchDTO.getDepositBalance()[0].equals("~1억원")) {
			queryBuilder.append(" AND a.account_balance <= 100000000");					
		} else if (accountSearchDTO.getDepositBalance()[0].equals("1억원 이상")) {
			queryBuilder.append(" AND a.account_balance >= 100000000");					
		} else {
			queryBuilder.append(" AND a.account_balance between ? and ?");
		}
		queryBuilder.append(" ORDER BY a.account_open_date DESC");
		queryBuilder.append(" LIMIT 20 OFFSET ?");
		
		try (Connection conn = DatabaseUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
			int parameterIndex = 1;
			
			if (!accountSearchDTO.getAccountNumber().equals("")) {
				pstmt.setString(parameterIndex++, encryptUtil.encrypt(accountSearchDTO.getAccountNumber()));
			}
			if (!accountSearchDTO.getCustomerName().equals("")) {
				pstmt.setString(parameterIndex++, "%" + accountSearchDTO.getCustomerName() + "%");
			}
			if (accountSearchDTO.getIdentification1() != "" && accountSearchDTO.getIdentification2() != "") {
				String identification = accountSearchDTO.getIdentification1() + "-" + accountSearchDTO.getIdentification2();
				String EncryptedIdentification = encryptUtil.encrypt(identification);
				pstmt.setString(parameterIndex++, EncryptedIdentification);
			}
			if (!accountSearchDTO.getDepositType().equals("전체")) {
				pstmt.setString(parameterIndex++, accountSearchDTO.getDepositType());
			}
			if (!accountSearchDTO.getAccountOpenDate()[0].equals("전체")) {
				if (accountSearchDTO.getAccountOpenDate()[1].equals(""))
					pstmt.setString(parameterIndex++, accountSearchDTO.getAccountOpenDate()[0] + "-%");
				else if (accountSearchDTO.getAccountOpenDate()[2].equals(""))
					pstmt.setString(parameterIndex++, accountSearchDTO.getAccountOpenDate()[0] + "-" + accountSearchDTO.getAccountOpenDate()[1] + "-%");
				else
					pstmt.setString(parameterIndex++, accountSearchDTO.getAccountOpenDate()[0] + "-" + accountSearchDTO.getAccountOpenDate()[1] + "-" + accountSearchDTO.getAccountOpenDate()[2] + "%");
			}
			if (accountSearchDTO.getDepositBalance().length == 2) {
				if (accountSearchDTO.getDepositBalance()[0].equals(""))
					pstmt.setString(parameterIndex++, "0");
				else 
					pstmt.setString(parameterIndex++, accountSearchDTO.getDepositBalance()[0]+"0000");
				if (accountSearchDTO.getDepositBalance()[1].equals(""))
					pstmt.setString(parameterIndex++, "99999999999999");
				else
					pstmt.setString(parameterIndex++, accountSearchDTO.getDepositBalance()[1]+"0000");
			}
			pstmt.setInt(parameterIndex++, (page-1)*20);
			
			System.out.println(pstmt);
			
			List<AccountDTO> findAccountList = new ArrayList<>();
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					AccountDTO accountDTO = new AccountDTO();
					accountDTO.setAccountId(rs.getInt("a_id"));
					accountDTO.setAccountType(rs.getString("account_type"));
					accountDTO.setAccountNumber(encryptUtil.decrypt(rs.getString("account_number")));
					accountDTO.setStringAccountOpenDate(
							DepositUtil.convertTimestampToString(rs.getTimestamp("account_open_date")));
					accountDTO.setCustomerName(rs.getString("c_name"));
					accountDTO.setEmployeeName(rs.getString("e_name"));
					DecimalFormat wonFormat = new DecimalFormat("#,###원");
					accountDTO.setStringAccountBalance(wonFormat.format(rs.getLong("account_balance")));
					findAccountList.add(accountDTO);
				}
			}
			return findAccountList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getTotalBalance(CreditScoringDTO creditScoringDTO) {
		String SQL = "SELECT SUM(account_balance) FROM accounts WHERE c_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, creditScoringDTO.getCustomerId());
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					return rs.getLong(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
