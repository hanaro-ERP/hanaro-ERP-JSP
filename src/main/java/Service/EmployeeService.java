package Service;

import DAO.BankDAO;
import DAO.EmployeeDAO;
import DTO.BankDTO;
import DTO.CustomerSearchDTO;
import DTO.EmployeeDTO;
import java.util.*;

public class EmployeeService {
	EmployeeDAO employeeDAO = new EmployeeDAO();
	
	public EmployeeService() {
		
	}
	
	public List<EmployeeDTO> getEmployeeList(EmployeeDTO employeeDTO, int page) {
		
		List<EmployeeDTO> findEmployees = employeeDAO.getEmployeesByDTO(employeeDTO, page);
		
		return findEmployees;
	}
	
	public int getEmployeeCount(EmployeeDTO employeeDTO) {
		return employeeDAO.getEmployeeCount(employeeDTO);
	}
}
