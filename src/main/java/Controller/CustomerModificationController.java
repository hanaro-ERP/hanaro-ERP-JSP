package Controller;

import java.io.*;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.CustomerDTO;
import Service.CustomerService;

@WebServlet("/customer/modification")
public class CustomerModificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CustomerService customerService = new CustomerService();
	
	public CustomerModificationController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		getCustomerEditProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postLoanModificationProcess(request, response);
	}
	
	private void getCustomerEditProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String urlPattern = request.getServletPath();
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			CustomerDTO customer = customerService.getCustomerByCustomerId(id);
			
			String[] address = customer.getAddress().split(" ");
			
			if (address.length > 0)
				customer.setCity(address[0]);
			if (address.length > 1)
				customer.setDistrict(address[1]);

			customer.setCustomerId(id);
			request.setAttribute("customer", customer);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/components/customerPopup/customerModification.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void postLoanModificationProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int customerId = Integer.parseInt(request.getParameter("id"));
			String customerName = request.getParameter("customerName");
			String phoneNumber = request.getParameter("phoneNumber");
			String citySelect = request.getParameter("citySelect");
			String district = request.getParameter("district");
			String country = request.getParameter("country");
			String userIdentification1 = request.getParameter("userIdentification1");
			String userIdentification2 = request.getParameter("userIdentification2");
			String job = request.getParameter("job");
			String creditRank = request.getParameter("creditRank");
			String customerRank = request.getParameter("customerRank");
			String employeeName = request.getParameter("employeeName");
			String bank = request.getParameter("bank");

			
			CustomerDTO customerDTO = new CustomerDTO();
			
			if (customerName != null)
				customerDTO.setCustomerName(customerName);
			if (phoneNumber != null)
				customerDTO.setPhoneNumber(phoneNumber);
			if (citySelect != null && district != null)
				customerDTO.setAddress(citySelect + " " + district);
			if (country != null) 
				customerDTO.setCountry(country);
			if (userIdentification1 != null && userIdentification2 != null)
				customerDTO.setIdentification(userIdentification1 + "-" + userIdentification2);
			if (job != null)
				customerDTO.setJobCode(job);
			if (creditRank != null)
				customerDTO.setCredit(creditRank);
			if (customerRank != null)
				customerDTO.setGrade(customerRank);
			if (employeeName != null)
				customerDTO.setEmployeeName(employeeName);
			if (bank != null)
				customerDTO.setBankName(bank);
			
			customerDTO.setCustomerId(customerId);
			int isCustomerModified = customerService.updateCustomer(customerDTO);
			
			request.setAttribute("isCustomerModified", isCustomerModified);
			response.sendRedirect(request.getContextPath() + "/customerDetail?id=" + customerId + "&mod=" + isCustomerModified);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
