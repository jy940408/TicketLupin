package com.TicketLupin.web.controller;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.TicketLupin.web.service.DibsDao;
import com.TicketLupin.web.service.DibsListVo;
import com.TicketLupin.web.service.DibsVo;

@WebServlet("/DibsController")
public class DibsController extends HttpServlet{

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Dibs/DibsAction.do")) {
			
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
			
			DibsDao dd = new DibsDao();
			dd.insertDibs(sidx, midx);
			int value = dd.getDibsCheck(sidx, midx);
			
			JSONObject obj = new JSONObject();
			obj.put("result", value);
			obj.put("test","testDatea");

			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(obj); //{"result":1}
			
		}else if(str.equals("/Dibs/DibsDeleteAction.do")) {
			
			HttpSession session = request.getSession();
			
			String sidx_ = request.getParameter("sidx");
			
			int midx = 0;
			if(session.getAttribute("midx") != null) {
				midx = (int) session.getAttribute("midx");
			}
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			System.out.println("midx_: " + midx);
			System.out.println("諛쏆븘�삤�뒗 媛�: " + sidx);
			
			DibsDao dd = new DibsDao();
			dd.deleteDibs(sidx, midx);
			int value = dd.getDibsCheck(sidx, midx);
			
			JSONObject obj = new JSONObject();
			obj.put("result", value);
			
			
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(obj);
			
		}else if(str.equals("/Dibs/DibsDeleteMyPageAction.do")) {
			
			HttpSession session = request.getSession();
			
			String sidx_ = request.getParameter("sidx");
			String page_ = request.getParameter("page");
			
			int midx = 0;
			if(session.getAttribute("midx") != null) {
				midx = (int) session.getAttribute("midx");
			}
			
			int sidx = 0;
			if(sidx_ != null && !sidx_.equals("")) {
				sidx = Integer.parseInt(sidx_);
			}
			
			int page = 1;
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			System.out.println("midx_: " + midx);
			System.out.println("諛쏆븘�삤�뒗 媛�: " + sidx);
			
			DibsDao dd = new DibsDao();
			dd.deleteDibs(sidx, midx);
			int value = dd.getDibsCheck(sidx, midx);
			
			JSONArray dibsArray = new JSONArray();
			JSONObject dibsObj = new JSONObject();
			
			ArrayList<DibsListVo> list = dd.getDibsList(midx, page);
			System.out.println("리스트 확인: " + list);
			
			for(int i = 0 ; i < list.size() ; i++) {
				JSONObject dibsObj_ = new JSONObject();
				dibsObj_.put("num", list.get(i).getNum());
				dibsObj_.put("sidx", list.get(i).getSidx());
				dibsObj_.put("stitle", list.get(i).getStitle());
				dibsObj_.put("sopendate", list.get(i).getSopendate());
				dibsObj_.put("senddate", list.get(i).getSenddate());
				dibsObj_.put("midx", list.get(i).getMidx());
				dibsArray.add(dibsObj_);
				
				System.out.println("흠...: " + dibsArray);
			}
			
			dibsObj.put("list", dibsArray);
			System.out.println("테스트: " + dibsObj.get("list"));
			
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(dibsArray);
			
		}else if(str.equals("/Dibs/MyDibs.do")) {
			
			HttpSession session = request.getSession();
			
			String page_ = request.getParameter("page");
			
			int midx = 0;
			if(session.getAttribute("midx") != null) {
				midx = (int) session.getAttribute("midx");
			}
			
			int page = 1;
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			System.out.println("midx: " + midx);
			
			DibsDao dd = new DibsDao();
			ArrayList<DibsListVo> list = dd.getDibsList(midx, page);
			int count = dd.getDibsListCount(midx);
			
			System.out.println(count);
			System.out.println(list);
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myticket_dibs_list.jsp").forward(request, response);
			
		}
		
	};
	
}
