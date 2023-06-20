package Controller;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.LoanContractDAO;
import DTO.LoanContractDTO;

@WebServlet("/loanContractList")
public class LoanContractController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LoanContractController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postLoanContractProcess(request, response);
	}
	
	LoanContractDTO loanContractDTO = new LoanContractDTO();
	LoanContractDAO loanContractDAO = new LoanContractDAO();
	
	protected void postLoanContractProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String[] infos = {"productName", "loanType", "customerName", "employeeName", "loanContractStartDate", "loanContractEndDate", "balanceList", "loanContractLimit"};
			
			for (int i = 0; i < infos.length; i++) {
			    String[] selectedInfos = request.getParameterValues(infos[i]);
			    if (selectedInfos != null) {
			        System.out.println(infos[i] + ": " + String.join(", ", selectedInfos));
			    }
			}
			
			// 데이터 가져오기
	        List<LoanContractDTO> loanContracts = loanContractDAO.getLoanContracts();
	        
	        // 가져온 데이터를 request에 저장
	        request.setAttribute("loanContracts", loanContracts);	        
	        
	        // JSP 페이지로 forward
	        request.getRequestDispatcher("/view/loan/loanContract/loanContractList.jsp").forward(request, response);
	        
	        
			// response.sendRedirect(request.getContextPath() + "/view/loan/loanContract/loanContractList.jsp");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
