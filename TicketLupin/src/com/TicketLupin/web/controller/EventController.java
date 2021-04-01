package com.TicketLupin.web.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.EventDao;
import com.TicketLupin.web.service.EventVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import domain.PageMaker;
import domain.SearchCriteria;

@WebServlet("/EventController")    
public class EventController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Event/EventMain.do")) {
			
			String page = request.getParameter("page");
			if (page == null) page ="1";
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if(keyword == null) keyword = "";
			
			
			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setKeyword(keyword);
			
			PageMaker pm = new PageMaker();
			pm.setScri(scri);
			
			EventDao ed = new EventDao();
			int cnt = ed.eventTotal(keyword);
			
			pm.setTotalCount(cnt);
			
			ArrayList<EventVo> alist = ed.eventSelectAll(scri);
			List<EventVo> list = ed.eventbanner1();
			List<EventVo> list2 = ed.eventbanner2();
			
			request.setAttribute("alist", alist);
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			request.setAttribute("pm", pm);
			
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Event_list.jsp").forward(request, response);
			
		}else if(str.equals("/Event/EventView.do")) {
			
			String eidx2 = request.getParameter("eidx");
			
			int eidx = 0;
			if(eidx2 != null && !eidx2.equals("")) {
				eidx = Integer.parseInt(eidx2);
			}
			
			
			EventDao ed = new EventDao();
			EventVo ev = ed.eventSelectOne(eidx);
			
			
			
			request.setAttribute("ev", ev);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Event_view.jsp").forward(request, response);
			
			
			
			
		}else if(str.equals("/Event/MyEvent.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myevent_list.jsp").forward(request, response);
			
		}else if(str.equals("/Event/EventWrite.do")) {
			
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Event_write_admin.jsp").forward(request, response);
		
		}else if(str.equals("/Event/EventModify.do")) {
			
			String eidx = request.getParameter("eidx");
			int eidx2 = Integer.parseInt(eidx);
			
			EventDao ed = new EventDao();
			EventVo ev = ed.eventSelectOne(eidx2);
			
			request.setAttribute("ev", ev);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/jsp/Event_modify_admin.jsp");
			rd.forward(request, response);
			
		}else if(str.equals("/Event/EventDeleteAction.do")) {
			
			String eidx2 = request.getParameter("eidx");
			System.out.println("������ eidx : "+eidx2);
			
			int eidx = 0;
			if(eidx2 != null && !eidx2.equals("")) {
				eidx = Integer.parseInt(eidx2);
			}
			
			EventDao ed = new EventDao();
			ed.EventDelete(eidx);
			response.sendRedirect("../Event/EventMain.do");
		}else if(str.equals("/Event/EventWriteAction.do")) {
			
			String uploadPath = request.getSession().getServletContext().getRealPath("image");
			int sizeLimit = 1024*1024*15;
			String efiles = "";
			String originFileName = "";
			
			String ethumbnail = "";
			String originThumb = "";
			
			MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy()); 
			
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement();
			String file2 = (String)files.nextElement();
			
			ethumbnail = multi.getFilesystemName(file);
			originThumb = multi.getOriginalFileName(file);

			efiles = multi.getFilesystemName(file2);
			originFileName = multi.getOriginalFileName(file2);
			
			String etitle = multi.getParameter("etitle");
			String econtent = multi.getParameter("econtent");
			String estart = multi.getParameter("estart");
			String eend = multi.getParameter("eend");
			String eidx2 = multi.getParameter("eidx");
			String ecategory = multi.getParameter("ecategory");
			
			int eidx = 0;
			if(eidx2 != null && !eidx2.equals("")) {
				eidx = Integer.parseInt(eidx2);
			}
			
			
			HttpSession session = request.getSession();
			
			int midx = 0;
			if(session.getAttribute("midx") != null) {
				midx = (int)session.getAttribute("midx");
			}
			
			EventVo ev = new EventVo();
			ev.setEtitle(etitle);
			ev.setEcontent(econtent);
			ev.setEstart(estart);
			ev.setEend(eend);
			ev.setMidx(midx);
			ev.setEfiles(originFileName);
			ev.setEthumbnail(originThumb);
			ev.setEcategory(ecategory);
			
			
			System.out.println("test->>>>>>>>>>"+ev.toString());
			System.out.println("uploadPath : "+uploadPath);
			
			EventDao ed = new EventDao();
			ed.insertEvent(etitle, econtent, efiles, estart, eend, ethumbnail, ecategory);
			
			response.sendRedirect("../Event/EventMain.do");
			
			
		}else if(str.equals("/Event/EventModifyAction.do")) {
			
			
			String uploadPath = request.getSession().getServletContext().getRealPath("image");
			int sizeLimit = 1024*1024*15;
			String efiles = "";
			String originFileName = "";
			
			String ethumbnail = "";
			String originThumb = "";
			
			MultipartRequest multi = new MultipartRequest(request, uploadPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
			
			String etitle = multi.getParameter("etitle");
			String econtent = multi.getParameter("econtent");
			String estart = multi.getParameter("estart");
			String eend = multi.getParameter("eend");
			String ecategory = multi.getParameter("ecategory");
			String eidx = multi.getParameter("eidx");
			int eidx2 = Integer.parseInt(eidx);
			
			
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement();
			String file2 = (String)files.nextElement();
			
			ethumbnail = multi.getFilesystemName(file);
			originThumb = multi.getOriginalFileName(file);

			efiles = multi.getFilesystemName(file2);
			originFileName = multi.getOriginalFileName(file2);
			
			HttpSession session = request.getSession();
			
			
			EventVo ev = new EventVo();
			ev.setEcategory(ecategory);
			ev.setEstart(estart);
			ev.setEend(eend);
			ev.setEtitle(etitle);
			ev.setEcontent(econtent);
			ev.setEfiles(efiles);
			ev.setEthumbnail(ethumbnail);
			ev.setEidx(eidx2);
			EventDao ed = new EventDao();
			ed.EventModify(etitle, econtent, estart, eend, efiles, ethumbnail, ecategory, eidx2);
			
			
			System.out.println("test->>>>>>>>>>"+ev.toString());
			
			response.sendRedirect("../Event/EventMain.do");
		
		}
		
	}
}
