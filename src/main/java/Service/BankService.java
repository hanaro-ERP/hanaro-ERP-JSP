package Service;

import java.util.List;

import DAO.BankDAO;
import DTO.BankDTO;

public class BankService {

	public static List<BankDTO> getBankList(BankDTO bankDTO) {
		BankDAO bankDAO = new BankDAO();
		List<BankDTO> bankList = bankDAO.getBankListByDTO(bankDTO);
	
		return bankList;
	}
}
