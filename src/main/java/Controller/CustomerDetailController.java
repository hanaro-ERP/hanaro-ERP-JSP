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

@WebServlet("/customerDetail")
public class CustomerDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CustomerService customerService = new CustomerService();
	
	public CustomerDetailController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		getCustomerDetailProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	}
	
	private void getCustomerDetailProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			CustomerDTO customer = customerService.getCustomerDetail(Integer.parseInt(id));
			
			String mod = request.getParameter("mod");
			String del = request.getParameter("del");
			customer.setMod(mod);
			customer.setDel(del);
			
			request.setAttribute("customer", customer);
			
			if (del != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/components/customerPopup/customerDeletion.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/components/customerPopup/customerInfo.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
