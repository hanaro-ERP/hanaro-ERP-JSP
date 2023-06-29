package Service;

import java.security.NoSuchAlgorithmException;

import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import util.HashingUtil;

public class RegisterService {

	public RegisterService() {
	}

	public static int registerEmployee(EmployeeDTO employeeDTO) throws NoSuchAlgorithmException {
		String salt = HashingUtil.generateSalt();
		String password = employeeDTO.getPassword();
		password = HashingUtil.hashPassword(password, salt);
		employeeDTO.setPassword(password);
		employeeDTO.setSalt(salt);
		employeeDTO.setBankId(1);
		employeeDTO.setPhoneNumber("010-9510-2137");
		employeeDTO.setAdmin(true);
		employeeDTO.setDepartment("디지털 혁신팀");
		EmployeeDAO employeeDAO = new EmployeeDAO();
		return employeeDAO.insertEmployee(employeeDTO);
	}
}
