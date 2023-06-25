package Controller;

import java.io.*;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.LoanRegistrationDTO;

@WebServlet("/loanRegistration")
public class LoanRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
			String[] infos = {"productName", "loanType", "collateralType", "jobCode", "loanIncome", "loanMinLimit", "loanMaxLimit", "loanMinPeriod", "loanMaxPeriod", "loanMinRate", "loanMaxRate"};
			
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

			LoanRegistrationDTO loanRegistrationDTO = new LoanRegistrationDTO();
			
			if (productName != null)
				loanRegistrationDTO.setProductName(productName);
			if (loanType != null)
				loanRegistrationDTO.setLoanType(loanType);
			if (collateralType != null)
				loanRegistrationDTO.setCollateralType(collateralType);
			if (jobCode != null)
				loanRegistrationDTO.setJobCode(jobCode);
			if (loanIncome != null)
				loanRegistrationDTO.setLoanIncome(Long.parseLong(loanIncome));
			if (loanMinLimit != null)
				loanRegistrationDTO.setLoanMinLimit(Integer.parseInt(loanMinLimit));
			if (loanMaxLimit != null)
				loanRegistrationDTO.setLoanMaxLimit(Integer.parseInt(loanMaxLimit));
			if (loanMinPeriod != null)
				loanRegistrationDTO.setLoanMinPeriod(Integer.parseInt(loanMinPeriod));
			if (loanMaxPeriod != null)
				loanRegistrationDTO.setLoanMaxPeriod(Integer.parseInt(loanMaxPeriod));
			if (loanMinRate != null)
				loanRegistrationDTO.setLoanMinRate(Float.parseFloat(loanMinRate));
			if (loanMaxRate != null)
				loanRegistrationDTO.setLoanMaxRate(Float.parseFloat(loanMaxRate));
			
			

			response.sendRedirect(request.getContextPath() + "../WEB-INF/view/loan/productRegistration.jsp");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
