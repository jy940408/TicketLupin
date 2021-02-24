package com.TicketLupin.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TicketLupin.web.service.NewsDao;
import com.TicketLupin.web.service.NewsVo;

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
			
			String query_ = request.getParameter("q");
			String page_ = request.getParameter("p");
			String setting_ = request.getParameter("s");
			
			String query = "";
			if(query_ != null && !query_.equals("")) {
				query = query_;
			}
			String setting = "wregdate";
			if(setting_ != null && !setting_.equals("")) {
				setting = setting_;
			}
			int page = 1;
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			NewsDao nd = new NewsDao();
			
			List<NewsVo> list = nd.getNewsList(query, setting, page);
			int count = nd.getNewsListCount(query, setting);
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Ticketopen_list.jsp").forward(request, response);
			
			System.out.println(setting);
			
		}else if(str.equals("/News/NewsDetail.do")) {
			System.out.println(":::testtesttest:::::");
			
			String widxS = (String)request.getParameter("widx");
			
			if(widxS != null && widxS.equals("")) {
				widxS = "0";
			}
			int widx =  Integer.parseInt(widxS);
			NewsDao nd = new NewsDao();
			NewsVo newsvo = nd.getNewsDetail(widx);
			
			request.setAttribute("detail", newsvo);
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
