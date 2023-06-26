package Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import DAO.BankDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import DAO.LoanContractDAO;
import DAO.LoanProductDAO;
import DAO.LoanRepaymentDAO;
import DTO.BankDTO;
import DTO.CustomerDTO;
import DTO.EmployeeDTO;
import DTO.LoanContractDTO;
import DTO.LoanProductDTO;
import DTO.LoanRepaymentDTO;
import DTO.LoanSearchDTO;

public class LoanService {
	public LoanService() {
		
	}
	
	public static List<LoanContractDTO> getLoanContractList(LoanContractDTO loanContractDTO) throws NoSuchAlgorithmException {
		LoanContractDAO loanContractDAO = new LoanContractDAO();

		List<LoanContractDTO> loanContractDTOList = loanContractDAO.getLoanContractByDTO(loanContractDTO);
		
		return loanContractDTOList;		
	}
	
	public static List<LoanProductDTO> getLoanProductList(LoanSearchDTO loanSearchDTO) throws NoSuchAlgorithmException {
		LoanProductDAO loanDAO = new LoanProductDAO();

		List<LoanProductDTO> loanProductList = loanDAO.getLoansByDTO(loanSearchDTO);
		
		return loanProductList;		
	}
	
	public static List<LoanRepaymentDTO> getLoanRepaymentList(LoanContractDTO loanContractDTO) {
		LoanRepaymentDAO loanRepaymentDAO = new LoanRepaymentDAO();
		List<LoanRepaymentDTO> loanRepaymentDTOList = loanRepaymentDAO.getLoanRepaymentByDTO(loanContractDTO);

		return loanRepaymentDTOList;
	}
}