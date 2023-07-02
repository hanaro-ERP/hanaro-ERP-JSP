package Controller;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.LoanProductDAO;
import DTO.CreditScoringDTO;
import DTO.CustomerDTO;
import DTO.LoanContractDTO;
import DTO.LoanProductDTO;
import DTO.RepaymentMethodDTO;
import Service.LoanService;
import util.CustomerUtil;

//@WebServlet("/loan/*")
@WebServlet(urlPatterns = {"/loan/repayment", "/loan/subscription"})
public class LoanSubscriptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoanService loanService = new LoanService();
	
	public LoanSubscriptionController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		postLoanSubscriptionProcess(request, response);
	}
	
	protected void postLoanSubscriptionProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("postLoanSubscriptionProcess: " );

		CustomerUtil customerUtil = new CustomerUtil();
		String requestURI = request.getRequestURI();
		System.out.println("requestURI: "+requestURI );
		
		if (requestURI.endsWith("subscription")) {
			try {			
				String[] infos = {"customerName", "phoneNumber", "suretyName", "residentRegistrationNumber", "age", "gender", "country", "city", "district", "employeeName", "bank", "customerRank", "creditRank", "disalbitilityRank", "job", "loanType", "loanProductName", "collateral", "collateralValue", "loanAmount", "interest", "interestRate", "loanPerpose", "repaymentMethod"};
				
				//고객 정보
				CustomerDTO customerDTO = new CustomerDTO();
				
				String customerName = request.getParameter("customerName");
				String phoneNumber = request.getParameter("phoneNumber");
				String address = request.getParameter("address");
				String id1 = request.getParameter("identification1");
				String id2 = request.getParameter("identification2");
				String identification = id1 + "-" + id2; 

				int age = customerUtil.getAgeFromIdentification(id1);
				boolean gender = customerUtil.convertIntToGender(Integer.parseInt(id2.substring(0,1)));

				String country = request.getParameter("country");
				String jobCode = request.getParameter("jobCode");
				String suretyName = request.getParameter("suretyName");			
				String employeeName = request.getParameter("employeeName");
				String bankName = request.getParameter("bankName"); //주거래지점
				String customerRank = request.getParameter("grade");
				String creditRank = request.getParameter("credit");
				
				if(!customerName.equals(""))
					customerDTO.setCustomerName(customerName);
				if(!phoneNumber.equals(""))
					customerDTO.setPhoneNumber(phoneNumber);
				if(!address.equals(""))
					customerDTO.setAddress(address);
				if(!identification.equals("")) {
					customerDTO.setIdentification(identification);
					customerDTO.setAge(age);
					customerDTO.setGender(gender);
				}
				if(!suretyName.equals(""))
					customerDTO.setGuarantor(suretyName);
				if(!jobCode.equals(""))
					customerDTO.setJobCode(jobCode);
				if(!country.equals(""))
					customerDTO.setCountry(country);
				if(!employeeName.equals(""))
					customerDTO.setEmployeeName(employeeName);
				if(!bankName.equals(""))
					customerDTO.setBankName(bankName);
				if(!customerRank.equals(""))
					customerDTO.setGrade(customerRank);
				if(!creditRank.equals(""))
					customerDTO.setCredit(creditRank);
							
				//상품정보
				LoanContractDTO loanContractDTO = new LoanContractDTO();

				String loanType = request.getParameter("loanType");
				String loanProductName = request.getParameter("loanProductName");
				String collateral = request.getParameter("collateral");
				String loanAmount = request.getParameter("loanAmount");
				String interestRate = request.getParameter("interestRate");
				String repaymentMethod = request.getParameter("repaymentMethod");
				String gracePeriod = request.getParameter("gracePeriod");

				if(loanType != null)
					loanContractDTO.setLoanType(loanType);
				if(loanProductName != null)
					loanContractDTO.setLoanName(loanProductName);
				if(collateral != null)
					loanContractDTO.setCollateralDetails(collateral);
				if(loanAmount != null) {
					loanContractDTO.setLoanAmount(Integer.parseInt(loanAmount+"0000"));
					loanContractDTO.setBalance(Integer.parseInt(loanAmount+"0000")); //대출 잔금
				}
				if(interestRate != null) {
					loanContractDTO.setInterestRate(Float.parseFloat(interestRate));
					}
				if(repaymentMethod != null)
					loanContractDTO.setPaymentMethod(repaymentMethod);
				if(gracePeriod != null)
					loanContractDTO.setGracePeriod(Integer.parseInt(gracePeriod));

				String repaymentAmountList = request.getParameter("repaymentAmountList");
				String identificationId = request.getParameter("identificationId");			
				String loanProductNameSelect = request.getParameter("loanProductNameSelect");
				
				int isLoanRegistered = loanService.subscriptionLoan(customerDTO, loanContractDTO, repaymentAmountList);
				
				List<RepaymentMethodDTO> repaymentMethodDTOList = loanService.getRepaymentMethod(identification);
				request.setAttribute("repaymentMethod", repaymentMethodDTOList);

				response.sendRedirect(request.getContextPath() + "/navigation/loanContract?mod=" + isLoanRegistered);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (requestURI.endsWith("repayment")) {
			String repaymentAmountList = request.getParameter("repaymentAmountList");
			String identificationId = request.getParameter("identificationId");			
			String loanProductNameSelect = request.getParameter("loanProductNameSelect");
			
			int isUpdated = loanService.updateRepaymentAmount(identificationId, loanProductNameSelect, repaymentAmountList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loan/productSubscription.jsp");
			dispatcher.forward(request, response);
		}
	}
}