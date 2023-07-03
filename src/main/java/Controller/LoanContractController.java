package Controller;

import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import DAO.LoanContractDAO;
import DTO.LoanContractDTO;
import DTO.LoanRepaymentDTO;
import DTO.PaginationDTO;
import DTO.TransactionDTO;
import Service.AccountService;
import Service.LoanService;

@WebServlet("/loanContracts/*")
public class LoanContractController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LoanContractController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getLoanRepaymentProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		postLoanContractProcess(request, response);
	}

	protected void getLoanRepaymentProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String requestURI = request.getRequestURI();

		if (requestURI.endsWith("repaymentList")) {			
			int loanContractId = Integer.parseInt(request.getParameter("id"));
			String loanName = request.getParameter("loan");
			String customerName = request.getParameter("customer");
	
			int pageNo = 1;
			String page = request.getParameter("page");
			if (page != null && !page.equals(""))
				pageNo = Integer.parseInt(page);
			int contractCount = LoanService.getRepaymentCountByContractId(loanContractId);
			
			PaginationDTO paginationDTO = new PaginationDTO();
			
			paginationDTO.setCount(contractCount);
			paginationDTO.setPage(pageNo);
			
			LoanContractDTO loanContractDTO = new LoanContractDTO();
			loanContractDTO.setLoanContractId(loanContractId);
			loanContractDTO.setLoanName(loanName);
			loanContractDTO.setCustomerName(customerName);
			
			List<LoanRepaymentDTO> loanRepaymentDTOList = LoanService.getLoanRepaymentList(loanContractDTO, pageNo);
			
			request.setAttribute("paginationDTO", paginationDTO);
			request.setAttribute("loanContractDTO", loanContractDTO);
			request.setAttribute("searchedRepaymentList", loanRepaymentDTOList);	

			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/components/repaymentPopup.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	protected void postLoanContractProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String requestURI = request.getRequestURI();

		if (requestURI.endsWith("contractList")) {
			try {
				String loanName = request.getParameter("loanName");
				String loanType = request.getParameter("loanType");
				String customerName = request.getParameter("customerName");
				String employeeName = request.getParameter("employeeName");
				String[] loanContractStartDate = request.getParameterValues("loanContractStartDate");
				String[] loanContractEndDate = request.getParameterValues("loanContractEndDate");
				String[] balanceList = request.getParameterValues("balanceList");
				String latePaymentDate = request.getParameter("latePayment");
				
				LoanContractDTO loanContractDTO = new LoanContractDTO();	// 받은 값 저장
				
				if (loanName != null && !loanName.equals("")) {
					loanContractDTO.setLoanName(loanName);
				}				
				if (loanType != null && !loanType.equals("")) {
					loanContractDTO.setLoanType(loanType);
				}				
				if (customerName != null && !customerName.equals("")) {
					loanContractDTO.setCustomerName(customerName);
				}				
				if (employeeName != null && !employeeName.equals("")) {
					loanContractDTO.setEmployeeName(employeeName);
				}
				
				loanContractDTO.setStartDateString(loanContractStartDate);
				loanContractDTO.setMuturityDateList(loanContractEndDate);
				
				int[] balanceRange = {0,0};
				if (balanceList.length > 1) {
					if (balanceList[0].equals(""))
						balanceRange[0] = 0;
					else
						balanceRange[0] = Integer.parseInt(balanceList[0]);
					
					if (balanceList[1].equals(""))
						balanceRange[1] = 999999999;
					else
						balanceRange[1] = Integer.parseInt(balanceList[1]);
				}
				else {
					if (balanceList[0].contains("2천")){
						balanceRange[0] = 0;
						balanceRange[1] = 2000;
					}
					else if (balanceList[0].contains("3천")){
						balanceRange[0] = 0;
						balanceRange[1] = 3000;
					}
					else if (balanceList[0].contains("5천")){
						balanceRange[0] = 0;
						balanceRange[1] = 5000;
					}
					else if (balanceList[0].contains("~1억")){
						balanceRange[0] = 0;
						balanceRange[1] = 10000;
					}
					else if (balanceList[0].contains("~5억")){
						balanceRange[0] = 0;
						balanceRange[1] = 50000;
					}
					else if (balanceList[0].contains("~10억")){
						balanceRange[0] = 0;
						balanceRange[1] = 100000;
					}
					else if (balanceList[0].contains("10억원 이상")){
						balanceRange[0] = 100000;
					}
				}
				loanContractDTO.setBalanceRange(balanceRange);
				loanContractDTO.setBalanceList(balanceList);
				
				int pageNo = 1;
				String page = request.getParameter("page");
				if (page != null && !page.equals(""))
					pageNo = Integer.parseInt(page);
				int contractCount = LoanService.getLoanContractCount(loanContractDTO);
				
				loanContractDTO.setCount(contractCount);
				loanContractDTO.setPage(pageNo);
				
				List<LoanContractDTO> loanContractDTOList = LoanService.getLoanContractList(loanContractDTO, pageNo);
				request.setAttribute("loanContracts", loanContractDTOList);
				request.setAttribute("searchInputValue", loanContractDTO);
				RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/loan/loanContractList.jsp");
				dispatcher.forward(request, response);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
