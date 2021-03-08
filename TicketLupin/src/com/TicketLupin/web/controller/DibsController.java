package com.TicketLupin.web.controller;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.DibsDao;
import com.TicketLupin.web.service.DibsListVo;

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
			int midx = 0;
			if(session.getAttribute("midx") != null) {
				midx = (int) session.getAttribute("midx");
			}
			
			String sidx_ = request.getParameter("sidx");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			System.out.println("midx_: " + midx);
			System.out.println("諛쏆븘�삤�뒗 媛�: " + sidx);
			
			DibsDao dd = new DibsDao();
			dd.insertDibs(sidx, midx);
			
			response.sendRedirect("../ConcertView/ConcertView.do?sidx=" + sidx);
			
		}else if(str.equals("/Dibs/DibsDeleteAction.do")) {
			
			HttpSession session = request.getSession();
			
			String sidx_ = request.getParameter("sidx");
			
			int midx = 0;
			if(session.getAttribute("midx") != null) {
				midx = (int) session.getAttribute("midx");
			}
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			System.out.println("midx_: " + midx);
			System.out.println("諛쏆븘�삤�뒗 媛�: " + sidx);
			
			DibsDao dd = new DibsDao();
			dd.deleteDibs(sidx, midx);
			
			response.sendRedirect("../ConcertView/ConcertView.do?sidx=" + sidx);
			
		}else if(str.equals("/Dibs/MyDibs.do")) {
			
			HttpSession session = request.getSession();
			
			String page_ = request.getParameter("page");
			
			int midx = 0;
			if(session.getAttribute("midx") != null) {
				midx = (int) session.getAttribute("midx");
			}
			
			int page = 1;
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			System.out.println("midx: " + midx);
			
			DibsDao dd = new DibsDao();
			ArrayList<DibsListVo> list = dd.getDibsList(midx, page);
			int count = dd.getDibsListCount(midx);
			
			System.out.println(count);
			System.out.println(list);
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myticket_dibs_list.jsp").forward(request, response);
			
		}
		
	};
	
}
