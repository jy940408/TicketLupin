package com.TicketLupin.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//가상경로와 같은지 확인한다
		String uri = request.getRequestURI();
		String projectname = request.getContextPath();
		int jarinum = projectname.length();
		String str = uri.substring(jarinum);
		System.out.println("get" + uri);
		// /board/boardList.do
		
		String[] po = str.split("/");
		String hc = po[1];
		
		if (hc.equals("Member")) {
			MemberController memberc = new MemberController();
			memberc.doGet(request, response);			
		}else if (hc.equals("Show")) {			
			ShowController showc = new ShowController();
			showc.doGet(request, response);			
		}else if (hc.equals("Notice")) {			
			NoticeController noticec = new NoticeController();
			noticec.doGet(request, response);					
		}else if (hc.equals("Event")) {			
			EventController eventc = new EventController();
			eventc.doGet(request, response);			
		}else if (hc.equals("E_comment")) {			
			E_commentController ecommentc = new E_commentController();
			ecommentc.doGet(request, response);			
		}else if (hc.equals("News")) {			
			NewsController newsc = new NewsController();
			newsc.doGet(request, response);					
		}else if (hc.equals("Winner")) {			
			WinnerController winnerc = new WinnerController();
			winnerc.doGet(request, response);			
		}else if (hc.equals("Reservation")) {			
			ReservationController reservationc = new ReservationController();
			reservationc.doGet(request, response);			
		}else if (hc.equals("Soldout")) {			
			SoldoutController soldoutc = new SoldoutController();
			soldoutc.doGet(request, response);			
		}else if (hc.equals("Actor")) {			
			ActorController actorc = new ActorController();
			actorc.doGet(request, response);			
		}else if (hc.equals("Pay")) {			
			PayController payc = new PayController();
			payc.doGet(request, response);			
		}else if (hc.equals("Review")) {			
			ReviewController reviewc = new ReviewController();
			reviewc.doGet(request, response);		
		}else if (hc.equals("Dibs")) {			
			DibsController dibsc = new DibsController();
			dibsc.doGet(request, response);
		}else if (hc.equals("Question")) {			
			QuestionController questionc = new QuestionController();
			questionc.doGet(request, response);
		}else if (hc.equals("Answer")) {			
			AnswerController answerc = new AnswerController();
			answerc.doGet(request, response);
		}else if (hc.equals("Coupon")) {			
			CouponController couponc = new CouponController();
			couponc.doGet(request, response);
		}else if (hc.equals("Expect")) {			
			ExpectController expectc = new ExpectController();
			expectc.doGet(request, response);
		}else if (hc.equals("Main")) {
			MainController mainc = new MainController();
			mainc.doGet(request, response);
		}else if (hc.equals("Manager")) {
			ManagerController managerc = new ManagerController();
			managerc.doGet(request, response);
		}else if (hc.equals("Myticket")) {
			MyticketController myticketc = new MyticketController();
			myticketc.doGet(request, response);
		}else if (hc.equals("ConcertView")) {
			ConcertViewController concertviewc = new ConcertViewController();
			concertviewc.doGet(request, response);
		}else if (hc.equals("Customer")) {
			CustomerController customerc = new CustomerController();
			customerc.doGet(request, response);
		}
	}
		
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//가상경로와 같은지 확인한다
				String uri = request.getRequestURI();
				String projectname = request.getContextPath();
				int jarinum = projectname.length();
				String str = uri.substring(jarinum);
				System.out.println("post" + uri);
				// /board/boardList.do
				
				String[] po = str.split("/");
				String hc = po[1];
		
		if (hc.equals("Member")) {
			MemberController memberc = new MemberController();
			memberc.doPost(request, response);			
		}
		
	}
	
}
