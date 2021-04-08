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
			
			SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
			Calendar time = Calendar.getInstance();
			String now = format.format(time.getTime());
			
			
			System.out.println("now test: " + now);
			System.out.println("setting: " + setting);
			System.out.println("array: " + array);
			
			System.out.println("리스트: " + list);
			System.out.println("카운트: " + count);
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("now", now);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_list.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowListAJAX.do")) {
			
			String checkList_ = request.getParameter("checkList");		
			String query_ = request.getParameter("q");
			String setting_ = request.getParameter("s");
			String page_ = request.getParameter("p");
			
			String checkList = "";
			if(checkList_ != null && !checkList_.equals("")) {
				checkList = checkList_;
			}
			
			String[] checkArray = checkList.split("/");
			
			ArrayList genre_ = new ArrayList();
			ArrayList place_ = new ArrayList();
			ArrayList sold_ = new ArrayList();
			
			for(int i = 0 ; i < checkArray.length ; i++) {
				
				if(checkArray[i].equals("genreall") || checkArray[i].equals("original") || checkArray[i].equals("license")|| checkArray[i].equals("creation")|| checkArray[i].equals("nonverbal")|| checkArray[i].equals("package")) {
					genre_.add(checkArray[i]);
				}else if(checkArray[i].equals("placeall") || checkArray[i].equals("seoul") || checkArray[i].equals("incheon")|| checkArray[i].equals("daejeon")|| checkArray[i].equals("busan")|| checkArray[i].equals("gwangju")) {
					place_.add(checkArray[i]);
				}else if(checkArray[i].equals("soldall") || checkArray[i].equals("now") || checkArray[i].equals("soon")) {
					sold_.add(checkArray[i]);
				}
				System.out.println("배열 확인: " + checkArray[i]);
			}
			
			System.out.println("genre_ 확인: " + genre_);
			System.out.println("place_ 확인: " + place_);
			System.out.println("sold_ 확인: " + sold_);
			
			ArrayList genre = new ArrayList();
			ArrayList place = new ArrayList();
			ArrayList sold = new ArrayList();
			
			for(int i = 0 ; i < genre_.size() ; i++) {
				if(genre_.get(i).equals("genreall")) {
					genre.add("");
				}else if(genre_.get(i).equals("original")) {
					genre.add("오리지널/내한공연");
				}else if(genre_.get(i).equals("license")) {
					genre.add("라이선스");
				}else if(genre_.get(i).equals("creation")) {
					genre.add("창작뮤지컬");
				}else if(genre_.get(i).equals("nonverbal")) {
					genre.add("넌버벌");
				}else if(genre_.get(i).equals("package")) {
					genre.add("패키지");
				}
			}
			
			for(int i = 0 ; i < place_.size() ; i++) {
				if(place_.get(i).equals("placeall")) {
					place.add("");
				}else if(place_.get(i).equals("seoul")) {
					place.add("서울");
				}else if(place_.get(i).equals("incheon")) {
					place.add("경기도");
					place.add("인천");
				}else if(place_.get(i).equals("daejeon")) {
					place.add("대전");
					place.add("충청");
					place.add("강원");
				}else if(place_.get(i).equals("busan")) {
					place.add("부산");
					place.add("대구");
					place.add("경상");
				}else if(place_.get(i).equals("gwangju")) {
					place.add("광주광역");
					place.add("전라");
					place.add("제주");
				}
			}
			
			System.out.println("genre 확인: " + genre);
			System.out.println("place 확인: " + place);
			System.out.println("sold 확인: " + sold);
			
			
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
			int count = sd.getShowListCount(query, setting);
			JSONArray list = sd.getShowListAJAX(genre, place, sold);
			System.out.println("setting: " + setting);
			System.out.println("array: " + array);
			
			System.out.println("리스트: " + list);
			System.out.println("카운트: " + count);
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(list); //{"result":1}
			
		}else if(str.equals("/Show/ShowWriteStep1.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_write_admin1.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowWriteStep2.do")) {

			//�ٷ� ������ ����� ������ sidx ��������
			ShowDao sd = new ShowDao();
			Show1Vo sv = sd.getRecentShowDetail();
			
			int sidx = sv.getSidx();
			System.out.println("�ֱ� sidx �� �޾ƿ����� Ȯ��: " + sidx);
			//sidx �� �����ֱ�
			request.setAttribute("sidx", sidx);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_write_admin2.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowWriteStep3.do")) {
			
			ShowDao sd = new ShowDao();
			Show1Vo sv = sd.getRecentShowDetail();
			
			int sidx = sv.getSidx();
			Date sopendate_ = sv.getSopendate();
			Date senddate_ = sv.getSenddate();
			
			System.out.println("���۳�¥: " + sopendate_);
			System.out.println("����¥: " + senddate_);

			Calendar sopendate1 = Calendar.getInstance();
			Calendar senddate1 = Calendar.getInstance();

			SimpleDateFormat dateFormat;
	        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
			//Calendar Ÿ������ ���� add()�޼ҵ�� 1�Ͼ� �߰��� �ֱ����� ����
			sopendate1.setTime(sopendate_);
			senddate1.setTime(senddate_);

			ArrayList datelist = new ArrayList();
			
			//���۳�¥�� �� ��¥�� ����, ���۳�¥�� �۰ų� ���� ��� ���			
			while(sopendate1.compareTo(senddate1) != 1){	
				//���
				System.out.println(dateFormat.format(sopendate1.getTime()));
				datelist.add(dateFormat.format(sopendate1.getTime()));
				//���۳�¥ + 1 ��
				sopendate1.add(Calendar.DATE, 1);
			}

			int datelength = datelist.size();
			
			System.out.println("��� Ȯ��: " + datelist);
			
			request.setAttribute("sidx", sidx);
			request.setAttribute("datelist", datelist);
			request.setAttribute("datelength", datelength);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_write_round_admin.jsp").forward(request, response);
			
		}else if(str.equals("/Show/ShowModifyStep1.do")) {
			
//==============================================================================================================================//

			//���� �ε��� ��ȣ ��������
			String sidx_ = request.getParameter("sidx");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
//==============================================================================================================================//			

			//���� ��� �⺻���� ��������
			ShowDao sd = new ShowDao();
			Show1Vo sv = sd.getShowDetail(sidx);
			
			System.out.println("show1 �׽�Ʈ: " + sv);


			request.setAttribute("list", sv);
			
//==============================================================================================================================//			

			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_modify_admin1.jsp").forward(request, response);
			
		}else if(str.equals("/Show/RankingList.do")) {

			//���� �� �޾ƿ���
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
				System.out.println("startdate �׽�Ʈ: " + startdate);
			}
			
//==============================================================================================================================//
			
			System.out.println("startdate: " + startdate);
			ShowDao sd = new ShowDao();
			ArrayList<ShowRankingVo> list = sd.getShowRankingList(startdate);
			System.out.println(list);
			
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Ranking_list.jsp").forward(request, response);
			
		}else if(str.equals("/Show/RankingListAJAX.do")) {

			//���� �� �޾ƿ���
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
				System.out.println("startdate �׽�Ʈ: " + startdate);
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
			
			System.out.println("objList �׽�Ʈ: " + objList);
			System.out.println("�Ѿ�� order ��: " + order);
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(objList); //{"result":1}
			
		}else if(str.equals("/Show/ShowDelete.do")) {

			String sidx_ = request.getParameter("sidx");
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			//�����Ǵ� ���� ���ų��� �����ϱ�
			ReservationDao rd = new ReservationDao();
			rd.deleteCancelReservation(sidx);
			
			ShowDao sd = new ShowDao();
			sd.getShowDelete(sidx);
			
			System.out.println("���� ���� ����");
			
			response.sendRedirect(request.getContextPath()+"/Show/ShowList.do");
		}else if(str.equals("/Show/ShowWriteStep1Action.do")) {
			
//==============================================================================================================================//
			
			//�⺻ ���� �޾ƿ���
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
			
			//���ǿ� �ִ� midx �� �޾ƿ���
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			//String���� ���� ���� int�� �ٲ��ֱ�
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
			
			System.out.println("vipprice �׽�Ʈ: " + vipprice);
			System.out.println("rprice �׽�Ʈ: " + rprice);
			System.out.println("sprice �׽�Ʈ: " + sprice);
			System.out.println("aprice �׽�Ʈ: " + aprice);
			
//==============================================================================================================================//
			//���� ��¥ oracle���� �� �� �ִ� date Ÿ������ �ٲ��ֱ�
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
			   System.out.println("��ȯ �Ϸ� ���۳�¥: " + sqlStartDate);
//==============================================================================================================================//
			 //���� ��¥ oracle���� �� �� �ִ� date Ÿ������ �ٲ��ֱ�
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
			   System.out.println("��ȯ �Ϸ� ����¥: " + sqlEndDate);
//==============================================================================================================================//
			   //���� ���� ��¥ oracle���� �� �� �ִ� date Ÿ������ �ٲ��ֱ�
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
			   System.out.println("��ȯ �Ϸ� Ƽ�Ͽ��³�¥: " + sqlTicketingDate);
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
			
			//���� ������ �ҷ�����
			response.sendRedirect(request.getContextPath()+"/Show/ShowWriteStep2.do");
			
		}else if(str.equals("/Show/ShowWriteStep2Action.do")) {
			
//==============================================================================================================================//		
			
			//�̹��� ���ε� �������ֱ�
			String uploadPath = request.getSession().getServletContext().getRealPath("poster");
			int sizeLimit = 1024*1024*15;
			System.out.println(uploadPath);
			MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

//==============================================================================================================================//
			
			//�⺻ �� �޾ƿ���
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
			//�̹��� �̸� �޾ƿ���   
			Enumeration files = multi.getFileNames();
			ArrayList image = new ArrayList();
			for(int i = 0 ; i <= 6 ; i++) {
				image.add((String)files.nextElement());
				System.out.println(image.get(i));
				System.out.println(multi.getFilesystemName((String)image.get(i)));
			}
			
			//�̹��� ���� ����� ���ֱ�
			ArrayList imageList = new ArrayList();
			for(int i = 0 ; i < 7 ; i++) {
				for(int j = 0 ; j < 7 ; j++) {
					if(((String)image.get(j)).equals("image" + (i+1))) {
						imageList.add(multi.getFilesystemName((String)image.get(j)));
					}
					
				}
				
			}
			
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
			sv.setStitleimage((String)imageList.get(0));
			sv.setSroundimage((String)imageList.get(1));
			sv.setSpriceimage((String)imageList.get(2));
			sv.setSnoticeimage((String)imageList.get(3));
			sv.setSdiscountimage((String)imageList.get(4));
			sv.setSinfoimage((String)imageList.get(5));
			sv.setScompanyimage((String)imageList.get(6));
			
			sd.insertShow2(sv);

			
			response.sendRedirect(request.getContextPath()+"/Show/ShowWriteStep3.do");
			
		}else if(str.equals("/Show/ShowWriteStep3Action.do")) {
			
			String sidx_ = request.getParameter("sidx");
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			System.out.println("ȸ�� sidx Ȯ��: " + sidx);
//==============================================================================================================================//			
			
			ShowDao sd = new ShowDao();
			Show1Vo sv = sd.getShowDetail(sidx);
			
			Date sopendate_ = sv.getSopendate();
			Date senddate_ = sv.getSenddate();
			
			System.out.println("���۳�¥  ����: " + sopendate_);
			System.out.println("����¥ ����: " + senddate_);

			Calendar sopendate1 = Calendar.getInstance();
			Calendar senddate1 = Calendar.getInstance();

			SimpleDateFormat dateFormat;
	        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
			//Calendar Ÿ������ ���� add()�޼ҵ�� 1�Ͼ� �߰��� �ֱ����� ����
			sopendate1.setTime(sopendate_);
			senddate1.setTime(senddate_);

			ArrayList datelist = new ArrayList();
			
			//���۳�¥�� �� ��¥�� ����, ���۳�¥�� �۰ų� ���� ��� ���			
			while(sopendate1.compareTo(senddate1) != 1){	
				//���
				System.out.println(dateFormat.format(sopendate1.getTime()));
				datelist.add(dateFormat.format(sopendate1.getTime()));
				//���۳�¥ + 1 ��
				sopendate1.add(Calendar.DATE, 1);
			}

//==============================================================================================================================//
			
			int datelength = datelist.size();
			
			System.out.println("����: " + datelength);
			
			ArrayList roundlist =  new ArrayList();
			
			String showdate = null;
			String round1 = null;
			String round2 = null;
			String round3 = null;
			String round4 = null;
			
			int i = 1;
			while(i != datelength+1) {
				
				showdate = request.getParameter("showdate" + i);
				round1 = request.getParameter("date_" + i + "_1");
				round2 = request.getParameter("date_" + i + "_2");
				round3 = request.getParameter("date_" + i + "_3");
				round4 = request.getParameter("date_" + i + "_4");
				
				
			   System.out.println(showdate);
				
			   roundlist.add(showdate);
			   roundlist.add(round1);
			   roundlist.add(round2);
			   roundlist.add(round3);
			   roundlist.add(round4);
				
			   i++;
			
			}
			System.out.println("roundlist 확인: " + roundlist);
			System.out.println("datelength 확인: " + datelength);
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
			
			//�⺻ ���� �޾ƿ���
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
			
			System.out.println("�޾ƿ��� vipPrice Ȯ��: " + vipprice_);
			System.out.println("�޾ƿ��� rPrice Ȯ��: " + rprice_);
			System.out.println("�޾ƿ��� sPrice Ȯ��: " + sprice_);
			System.out.println("�޾ƿ��� aPrice Ȯ��: " + aprice_);
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			
			//���ǿ� �ִ� midx �� �޾ƿ���
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			//String���� ���� ���� int�� �ٲ��ֱ�
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
			
			System.out.println("�ٲ� vipPrice Ȯ��: " + vipprice);
			System.out.println("�ٲ� rPrice Ȯ��: " + rprice);
			System.out.println("�ٲ� sPrice Ȯ��: " + sprice);
			System.out.println("�ٲ� aPrice Ȯ��: " + aprice);
//==============================================================================================================================//
			//���� ��¥ oracle���� �� �� �ִ� date Ÿ������ �ٲ��ֱ�
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
			   System.out.println("��ȯ �Ϸ� ���۳�¥: " + sqlStartDate);
//==============================================================================================================================//
			 //���� ��¥ oracle���� �� �� �ִ� date Ÿ������ �ٲ��ֱ�
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
			   System.out.println("��ȯ �Ϸ� ����¥: " + sqlEndDate);
//==============================================================================================================================//
			   //���� ���� ��¥ oracle���� �� �� �ִ� date Ÿ������ �ٲ��ֱ�
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
			   System.out.println("��ȯ �Ϸ� Ƽ�Ͽ��³�¥: " + sqlTicketingDate);
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
			System.out.println("show1 �׽�Ʈ: " + sv2);


			request.setAttribute("list", sv2);
			request.setAttribute("sidx", sidx);
//==============================================================================================================================//			
			
			//���� ������ �ҷ�����
			request.getRequestDispatcher("/WEB-INF/view/jsp/Concert_modify_admin2.jsp").forward(request, response);
		
		}else if(str.equals("/Show/ShowModifyStep2Action.do")) {
			
//==============================================================================================================================//		
			
			//�̹��� ���ε� �������ֱ�
			String uploadPath = request.getSession().getServletContext().getRealPath("poster");
			int sizeLimit = 1024*1024*15;
			System.out.println(uploadPath);
			MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

//==============================================================================================================================//
			
			//�⺻ �� �޾ƿ���
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
			//�̹��� �̸� �޾ƿ���   
			Enumeration files = multi.getFileNames();
			ArrayList image = new ArrayList();
			for(int i = 0 ; i <= 6 ; i++) {
				image.add((String)files.nextElement());
				System.out.println(image.get(i));
				System.out.println(multi.getFilesystemName((String)image.get(i)));
			}
			
			//�̹��� ���� ����� ���ֱ�
			ArrayList imageList = new ArrayList();
			for(int i = 0 ; i < 7 ; i++) {
				for(int j = 0 ; j < 7 ; j++) {
					if(((String)image.get(j)).equals("image" + (i+1))) {
						imageList.add(multi.getFilesystemName((String)image.get(j)));
					}
					
				}
				
			}
			
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
			sv.setStitleimage((String)imageList.get(0));
			sv.setSroundimage((String)imageList.get(1));
			sv.setSpriceimage((String)imageList.get(2));
			sv.setSnoticeimage((String)imageList.get(3));
			sv.setSdiscountimage((String)imageList.get(4));
			sv.setSinfoimage((String)imageList.get(5));
			sv.setScompanyimage((String)imageList.get(6));
			
			sd.modifyShow2(sv);
			
//==============================================================================================================================//
			
			//round_admin�� ������ �� �޾ƿ���
			
			Show1Vo sv1 = sd.getShowDetail(sidx);
			
			Date sopendate_ = sv1.getSopendate();
			Date senddate_ = sv1.getSenddate();
			
			System.out.println("���۳�¥: " + sopendate_);
			System.out.println("����¥: " + senddate_);

			Calendar sopendate1 = Calendar.getInstance();
			Calendar senddate1 = Calendar.getInstance();

			SimpleDateFormat dateFormat;
	        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
			//Calendar Ÿ������ ���� add()�޼ҵ�� 1�Ͼ� �߰��� �ֱ����� ����
			sopendate1.setTime(sopendate_);
			senddate1.setTime(senddate_);

			ArrayList datelist = new ArrayList();
			
			//���۳�¥�� �� ��¥�� ����, ���۳�¥�� �۰ų� ���� ��� ���			
			while(sopendate1.compareTo(senddate1) != 1){	
				//���
				System.out.println(dateFormat.format(sopendate1.getTime()));
				datelist.add(dateFormat.format(sopendate1.getTime()));
				//���۳�¥ + 1 ��
				sopendate1.add(Calendar.DATE, 1);
			}

			int datelength = datelist.size();
			
			System.out.println("��� Ȯ��: " + datelist);
			
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
			System.out.println("ȸ�� sidx Ȯ��: " + sidx);
//==============================================================================================================================//			
			
			ShowDao sd = new ShowDao();
			Show1Vo sv = sd.getShowDetail(sidx);
			
			Date sopendate_ = sv.getSopendate();
			Date senddate_ = sv.getSenddate();
			
			System.out.println("���۳�¥  ����: " + sopendate_);
			System.out.println("����¥ ����: " + senddate_);

			Calendar sopendate1 = Calendar.getInstance();
			Calendar senddate1 = Calendar.getInstance();

			SimpleDateFormat dateFormat;
	        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
			//Calendar Ÿ������ ���� add()�޼ҵ�� 1�Ͼ� �߰��� �ֱ����� ����
			sopendate1.setTime(sopendate_);
			senddate1.setTime(senddate_);

			ArrayList datelist = new ArrayList();
			
			//���۳�¥�� �� ��¥�� ����, ���۳�¥�� �۰ų� ���� ��� ���			
			while(sopendate1.compareTo(senddate1) != 1){	
				//���
				System.out.println(dateFormat.format(sopendate1.getTime()));
				datelist.add(dateFormat.format(sopendate1.getTime()));
				//���۳�¥ + 1 ��
				sopendate1.add(Calendar.DATE, 1);
			}

//==============================================================================================================================//
			
			int datelength = datelist.size();
			
			System.out.println("����: " + datelength);
			
			ArrayList roundlist =  new ArrayList();
			
			String showdate = null;
			String round1 = null;
			String round2 = null;
			String round3 = null;
			String round4 = null;
			
			int i = 1;
			while(i != datelength+1) {
				
				showdate = request.getParameter("showdate" + i);
				round1 = request.getParameter("date_" + i + "_1");
				round2 = request.getParameter("date_" + i + "_2");
				round3 = request.getParameter("date_" + i + "_3");
				round4 = request.getParameter("date_" + i + "_4");
				
				
			   System.out.println(showdate);
				
			   roundlist.add(showdate);
			   roundlist.add(round1);
			   roundlist.add(round2);
			   roundlist.add(round3);
			   roundlist.add(round4);
				
			   i++;
			
			}
			
			ShowRoundDao srd = new ShowRoundDao();
			
			//���� ���� ȸ�� �����ϱ�
			srd.deleteShowRound(sidx);
			
			//���� ���� ȸ�� ����ϱ�
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
			
			ReservationDao rd = new ReservationDao();
			
			//���� ���� ȸ�� ���ų��� �����ϱ�(������)
			rd.deleteUpdateReservation2(sidx);
			rd.deleteUpdateReservationIDX2(sidx);
			
			//���� ���� ȸ�� �����ߴ� ȸ���� �ҷ�����
			ArrayList<ReservationIdxVo> list = rd.deleteUpdateReservationIDX2List(sidx);
			System.out.println("ȸ�� ������ ȸ�� ���: " + list);
			
			//���� ���� ȸ�� ������ ȸ�� �������� �����ֱ�
			deleteReservationMail drm = new deleteReservationMail();
			drm.naverMailSend(list);
			
			System.out.println(roundlist);
			response.sendRedirect(request.getContextPath()+"/Show/ShowList.do");
		
		}
	}
}
