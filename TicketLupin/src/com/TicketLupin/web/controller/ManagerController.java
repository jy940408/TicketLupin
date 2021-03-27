package com.TicketLupin.web.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.TicketLupin.web.service.*;

import domain.PageMaker;
import domain.SearchCriteria;

@WebServlet("/ManagerController")
public class ManagerController extends HttpServlet{

	

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Manager/MemberList.do")) {
			
			String page = request.getParameter("page");
			if (page==null) page = "1";
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if (keyword ==null) keyword ="";
			
			String searchType = request.getParameter("searchType");
			if (searchType == null) searchType = "";
			
			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setKeyword(keyword);
			scri.setSearchType(searchType);
			
			PageMaker pm = new PageMaker();
			pm.setScri(scri);
			
			MemberDao md = new MemberDao();
			int cnt = md.memberTotal(keyword);
			
			pm.setTotalCount(cnt);
			
			ArrayList<MemberVo> alist = md.memberSelectAll(scri);
			
			request.setAttribute("alist", alist);
			request.setAttribute("pm", pm);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_user_list.jsp");
			rd.forward(request, response);
			
		}else if(str.equals("/Manager/Memberinfo.do")) {
			
			String midx = request.getParameter("midx");
			int midx2 = Integer.parseInt(midx);
			
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberSelectOne(midx2);
			
			request.setAttribute("mv", mv);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_user_view.jsp");
			rd.forward(request, response);
		
		}else if(str.equals("/Manager/MemberDeleteAction.do")) {
			
			String midx2 = request.getParameter("midx");
			System.out.println("삭제된 번호 : "+midx2);
			
			int midx = 0 ;
			if(midx2 != null && !midx2.equals("")) {
				midx = Integer.parseInt(midx2); 
			}
			
			MemberDao md = new MemberDao();
			md.MemberDelete(midx);
		
			response.sendRedirect(request.getContextPath()+"/Manager/MemberList.do");
			
		}else if(str.equals("/Manager/UserBuyList.do")) {
			
			String midx = request.getParameter("midx");
			int midx2 = Integer.parseInt(midx);
			
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberSelectOne(midx2);
			
			
			AdminDao ad = new AdminDao();
			ArrayList<ReservationVo> alist = ad.UserReservationList(midx2);
			
			request.setAttribute("mv", mv);
			request.setAttribute("alist", alist);
			
			System.out.println(midx2);
			
			RequestDispatcher rqd = request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_user_buy_list.jsp");
			rqd.forward(request, response);
		
		}else if(str.equals("/Manager/UserQnaList.do")) {
			
			String midx = request.getParameter("midx");
			int midx2 = Integer.parseInt(midx);
			
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberSelectOne(midx2);
			
			AdminDao ad = new AdminDao();
			ArrayList<FaqVo> alist = ad.UserQnaList(midx2);
			
			request.setAttribute("mv", mv);
			request.setAttribute("alist", alist);
			
			System.out.println("mv : "+mv);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_user_qna_list.jsp").forward(request, response);
			
		}else if(str.equals("/Manager/ConcertList.do")) {
			
			String query_ = request.getParameter("q");
			
			
			String query = "";
			if(query_ != null && !query_.equals("")) {
				query = query_;
			}
			
			
			AdminDao ad = new AdminDao();
			ArrayList<Show1Vo> alist = ad.ShowSelectAll(query);
			
			
			request.setAttribute("alist", alist);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_concert_list.jsp").forward(request, response);
		
			
			
		}else if(str.equals("/Manager/UserList.do")) {
			
			String sidx = request.getParameter("sidx");
			int sidx2 = Integer.parseInt(sidx);
			
			System.out.println("sidx : "+sidx);
			
			JSONArray arr = new JSONArray();
			JSONObject obj = new JSONObject();
			
			AdminDao ad = new AdminDao();
			ArrayList<MemberVo> list = ad.getUserList(sidx2);
			
			System.out.println("list : "+list);
			
			for(int i=0; i < list.size(); i++) {
				JSONObject jobj = new JSONObject();
				jobj.put("ridx", list.get(i).getRidx());
				jobj.put("mname", list.get(i).getMname());
				jobj.put("mid", list.get(i).getMid());
				jobj.put("sidx", list.get(i).getSidx());
				jobj.put("midx", list.get(i).getMidx());
				arr.add(jobj);
				
	
			}
			
			obj.put("list", arr);
			System.out.println(arr);
			
			
//			PrintWriter out = response.getWriter();
//			out.println("{\"ridx\":\"1\"}");
			
			
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(arr);
		
		}else if(str.equals("/Manager/UserCommentList.do")) {
			
			String midx = request.getParameter("midx");
			int midx2 = Integer.parseInt(midx);
			
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberSelectOne(midx2);
			
			AdminDao ad = new AdminDao();
			ArrayList<C_commentVo> alist = ad.UserCommentList(midx2);
			
			request.setAttribute("mv", mv);
			request.setAttribute("alist", alist);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_user_comment_list.jsp").forward(request, response);
			
		}
		
	};
	
}
