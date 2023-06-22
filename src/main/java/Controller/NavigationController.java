package Controller;

import java.io.*;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NavigationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public NavigationController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		getNavigationProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

	}
	
	protected void getNavigationProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String uri = request.getRequestURI();
			String goTo = "";
			System.out.println(uri);
			if (uri.equals("/hanaro-ERP-JSP/")) {
				goTo = "/login/login.jsp";
			} else if (uri == null || uri.equals("/hanaro-ERP-JSP/navigation/login")) {
				goTo = "/login/login.jsp";
			} else if (uri.equals("/hanaro-ERP-JSP/navigation/main")) {
				goTo = "/main/main.jsp";
			} else if (uri.equals("/hanaro-ERP-JSP/navigation/customer")) {
				goTo = "/customer/customerList.jsp";
			} else if (uri.equals("/hanaro-ERP-JSP/navigation/employee")) {
				goTo = "/employee/employeeList.jsp";
			} else if (uri.equals("/hanaro-ERP-JSP/navigation/loan")) {
				goTo = "/loan/loanProductList.jsp";
			} else if (uri.equals("/hanaro-ERP-JSP/navigation/deposit")) {
				goTo = "/deposit/depositProductList.jsp";
			} else {
				goTo = "/main/main.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view" + goTo);
			dispatcher.forward(request, response);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
