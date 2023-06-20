package Controller;

import java.io.*;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			String[] infos = {"productName", "loanPeriod", "loanType", "collateralType", "loanLimit", "interestRate", "interestRateLimit", "loanPerpose", "adequateRisk"};
			
			for (int i = 0; i < infos.length; i++) {
			    String[] selectedJobs = request.getParameterValues(infos[i]);
			    if (selectedJobs != null) {
			        System.out.println(infos[i] + ": " + String.join(", ", selectedJobs));
			    }
			}

			response.sendRedirect(request.getContextPath() + "/view/productRegistration.jsp");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
