package com.TicketLupin.web.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.NewsVo;
import com.TicketLupin.web.service.ShowDao;
import com.TicketLupin.web.service.ShowRoundDao;
import com.TicketLupin.web.service.ShowRoundVo;
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
			
			HttpSession session = request.getSession();
			
			String query_ = request.getParameter("q");
			String setting_ = request.getParameter("s");
			String page_ = request.getParameter("p");
			String mid = (String)session.getAttribute("mid");
			
			String query = "";
			if(query_ != null && !query_.equals("")) {
				query = query_;
			}
			
			String setting = "sregdate";
			if(setting_ != null && !setting_.equals("")) {
				setting = setting_;
			}
			
			int page = 1;
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			String array = "DESC";
			if(setting.equals("sopendate")) {
				array = "ASC";
			}
			
			ShowDao sd = new ShowDao();
			List<ShowVo> list = sd.getShowList(query, setting, array, page);
			int count = sd.getShowListCount(query, setting);
			
			System.out.println("setting: " + setting);
			System.out.println("array: " + array);
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_list.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowWriteStep1.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_write_admin.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowWriteStep2.do")) {
			
			ShowDao sd = new ShowDao();
			ShowVo sv = sd.getRecentShowDetail();
			
			int sidx = sv.getSidx();
			Date sopendate_ = sv.getSopendate();
			Date senddate_ = sv.getSenddate();
			
			System.out.println("½ÃÀÛ³¯Â¥: " + sopendate_);
			System.out.println("³¡³¯Â¥: " + senddate_);

			Calendar sopendate1 = Calendar.getInstance();
			Calendar senddate1 = Calendar.getInstance();

			SimpleDateFormat dateFormat;
	        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
			//Calendar Å¸ÀÔÀ¸·Î º¯°æ add()¸Þ¼Òµå·Î 1ÀÏ¾¿ Ãß°¡ÇØ ÁÖ±âÀ§ÇØ º¯°æ
			sopendate1.setTime(sopendate_);
			senddate1.setTime(senddate_);

			ArrayList datelist = new ArrayList();
			
			//½ÃÀÛ³¯Â¥¿Í ³¡ ³¯Â¥¸¦ ºñ±³ÇØ, ½ÃÀÛ³¯Â¥°¡ ÀÛ°Å³ª °°Àº °æ¿ì Ãâ·Â			
			while(sopendate1.compareTo(senddate1) != 1){	
				//Ãâ·Â
				System.out.println(dateFormat.format(sopendate1.getTime()));
				datelist.add(dateFormat.format(sopendate1.getTime()));
				//½ÃÀÛ³¯Â¥ + 1 ÀÏ
				sopendate1.add(Calendar.DATE, 1);
			}

			int datelength = datelist.size();
			
			System.out.println("¸ñ·Ï È®ÀÎ: " + datelist);
			
			request.setAttribute("sidx", sidx);
			request.setAttribute("datelist", datelist);
			request.setAttribute("datelength", datelength);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_write_round_admin.jsp").forward(request, response);
			
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Show/ShowWriteStep1Action.do")) {
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			String uploadPath = request.getSession().getServletContext().getRealPath("poster");
			int sizeLimit = 1024*1024*15;
			String file = "";
			String originalFile = "";
			System.out.println(uploadPath);
			MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
			
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String genre = multi.getParameter("genre");
			String startdate_ = multi.getParameter("startdate");
			String enddate_ = multi.getParameter("enddate");
			String ticketingdate_ = multi.getParameter("ticketingdate");
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
			   System.out.println("ï¿½ï¿½È¯ ï¿½Ï·ï¿½ ï¿½ï¿½ï¿½Û³ï¿½Â¥: " + sqlStartDate);
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
			   System.out.println("ï¿½ï¿½È¯ ï¿½Ï·ï¿½ ï¿½ï¿½ï¿½ï¿½Â¥: " + sqlEndDate);
//==============================================================================================================================//
			
			   Date ticketingdate = null;
			   try {
				   DateFormat formatter;
				   
				   formatter =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				   ticketingdate = (Date)formatter.parse(ticketingdate_);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			   java.sql.Date sqlTicketingDate = new java.sql.Date(ticketingdate.getTime());
			   System.out.println("ï¿½ï¿½È¯ ï¿½Ï·ï¿½ Æ¼ï¿½Ï¿ï¿½ï¿½Â³ï¿½Â¥: " + sqlTicketingDate);
//==============================================================================================================================//
			   
			Enumeration files = multi.getFileNames();
			String str_ = (String)files.nextElement();
							
			file = multi.getFilesystemName(str_);
			originalFile = multi.getOriginalFileName(str_);
							
			System.out.println("fileï¿½ï¿½: " + file);
			System.out.println("originalFileï¿½ï¿½: " + originalFile);
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
			sv.setSticketingdate(sqlTicketingDate);
			sd.insertShow(sv);
			
			
			response.sendRedirect(request.getContextPath()+"/Show/ShowWriteStep2.do");
			
		}else if(str.equals("/Show/ShowWriteStep2Action.do")) {
			
			String sidx_ = request.getParameter("sidx");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			System.out.println("È¸Â÷ sidx È®ÀÎ: " + sidx);
//==============================================================================================================================//			
			
			ShowDao sd = new ShowDao();
			ShowVo sv = sd.getShowDetail(sidx);
			
			Date sopendate_ = sv.getSopendate();
			Date senddate_ = sv.getSenddate();
			
			System.out.println("½ÃÀÛ³¯Â¥  ¼³Á¤: " + sopendate_);
			System.out.println("³¡³¯Â¥ ¼³Á¤: " + senddate_);

			Calendar sopendate1 = Calendar.getInstance();
			Calendar senddate1 = Calendar.getInstance();

			SimpleDateFormat dateFormat;
	        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
			//Calendar Å¸ÀÔÀ¸·Î º¯°æ add()¸Þ¼Òµå·Î 1ÀÏ¾¿ Ãß°¡ÇØ ÁÖ±âÀ§ÇØ º¯°æ
			sopendate1.setTime(sopendate_);
			senddate1.setTime(senddate_);

			ArrayList datelist = new ArrayList();
			
			//½ÃÀÛ³¯Â¥¿Í ³¡ ³¯Â¥¸¦ ºñ±³ÇØ, ½ÃÀÛ³¯Â¥°¡ ÀÛ°Å³ª °°Àº °æ¿ì Ãâ·Â			
			while(sopendate1.compareTo(senddate1) != 1){	
				//Ãâ·Â
				System.out.println(dateFormat.format(sopendate1.getTime()));
				datelist.add(dateFormat.format(sopendate1.getTime()));
				//½ÃÀÛ³¯Â¥ + 1 ÀÏ
				sopendate1.add(Calendar.DATE, 1);
			}

//==============================================================================================================================//
			
			int datelength = datelist.size();
			
			System.out.println("±æÀÌ: " + datelength);
			
			ArrayList roundlist =  new ArrayList();
			
			String showdate = null;
			String round1 = null;
			String round2 = null;
			String round3 = null;
			String round4 = null;
			
			int i = 1;
			while(i != datelength+1) {
				
				showdate = request.getParameter("showdate" + i);
				round1 = request.getParameter("date" + i + "_1");
				round2 = request.getParameter("date" + i + "_2");
				round3 = request.getParameter("date" + i + "_3");
				round4 = request.getParameter("date" + i + "_4");
				
				
			   System.out.println(showdate);
				
			   roundlist.add(showdate);
			   roundlist.add(round1);
			   roundlist.add(round2);
			   roundlist.add(round3);
			   roundlist.add(round4);
				
			   i++;
			
			}
			
			ShowRoundDao srd = new ShowRoundDao();
			
			
			int j = 0;
			while(j != datelength*5) {
				
				ShowRoundVo srv = new ShowRoundVo();
				
				srv.setSidx(sidx);
				srv.setSrdate(roundlist.get(j).toString());
				srv.setSrround1(roundlist.get(j+1).toString());
				srv.setSrround2(roundlist.get(j+2).toString());
				srv.setSrround3(roundlist.get(j+3).toString());
				srv.setSrround4(roundlist.get(j+4).toString());
				
				System.out.println(srv);
				srv = srd.insertShowRound(srv);
				
				j = j+5;
				
			}
			
			System.out.println(roundlist);
			response.sendRedirect(request.getContextPath()+"/Show/ShowList.do");
		}
		
		
	}
}
