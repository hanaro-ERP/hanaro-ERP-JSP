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

import DTO.LoanSearchDTO;
import DTO.CustomerDTO;
import DTO.LoanProductDTO;
import Service.LoanService;

@WebServlet("/loanProduct/list")
public class LoanProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoanService loanService = new LoanService();

	public LoanProductListController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postLoanProductListProcess(request, response);
	}
	
	protected void postLoanProductListProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String type = request.getParameter("loanType");
			String name = request.getParameter("loanName");

			String[] jobs = request.getParameterValues("loanProductJob");
			String[] collaterals = request.getParameterValues("loanProductCollateral");
			String[] periods = request.getParameterValues("loanProductPeriod");
			String[] incomes = request.getParameterValues("loanProductIncome");
			String[] limits = request.getParameterValues("loanProductLimit");

			LoanSearchDTO loanSearchDTO = new LoanSearchDTO();
			
			if (type != null)
				loanSearchDTO.setType(type);
			if (name != null || name.equals(""))
				loanSearchDTO.setName(name);
			if (jobs != null)
				loanSearchDTO.setJobs(jobs);
			if (collaterals != null)
				loanSearchDTO.setCollaterals(collaterals);
			if (periods != null)
				loanSearchDTO.setPeriods(periods);
			if (incomes != null)
				loanSearchDTO.setIncomes(incomes);
			if (limits != null)
				loanSearchDTO.setLimits(limits);
			
			int pageNo = 1;
			String page = request.getParameter("page");
			if (page != null && !page.equals(""))
				pageNo = Integer.parseInt(page);

			int loanCount = loanService.getLoanCount(loanSearchDTO);
			loanSearchDTO.setCount(loanCount);
			loanSearchDTO.setPage(pageNo);
			System.out.println(loanCount);
			
			List<LoanProductDTO> loanList = loanService.getLoanProductList(loanSearchDTO, pageNo);
			
			request.setAttribute("loanProductInput", loanSearchDTO);
			request.setAttribute("loanProductList", loanList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/loan/loanProductList.jsp");
			dispatcher.forward(request, response);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
