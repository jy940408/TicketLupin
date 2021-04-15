package com.TicketLupin.web.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
	         
	         System.out.println("alist : "+ alist);
	         System.out.println("cnt : "+cnt);
	         
	         
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
	         
	         String page = request.getParameter("page");
	         int page2 = 1;
	         if(page != null && !page.equals("")) {
	            page2 = Integer.parseInt(page);
	         }
	         
	         MemberDao md = new MemberDao();
	         MemberVo mv = md.memberSelectOne(midx2);
	         
	         AdminDao ad = new AdminDao();
	         ArrayList<ReservationVo> alist = ad.UserReservationList(midx2, page2);
	         int count = ad.getUserBuyListCount(midx2);
	         
	         request.setAttribute("count", count);
	         request.setAttribute("mv", mv);
	         request.setAttribute("alist", alist);
	         
	         RequestDispatcher rqd = request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_user_buy_list.jsp");
	         rqd.forward(request, response);
	      
	      }else if(str.equals("/Manager/UserBuyDelete.do")) {
	         
	         String ridx = request.getParameter("ridx");
	         int ridx2 = Integer.parseInt(ridx);
	         
	         AdminDao ad = new AdminDao();
	         ad.UserBuyDelete(ridx2);
	         
	      }else if(str.equals("/Manager/UserQnaList.do")) {
	         
	         String midx = request.getParameter("midx");
	         int midx2 = Integer.parseInt(midx);
	         
	         String page = request.getParameter("page");
	         int page2 = 1;
	         if(page != null && !page.equals("")) {
	            page2 = Integer.parseInt(page);
	         }
	         
	         MemberDao md = new MemberDao();
	         MemberVo mv = md.memberSelectOne(midx2);
	         
	         AdminDao ad = new AdminDao();
	         ArrayList<FaqVo> alist = ad.UserQnaList(midx2, page2);
	         
	         request.setAttribute("mv", mv);
	         request.setAttribute("alist", alist);
	         
	         
	         request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_user_qna_list.jsp").forward(request, response);
	         
	      }else if(str.equals("/Manager/ConcertList.do")) {
	         
	         String query_ = request.getParameter("q");
	         
	         
	         String query = "";
	         if(query_ != null && !query_.equals("")) {
	            query = query_;
	         }
	         
	         
	         String page_ = request.getParameter("page");
	         
	         if(page_ == null) page_ = "0";
	         int page = Integer.parseInt(page_);
	         
	         if(page == 0) {
	            
	            AdminDao ad = new AdminDao();
	            ArrayList<Show1Vo> alist = ad.ShowSelectAll(query);
	            
	            request.setAttribute("alist", alist);
	            request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_concert_list.jsp").forward(request, response);
	            
	         }else if(page > 0){
	            
	            
	            HttpSession session = request.getSession();
	            
	            String sidx2 = request.getParameter("sidx");
	            int sidx = Integer.parseInt(sidx2);
	            
	            
	            AdminDao ad = new AdminDao();
	            ArrayList<Show1Vo> alist = ad.ShowSelectAll(query);
	            ArrayList<MemberVo> list = ad.getUserBuyList(sidx, page);
	            int count = ad.getBuyListCount(sidx);
	            
	            Show1Vo sv = ad.getShowImg(sidx);
	            
	            
	            
	            request.setAttribute("alist", alist);
	            request.setAttribute("list", list);
	            request.setAttribute("count", count);
	            request.setAttribute("sv", sv);
	            
	            request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_concert_list.jsp").forward(request, response);
	            
	         }
	         
	         
	      }else if(str.equals("/Manager/UserList.do")) {
	//ajax         
	         HttpSession session = request.getSession();
	         
	         String sidx2 = request.getParameter("sidx");
	         int sidx = Integer.parseInt(sidx2);
	         
	         String page2 = request.getParameter("page");
	         if(page2 == null) page2 = "1";
	         int page = Integer.parseInt(page2);
	         
	         JSONArray arr = new JSONArray();
	         JSONObject obj = new JSONObject();
	         
	         AdminDao ad = new AdminDao();
	         ArrayList<MemberVo> list = ad.getUserBuyList(sidx, page);
	         int count = ad.getBuyListCount(sidx);
	         
	         request.setAttribute("count", count);
	         request.setAttribute("list", list);
	         
	         System.out.println("count : "+count);
	         System.out.println("sidx : "+ sidx);
	         
	         
	         for(int i=0; i < list.size(); i++) {
	            JSONObject jobj = new JSONObject();
	            jobj.put("num", list.get(i).getNum());
	            jobj.put("riidx", list.get(i).getRidx());
	            jobj.put("mname", list.get(i).getMname());
	            jobj.put("mid", list.get(i).getMid());
	            jobj.put("sidx", list.get(i).getSidx());
	            jobj.put("midx", list.get(i).getMidx());
	            jobj.put("count", count);
	            arr.add(jobj);
	         }
	         obj.put("list", arr);
	         
	         
	         JSONObject jobjP = new JSONObject();
	         jobjP.put("page", (page == 0)?1:page);
	         jobjP.put("startNum", 1+(page-1)*10);
	         jobjP.put("lastNum", Math.ceil(count/10.0));
	         jobjP.put("next", page+1);   
	         jobjP.put("prev", page-1);
	         jobjP.put("sidx", sidx);
	         jobjP.put("count", count);
	         arr.add(jobjP);
	         
	         
	         
	         response.setContentType("application/x-json; charset=UTF-8");
	         response.getWriter().print(arr);
	         
	      
	      }else if(str.equals("/ConcertView/ConcertView.do")) {
	         
	         String sidx = request.getParameter("sidx");
	         int sidx2 = Integer.parseInt(sidx);
	         
	         ShowDao sd = new ShowDao();
	         Show1Vo s1v = sd.getShowDetail(sidx2);
	         
	         request.setAttribute("s1v", s1v);
	         
	         request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_View.jsp").forward(request, response);
	         
	      }else if(str.equals("/Manager/UserCommentList.do")) {
	         
	         String midx = request.getParameter("midx");
	         int midx2 = Integer.parseInt(midx);
	         
	         String page = request.getParameter("page");
	         int page2 = 1;
	         if(page != null && !page.equals("")) {
	            page2 = Integer.parseInt(page);
	         }
	         
	         MemberDao md = new MemberDao();
	         MemberVo mv = md.memberSelectOne(midx2);
	         
	         AdminDao ad = new AdminDao();
	         ArrayList<CommentAVo> alist = ad.UserCommentList(midx2, page2);
	         String c_sort = request.getParameter("c_sort");
	         
	         request.setAttribute("c_sort", c_sort);
	         request.setAttribute("mv", mv);
	         request.setAttribute("alist", alist);
	         
	         request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_user_comment_list.jsp").forward(request, response);
	         
	      }else if(str.equals("/Manager/UserCommentDelete.do")) {
	         
	         String midx = request.getParameter("midx");
	         int midx2 = Integer.parseInt(midx);
	         
	         String c_idx2 = request.getParameter("c_idx");
	         
	         int c_idx = 0;
	         if(c_idx2 != null && !c_idx2.equals("")) {
	            c_idx = Integer.parseInt(c_idx2);
	         }
	         
	         AdminDao ad = new AdminDao();
	         ad.UserCommentDelete(midx2, c_idx);
	         

			}else if(str.equals("/Manager/Main.do")) {
				HttpSession session = request.getSession();
				int midx = (int)session.getAttribute("midx");
				
				ManagerDao ed = new ManagerDao();
				List<ManagerVo> mlist = ed.getmemberAMainList(); 
				List<ManagerVo> plist = ed.getPayAMainList();
				List<ManagerVo> clist = ed.getCommentAMainList();
				List<ManagerVo> qlist = ed.getQnaAMainList();
				String name = ed.getName(midx);
				int count = ed.UnansweredCount();
				int rcount = ed.ReportCount();
				request.setAttribute("mlist", mlist); 
				request.setAttribute("plist", plist); 
				request.setAttribute("clist", clist); 
				request.setAttribute("qlist", qlist); 
				request.setAttribute("name", name);
				request.setAttribute("count", count);
				request.setAttribute("rcount", rcount);
				
				
				request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_main.jsp").forward(request, response);
				
			}else if(str.equals("/Manager/comment.do")) {
				String page_ = request.getParameter("p");
				String query_ = request.getParameter("q");
				String setting_ = request.getParameter("s");
				String od = request.getParameter("od");
				String setting = "a.c_content";
					if (setting_ != null && !setting_.equals("")) {	
						setting = setting_;
					}

					if (od == ""||od == null)
						od = "";
		
				String query ="";
				if(query_ != null && !query_.equals("")) {
					query =query_;
				}
				
				int page = 1;
				  
				  if (page_ != null && !page_.equals("")) { 
					  page = Integer.parseInt(page_);
				  }
				String order="c_idx";
				String delyn="N";
				if (od.equals("latest")) {
					order = "c_idx";
					delyn="N";
				}else if (od.equals("report")) {
					order = "rcnt";
					delyn="N";
				}else if (od.equals("delete")) {
					order = "c_idx";
					delyn = "Y";
				}  
			  
			
				CommentADao cad =new CommentADao();
				List<CommentAVo> clist= cad.getCommentAList(setting,query,page,order,delyn);
				
				int count = cad.getCommentListCount(page,setting,query,delyn );
				request.setAttribute("clist", clist);
				request.setAttribute("count", count);
				  
				
				
				request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_comment_list.jsp").forward(request, response);
					
			} else if (str.equals("/Manager/CommentDeleteAction.do")) {

				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				request.setCharacterEncoding("UTF-8");
				int c_idx = Integer.parseInt(request.getParameter("c_idx"));
				
				String delyn = request.getParameter("delyn");
				String od = request.getParameter("od");
				
				CommentADao ca = new CommentADao();
				ca.deleteComment(delyn,c_idx);
				response.sendRedirect(request.getContextPath() + "/Manager/comment.do?od="+od);
				
					
			} else if (str.equals("/Manager/CommentView.do")) {
				String page_ = request.getParameter("p");
				int c_idx = Integer.parseInt(request.getParameter("c_idx")); 
				
				
				
				int page = 1;
				  
				  if (page_ != null && !page_.equals("")) { 
					  page = Integer.parseInt(page_);
				  }
				
				
				
				CommentADao cad = new CommentADao();
				CommentAVo test = cad.getCommentView(c_idx);
				List<CommentAVo> list = cad.getReportList(c_idx,page);
				int count =cad.getReportListCount(c_idx);
				
				request.setAttribute("count", count);
				request.setAttribute("list", list);
				request.setAttribute("test", test);
				request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_comment_view.jsp").forward(request, response);
			}else if(str.equals("/Manager/CommentCheckUpdate.do")) {	


				//파라미터 배열로 받기
				String[] arrayValue = request.getParameterValues("c_idx");
				
			     for (int i = 0; i < arrayValue.length; i++) {

			    	 System.out.println("arrayValue " + arrayValue[i]);
			      }
				
			     String checked = Arrays.toString(arrayValue);
			     String	checked2 = checked.replace("[", "");
			     String	checked3 = checked2.replace("]", "");
			    System.out.println("checked->"+checked3);
				CommentADao ca = new CommentADao();
				ca.deleteCheckComment(checked3);
				response.sendRedirect(request.getContextPath() + "/Manager/comment.do");
				
			}else if(str.equals("/Manager/ReportCheckUpdate.do")) {	

				String referer = (String)request.getHeader("REFERER");	
				//파라미터 배열로 받기
				String[] arrayValue = request.getParameterValues("c_idx");
				
			     for (int i = 0; i < arrayValue.length; i++) {

			    	 System.out.println("arrayValue " + arrayValue[i]);
			      }
				
			     String checked = Arrays.toString(arrayValue);
			     String	checked2 = checked.replace("[", "");
			     String	checked3 = checked2.replace("]", "");
			    System.out.println("checked->"+checked3);
				CommentADao ca = new CommentADao();
				ca.deleteCheckReport(checked3);
				response.sendRedirect(referer);
				
			}		
		
	};
	
}
				
				