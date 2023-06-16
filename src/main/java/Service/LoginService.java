package Service;

import DTO.EmployeeDTO;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmployeeDAO;

public class LoginService {
	
	public LoginService() {
	}
    public static boolean authenticateEmployee(EmployeeDTO employeeDTO) {
        int employeeId = employeeDTO.getEmployeeId();
        String password = employeeDTO.getPassword();
    	EmployeeDAO employeeDAO = new EmployeeDAO(employeeId);
    	String storedPassword = employeeDAO.getEmployeePasswordByEId(employeeId);
    	
    	return password.equals(storedPassword) ? true : false;
    }
}
