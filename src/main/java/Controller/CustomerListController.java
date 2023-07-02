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

@WebServlet("/customer/list")
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
			String gender = request.getParameter("customerGender");

			String phoneNumber1 = customerUtil.InvertDash(request.getParameterValues("phoneNumber1"));
			String phoneNumber2 = customerUtil.InvertDash(request.getParameterValues("phoneNumber2"));
			String phoneNumber3 = customerUtil.InvertDash(request.getParameterValues("phoneNumber3"));

			String identification1 = customerUtil.InvertDash(request.getParameterValues("identificationNumber1"));
			String identification2 = customerUtil.InvertDash(request.getParameterValues("identificationNumber2"));

			String[] customerAges = request.getParameterValues("customerAge");
			String[] customerGrades = request.getParameterValues("customerGrade");
			String[] customerCredits = request.getParameterValues("customerCredit");
			
			String country = request.getParameter("country");
			String city = request.getParameter("city");
			String district = request.getParameter("district");
			
			String isOpen = request.getParameter("isOpen");

			CustomerSearchDTO customerSearchDTO = new CustomerSearchDTO();
			if (!customerName.equals(""))
				customerSearchDTO.setCustomerName(customerName);
			if (!customerEmployee.equals(""))
				customerSearchDTO.setEmployeeName(customerEmployee);
			if (!bankLocation.equals(""))
				customerSearchDTO.setBankName(bankLocation);	
			if (!jobCode.equals(""))
				customerSearchDTO.setJobCode(jobCode);	
			if (!gender.equals("")) 
				customerSearchDTO.setGender(customerUtil.convertGenderToBinary(gender));	
				customerSearchDTO.setStrGender(gender);	
			if (!phoneNumber1.equals(""))
				customerSearchDTO.setPhoneNumber1(phoneNumber1);
			if (!phoneNumber2.equals(""))
				customerSearchDTO.setPhoneNumber2(phoneNumber2);
			if (!phoneNumber3.equals(""))
				customerSearchDTO.setPhoneNumber3(phoneNumber3);
			if (!identification1.equals(""))
				customerSearchDTO.setIdentification1(identification1);	
			if (!identification2.equals(""))
				customerSearchDTO.setIdentification2(identification2);	
			if (customerAges != null && customerAges.length > 0)
				customerSearchDTO.setCustomerAges(customerAges);
			if (!customerGrades[0].equals(""))
				customerSearchDTO.setCustomerGrades(customerGrades);
			if (!customerCredits[0].equals(""))
				customerSearchDTO.setCustomerCredits(customerCredits);
			if (!country.equals(""))
				customerSearchDTO.setCountry(country);
			if (!city.equals(""))
				customerSearchDTO.setCity(city);
			if (!district.equals(""))
				customerSearchDTO.setDistrict(district);
			if (!isOpen.equals(""))
				customerSearchDTO.setIsOpen(isOpen);
			
			int pageNo = 1;
			String page = request.getParameter("page");
			if (page != null && !page.equals(""))
				pageNo = Integer.parseInt(page);

			List<CustomerDTO> customerList = customerService.getCustomerList(customerSearchDTO, pageNo);
			int customerCount = customerService.getCustomerCount(customerSearchDTO);
			customerSearchDTO.setCount(customerCount);
			customerSearchDTO.setPage(pageNo);
			
			request.setAttribute("customerSearchDTO", customerSearchDTO);
			request.setAttribute("customerList", customerList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/customer/customerList.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
