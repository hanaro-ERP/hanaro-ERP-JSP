package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.EmployeeDTO;
import Service.EmployeeService;

@WebServlet("/employee/list")
public class EmployeeListController extends HttpServlet {

	EmployeeService employeeService = new EmployeeService();

	private static final long serialVersionUID = 1L;

	public EmployeeListController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		postEmployeeListProcess(request, response);
	}

	protected void postEmployeeListProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String employeeName = request.getParameter("employeeName");
		String bankLocation = request.getParameter("bankLocation");
		String department = request.getParameter("department");
		String position = request.getParameter("position");

		EmployeeDTO employeeDTO = new EmployeeDTO();
		if (employeeName != null && !employeeName.equals(""))
			employeeDTO.setEmployeeName(employeeName);
		if (bankLocation != null && !bankLocation.equals(""))
			employeeDTO.setBankLocation(bankLocation);
		if (department != null && !department.equals(""))
			employeeDTO.setDepartment(department);
		if (position != null && !position.equals(""))
			employeeDTO.setPosition(position);

		int pageNo = 1;
		String page = request.getParameter("page");
		if (page != null && !page.equals(""))
			pageNo = Integer.parseInt(page);

		int employeeCount = employeeService.getEmployeeCount(employeeDTO);
		employeeDTO.setCount(employeeCount);
		employeeDTO.setPage(pageNo);

		try {
			List<EmployeeDTO> getEmlpoyeeList = employeeService.getEmployeeList(employeeDTO, pageNo);

			// 검색을 위해 입력받은 값
			request.getSession().setAttribute("searchInputValue", employeeDTO);
			request.getSession().setAttribute("findEmployee", getEmlpoyeeList);
		} catch (Exception e) {
			System.out.println("employee Service 오류 " + e);
		}

		response.sendRedirect("../navigation/employee");
	}
}
