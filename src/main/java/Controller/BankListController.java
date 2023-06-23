package Controller;

import java.io.*;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.BankDTO;
import Service.BankService;

@WebServlet("/bank/list")
public class BankListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BankListController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postBankListProcess(request, response);
	}
	
	protected void postBankListProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bankName = request.getParameter("bankName");
		String citySelect = request.getParameter("citySelect");
		String districtSelect = request.getParameter("district");
		String location = "";
		
		BankDTO bankDTO = new BankDTO();
		
		if(bankName != "")
			bankDTO.setBankName(bankName);
		if(citySelect != "" ) {
			bankDTO.setCity(citySelect);
			
			if(districtSelect != "") {
				location = citySelect + " " + districtSelect;
				bankDTO.setLocation(location);
				bankDTO.setDistrict(districtSelect);		
			}
		}

		try {
			List<BankDTO> getBankList = BankService.getBankList(bankDTO);
		
			request.setAttribute("searchInputValue", bankDTO);
			request.setAttribute("findBankList", getBankList);
		} catch (Exception e) {
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/employee/bankList.jsp");
		dispatcher.forward(request, response);
	}
}
