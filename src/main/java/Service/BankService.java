package Service;

import java.util.List;

import DAO.BankDAO;
import DTO.BankDTO;
import DTO.EmployeeDTO;

public class BankService {

	public static List<BankDTO> getBankList(BankDTO bankDTO) {
		BankDAO bankDAO = new BankDAO();
		List<BankDTO> bankList = bankDAO.getBankListByDTO(bankDTO);
	
		return bankList;
	}

	public static BankDTO getBankName(EmployeeDTO employeeDTO) {
		BankDAO bankDAO = new BankDAO();
		BankDTO bankDTO = bankDAO.getBankNameByBankID(employeeDTO);
		
		return bankDTO;
	}
}
