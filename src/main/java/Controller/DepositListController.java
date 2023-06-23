package Controller;

import java.io.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.AccountDTO;
import DTO.AccountSearchDTO;
import DTO.BankDTO;
import Service.AccountService;
import Service.BankService;

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
			String depositOpenDate = String.join("-", request.getParameterValues("depositStartDate"));
			String[] depositBalance = request.getParameterValues("depositBalance");
			
			AccountSearchDTO accountSearchDTO = new AccountSearchDTO();
			accountSearchDTO.setCustomerName(customerName);
			if (identification.length() == 14) accountSearchDTO.setIdentification(identification);
			accountSearchDTO.setAccountNumber(accountNumber);
			accountSearchDTO.setDepositType(depositType);
			accountSearchDTO.setAccountOpenDate(depositOpenDate);
			accountSearchDTO.setDepositBalance(depositBalance);
			
			List<AccountDTO> getAccountList = AccountService.getAccountList(accountSearchDTO);
			AccountDTO accountDTO = new AccountDTO();
			
			request.setAttribute("accountDTO", accountDTO);
			request.setAttribute("findAccountList", getAccountList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/deposit/depositProductList/depositProductList.jsp");
			dispatcher.forward(request, response);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}