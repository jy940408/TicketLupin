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
import com.TicketLupin.web.service.ReservationIdxVo;
import com.TicketLupin.web.service.ReservationListVo;
import com.TicketLupin.web.service.ReservationShowVo;
import com.TicketLupin.web.service.ReservationVo;
import com.TicketLupin.web.service.ShowDao;

import util.reservationMail;

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
				month = "0" + month;
			}
			System.out.println("달 테스트: " + month);
			
			String date = Integer.toString(date_);
			if((int)(Math.log10(date_)+1) == 1) {
				date = "0" + date_;
			}
			
			//예매 공연 날짜 완성하기
			String comDate = year + "-" + (month) + "-" + date;
			System.out.println("comDate: " + comDate);
			
			ShowDao sd = new ShowDao();
			Show1Vo sv = sd.getShowDetail(sidx);
			
			System.out.println(sv);
			request.setAttribute("detail", sv);
			request.setAttribute("sidx", sidx);
			request.setAttribute("comDate", comDate);
			request.setAttribute("round", round);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/pay_step1.jsp").forward(request, response);
			
		}else if(str.equals("/Reservation/ReservationStep1Seat.do")) {
			
			String sidx_ = request.getParameter("sidx");
			String srdate = request.getParameter("srdate");
			String srround = request.getParameter("srround");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			System.out.println("좌석목록 ridx_: " + sidx_);
			System.out.println("좌석목록 ridx: " + sidx);
			System.out.println("좌석목록 srdate: " + srdate);
			System.out.println("좌석목록 srround: " + srround);
			
			ReservationDao rd = new ReservationDao();
			ArrayList<ReservationShowVo> list = rd.getReservationSeatList(sidx, srdate, srround);
			
			for(int i = 0 ; i < list.size() ; i++) {
				System.out.println("좌석 목록 테스트: " + list.get(i).getRseat());
			}
			
			request.setAttribute("list", list);
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
			
			if(!gradeCount.containsKey("VIP")) {
				gradeCount.put("VIP", 0);
			}
			if(!gradeCount.containsKey("R")) {
				gradeCount.put("R", 0);
			}
			if(!gradeCount.containsKey("S")) {
				gradeCount.put("S", 0);
			}
			if(!gradeCount.containsKey("A")) {
				gradeCount.put("A", 0);
			}
			
			System.out.println("gradeCount: " + gradeCount);
			System.out.println("arraySeat 첫번째 확인: " + arraySeat[0]);
			
			//가격 불러오기 용도 공연 디테일 항목 가져오기
			ShowDao sd = new ShowDao();
			Show1Vo sv = new Show1Vo();
			sv = sd.getShowDetail(sidx);
			
			request.setAttribute("sidx", sidx);
			request.setAttribute("title", title);
			request.setAttribute("comDate", comDate);
			request.setAttribute("round", round);
			request.setAttribute("arraySeat", arraySeat);
			request.setAttribute("seatGrade", seatGrade);
			request.setAttribute("gradeCategory", gradeCategory);
			request.setAttribute("floor", floor);
			request.setAttribute("gradeCount", gradeCount);
			request.setAttribute("detail", sv);
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
				for(int j = 1 ; j <= 6 ; j++) {
					strDiscountParam = request.getParameter("discount" + i + "_" + j);
					discountParameter.add("discount" + i + "_" + j + "/" + strDiscountParam);
				}
			}
			
			String basicSum_ = request.getParameter("basicSum");
			String basicSumVAT_ = request.getParameter("basicSumVAT");
			String discountSum_ = request.getParameter("discountSum");
			String discountSumVAT_ = request.getParameter("discountSumVAT");
			String priceSum_ = request.getParameter("priceSum");
			String paymentAmount_ = request.getParameter("paymentAmount");
			
			System.out.println("priceSum_ 제대로 받는지 확인: " + priceSum_);
			
			int basicSum = 0;
			if(basicSum_ != null && !basicSum_.equals("")) {
				basicSum = Integer.parseInt(basicSum_);
			}
			int basicSumVAT = 0;
			if(basicSumVAT_ != null && !basicSumVAT_.equals("")) {
				basicSumVAT = Integer.parseInt(basicSumVAT_);
			}
			int discountSum = 0;
			if(discountSum_ != null && !discountSum_.equals("")) {
				discountSum = Integer.parseInt(discountSum_);
			}
			int discountSumVAT = 0;
			if(discountSumVAT_ != null && !discountSumVAT_.equals("")) {
				discountSumVAT = Integer.parseInt(discountSumVAT_);
			}
			int priceSum = 0;
			if(priceSum_ != null && !priceSum_.equals("")) {
				priceSum = Integer.parseInt(priceSum_);
			}
			int paymentAmount = 0;
			if(paymentAmount_ != null && !paymentAmount_.equals("")) {
				paymentAmount = Integer.parseInt(paymentAmount_);
			}
			System.out.println("basicSum 테스트: " + basicSum);
			System.out.println("discountSum 테스트: " + discountSum);
			System.out.println("priceSum 테스트: " + priceSum);
			
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
			
			request.setAttribute("basicSum", basicSum);
			request.setAttribute("basicSumVAT", basicSumVAT);
			request.setAttribute("discountSum", discountSum);
			request.setAttribute("discountSumVAT", discountSumVAT);
			request.setAttribute("priceSum", priceSum);
			request.setAttribute("paymentAmount", paymentAmount);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Pay_step3.jsp").forward(request, response);
			
//==============================================================================================================================//
		
		}else if(str.equals("/Reservation/ReservationStep4.do")) {

//==============================================================================================================================//

			//결제 금액 가져오기
			String basicSum_ = request.getParameter("basicSum");
			String basicSumVAT_ = request.getParameter("basicSumVAT");
			String discountSum_ = request.getParameter("discountSum");
			String discountSumVAT_ = request.getParameter("discountSumVAT");
			String priceSum_ = request.getParameter("priceSum");
			String deliverySum_ = request.getParameter("deliverySum");
			String paymentSum_ = request.getParameter("paymentSum");
			
			System.out.println("basicSum_ 확인: " + basicSum_);
			System.out.println("basicSumVAT_ 확인: " + basicSumVAT_);
			System.out.println("discountSum_ 확인: " + discountSum_);
			System.out.println("discountSumVAT_ 확인: " + discountSumVAT_);
			System.out.println("priceSum_ 확인: " + priceSum_);
			System.out.println("deliverySum_ 확인: " + deliverySum_);
			System.out.println("paymentSum_ 확인: " + paymentSum_);
			
			int basicSum = 0;
			if(basicSum_ != null && !basicSum_.equals("")) {
				basicSum = Integer.parseInt(basicSum_);
			}
			int basicSumVAT = 0;
			if(basicSumVAT_ != null && !basicSumVAT_.equals("")) {
				basicSumVAT = Integer.parseInt(basicSumVAT_);
			}
			int discountSum = 0;
			if(discountSum_ != null && !discountSum_.equals("")) {
				discountSum = Integer.parseInt(discountSum_);
			}
			int discountSumVAT = 0;
			if(discountSumVAT_ != null && !discountSumVAT_.equals("")) {
				discountSumVAT = Integer.parseInt(discountSumVAT_);
			}
			int priceSum = 0;
			if(priceSum_ != null && !priceSum_.equals("")) {
				priceSum = Integer.parseInt(priceSum_);
			}
			int deliverySum = 0;
			if(deliverySum_ != null && !deliverySum_.equals("")) {
				deliverySum = Integer.parseInt(deliverySum_);
			}
			int paymentSum = 0;
			if(paymentSum_ != null && !paymentSum_.equals("")) {
				paymentSum = Integer.parseInt(paymentSum_);
			}
//==============================================================================================================================//
			
			//결제 정보 가져오기
			String pick_ = request.getParameter("pick");
			String name = request.getParameter("name");
			String tel1_ = request.getParameter("tel1");
			String tel2_ = request.getParameter("tel2");
			String tel3_ = request.getParameter("tel3");
			String tel = tel1_ + tel2_ + tel3_;
			String email = request.getParameter("email");
			String payMethodCode = request.getParameter("payMethodCode");
			String cardCode = request.getParameter("cardCode");
			String quota = request.getParameter("quota");
			
			System.out.println("수령방법: " + pick_);
			System.out.println("예매자 이름: " + name);
			System.out.println("번호: " + tel);
			System.out.println("이메일: " + email);
			System.out.println("결제방법: " + payMethodCode);
			System.out.println("은행 구분: " + cardCode);
			System.out.println("할부 개월수: " + quota);
			
//==============================================================================================================================//

			//코드 바꿔주기
			String pick = null;
			if(pick_.equals("mobile")) {
				pick = "모바일 티켓";
			}else if(pick_.equals("pickup")) {
				pick = "현장수령";
			}else if(pick_.equals("delivery")) {
				pick = "배송";
			}
			
			String payMethod = null;
			if(payMethodCode.equals("credit")) {
				payMethod = "신용카드";
			}else if(payMethodCode.equals("bank")) {
				payMethod = "무통장입금";
			}else if(payMethodCode.equals("phone")) {
				payMethod = "휴대폰 결제";
			}else if(payMethodCode.equals("kakaoM")) {
				payMethod = "카카오페이 머니";
			}else if(payMethodCode.equals("kakaoP")) {
				payMethod = "카카오페이 카드";
			}
			
			String card = null;
			if(cardCode.equals("SAMSUNG")) {
				card = "삼성카드";
			}else if(cardCode.equals("KB")) {
				card = "KB국민카드";
			}else if(cardCode.equals("HYUNDAI")) {
				card = "현대카드";
			}else if(cardCode.equals("BC")) {
				card = "BC카드";
			}else if(cardCode.equals("SHINHAN")) {
				card = "신한카드";
			}else if(cardCode.equals("NH")) {
				card = "NH농협카드";
			}else if(cardCode.equals("NHMH")) {
				card = "NH문화누리카드";
			}else if(cardCode.equals("HANA_SK")) {
				card = "하나카드";
			}else if(cardCode.equals("LOTTE")) {
				card = "롯데카드";
			}else if(cardCode.equals("CITI")) {
				card = "씨티카드";
			}else if(cardCode.equals("KAKAOBANK")) {
				card = "카카오뱅크카드";
			}else if(cardCode.equals("BCKA")) {
				card = "카카오페이카드";
			}else if(cardCode.equals("KBANK")) {
				card = "케이뱅크카드";
			}else if(cardCode.equals("WOORI")) {
				card = "우리카드";
			}else if(cardCode.equals("GWANGJU")) {
				card = "광주카드";
			}else if(cardCode.equals("JEONBOOK")) {
				card = "전북카드";
			}else if(cardCode.equals("SOOHYUP")) {
				card = "수협카드";
			}else if(cardCode.equals("KDB")) {
				card = "KDB산업은행카드";
			}else if(cardCode.equals("JEJU")) {
				card = "제주카드";
			}
//==============================================================================================================================//				
			
			//기본 정보 가져오기
			
			String title = request.getParameter("title");
			String comDate = request.getParameter("comDate");
			String round = request.getParameter("round");
			
			System.out.println("round 확인: " + round);
			
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
			ReservationIdxVo riv = new ReservationIdxVo();
			
			riv.setSidx(sidx);
			riv.setMidx(midx);
			riv.setSrdate(comDate);
			riv.setSrround(round);
			riv.setRibasic(basicSum);
			riv.setRidiscount(discountSum);
			riv.setRivat(basicSumVAT + discountSumVAT);
			riv.setRidelivery(deliverySum);
			riv.setRipayment(paymentSum);
			
			rd.insertReservationIdx(riv);
			
			int riidx = rd.getReservaionRecentIdx(sidx, midx);
			rv.setSidx(sidx);
			rv.setMidx(midx);
			rv.setSrdate(comDate);
			rv.setSrround(round);
			rv.setRpick(pick);
			rv.setRname(name);
			rv.setRtel(tel);
			rv.setRemail(email);
			rv.setRpaymethod(payMethod);
			rv.setRcard(card);
			rv.setRquota(quota);
			rv.setRiidx(riidx);
			System.out.println("rv 테스트: " + rv);
			System.out.println("테스트: " + rv.getRseat());
			
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
			
			reservationMail mail = new reservationMail();
			mail.naverMailSend(title, arraySeat, sidx, comDate, round, name, tel, email, pick, payMethod, paymentSum, basicSum, discountSum, basicSumVAT + discountSumVAT, deliverySum);
			
			//팝업창 종료해주기
			PrintWriter pt = response.getWriter();
			pt.write("<script>self.close();</script>");
			pt.flush();
			pt.close();
			
		}else if(str.equals("/Reservation/deleteReservation.do")) {
			
			String riidx_ = request.getParameter("riidx");
			int riidx = 0;
			if(riidx_ != null && !riidx_.equals("")) {
				riidx = Integer.parseInt(riidx_);
			}
			
			System.out.println("delete부분 ridx: " + riidx);
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			System.out.println("delete부분 midx: " + midx);
			
			ReservationDao rd = new ReservationDao();
			rd.deleteReservationIDX(riidx, midx);
			rd.deleteReservation(riidx, midx);
			System.out.println("rd: " + rd);
			
			PrintWriter pt = response.getWriter();
			pt.write("<script>self.close();</script>");
			pt.flush();
			pt.close();
			
//==============================================================================================================================//
		
		}
	}
	
}
