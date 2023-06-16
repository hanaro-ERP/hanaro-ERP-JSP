package Controller;

import java.io.*;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loanProductSearch")
public class loanProductSearch extends HttpServlet {
	public loanProductSearch() {
		super();
        // TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("100");
		getLoanProductListProcess(request, response);
		System.out.println("101");
	}
	
	protected void getLoanProductListProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String[] selectedJobs = request.getParameterValues("dynamicName");
            
			request.setAttribute("searchResults", selectedJobs);
			System.out.println(selectedJobs);
	        response.sendRedirect(request.getContextPath() + "/view/loan/loanProductList/loanProductList.jsp");
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
