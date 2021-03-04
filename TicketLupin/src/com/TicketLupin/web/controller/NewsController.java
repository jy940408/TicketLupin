package com.TicketLupin.web.controller;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.MemberDao;
import com.TicketLupin.web.service.NewsDao;
import com.TicketLupin.web.service.NewsVo;
import com.TicketLupin.web.service.WinnerDao;
import com.TicketLupin.web.service.WinnerVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
			
			HttpSession session = request.getSession();
			
			String query_ = request.getParameter("q");
			String page_ = request.getParameter("p");
			String setting_ = request.getParameter("s");
			String mid = (String)session.getAttribute("mid");
			
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
			
			System.out.println("list " + list);
			System.out.println("member id " + mid);
			
		}else if(str.equals("/News/NewsDetail.do")) {
			
			String widx_ = (String)request.getParameter("widx");
			
			int widx = 0;
			if(widx_ != null && !widx_.equals("")) {
				widx = Integer.parseInt(widx_);
			}
			
			NewsDao nd = new NewsDao();
			NewsVo newsvo = nd.getNewsDetail(widx);
			
			System.out.println("detail widx: " + widx);
			System.out.println("detail: " + newsvo);
			
			request.setAttribute("detail", newsvo);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Ticketopen_view.jsp").forward(request, response);
			
		}else if(str.equals("/News/NewsWrite.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Ticketopen_write_admin.jsp").forward(request, response);
			
		}else if(str.equals("/News/NewsModify.do")) {
			
			String widx_ = request.getParameter("widx");
			
			int widx = 0;
			if(widx_ != null && !widx_.equals("")) {
				widx = Integer.parseInt(widx_);
			}
			
			NewsDao wd = new NewsDao();
			NewsVo newsvo = wd.getNewsDetail(widx);
			
			System.out.println(widx);
			System.out.println(newsvo);
			
			request.setAttribute("detail", newsvo);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Ticketopen_modify_admin.jsp").forward(request, response);
			
		}else if(str.equals("/News/NewsDeleteAction.do")) {
			String widx_ = request.getParameter("widx");
			System.out.println("삭제 widx_ 확인: " + widx_);
			
			int widx = 0;
			if(widx_ != null && !widx_.equals("")) {
				widx = Integer.parseInt(widx_);
			}
			
			NewsDao nd = new NewsDao();
			nd.deleteNews(widx);
			System.out.println("삭제 widx 확인: " + widx);
			response.sendRedirect("../News/NewsList.do");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str); 
		
		if(str.equals("/News/NewsWriteAction.do")) {
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			String uploadPath = request.getSession().getServletContext().getRealPath("poster");
			int sizeLimit = 1024*1024*15;
			String file = "";
			String originalFile = "";
			
			MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
			
			String title = multi.getParameter("title"); //제목
			String category = multi.getParameter("category"); //카테고리
			String binfo = multi.getParameter("binfo"); //기본정보
			String introduce = multi.getParameter("introduce"); //공연소개
			String discount = multi.getParameter("discount"); //할인정보
			String company = multi.getParameter("company"); //기획사 정보
			String pub_ = multi.getParameter("pub"); //공개여부
			String opendate_ = multi.getParameter("opendate"); // 오픈 날짜
			
			Enumeration files = multi.getFileNames();
			String str_ = (String)files.nextElement();
			
			file = multi.getFilesystemName(str_);
			originalFile = multi.getOriginalFileName(str_);
			
			System.out.println("file명: " + file);
			System.out.println("originalFile명: " + originalFile);
			
//==============================================================================================================================//
			Date opendate = null;
			try {
				DateFormat formatter;
				   
				formatter =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				opendate = (Date)formatter.parse(opendate_);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			java.sql.Date sqlOpenDate = new java.sql.Date(opendate.getTime());
			System.out.println(sqlOpenDate);
//==============================================================================================================================//			
			String pub = "Y";
			if(pub_ != null) {
				pub = "Y";
			} else{
				pub = "N";
			}
			
			HttpSession session = request.getSession();
			
			String getid = (String)session.getAttribute("mid");
			
			NewsVo nv = new NewsVo();
			nv.setWtitle(title);
			nv.setWcategory(category);
			nv.setWbasicinfo(binfo);
			nv.setWintroduce(introduce);
			nv.setWdiscount(discount);
			nv.setWcompany(company);
			nv.setWpub(pub);
			nv.setWtitleposter(originalFile);
			nv.setWopendate(sqlOpenDate);
			
			NewsDao nd = new NewsDao();
			nd.insertNews(nv);
			response.sendRedirect("../News/NewsList.do");
			
		}else if(str.equals("/News/NewsModifyAction.do")) {
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			String uploadPath = request.getSession().getServletContext().getRealPath("poster");
			int sizeLimit = 1024*1024*15;
			String file = "";
			String originalFile = "";
			
			MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
			
			String title = multi.getParameter("title"); //제목
			String category = multi.getParameter("category"); //카테고리
			String binfo = multi.getParameter("binfo"); //기본정보
			String introduce = multi.getParameter("introduce"); //공연소개
			String discount = multi.getParameter("discount"); //할인정보
			String company = multi.getParameter("company"); //기획사 정보
			String pub_ = multi.getParameter("pub"); //공개여부
			String opendate_ = multi.getParameter("opendate"); // 오픈 날짜
			String widx_ = request.getParameter("widx");
			
			int widx = 0;
			if(widx_ != null && !widx_.equals("")) {
				widx = Integer.parseInt(widx_);
			}
			
			Enumeration files = multi.getFileNames();
			String str_ = (String)files.nextElement();
			
			file = multi.getFilesystemName(str_);
			originalFile = multi.getOriginalFileName(str_);
			
			System.out.println("file명: " + file);
			System.out.println("originalFile명: " + originalFile);
			
//==============================================================================================================================//
			Date opendate = null;
			try {
				DateFormat formatter;
				   
				formatter =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				opendate = (Date)formatter.parse(opendate_);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			java.sql.Date sqlOpenDate = new java.sql.Date(opendate.getTime());
			System.out.println(sqlOpenDate);
//==============================================================================================================================//			
			String pub = "Y";
			if(pub_ != null) {
				pub = "Y";
			} else{
				pub = "N";
			}
			
			HttpSession session = request.getSession();
			
			String getid = (String)session.getAttribute("mid");
			
			NewsVo nv = new NewsVo();
			nv.setWtitle(title);
			nv.setWcategory(category);
			nv.setWbasicinfo(binfo);
			nv.setWintroduce(introduce);
			nv.setWdiscount(discount);
			nv.setWcompany(company);
			nv.setWpub(pub);
			nv.setWtitleposter(originalFile);
			nv.setWopendate(sqlOpenDate);
			nv.setWidx(widx);
			
			NewsDao nd = new NewsDao();
			nd.modifyNews(nv);
			response.sendRedirect("../News/NewsList.do");
			
		}
		
		
	}
}
