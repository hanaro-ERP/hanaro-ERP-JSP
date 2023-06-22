package Controller;

import java.io.*;
import java.sql.Date;
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
import Service.LoanContractService;

@WebServlet("/loanContractList")
public class LoanContractController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//LoanContractService loanContractService = new LoanContractService();
	
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
		String loanName = request.getParameter("loanName");
		String loanType = request.getParameter("loanType");
		String customerName = request.getParameter("customerName");
		String employeeName = request.getParameter("employeeName");
		String loanContractStartDate = request.getParameter("loanContractStartDate");
		String loanContractEndDate = request.getParameter("loanContractEndDate");
		String balanceList = request.getParameter("balanceList");
		String latePaymentDate = request.getParameter("loanContractLimit");
		
		LoanContractDTO loanContractDTO = new LoanContractDTO();	// 받은 값 저장
		
		if (loanName != "") {
			loanContractDTO.setLoanName(loanName);
		}
		if (loanType != "") {
			loanContractDTO.setLoanType(loanType);
		}
		if (customerName != "") {
			loanContractDTO.setCustomerName(customerName);
		}
		if (employeeName != "") {
			loanContractDTO.setEmployeeName(employeeName);
		}
		
		try {
			System.out.println("!!! postLoanContractProcess");

			List<LoanContractDTO> loanContractDTOList = LoanContractService.getLoanContractDetail(loanContractDTO);

			// JSP에 데이터 전달
			request.setAttribute("loanContracts", loanContractDTOList);
			request.setAttribute("searchInputValue", loanContractDTO);
			
		} catch (Exception e) {
			System.out.println("!!! LoanContractController 오류 " + e);
			e.printStackTrace();
		}

		// JSP 페이지로 포워드
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/loan/loanContract/loanContractList.jsp");
		dispatcher.forward(request, response);
	}
}
