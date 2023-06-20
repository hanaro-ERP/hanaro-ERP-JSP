package Service;

import DAO.BankDAO;
import DAO.EmployeeDAO;
import DTO.BankDTO;
import DTO.EmployeeDTO;
import java.util.*;

public class EmployeeService {

	public EmployeeService() {
		
	}
	
	public static List<EmployeeDTO> findEmployee(EmployeeDTO employeeDTO) {
		BankDAO bankDAO = new BankDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		//BankDTO bankDTO = bankDAO.getBankByBankId(employeeDTO.getBankId());
		List<EmployeeDTO> findEmployees = employeeDAO.getEmployeesByInfo(employeeDTO);
		//List<EmployeeDTO> returnList = emp
		
		return findEmployees;
	}
}
