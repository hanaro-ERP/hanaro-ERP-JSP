package Service;

import java.util.List;

import DAO.AccountDAO;
import DTO.AccountDTO;
import DTO.AccountSearchDTO;
import DTO.BankDTO;
import DAO.TransactionDAO;
import DTO.TransactionDTO;

public class AccountService {
	public static List<AccountDTO> getAccountList(AccountSearchDTO accountSearchDTO, int page) {
		AccountDAO accountDAO = new AccountDAO();

		return accountDAO.getAccountListByDTO(accountSearchDTO, page);
	}

	public static int getAccountCount(AccountSearchDTO accountSearchDTO) {
		AccountDAO accountDAO = new AccountDAO();
		
		return accountDAO.getAccountCount(accountSearchDTO);
	}
	
	public static int getTransactionCount(int accountId) {
		TransactionDAO transactionDAO = new TransactionDAO();

		return transactionDAO.getTransactionCountByAccountId(accountId);
	}
	
	public static List<TransactionDTO> getTransactionList(AccountDTO accountDTO, int page) {
		TransactionDAO transactionDAO = new TransactionDAO();

		return transactionDAO.getTransactionListByAccountId(accountDTO, page);
	}
	
	public static int createAccount(AccountDTO accountDTO) {
		AccountDAO accountDAO = new AccountDAO();
		
		return accountDAO.insertAccount(accountDTO);
	}
}