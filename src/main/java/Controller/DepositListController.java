package Controller;

import java.io.*;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.AccountSearchDTO;

@WebServlet("/depositList")
public class DepositListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public DepositListController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postDepositListProcess(request, response);
	}
	
	protected void postDepositListProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String customerName = request.getParameter("customerName");
			String identification = String.join("-", request.getParameterValues("identification"));
			String accountNumber = request.getParameter("accountNumber");
			String depositType = request.getParameter("depositType");
			String depositOpenDate = String.join("-", request.getParameterValues("loanContractStartDate"));				
			String depositBalance = String.join("-", request.getParameterValues("depositBalance"));
			
			AccountSearchDTO accountSearchDTO = new AccountSearchDTO();
			if (customerName != null) accountSearchDTO.setCustomerName(customerName);
			if (identification.length() == 14) accountSearchDTO.setIdentification(identification);
			if (accountNumber != null) ;
			
			
			System.out.println(customerName);
			System.out.println(identification);
			System.out.println(accountNumber);
			System.out.println(depositType);
			System.out.println(depositOpenDate);
			System.out.println(depositBalance);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
