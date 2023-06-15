package Controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.EmployeeDTO;
import Service.AuthenticationService;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String employeeIdString = request.getParameter("employeeId");
    	int employeeId = Integer.parseInt(employeeIdString);
        String password = request.getParameter("password");
        EmployeeDTO employeeDTO = new EmployeeDTO(employeeId, password);
        boolean loginSuccess = AuthenticationService.authenticateEmployee(employeeDTO);

        if (loginSuccess) {
        	response.sendRedirect(request.getContextPath() + "/view/main/main.jsp");
        } else {
        	response.sendRedirect(request.getContextPath() + "/view/login/login.jsp");
        }

	}
}
