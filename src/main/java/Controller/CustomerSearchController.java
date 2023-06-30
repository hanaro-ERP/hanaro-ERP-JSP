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
		try {
			LoanUtil loanUtil = new LoanUtil();
			
			String id1 = request.getParameter("identification1");
			String id2 = request.getParameter("identification2");
			
			CustomerDTO customerDTO = customerService.getCustomerListByIdentification(id1+"-"+id2);
			
			if(customerDTO.getCustomerName() != null) {
				CustomerDTO customer = customerService.getCustomerDetail(customerDTO.getCustomerId());
				
				customer.setJobName(loanUtil.convertJobCode(customer.getJobCode()));
				
				request.setAttribute("customer", customer);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loan/productSubscription.jsp");			
				dispatcher.forward(request, response);
				
				/*request.getSession().setAttribute("customer", customer);
				
				if(pageId.equals("1")) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/customer/customerRegist.jsp");			
					dispatcher.forward(request, response);
					String newURL = "/hanaro-ERP-JSP/navigation/customerRegist";
				    response.sendRedirect(newURL);
				} else if(pageId.equals("2")) {		*/	
			} else {
				System.out.println("aa");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loan/productSubscription.jsp");			
				dispatcher.forward(request, response);
			}
			/*String newURL = "/hanaro-ERP-JSP/navigation/loanSubscription";
			response.sendRedirect(newURL);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
