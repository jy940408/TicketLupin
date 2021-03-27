package com.TicketLupin.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TicketLupin.web.service.AnswerDao;
import com.TicketLupin.web.service.AnswerVo;
import com.TicketLupin.web.service.FaqDao;
import com.TicketLupin.web.service.FaqVo;
import com.TicketLupin.web.service.MemberDao;
import com.TicketLupin.web.service.MemberVo;
import com.TicketLupin.web.service.NoticeDao;
import com.TicketLupin.web.service.NoticeVo;
import com.TicketLupin.web.service.QuestionDao;
import com.TicketLupin.web.service.QuestionVo;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet{

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException ,java.io.IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int len = request.getContextPath().length();
		String str = uri.substring(len);
		System.out.println("str"+str);
																	
		// 공지사항
		
		if(str.equals("/Customer/NoticeList.do")) {
			
			String page_ = request.getParameter("p");
			String keyword_ = request.getParameter("keyword");
			String searchType_ = request.getParameter("searchType");	
			
			int page = 1;
			
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			String keyword = "";
			
			if(keyword_ != null && !keyword_.equals("")) {
				keyword = keyword_;
			}	
			
			String searchType = "";
			
			if(searchType_ != null && !searchType_.equals("")) {
				searchType = searchType_;
			}	
				
			NoticeDao nd = new NoticeDao();
			
			List<NoticeVo> list = nd.getNoticeList(page, keyword, searchType);
			
			int count = nd.getNoticeListCount(page, keyword, searchType);
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("searchType", searchType);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Notice_list.jsp").forward(request, response);
			
		}else if(str.equals("/Customer/NoticeWrite.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Notice_write_admin.jsp").forward(request, response);
		
		}else if(str.equals("/Customer/NoticeWriteAction.do")) {
			
			String ntitle = request.getParameter("ntitle");
			String ncontent = request.getParameter("ncontent");
			String ncategory = request.getParameter("ncategory");
			
			NoticeDao nd = new NoticeDao();
			
			nd.insertNotice(ntitle, ncontent, ncategory);
			
			response.sendRedirect("../Customer/NoticeList.do");	
			
		}else if(str.equals("/Customer/NoticeView.do")) {
			
			String nidx_ = request.getParameter("nidx");
			int nidx = Integer.parseInt(nidx_);
			
			String page_ = request.getParameter("p");
			
			int page = 1;
			
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			NoticeDao nd = new NoticeDao();
			
			NoticeVo nv = nd.getNoticeListOne(nidx);
			NoticeVo pnv = nd.getNoticeListOnePrev(nidx);
			NoticeVo nnv = nd.getNoticeListOneNext(nidx);
			
			int count = nd.getNoticeListCountAll(page);
			
			request.setAttribute("nv", nv);
			request.setAttribute("pnv", pnv);
			request.setAttribute("nnv", nnv);
			request.setAttribute("count", count);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Notice_view.jsp").forward(request, response);
			
		}else if(str.equals("/Customer/NoticeModify.do")) {
			
			String nidx_ = request.getParameter("nidx");
			int nidx = Integer.parseInt(nidx_);
			
			NoticeDao nd = new NoticeDao();
			
			NoticeVo nv = nd.getNoticeListOne(nidx);
			
			request.setAttribute("nv", nv);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Notice_modify_admin.jsp").forward(request, response);
			
		}else if(str.equals("/Customer/NoticeModifyAction.do")) {
			
			String ntitle = request.getParameter("ntitle");
			String ncontent = request.getParameter("ncontent");
			String ncategory = request.getParameter("ncategory");
			String nidx_ = request.getParameter("nidx");
			int nidx = Integer.parseInt(nidx_);
			
			NoticeDao nd = new NoticeDao();
			
			nd.noticeModify(nidx, ntitle, ncontent, ncategory);
			
			response.sendRedirect("../Customer/NoticeView.do?nidx="+nidx);			
			
		}else if(str.equals("/Customer/NoticeDeleteAction.do")) {
			
			String nidx_ = request.getParameter("nidx");
			int nidx = Integer.parseInt(nidx_);
			
			NoticeDao nd = new NoticeDao();
			
			nd.noticeDelete(nidx);
			
			response.sendRedirect("../Customer/NoticeList.do");
		}
		
		// 이용안내
		
		else if(str.equals("/Customer/Buyguide.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Buyguide.jsp").forward(request, response);
		}
		
		// FAQ
		
		else if(str.equals("/Customer/FaqList.do")) {
			
			String page_ = request.getParameter("p");
			String type_ = request.getParameter("type");
			String keyword_ = request.getParameter("keyword");

			int page = 1;
			
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			String type = "";
			
			if(type_ != null && !type_.equals("")) {
				type = type_;
			}	
			
			String keyword = "";
			
			if(keyword_ != null && !keyword_.equals("")) {
				keyword = keyword_;
			}		
			
			FaqDao fd = new FaqDao();
			
			List<FaqVo> list = fd.getFaqList(page, type, keyword);
					
			int count = fd.getFaqListCount(page, type, keyword);
			
			request.setAttribute("keyword", keyword);
			request.setAttribute("type", type);
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Faq_list.jsp").forward(request, response);
			
		}else if(str.equals("/Customer/FaqWrite.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Faq_write.jsp").forward(request, response);
			
		}else if(str.equals("/Customer/FaqWriteAction.do")) {
			
			String ftitle = request.getParameter("ftitle");
			String ftype = request.getParameter("ftype");
			String fcontent = request.getParameter("fcontent");
			
			FaqDao fd = new FaqDao();
			
			fd.insertFaq(ftitle, ftype, fcontent);
			
			response.sendRedirect("../Customer/FaqList.do");
			
		}else if(str.equals("/Customer/FaqModify.do")) {
			
			String fidx = request.getParameter("fidx");
			int fidx_ = Integer.parseInt(fidx);
			
			FaqDao fd = new FaqDao();
			
			FaqVo fv = fd.getFaqListOne(fidx_);
			
			request.setAttribute("fv", fv);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Faq_modify.jsp").forward(request, response);		
			
		}else if(str.equals("/Customer/FaqModifyAction.do")) {
			
			String ftitle = request.getParameter("ftitle");
			String ftype = request.getParameter("ftype");
			String fcontent = request.getParameter("fcontent");
			String fidx = request.getParameter("fidx");
			int fidx_ = Integer.parseInt(fidx);
			
			FaqDao fd = new FaqDao();
			
			fd.modifyFaq(ftitle, ftype, fcontent, fidx_);

			response.sendRedirect("../Customer/FaqList.do");
					
		}else if(str.equals("/Customer/FaqDeleteAction.do")) {
			
			String fidx_ = request.getParameter("fidx");
			int fidx = Integer.parseInt(fidx_);
			
			FaqDao fd = new FaqDao();
			
			fd.deleteFaq(fidx);
					
			response.sendRedirect("../Customer/FaqList.do");
			
		}
		
		// 나의 문의 내역
		
		else if(str.equals("/Customer/QuestionList.do")) {
			
			String state_ = request.getParameter("state");
			String page_ = request.getParameter("p");
			int midx = (Integer)request.getSession().getAttribute("midx");
			
			String state = "";
			
			if(state_ != null && !state_.equals("")) {
				state = state_;
			}
			
			int page = 1;
			
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			QuestionDao qd = new QuestionDao();		
				
			List<QuestionVo> list = qd.getQuestionList(state, page, midx);
			int count = qd.getQuestionListCount(state, page, midx);
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("state", state);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myask_list.jsp").forward(request, response);
	
		}else if(str.equals("/Customer/QuestionWrite.do")) {
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myask_write.jsp").forward(request, response);
			
		}else if(str.equals("/Customer/QuestionWriteAction.do")) {
			
			String qtitle = request.getParameter("qtitle");
			String qcontent = request.getParameter("qcontent");
			String qtype = request.getParameter("qtype");
			int midx = (Integer)request.getSession().getAttribute("midx");
			
			QuestionDao qd = new QuestionDao();
			qd.insertQuestion(qtitle, qcontent, qtype, midx);
			
			response.sendRedirect("../Customer/QuestionList.do");	
			
		}else if(str.equals("/Customer/QuestionModify.do")) {
			
			String qidx_ = request.getParameter("qidx");
			int qidx = Integer.parseInt(qidx_);
			int midx = (Integer)request.getSession().getAttribute("midx");
			
			QuestionDao qd = new QuestionDao();
			
			QuestionVo qv = qd.getQuestionListOne(qidx, midx);
			
			request.setAttribute("qv", qv);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myask_modify.jsp").forward(request, response);
			
		}else if(str.equals("/Customer/QuestionModifyAction.do")) {
			
			String qtitle = request.getParameter("qtitle");
			String qcontent = request.getParameter("qcontent");
			String qtype = request.getParameter("qtype");
			String qidx_ = request.getParameter("qidx");
			int qidx = Integer.parseInt(qidx_);
			int midx = (Integer)request.getSession().getAttribute("midx");
			
			QuestionDao qd = new QuestionDao();
			
			qd.modifyQuestion(qtitle, qcontent, qtype, qidx, midx);

			response.sendRedirect("../Customer/QuestionView.do?qidx="+qidx);
			
		}else if(str.equals("/Customer/QuestionView.do")) {
			
			String qidx_ = request.getParameter("qidx");
			int qidx = Integer.parseInt(qidx_);		
			String page_ = request.getParameter("p");
			int midx = (Integer)request.getSession().getAttribute("midx");
			
			int page = 1;
			
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			QuestionDao qd = new QuestionDao();
			
			QuestionVo qv = qd.getQuestionListOne(qidx, midx);
			QuestionVo pqv = qd.getQuestionListOnePrev(qidx, midx);
			QuestionVo nqv = qd.getQuestionListOneNext(qidx, midx);
			
			int count = qd.getQuestionListCountAll(page, midx);
			
			request.setAttribute("qv", qv);
			request.setAttribute("pqv", pqv);
			request.setAttribute("nqv", nqv);
			request.setAttribute("count", count);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myask_view.jsp").forward(request, response);
			
		}else if(str.equals("/Customer/QuestionView2.do")) {
			
			String qidx_ = request.getParameter("qidx");
			int qidx = Integer.parseInt(qidx_);
			String acontent = request.getParameter("acontent");
			String page_ = request.getParameter("p");
			int midx = (Integer)request.getSession().getAttribute("midx");
			
			int page = 1;
			
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			QuestionDao qd = new QuestionDao();
			AnswerDao ad = new AnswerDao();
			
			QuestionVo qv = qd.getQuestionListOne(qidx, midx);
			QuestionVo pqv = qd.getQuestionListOnePrev(qidx, midx);
			QuestionVo nqv = qd.getQuestionListOneNext(qidx, midx);
			
			AnswerVo av = ad.getAnswerListOne(qidx, midx);
			
			int count = qd.getQuestionListCountAll(page, midx);
			
			request.setAttribute("qv", qv);
			request.setAttribute("pqv", pqv);
			request.setAttribute("nqv", nqv);
			request.setAttribute("count", count);
			
			request.setAttribute("av", av);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myask_view2.jsp").forward(request, response);
			
		}else if(str.equals("/Customer/QuestionDeleteAction.do")) {
			
			String qidx_ = request.getParameter("qidx");
			int qidx = Integer.parseInt(qidx_);
			int midx = (Integer)request.getSession().getAttribute("midx");
			
			QuestionDao qd = new QuestionDao();
			
			qd.deleteQuestion(qidx, midx);
			
			response.sendRedirect("../Customer/QuestionList.do");		
		}
		
		//문의 관리
		
		else if(str.equals("/Customer/AnswerMain.do")) {
			
			String keyword_ = request.getParameter("keyword"); //검색창 키워드
			String searchType_ = request.getParameter("searchType"); //검색 유형
			String type_ = request.getParameter("type"); //문의 유형
			String state_ = request.getParameter("state"); //문의 답변여부
			String page_ = request.getParameter("p");
			
			String keyword = "";
			
			if(keyword_ != null && !keyword_.equals("")) {
				keyword = keyword_;
			}
			
			String searchType = "";
			
			if(searchType_ != null && !searchType_.equals("")) {
				searchType = searchType_;
			}
			
			String type = "";
			
			if(type_ != null && !type_.equals("")) {
				type = type_;
			}
			
			String state = "";
			
			if(state_ != null && !state_.equals("")) {
				state = state_;
			}
			
			int page = 1;
			
			if(page_ != null && !page_.equals("")) {
				page = Integer.parseInt(page_);
			}
			
			QuestionDao qd = new QuestionDao();
			
			List<QuestionVo> list = qd.getQuestionList2(keyword, searchType, type, state, page);
	
			int count = qd.getQuestionListCount2(keyword, searchType, type, state, page);
			
			request.setAttribute("list", list);
			request.setAttribute("searchType", searchType);
			request.setAttribute("type", type);
			request.setAttribute("state", state);
			request.setAttribute("count", count);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Admin_qna_list.jsp").forward(request, response);
			
		}else if(str.equals("/Customer/AnswerWrite.do")) {
			
			String midx_ = request.getParameter("midx");
			int midx = Integer.parseInt(midx_);	
			String qidx_ = request.getParameter("qidx");
			int qidx = Integer.parseInt(qidx_);
			
			QuestionDao qd = new QuestionDao();
			
			QuestionVo qv = qd.getQuestionListOne2(qidx, midx);
			
			request.setAttribute("qv", qv);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myask_view_manage_write.jsp").forward(request, response);
			
		}else if(str.equals("/Customer/AnswerWriteAction.do")) {
			
			String acontent = request.getParameter("acontent");
			String qidx_ = request.getParameter("qidx");
			int qidx = Integer.parseInt(qidx_);
			String midx_ = request.getParameter("midx");
			int midx = Integer.parseInt(midx_);
			
			AnswerDao ad = new AnswerDao();
			
			ad.insertAnswer(acontent, qidx, midx);
			
			response.sendRedirect("../Customer/AnswerView.do?midx="+midx+"&qidx="+qidx);
			
		}else if(str.equals("/Customer/AnswerModify.do")) {
			
			String midx_ = request.getParameter("midx");
			int midx = Integer.parseInt(midx_);	
			String qidx_ = request.getParameter("qidx");
			int qidx = Integer.parseInt(qidx_);
			
			QuestionDao qd = new QuestionDao();
			AnswerDao ad = new AnswerDao();
			
			QuestionVo qv = qd.getQuestionListOne2(qidx, midx);
			AnswerVo av = ad.getAnswerListOne(qidx, midx);
			
			request.setAttribute("qv", qv);
			request.setAttribute("av", av);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myask_view_manage_modify.jsp").forward(request, response);
			
		}else if(str.equals("/Customer/AnswerModifyAction.do")) {
			
			String acontent = request.getParameter("acontent");
			String qidx_ = request.getParameter("qidx");
			int qidx = Integer.parseInt(qidx_);
			String midx_ = request.getParameter("midx");
			int midx = Integer.parseInt(midx_);
			
			AnswerDao ad = new AnswerDao();
			
			ad.modifyAnswer(acontent, qidx, midx);
			
			response.sendRedirect("../Customer/AnswerView.do?midx="+midx+"&qidx="+qidx);
			
		}else if(str.equals("/Customer/AnswerDeleteAction.do")) {
			
			String qidx_ = request.getParameter("qidx");
			int qidx = Integer.parseInt(qidx_);
			String midx_ = request.getParameter("midx");
			int midx = Integer.parseInt(midx_);
			
			System.out.println(qidx);
			System.out.println(midx);
			
			QuestionDao qd = new QuestionDao();
			
			qd.deleteQuestion(qidx, midx);
			
			response.sendRedirect("../Customer/AnswerMain.do");
			
		}else if(str.equals("/Customer/AnswerView.do")) {
			
			String midx_ = request.getParameter("midx");
			int midx = Integer.parseInt(midx_);	
			String qidx_ = request.getParameter("qidx");
			int qidx = Integer.parseInt(qidx_);
			
			QuestionDao qd = new QuestionDao();
			AnswerDao ad = new AnswerDao();
			
			QuestionVo qv = qd.getQuestionListOne2(qidx, midx);
			AnswerVo av = ad.getAnswerListOne(qidx, midx);
			
			request.setAttribute("qv", qv);
			request.setAttribute("av", av);
			
			request.getRequestDispatcher("/WEB-INF/view/jsp/Myask_view_manage.jsp").forward(request, response);
		}
			
	}
	
}
