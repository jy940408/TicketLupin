package com.TicketLupin.web.controller;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.TicketLupin.web.service.QuestionDao;
import com.TicketLupin.web.service.QuestionVo;

@WebServlet("/QuestionController")
public class QuestionController extends HttpServlet{

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
		
		if(str.equals("/Question/QuestionWrite.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myask_write.jsp").forward(request, response);
			
		}else if(str.equals("/Question/QuestionWriteAction.do")) {
			
			String qtitle = request.getParameter("qtitle");
			String qcontent = request.getParameter("qcontent");
			
			QuestionDao qd = new QuestionDao();
			qd.insertQuestion(qtitle, qcontent);
			
			response.sendRedirect(request.getContextPath()+"/Question/QuestionList.do");
			
		}else if(str.equals("/Question/QuestionList.do")) {
			
			String page_ = request.getParameter("p");
			
			int page = 1;
			
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			QuestionDao qd = new QuestionDao();
			
			List<QuestionVo> list = qd.getQuestionList(page);
			
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myask_list.jsp").forward(request, response);
		}
		
	};
	
}
