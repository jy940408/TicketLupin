package com.TicketLupin.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.TicketLupin.web.service.NewsDao;
import com.TicketLupin.web.service.NewsVo;
import com.TicketLupin.web.service.ShowDao;
import com.TicketLupin.web.service.ShowRankingVo;
import com.TicketLupin.web.service.Show1Vo;

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
			
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
			Calendar start = Calendar.getInstance();
			String startdate = "0000-00-00";
			startdate = formatter.format(start.getTime());
			System.out.println("startdate 테스트: " + startdate);
			
			ShowDao sd = new ShowDao();
			ArrayList<Show1Vo> showList = sd.getShowList(query, ssetting, array, page);
			ArrayList<ShowRankingVo> rankingList = sd.getShowRankingList(startdate);
			System.out.println("이미지 나오는지 테스트: " + showList.get(0));
			System.out.println("이미지 나오는지 테스트: " + showList.get(0).getStitleimage());
			
			NewsDao nd = new NewsDao();
			List<NewsVo> newsList = nd.getNewsList(query, nsetting, page);
			
			request.setAttribute("showList", showList);
			request.setAttribute("newsList", newsList);
			request.setAttribute("rankingList", rankingList);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Main.jsp").forward(request, response);
			
		}
		
	};
	
}
