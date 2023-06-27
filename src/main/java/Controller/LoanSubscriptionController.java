package Controller;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.CustomerDTO;
import DTO.LoanContractDTO;
import DTO.RepaymentMethodDTO;
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
		
		System.out.println("CONTROLLER postLoanSubscriptionProcess ");
		
		
		
		CustomerUtil customerUtil = new CustomerUtil();
		try {
			String[] infos = {"customerName", "phoneNumber", "suretyName", "residentRegistrationNumber", "age", "gender", "country", "city", "district", "employeeName", "bank", "customerRank", "creditRank", "disalbitilityRank", "job", "loanType", "loanProductName", "collateral", "collateralValue", "loanAmount", "interest", "interestRate", "loanPerpose", "repaymentMethod"};
			
			//고객 정보
			CustomerDTO customerDTO = new CustomerDTO();
			
			String customerName = request.getParameter("customerName");
			String phoneNumber = request.getParameter("phoneNumber");
			String citySelect = request.getParameter("citySelect");
			String district = request.getParameter("district");
			String address = citySelect + " " + district;
			String id[] = request.getParameterValues("residentRegistrationNumber");			

			String identification = id[0] + "-" + id[1];
			int age = customerUtil.getAgeFromIdentification(id[0]);
			boolean gender = customerUtil.convertIntToGender(Integer.parseInt(id[1].substring(0,1)));

			String country = request.getParameter("country");
			String jobCode = request.getParameter("job");
			String suretyName = request.getParameter("suretyName");			
			String employeeName = request.getParameter("employeeName");
			String bankName = request.getParameter("bank"); //주거래지점
			String customerRank = request.getParameter("customerRank");
			String creditRank = request.getParameter("creditRank");
			
			if(customerName != "")
				customerDTO.setCustomerName(customerName);
			if(phoneNumber != "")
				customerDTO.setPhoneNumber(phoneNumber);
			if(citySelect != "")
				customerDTO.setAddress(address);
			if(identification != "") {
				customerDTO.setIdentification(identification);
				customerDTO.setAge(age);
				customerDTO.setGender(gender);
			}
			if(customerName != "")
				customerDTO.setBankName(customerName);
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
						
			//상품정보
			LoanContractDTO loanContractDTO = new LoanContractDTO();

			String loanType = request.getParameter("loanType");
			String loanProductName = request.getParameter("loanProductName");
			String collateral = request.getParameter("collateral");
			String loanAmount = request.getParameter("loanAmount");
			String interestRate = request.getParameter("interestRate");
			String repaymentMethod = request.getParameter("repaymentMethod");
			
			if(loanType != null)
				loanContractDTO.setLoanType(loanType);
			if(loanProductName != null)
				loanContractDTO.setLoanName(loanProductName);
			if(loanAmount != null) {
				loanContractDTO.setLoanAmount(Integer.parseInt(loanAmount+"0000"));
				loanContractDTO.setBalance(Integer.parseInt(loanAmount+"0000")); //대출 잔금
			}
			if(interestRate != null) 
				loanContractDTO.setInterestRate(Integer.parseInt(interestRate));
			if(repaymentMethod != null)
				loanContractDTO.setPaymentMethod(repaymentMethod);
			
			int isLoanRegistered = loanService.subscriptionLoan(customerDTO, loanContractDTO);
			
			
			
			
			
			System.out.println("CONTROLLER id = "+ id[0]);
			List<RepaymentMethodDTO> repaymentMethodDTOList = loanService.getRepaymentMethod(id);
			request.setAttribute("repaymentMethod", repaymentMethodDTOList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/loan/productSubscription.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}