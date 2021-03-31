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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.TicketLupin.web.service.NewsVo;
import com.TicketLupin.web.service.ReservationDao;
import com.TicketLupin.web.service.ReservationIdxVo;
import com.TicketLupin.web.service.ShowDao;
import com.TicketLupin.web.service.ShowRankingVo;
import com.TicketLupin.web.service.ShowRoundDao;
import com.TicketLupin.web.service.ShowRoundVo;
import com.TicketLupin.web.service.Show1Vo;
import com.TicketLupin.web.service.Show2Vo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import domain.PageMaker;
import domain.SearchCriteria;
import util.deleteReservationMail;

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
			List<Show1Vo> list = sd.getShowList(query, setting, array, page);
			int count = sd.getShowListCount(query, setting);
			
			System.out.println("setting: " + setting);
			System.out.println("array: " + array);
			
			System.out.println("리스트 테스트: " + list);
			System.out.println("카운트 테스트: " + count);
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_list.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowWriteStep1.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_write_admin1.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowWriteStep2.do")) {

			//바로 직전에 등록한 공연의 sidx 가져오기
			ShowDao sd = new ShowDao();
			Show1Vo sv = sd.getRecentShowDetail();
			
			int sidx = sv.getSidx();
			System.out.println("최근 sidx 값 받아오는지 확인: " + sidx);
			//sidx 값 보내주기
			request.setAttribute("sidx", sidx);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_write_admin2.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowWriteStep3.do")) {
			
			ShowDao sd = new ShowDao();
			Show1Vo sv = sd.getRecentShowDetail();
			
			int sidx = sv.getSidx();
			Date sopendate_ = sv.getSopendate();
			Date senddate_ = sv.getSenddate();
			
			System.out.println("시작날짜: " + sopendate_);
			System.out.println("끝날짜: " + senddate_);

			Calendar sopendate1 = Calendar.getInstance();
			Calendar senddate1 = Calendar.getInstance();

			SimpleDateFormat dateFormat;
	        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
			//Calendar 타입으로 변경 add()메소드로 1일씩 추가해 주기위해 변경
			sopendate1.setTime(sopendate_);
			senddate1.setTime(senddate_);

			ArrayList datelist = new ArrayList();
			
			//시작날짜와 끝 날짜를 비교해, 시작날짜가 작거나 같은 경우 출력			
			while(sopendate1.compareTo(senddate1) != 1){	
				//출력
				System.out.println(dateFormat.format(sopendate1.getTime()));
				datelist.add(dateFormat.format(sopendate1.getTime()));
				//시작날짜 + 1 일
				sopendate1.add(Calendar.DATE, 1);
			}

			int datelength = datelist.size();
			
			System.out.println("목록 확인: " + datelist);
			
			request.setAttribute("sidx", sidx);
			request.setAttribute("datelist", datelist);
			request.setAttribute("datelength", datelength);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_write_round_admin.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowModifyStep1.do")) {
			
//==============================================================================================================================//

			//공연 인덱스 번호 가져오기
			String sidx_ = request.getParameter("sidx");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
//==============================================================================================================================//			

			//공연 등록 기본정보 가져오기
			ShowDao sd = new ShowDao();
			Show1Vo sv = sd.getShowDetail(sidx);
			
			System.out.println("show1 테스트: " + sv);


			request.setAttribute("list", sv);
			
//==============================================================================================================================//			

			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_modify_admin1.jsp").forward(request, response);
			
		}else if(str.equals("/Show/RankingList.do")) {

			//정렬 값 받아오기
			String order_ = request.getParameter("order");
			
			String order = "now";
			if(order_ != null && !order_.equals("")) {
				order = order_;
			}

//==============================================================================================================================//			
			
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
			Calendar start = Calendar.getInstance();
			
			String startdate = "0000-00-00";
			if(order.equals("week")) {
				start.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				startdate = formatter.format(start.getTime());
				System.out.println(startdate);
			}else if(order.equals("month")) {
				String startdate_ = formatter.format(start.getTime());
				int month = (start.get(Calendar.MONTH)) +1;
				startdate = start.get(Calendar.YEAR) + "-" + month + "-" + 1;
				System.out.println(startdate);
			}else if(order.equals("now")) {
				startdate = formatter.format(start.getTime());
				System.out.println("startdate 테스트: " + startdate);
			}
			
//==============================================================================================================================//
			
			System.out.println("startdate: " + startdate);
			ShowDao sd = new ShowDao();
			ArrayList<ShowRankingVo> list = sd.getShowRankingList(startdate);
			System.out.println(list);
			
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Ranking_list.jsp").forward(request, response);
			
		}else if(str.equals("/Show/RankingListAJAX.do")) {

			//정렬 값 받아오기
			String order_ = request.getParameter("order");
			
			String order = "now";
			if(order_ != null && !order_.equals("")) {
				order = order_;
			}

//==============================================================================================================================//			
			
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
			Calendar start = Calendar.getInstance();
			
			String startdate = "0000-00-00";
			if(order.equals("week")) {
				start.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				startdate = formatter.format(start.getTime());
				System.out.println(startdate);
			}else if(order.equals("month")) {
				String startdate_ = formatter.format(start.getTime());
				int month = (start.get(Calendar.MONTH)) +1;
				startdate = start.get(Calendar.YEAR) + "-" + month + "-" + 1;
				System.out.println(startdate);
			}else if(order.equals("now")) {
				startdate = formatter.format(start.getTime());
				System.out.println("startdate 테스트: " + startdate);
			}
			
//==============================================================================================================================//
			
			System.out.println("startdate: " + startdate);
			ShowDao sd = new ShowDao();
			ArrayList<ShowRankingVo> list = sd.getShowRankingList(startdate);
			System.out.println(list);
			
			JSONObject obj = new JSONObject();
			JSONArray objList = new JSONArray();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			for (int i = 0 ; i < list.size() ; i++) {
				
				JSONObject obj_ = new JSONObject();
				obj_.put("sidx", list.get(i).getSidx());
				obj_.put("stitle", list.get(i).getStitle());
				obj_.put("sopendate", transFormat.format(list.get(i).getSopendate()));
				obj_.put("senddate", transFormat.format(list.get(i).getSenddate()));
				obj_.put("ssdetailaddress", list.get(i).getSdetailaddress());
				obj_.put("sstitleimage", list.get(i).getStitleimage());

				objList.add(obj_);
			}
			
			System.out.println("objList 테스트: " + objList);
			System.out.println("넘어온 order 값: " + order);
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(objList); //{"result":1}
			
		}else if(str.equals("/Show/ShowDelete.do")) {

			String sidx_ = request.getParameter("sidx");
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			//삭제되는 공연 예매내역 삭제하기
			ReservationDao rd = new ReservationDao();
			rd.deleteCancelReservation(sidx);
			
			ShowDao sd = new ShowDao();
			sd.getShowDelete(sidx);
			
			System.out.println("공연 삭제 성공");
			
			response.sendRedirect(request.getContextPath()+"/Show/ShowList.do");
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
			
//==============================================================================================================================//
			
			//기본 정보 받아오기
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			String title = request.getParameter("title");
			String genre = request.getParameter("genre");
			String startdate_ = request.getParameter("startdate");
			String enddate_ = request.getParameter("enddate");
			String ticketingdate_ = request.getParameter("ticketingdate");
			String rating = request.getParameter("rating");
			String postcode_ = request.getParameter("postcode");
			String roadAddress = request.getParameter("roadAddress");
			String jibunAddress = request.getParameter("jibunAddress");
			String detailAddress = request.getParameter("detailAddress");
			String extraAddress = request.getParameter("extraAddress");
			String vipprice_ = request.getParameter("vipPrice");
			String rprice_ = request.getParameter("rPrice");
			String sprice_ = request.getParameter("sPrice");
			String aprice_ = request.getParameter("aPrice");
			
			//세션에 있는 midx 값 받아오기
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			//String으로 받은 값들 int로 바꿔주기
			int postcode = 0;
			if(postcode_ != null && !postcode_.equals("")) {
				postcode = Integer.parseInt(postcode_);
			}
			
			int vipprice = 0;
			if(vipprice_ != null && !vipprice_.equals("")) {
				vipprice = Integer.parseInt(vipprice_);
			}
			int rprice = 0;
			if(rprice_ != null && !rprice_.equals("")) {
				rprice = Integer.parseInt(rprice_);
			}
			int sprice = 0;
			if(sprice_ != null && !sprice_.equals("")) {
				sprice = Integer.parseInt(sprice_);
			}
			int aprice = 0;
			if(aprice_ != null && !aprice_.equals("")) {
				aprice = Integer.parseInt(aprice_);
			}
			
			System.out.println("vipprice 테스트: " + vipprice);
			System.out.println("rprice 테스트: " + rprice);
			System.out.println("sprice 테스트: " + sprice);
			System.out.println("aprice 테스트: " + aprice);
			
//==============================================================================================================================//
			//오픈 날짜 oracle에서 쓸 수 있는 date 타입으로 바꿔주기
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
			   System.out.println("占쏙옙환 占싹뤄옙 占쏙옙占쌜놂옙짜: " + sqlStartDate);
//==============================================================================================================================//
			 //마감 날짜 oracle에서 쓸 수 있는 date 타입으로 바꿔주기
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
			   System.out.println("占쏙옙환 占싹뤄옙 占쏙옙占쏙옙짜: " + sqlEndDate);
//==============================================================================================================================//
			   //예매 시작 날짜 oracle에서 쓸 수 있는 date 타입으로 바꿔주기
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
			   System.out.println("占쏙옙환 占싹뤄옙 티占싹울옙占승놂옙짜: " + sqlTicketingDate);
//==============================================================================================================================//
			
			ShowDao sd = new ShowDao();
			Show1Vo sv = new Show1Vo();
			
			sv.setStitle(title);
			sv.setSgenre(genre);
			sv.setSopendate(sqlStartDate);
			sv.setSenddate(sqlEndDate);
			sv.setSrating(rating);
			sv.setSpostcode(postcode);
			sv.setSroadaddress(roadAddress);
			sv.setSjibunaddress(jibunAddress);
			sv.setSdetailaddress(detailAddress);
			sv.setSextraaddress(extraAddress);
			sv.setMidx(midx);
			sv.setSticketingdate(sqlTicketingDate);
			sv.setSvipprice(vipprice);
			sv.setSrprice(rprice);
			sv.setSsprice(sprice);
			sv.setSaprice(aprice);
			
			sd.insertShow(sv);

//==============================================================================================================================//			
			
			//다음 페이지 불러오기
			response.sendRedirect(request.getContextPath()+"/Show/ShowWriteStep2.do");
			
		}else if(str.equals("/Show/ShowWriteStep2Action.do")) {
			
//==============================================================================================================================//		
			
			//이미지 업로드 설정해주기
			String uploadPath = request.getSession().getServletContext().getRealPath("poster");
			int sizeLimit = 1024*1024*15;
			System.out.println(uploadPath);
			MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

//==============================================================================================================================//
			
			//기본 값 받아오기
			String sidx_ = multi.getParameter("sidx");
			String round = multi.getParameter("roundText");
			String price = multi.getParameter("priceText");
			String notice = multi.getParameter("noticeText");
			String discount = multi.getParameter("discountText");
			String info = multi.getParameter("infoText");
			String company = multi.getParameter("companyText");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
//==============================================================================================================================//
			//이미지 이름 받아오기   
			Enumeration files = multi.getFileNames();
			String titleImage = (String)files.nextElement();
			String roundImage = (String)files.nextElement();
			String priceImage = (String)files.nextElement();
			String noticeImage = (String)files.nextElement();
			String discountImage = (String)files.nextElement();
			String infoImage = (String)files.nextElement();
			String companyImage = (String)files.nextElement();
							
			String tfile = multi.getFilesystemName(titleImage);
			String toriginalFile = multi.getOriginalFileName(titleImage);
			String rifile = multi.getFilesystemName(roundImage);
			String rioriginalFile = multi.getOriginalFileName(roundImage);
			String pifile = multi.getFilesystemName(priceImage);
			String pioriginalFile = multi.getOriginalFileName(priceImage);
			String nifile = multi.getFilesystemName(noticeImage);
			String nioriginalFile = multi.getOriginalFileName(noticeImage);
			String difile = multi.getFilesystemName(discountImage);
			String dioriginalFile = multi.getOriginalFileName(discountImage);
			String iifile = multi.getFilesystemName(infoImage);
			String iioriginalFile = multi.getOriginalFileName(infoImage);
			String cifile = multi.getFilesystemName(companyImage);
			String cioriginalFile = multi.getOriginalFileName(companyImage);
			
			System.out.println("제목사진 받아오는지 테스트: " + toriginalFile);
			System.out.println("공연시간 사진 받아오는지 테스트: " + rioriginalFile);
			System.out.println("가격사진 받아오는지 테스트: " + pioriginalFile);
			System.out.println("공지사진 받아오는지 테스트: " + nioriginalFile);
			System.out.println("할인사진 받아오는지 테스트: " + dioriginalFile);
			System.out.println("정보사진 받아오는지 테스트: " + iioriginalFile);
			System.out.println("회사사진 받아오는지 테스트: " + cioriginalFile);
			
//==============================================================================================================================//
			
			ShowDao sd = new ShowDao();
			Show2Vo sv = new Show2Vo();
			
			sv.setSidx(sidx);
			sv.setSround(round);
			sv.setSprice(price);
			sv.setSnotice(notice);
			sv.setSdiscount(discount);
			sv.setSinfo(info);
			sv.setScompany(company);
			sv.setStitleimage(toriginalFile);
			sv.setSroundimage(rioriginalFile);
			sv.setSpriceimage(pioriginalFile);
			sv.setSnoticeimage(nioriginalFile);
			sv.setSdiscountimage(dioriginalFile);
			sv.setSinfoimage(iioriginalFile);
			sv.setScompanyimage(cioriginalFile);
			
			sd.insertShow2(sv);

			
			response.sendRedirect(request.getContextPath()+"/Show/ShowWriteStep3.do");
			
		}else if(str.equals("/Show/ShowWriteStep3Action.do")) {
			
			String sidx_ = request.getParameter("sidx");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			System.out.println("회차 sidx 확인: " + sidx);
//==============================================================================================================================//			
			
			ShowDao sd = new ShowDao();
			Show1Vo sv = sd.getShowDetail(sidx);
			
			Date sopendate_ = sv.getSopendate();
			Date senddate_ = sv.getSenddate();
			
			System.out.println("시작날짜  설정: " + sopendate_);
			System.out.println("끝날짜 설정: " + senddate_);

			Calendar sopendate1 = Calendar.getInstance();
			Calendar senddate1 = Calendar.getInstance();

			SimpleDateFormat dateFormat;
	        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
			//Calendar 타입으로 변경 add()메소드로 1일씩 추가해 주기위해 변경
			sopendate1.setTime(sopendate_);
			senddate1.setTime(senddate_);

			ArrayList datelist = new ArrayList();
			
			//시작날짜와 끝 날짜를 비교해, 시작날짜가 작거나 같은 경우 출력			
			while(sopendate1.compareTo(senddate1) != 1){	
				//출력
				System.out.println(dateFormat.format(sopendate1.getTime()));
				datelist.add(dateFormat.format(sopendate1.getTime()));
				//시작날짜 + 1 일
				sopendate1.add(Calendar.DATE, 1);
			}

//==============================================================================================================================//
			
			int datelength = datelist.size();
			
			System.out.println("길이: " + datelength);
			
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
		
		}else if(str.equals("/Show/ShowModifyStep1Action.do")) {
			
//==============================================================================================================================//
			
			//기본 정보 받아오기
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			String title = request.getParameter("title");
			String genre = request.getParameter("genre");
			String startdate_ = request.getParameter("startdate");
			String enddate_ = request.getParameter("enddate");
			String ticketingdate_ = request.getParameter("ticketingdate");
			String rating = request.getParameter("rating");
			String postcode_ = request.getParameter("postcode");
			String roadAddress = request.getParameter("roadAddress");
			String jibunAddress = request.getParameter("jibunAddress");
			String detailAddress = request.getParameter("detailAddress");
			String extraAddress = request.getParameter("extraAddress");
			String vipprice_ = request.getParameter("vipPrice");
			String rprice_ = request.getParameter("rPrice");
			String sprice_ = request.getParameter("sPrice");
			String aprice_ = request.getParameter("aPrice");
			String sidx_ = request.getParameter("sidx");
			
			System.out.println("받아오는 vipPrice 확인: " + vipprice_);
			System.out.println("받아오는 rPrice 확인: " + rprice_);
			System.out.println("받아오는 sPrice 확인: " + sprice_);
			System.out.println("받아오는 aPrice 확인: " + aprice_);
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			
			//세션에 있는 midx 값 받아오기
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			//String으로 받은 값들 int로 바꿔주기
			int postcode = 0;
			if(postcode_ != null && !postcode_.equals("")) {
				postcode = Integer.parseInt(postcode_);
			}
			
			int vipprice = 0;
			if(vipprice_ != null && !vipprice_.equals("")) {
				vipprice = Integer.parseInt(vipprice_);
			}
			int rprice = 0;
			if(rprice_ != null && !rprice_.equals("")) {
				rprice = Integer.parseInt(rprice_);
			}
			int sprice = 0;
			if(sprice_ != null && !sprice_.equals("")) {
				sprice = Integer.parseInt(sprice_);
			}
			int aprice = 0;
			if(aprice_ != null && !aprice_.equals("")) {
				aprice = Integer.parseInt(aprice_);
			}
			
			System.out.println("바뀐 vipPrice 확인: " + vipprice);
			System.out.println("바뀐 rPrice 확인: " + rprice);
			System.out.println("바뀐 sPrice 확인: " + sprice);
			System.out.println("바뀐 aPrice 확인: " + aprice);
//==============================================================================================================================//
			//오픈 날짜 oracle에서 쓸 수 있는 date 타입으로 바꿔주기
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
			   System.out.println("占쏙옙환 占싹뤄옙 占쏙옙占쌜놂옙짜: " + sqlStartDate);
//==============================================================================================================================//
			 //마감 날짜 oracle에서 쓸 수 있는 date 타입으로 바꿔주기
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
			   System.out.println("占쏙옙환 占싹뤄옙 占쏙옙占쏙옙짜: " + sqlEndDate);
//==============================================================================================================================//
			   //예매 시작 날짜 oracle에서 쓸 수 있는 date 타입으로 바꿔주기
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
			   System.out.println("占쏙옙환 占싹뤄옙 티占싹울옙占승놂옙짜: " + sqlTicketingDate);
//==============================================================================================================================//
			
			ShowDao sd = new ShowDao();
			Show1Vo sv = new Show1Vo();
			
			sv.setStitle(title);
			sv.setSgenre(genre);
			sv.setSopendate(sqlStartDate);
			sv.setSenddate(sqlEndDate);
			sv.setSrating(rating);
			sv.setSpostcode(postcode);
			sv.setSroadaddress(roadAddress);
			sv.setSjibunaddress(jibunAddress);
			sv.setSdetailaddress(detailAddress);
			sv.setSextraaddress(extraAddress);
			sv.setMidx(midx);
			sv.setSticketingdate(sqlTicketingDate);
			sv.setSidx(sidx);
			sv.setSvipprice(vipprice);
			sv.setSrprice(rprice);
			sv.setSsprice(sprice);
			sv.setSaprice(aprice);
			
			sd.modifyShow(sv);
			
			ReservationDao rd = new ReservationDao();
			rd.deleteUpdateReservation1(sidx);
			rd.deleteUpdateReservationIDX1(sidx);
			
			Show1Vo sv2 = sd.getShowDetail(sidx);
			System.out.println("show1 테스트: " + sv2);


			request.setAttribute("list", sv2);
			request.setAttribute("sidx", sidx);
//==============================================================================================================================//			
			
			//다음 페이지 불러오기
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_modify_admin2.jsp").forward(request, response);
		
		}else if(str.equals("/Show/ShowModifyStep2Action.do")) {
			
//==============================================================================================================================//		
			
			//이미지 업로드 설정해주기
			String uploadPath = request.getSession().getServletContext().getRealPath("poster");
			int sizeLimit = 1024*1024*15;
			System.out.println(uploadPath);
			MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

//==============================================================================================================================//
			
			//기본 값 받아오기
			String sidx_ = multi.getParameter("sidx");
			String round = multi.getParameter("roundText");
			String price = multi.getParameter("priceText");
			String notice = multi.getParameter("noticeText");
			String discount = multi.getParameter("discountText");
			String info = multi.getParameter("infoText");
			String company = multi.getParameter("companyText");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
//==============================================================================================================================//
			//이미지 이름 받아오기   
			Enumeration files = multi.getFileNames();
			String titleImage = (String)files.nextElement();
			String roundImage = (String)files.nextElement();
			String priceImage = (String)files.nextElement();
			String noticeImage = (String)files.nextElement();
			String discountImage = (String)files.nextElement();
			String infoImage = (String)files.nextElement();
			String companyImage = (String)files.nextElement();
							
			String tfile = multi.getFilesystemName(titleImage);
			String toriginalFile = multi.getOriginalFileName(titleImage);
			String rifile = multi.getFilesystemName(roundImage);
			String rioriginalFile = multi.getOriginalFileName(roundImage);
			String pifile = multi.getFilesystemName(priceImage);
			String pioriginalFile = multi.getOriginalFileName(priceImage);
			String nifile = multi.getFilesystemName(noticeImage);
			String nioriginalFile = multi.getOriginalFileName(noticeImage);
			String difile = multi.getFilesystemName(discountImage);
			String dioriginalFile = multi.getOriginalFileName(discountImage);
			String iifile = multi.getFilesystemName(infoImage);
			String iioriginalFile = multi.getOriginalFileName(infoImage);
			String cifile = multi.getFilesystemName(companyImage);
			String cioriginalFile = multi.getOriginalFileName(companyImage);
			
			System.out.println("제목사진 받아오는지 테스트: " + toriginalFile);
			System.out.println("공연시간 사진 받아오는지 테스트: " + rioriginalFile);
			System.out.println("가격사진 받아오는지 테스트: " + pioriginalFile);
			System.out.println("공지사진 받아오는지 테스트: " + nioriginalFile);
			System.out.println("할인사진 받아오는지 테스트: " + dioriginalFile);
			System.out.println("정보사진 받아오는지 테스트: " + iioriginalFile);
			System.out.println("회사사진 받아오는지 테스트: " + cioriginalFile);
			
//==============================================================================================================================//
			
			ShowDao sd = new ShowDao();
			Show2Vo sv = new Show2Vo();
			
			sv.setSidx(sidx);
			sv.setSround(round);
			sv.setSprice(price);
			sv.setSnotice(notice);
			sv.setSdiscount(discount);
			sv.setSinfo(info);
			sv.setScompany(company);
			sv.setStitleimage(toriginalFile);
			sv.setSroundimage(rioriginalFile);
			sv.setSpriceimage(pioriginalFile);
			sv.setSnoticeimage(nioriginalFile);
			sv.setSdiscountimage(dioriginalFile);
			sv.setSinfoimage(iioriginalFile);
			sv.setScompanyimage(cioriginalFile);
			
			sd.modifyShow2(sv);
			
//==============================================================================================================================//
			
			//round_admin에 보내줄 값 받아오기
			
			Show1Vo sv1 = sd.getShowDetail(sidx);
			
			Date sopendate_ = sv1.getSopendate();
			Date senddate_ = sv1.getSenddate();
			
			System.out.println("시작날짜: " + sopendate_);
			System.out.println("끝날짜: " + senddate_);

			Calendar sopendate1 = Calendar.getInstance();
			Calendar senddate1 = Calendar.getInstance();

			SimpleDateFormat dateFormat;
	        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
			//Calendar 타입으로 변경 add()메소드로 1일씩 추가해 주기위해 변경
			sopendate1.setTime(sopendate_);
			senddate1.setTime(senddate_);

			ArrayList datelist = new ArrayList();
			
			//시작날짜와 끝 날짜를 비교해, 시작날짜가 작거나 같은 경우 출력			
			while(sopendate1.compareTo(senddate1) != 1){	
				//출력
				System.out.println(dateFormat.format(sopendate1.getTime()));
				datelist.add(dateFormat.format(sopendate1.getTime()));
				//시작날짜 + 1 일
				sopendate1.add(Calendar.DATE, 1);
			}

			int datelength = datelist.size();
			
			System.out.println("목록 확인: " + datelist);
			
			request.setAttribute("sidx", sidx);
			request.setAttribute("datelist", datelist);
			request.setAttribute("datelength", datelength);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_modify_round_admin.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowModifyStep3Action.do")) {
			
			String sidx_ = request.getParameter("sidx");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			System.out.println("회차 sidx 확인: " + sidx);
//==============================================================================================================================//			
			
			ShowDao sd = new ShowDao();
			Show1Vo sv = sd.getShowDetail(sidx);
			
			Date sopendate_ = sv.getSopendate();
			Date senddate_ = sv.getSenddate();
			
			System.out.println("시작날짜  설정: " + sopendate_);
			System.out.println("끝날짜 설정: " + senddate_);

			Calendar sopendate1 = Calendar.getInstance();
			Calendar senddate1 = Calendar.getInstance();

			SimpleDateFormat dateFormat;
	        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
			//Calendar 타입으로 변경 add()메소드로 1일씩 추가해 주기위해 변경
			sopendate1.setTime(sopendate_);
			senddate1.setTime(senddate_);

			ArrayList datelist = new ArrayList();
			
			//시작날짜와 끝 날짜를 비교해, 시작날짜가 작거나 같은 경우 출력			
			while(sopendate1.compareTo(senddate1) != 1){	
				//출력
				System.out.println(dateFormat.format(sopendate1.getTime()));
				datelist.add(dateFormat.format(sopendate1.getTime()));
				//시작날짜 + 1 일
				sopendate1.add(Calendar.DATE, 1);
			}

//==============================================================================================================================//
			
			int datelength = datelist.size();
			
			System.out.println("길이: " + datelength);
			
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
			
			//기존 공연 회차 삭제하기
			srd.deleteShowRound(sidx);
			
			//수정 공연 회차 등록하기
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
			
			//기존 공연 회차 예매했던 회원들 불러오기
			ReservationDao rd = new ReservationDao();
			ArrayList<ReservationIdxVo> list = rd.deleteUpdateReservationIDX2List(sidx);
			deleteReservationMail drm = new deleteReservationMail();
			drm.naverMailSend(list);
			
			
			//기존 공연 회차 예매내역 삭제하기(수정중)
			//rd.deleteUpdateReservation2(sidx);
			//rd.deleteUpdateReservationIDX2(sidx);
			
			System.out.println(roundlist);
			response.sendRedirect(request.getContextPath()+"/Show/ShowList.do");
		
		}
		
		
	}
}
