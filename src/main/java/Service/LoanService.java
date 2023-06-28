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
import util.LoanUtil;

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
	
	public int registerLoanProduct(LoanProductDTO loanProductDTO) throws NoSuchAlgorithmException {
		LoanProductDAO loanDAO = new LoanProductDAO();
		
		int isLoanRegistered = loanDAO.insertLoanProduct(loanProductDTO);
		
		return isLoanRegistered;
	}
	
	public static List<LoanRepaymentDTO> getLoanRepaymentList(LoanContractDTO loanContractDTO) {
		LoanRepaymentDAO loanRepaymentDAO = new LoanRepaymentDAO();

		List<LoanRepaymentDTO> loanRepaymentDTOList = loanRepaymentDAO.getLoanRepaymentByDTO(loanContractDTO);

		return loanRepaymentDTOList;
	}
	
	public int subscriptionLoan(CustomerDTO customerDTO, LoanContractDTO loanContractDTO) {
		LoanUtil loanUtil = new LoanUtil();
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		BankDAO bankDAO = new BankDAO();
		LoanProductDAO loanDAO = new LoanProductDAO();
		
		CustomerDAO customerDAO = new CustomerDAO();
		LoanContractDAO loanContractDAO = new LoanContractDAO();
		
		int e_id = employeeDAO.getEmployeeIdByEmployeeName(customerDTO.getEmployeeName());
		int l_id = loanDAO.getLoanIdByLoanName(loanContractDTO.getLoanName());
		
		int c_id = customerDAO.getCustomerIdByCustomerName(customerDTO.getCustomerName());
		
		System.out.println(c_id + "유저 아이디");
		
		//가입한 날의 일(day)구하여서 넣기
		loanUtil.setDate(loanContractDTO);
		
		//1. 이자율 이후 위험도로 변동생길 예정
		//2. 연체 관련 값 계산
		
		//보증인 id 구하기
		/*int guarantor_id = customerDAO.getCustomerIdByCustomerName(loanContractDTO.getGuarantorName());
		loanContractDTO.setGuarantorId(guarantor_id);
		*/
		int isLoanContract = loanContractDAO.insertLoanContract(loanContractDTO, l_id, c_id, e_id);
		
		return isLoanContract;
	}
}
