package com.TicketLupin.web.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/MyticketController")
public class MyticketController extends HttpServlet{

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Myticket/MyticketMain.do")) {
			System.out.println("»Æ¿Œ");
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myticket_main.jsp").forward(request, response);
			
		}
		
	};
	
}
