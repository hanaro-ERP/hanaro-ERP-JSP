package Service;

import DAO.EmployeeDAO;
import DTO.EmployeeDTO;

public class LoginService {

	public LoginService() {
	}

	public static Object authenticateEmployee(EmployeeDTO employeeDTO) {
		int employeeId = employeeDTO.getEmployeeId();
		String password = employeeDTO.getPassword();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeDTO storedEmployeeDTO = new EmployeeDTO();
		storedEmployeeDTO = employeeDAO.getEmployeeByEmployeeId(employeeId);

		return password.equals(storedEmployeeDTO.getPassword()) ? storedEmployeeDTO : null;
	}
}
