package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.AccountDTO;
import DTO.CreditScoringDTO;
import DTO.TransactionDTO;
import util.DatabaseUtil;
import util.DepositUtil;

public class TransactionDAO {

	// insert a new Transaction detail
	public int insertTransaction(TransactionDTO transaction) {
		String SQL = "INSERT INTO transactions (a_id, transaction_amount, transaction_type, transaction_location) "
				+ "VALUES (?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, transaction.getTransactionId());
			pstmt.setLong(2, transaction.getTransactionAmount());
			pstmt.setString(3, transaction.getTransactionType());
			pstmt.setString(4, transaction.getTransactionLocation());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Read a Transaction detail by transactionId
	public TransactionDTO getTransactionByTransactionId(int transactionId) {
		TransactionDTO transaction = new TransactionDTO();
		String SQL = "SELECT * FROM transactions WHERE a_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, transactionId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					fillTransactionDTOFromResultSet(transaction, rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transaction;
	}

	// Fill a transactionDTO from a ResultSet
	private void fillTransactionDTOFromResultSet(TransactionDTO transaction, ResultSet rs) throws SQLException {
		transaction.setTransactionId(rs.getInt("a_id"));
		transaction.setTransactionAmount(rs.getLong("transaction_amount"));
		transaction.setTransactionType(rs.getString("transaction_type"));
		transaction.setTransactionLocation(rs.getString("transaction_location"));
	}

	// Update a transaction
	public int updateTransaction(TransactionDTO transaction) {
		String SQL = "UPDATE transactions SET transaction_amount = ?, transaction_type = ?, transaction_location = ? "
				+ "WHERE a_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setLong(1, transaction.getTransactionAmount());
			pstmt.setString(2, transaction.getTransactionType());
			pstmt.setString(3, transaction.getTransactionLocation());
			pstmt.setInt(4, transaction.getTransactionId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Delete a transaction detail
	public int deleteTransaction(int transactionId) {
		String SQL = "DELETE FROM transactions WHERE a_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, transactionId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Database operation failed
	}

	// Get all transaction
	public List<TransactionDTO> getTransactions() {
		String SQL = "SELECT * FROM transaction";
		List<TransactionDTO> transactions = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					TransactionDTO transaction = new TransactionDTO();
					fillTransactionDTOFromResultSet(transaction, rs);
					transactions.add(transaction);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactions;
	}

	// Get transactions by accountId
	public int getTransactionCountByAccountId(int accountId) {
		int cnt = 0;

		String query = "SELECT count(*) AS cnt FROM transactions where a_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, accountId);
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
		
	// Get transactions by accountId
	public List<TransactionDTO> getTransactionListByAccountId(AccountDTO accountDTO, int page) {
		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM transactions where a_id = ?");
		queryBuilder.append(" ORDER BY transaction_date DESC");
		queryBuilder.append(" LIMIT 10 OFFSET ?");

		List<TransactionDTO> transactions = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
			pstmt.setInt(1, accountDTO.getAccountId());
			pstmt.setInt(2, (page-1)*10);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					TransactionDTO transaction = new TransactionDTO();
					transaction.setAccountId(accountDTO.getAccountId());
					transaction.setTransactionType(rs.getString("transaction_type"));
					transaction.setStringTransactionDate(
							DepositUtil.convertTimestampToString(rs.getTimestamp("transaction_date")));
					transaction.setAccountNumber(accountDTO.getAccountNumber());
					transaction.setCustomerName(accountDTO.getCustomerName());
					transaction.setTransactionLocation(rs.getString("transaction_location"));
					transaction.setTransactionAmount(rs.getLong("transaction_amount"));
					transaction.setBalance(rs.getLong("balance"));
					transactions.add(transaction);
				}
			}
			return transactions;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Get transactions by customerId
	public List<TransactionDTO> getThreeMonthsTransactionListByCustomerId(CreditScoringDTO creditScoringDTO) {
		String query = "SELECT * FROM transactions WHERE a_id IN "
				+ "(SELECT a_id FROM accounts WHERE c_id = ? and account_type = '예금') "
				+ "AND transaction_date >= NOW() - INTERVAL 3 MONTH";
		List<TransactionDTO> transactions = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, creditScoringDTO.getCustomerId());
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					TransactionDTO transaction = new TransactionDTO();
					transaction.setAccountId(rs.getInt("a_id"));
					transaction.setTransactionType(rs.getString("transaction_type"));
					transaction.setStringTransactionDate(
							DepositUtil.convertTimestampToString(rs.getTimestamp("transaction_date")));
					transaction.setTransactionLocation(rs.getString("transaction_location"));
					transaction.setTransactionAmount(rs.getLong("transaction_amount"));
					transactions.add(transaction);
				}
			}
			return transactions;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}