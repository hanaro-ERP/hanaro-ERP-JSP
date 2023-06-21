package Controller;

import java.io.*;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.CustomerDTO;
import DTO.CustomerSearchDTO;
import Service.CustomerService;
import util.CustomerUtil;

@WebServlet("/customerList")
public class CustomerListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CustomerService customerService = new CustomerService();
	CustomerUtil customerUtil = new CustomerUtil();

	public CustomerListController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postCustomerListProcess(request, response);
	}
	
	protected void postCustomerListProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String customerName = request.getParameter("customerName");
			String customerEmployee = request.getParameter("customerEmployee");
			String bankLocation = request.getParameter("bankLocation");
			String jobCode = request.getParameter("jobCode");
			Boolean gender = customerUtil.convertGenderToBinary(request.getParameter("customerGender"));

			String phoneNumber = customerUtil.InvertDash(request.getParameterValues("phoneNumber"));
			String identification = customerUtil.InvertDash(request.getParameterValues("identificationNumber"));

			String[] customerAges = request.getParameterValues("customerAge");
			String[] customerGrades = request.getParameterValues("customerGrade");
			String[] customerCredits = request.getParameterValues("customerCredit");

			CustomerSearchDTO customerSearchDTO = new CustomerSearchDTO(customerName, customerEmployee, bankLocation, jobCode, gender, phoneNumber, identification, customerAges, customerGrades, customerCredits);
		
			List<CustomerDTO> customerList = customerService.getCustomerList(customerSearchDTO);
			
			request.setAttribute("customerList", customerList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/customer/customerList/customerList.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
