package Controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.EmployeeDTO;
import Service.LoginService;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void destroy() {
	}

	protected void redirectWithErrorMessage(HttpServletRequest request, HttpServletResponse response,
			String errorMessage, String employeeId) throws ServletException, IOException {
		request.getSession().setAttribute("employeeId", employeeId);
		request.getSession().setAttribute("errorMessage", errorMessage);
		response.sendRedirect(request.getContextPath() + "/view/login/login.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("employeeId") == "" || request.getParameter("password") == "") {
			redirectWithErrorMessage(request, response, "아이디 또는 비밀번호를 입력하지 않았습니다.", request.getParameter("employeeId"));
			return;
		}
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String password = request.getParameter("password");
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployeeId(employeeId);
		employeeDTO.setPassword(password);
		EmployeeDTO storedEmployeeDTO = new EmployeeDTO();
		try {
			storedEmployeeDTO = (EmployeeDTO) LoginService.authenticateEmployee(employeeDTO);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (storedEmployeeDTO != null) {
			request.getSession().setAttribute("loginId", storedEmployeeDTO.getEmployeeId());
			request.getSession().setAttribute("loginName", storedEmployeeDTO.getEmployeeName());
			request.getSession().setAttribute("loginPosition", storedEmployeeDTO.getPosition());
			response.sendRedirect(request.getContextPath() + "/view/main/main.jsp");
		} else {
			redirectWithErrorMessage(request, response, "아이디 또는 비밀번호를 잘못 입력했습니다.", String.valueOf(employeeId));
		}
	}
}