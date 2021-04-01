package com.TicketLupin.web.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

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
			
						
			request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_user_list.jsp").forward(request, response);
			
			
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
			System.out.println("������ ��ȣ : "+midx2);
			
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
			ArrayList<MemberVo> list = ad.getUserBuyList(sidx2);
			
			
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
			
		}else if(str.equals("/Manager/Main.do")) {
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			
			ManagerDao ed = new ManagerDao();
			List<ManagerVo> mlist = ed.getmemberAMainList(); 
			List<ManagerVo> plist = ed.getPayAMainList();
			List<ManagerVo> clist = ed.getCommentAMainList();
			List<ManagerVo> qlist = ed.getQnaAMainList();
			String name = ed.getName(midx);
			request.setAttribute("mlist", mlist); 
			request.setAttribute("plist", plist); 
			request.setAttribute("clist", clist); 
			request.setAttribute("qlist", qlist); 
			request.setAttribute("name", name);
			
			
			
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_main.jsp").forward(request, response);
			
		}else if(str.equals("/Manager/comment.do")) {
			String page_ = request.getParameter("p");
			String query_ = request.getParameter("q");
			String setting_ = request.getParameter("s");

			String setting = "a.c_content";
				if (setting_ != null && !setting_.equals("")) {	
					setting = setting_;
				}
			
			String query ="";
			if(query_ != null && !query_.equals("")) {
				query =query_;
			}
			
			int page = 1;
			  
			  if (page_ != null && !page_.equals("")) { 
				  page = Integer.parseInt(page_);
			  }
			  
			  
			
			CommentADao cad =new CommentADao();
			List<CommentAVo> clist= cad.getCommentAList(setting,query,page);
			
			int count = cad.getCommentListCount(page,setting,query );
			request.setAttribute("clist", clist);
			request.setAttribute("count", count);
			  
			
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_comment_list.jsp").forward(request, response);
				
		} else if (str.equals("/Manager/CommentDeleteAction.do")) {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");

			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			CommentADao ca = new CommentADao();
			ca.deleteComment(c_idx);
			response.sendRedirect(request.getContextPath() + "/Manager/comment.do");
		
		} else if (str.equals("/Manager/CommentDeleteAllAction.do")) {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");

			String keyword = request.getParameter("keyword");
			CommentADao ca = new CommentADao();
			ca.deleteCommentall(keyword);
			response.sendRedirect(request.getContextPath() + "/Manager/comment.do");
		
		
		
		
		
		
		} else if (str.equals("/Manager/Memberlist.do")) {

			request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_user_list.jsp").forward(request, response);
				
		} else if (str.equals("/Manager/Paylist.do")) {

			request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_concert_list.jsp").forward(request, response);
				
				
		} else if (str.equals("/Manager/Qnalist.do")) {

			request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_qna_list.jsp").forward(request, response);
				
		}
		
				
		
	};
	
}
