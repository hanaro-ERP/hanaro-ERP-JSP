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

import DTO.LoanProductDTO;
import Service.LoanService;

@WebServlet("/loan/registration")
public class LoanRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoanService loanService = new LoanService();

	public LoanRegistrationController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postLoanRegistrationProcess(request, response);
	}
	
	protected void postLoanRegistrationProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			String productName = request.getParameter("productName");
			String loanType = request.getParameter("loanType");
			String collateralType = request.getParameter("collateralType");
			String jobCode = request.getParameter("jobCode");
			String loanIncome = request.getParameter("loanIncome");
			String loanMinLimit = request.getParameter("loanMinLimit");
			String loanMaxLimit = request.getParameter("loanMaxLimit");
			String loanMinPeriod = request.getParameter("loanMinPeriod");
			String loanMaxPeriod = request.getParameter("loanMaxPeriod");
			String loanMinRate = request.getParameter("loanMinRate");
			String loanMaxRate = request.getParameter("loanMaxRate");

			LoanProductDTO loanProductDTO = new LoanProductDTO();
			
			if (productName != null)
				loanProductDTO.setLoanName(productName);
			if (loanType != null)
				loanProductDTO.setLoanType(loanType);
			if (collateralType != null)
				loanProductDTO.setCollateral(collateralType);
			else 
				loanProductDTO.setCollateral("");
			if (jobCode != null)
				loanProductDTO.setJob(jobCode);
			else
				loanProductDTO.setJob("");
			if (loanIncome != null)
				loanProductDTO.setIncome(Long.parseLong(loanIncome)*10000);
			if (loanMinLimit != null)
				loanProductDTO.setMinAmount(Long.parseLong(loanMinLimit)*10000);
			if (loanMaxLimit != null)
				loanProductDTO.setMaxAmount(Long.parseLong(loanMaxLimit)*10000);
			if (loanMinPeriod != null)
				loanProductDTO.setMinDuration(Integer.parseInt(loanMinPeriod));
			if (loanMaxPeriod != null)
				loanProductDTO.setMaxDuration(Integer.parseInt(loanMaxPeriod));
			if (loanMinRate != null)
				loanProductDTO.setMinRate(Float.parseFloat(loanMinRate));
			if (loanMaxRate != null)
				loanProductDTO.setMaxRate(Float.parseFloat(loanMaxRate));
			
			int isLoanRegistered = loanService.registerLoanProduct(loanProductDTO);
			
			request.setAttribute("isLoanRegistered", isLoanRegistered);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/loan/productRegistration.jsp");
			dispatcher.forward(request, response);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
