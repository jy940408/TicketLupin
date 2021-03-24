<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/nav_event.js"></script>
		<script src="<%=request.getContextPath() %>/js/Myticket_buy_list.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Myticket_buy_list.css">
		<script src="${pageContext.request.contextPath}/js/loginAlert.js"></script>
	</head>
	<body>
		<header>
			<div id="h_title">
				<div id="h_title_inner">
					<span id="h_top_menu">
						<ul id="h_top_menu_ul">
						<c:if test="${not empty sessionScope.mid}">
							<li>${sessionScope.mid }님 환영합니다!&nbsp;&nbsp;&nbsp;&nbsp;</li>
							<li><a href="${pageContext.request.contextPath}/Member/Memberlogout.do">로그아웃&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
						<c:if test="${empty sessionScope.mid}">
							<li class="login"><a href="${pageContext.request.contextPath}/Member/MemberLogin.do">로그인&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="${pageContext.request.contextPath}/Member/MemberJoin.do">회원가입&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
							<li><a href="${pageContext.request.contextPath}/Notice/NoticeList.do">고객센터&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">이용안내&nbsp;&nbsp;&nbsp;&nbsp;</a></li><br/>
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
				<a href="${pageContext.request.contextPath}/Main/MainPage.do" id="main_nav_home">홈</a>
				<a href="${pageContext.request.contextPath}/Show/ShowList.do" id="main_nav_concert">공연</a>
				<a href="${pageContext.request.contextPath}/Show/RankingList.do" id="main_nav_ranking">랭킹</a>
				<a href="${pageContext.request.contextPath}/News/NewsList.do" id="main_nav_news">티켓오픈소식</a>
				<a href="#" id="main_nav_event">이벤트</a>
				<c:choose>
					<c:when test="${sessionScope.mgrade eq 'M' }">
						<a href="#" id="main_nav_myticket">관리자</a>
					</c:when>
					<c:otherwise>
						<a href="#" id="main_nav_myticket">마이티켓</a>
					</c:otherwise>
				</c:choose>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		<div id="nav_menu_sub_event_div" class="main_nav_all">
			<ul id="nav_menu_sub_event" style="margin:0px;">
				<li><a href="${pageContext.request.contextPath}/Event/EventMain.do">전체 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="${pageContext.request.contextPath}/Winner/WinnerList.do">당첨자 발표</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">참여 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		<div id="nav_menu_sub_myticket_div" class="main_nav_all">
			<ul id="nav_menu_sub_myticket" style="margin:0px;">
				<c:choose>
					<c:when test="${sessionScope.mgrade eq 'M' }">
						<li><a href="${pageContext.request.contextPath}/Admin/AdminMain.do">관리자홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/Admin/AdminMember.do">회원관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="#">공연관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="#">댓글관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="#">문의관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${not empty sessionScope.mid}">
								<li><a href="#">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a href="${pageContext.request.contextPath}/Myticket/MyticketReservation.do">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a href="${pageContext.request.contextPath}/Dibs/MyDibs.do">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a href="#">할인쿠폰</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							</c:when>
							<c:otherwise>
								<li><a onclick="loginAlert()">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a onclick="loginAlert()">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a onclick="loginAlert()">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a onclick="loginAlert()">할인쿠폰</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		
		
		<section>
			<article>
				<div style="padding:30px;"></div>
				<div class="warp_tap">
					<div class="btns">
						<button class="btn_rsrv" type="button">예매확인</button>
						<button class="btn_cancel" type="button">예매취소</button>
					</div>
					<div class="box_list_date">
						<table border="1">
							<tr>
								<td width="57px"><a href="#">6개월</a></td>
								<td width="57px"><a href="#">12개월</a></td>
								<td width="57px"><a href="#">24개월</a></td>
							</tr>
						</table>
					</div>
				</div>
				
				<div class="cont">
					<div class="box_customer" style="display:block;">
						<table class="table_list">
							<thead>
								<tr>
									<th width="10%"></th>
									<th width="40%">예매확인</th>
									<th width="10%">좌석</th>
									<th width="10%">공연날짜</th>
									<th width="15%"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="l" items="${list}">
								<tr>
									<td>${l.num}</td>
									<td class="td_"><a href="${pageContext.request.contextPath}/Myticket/MyticketDetail.do?ridx=${l.ridx}">${l.stitle }</a></td>
									<td>
										${l.rseat}
									</td>
									<td>
										${l.srdate}<br>
										${l.srround}
									</td>
									<td>
										<button type="submit" value="cancel" class="td_button">취소하기</button>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
<!--------------------------------------------------------------------------------------------------------------------->
		
				<c:set var="page" value="${(param.p == null)?1:param.p}"/>
				<c:set var="startNum" value="${page-(page-1)%5}"/>
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/15),'.')}"/>

<!--------------------------------------------------------------------------------------------------------------------->
					
					<div id="main_news_page">
						<div id="main_news_page_set">
<!--------------------------------------------------------------------------------------------------------------------->
							<a href="?p=1&q=">
								<div class="main_news_page_button main_news_page_bn">
									<div class="main_news_page_button_llgg">&lt;&lt;</div>
								</div>
							</a>						
							<c:if test="${startNum>1}">
								<a href="?p=${startNum-1}">
									<div class="main_news_page_button main_news_page_bn">
										<div class="main_news_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
							<c:if test="${startNum<=1}">
								<a href="#" onclick="alert('이전 페이지가 없습니다.');">
									<div class="main_news_page_button main_news_page_bn">
										<div class="main_news_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
<!--------------------------------------------------------------------------------------------------------------------->
							
							<div class="main_news_page_bn">
								<c:forEach var="i" begin="0" end="4">
									<c:if test="${(startNum+i) <= lastNum}">
										<div class="main_news_page_button_page">
											<a style="color: ${(page==(startNum+i))?'red':'black'}; font-weight:${(page==(startNum+i))?'bold':''};" href="?p=${startNum+i}" >${startNum+i}</a>
										</div>
									</c:if>
								</c:forEach>
							</div>
<!--------------------------------------------------------------------------------------------------------------------->
							<c:if test="${startNum+4<lastNum}">
								<a href="?p=${startNum+5}">
									<div class="main_news_page_button main_news_page_bn">
										<div class="main_news_page_button_lg">&gt;</div>
									</div>
							</c:if>
							<c:if test="${startNum+4>=lastNum}">
								<a href="#" onclick="alert('다음 페이지가 없습니다.');">
									<div class="main_news_page_button main_news_page_bn">
										<div class="main_news_page_button_lg">&gt;</div>
									</div>
								</a>
							</c:if>
								<div class="main_news_page_button main_news_page_bn">
									<div class="main_news_page_news_llgg">&gt;&gt;</div>
								</div>
							</a>
<!--------------------------------------------------------------------------------------------------------------------->

						</div>
					</div>
				</div>
				<div style="display:none;" class="cancel_view">
					<div class="cancel_list">
						<div><h2>취소 내역이 없습니다.</h2></div>
					</div>
				</div>
				
			</div>
						
				<div class="cancel_notice">
					<h3>취소안내</h3>
					<ul>
						<li>예매상세에서 확인 및 취소를 진행하실 수 있습니다.</li>
						<li>배송이 시작된 경우 취소마감시간이전까지 티켓루팡 고객센터로 티켓을 반환해주셔야 환불이 가능하며, 도착일자 기준으로 취소 수수료가 부과됩니다.<br/>
							 &nbsp; (*단, 반환된 티켓의 배송료는 환불되지 않으며 일괄배송 상품의 경우 취소에 대한 자세한 문의는 고객센터로 문의해 주시기 바랍니다.)	
						</li>
					</ul>
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