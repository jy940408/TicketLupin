package com.TicketLupin.web.controller;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.TicketLupin.web.service.MemberDao;
import com.TicketLupin.web.service.MemberVo;

@WebServlet("/ManagerController")
public class ManagerController extends HttpServlet{

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Admin/AdminMain.do")) {
			
			
			MemberDao md = new MemberDao();
			ArrayList<MemberVo> memberList = md.getMemberList();
			
			request.setAttribute("memberList", memberList);
			System.out.println(memberList);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_main.jsp").forward(request, response);
			
		}else if(str.equals("/Admin/AdminMember.do")) {
			
			
			MemberDao md = new MemberDao();
			ArrayList<MemberVo> memberList = md.getMemberList();
			
			request.setAttribute("memberList", memberList);
			System.out.println(memberList);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_user_list.jsp").forward(request, response);
			
		}
		
	};
	
}
