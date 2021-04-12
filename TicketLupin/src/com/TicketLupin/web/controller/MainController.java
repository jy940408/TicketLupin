package com.TicketLupin.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.TicketLupin.web.service.EventDao;
import com.TicketLupin.web.service.EventVo;
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
			String ssetting2 = "sregdate";
			String nsetting = "wregdate";
			int page = 1;
			String array = "ASC";
			String array2 = "DESC";
			
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
			Calendar start = Calendar.getInstance();
			String startdate = "0000-00-00";
			startdate = formatter.format(start.getTime());
			System.out.println("startdate �׽�Ʈ: " + startdate);
			
			ShowDao sd = new ShowDao();
			ArrayList<Show1Vo> showList = sd.getShowList(query, ssetting, array, page);
			ArrayList<Show1Vo> showRecentList = sd.getShowList(query, ssetting2, array2, page);
			ArrayList<ShowRankingVo> rankingList = sd.getShowRankingList(startdate);
			
			
			NewsDao nd = new NewsDao();
			List<NewsVo> newsList = nd.getNewsList(query, nsetting, "", page);
			System.out.println("메인페이지 뉴스 목록 확인: " + newsList);
			
			
			request.setAttribute("showList", showList);
			request.setAttribute("showRecentList", showRecentList);
			request.setAttribute("newsList", newsList);
			request.setAttribute("rankingList", rankingList);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Main.jsp").forward(request, response);
			
		}else if(str.equals("/Main/MainSearch.do")) {
			
			String query_ = request.getParameter("q");
			
			String query = "";
			if(query_ != null && query_.equals("")) {
				query = query_;
			}
			
			ShowDao sd = new ShowDao();
			List<Show1Vo> slist = sd.getShowList(query, "sregdate", "DESC", 1);
			
			NewsDao nd = new NewsDao();
			List<NewsVo> nlist = nd.getNewsList(query, "wregdate", "total", 1);
			
			EventDao ed = new EventDao();
//			ArrayList<EventVo> elist = ed.eventSelectAll(scri);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/MainSearch.jsp").forward(request, response);
		}
		
	};
	
}
