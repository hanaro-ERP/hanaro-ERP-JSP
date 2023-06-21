package Controller;

import java.io.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.LoanContractDAO;
import DTO.LoanContractDTO;

@WebServlet("/loanContractList")
public class LoanContractController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoanContractController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		postLoanContractProcess(request, response);
	}

	protected void postLoanContractProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LoanContractDTO loanContractDTO = new LoanContractDTO();
		
		try {
			System.out.println("!!! postLoanContractProcess");

			LoanContractDAO loanContractDAO = new LoanContractDAO();
			List<LoanContractDTO> loanContracts = loanContractDAO.getLoanContracts();

			// JSP에 데이터 전달
			request.setAttribute("loanContracts", loanContracts);

			// JSP 페이지로 포워드
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/loan/loanContract/loanContractList.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
