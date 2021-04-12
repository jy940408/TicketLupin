package com.TicketLupin.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.MemberVo;
import com.TicketLupin.web.service.MyticketDao;
import com.TicketLupin.web.service.MyticketVo;
import com.TicketLupin.web.service.QuestionVo;
import com.TicketLupin.web.service.ReservationDao;
import com.TicketLupin.web.service.ReservationListVo;
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
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
					
			MyticketDao md = new MyticketDao();
			List<QuestionVo> qlist = md.getQuestionList(midx);
			List<ReservationShowVo> rlist = md.getReservationList(midx);
			MemberVo mv = md.getName(midx);
			int count = md.getReservationCount(midx);
			
			request.setAttribute("rlist", rlist);
			request.setAttribute("qlist", qlist);
			request.setAttribute("count", count);
			request.setAttribute("mv", mv);		
			
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
			ArrayList<ReservationListVo> list = rd.getReservationIdxList(midx, page);
			int count = rd.getReservationCount(midx);
			
			ArrayList<ReservationListVo> dList = rd.getReservationIdxDelList(midx, page);
			int dCount = rd.getDelReservationCount(midx);
			
			
			System.out.println("list Ȯ��: " + list);
			System.out.println("count Ȯ��: " + count);
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("dList", dList);
			request.setAttribute("dCount", dCount);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myticket_buy_list.jsp").forward(request, response);
		
		}else if(str.equals("/Myticket/MyticketDetail.do")) {
			
			String riidx_ = request.getParameter("riidx");
			int riidx = 0;
			if(riidx_ != null && !riidx_.equals("")) {
				riidx = Integer.parseInt(riidx_);
			}
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			ReservationDao rd = new ReservationDao();
			ArrayList<ReservationShowVo> detail = rd.getReservationDetail(midx, riidx);
			System.out.println("detail Ȯ��: " + detail);
			
			request.setAttribute("detail", detail);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myticket_buy_detail.jsp").forward(request, response);
			
		}else if(str.equals("/Myticket/MyticketCancelDetail.do")) {
			
			String riidx_ = request.getParameter("riidx");
			int riidx = 0;
			if(riidx_ != null && !riidx_.equals("")) {
				riidx = Integer.parseInt(riidx_);
			}
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			System.out.println("ridx: " + riidx);
			System.out.println("midx: " + midx);
			
			ReservationDao rd = new ReservationDao();
			ArrayList<ReservationShowVo> detail = rd.getReservationDetail(midx, riidx);
			System.out.println("detail Ȯ��: " + detail);
			
			request.setAttribute("detail", detail);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myticket_cancel_detail.jsp").forward(request, response);
			
		}
		
	}
	
}
