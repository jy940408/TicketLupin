package com.TicketLupin.web.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TicketLupin.web.service.NoticeDao;
import com.TicketLupin.web.service.NoticeVo;

import domain.PageMaker;
import domain.SearchCriteria;

@WebServlet("/NoticeController")
public class NoticeController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
		
	
	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) 
			throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Notice/NoticeList.do")) {
			
			String page = request.getParameter("page");
			if (page==null) page = "1";
			int pagex = Integer.parseInt(page);
			
			
			String keyword = request.getParameter("keyword");
			if (keyword ==null) keyword ="";
			
			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setKeyword(keyword);
			
			PageMaker pm = new PageMaker();
			pm.setScri(scri);
			
			NoticeDao nd = new NoticeDao();
			int cnt = nd.getNoticeListCount();
			
			pm.setTotalCount(cnt);			
			
			ArrayList<NoticeVo> alist = nd.getNoticeList(scri);
			
			request.setAttribute("alist", alist);
			request.setAttribute("pm", pm);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Notice_list.jsp").forward(request, response);
			
		}else if(str.equals("/Notice/NoticeWrite.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Notice_write_admin.jsp").forward(request, response);
		
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Notice/NoticeWriteAction.do")) {
			
			String ntitle = request.getParameter("NTITLE");
			String ncontent = request.getParameter("NCONTENT");
			String ncategory = request.getParameter("NCATEGORY");
			
			System.out.println("NTITLE:"+ntitle);
			System.out.println("NCONTENT:"+ncontent);
			System.out.println("NCATEGORY:"+ncategory);
			
			NoticeDao nd = new NoticeDao();
			nd.insertNotice(ntitle, ncontent, ncategory);
			
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Notice_list.jsp").forward(request, response);
			
			
		}else if(str.equals("/Notice/NoticeView.do")) {
			
			String nidx = request.getParameter("nidx");
			int nidx2 = Integer.parseInt(nidx);
			
			NoticeDao nd = new NoticeDao();
			NoticeVo nv = nd.noticeSelectOne(nidx2);
			
			request.setAttribute("nv", nv);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_list.jsp").forward(request, response);
			
		}
		
	}

	
	
}
