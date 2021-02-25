package com.TicketLupin.web.controller;

import java.io.IOException;

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

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str get: "+str);
		
		if(str.equals("/Member/MemberLogin.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Login.jsp").forward(request, response);
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str post: "+str);	
		
		if(str.equals("/Member/MemberLoginAction.do")) {
		
			String mid = request.getParameter("mid");
			String mpwd = request.getParameter("mpwd");
			System.out.println("mid:"+mid);
			System.out.println("mpwd:"+mpwd);
			
			MemberDao md = new MemberDao();
			int value = md.memberLogin(mid, mpwd);
			System.out.println("value:"+value);
			
			HttpSession session = request.getSession();
			
			if(value == 1 ) {
				
				System.out.println("¼º°ø");
				session.setAttribute("mid", mid);
				session.setAttribute("mpwd", mpwd);
				String loginId = (String)session.getAttribute("mid");
				System.out.println(loginId);
				RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/jsp/Main.jsp");
				dis.forward(request, response);
				
				
			}else {
				
				response.sendRedirect(request.getContextPath()+"/Member/Memberlogin.do");
				
				
			}
			
		}
	}
	
}
