package Service;

import DAO.EmployeeDAO;
import DTO.EmployeeDTO;

public class LoginService {

	public LoginService() {
	}

	public static boolean authenticateEmployee(EmployeeDTO employeeDTO) {
		int employeeId = employeeDTO.getEmployeeId();
		String password = employeeDTO.getPassword();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		String storedPassword = employeeDAO.getPasswordByEmployeeId(employeeId);

		return password.equals(storedPassword) ? true : false;
	}
}
