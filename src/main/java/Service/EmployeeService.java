package Service;

import DAO.BankDAO;
import DAO.EmployeeDAO;
import DTO.BankDTO;
import DTO.EmployeeDTO;
import java.util.*;

public class EmployeeService {

	public EmployeeService() {
		
	}
	
	public static List<EmployeeDTO> getEmployeeList(EmployeeDTO employeeDTO) {
		BankDAO bankDAO = new BankDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<EmployeeDTO> findEmployees = employeeDAO.getEmployeesByDTO(employeeDTO);
		
		return findEmployees;
	}
}
