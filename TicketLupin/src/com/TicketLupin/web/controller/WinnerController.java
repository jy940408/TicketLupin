package com.TicketLupin.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TicketLupin.web.service.WinnerDao;
import com.TicketLupin.web.service.WinnerVo;


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
			
			String query_ = request.getParameter("q");
			String page_ = request.getParameter("p");
			
			String query = "";
			if(query_ != null && !query_.equals("")) {
				query = query_;
			}
			
			int page = 1;
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			WinnerDao wd = new WinnerDao();
			
			List<WinnerVo> list = wd.getWinnerList(query, page);
			int count = wd.getWinnerListCount(query, page);
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Winner_list.jsp").forward(request, response);
			
		}else if(str.equals("/Winner/WinnerDetail.do")) {
			
			String iidx_ = request.getParameter("iidx");
			
			int iidx = 0;
			if(iidx_ != null && !iidx_.equals("")) {
				iidx = Integer.parseInt(iidx_);
			}
			
			WinnerDao wd = new WinnerDao();
			WinnerVo winnervo = wd.getWinnerDetail(iidx);
			
			System.out.println(iidx);
			System.out.println(winnervo);
			
			request.setAttribute("detail", winnervo);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Winner_view.jsp").forward(request, response);
			
		}else if(str.equals("/Winner/WinnerWrite.do")) {
			
		}else if(str.equals("/Winner/WinnerWriteAction.do")) {
			
		}else if(str.equals("/Winner/WinnerModify.do")) {
			
		}else if(str.equals("/Winner/WinnerModifyAction.do")) {
			
		}else if(str.equals("/Winner/WinnerDeleteAction.do")) {
			
		}
		
	}
	
}
