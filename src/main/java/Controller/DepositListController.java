package Controller;

import java.io.*;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/depositList")
public class DepositListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public DepositListController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		postDepositListProcess(request, response);
	}
	
	protected void postDepositListProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String[] infos = {"customerName", "employeeName", "accountNumber", "depositType", "depositBalance"};
			
			for (int i = 0; i < infos.length; i++) {
			    String[] selected = request.getParameterValues(infos[i]);
			    if (selected != null) {
			        System.out.println(infos[i] + ": " + String.join(", ", selected));
			    }
			}
						
			response.sendRedirect(request.getContextPath() + "/view/deposit/depositProductList/depositProductList.jsp");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
