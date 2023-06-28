package Controller;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.CustomerDTO;
import DTO.LoanContractDTO;
import Service.LoanService;
import util.CustomerUtil;

@WebServlet("/loan/subscription")
public class LoanSubscriptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoanService loanService = new LoanService();
	
	public LoanSubscriptionController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		postLoanSubscriptionProcess(request, response);
	}
	
	protected void postLoanSubscriptionProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerUtil customerUtil = new CustomerUtil();
		try {
			String[] infos = {"customerName", "phoneNumber", "suretyName", "residentRegistrationNumber", "age", "gender", "country", "city", "district", "employeeName", "bank", "customerRank", "creditRank", "disalbitilityRank", "job", "loanType", "loanProductName", "collateral", "collateralValue", "loanAmount", "interest", "interestRate", "loanPerpose", "repaymentMethod"};
			
			//고객 정보
			CustomerDTO customerDTO = new CustomerDTO();
			
			String customerName = request.getParameter("customerName");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");//.getParameter("citySelect");
			String id1 = request.getParameter("identification").substring(0, 6);
			String id2 = request.getParameter("identification").substring(7, 14);
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
			
			if(customerName != "")
				customerDTO.setCustomerName(customerName);
			if(phoneNumber != "")
				customerDTO.setPhoneNumber(phoneNumber);
			if(address != "")
				customerDTO.setAddress(address);
			if(identification != "") {
				customerDTO.setIdentification(identification);
				customerDTO.setAge(age);
				customerDTO.setGender(gender);
			}
			if(jobCode != "")
				customerDTO.setJobCode(jobCode);
			if(country != "")
				customerDTO.setCountry(country);
			//보증인은 보류
			if(employeeName != "")
				customerDTO.setEmployeeName(employeeName);
			if(bankName != "")
				customerDTO.setBankName(bankName);
			if(customerRank != "")
				customerDTO.setGrade(customerRank);
			if(creditRank != "")
				customerDTO.setCredit(creditRank);
			//보증인은 보류
			
			System.out.println("customerName: " + customerName);
			System.out.println("phoneNumber: " + phoneNumber);
			System.out.println("address : " + address );
			System.out.println("identification : " + identification );
			System.out.println("country : " + country );
			System.out.println("jobCode : " + jobCode );			
			
			System.out.println("suretyName : " + suretyName );
			System.out.println("employeeName : " + employeeName );
			System.out.println("bankName  : " + bankName  );
			System.out.println("customerRank  : " + customerRank  );
			System.out.println("creditRank  : " + creditRank  );
			
						
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
				System.out.println("이자율111111: " +loanContractDTO.getInterestRate());
			}
			if(repaymentMethod != null)
				loanContractDTO.setPaymentMethod(repaymentMethod);
			if(gracePeriod != null)
				loanContractDTO.setGracePeriod(Integer.parseInt(gracePeriod));
			/*
			System.out.println("customerName: " + customerName);
			System.out.println("phoneNumber: " + phoneNumber);
			System.out.println("address : " + address );
			System.out.println("identification : " + identification );
			System.out.println("country : " + country );
			System.out.println("jobCode : " + jobCode );			
			
			System.out.println("suretyName : " + suretyName );
			System.out.println("employeeName : " + employeeName );
			System.out.println("bankName  : " + bankName  );
			System.out.println("customerRank  : " + customerRank  );
			System.out.println("creditRank  : " + creditRank  );
			
			System.out.println("loanType: " + loanType);
			System.out.println("loanProductName: " + loanProductName);
			System.out.println("collateral: " + collateral);
			System.out.println("loanAmount: " + loanAmount);
			System.out.println("interestRate: " + interestRate);
			System.out.println("repaymentMethod: " + repaymentMethod);
			*/
			
			int isLoanRegistered = loanService.subscriptionLoan(customerDTO, loanContractDTO);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/loan/productSubscription.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
