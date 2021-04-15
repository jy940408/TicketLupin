package com.TicketLupin.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.MemberDao;
import com.TicketLupin.web.service.MemberVo;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Member/MemberLogin.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Login.jsp").forward(request, response);
			
		}else if (str.equals("/Member/Memberlogout.do")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/");							
			
		}else if(str.equals("/Member/MemberJoin.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Join_step2.jsp").forward(request, response);
			
		}else if(str.equals("/Member/MemberIdCheck.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Find_ID_step1.jsp").forward(request, response);
			
		}else if(str.equals("/Member/MemberPwdCheck.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Find_pwd_step1.jsp").forward(request, response);
			
		}else if(str.equals("/Member/pwdChange.do")) {
			
			String mid = request.getParameter("mid");
			String mpwd = request.getParameter("mpwd");
			
			MemberDao md = new MemberDao();
			MemberVo mv = md.getMember(mid, mpwd);
			
			request.setAttribute("mv", mv);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Find_pwd_step3.jsp").forward(request, response);
			
		}else if(str.equals("/Member/joinIdCheck.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/ID_Check.jsp").forward(request, response);
			
		}else if(str.equals("/Member/findPwdAction.do")) {
			
			String mname = request.getParameter("mname");
			String mid = request.getParameter("mid");
			String memail = request.getParameter("memail");
			
			System.out.println("mname : "+mname);
			System.out.println("mid : "+mid);
			System.out.println("memail : "+memail);
			
			MemberDao md = new MemberDao();
			MemberVo mv = new MemberVo();
			
			try {
				md.findPwd(mid, memail, mname);
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Find_pwd_step2.jsp").forward(request, response);
			
		}else if(str.equals("/Member/pwdChangeAction.do")) {
			
			String mpwd = request.getParameter("mpwd");
			String mid = request.getParameter("mid");
			
			System.out.println("mpwd : "+mpwd);
			System.out.println("mid : "+mid);
			
			MemberDao md = new MemberDao();
			try {
				md.changePwd(mpwd, mid);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			//request.getRequestDispatcher("/WEB-INF/view/jsp/Main.jsp").forward(request, response);
			response.sendRedirect("../Main/MainPage.do");
		
		}else if(str.equals("/Member/Member_Modify_PwdCheck.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Check_user.jsp").forward(request, response);
			
		}else if(str.equals("/Member/UserDelete.do")) {
			
			String mid = request.getParameter("mid");
			
			request.setAttribute("mid", mid);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/jsp/Member_PwdCheck.jsp");
			rd.forward(request, response);
			
		}else if(str.equals("/Member/MemberLoginAction.do")) {
			
			String mid = request.getParameter("mid");
	        String mpwd = request.getParameter("mpwd");
	        
	        MemberDao md = new MemberDao();
	        int value_ = md.LoginCnt(mid, mpwd);
	        HttpSession session = request.getSession();
	        
	        if (value_ > 0) {
	        	
	        	MemberDao md_ = new MemberDao();
	        	ArrayList value = md_.memberLogin(mid, mpwd);
	            System.out.println(value);
	        	
	        	 if(value.get(0).equals("M") && value.get(2).equals("N")) {
	 	            
	 	            session.setAttribute("mid", mid);
	 	            session.setAttribute("mpwd", mpwd);
	 	            session.setAttribute("mgrade", value.get(0));
	 	            session.setAttribute("midx", value.get(1));
	 	            
	 	            response.sendRedirect("../Main/MainPage.do");
	 	            System.out.println("manager");
	 	             
	 	        }else if(value.get(0).equals("G") && value.get(2).equals("N")){
	 	        	session.setAttribute("mid", mid);
	 	            session.setAttribute("mpwd", mpwd);
	 	            session.setAttribute("mgrade", value.get(0));
	 	            session.setAttribute("midx", value.get(1));
	 	           
	 	           response.sendRedirect("../Main/MainPage.do");
	 	            System.out.println("user");
	 	           	
	 			}else if(value.get(2).equals("Y") ) {
					session.invalidate();
					request.setAttribute("err_message", "등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
					RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/jsp/Login.jsp");
		            dis.forward(request, response);
		            System.out.println("로그인안됨");
				}
	        }else {
	        	
	        	session.invalidate();
				request.setAttribute("err_message", "등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
				RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/jsp/Login.jsp");
	            dis.forward(request, response);
	            System.out.println("로그인안됨");
	        }	
			
			
		}else if(str.equals("/Member/MemberJoinAction.do")) {
			
			
			String mid = request.getParameter("mid");
			String mpwd = request.getParameter("mpwd");
			String mname = request.getParameter("mname");
			String maddress = request.getParameter("maddress");
			String memail = request.getParameter("memail");
			String mphone = request.getParameter("mphone");
			String mssn = request.getParameter("mssn");
			String mbirthmonth= request.getParameter("mbirthmonth");
			String mbirthday = request.getParameter("mbirthday");
			String mpostcode = request.getParameter("mpostcode");
			String mdetailaddress = request.getParameter("mdetailaddress");
			String mextraaddress = request.getParameter("mextraaddress");
			String mgender = request.getParameter("mgender");
			String msignindate = request.getParameter("msignindate");
			
			
			MemberDao md = new MemberDao();
			md.insertMember(mid, mpwd, mname, maddress, memail, mphone, mssn, mbirthmonth, mbirthday, mpostcode, mdetailaddress, mextraaddress, mgender, msignindate);
			
			response.sendRedirect(request.getContextPath()+"/");
		
		}else if(str.equals("/Member/findIdAction.do")) {
			
			String mname = request.getParameter("mname");
			String memail = request.getParameter("memail");
			String msecessionyn = request.getParameter("msecessionyn");
			
			System.out.println("mname:"+mname);
			System.out.println("memail:"+memail);
			
			MemberDao md = new MemberDao();
			MemberVo mv = new MemberVo();
			
			try {
				md.findId(mname, memail);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Find_Id.jsp").forward(request, response);
			
		}else if(str.equals("/Member/MemberModifyForm.do")) {
			
			String mid = request.getParameter("mid");
			
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberSelectOne2(mid);
			
			request.setAttribute("mv", mv);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/MemberModifyForm.jsp").forward(request, response);
		
		}else if(str.equals("/Member/MemberModifyAction.do")) {
			
			String mid = request.getParameter("mid");
			String mname = request.getParameter("mname");
			String mssn = request.getParameter("mssn");
			String mbirthmonth = request.getParameter("mbirthmonth");
			String mbirthday = request.getParameter("mbirthday");
			String mpostcode = request.getParameter("mpostcode");
			String maddress = request.getParameter("maddress");
			String mdetailaddress = request.getParameter("mdetailaddress");
			String mextraaddress = request.getParameter("mextraaddress");
			String mgender = request.getParameter("mgender");
			String memail = request.getParameter("memail");
			String mphone = request.getParameter("mphone");
			String mpwd = request.getParameter("mpwd");
			
			MemberDao md = new MemberDao();
			int value = md.MemberModify(mid, mpwd, mname, mssn, mbirthmonth, mbirthday, mpostcode, maddress, mdetailaddress, mextraaddress, mgender, memail, mphone);
			
			
			response.sendRedirect("../Main/MainPage.do");
			
		}else if(str.equals("/Member/UserDeleteAction.do")) {
			
			String mpwd = request.getParameter("mpwd");
			
			MemberDao md = new MemberDao();
			md.userDelete(mpwd);
			
			response.sendRedirect("../Main/MainPage.do");	
		}
		
	}
	
}
