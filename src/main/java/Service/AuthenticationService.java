package Service;

import DTO.EmployeeDTO;
import DAO.EmployeeDAO;

public class AuthenticationService {
	
	public AuthenticationService() {
	}
    public static boolean authenticateEmployee(EmployeeDTO employeeDTO) {
        int employeeId = employeeDTO.getEmployeeId();
        String password = employeeDTO.getPassword();
    	EmployeeDAO employeeDAO = new EmployeeDAO(employeeId);
    	String storedPassword = employeeDAO.returnPasswordFromDatabase(employeeId);
    	
    	return password.equals(storedPassword) ? true : false;
    }
}
