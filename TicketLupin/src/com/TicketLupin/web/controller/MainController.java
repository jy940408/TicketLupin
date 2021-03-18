package com.TicketLupin.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.TicketLupin.web.service.NewsDao;
import com.TicketLupin.web.service.NewsVo;
import com.TicketLupin.web.service.ShowDao;
import com.TicketLupin.web.service.ShowVo;

@WebServlet("/MainController")
public class MainController extends HttpServlet{

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Main/MainPage.do")) {
			
			String query = "";
			String ssetting = "sopendate";
			String nsetting = "wregdate";
			int page = 1;
			String array = "ASC";
			
			ShowDao sd = new ShowDao();
			ArrayList<ShowVo> showList = sd.getShowList(query, ssetting, array, page);
					
			NewsDao nd = new NewsDao();
			List<NewsVo> newsList = nd.getNewsList(query, nsetting, page);
			
			request.setAttribute("showList", showList);
			request.setAttribute("newsList", newsList);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Main.jsp").forward(request, response);
			
		}
		
	};
	
}
