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

@WebServlet("/loanDetail")
public class LoanDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoanService loanService = new LoanService();
	
	public LoanDetailController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		getLoanDetailProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	}
	
	private void getLoanDetailProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			LoanProductDTO loanProduct = loanService.getLoanProductDetail(id);
			
			String mod = request.getParameter("mod");
			String del = request.getParameter("del");
			loanProduct.setMod(mod);
			loanProduct.setDel(del);
			
			int subscriberCount = loanService.getLoanContractCountByLoanProductId(id);
			loanProduct.setSubscriberCount(subscriberCount);
			
			request.setAttribute("loanProduct", loanProduct);
			
			if (del != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/components/loanDeletionPopup.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/components/loanProductInfo.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
