package com.TicketLupin.web.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.DibsDao;

@WebServlet("/DibsController")
public class DibsController extends HttpServlet{

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Dibs/DibsAction.do")) {
			
			HttpSession session = request.getSession();
			int midx = (Integer)session.getAttribute("midx");
			
			String sidx_ = request.getParameter("sidx");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			System.out.println("midx_: " + midx);
			System.out.println("받아오는 값: " + sidx);
			
			DibsDao dd = new DibsDao();
			dd.insertDibs(sidx, midx);
			
			response.sendRedirect("../ConcertView/ConcertView.do?sidx=" + sidx);
			
		}
		
	};
	
}
