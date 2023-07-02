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
	
	public int updateCustomer(CustomerDTO customerDTO) {		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		BankDAO bankDAO = new BankDAO();
		
		CustomerDAO customerDAO = new CustomerDAO();

		int e_id = employeeDAO.getEmployeeIdByEmployeeName(customerDTO.getEmployeeName());
		int b_id = bankDAO.getBankIdByBankName(customerDTO.getBankName());
		
		int isCustomerRegister = customerDAO.updateCustomer(customerDTO, e_id, b_id);
		
		return isCustomerRegister;
	}
	
	public int registerCustomer(CustomerDTO customerDTO) {		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		BankDAO bankDAO = new BankDAO();
		
		CustomerDAO customerDAO = new CustomerDAO();
		
		int e_id = employeeDAO.getEmployeeIdByEmployeeName(customerDTO.getEmployeeName());
		int b_id = bankDAO.getBankIdByBankName(customerDTO.getBankName());
		int guarantor_id = customerDAO.getCustomerIdByCustomerName(customerDTO.getGuarantor());
		
		int isCustomerRegister = customerDAO.insertCustomer(customerDTO, e_id, b_id, guarantor_id);
		
		return isCustomerRegister;
	}

	public CustomerDTO getCustomerDetail(int cutomerId) throws NoSuchAlgorithmException {
		CustomerDTO customer = customerDAO.getCustomerByCustomerId(cutomerId);
		CustomerDTO guarantor = customerDAO.getCustomerByCustomerId(customer.getGuarantorId());
		EmployeeDTO employee = employeeDAO.getEmployeeByEmployeeId(customer.getEmployeeId());
		BankDTO bank = bankDAO.getBankByBankId(customer.getBankId());

		customer.setStrGender(customerUtil.convertBinaryToGender(customer.isGender()));
		customer.setEmployeeName(employee.getEmployeeName());
		customer.setBankName(bank.getBankName());
		customer.setGuarantor(guarantor.getCustomerName());

		return customer;
	}
	
	public List<CustomerDTO> getCustomerList(CustomerSearchDTO customerSearchDTO, int page) {		
		return customerDAO.getCustomersByDTO(customerSearchDTO, page);
	}
	
	public CustomerDTO getCustomerListByIdentification(String identification) {		
		return customerDAO.getCustomersByIdentification(identification);
	}
	
	public int deleteCustomerByCustomerId(int customerId) {		
		return customerDAO.deleteCustomer(customerId);
	}
	
	public CustomerDTO getCustomerByCustomerId(int customerId) {		
		return customerDAO.getCustomerByCustomerId(customerId);
	}
	
	public List<CustomerDTO> getCustomerListByName(String name) {		
		return customerDAO.getCustomersByName(name);
	}
	
	public int getCustomerCount(CustomerSearchDTO customerSearchDTO) {
		return customerDAO.getCustomerCount(customerSearchDTO);
	}
}
