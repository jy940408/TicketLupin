package com.TicketLupin.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NewsController")
public class NewsController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/News/NewsList.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/Ticketopen_list.jsp").forward(request, response);
			
		}else if(str.equals("/News/NewsDetail.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Ticketopen_view.jsp").forward(request, response);
			
		}else if(str.equals("/News/NewsWrite.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Ticketopen_write.jsp").forward(request, response);
			
		}else if(str.equals("/News/NewsWriteAction.do")) {
			
		}else if(str.equals("/News/NewsModify.do")) {
			
		}else if(str.equals("/News/NewsModifyAction.do")) {
			
		}else if(str.equals("/News/NewsDeleteAction.do")) {
			
		}
		
	}
	
}
