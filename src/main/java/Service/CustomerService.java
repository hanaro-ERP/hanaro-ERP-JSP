package Service;

import java.security.NoSuchAlgorithmException;

import DAO.BankDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import DTO.BankDTO;
import DTO.CustomerDTO;
import DTO.EmployeeDTO;

public class CustomerService {

	public CustomerService() {
	}

	public static CustomerDTO getCustomerDetail(int cutomerId) throws NoSuchAlgorithmException {
		CustomerDAO customerDAO = new CustomerDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		BankDAO bankDAO = new BankDAO();

		CustomerDTO customer = customerDAO.getCustomerByCustomerId(cutomerId);
		EmployeeDTO employee = employeeDAO.getEmployeeByEmployeeId(customer.getEmployeeId());
		BankDTO bank = bankDAO.getBankByBankId(customer.getBankId());

		if (customer.isGender() == false) {
			customer.setStrGender("남");
		} else {
			customer.setStrGender("여");
		}
		customer.setEmployeeName(employee.getEmployeeName());
		customer.setBankName(bank.getBankName());

		return customer;
	}
}
