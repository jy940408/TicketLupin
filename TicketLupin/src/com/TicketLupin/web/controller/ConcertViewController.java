package com.TicketLupin.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.TicketLupin.web.service.CommentADao;
import com.TicketLupin.web.service.DibsDao;
import com.TicketLupin.web.service.ExpectDao;
import com.TicketLupin.web.service.ExpectVo;
import com.TicketLupin.web.service.GoodbadDao;
import com.TicketLupin.web.service.ShowDao;
import com.TicketLupin.web.service.ShowRoundDao;
import com.TicketLupin.web.service.ShowRoundVo;
import com.TicketLupin.web.service.Show1Vo;

@WebServlet("/ConcertViewController")
public class ConcertViewController extends HttpServlet{

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/ConcertView/ConcertView.do")) {
			
			HttpSession session = request.getSession();
			int midx = 0;
			if(session.getAttribute("midx") != null) {
				midx = (int) session.getAttribute("midx");
			}
			
			String sidx_ = request.getParameter("sidx");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			ShowDao sd = new ShowDao();
			Show1Vo detail = sd.getShowDetail(sidx);
			
			DibsDao dd = new DibsDao();
			int didx = dd.getDibsCheck(sidx, midx);
			
			request.setAttribute("detail", detail);
			request.setAttribute("didx", didx);
			
			System.out.println("showvo: " + detail);
			System.out.println("didx: " + didx);
			System.out.println("midx: " + midx);
			
//==============================================================================================================================//	
			
			String color = null; //달력에서 색상 표시할 때 쓸 String
			
			Calendar cal = Calendar.getInstance();
			System.out.println("현재시각: " + cal); // 현재 시각
			
			System.out.println("현재시각: " + cal); // 현재 시각
			
			String strYear = request.getParameter("year");
			String strMonth = request.getParameter("month");
			String strDate = request.getParameter("date");
			
			int year = cal.get(Calendar.YEAR); //현재 년도
			int month = cal.get(Calendar.MONTH); //현재 월, 0부터 시작, 즉 0이 1월
			int date = cal.get(Calendar.DATE); //현재 일, 이건 또 1부터 시작, 뭔짓거리야 이게 헷갈리게...
			System.out.println("year: " + year);
			System.out.println("month: " + month);
			System.out.println("date: " + date);
			
			if(strYear != null) {
				year = Integer.parseInt(strYear);
				System.out.println("strYear 먹은 year: " + year);
			}
			if(strMonth != null) {
				month = Integer.parseInt(strMonth);
				System.out.println("strMonth 먹은 month: " + month);
			}
			if(strDate != null) {
				date = Integer.parseInt(strDate);
				System.out.println("strDate 먹은 date: " + date);
			}
			
			cal.set(year, month, 1); //날짜 설정하기, 앞서 설정한 현재 년도, 현재 월, 1일을 차례로 세팅
			
			int startDay = cal.getMinimum(Calendar.DATE); //현재 달의 첫 날
			int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 현재 달의 마지막 날
			int start = cal.get(Calendar.DAY_OF_WEEK); //현재 요일을 출력, 1일로 설정을 해놨기 때문에 월요일이 출력됨, 일요일부터 1,2,3... 순서
			int newLine = 0;
			System.out.println("Calendar.DATE " + Calendar.DATE);
			System.out.println("Calendar.DAY_OF_MONTH: " + Calendar.DAY_OF_MONTH);
			System.out.println("startDay: " + startDay);
			System.out.println("endDay: " + endDay);
			System.out.println("start: " + start);
			
			Calendar todayCal = Calendar.getInstance(); // 오늘
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			int intToday = Integer.parseInt(sdf.format(todayCal.getTime())); // 오늘 날짜의 형식을 yyyyMMdd형식으로 바꾸고 이를 int형으로 변환
			System.out.println("todayCal: " + todayCal);
			System.out.println("intToday: " + intToday);
			
			request.setAttribute("color", color);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("date", date);
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
			request.setAttribute("start", start);
			request.setAttribute("intToday", intToday);
			
			
//==============================================================================================================================//
			// 회차 불러오기
			ShowRoundDao srd = new ShowRoundDao();
			ArrayList<ShowRoundVo> srv = srd.getShowRoundList(sidx);
			
			request.setAttribute("srv", srv);

//==============================================================================================================================//

			//각 날짜별로 round 있는지 확인
			//회차 있는 날 = 1, 회차 없는 날 = 0
			int strRoundCheck = 0;
			
			String month_ = Integer.toString(month+1);
			if((int)(Math.log10(month)+1) == 1) {
				month_ = "0" + month_;
			}
			
			ArrayList roundCheck = new ArrayList();
			for(int testDate = 1 ; testDate <= endDay ; testDate++) {
				
				String date_ = Integer.toString(testDate);
				if((int)(Math.log10(testDate)+1) == 1) {
					date_ = "0" + date_;
				}	
				
				String comDate = year + "-" + (month_) + "-" + date_;
				System.out.println("if문 바깥 : " + srd.getShowRoundDetail(sidx, comDate));
				if(srd.getShowRoundDetail(sidx, comDate) != null && !srd.getShowRoundDetail(sidx, comDate).equals("")) {
					strRoundCheck = 1;
					roundCheck.add(strRoundCheck);
				}else {
					strRoundCheck = 0;
					roundCheck.add(strRoundCheck);
				}
			}
			
			request.setAttribute("roundCheck", roundCheck);
			System.out.println(srv);
			System.out.println("roundCheck: " + roundCheck);
			
//==============================================================================================================================//	

			Show1Vo sv = sd.getShowDetail(sidx);
			request.setAttribute("contentsDetail", sv);
			
//==============================================================================================================================//	
			
			//상세 정보 받아오기
			String page_ = request.getParameter("p");
			String tab = request.getParameter("tab");
			String od = request.getParameter("od");
			System.out.println("OD"+od);
			if (od == ""||od == null)
				od = "";
			
			String setting3="E";
			 
			if (tab == null || tab.equals("")) {
				tab = "main_concert_detail_content_all";
			}else if (tab.equals("main_concert_expect_all")) {
				setting3 = "E";
			}else if (tab.equals("main_concert_review_all")) {
				setting3 = "R";
			}else if (tab.equals("main_concert_question_all")) {
				setting3 = "Q";
			}
		
			
			String setting = "";
			String setting2 = "";
			 if (od.equals("")) {
				setting = "";
				setting2 = "";
		
			}else if (od.equals("latest")) {
				setting = "";
				setting2 = "";
			}else if (od.equals("recommended")) {
				setting = "ORIGIN_GOOD DESC,";
				setting2 = "GOOD DESC,";
			}else if (od.equals("comments")) {
				setting = "CNT DESC,";
				setting2 = "";
			}
			 
			 
			 
			  int page = 1;
			  
			  if (page_ != null && !page_.equals("")) { 
				  page = Integer.parseInt(page_);
			  }
			  
			  ExpectDao ed = new ExpectDao();
			  
			  request.setAttribute("tab", tab);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_View.jsp").forward(request, response);
			
//==============================================================================================================================//				

		}else if(str.equals("/ConcertView/ConcertViewDateAJAX.do")) {
			
			HttpSession session = request.getSession();
			int midx = 0;
			if(session.getAttribute("midx") != null) {
				midx = (int) session.getAttribute("midx");
			}
			
			String sidx_ = request.getParameter("sidx");
			System.out.println("sidx_ 들어와 안들어와: " + sidx_);
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
//==============================================================================================================================//	
			
			String color = null; //달력에서 색상 표시할 때 쓸 String
			
			Calendar cal = Calendar.getInstance();
			System.out.println("현재시각: " + cal); // 현재 시각
			
			System.out.println("현재시각: " + cal); // 현재 시각
			
			String strYear = request.getParameter("year");
			String strMonth = request.getParameter("month");
			String strDate = request.getParameter("date");
			
			int year = cal.get(Calendar.YEAR); //현재 년도
			int month = cal.get(Calendar.MONTH); //현재 월, 0부터 시작, 즉 0이 1월
			int date = cal.get(Calendar.DATE); //현재 일, 이건 또 1부터 시작, 뭔짓거리야 이게 헷갈리게...
			System.out.println("year: " + year);
			System.out.println("month: " + month);
			System.out.println("date: " + date);
			
			if(strYear != null) {
				year = Integer.parseInt(strYear);
				System.out.println("strYear 먹은 year: " + year);
			}
			if(strMonth != null) {
				month = Integer.parseInt(strMonth);
				System.out.println("strMonth 먹은 month: " + month);
			}
			if(strDate != null) {
				date = Integer.parseInt(strDate);
				System.out.println("strDate 먹은 date: " + date);
			}
			
//==============================================================================================================================//
			
			String month_ = Integer.toString(month+1);
			if((int)(Math.log10(month)+1) == 1) {
				month_ = "0" + month_;
			}
			
			String date_ = Integer.toString(date);
			if((int)(Math.log10(date)+1) == 1) {
				date_ = "0" + date_;
			}
			
			String comDate = year + "-" + (month_) + "-" + date_;
			System.out.println("comDate: " + comDate);
			System.out.println("date_ 0 더하기: " + date_);
			ShowRoundDao srd = new ShowRoundDao();
			ShowRoundVo srv = srd.getShowRoundDetail(sidx, comDate);
			
			System.out.println("sidx 확인 중: " + sidx);
			System.out.println("comDate 확인 중: " + comDate);
			System.out.println("리스트 나오는지 확인 중: " + srv);
			System.out.println("리스트 내용 확인: " + srv);
//==============================================================================================================================//	
			
			JSONObject obj = new JSONObject();
			obj.put("sidx", srv.getSidx());
			obj.put("srdate", srv.getSrdate());
			obj.put("srround1", srv.getSrround1());
			obj.put("srround2", srv.getSrround2());
			obj.put("srround3", srv.getSrround3());
			obj.put("srround4", srv.getSrround4());
			obj.put("year", year);
			obj.put("month", strMonth);
			obj.put("date", strDate);
			
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(obj); //{"result":1}
			
			
//==============================================================================================================================//				
			
		}else if(str.equals("/ConcertView/Commentreport.do")){
			request.getRequestDispatcher("/WEB-INF/view/jsp/comment_report.jsp").forward(request, response);
			
	}else if(str.equals("/ConcertView/Commentreportaction.do")){ 	
			
		HttpSession session = request.getSession();
			int midx = (int) session.getAttribute("midx");
			int c_idx= Integer.parseInt(request.getParameter("c_idx"));
			String sort = request.getParameter("radioval");
			String etcval = request.getParameter("etcval");
			
			if (etcval  == ""||etcval  == null)
				etcval  = "NULL";
			
			CommentADao cd = new CommentADao();
			cd.insertReport(midx,c_idx,sort,etcval);

			PrintWriter pt = response.getWriter();
	         pt.write("<script>self.close();</script>");
	         pt.flush();
	         pt.close();
			
		}  else if (str.equals("/ConcertView/ExpectModifyWriteAction.do")) {
			
			  response.setCharacterEncoding("UTF-8");
			  response.setContentType("text/html; charset=UTF-8");
			  request.setCharacterEncoding("UTF-8");
			  
			  HttpSession session = request.getSession();
			  int midx = (int)session.getAttribute("midx");
			  int c_idx = Integer.parseInt(request.getParameter("c_idx")); 
			  String c_content = request.getParameter("content");
			  String tab = request.getParameter("tab");

			  
			  ExpectDao ed = new ExpectDao();
			  
			  ed.Expectupdate(midx,c_idx,c_content);
			  

			response.sendRedirect(request.getContextPath() + "/ConcertView/ConcertView.do?tab="+tab);
				
			 

		

		}else if (str.equals("/ConcertView/ExpectDeleteAction.do")) {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");		
			
			String tab	= request.getParameter("tab");
			String c_idx_ = request.getParameter("c_idx");
			String origin_c_idx_ = request.getParameter("origin_c_idx");

			int idx = 0;
			String setting = "";
			if (origin_c_idx_ != null && !origin_c_idx_.equals("")) {
				idx = Integer.parseInt(origin_c_idx_);
				setting = "origin_c_idx";
			}else if(c_idx_ != null && !c_idx_.equals("")) {
				idx = Integer.parseInt(c_idx_);
				setting = "c_idx";
			}
			
			
			
			
			System.out.println("idx->"+ idx+"/"+setting );
			ExpectDao rd = new ExpectDao();
			rd.deleteExpect(idx, setting);
			response.sendRedirect(request.getContextPath() + "/ConcertView/ConcertView.do?tab="+tab);
			
		
			/* 좋아요 */
		} else if (str.equals("/ConcertView/GoodAction.do")) {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();

			HttpSession session = request.getSession();
			int midx = (int) session.getAttribute("midx");
			String c_idx_ = request.getParameter("c_idx");
			String lsort = request.getParameter("good");
			String tab	= request.getParameter("tab");
			String origin_c_idx_ = request.getParameter("origin_c_idx");


			int c_idx = 0;
			if (c_idx_ != null && !c_idx_.equals("")) {
				c_idx = Integer.parseInt(c_idx_);
			}


			int origin_c_idx = 0;
			if (origin_c_idx_ != null && !origin_c_idx_.equals("")) {
				origin_c_idx = Integer.parseInt(origin_c_idx_);
			}

			GoodbadDao gd = new GoodbadDao();
			int check = gd.checkGood(midx, c_idx, origin_c_idx);

			if (check == 0) {
				gd.insertgoodbad(midx,c_idx, lsort, origin_c_idx);
				response.sendRedirect(request.getContextPath() + "/ConcertView/ConcertView.do?tab="+tab);
			} else {
				out.println("<script>alert('이미 좋아요를 하셨습니다.'); history.go(-1);</script>");
			}
		}  
	};
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	
}
