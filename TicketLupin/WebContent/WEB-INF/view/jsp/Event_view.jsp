<%@ page import="com.TicketLupin.web.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% EventVo ev = (EventVo)request.getAttribute("ev"); %>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓루팡</title>
		<script src="<%=request.getContextPath() %>/js/Event_view.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Event_view.css">
		<script>
			function edelete(){
				alert("삭제합니다.");
				
				location.href="<%=request.getContextPath()%>/Event/EventDeleteAction.do?eidx=<%=ev.getEidx()%>";
			}
		</script>
	</head>
	<body>
		<header>
			<div id="h_title">
				<div id="h_title_inner">
					<span id="h_top_menu">
						<ul id="h_top_menu_ul">
							<li><a href="#">로그인&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">회원가입&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">고객센터&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">이용안내&nbsp;&nbsp;&nbsp;&nbsp;</a></li><br>
						</ul>
						<img src="../ads/musicalads.png" id="h_ads">
					</span>
					<img src="../icon/lupinlogo.png" id="h_logo">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" id="h_search" placeholder="뮤지컬 〈캣츠〉 40주년 내한공연 앙코르－서울（Musical CATS Encore">
					<button type="submit" id="h_search_button"><img src="../icon/search.png" id="h_search_img"></button>
				</div>
			</div>
		</header>
		<hr id="nav_bar_top">
		<div id="n_nav_div">
			<nav id="main_nav">
				<a href="#" id="n_home">홈</a>
				<a href="#">공연</a>
				<a href="#">랭킹</a>
				<a href="#">티켓오픈소식</a>
				<a href="#">이벤트</a>
				<a href="#">마이 티켓</a>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		<div id="nav_menu_sub_div">
			<ul id="nav_menu_sub">
				<li><a href="<%=request.getContextPath()%>/Event/EventMain.do">전체 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">당첨자 발표</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">참여 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
		</div>
		<hr id="nav_bar_sub">
		
		
		<section>
			<article>
				<div class="view">
					<input type="hidden" name="eidx" value="<%=ev.getEidx() %>">
					<input type="hidden" name="midx" value="<%=ev.getMidx() %>">
					<div class="view_top">
						<div class="view_top_title">
							<span class="view_top_span1">
								<%=ev.getEtitle()%>
							</span>
							<input type="text" id="url" class="url_input" size="35" />
							<button class="url_btn" onclick="urlClipCopy()">
								<img src="../icon/url.png" class="url_btn_img">
							</button>
						</div>
						<div class="view_top_period">
							<span class="view_top_span2">
								기간 : <%=ev.getEstart()%> ~ <%=ev.getEend()%>
							</span>
						</div>
					</div>
					<div class="view_mid">
						<center>
							<img src="<%=request.getContextPath()%>/image/<%=ev.getEfiles()%>">
							<div style="font-size:20px;"><%=ev.getEcontent()%></div>
						</center>
					</div>
					
					<div class="modify">
						<button class="remove_btn" onclick="edelete()">제거하기</button>
						<button class="modify_btn" onclick="location='<%=request.getContextPath()%>/Event/EventModify.do?eidx=<%=ev.getEidx()%>'">수정</button>
					</div>
					<div class="list">
						<button class="list_btn" onclick="location='<%=request.getContextPath()%>/Event/EventMain.do'">목록으로</button>
					</div>					
				</div>
			</article>
		</section>
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