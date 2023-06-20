package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class LinkController
 */
@WebServlet("/LinkController")
public class LinkController extends HttpServlet {
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		requestPro(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		requestPro(request, response);
	}
	
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* URL check */
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		String command = uri.substring(context.length());
		String site = null;
		
		System.out.println("command : "+command);
		
		switch(command) {
		case "/":
			site = "index.jsp";
			break;
			
		case "/view/customerList.do" : 
			site = "customerList.jsp";
			break;
			
		case "/view/empList.do":
			site = "empList.jsp";
			break;
			
		case "/view/loanProductList.do" : 
			site = "loanProductList.jsp";
			break;
			
		case "/view/depositProductList.do" :
			site = "depositProductList.jsp";
			break;
			
		default : break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(site);
		dispatcher.forward(request, response);
	}
}
