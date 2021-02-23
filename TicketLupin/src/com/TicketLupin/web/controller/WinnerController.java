package com.TicketLupin.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WinnerController")
public class WinnerController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Winner/WinnerList.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Winner_list.jsp").forward(request, response);
			
		}else if(str.equals("/Winner/WinnerDetail.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Winner_list.jsp").forward(request, response);
			
		}else if(str.equals("/Winner/WinnerWrite.do")) {
			
		}else if(str.equals("/Winner/WinnerWriteAction.do")) {
			
		}else if(str.equals("/Winner/WinnerModify.do")) {
			
		}else if(str.equals("/Winner/WinnerModifyAction.do")) {
			
		}else if(str.equals("/Winner/WinnerDeleteAction.do")) {
			
		}
		
	}
	
}
