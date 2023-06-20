package Controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loanContractList")
public class LoanContractController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LoanContractController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postLoanContractProcess(request, response);
	}
	
	protected void postLoanContractProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String[] infos = {"productName", "loanType", "customerName", "employeeName", "loanContractStartDate", "loanContractEndDate", "balanceList", "loanContractLimit"};
			
			for (int i = 0; i < infos.length; i++) {
			    String[] selectedJobs = request.getParameterValues(infos[i]);
			    if (selectedJobs != null) {
			        System.out.println(infos[i] + ": " + String.join(", ", selectedJobs));
			    }
			}

			response.sendRedirect(request.getContextPath() + "/view/loan/loanContract/loanContract.jsp");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
