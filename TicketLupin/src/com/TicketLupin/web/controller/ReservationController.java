package com.TicketLupin.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TicketLupin.web.service.ShowDao;
import com.TicketLupin.web.service.ShowVo;

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
			String year = request.getParameter("year"); //���� �⵵
			String strMonth = request.getParameter("month"); //���� ��, 0���� ����, �� 0�� 1��
			String strDate = request.getParameter("date"); //���� ��, �̰� �� 1���� ����, �����Ÿ��� �̰� �򰥸���...
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
			
			ShowDao sd = new ShowDao();
			ShowVo sv = sd.getShowDetail(sidx);
			
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
			
			//���� �¼� ��� �� ���� ���ϱ�
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
				System.out.println("�� ���� ��� �׽�Ʈ: " + arraySeat[i].substring(0,2));
				System.out.println("arraySeat[" + i + "]: " + arraySeat[i]);
				System.out.println("arraySeat[" + i + "] ���: " + seatGrade[i]);
				System.out.println("arraySeat[" + i + "] ����: " + floor[i]);
			}
			
			// ���� �¼� ��� ��� (�ߺ� ����) ���ϱ�
			ArrayList<String> gradeCategory = new ArrayList<>();
			for(int i = 0 ; i < seatGrade.length ; i++) {
				if(!gradeCategory.contains(seatGrade[i])) {
					gradeCategory.add(seatGrade[i]);
				}
			}
			System.out.println("gradeCategory: " + gradeCategory);
			
			
			//HashMap�� �̿��Ͽ� �¼� ��� �� ���� ����
			HashMap<String, Integer> gradeCount = new HashMap<String, Integer>(); //HashMap ����
			
			for(int i = 0 ; i < seatGrade.length ; i++) { //seatGrade��ŭ �ݺ�
				if(gradeCount.containsKey(seatGrade[i])) { //HashMap ���ο� �̹� key���� �����ϴ��� Ȯ��
					gradeCount.put(seatGrade[i], gradeCount.get(seatGrade[i])+1); //key�� �ִٸ� �ش� value�� 1 �߰�
				}else {
					gradeCount.put(seatGrade[i], 1); //key�� ���ٸ� key���� ���� �� 1�� �ʱ�ȭ
				}
			}
			System.out.println("gradeCount: " + gradeCount);
			
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
			
			//��޺� �⺻ �� ���� ���� �޾ƿ���
			
			String vipBasic_ = request.getParameter("vipBasic");
			String vipSpecial_ = request.getParameter("vipSpecial");
			String vip3Package_ = request.getParameter("vip3Package");
			String vip4Package_ = request.getParameter("vip4Package");
			String vipYouth_ = request.getParameter("vipYouth");
			String vip4to6_ = request.getParameter("vip4to6");
			String vip1to3_ = request.getParameter("vip1to3");
			String vipVeterans_ = request.getParameter("vipVeterans");
			
			int vipBasic = 0;
			if(vipBasic_ != null && !vipBasic_.equals("")) {
				vipBasic = Integer.parseInt(vipBasic_);
			}
			int vipSpecial = 0;
			if(vipSpecial_ != null && !vipSpecial_.equals("")) {
				vipSpecial = Integer.parseInt(vipSpecial_);
			}
			int vip3Package = 0;
			if(vip3Package_ != null && !vip3Package_.equals("")) {
				vip3Package = Integer.parseInt(vip3Package_);
			}
			int vip4Package = 0;
			if(vip4Package_ != null && !vip4Package_.equals("")) {
				vip4Package = Integer.parseInt(vip4Package_);
			}
			int vipYouth = 0;
			if(vipYouth_ != null && !vipYouth_.equals("")) {
				vipYouth = Integer.parseInt(vipYouth_);
			}
			int vip4to6 = 0;
			if(vip4to6_ != null && !vip4to6_.equals("")) {
				vip4to6 = Integer.parseInt(vip4to6_);
			}
			int vip1to3 = 0;
			if(vip1to3_ != null && !vip1to3_.equals("")) {
				vip1to3 = Integer.parseInt(vip1to3_);
			}
			int vipVeterans = 0;
			if(vipVeterans_ != null && !vipVeterans_.equals("")) {
				vipVeterans = Integer.parseInt(vipVeterans_);
			}
			
			String rBasic_ = request.getParameter("rBasic");
			String rSpecial_ = request.getParameter("rSpecial");
			String r3Package_ = request.getParameter("r3Package");
			String r4Package_ = request.getParameter("r4Package");
			String rYouth_ = request.getParameter("rYouth");
			String r4to6_ = request.getParameter("r4to6");
			String r1to3_ = request.getParameter("r1to3");
			String rVeterans_ = request.getParameter("rVeterans");
			
			int rBasic = 0;
			if(rBasic_ != null && !rBasic_.equals("")) {
				rBasic = Integer.parseInt(rBasic_);
			}
			int rSpecial = 0;
			if(rSpecial_ != null && !rSpecial_.equals("")) {
				rSpecial = Integer.parseInt(rSpecial_);
			}
			int r3Package = 0;
			if(r3Package_ != null && !r3Package_.equals("")) {
				r3Package = Integer.parseInt(r3Package_);
			}
			int r4Package = 0;
			if(r4Package_ != null && !r4Package_.equals("")) {
				r4Package = Integer.parseInt(r4Package_);
			}
			int rYouth = 0;
			if(rYouth_ != null && !rYouth_.equals("")) {
				rYouth = Integer.parseInt(rYouth_);
			}
			int r4to6 = 0;
			if(r4to6_ != null && !r4to6_.equals("")) {
				r4to6 = Integer.parseInt(r4to6_);
			}
			int r1to3 = 0;
			if(r1to3_ != null && !r1to3_.equals("")) {
				r1to3 = Integer.parseInt(r1to3_);
			}
			int rVeterans = 0;
			if(rVeterans_ != null && !rVeterans_.equals("")) {
				rVeterans = Integer.parseInt(rVeterans_);
			}
			
			String sBasic_ = request.getParameter("sBasic");
			String sSpecial_ = request.getParameter("sSpecial");
			String s3Package_ = request.getParameter("s3Package");
			String s4Package_ = request.getParameter("s4Package");
			String sYouth_ = request.getParameter("sYouth");
			String s4to6_ = request.getParameter("s4to6");
			String s1to3_ = request.getParameter("s1to3");
			String sVeterans_ = request.getParameter("sVeterans");
			
			int sBasic = 0;
			if(sBasic_ != null && !sBasic_.equals("")) {
				sBasic = Integer.parseInt(sBasic_);
			}
			int sSpecial = 0;
			if(sSpecial_ != null && !sSpecial_.equals("")) {
				sSpecial = Integer.parseInt(sSpecial_);
			}
			int s3Package = 0;
			if(s3Package_ != null && !s3Package_.equals("")) {
				s3Package = Integer.parseInt(s3Package_);
			}
			int s4Package = 0;
			if(s4Package_ != null && !s4Package_.equals("")) {
				s4Package = Integer.parseInt(s4Package_);
			}
			int sYouth = 0;
			if(sYouth_ != null && !sYouth_.equals("")) {
				sYouth = Integer.parseInt(sYouth_);
			}
			int s4to6 = 0;
			if(s4to6_ != null && !s4to6_.equals("")) {
				s4to6 = Integer.parseInt(s4to6_);
			}
			int s1to3 = 0;
			if(s1to3_ != null && !s1to3_.equals("")) {
				s1to3 = Integer.parseInt(s1to3_);
			}
			int sVeterans = 0;
			if(sVeterans_ != null && !sVeterans_.equals("")) {
				sVeterans = Integer.parseInt(sVeterans_);
			}
			
			String aBasic_ = request.getParameter("aBasic");
			String aSpecial_ = request.getParameter("aSpecial");
			String a3Package_ = request.getParameter("a3Package");
			String a4Package_ = request.getParameter("a4Package");
			String aYouth_ = request.getParameter("aYouth");
			String a4to6_ = request.getParameter("a4to6");
			String a1to3_ = request.getParameter("a1to3");
			String aVeterans_ = request.getParameter("aVeterans");
			
			int aBasic = 0;
			if(aBasic_ != null && !aBasic_.equals("")) {
				aBasic = Integer.parseInt(aBasic_);
			}
			int aSpecial = 0;
			if(aSpecial_ != null && !aSpecial_.equals("")) {
				aSpecial = Integer.parseInt(aSpecial_);
			}
			int a3Package = 0;
			if(a3Package_ != null && !a3Package_.equals("")) {
				a3Package = Integer.parseInt(a3Package_);
			}
			int a4Package = 0;
			if(a4Package_ != null && !a4Package_.equals("")) {
				a4Package = Integer.parseInt(a4Package_);
			}
			int aYouth = 0;
			if(aYouth_ != null && !aYouth_.equals("")) {
				aYouth = Integer.parseInt(aYouth_);
			}
			int a4to6 = 0;
			if(a4to6_ != null && !a4to6_.equals("")) {
				a4to6 = Integer.parseInt(a4to6_);
			}
			int a1to3 = 0;
			if(a1to3_ != null && !a1to3_.equals("")) {
				a1to3 = Integer.parseInt(a1to3_);
			}
			int aVeterans = 0;
			if(aVeterans_ != null && !aVeterans_.equals("")) {
				aVeterans = Integer.parseInt(aVeterans_);
			}
//==============================================================================================================================//
			//�� ������

			request.setAttribute("vipBasic", vipBasic);
			request.setAttribute("vipSpecial", vipSpecial);
			request.setAttribute("vip3Package", vip3Package);
			request.setAttribute("vip4Package", vip4Package);
			request.setAttribute("vipYouth", vipYouth);
			request.setAttribute("vip4to6", vip4to6);
			request.setAttribute("vip1to3", vip1to3);
			request.setAttribute("vipVeterans", vipVeterans);
			
			request.setAttribute("rBasic", rBasic);
			request.setAttribute("rSpecial", rSpecial);
			request.setAttribute("r3Package", r3Package);
			request.setAttribute("r4Package", r4Package);
			request.setAttribute("rYouth", rYouth);
			request.setAttribute("r4to6", r4to6);
			request.setAttribute("r1to3", r1to3);
			request.setAttribute("rVeterans", rVeterans);
			
			request.setAttribute("sBasic", sBasic);
			request.setAttribute("sSpecial", sSpecial);
			request.setAttribute("s3Package", s3Package);
			request.setAttribute("s4Package", s4Package);
			request.setAttribute("sYouth", sYouth);
			request.setAttribute("s4to6", s4to6);
			request.setAttribute("s1to3", s1to3);
			request.setAttribute("sVeterans", sVeterans);
			
			request.setAttribute("aBasic", aBasic);
			request.setAttribute("aSpecial", aSpecial);
			request.setAttribute("a3Package", a3Package);
			request.setAttribute("a4Package", a4Package);
			request.setAttribute("aYouth", aYouth);
			request.setAttribute("a4to6", a4to6);
			request.setAttribute("a1to3", a1to3);
			request.setAttribute("aVeterans", aVeterans);
			
//==============================================================================================================================//

			//�� �� �ڷ�� �޾ƿ���
			
			String sidx_ = request.getParameter("sidx");
			String title = request.getParameter("title");
			String comDate = request.getParameter("comDate");
			String round = request.getParameter("round");
			String priceSum_ = request.getParameter("priceSumP");
			String basicSum_ = request.getParameter("basicSumP");
			String discountSum_ = request.getParameter("discountSumP");
			String couponSum_ = request.getParameter("couponSumP");
			String paymentAmount_ = request.getParameter("paymentAmount");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			int priceSum = 0;
			if(priceSum_ != null && !priceSum_.equals("")) {
				priceSum = Integer.parseInt(priceSum_);
			}
			int basicSum = 0;
			if(basicSum_ != null && !basicSum_.equals("")) {
				basicSum = Integer.parseInt(basicSum_);
			}
			int discountSum = 0;
			if(discountSum_ != null && !discountSum_.equals("")) {
				discountSum = Integer.parseInt(discountSum_);
			}
			int couponSum = 0;
			if(couponSum_ != null && !couponSum_.equals("")) {
				couponSum = Integer.parseInt(couponSum_);
			}
			int paymentAmount = 0;
			if(paymentAmount_ != null && !paymentAmount_.equals("")) {
				paymentAmount = Integer.parseInt(paymentAmount_);
			}
			
//==============================================================================================================================//
			//�� ������

			request.setAttribute("sidx", sidx);
			request.setAttribute("priceSum", priceSum);
			request.setAttribute("basicSum", basicSum);
			request.setAttribute("discountSum", discountSum);
			request.setAttribute("couponSum", couponSum);
			request.setAttribute("paymentAmount", paymentAmount);
			
//==============================================================================================================================//
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Pay_step3.jsp").forward(request, response);
			
		}else if(str.equals("/Reservation/ReservationStep4.do")) {
			
			//��޺� �⺻ �� ���� ���� �޾ƿ���
			
			String vipBasic_ = request.getParameter("vipBasic");
			String vipSpecial_ = request.getParameter("vipSpecial");
			String vip3Package_ = request.getParameter("vip3Package");
			String vip4Package_ = request.getParameter("vip4Package");
			String vipYouth_ = request.getParameter("vipYouth");
			String vip4to6_ = request.getParameter("vip4to6");
			String vip1to3_ = request.getParameter("vip1to3");
			String vipVeterans_ = request.getParameter("vipVeterans");
			
			int vipBasic = 0;
			if(vipBasic_ != null && !vipBasic_.equals("")) {
				vipBasic = Integer.parseInt(vipBasic_);
			}
			int vipSpecial = 0;
			if(vipSpecial_ != null && !vipSpecial_.equals("")) {
				vipSpecial = Integer.parseInt(vipSpecial_);
			}
			int vip3Package = 0;
			if(vip3Package_ != null && !vip3Package_.equals("")) {
				vip3Package = Integer.parseInt(vip3Package_);
			}
			int vip4Package = 0;
			if(vip4Package_ != null && !vip4Package_.equals("")) {
				vip4Package = Integer.parseInt(vip4Package_);
			}
			int vipYouth = 0;
			if(vipYouth_ != null && !vipYouth_.equals("")) {
				vipYouth = Integer.parseInt(vipYouth_);
			}
			int vip4to6 = 0;
			if(vip4to6_ != null && !vip4to6_.equals("")) {
				vip4to6 = Integer.parseInt(vip4to6_);
			}
			int vip1to3 = 0;
			if(vip1to3_ != null && !vip1to3_.equals("")) {
				vip1to3 = Integer.parseInt(vip1to3_);
			}
			int vipVeterans = 0;
			if(vipVeterans_ != null && !vipVeterans_.equals("")) {
				vipVeterans = Integer.parseInt(vipVeterans_);
			}
			
			String rBasic_ = request.getParameter("rBasic");
			String rSpecial_ = request.getParameter("rSpecial");
			String r3Package_ = request.getParameter("r3Package");
			String r4Package_ = request.getParameter("r4Package");
			String rYouth_ = request.getParameter("rYouth");
			String r4to6_ = request.getParameter("r4to6");
			String r1to3_ = request.getParameter("r1to3");
			String rVeterans_ = request.getParameter("rVeterans");
			
			int rBasic = 0;
			if(rBasic_ != null && !rBasic_.equals("")) {
				rBasic = Integer.parseInt(rBasic_);
			}
			int rSpecial = 0;
			if(rSpecial_ != null && !rSpecial_.equals("")) {
				rSpecial = Integer.parseInt(rSpecial_);
			}
			int r3Package = 0;
			if(r3Package_ != null && !r3Package_.equals("")) {
				r3Package = Integer.parseInt(r3Package_);
			}
			int r4Package = 0;
			if(r4Package_ != null && !r4Package_.equals("")) {
				r4Package = Integer.parseInt(r4Package_);
			}
			int rYouth = 0;
			if(rYouth_ != null && !rYouth_.equals("")) {
				rYouth = Integer.parseInt(rYouth_);
			}
			int r4to6 = 0;
			if(r4to6_ != null && !r4to6_.equals("")) {
				r4to6 = Integer.parseInt(r4to6_);
			}
			int r1to3 = 0;
			if(r1to3_ != null && !r1to3_.equals("")) {
				r1to3 = Integer.parseInt(r1to3_);
			}
			int rVeterans = 0;
			if(rVeterans_ != null && !rVeterans_.equals("")) {
				rVeterans = Integer.parseInt(rVeterans_);
			}
			
			String sBasic_ = request.getParameter("sBasic");
			String sSpecial_ = request.getParameter("sSpecial");
			String s3Package_ = request.getParameter("s3Package");
			String s4Package_ = request.getParameter("s4Package");
			String sYouth_ = request.getParameter("sYouth");
			String s4to6_ = request.getParameter("s4to6");
			String s1to3_ = request.getParameter("s1to3");
			String sVeterans_ = request.getParameter("sVeterans");
			
			int sBasic = 0;
			if(sBasic_ != null && !sBasic_.equals("")) {
				sBasic = Integer.parseInt(sBasic_);
			}
			int sSpecial = 0;
			if(sSpecial_ != null && !sSpecial_.equals("")) {
				sSpecial = Integer.parseInt(sSpecial_);
			}
			int s3Package = 0;
			if(s3Package_ != null && !s3Package_.equals("")) {
				s3Package = Integer.parseInt(s3Package_);
			}
			int s4Package = 0;
			if(s4Package_ != null && !s4Package_.equals("")) {
				s4Package = Integer.parseInt(s4Package_);
			}
			int sYouth = 0;
			if(sYouth_ != null && !sYouth_.equals("")) {
				sYouth = Integer.parseInt(sYouth_);
			}
			int s4to6 = 0;
			if(s4to6_ != null && !s4to6_.equals("")) {
				s4to6 = Integer.parseInt(s4to6_);
			}
			int s1to3 = 0;
			if(s1to3_ != null && !s1to3_.equals("")) {
				s1to3 = Integer.parseInt(s1to3_);
			}
			int sVeterans = 0;
			if(sVeterans_ != null && !sVeterans_.equals("")) {
				sVeterans = Integer.parseInt(sVeterans_);
			}
			
			String aBasic_ = request.getParameter("aBasic");
			String aSpecial_ = request.getParameter("aSpecial");
			String a3Package_ = request.getParameter("a3Package");
			String a4Package_ = request.getParameter("a4Package");
			String aYouth_ = request.getParameter("aYouth");
			String a4to6_ = request.getParameter("a4to6");
			String a1to3_ = request.getParameter("a1to3");
			String aVeterans_ = request.getParameter("aVeterans");
			
			int aBasic = 0;
			if(aBasic_ != null && !aBasic_.equals("")) {
				aBasic = Integer.parseInt(aBasic_);
			}
			int aSpecial = 0;
			if(aSpecial_ != null && !aSpecial_.equals("")) {
				aSpecial = Integer.parseInt(aSpecial_);
			}
			int a3Package = 0;
			if(a3Package_ != null && !a3Package_.equals("")) {
				a3Package = Integer.parseInt(a3Package_);
			}
			int a4Package = 0;
			if(a4Package_ != null && !a4Package_.equals("")) {
				a4Package = Integer.parseInt(a4Package_);
			}
			int aYouth = 0;
			if(aYouth_ != null && !aYouth_.equals("")) {
				aYouth = Integer.parseInt(aYouth_);
			}
			int a4to6 = 0;
			if(a4to6_ != null && !a4to6_.equals("")) {
				a4to6 = Integer.parseInt(a4to6_);
			}
			int a1to3 = 0;
			if(a1to3_ != null && !a1to3_.equals("")) {
				a1to3 = Integer.parseInt(a1to3_);
			}
			int aVeterans = 0;
			if(aVeterans_ != null && !aVeterans_.equals("")) {
				aVeterans = Integer.parseInt(aVeterans_);
			}
			
//==============================================================================================================================//

			//�� �� �ڷ�� �޾ƿ���
			
			String sidx_ = request.getParameter("sidx");
			String title = request.getParameter("title");
			String comDate = request.getParameter("comDate");
			String round = request.getParameter("round");
			String priceSum_ = request.getParameter("priceSumP");
			String basicSum_ = request.getParameter("basicSumP");
			String discountSum_ = request.getParameter("discountSumP");
			String couponSum_ = request.getParameter("couponSumP");
			String paymentAmount_ = request.getParameter("paymentAmount");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			int priceSum = 0;
			if(priceSum_ != null && !priceSum_.equals("")) {
				priceSum = Integer.parseInt(priceSum_);
			}
			int basicSum = 0;
			if(basicSum_ != null && !basicSum_.equals("")) {
				basicSum = Integer.parseInt(basicSum_);
			}
			int discountSum = 0;
			if(discountSum_ != null && !discountSum_.equals("")) {
				discountSum = Integer.parseInt(discountSum_);
			}
			int couponSum = 0;
			if(couponSum_ != null && !couponSum_.equals("")) {
				couponSum = Integer.parseInt(couponSum_);
			}
			int paymentAmount = 0;
			if(paymentAmount_ != null && !paymentAmount_.equals("")) {
				paymentAmount = Integer.parseInt(paymentAmount_);
			}
			

//==============================================================================================================================//

			String pick_ = request.getParameter("pick");
			String name = request.getParameter("name");
			String tel1_ = request.getParameter("tel1");
			String tel2_ = request.getParameter("tel2");
			String tel3_ = request.getParameter("tel3");
			String tel_ = tel1_ + tel2_ + tel3_;
			String email = request.getParameter("email");
			String payMethodCode_ = request.getParameter("payMethodCode");
			String cardCode = request.getParameter("cardCode");
			String quota = request.getParameter("quota");
			
			String pick = null;
			if(pick_.equals("1")) {
				pick = "����� Ƽ��";
			}else if(pick_.equals("2")) {
				pick = "���� ����";
			}else if(pick_.equals("3")) {
				pick = "���";
			}
			
			int tel = 0;
			if(tel_ != null && !tel_.equals("")) {
				tel = Integer.parseInt(tel_);
			}
			
			String payMethodCode = null;
			if(payMethodCode_.equals("credit")) {
				payMethodCode = "�ſ�ī��";
			}else if(payMethodCode_.equals("bank")) {
				payMethodCode = "������ �Ա�";
			}else if(payMethodCode_.equals("phone")) {
				payMethodCode = "�޴��� ����";
			}else if(payMethodCode_.equals("kakaoM")) {
				payMethodCode = "īī������ �Ӵ�";
			}else if(payMethodCode_.equals("kakaoP")) {
				payMethodCode = "īī������ ī��";
			}
			
		}
		
	}
	
}
