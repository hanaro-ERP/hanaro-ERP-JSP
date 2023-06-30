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
	    } else if ("/customer/searchReturn".equals(urlPattern)) {
	        returnCustomerProcess(request, response);
	    } else {

	    }
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	}
	
	protected void getCustomerSearchProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id1 = request.getParameter("id1");
			String id2 = request.getParameter("id2");
			String pageId = request.getParameter("pageId");
			
			List<CustomerDTO> customerList = customerService.getCustomerListByIdentification(id1+"-"+id2);
			
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
		try {
			LoanUtil loanUtil = new LoanUtil();
			
			String userId = request.getParameter("userId");
			String pageId = request.getParameter("pageId");
			
			CustomerDTO customer = customerService.getCustomerDetail(Integer.parseInt(userId));
			customer.setJobName(loanUtil.convertJobCode(customer.getJobCode()));
			
			request.setAttribute("customer", customer);
			//request.getSession().setAttribute("customer", customer);
			
			if(pageId.equals("1")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/customer/customerRegist.jsp");			
				dispatcher.forward(request, response);
				/*String newURL = "/hanaro-ERP-JSP/navigation/customerRegist";
			    response.sendRedirect(newURL);*/
			} else if(pageId.equals("2")) {			
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loan/productSubscription.jsp");			
				dispatcher.forward(request, response);
			    /*String newURL = "/hanaro-ERP-JSP/navigation/loanSubscription";
			    response.sendRedirect(newURL);*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
