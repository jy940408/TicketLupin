package com.TicketLupin.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.TicketLupin.web.service.DibsDao;
import com.TicketLupin.web.service.ShowDao;
import com.TicketLupin.web.service.ShowRoundDao;
import com.TicketLupin.web.service.ShowRoundVo;
import com.TicketLupin.web.service.ShowVo;

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
			ShowVo detail = sd.getShowDetail(sidx);
			
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
			ArrayList<ShowRoundVo> srv = srd.getShowRoundList(sidx);
			
			ShowRoundDao srd2 = new ShowRoundDao();
			ShowRoundVo srv2 = srd2.getShowRoundDetail(sidx, comDate);
			
			System.out.println("srv2 test: " + srv2);
			
			request.setAttribute("srv", srv);
			request.setAttribute("roundCheck", srv2);
			
			System.out.println(srv);
			
//==============================================================================================================================//	
			
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
			
			
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(obj); //{"result":1}
			
		}
		
	};
	
}
