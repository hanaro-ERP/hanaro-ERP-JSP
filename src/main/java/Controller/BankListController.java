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
import DTO.CustomerDTO;
import Service.BankService;

@WebServlet("/bank/list")
public class BankListController extends HttpServlet {
	BankService bankService = new BankService();
	
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
		
		if(!bankName.equals(""))
			bankDTO.setBankName(bankName);
		if(!citySelect.equals("")) {
			bankDTO.setCity(citySelect);
			
			if(!districtSelect.equals("")) {
				location = citySelect + " " + districtSelect;
				bankDTO.setLocation(location);
				bankDTO.setDistrict(districtSelect);		
			}
		}

		int pageNo = 1;
		String page = request.getParameter("page");
		if (page != null && !page.equals(""))
			pageNo = Integer.parseInt(page);
		int bankCount = bankService.getBankCount(bankDTO);
		bankDTO.setCount(bankCount);
		bankDTO.setPage(pageNo);
		
		try {
			List<BankDTO> getBankList = bankService.getBankList(bankDTO, pageNo);
		
			request.setAttribute("searchInputValue", bankDTO);
			request.setAttribute("findBankList", getBankList);
		} catch (Exception e) {
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/employee/bankList.jsp");
		dispatcher.forward(request, response);
	}
}
