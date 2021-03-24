package com.TicketLupin.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.ReservationDao;
import com.TicketLupin.web.service.ReservationVo;
import com.TicketLupin.web.service.ShowDao;
import com.TicketLupin.web.service.Show1Vo;

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
			
			//예매 공연 날짜 완성하기
			String comDate = year + "-" + (month) + "-" + date;
			System.out.println("comDate: " + comDate);
			
			ShowDao sd = new ShowDao();
			Show1Vo sv = sd.getShowDetail(sidx);
			
			request.setAttribute("title", sv.getStitle());
			request.setAttribute("sidx", sidx);
			request.setAttribute("comDate", comDate);
			request.setAttribute("round", round);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/pay_step1.jsp").forward(request, response);
			
		}else if(str.equals("/Reservation/ReservationStep1Seat.do")) {
			
			
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/pay_step1_seat.jsp").forward(request, response);
			
		}else if(str.equals("/Reservation/ReservationStep2.do")) {
			
			String sidx_ = request.getParameter("sidx");
			String title = request.getParameter("title");
			String comDate = request.getParameter("comDate");
			String round = request.getParameter("round");
			
			
			System.out.println("title: " + title);
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
			
			//예매 좌석 등급 및 층수 구하기
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
			
			// 예매 좌석 등급 목록 (중복 제외) 구하기
			ArrayList<String> gradeCategory = new ArrayList<>();
			for(int i = 0 ; i < seatGrade.length ; i++) {
				if(!gradeCategory.contains(seatGrade[i])) {
					gradeCategory.add(seatGrade[i]);
				}
			}
			System.out.println("gradeCategory: " + gradeCategory);
			
			
			//HashMap을 이용하여 좌석 등급 별 개수 세기
			HashMap<String, Integer> gradeCount = new HashMap<String, Integer>(); //HashMap 생성
			
			for(int i = 0 ; i < seatGrade.length ; i++) { //seatGrade만큼 반복
				if(gradeCount.containsKey(seatGrade[i])) { //HashMap 내부에 이미 key값이 존재하는지 확인
					gradeCount.put(seatGrade[i], gradeCount.get(seatGrade[i])+1); //key가 있다면 해당 value에 1 추가
				}else {
					gradeCount.put(seatGrade[i], 1); //key가 없다면 key값을 생성 후 1로 초기화
				}
			}
			System.out.println("gradeCount: " + gradeCount);
			System.out.println("arraySeat 첫번째 확인: " + arraySeat[0]);
			
			request.setAttribute("sidx", sidx);
			request.setAttribute("title", title);
			request.setAttribute("comDate", comDate);
			request.setAttribute("round", round);
			request.setAttribute("arraySeat", arraySeat);
			request.setAttribute("seatGrade", seatGrade);
			request.setAttribute("gradeCategory", gradeCategory);
			request.setAttribute("floor", floor);
			request.setAttribute("gradeCount", gradeCount);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Pay_step2.jsp").forward(request, response);

//==============================================================================================================================//

		}else if(str.equals("/Reservation/ReservationStep3.do")) {
			
			//좌석 목록 받아오기
			ArrayList arraySeat = new ArrayList();
			String[] arraySeat_ = request.getParameterValues("arraySeat");
			for(int i = 0 ; i < arraySeat_.length ; i++) {
				arraySeat.add(arraySeat_[i]);
			}
			
			//할인 목록 받아오기
			ArrayList discountParameter = new ArrayList();
			String strDiscountParam = null;
			for(int i = 1 ; i <= 4 ; i ++) {
				for(int j = 1 ; j <= 8 ; j++) {
					strDiscountParam = request.getParameter("discount" + i + "_" + j);
					discountParameter.add("discount" + i + "_" + j + "/" + strDiscountParam);
				}
			}
			
			String sidx_ = request.getParameter("sidx");
			String title = request.getParameter("title");
			String comDate = request.getParameter("comDate");
			String round = request.getParameter("round");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			System.out.println("함 되는지 봅시다: " + arraySeat);
			System.out.println(discountParameter);
			
//==============================================================================================================================//
			
			request.setAttribute("sidx", sidx);
			request.setAttribute("title", title);
			request.setAttribute("comDate", comDate);
			request.setAttribute("round", round);
			request.setAttribute("arraySeat", arraySeat);
			request.setAttribute("discountParameter", discountParameter);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Pay_step3.jsp").forward(request, response);
			
//==============================================================================================================================//
		
		}else if(str.equals("/Reservation/ReservationStep4.do")) {
			
			//결제 정보 가져오기
			String pick = request.getParameter("pick");
			String name = request.getParameter("name");
			String tel1_ = request.getParameter("tel1");
			String tel2_ = request.getParameter("tel2");
			String tel3_ = request.getParameter("tel3");
			String tel = tel1_ + tel2_ + tel3_;
			String email = request.getParameter("email");
			String payMethodCode_ = request.getParameter("payMethodCode");
			String cardCode = request.getParameter("cardCode");
			String quota = request.getParameter("quota");
			
			System.out.println("수령방법: " + pick);
			System.out.println("예매자 이름: " + name);
			System.out.println("번호: " + tel);
			System.out.println("이메일: " + email);
			System.out.println("결제방법: " + payMethodCode_);
			System.out.println("은행 구분: " + cardCode);
			System.out.println("할부 개월수: " + quota);

			
			System.out.println(tel);
//==============================================================================================================================//				
			
			//기본 정보 가져오기
			String comDate = request.getParameter("comDate");
			String round = request.getParameter("round");
			
			String sidx_ = request.getParameter("sidx");
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
//==============================================================================================================================//				
			
			//할인 정보 가져오기
			ArrayList discountParameter = new ArrayList();
			String[] discountParameter_ = request.getParameterValues("discountParameter");
			for(int i = 0 ; i < discountParameter_.length ; i++) {
				discountParameter.add(discountParameter_[i]);
			}
			//좌석 정보 가져오기
			ArrayList arraySeat = new ArrayList();
			String[] arraySeat_ = request.getParameterValues("arraySeat");
			for(int i = 0 ; i < arraySeat_.length ; i++) {
				arraySeat.add(arraySeat_[i]);
			}
			
			System.out.println("배열 받는지 확인: " + discountParameter);
			System.out.println("배열 또 받는지 확인: " + arraySeat);
			
			//가격 선택 중 선택 된 값만 남기고 나머지 없애기, 여러번 선택된 것은 배열에 갯수만큼 더 써주기
			ArrayList discount = new ArrayList();
			for(int i = 0 ; i < discountParameter.size() ; i++) {
				String split_ = (String) discountParameter.get(i);
				String[] split =  split_.split("/");
				System.out.println(split[1].getClass().getName());
				if(!split[1].equals("null")) {
					if(Integer.parseInt(split[1]) >= 1) {
						for(int j = 0 ; j < Integer.parseInt(split[1]) ; j++){ 
							discount.add(split[0]);
						}
					}
				}
			}

//==============================================================================================================================//		
			
			//등급별로 나눠주기
			ArrayList vipDiscount = new ArrayList();
			ArrayList rDiscount = new ArrayList();
			ArrayList sDiscount = new ArrayList();
			ArrayList aDiscount = new ArrayList();
			ArrayList vipSeat = new ArrayList();
			ArrayList rSeat = new ArrayList();
			ArrayList sSeat = new ArrayList();
			ArrayList aSeat = new ArrayList();
			
			for(int i = 0 ; i < discount.size() ; i++) {
				if(((String)discount.get(i)).substring(8,9).equals("1")) {
					vipDiscount.add(discount.get(i));
				}else if(((String)discount.get(i)).substring(8,9).equals("2")) {
					rDiscount.add(discount.get(i));
				}else if(((String)discount.get(i)).substring(8,9).equals("3")) {
					sDiscount.add(discount.get(i));
				}else if(((String)discount.get(i)).substring(8,9).equals("4")) {
					aDiscount.add(discount.get(i));
				}
				
				if(Integer.parseInt(((String)arraySeat.get(i)).substring(0,2)) <= 6) {
					vipSeat.add(arraySeat.get(i));
				}else if(Integer.parseInt(((String)arraySeat.get(i)).substring(0,2)) > 6 && Integer.parseInt(((String)arraySeat.get(i)).substring(0,2)) <= 20) {
					rSeat.add(arraySeat.get(i));
				}else if(Integer.parseInt(((String)arraySeat.get(i)).substring(0,2)) > 20 && Integer.parseInt(((String)arraySeat.get(i)).substring(0,2)) <= 29) {
					aSeat.add(arraySeat.get(i));
				}else if(Integer.parseInt(((String)arraySeat.get(i)).substring(0,2)) > 29 && Integer.parseInt(((String)arraySeat.get(i)).substring(0,2)) <= 37) {
					sSeat.add(arraySeat.get(i));
				}
			}

//==============================================================================================================================//
			
			System.out.println("vipDiscount: " + vipDiscount);
			System.out.println("vipSeat: " + vipSeat);
			System.out.println("rDiscount: " + rDiscount);
			System.out.println("rSeat: " + rSeat);
			System.out.println("sDiscount: " + sDiscount);
			System.out.println("sSeat: " + sSeat);
			System.out.println("aDiscount: " + aDiscount);
			System.out.println("aSeat: " + aSeat);

			System.out.println(discount);
			System.out.println(arraySeat);
//==============================================================================================================================//		
			//구분해서 DB에 집어넣기
			ReservationDao rd = new ReservationDao();
			ReservationVo rv = new ReservationVo();
			rv.setSidx(sidx);
			rv.setMidx(midx);
			rv.setSrdate(comDate);
			rv.setSrround(round);
			if(vipDiscount != null) {
				for(int i = 0 ; i < vipDiscount.size() ; i++) {
					rv.setRseat((String)vipSeat.get(i));
					rv.setRdiscount((String)vipDiscount.get(i));
					rd.insertReservation(rv);
					System.out.println(rv.getRseat());
					System.out.println(rv.getRdiscount());
				}
			}
			if(rDiscount != null) {
				for(int i = 0 ; i < rDiscount.size() ; i++) {
					rv.setRseat((String)rSeat.get(i));
					rv.setRdiscount((String)rDiscount.get(i));
					rd.insertReservation(rv);
				}
			}
			if(sDiscount != null) {
				for(int i = 0 ; i < sDiscount.size() ; i++) {
					rv.setRseat((String)sSeat.get(i));
					rv.setRdiscount((String)sDiscount.get(i));
					rd.insertReservation(rv);
				}
			}
			if(aDiscount != null) {
				for(int i = 0 ; i < aDiscount.size() ; i++) {
					rv.setRseat((String)aSeat.get(i));
					rv.setRdiscount((String)aDiscount.get(i));
					rd.insertReservation(rv);
				}
			}
//==============================================================================================================================//	
			
			
			//팝업창 종료해주기
			PrintWriter pt = response.getWriter();
			pt.write("<script>self.close();</script>");
			pt.flush();
			pt.close();
		}
	}
	
}
