package Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import DAO.BankDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import DTO.BankDTO;
import DTO.CustomerDTO;
import DTO.CustomerSearchDTO;
import DTO.EmployeeDTO;
import util.CustomerUtil;

public class CustomerService {
	CustomerDAO customerDAO = new CustomerDAO();
	EmployeeDAO employeeDAO = new EmployeeDAO();
	BankDAO bankDAO = new BankDAO();

	CustomerUtil customerUtil = new CustomerUtil();
	
	public CustomerService() {
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
		
		System.out.print(customerList);
		return customerList;
	}
}
