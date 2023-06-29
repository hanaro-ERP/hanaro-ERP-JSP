package Service;

import java.util.List;

import DAO.BankDAO;
import DTO.BankDTO;
import DTO.CustomerSearchDTO;
import DTO.EmployeeDTO;

public class BankService {
	BankDAO bankDAO = new BankDAO();
	
	public List<BankDTO> getBankList(BankDTO bankDTO, int page) {
		List<BankDTO> bankList = bankDAO.getBankListByDTO(bankDTO, page);
	
		return bankList;
	}

	public BankDTO getBankName(EmployeeDTO employeeDTO) {
		BankDTO bankDTO = bankDAO.getBankNameByBankID(employeeDTO);
		
		return bankDTO;
	}
	
	public int getBankCount(BankDTO bankDTO) {
		return bankDAO.getBankCount(bankDTO);
	}
}
