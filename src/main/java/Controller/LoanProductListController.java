package Controller;

import java.io.*;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loanProductList")
public class LoanProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LoanProductListController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postLoanProductListProcess(request, response);
	}
	
	protected void postLoanProductListProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String type = request.getParameter("loanType");
			String[] creditInfos = {"loanType", "loanProductJob", "loanProductPeriod", "loanProductIncome", "loanProductLimit", "loanName"};
			String[] mortgageInfos = {"loanType", "loanProductMortgage", "loanName"};

			if (type.equals("신용대출")) {
				for (int i = 0; i < creditInfos.length; i++) {
				    String[] selectedJobs = request.getParameterValues(creditInfos[i]);
				    if (selectedJobs != null) {
				        System.out.println(creditInfos[i] + ": " + String.join(", ", selectedJobs));
				    }
				}
			} else {
				for (int i = 0; i < mortgageInfos.length; i++) {
				    String[] selectedJobs = request.getParameterValues(mortgageInfos[i]);
				    if (selectedJobs != null) {
				        System.out.println(mortgageInfos[i] + ": " + String.join(", ", selectedJobs));
				    }
				}
			}
			
			
			response.sendRedirect(request.getContextPath() + "/view/loan/loanProductList/loanProductList.jsp");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
