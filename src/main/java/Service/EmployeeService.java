package Service;

import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import java.util.*;

public class EmployeeService {

	public EmployeeService() {
		
	}
	
	public static List<EmployeeDTO> findEmployee(EmployeeDTO employeeDTO) {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<EmployeeDTO> returnList = employeeDAO.getEmployees();
		
		return returnList;
	}
}
