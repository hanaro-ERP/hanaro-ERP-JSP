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

@WebServlet("/employeeList")
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
		/*try {
			String[] infos = {"employeeName", "bankLocation", "department", "position"};
			
			for (int i = 0; i < infos.length; i++) {
			    String[] selectedJobs = request.getParameterValues(infos[i]);
			    if (selectedJobs != null) {
			        System.out.println(infos[i] + ": " + String.join(", ", selectedJobs));
			    }
			}
			
			response.sendRedirect(request.getContextPath() + "/view/employee/empList/empList.jsp");
		} catch (Exception e) {
		
			e.printStackTrace();
		}*/
		
		String employeeName = request.getParameter("employeeName");
		String bankLocation = request.getParameter("bankLocation");
		String department = request.getParameter("department");
		String position = request.getParameter("position");
		
		//System.out.println(employeeName + " " + bankLocation + " " + department + " " + position);
		
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
			List<EmployeeDTO> findEmployee = EmployeeService.findEmployee(employeeDTO);
			
			request.setAttribute("findEmployee", findEmployee);
		} catch (Exception e) {
			System.out.println("employee Service 오류 " + e);
		}
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/view/employee/empList/empList.jsp");
	    dispatcher.forward(request, response);
	}
}
