package Service;

import java.util.List;

import DAO.AccountDAO;
import DTO.AccountDTO;
import DTO.AccountSearchDTO;
import DAO.TransactionDAO;
import DTO.TransactionDTO;

public class AccountService {
	public static List<AccountDTO> getAccountList(AccountSearchDTO accountSearchDTO) {
		AccountDAO accountDAO = new AccountDAO();

		return accountDAO.getAccountListByDTO(accountSearchDTO);
	}

	public static List<TransactionDTO> getTransactionList(AccountDTO accountDTO) {
		TransactionDAO transactionDAO = new TransactionDAO();

		return transactionDAO.getTransactionListByAccountId(accountDTO);
	}
}