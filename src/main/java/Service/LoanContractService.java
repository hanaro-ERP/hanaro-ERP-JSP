package Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import DAO.BankDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import DAO.LoanContractDAO;
import DAO.LoanDAO;
import DAO.LoanRepaymentDAO;
import DTO.BankDTO;
import DTO.CustomerDTO;
import DTO.EmployeeDTO;
import DTO.LoanContractDTO;
import DTO.LoanDTO;
import DTO.LoanRepaymentDTO;

public class LoanContractService {
	public LoanContractService() {
		
	}
	
	public static List<LoanContractDTO> getLoanContractList(LoanContractDTO loanContractDTO)
			throws NoSuchAlgorithmException {
		System.out.println("~~~~~~~~~~service - getLoanContractDetail");

		LoanContractDAO loanContractDAO = new LoanContractDAO();
		List<LoanContractDTO> loanContractDTOList = loanContractDAO.getLoanContractByDTO(loanContractDTO);

		return loanContractDTOList;
	}

	public static List<LoanRepaymentDTO> getLoanRepaymentList(LoanContractDTO loanContractDTO) {
		LoanRepaymentDAO loanRepaymentDAO = new LoanRepaymentDAO();
		List<LoanRepaymentDTO> loanRepaymentDTOList = loanRepaymentDAO.getLoanRepaymentByDTO(loanContractDTO);

		return loanRepaymentDTOList;
	}
}