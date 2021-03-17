package com.TicketLupin.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
			
			System.out.println("끝 페이지: " + pm.getEndPage());
			System.out.println("시작 페이지: " + pm.getStartPage());
			
			EventDao ed = new EventDao();
			int cnt = ed.eventTotal(keyword);
			
			pm.setTotalCount(cnt);
			
			ArrayList<EventVo> alist = ed.eventSelectAll(scri);
			
			request.setAttribute("alist", alist);
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
			
			String eidx2 = request.getParameter("eidx");
			
			int eidx = 0;
			if(eidx2 != null && !eidx2.equals("")) {
				eidx = Integer.parseInt(eidx2);
			}
			
			EventDao ed = new EventDao();
			EventVo ev = ed.eventSelectOne(eidx);
			
			request.setAttribute("ev", ev);
			request.getRequestDispatcher("/WEB-INF/view/jsp/Event_modify_admin.jsp").forward(request, response);
			
		}else if(str.equals("/Event/EventDeleteAction.do")) {
			
			String eidx2 = request.getParameter("eidx");
			System.out.println("삭제된 eidx : "+eidx2);
			
			int eidx = 0;
			if(eidx2 != null && !eidx2.equals("")) {
				eidx = Integer.parseInt(eidx2);
			}
			
			EventDao ed = new EventDao();
			ed.EventDelete(eidx);
			response.sendRedirect("../Event/EventMain.do");
		}
		
	}
	
	
	@Override
	protected void doPost(javax.servlet.http.HttpServletRequest request,  javax.servlet.http.HttpServletResponse response)  throws javax.servlet.ServletException ,java.io.IOException{
		//doGet(request, response);
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		
		if(str.equals("/Event/EventWriteAction.do")) {
			
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
			
			String etitle = request.getParameter("etitle");
			String econtent = request.getParameter("econtent");
			String estart=request.getParameter("estart");
			String eend = request.getParameter("eend");
			String eidx2 = request.getParameter("eidx");
			String ecategory = request.getParameter("ecategory");
			
			int eidx = 0;
			
			if(eidx2 != null && !eidx2.trim().equals("")) {
				eidx = Integer.parseInt(eidx2);
			}
			
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement();
			String file2 = (String)files.nextElement();
			
			ethumbnail = multi.getFilesystemName(file);
			originThumb = multi.getOriginalFileName(file);

			efiles = multi.getFilesystemName(file2);
			originFileName = multi.getOriginalFileName(file2);
			
			HttpSession session = request.getSession();
			
			String getid = (String) session.getAttribute("mid");
			
			EventVo ev = new EventVo();
			ev.setEtitle(etitle);
			ev.setEcontent(econtent);
			ev.setEstart(estart);
			ev.setEend(eend);
			ev.setEfiles(originFileName);
			ev.setEidx(eidx);
			ev.setEthumbnail(originThumb);
			ev.setEcategory(ecategory);
			
			EventDao ed = new EventDao();
			boolean result = ed.EventModify(eidx, etitle, econtent, estart, eend, efiles, ethumbnail, ecategory);
			
			System.out.println(result);
			
			response.sendRedirect("../Event/EventMain.do");
		
		}
	}
	
	
	
	

	
	
}
