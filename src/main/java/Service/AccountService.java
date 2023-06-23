package Service;

import java.util.List;

import DAO.AccountDAO;
import DTO.AccountDTO;
import DTO.AccountSearchDTO;

public class AccountService {
	public static List<AccountDTO> getAccountList(AccountSearchDTO accountSearchDTO) {
		AccountDAO accountDAO = new AccountDAO();
		
		return accountDAO.getAccountListByDTO(accountSearchDTO);
	}
}
