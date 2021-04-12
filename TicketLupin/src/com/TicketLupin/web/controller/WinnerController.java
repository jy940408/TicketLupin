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

import com.TicketLupin.web.service.WinnerDao;
import com.TicketLupin.web.service.WinnerVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


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
			System.out.println(query);
			System.out.println(page);
			WinnerDao wd = new WinnerDao();
			
			List<WinnerVo> list = wd.getWinnerList(query, page);
			int count = wd.getWinnerListCount(query);
			
			System.out.println("winner count test: " + count);
			
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
			wd.countWinnerView(iidx);
			
			request.setAttribute("detail", winnervo);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Winner_view.jsp").forward(request, response);
			
		}else if(str.equals("/Winner/WinnerWrite.do")) {
			request.getRequestDispatcher("/WEB-INF/view/jsp/Winner_write_admin.jsp").forward(request, response);
		
		}else if(str.equals("/Winner/WinnerModify.do")) {
			
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
			request.getRequestDispatcher("/WEB-INF/view/jsp/Winner_modify_admin.jsp").forward(request, response);
			
		}else if(str.equals("/Winner/WinnerDeleteAction.do")) {
			
			String iidx_ = request.getParameter("iidx");
			System.out.println("���� iidx_ Ȯ��: " + iidx_);
			
			int iidx = 0;
			if(iidx_ != null && !iidx_.equals("")) {
				iidx = Integer.parseInt(iidx_);
			}
			
			WinnerDao wd = new WinnerDao();
			wd.deleteWinner(iidx);
			System.out.println("���� iidx Ȯ��: " + iidx);
			response.sendRedirect("../Winner/WinnerList.do");
		}else if(str.equals("/Winner/WinnerWriteAction.do")) {
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			String uploadPath = request.getSession().getServletContext().getRealPath("content");
			int sizeLimit = 1024*1024*15;
			String file = "";
			String originalFile = "";
			
			MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
			
			String title = multi.getParameter("title"); //����
			String content = multi.getParameter("content"); //ī�װ�
			String pub_ = multi.getParameter("pub"); //��������
			String image = multi.getParameter("image");
			String startdate_ = multi.getParameter("startdate");
			String enddate_ = multi.getParameter("enddate");
			
			System.out.println(image);
			System.out.println(startdate_);
			System.out.println(enddate_);
//==============================================================================================================================//		    
		   Date startdate = null;
		   try {
			   DateFormat formatter;
			   
			   formatter =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			   startdate = (Date)formatter.parse(startdate_);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   java.sql.Date sqlStartDate = new java.sql.Date(startdate.getTime());
//==============================================================================================================================//
		    
		   Date enddate = null;
		   try {
			   DateFormat formatter;
			   
			   formatter =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			   enddate = (Date)formatter.parse(enddate_);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   java.sql.Date sqlEndDate = new java.sql.Date(enddate.getTime());
//==============================================================================================================================//
		   
			Enumeration files = multi.getFileNames();
			String str_ = (String)files.nextElement();
			
			file = multi.getFilesystemName(str_);
			originalFile = multi.getOriginalFileName(str_);
			
			System.out.println("file��: " + file);
			System.out.println("originalFile��: " + originalFile);
			
			String pub = "Y";
			if(pub_ != null) {
				pub = "Y";
			} else{
				pub = "N";
			}
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			WinnerVo wv = new WinnerVo();
			wv.setItitle(title);
			wv.setIcontent(content);
			wv.setIpub(pub);
			wv.setIimage(originalFile);
			wv.setIopendate(sqlStartDate);
			wv.setIenddate(sqlEndDate);
			wv.setMidx(midx);
			WinnerDao wd = new WinnerDao();
			wd.insertWinner(wv);
			
			response.sendRedirect("../Winner/WinnerList.do");
			
		}else if(str.equals("/Winner/WinnerModifyAction.do")) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			String uploadPath = request.getSession().getServletContext().getRealPath("content");
			int sizeLimit = 1024*1024*15;
			String file = "";
			String originalFile = "";
			
			MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
			
			String title = multi.getParameter("title"); //����
			String content = multi.getParameter("content"); //ī�װ�
			String pub_ = multi.getParameter("pub"); //��������
			String image = multi.getParameter("image");
			String startdate_ = multi.getParameter("startdate");
			String enddate_ = multi.getParameter("enddate");
			String iidx_ = request.getParameter("iidx");
			
			int iidx = 0;
			if(iidx_ != null && !iidx_.equals("")) {
				iidx = Integer.parseInt(iidx_);
			}
			
			System.out.println("����: " + iidx);
			System.out.println("���۳�¥ ����: " + startdate_);
			System.out.println("����¥ ����: " + enddate_);
//==============================================================================================================================//		    
		   Date startdate = null;
		   try {
			   DateFormat formatter;
			   
			   formatter =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			   startdate = (Date)formatter.parse(startdate_);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   java.sql.Date sqlStartDate = new java.sql.Date(startdate.getTime());
//==============================================================================================================================//
		    
		   Date enddate = null;
		   try {
			   DateFormat formatter;
			   
			   formatter =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			   enddate = (Date)formatter.parse(enddate_);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   java.sql.Date sqlEndDate = new java.sql.Date(enddate.getTime());
//==============================================================================================================================//
		   
			Enumeration files = multi.getFileNames();
			String str_ = (String)files.nextElement();
			
			file = multi.getFilesystemName(str_);
			originalFile = multi.getOriginalFileName(str_);
			
			System.out.println("file��: " + file);
			System.out.println("originalFile��: " + originalFile);
			
			String pub = "Y";
			if(pub_ != null) {
				pub = "Y";
			} else{
				pub = "N";
			}
			
			HttpSession session = request.getSession();
			
			String getid = (String)session.getAttribute("mid");
			
			WinnerVo wv = new WinnerVo();
			wv.setItitle(title);
			wv.setIcontent(content);
			wv.setIpub(pub);
			wv.setIimage(originalFile);
			wv.setIopendate(sqlStartDate);
			wv.setIenddate(sqlEndDate);
			wv.setIidx(iidx);
			WinnerDao wd = new WinnerDao();
			wd.modifyWinner(wv);
			
			response.sendRedirect("../Winner/WinnerList.do");
		}else if(str.equals("/Winner/WinnerModifyAction.do")) {
			
		}
		
	}
	
}
