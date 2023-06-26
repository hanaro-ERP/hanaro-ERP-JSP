package Controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.BankDTO;
import DTO.EmployeeDTO;
import Service.AuthService;
import Service.BankService;

@WebServlet("/AuthController/*")
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AuthController() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login/login.jsp");
		dispatcher.forward(request, response);
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
		String requestURI = request.getRequestURI();
		if (requestURI.endsWith("/AuthController/Login/")) {
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
				storedEmployeeDTO = (EmployeeDTO) AuthService.authenticateEmployee(employeeDTO);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			if (storedEmployeeDTO != null) {
				UUID uuid = UUID.randomUUID();
				request.getSession().setAttribute("sessionId", uuid.toString());
				request.getSession().setAttribute("loginId", storedEmployeeDTO.getEmployeeId());
				request.getSession().setAttribute("loginName", storedEmployeeDTO.getEmployeeName());
				request.getSession().setAttribute("loginPosition", storedEmployeeDTO.getPosition());
				BankDTO bankDTO = BankService.getBankName(storedEmployeeDTO);
				request.getSession().setAttribute("bankName", bankDTO.getBankName());

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/main/main.jsp");
				dispatcher.forward(request, response);
			} else {
				redirectWithErrorMessage(request, response, "아이디 또는 비밀번호를 잘못 입력했습니다.", String.valueOf(employeeId));
			}
		} else if (requestURI.endsWith("/AuthController/Logout/")) {
			request.getSession().invalidate();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login/login.jsp");
			dispatcher.forward(request, response);
		}
		return;
	}
}