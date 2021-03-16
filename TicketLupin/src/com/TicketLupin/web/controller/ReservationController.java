package com.TicketLupin.web.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReservationController")
public class ReservationController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Reservation/ReservationStep1.do")) {
			
			String sidx_ = request.getParameter("sidx");
			String year = request.getParameter("year"); //현재 년도
			String strMonth = request.getParameter("month"); //현재 월, 0부터 시작, 즉 0이 1월
			String strDate = request.getParameter("date"); //현재 일, 이건 또 1부터 시작, 뭔짓거리야 이게 헷갈리게...
			String round = request.getParameter("round");
			System.out.println("year: " + year);
			System.out.println("month: " + strMonth);
			System.out.println("date: " + strDate);
			System.out.println("sidx: " + sidx_);
			System.out.println("round: " + round);
			
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			int month_ = 0;
			if(strMonth != null && !strMonth.equals("")) {
				month_ = Integer.parseInt(strMonth);
			}
			int date_ = 0;
			if(strDate != null && !strDate.equals("")) {
				date_ = Integer.parseInt(strDate);
			}
			
			
			String month = Integer.toString(month_+1);
			if((int)(Math.log10(month_)+1) == 1) {
				month = "0" + month_;
			}
			String date = Integer.toString(date_);
			if((int)(Math.log10(date_)+1) == 1) {
				date = "0" + date_;
			}
			
			String comDate = year + "-" + (month) + "-" + date;
			System.out.println("comDate: " + comDate);
			
			request.setAttribute("sidx", sidx);
			request.setAttribute("comDate", comDate);
			request.setAttribute("round", round);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/pay_step1.jsp").forward(request, response);
			
		}else if(str.equals("/Reservation/ReservationStep1Seat.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/pay_step1_seat.jsp").forward(request, response);
			
		}else if(str.equals("/Reservation/ReservationStep2.do")) {
			
			String sidx_ = request.getParameter("sidx");
			String comDate = request.getParameter("comDate");
			String round = request.getParameter("round");
			
			System.out.println("sidx: " + sidx_);
			System.out.println("comDate: " + comDate);
			System.out.println("round: " + round);
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			String seat = request.getParameter("seatHidden");
			System.out.println("seat: " + seat);
			
			String[] arraySeat = seat.split("/");
			
			String[] seatGrade = new String[arraySeat.length];
			String[] floor = new String[arraySeat.length];
			
			for(int i = 0 ; i < arraySeat.length ; i ++) {
				if(Integer.parseInt(arraySeat[i].substring(0,2)) <= 6) {
					seatGrade[i] = "VIP";
					floor[i] = "1";
				}else if(Integer.parseInt(arraySeat[i].substring(0,2)) > 6 && Integer.parseInt(arraySeat[i].substring(0,2)) <= 20) {
					seatGrade[i] = "R";
					floor[i] = "1";
				}else if(Integer.parseInt(arraySeat[i].substring(0,2)) > 20 && Integer.parseInt(arraySeat[i].substring(0,2)) <= 29) {
					seatGrade[i] = "S";
					floor[i] = "2";
				}else if(Integer.parseInt(arraySeat[i].substring(0,2)) > 29 && Integer.parseInt(arraySeat[i].substring(0,2)) <= 37) {
					seatGrade[i] = "A";
					floor[i] = "3";
				}
				System.out.println("앞 숫자 출력 테스트: " + arraySeat[i].substring(0,2));
				System.out.println("arraySeat[" + i + "]: " + arraySeat[i]);
				System.out.println("arraySeat[" + i + "] 등급: " + seatGrade[i]);
				System.out.println("arraySeat[" + i + "] 층수: " + floor[i]);
			}
			
			request.setAttribute("sidx", sidx);
			request.setAttribute("comDate", comDate);
			request.setAttribute("round", round);
			request.setAttribute("arraySeat", arraySeat);
			request.setAttribute("seatGrade", seatGrade);
			request.setAttribute("floor", floor);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Pay_step2.jsp").forward(request, response);
			
		}else if(str.equals("/Reservation/ReservationStep3.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Pay_step3.jsp").forward(request, response);
			
		}
		
	}
	
}
