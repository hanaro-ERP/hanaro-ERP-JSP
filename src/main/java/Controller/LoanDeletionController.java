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

@WebServlet("/loan/deletion")
public class LoanDeletionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoanService loanService = new LoanService();
	
	public LoanDeletionController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		getLoanDeletionProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	}
	
	private void getLoanDeletionProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int isDeleted = loanService.deleteLoanProduct(id);
			
			request.setAttribute("isDeleted", isDeleted);
			response.sendRedirect(request.getContextPath() + "/loanDetail?id=" + id + "&del=" + isDeleted);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
