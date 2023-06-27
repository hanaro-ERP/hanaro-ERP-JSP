package Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import DAO.BankDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import DAO.LoanContractDAO;
import DAO.LoanProductDAO;
import DTO.BankDTO;
import DTO.CustomerDTO;
import DTO.CustomerSearchDTO;
import DTO.EmployeeDTO;
import util.CustomerUtil;
import util.LoanUtil;

public class CustomerService {
	CustomerDAO customerDAO = new CustomerDAO();
	EmployeeDAO employeeDAO = new EmployeeDAO();
	BankDAO bankDAO = new BankDAO();

	CustomerUtil customerUtil = new CustomerUtil();
	
	public CustomerService() {
	}
	
	public int registerCustomer(CustomerDTO customerDTO) {		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		BankDAO bankDAO = new BankDAO();
		
		CustomerDAO customerDAO = new CustomerDAO();
		
		int e_id = employeeDAO.getEmployeeIdByEmployeeName(customerDTO.getEmployeeName());
		int b_id = bankDAO.getBankIdByBankName(customerDTO.getBankName());
		
		int isCustomerRegister = customerDAO.insertCustomer(customerDTO, e_id, b_id);
		
		return isCustomerRegister;
	}

	public CustomerDTO getCustomerDetail(int cutomerId) throws NoSuchAlgorithmException {
		CustomerDTO customer = customerDAO.getCustomerByCustomerId(cutomerId);
		EmployeeDTO employee = employeeDAO.getEmployeeByEmployeeId(customer.getEmployeeId());
		BankDTO bank = bankDAO.getBankByBankId(customer.getBankId());

		customer.setStrGender(customerUtil.convertBinaryToGender(customer.isGender()));
		customer.setEmployeeName(employee.getEmployeeName());
		customer.setBankName(bank.getBankName());

		return customer;
	}
	
	public List<CustomerDTO> getCustomerList(CustomerSearchDTO customerSearchDTO) {
		
		List<CustomerDTO> customerList = customerDAO.getCustomersByDTO(customerSearchDTO);
		
		return customerList;
	}
}
