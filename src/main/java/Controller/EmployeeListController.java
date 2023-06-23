package Controller;

import java.util.List;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.EmployeeDTO;
import Service.EmployeeService;

@WebServlet("/employee/list")
public class EmployeeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public EmployeeListController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postEmployeeListProcess(request, response);
	}
	
	protected void postEmployeeListProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String employeeName = request.getParameter("employeeName");
		String bankLocation = request.getParameter("bankLocation");
		String department = request.getParameter("department");
		String position = request.getParameter("position");
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		if(employeeName != "")
			employeeDTO.setEmployeeName(employeeName);
		if(bankLocation != "")
			employeeDTO.setBankLocation(bankLocation);
		if(department != "") 
			employeeDTO.setDepartment(department);
		if(position != "")
			employeeDTO.setPosition(position);
		
		try {
			List<EmployeeDTO> getEmlpoyeeList = EmployeeService.getEmployeeList(employeeDTO);
			
			//검색을 위해 입력받은 값
			request.setAttribute("searchInputValue", employeeDTO);
			request.setAttribute("findEmployee", getEmlpoyeeList);
		} catch (Exception e) {
			System.out.println("employee Service 오류 " + e);
		}
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/employee/employeeList.jsp");
	    dispatcher.forward(request, response);
	}
}
