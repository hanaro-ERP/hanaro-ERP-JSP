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
import Service.LoanService;

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
		try {
			String loanName = request.getParameter("loanName");
			String loanType = request.getParameter("loanType");
			String customerName = request.getParameter("customerName");
			String employeeName = request.getParameter("employeeName");
			String[] loanContractStartDate = request.getParameterValues("loanContractStartDate");
			String[] loanContractEndDate = request.getParameterValues("loanContractEndDate");
			String[] balanceList = request.getParameterValues("balanceList");
			String latePaymentDate = request.getParameter("latePayment");

			LoanContractDTO loanContractDTO = new LoanContractDTO(); // 받은 값 저장

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

			if (loanContractStartDate.length > 1) {
				String inputStartDate = loanContractStartDate[0] + "-" + loanContractStartDate[1] + "-"
						+ loanContractStartDate[2] + " 00:00:00.0";
				Timestamp inputStartDateTimestamp = Timestamp.valueOf(inputStartDate);
				loanContractDTO.setStartDate(inputStartDateTimestamp);
			}

			if (loanContractEndDate.length > 1) {
				String inputMuturityDate = loanContractEndDate[0] + "-" + loanContractEndDate[1] + "-"
						+ loanContractEndDate[2] + " 00:00:00.0";
				Timestamp inputMuturityDateTimestamp = Timestamp.valueOf(inputMuturityDate);
				loanContractDTO.setMuturityDate(inputMuturityDateTimestamp);
			}

			int[] balanceRange = { 0, 0 };
			if (balanceList.length > 1) {
				balanceRange[0] = Integer.parseInt(balanceList[0]);
				balanceRange[1] = Integer.parseInt(balanceList[1]);
			} else {
				if (balanceList[0].contains("2천")) {
					balanceRange[0] = 0;
					balanceRange[1] = 2000;
				} else if (balanceList[0].contains("3천")) {
					balanceRange[0] = 0;
					balanceRange[1] = 3000;
				} else if (balanceList[0].contains("5천")) {
					balanceRange[0] = 0;
					balanceRange[1] = 5000;
				} else if (balanceList[0].contains("~1억")) {
					balanceRange[0] = 0;
					balanceRange[1] = 10000;
				} else if (balanceList[0].contains("1억원 이상")) {
					balanceRange[0] = 10000;
				}
			}
			loanContractDTO.setBalanceRange(balanceRange);

			int latePaymentPeriod = -1; // 전체
			if (latePaymentDate != "") {
				if (latePaymentDate.contains("6개월")) {
					latePaymentPeriod = 180;
				} else if (latePaymentDate.contains("1년")) {
					latePaymentPeriod = 365;
				} else if (latePaymentDate.contains("3년")) {
					latePaymentPeriod = 365 * 3;
				} else if (latePaymentDate.contains("5년")) {
					latePaymentPeriod = 365 * 5;
				} else if (latePaymentDate.contains("5년이상")) {
					latePaymentPeriod = 365 * 5 + 1;
				}
				loanContractDTO.setLatePaymentPeriod(latePaymentPeriod);
			}

			List<LoanContractDTO> loanContractDTOList = LoanService.getLoanContractList(loanContractDTO);
			request.setAttribute("loanContracts", loanContractDTOList);
			request.setAttribute("searchInputValue", loanContractDTO);

			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/loan/loanContractList.jsp");
			dispatcher.forward(request, response);
		} 
		catch (

		Exception e) {
			System.out.println("controller e =" + e);
			e.printStackTrace();
		}
	}
}
