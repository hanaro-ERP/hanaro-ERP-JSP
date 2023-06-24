package Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import DAO.BankDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import DAO.LoanContractDAO;
import DAO.LoanDAO;
import DTO.BankDTO;
import DTO.CustomerDTO;
import DTO.EmployeeDTO;
import DTO.LoanContractDTO;
import DTO.LoanDTO;

public class LoanContractService {
	public LoanContractService() {
		
	}
	
	public static List<LoanContractDTO> getLoanContractDetail(LoanContractDTO loanContractDTO) throws NoSuchAlgorithmException {
		System.out.println("~~~~~~~~~~service - getLoanContractDetail");
		
		LoanContractDAO loanContractDAO = new LoanContractDAO();
		CustomerDAO customerDAO = new CustomerDAO();
		LoanDAO loanDAO = new LoanDAO();

		List<LoanContractDTO> loanContractDTOList = loanContractDAO.getLoanContractByDTO(loanContractDTO);
		
		return loanContractDTOList;		
	}
}