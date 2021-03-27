package com.TicketLupin.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
			
			String mid2 = request.getParameter("mid");
			
			MemberDao md = new MemberDao();
			MemberVo mv = md.getMember(mid2);
			
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Main.jsp").forward(request, response);
		
		}
		
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);	
		
		
		if(str.equals("/Member/MemberLoginAction.do")) {
			
			String mid = request.getParameter("mid");
	        String mpwd = request.getParameter("mpwd");
	         
	        System.out.println("mid:"+mid);
	        System.out.println("mpwd:"+mpwd);
	         
	        MemberDao md = new MemberDao();
	        ArrayList value = md.memberLogin(mid, mpwd);
	        System.out.println("value:"+value);
	         
	        HttpSession session = request.getSession();
	         
	        if(value.get(0).equals("M")) {
	             
	            session.setAttribute("mid", mid);
	            session.setAttribute("mpwd", mpwd);
	            session.setAttribute("mgrade", value.get(0));
	            session.setAttribute("midx", value.get(1));
	            response.sendRedirect("../Main/MainPage.do");
				System.out.println("로그인 성공 값 받기");
	             
	        }else if(value.get(0).equals("G")){
	        	session.setAttribute("mid", mid);
	            session.setAttribute("mpwd", mpwd);
	            session.setAttribute("mgrade", value.get(0));
	            session.setAttribute("midx", value.get(1));
	            response.sendRedirect("../Main/MainPage.do");
				System.out.println("로그인 성공 값 받기");
	        }else {
	            response.sendRedirect(request.getContextPath()+"/Member/MemberLogin.do");
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
		
			MemberDao md = new MemberDao();
			md.insertMember(mid, mpwd, mname, maddress, memail, mphone, mssn, mbirthmonth, mbirthday);
			
			
			response.sendRedirect(request.getContextPath()+"/");
		
		}else if(str.equals("/Member/findIdAction.do")) {
			
			String mname = request.getParameter("mname");
			String memail = request.getParameter("memail");
			
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
			
		}
	}
	
}
