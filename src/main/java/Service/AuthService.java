package Service;

import java.security.NoSuchAlgorithmException;

import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import util.PasswordUtil;

public class AuthService {

	public AuthService() {
	}

	public static Object authenticateEmployee(EmployeeDTO employeeDTO) throws NoSuchAlgorithmException {
		int employeeId = employeeDTO.getEmployeeId();
		String password = employeeDTO.getPassword();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeDTO storedEmployeeDTO = new EmployeeDTO();
		storedEmployeeDTO = employeeDAO.getEmployeeByEmployeeId(employeeId);
		String storedPassword = storedEmployeeDTO.getPassword();
		String storedSalt = storedEmployeeDTO.getSalt();
		String hashedPassword = PasswordUtil.hashPassword(password, storedSalt);

		return hashedPassword.equals(storedPassword) ? storedEmployeeDTO : null;
	}
}
