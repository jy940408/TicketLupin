package com.TicketLupin.web.controller;

import java.io.IOException;
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

@WebServlet("/MemberController")
public class MemberController extends HttpServlet{

	private static final long serialVersionUID = 1L;

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
			
		}else if(str.equals("/Member/joinIdCheck.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/ID_Check.jsp").forward(request, response);
			
		}
		
	}
	
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
				RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/jsp/Main.jsp");
				dis.forward(request, response);
				System.out.println("로그인 성공 값 받기");
			}else if(value.get(0).equals("G")){
				session.setAttribute("mid", mid);
				session.setAttribute("mpwd", mpwd);
				session.setAttribute("mgrade", value.get(0));
				session.setAttribute("midx", value.get(1));
				RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/jsp/Main.jsp");
				dis.forward(request, response);
				System.out.println("로그인 성공 값 받기");
			}else {
				response.sendRedirect(request.getContextPath()+"/Member/MemberLogin.do");
			}
			
		}else if(str.equals("/Member/MemberJoinAction.do")) {
			
			
			String MID = request.getParameter("MID");
			String MPWD = request.getParameter("MPWD");
			String MNAME = request.getParameter("MNAME");
			String MADDRESS = request.getParameter("MADDRESS");
			String MEMAIL = request.getParameter("MEMAIL");
			String MPHONE = request.getParameter("MPHONE");
		
			MemberDao md = new MemberDao();
			md.insertMember(MID, MPWD, MNAME, MADDRESS, MEMAIL, MPHONE);
			
			response.sendRedirect(request.getContextPath()+"/");
		
		}else if(str.equals("/Member/findIdAction.do")) {
			
			String MNAME = request.getParameter("MNAME");
			String MEMAIL = request.getParameter("MEMAIL");
			
			System.out.println("MNAME:"+MNAME);
			System.out.println("MEMAIL:"+MEMAIL);
			
			MemberDao md = new MemberDao();
			try {
				md.findId(MNAME, MEMAIL);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
	}
	
}
