<%@page import="com.TicketLupin.web.service.*"%>
<%@page import="domain.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	ArrayList<EventVo> alist = (ArrayList<EventVo>) request.getAttribute("alist"); 
	PageMaker pm = (PageMaker)request.getAttribute("pm");
	System.out.println("pm.getStartPage() jsp부분: " + pm.getStartPage());
	System.out.println("pm.getStartPage() getAttribute: " + request.getAttribute("StartPage"));
	System.out.println("alist jsp부분: " + alist);
	System.out.println("alist getAttribute: " + request.getAttribute("alist"));
%>

<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<link rel="stylesheet" type"text/css" href="<%=request.getContextPath() %>/css/Event_list.css">
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Event_list.js"></script>
	</head>
	<body>
		<header>
			<div id="h_title">
				<div id="h_title_inner">
					<span id="h_top_menu">
						<ul id="h_top_menu_ul">
							<%
								if(session.getAttribute("mid") != null){
									String mid = (String) session.getAttribute("mid");
							%>
							<li><%=mid %>님 환영합니다!&nbsp;&nbsp;&nbsp;&nbsp;</li>
							<li><a href="<%=request.getContextPath()%>/Member/Memberlogout.do">로그아웃&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<% 
								} else{
							%> 
							<li class="login"><a href="<%=request.getContextPath()%>/Member/MemberLogin.do">로그인&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="<%=request.getContextPath()%>/Member/MemberJoin.do">회원가입&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<% } %>
							<li><a href="<%=request.getContextPath()%>/Notice/NoticeList.do">고객센터&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">이용안내&nbsp;&nbsp;&nbsp;&nbsp;</a></li><br/>
						</ul>
						<img src="../ads/musicalads.png" id="h_ads">
					</span>
					<a href="<%=request.getContextPath()%>/Main/MainPage.do">
						<img src="../icon/lupinlogo.png" id="h_logo"/>
					</a>
					<input type="text" id="h_search" placeholder="뮤지컬 〈캣츠〉 40주년 내한공연 앙코르－서울（Musical CATS Encore">
					<button type="submit" id="h_search_button"><img src="../icon/search.png" id="h_search_img"></button>
				</div>
			</div>
		</header>
		<hr id="nav_bar_top">
		<div id="n_nav_div">
			<nav id="main_nav">
				<a href="#" id="main_nav_home">홈</a>
				<a href="#" id="main_nav_concert">공연</a>
				<a href="#" id="main_nav_ranking">랭킹</a>
				<a href="#" id="main_nav_news">티켓오픈소식</a>
				<a href="#" id="main_nav_event">이벤트</a>
				<a href="#" id="main_nav_myticket">마이 티켓</a>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		<div id="nav_menu_sub_event_div" class="main_nav_all">
			<ul id="nav_menu_sub_event" style="margin:0px;">
				<li><a href="#">전체 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">당첨자 발표</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="<%=request.getContextPath()%>/Event/MyEvent.do">참여 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		<div id="nav_menu_sub_myticket_div" class="main_nav_all">
			<ul id="nav_menu_sub_myticket" style="margin:0px;">
				<li><a href="#">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">할인쿠폰</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		
<!------------------------------------------------------------------------------------------------------------------------------------->		
		<script>
			function search(){
				frm.action="<%=request.getContextPath()%>/Event/EventMain.do";
				frm.method="get";
				frm.submit();
			}
		</script>
		<section>
			<article>
				<div id="main_eventlist_all">
					
					<div id="manage_write_div">
						<a href="<%=request.getContextPath()%>/Event/EventWrite.do" id="manage_write">작성하기</a>
					</div>
					
					
					<div id="main_banner_set">
						<div class="main_event_banner" id="main_event_banner_main">
							<img id="main_event_banner_main_img">
							<div id="main_event_banner_main_ment">[Follow Me!] 티켓루팡 SNS에서 더 많은 공연 이야기를 만나보세요</div>
						</div>
						<div class="main_event_banner" id="main_event_banner_sub">
							<img id="main_event_banner_sub_img">
							<div id="main_event_banner_sub_ment">Hullo Hullo, Following on: 로즈 와일리 전</div>
						</div>
					</div>
					
					<div id="main_search_set">
						<form id="main_search_form" name="frm">
							<input type="text" name="keyword" id="main_search_text" placeholder="&nbsp;&nbsp;이벤트 검색">
							<button id="main_search_button" onclick="search();">검색</button>
						</form>
					</div>
					
			
					<div id="main_event_list_set">
						<ul id="main_evenet_list">
						<% for(EventVo ev : alist) { %>
							<li class="main_evenet_list_li">
								<div class="main_evenet_list_div" id="main_evenet_list_1">
									<a href="<%=request.getContextPath()%>/Event/EventView.do?eidx=<%=ev.getEidx()%>" class="main_evenet_list_a">
										<img class="main_evenet_list_img" src="<%=request.getContextPath()%>/image/<%=ev.getEthumbnail() %>">
										<div><%=ev.getEtitle() %></div>
									</a>
									<div class="main_event_list_detail">
										<span>이벤트 기간: <%=ev.getEstart() %> ~ <%=ev.getEend() %></span><br>
										<span>당첨혜택: </span><br>
										<span>당첨자 발표일: </span>
									</div>
									<input type="hidden" name="edelyn" value="<%=ev.getEdelyn() %>">
								</div>
							</li>
						<% } %>	
						</ul>
					</div>
			
		
					<div id="main_event_page">
						<div id="main_event_page_set">
							<a href="#">
								<div class="main_event_page_button main_event_page_bn">
									<div class="main_event_page_button_llgg">&lt;&lt;</div>
								</div>
								<div class="main_event_page_button main_event_page_bn">
									<%if (pm.isPrev() == true) { %>
									<div class="main_event_page_button_lg">
										<a href="<%=request.getContextPath()%>/Event/EventMain.do?page=<%=pm.getStartPage()-1%>
										&keyword=<%=pm.encoding(pm.getScri().getKeyword())%>">
											&lt;
										</a>
									</div>
									<% } %>
								</div>
							</a>
							<a href="#">
								<div class="main_event_page_bn">
									<div class="main_event_page_button_page">
										<% for (int i=pm.getStartPage(); i<=pm.getEndPage(); i++){ %>
											<a href="<%=request.getContextPath()%>/Event/EventMain.do?page=<%=i%>&keyword=<%=pm.encoding(pm.getScri().getKeyword())%>"><%=i%>${pm.getStartPage()}</a>	
										<% 
										System.out.println("i가 뭐야: " + i);
										} 
											
										%>
									</div>
								</div>
							</a>
							<a href="#">
								<div class="main_event_page_button main_event_page_bn">
									<%if (pm.isNext() && pm.getEndPage() >0) { %>
									<div class="main_event_page_button_lg">
										<a href="<%=request.getContextPath()%>/Event/EventMain.do?page=<%=pm.getEndPage()+1%>
										&keyword=<%=pm.encoding(pm.getScri().getKeyword())%>">
											&gt;
										</a>
									</div>
									<% } %>
								</div>
								<div class="main_event_page_button main_event_page_bn">
									<div class="main_event_page_button_llgg">&gt;&gt;</div>
								</div>
							</a>
						</div>
					</div>
					
		
				</div>
			</article>
		</section>

<!------------------------------------------------------------------------------------------------------------------------------------->

		<footer>
				<hr class="f_bar" id="f_bar_bottom">
				<div id="f_last">
					<span class="f_bottom_ment"><img src="../icon/lupinlogo.png" id="f_logo"></span>
					<span class="f_bottom_ment">
						<span class="f_bottom_tagset">예매문의(1234-1234)</span>
						<a href="#" class="f_bottom_tagset">티켓판매제휴&nbsp;&nbsp;&nbsp;&nbsp;</a>
						<a href="#" class="f_bottom_tagset">예매가이드&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</span>
				</div>
				<hr class="f_bar" id="f_bar_bottom">
			<div class="f_title_inner">
				<span id="f_menu_contract_span">
					<ul id="f_menu_contract">
						<li><a href="#">회사소개</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">이용약관</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">전자금융거래약관</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">위치기반서비스 이용약관&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						<li><a href="#" style="font-weight:bold;">개인정보처리방침</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">청소년보호정책</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">파트너센터</a>&nbsp;&nbsp;&nbsp;&nbsp;</li><br>
					</ul>
				</span>
			</div>
			<div class="f_title_inner">
				<span id="f_menu_produce">
					<ul id="f_menu_produce">
						<li><a href="#" style="font-weight:bold;">(주)티켓루팡</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">전북 전주시 덕진구 백제대로 572 4층</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">대표이사:최민우</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">사업자등록번호: 111-11-11111&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						<li><a href="#">통신판매업 신고번호: 제1111-이젠이젠-1111호 <a href="#" style="font-weight:bold;">&nbsp;&nbsp;사업자정보확인>&nbsp;&nbsp;</a></a>&nbsp;&nbsp;&nbsp;&nbsp;</li><br>
						<li><a href="#">고객센터(평일/주말 09:00~18:00): 1234-1234(유료)</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">호스팅제공자: (주)티켓루팡</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">&copy;TicketLupin Corp. All rights reserved.</a>&nbsp;&nbsp;&nbsp;&nbsp;</li><br>
					</ul>
				</span>
			</div>
		</footer>
	</body>
</html>
