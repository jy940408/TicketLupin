package com.TicketLupin.web.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.DibsDao;
import com.TicketLupin.web.service.ShowDao;
import com.TicketLupin.web.service.ShowVo;

@WebServlet("/ConcertViewController")
public class ConcertViewController extends HttpServlet{

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/ConcertView/ConcertView.do")) {
			
			
			HttpSession session = request.getSession();
			
			int midx = (Integer)session.getAttribute("midx");
			String sidx_ = request.getParameter("sidx");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			ShowDao sd = new ShowDao();
			ShowVo detail = sd.getShowDetail(sidx);
			
			DibsDao dd = new DibsDao();
			int didx = dd.getDibsCheck(sidx, midx);
			
			request.setAttribute("detail", detail);
			request.setAttribute("didx", didx);
			
			System.out.println("공연 디테일 테스트: " + detail);
			System.out.println("didx 테스트: " + didx);
			System.out.println("midx 세션 값: " + midx);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_View.jsp").forward(request, response);
			
		}
		
	};
	
}
