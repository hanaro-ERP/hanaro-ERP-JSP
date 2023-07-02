package Controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.AccountDTO;
import DTO.CustomerDTO;
import Service.AccountService;
import Service.CustomerService;
import util.CustomerUtil;
import util.EncryptUtil;

@WebServlet("/deposit/creation")
public class DepositCreationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService customerService = new CustomerService();
	
	public DepositCreationController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postDepositCreationProcess(request, response);
	}
	
	protected void postDepositCreationProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String customerId = request.getParameter("customerId");
			String depositType = request.getParameter("depositType");
			String accountNumber = request.getParameter("accountNumber");
			AccountDTO accountDTO = new AccountDTO();
			
			accountDTO.setCustomerId(Integer.parseInt(customerId));
			accountDTO.setAccountNumber(accountNumber);
			accountDTO.setAccountType(depositType);
			accountDTO.setAccountBalance(0);
			accountDTO.setAccountOpenDate(new Timestamp(System.currentTimeMillis()));
		
			int isDepositCreated = AccountService.createAccount(accountDTO);
			
			request.setAttribute("isDepositCreated", isDepositCreated);
			
			response.sendRedirect(request.getContextPath() + "/navigation/depositList?mod=" + isDepositCreated);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
