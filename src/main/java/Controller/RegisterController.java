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
import Service.RegisterService;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void destroy() {
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
		// DTO를 매개로 보내는 이유는 정보 은닉을 위해겠죠? 너무 번거로움. 따로 메소드를 만들까...
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployeeId(employeeId);
		employeeDTO.setPassword(password);
		employeeDTO.setEmployeeName(name);
		employeeDTO.setPosition(position);
		int isSuccess = 0;
		try {
			isSuccess = RegisterService.registerEmployee(employeeDTO);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (isSuccess == -1) {
			request.getSession().setAttribute("failed", "failed");
			response.sendRedirect(request.getContextPath() + "/view/login/tempRegister.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/view/login/login.jsp");
		}
	}

}
