package com.TicketLupin.web.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/ShowController")
public class ShowController extends HttpServlet{

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Show/ShowList.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_list.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowWrite.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_write_admin.jsp").forward(request, response);
			
		}
		
	}
	
}
