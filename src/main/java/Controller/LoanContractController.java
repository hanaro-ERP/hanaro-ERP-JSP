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
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		postLoanContractProcess(request, response);
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
				
				loanContractDTO.setStartDateString(loanContractStartDate);
				loanContractDTO.setMuturityDateList(loanContractEndDate);
				
				if (loanContractEndDate.length > 1) {
					String inputMuturityDate = loanContractEndDate[0] + "-" + loanContractEndDate[1] + "-" + loanContractEndDate[2] + " 00:00:00.0";
					Timestamp inputMuturityDateTimestamp = Timestamp.valueOf(inputMuturityDate);
					loanContractDTO.setMaturityDate(inputMuturityDateTimestamp);
				}				
				
				int[] balanceRange = {0,0};
				if (balanceList.length > 1) {
					if (balanceList[0].equals(""))
						balanceRange[0] = 0;
					else
						balanceRange[0] = Integer.parseInt(balanceList[0]);
					
					if (balanceList[1].equals(""))
						balanceRange[1] = 99999;
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
				
				int latePaymentPeriod = -1;	// 전체
				if (latePaymentDate != "") {
					if (latePaymentDate.contains("6개월")){
						latePaymentPeriod = 180;
					}
					else if (latePaymentDate.contains("1년")){
						latePaymentPeriod = 365;
					}
					else if (latePaymentDate.contains("3년")){
						latePaymentPeriod = 365 * 3;
					}
					else if (latePaymentDate.contains("5년")){
						latePaymentPeriod = 365 * 5;
					}
					else if (latePaymentDate.contains("5년이상")){
						latePaymentPeriod = 365 * 5 + 1;
					}
					else if (latePaymentDate.contains("없음")){
						latePaymentPeriod = 0;
					}
					loanContractDTO.setLatePaymentPeriod(latePaymentPeriod);
				}
				
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

		else if (requestURI.endsWith("repaymentList")) {			
			int loanContractId = Integer.parseInt(request.getParameter("selectedLoanContractId"));
			String customerName = request.getParameter("selectedCustomerName");
			String employeeName = request.getParameter("selectedEmployeeName");

			LoanContractDTO loanContractDTO = new LoanContractDTO();
			loanContractDTO.setLoanContractId(loanContractId);
			loanContractDTO.setCustomerName(customerName);
			loanContractDTO.setEmployeeName(employeeName);
			
			List<LoanRepaymentDTO> loanRepaymentDTOList = LoanService.getLoanRepaymentList(loanContractDTO);
			request.setAttribute("showRepaymentList", "showRepaymentList");
			request.setAttribute("searchedRepaymentList", loanRepaymentDTOList);	
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/loan/loanContractList.jsp");
			dispatcher.forward(request, response);
		}
	}
}
