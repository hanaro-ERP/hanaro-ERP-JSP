package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.CustomerDTO;
import Service.CustomerService;
import util.CustomerUtil;
import util.EncryptUtil;

@WebServlet("/customer/register")
public class CustomerRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService customerService = new CustomerService();
	
	public CustomerRegisterController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postCustomerRegistProcess(request, response);
	}
	
	protected void postCustomerRegistProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerUtil customerUtil = new CustomerUtil();
		try {
			String[] infos = {"customerName", "phoneNumber", "suretyName", "residentRegistrationNumber", "age", "gender", "country", "city", "district", "employeeName", "bank", "customerRank", "creditRank", "disalbitilityRank", "job", "loanType", "loanProductName", "collateral", "collateralValue", "loanAmount", "interest", "interestRate", "loanPerpose", "repaymentMethod"};
			EncryptUtil encryptUtil = new EncryptUtil();
			//String encrypted = encryptUtil.encrypt(string);
			
			//고객 정보
			CustomerDTO customerDTO = new CustomerDTO();

			String customerName = request.getParameter("customerName");
			String phoneNumber = request.getParameter("phoneNumber");
			String citySelect = request.getParameter("citySelect");
			String district = request.getParameter("district");
			String address = citySelect + " " + district;

			String userId1 = request.getParameter("userIdentification1");
			String userId2 = request.getParameter("userIdentification2");
			
			phoneNumber = phoneNumber.substring(0,3) + "-" + phoneNumber.substring(3,7) + "-" + phoneNumber.substring(7,11);

			String identification = userId1 + "-" + userId2;
			int age = customerUtil.getAgeFromIdentification(userId1);
			boolean gender = customerUtil.convertIntToGender(Integer.parseInt(userId2.substring(0,1)));
			
			String country = request.getParameter("country");
			String jobCode = request.getParameter("job");
			String guarantor = request.getParameter("guarantor");			
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
			if(guarantor != "")
				customerDTO.setGuarantor(guarantor);
			if(jobCode != "")
				customerDTO.setJobCode(jobCode);
			if(country != "")
				customerDTO.setCountry(country);
			if(employeeName != "")
				customerDTO.setEmployeeName(employeeName);
			if(bankName != "")
				customerDTO.setBankName(bankName);
			if(customerRank != "")
				customerDTO.setGrade(customerRank);
			if(creditRank != "")
				customerDTO.setCredit(creditRank);
		
			int isCustomerRegistered = customerService.registerCustomer(customerDTO);
			
			request.setAttribute("customerDTO", customerDTO);
			
			response.sendRedirect(request.getContextPath() + "/navigation/customer?mod=" + isCustomerRegistered);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
