package com.TicketLupin.web.controller;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.ReservationDao;
import com.TicketLupin.web.service.ReservationShowVo;

@WebServlet("/MyticketController")
public class MyticketController extends HttpServlet{

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Myticket/MyticketMain.do")) {
			
			String page_ = request.getParameter("p");
			int page = 1;
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			ReservationDao rd = new ReservationDao();
			ArrayList<ReservationShowVo> list = rd.getReservationList(midx, page);
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myticket_main.jsp").forward(request, response);
			
		}else if(str.equals("/Myticket/MyticketReservation.do")) {
			
			String page_ = request.getParameter("p");
			
			int page = 1;
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			ReservationDao rd = new ReservationDao();
			ArrayList<ReservationShowVo> list = rd.getReservationList(midx, page);
			int count = rd.getReservationCount(midx);
			System.out.println("list Ȯ��: " + list);
			System.out.println("count Ȯ��: " + count);
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myticket_buy_list.jsp").forward(request, response);
			
		}else if(str.equals("/Myticket/MyticketDetail.do")) {
			
			String ridx_ = request.getParameter("ridx");
			int ridx = 0;
			if(ridx_ != null && !ridx_.equals("")) {
				ridx = Integer.parseInt(ridx_);
			}
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			ReservationDao rd = new ReservationDao();
			ReservationShowVo detail = rd.getReservationDetail(ridx, midx);
			int count = rd.getReservationCount(midx);
			System.out.println("detail Ȯ��: " + detail);
			System.out.println("count Ȯ��: " + count);
			
			request.setAttribute("detail", detail);
			request.setAttribute("count", count);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myticket_buy_detail.jsp").forward(request, response);
			
		}
		
	};
	
}
