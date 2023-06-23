package Service;

import java.util.List;

import DTO.AccountDTO;
import DTO.AccountSearchDTO;
import DAO.AccountDAO;

public class DepositService {

	
	public static List<AccountDTO> getBankList(AccountSearchDTO accountSearchDTO) {
		AccountDAO accountDAO = new AccountDAO();
	
		return accountDAO.getAccountListByDTO(accountSearchDTO);
	}

}
