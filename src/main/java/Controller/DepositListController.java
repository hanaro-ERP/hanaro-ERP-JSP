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
import DTO.CustomerDTO;
import DTO.PaginationDTO;
import DTO.TransactionDTO;
import Service.AccountService;

@WebServlet("/depositList/*")
public class DepositListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public DepositListController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		getTransactionListProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postDepositListProcess(request, response);
	}
	
	protected void postDepositListProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		if (requestURI.endsWith("/depositList/searchAccounts")) {
			try {
				String customerName = request.getParameter("customerName");
				String identification1 = request.getParameter("identification1");
				String identification2 = request.getParameter("identification2");
				String accountNumber = request.getParameter("accountNumber");
				String depositType = request.getParameter("depositType");
				String[] depositOpenDate = request.getParameterValues("depositStartDate");
				String[] depositBalance = request.getParameterValues("depositBalance");
				
				AccountSearchDTO accountSearchDTO = new AccountSearchDTO();
				accountSearchDTO.setCustomerName(customerName);
				accountSearchDTO.setIdentification1(identification1);
				accountSearchDTO.setIdentification2(identification2);
				accountSearchDTO.setAccountNumber(accountNumber);
				accountSearchDTO.setDepositType(depositType);
				accountSearchDTO.setAccountOpenDate(depositOpenDate);
				accountSearchDTO.setDepositBalance(depositBalance);
				
				int pageNo = 1;
				String page = request.getParameter("page");
				if (page != null && !page.equals(""))
					pageNo = Integer.parseInt(page);
				
				int accountCount = AccountService.getAccountCount(accountSearchDTO);
				accountSearchDTO.setCount(accountCount);
				accountSearchDTO.setPage(pageNo);
				
				List<AccountDTO> accountList = AccountService.getAccountList(accountSearchDTO, pageNo);
				
				request.setAttribute("accountDTO", accountSearchDTO);
				request.setAttribute("searchedAccountList", accountList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/deposit/depositProductList.jsp");
				dispatcher.forward(request, response);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void getTransactionListProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		if (requestURI.endsWith("/depositList/searchTransactions")) {
			try {
				int accountId = Integer.parseInt(request.getParameter("id"));
				String accountNumber = request.getParameter("number");
				String customerName = request.getParameter("name");

				int pageNo = 1;
				String page = request.getParameter("page");
				if (page != null && !page.equals(""))
					pageNo = Integer.parseInt(page);
				int accountCount = AccountService.getTransactionCount(accountId);
				
				PaginationDTO paginationDTO = new PaginationDTO();
				
				paginationDTO.setCount(accountCount);
				paginationDTO.setPage(pageNo);
				
				AccountDTO accountDTO = new AccountDTO();
				accountDTO.setAccountId(accountId);
				accountDTO.setAccountNumber(accountNumber);
				accountDTO.setCustomerName(customerName);
				
				List<TransactionDTO> transactionList = AccountService.getTransactionList(accountDTO, pageNo);
				
				request.setAttribute("paginationDTO", paginationDTO);
				request.setAttribute("searchedTransactionList", transactionList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/components/transactionPopup.jsp");
				dispatcher.forward(request, response);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}