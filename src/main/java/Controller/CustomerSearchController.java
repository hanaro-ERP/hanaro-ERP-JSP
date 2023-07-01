package Controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CustomerDAO;
import DTO.CustomerDTO;
import Service.CustomerService;
import util.EncryptUtil;
import util.LoanUtil;

@WebServlet(urlPatterns = {"/customerSearch", "/customer/searchReturn", "/loan/searchReturn", "/deposit/searchReturn"})
public class CustomerSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService customerService = new CustomerService();
	CustomerDTO customer = new CustomerDTO();

	public CustomerSearchController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String urlPattern = request.getServletPath();
	    if ("/customerSearch".equals(urlPattern)) {
	    	getCustomerSearchProcess(request, response);
	    } else if("/customer/searchReturn".equals(urlPattern)){
	    	setCustomerGuarantorProcess(request, response);
	    }
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		returnCustomerProcess(request, response);
	}
	
	protected void getCustomerSearchProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			String customerName = request.getParameter("name");
			String pageId = request.getParameter("pageId");
			
			List<CustomerDTO> customerList = customerService.getCustomerListByName(customerName);
			
			if(customerList == null)
				customerList = new ArrayList<>();
			
			request.setAttribute("customerList", customerList);
			request.setAttribute("pageId", pageId);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/components/customerSearch.jsp");

			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void setCustomerGuarantorProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			String cId = request.getParameter("userId");
			CustomerDTO customer = customerService.getCustomerDetail(Integer.parseInt(cId));
			
			request.setAttribute("customer", customer);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/customer/customerRegist.jsp");			
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void returnCustomerProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			LoanUtil loanUtil = new LoanUtil();
			
			String id1 = request.getParameter("guarantorIdentification1");
			String id2 = request.getParameter("guarantorIdentification2");
			
			//입력정보
			String customerName = request.getParameter("customerName");
			String phoneNumber = request.getParameter("phoneNumber");
			String citySelect = request.getParameter("citySelect");
			String district = request.getParameter("district");
			String address = citySelect + " " + district;			
			String country = request.getParameter("country");
			String jobCode = request.getParameter("job");
			String customerRank = request.getParameter("customerRank");
			String creditRank = request.getParameter("creditRank");
			String userId1 = request.getParameter("userIdentification1");
			String userId2 = request.getParameter("userIdentification2");
			
			CustomerDTO inputDTO = new CustomerDTO();
			if(customerName != "")
				inputDTO.setCustomerName(customerName);
			if(phoneNumber != "")
				inputDTO.setPhoneNumber(phoneNumber);
			if(citySelect != "")
				inputDTO.setCity(citySelect);
			if(district != "")
				inputDTO.setDistrict(district);
			if(country != "")
				inputDTO.setCountry(country);
			if(jobCode != "")
				inputDTO.setJobCode(jobCode);
			if(customerRank != "")
				inputDTO.setGrade(customerRank);
			if(creditRank != "")
				inputDTO.setCredit(creditRank);
			if(userId1 != "")
				inputDTO.setId1(userId1);
			if(userId2 != "")
				inputDTO.setId2(userId2);
			
			CustomerDTO customerDTO = customerService.getCustomerListByIdentification(id1+"-"+id2);
			if(customerDTO.getCustomerName() != null) {
				customer = customerService.getCustomerDetail(customerDTO.getCustomerId());
				
				customer.setGuarantor(customer.getGuarantor());
				customer.setJobName(loanUtil.convertJobCode(customer.getJobCode()));
				customer.setIdentification(id1+"-"+id2);
				
				request.setAttribute("customer", customer);
			}
			else {
				String msg = "해당 고객 정보가 없습니다.";
				request.setAttribute("msg", msg);
			}

			request.setAttribute("inputData", inputDTO);

			if(request.getParameter("formId").equals("guarantorFind")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/customer/customerRegist.jsp");			
				dispatcher.forward(request, response);
			} else if (request.getParameter("formId").equals("depositFind")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/deposit/depositCreation.jsp");			
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loan/productSubscription.jsp");			
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
