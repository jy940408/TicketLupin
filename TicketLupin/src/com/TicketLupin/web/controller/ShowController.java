package com.TicketLupin.web.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.ShowDao;
import com.TicketLupin.web.service.ShowVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/ShowController")
public class ShowController extends HttpServlet{

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Show/ShowList.do")) {
			
			String query_ = request.getParameter("q");
			String setting_ = request.getParameter("f");
			String page_ = request.getParameter("p");
			
			String query = null;
			if(query_ != null && query_.equals("")) {
				query = query_;
			}
			
			String setting = "";
			if(query_ != null && query_.equals("")) {
				query = query_;
			}
			
			int page = 1;
			if(page_ != null && page_.equals("")) {
				page = Integer.parseInt(query_);
			}
			
			ShowDao sd = new ShowDao();
			sd.getShowList(query, setting, page);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_list.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowWrite.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_write_admin.jsp").forward(request, response);
			
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Show/ShowWriteAction.do")) {
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			String uploadPath = request.getSession().getServletContext().getRealPath("poster");
			int sizeLimit = 1024*1024*15;
			String file = "";
			String originalFile = "";
			
			MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
			
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String genre = multi.getParameter("genre");
			String startdate_ = multi.getParameter("startdate");
			String enddate_ = multi.getParameter("enddate");
			String rating = multi.getParameter("rating");
			String round = multi.getParameter("platanusTime");
			String image = multi.getParameter("image");
			String postcode_ = multi.getParameter("postcode");
			String roadAddress = multi.getParameter("roadAddress");
			String jibunAddress = multi.getParameter("jibunAddress");
			String detailAddress = multi.getParameter("detailAddress");
			String extraAddress = multi.getParameter("extraAddress");
			
			int postcode = 0;
			if(postcode_ != null && !postcode_.equals("")) {
				postcode = Integer.parseInt(postcode_);
			}
			
			System.out.println("title: " + title);
			System.out.println("content: " + content);
			System.out.println("genre: " + genre);
			System.out.println("startdate: " + startdate_);
			System.out.println("enddate: " + enddate_);
			System.out.println("rating: " + rating);
			System.out.println("platanusTime: " + round);
			System.out.println("image: " + image);
			System.out.println("postcode: " + postcode);
			System.out.println("roadAddress: " + roadAddress);
			System.out.println("jibunAddress: " + jibunAddress);
			System.out.println("detailAddress: " + detailAddress);
			System.out.println("extraAddress: " + extraAddress);
//==============================================================================================================================//
			Date startdate = null;
			   try {
				   DateFormat formatter;
				   
				   formatter =  new SimpleDateFormat("yyyy-MM-dd");
				   startdate = (Date)formatter.parse(startdate_);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			   java.sql.Date sqlStartDate = new java.sql.Date(startdate.getTime());
			   System.out.println("변환 완료 시작날짜: " + sqlStartDate);
//==============================================================================================================================//
			Date enddate = null;
			   try {
				   DateFormat formatter;
				   
				   formatter =  new SimpleDateFormat("yyyy-MM-dd");
				   enddate = (Date)formatter.parse(enddate_);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			   java.sql.Date sqlEndDate = new java.sql.Date(enddate.getTime());
			   System.out.println("변환 완료 끝날짜: " + sqlEndDate);
//==============================================================================================================================//
			Enumeration files = multi.getFileNames();
			String str_ = (String)files.nextElement();
							
			file = multi.getFilesystemName(str_);
			originalFile = multi.getOriginalFileName(str_);
							
			System.out.println("file명: " + file);
			System.out.println("originalFile명: " + originalFile);
//==============================================================================================================================//
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
//==============================================================================================================================//
			
			ShowDao sd = new ShowDao();
			ShowVo sv = new ShowVo();
			
			sv.setStitle(title);
			sv.setScontent(content);
			sv.setSgenre(genre);
			sv.setSopendate(sqlStartDate);
			sv.setSenddate(sqlEndDate);
			sv.setSrating(rating);
			sv.setSround(round);
			sv.setSimage(originalFile);
			sv.setSpostcode(postcode);
			sv.setSroadaddress(roadAddress);
			sv.setSjibunaddress(jibunAddress);
			sv.setSdetailaddress(detailAddress);
			sv.setSextraaddress(extraAddress);
			sv.setMidx(midx);
			sd.insertShow(sv);
			
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_list.jsp").forward(request, response);
			
		}
		
		
	}
}
