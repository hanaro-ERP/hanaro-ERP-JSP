package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.CustomerDTO;
import Service.CustomerService;
import util.EncryptUtil;
import util.LoanUtil;

@WebServlet(urlPatterns = {"/customerSearch", "/customer/searchReturn"})
public class CustomerSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService customerService = new CustomerService();
	
	public CustomerSearchController() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String urlPattern = request.getServletPath();
	    if ("/customerSearch".equals(urlPattern)) {
	    	getCustomerSearchProcess(request, response);
	    } else {

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
	
	protected void returnCustomerProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		CustomerDTO customer = new CustomerDTO();

		try {
			LoanUtil loanUtil = new LoanUtil();
			EncryptUtil encryptUtil = new EncryptUtil();
			
			String id1 = request.getParameter("identification1");
			String id2 = request.getParameter("identification2");
			
			String encrypted = encryptUtil.encrypt(id1+"-"+id2);
			
			CustomerDTO customerDTO = customerService.getCustomerListByIdentification(encrypted);
			
			if(customerDTO.getCustomerName() != null) {
				customer = customerService.getCustomerDetail(customerDTO.getCustomerId());
				
				customer.setJobName(loanUtil.convertJobCode(customer.getJobCode()));
				customer.setIdentification(id1+"-"+id2);
				System.out.println(customer.getIdentification()+"zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
				
				request.setAttribute("customer", customer);
			} 
			else {
				String msg = "해당 고객 정보가 없습니다.";
				request.setAttribute("msg", msg);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loan/productSubscription.jsp");			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
