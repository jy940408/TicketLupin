<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/Admin_main.css">
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Myticket_main.js"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_event.js"></script>
	</head>
	<body>
		<header>
			<div id="h_title">
				<div id="h_title_inner">
					<span id="h_top_menu">
						<ul id="h_top_menu_ul">
						<c:if test="${not empty sessionScope.mid}">
							<li>${sessionScope.mid }님 환영합니다!&nbsp;&nbsp;&nbsp;&nbsp;</li>
							<li><a href="<%=request.getContextPath()%>/Member/Memberlogout.do">로그아웃&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
						<c:if test="${empty sessionScope.mid}">
							<li><a href="<%=request.getContextPath()%>/Member/MemberLogin.do">로그인&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">회원가입&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
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
				<a href="${pageContext.request.contextPath}/Main/MainPage.do" id="main_nav_home">홈</a>
				<a href="${pageContext.request.contextPath}/Show/ShowList.do" id="main_nav_concert">공연</a>
				<a href="${pageContext.request.contextPath}/Show/RankingList.do" id="main_nav_ranking">랭킹</a>
				<a href="${pageContext.request.contextPath}/News/NewsList.do" id="main_nav_news">티켓오픈소식</a>
				<a href="#" id="main_nav_event">이벤트</a>
				<c:choose>
					<c:when test="${sessionScope.mgrade eq 'M' }">
						<a href="${pageContext.request.contextPath}/Manager/Main.do" id="main_nav_myticket">관리자</a>
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
			</ul>
			<hr id="nav_bar_sub">
		</div>
		<div id="nav_menu_sub_myticket_div" class="main_nav_all">
			<ul id="nav_menu_sub_myticket" style="margin:0px;">
				<c:choose>
					<c:when test="${sessionScope.mgrade eq 'M' }">
						<li><a href="${pageContext.request.contextPath}/Manager/MemberList.do">회원관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/Manager/ConcertList.do">공연관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/Manager/comment.do">댓글관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/Customer/AnswerMain.do">문의관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${not empty sessionScope.mid}">
								<li><a href="${pageContext.request.contextPath}/Myticket/MyticketMain.do">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a href="${pageContext.request.contextPath}/Myticket/MyticketReservation.do">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a href="${pageContext.request.contextPath}/Dibs/MyDibs.do">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							</c:when>
							<c:otherwise>
								<li><a onclick="loginAlert()">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a onclick="loginAlert()">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a onclick="loginAlert()">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		
<!------------------------------------------------------------------------------------------------------------------------------------->

		<section>
			<article>
				<div id="main_myticket_all">
					<div id="main_myticket_set">
						<div id="main_myticket_member" class="main_myticket_member_set">
							<img src="../icon/person.png" id="main_myticket_picture" class="main_myticket_member_all">
							<div id="main_myticket_id_set" class="main_myticket_member_all">
								<div id="main_myticket_id">${name}님 환영합니다.</div><br>
								<a href="<%=request.getContextPath() %>/Member/MemberModifyForm.do?" class="main_myticket_button"><div class="main_myticekt_button_div">기본정보 관리</div></a>
							</div>
						</div>
						<div id="main_myticket_amount" class="main_myticket_member_set">
							<a href="#" id="main_myticket_amount_number2_set">
								<div class="main_myticket_amount_number">0</div><br>
								<div>신고 내역</div>
							</a>
							<a href="" id="main_myticket_amount_number3_set">
								<div class="main_myticket_amount_number"> ${count}</div><br>
								<div>미답변 문의내역</div>
							</a>
						</div>
					</div>
					<div id="main_myticket_bottom">
						<div id="main_myticket_event">
							<div class="main_myticket_sub_title_set">
								<div class="main_myticket_title_set">회원 내역</div>
								<a href="<%=request.getContextPath() %>/Manager/MemberList.do" class="main_myticket_more_set" >더보기 ></a>
								<div id="main_myticket_event_list"class="divoverflow">
									<table>
										<c:if test="${empty mlist}">
											<td class="null"><h3>회원 내역을 찾을 수 없습니다.</h3></td>
										</c:if>
										<c:if test="${!empty mlist}">
											<tr><th width="35">no</th><th width="90">아이디</th><th width="90">성함</th><th width="90">메일</th><th width="50" >가입일</th></tr>
											<c:forEach var="cc" items="${mlist}">
												<tr style="cursor:pointer;" class="trtr" onclick="window.open('<%=request.getContextPath() %>/Manager/Memberinfo.do?midx=${cc.midx}','회원정보','width=490, height=380, menubar=no, status=no, toolbar=no, scrollbars=no');">
													<td><div class="ellipsis">${cc.no}</div></td>
													<td><div class="ellipsis">${cc.mid}</div></td>
													<td><div class="ellipsis">${cc.mname}</div></td>
													<td><div class="ellipsis">${cc.memail}</div></td>
													<td><div class="ellipsis">${cc.c_date}</div></td>
												</tr>
											</c:forEach>
										</c:if>
									</table>			
								</div>
							</div>
						</div>
						<div id="main_myticket_question">
							<div class="main_myticket_title_set">공연 결제 내역</div>
							<a href="<%=request.getContextPath() %>/Manager/ConcertList.do" class="main_myticket_more_set">더보기 ></a>
							<div id="main_myticket_question_list"class="divoverflow">
								<table>
									<c:if test="${empty plist}">
										<td class="null"><h3>결제 내역을 찾을 수 없습니다.</h3></td>
									</c:if>
									<c:if test="${!empty plist}">
										<tr><th width="35" >no</th><th width="70">아이디</th><th width="130">공연명</th><th width="80">결제금액</th><th width="50">결제일</th></tr>
										<c:forEach var="a" items="${plist}">
											<tr style="cursor:pointer;" class="trtr" onClick="location.href='링크주소'">
												<td><div class="ellipsis">${a.no}</div></td>	
												<td><div class="ellipsis">${a.mid}</div></td>
												<td><div class="ellipsis">${a.stitle}</div></td>
												<td><div class="ellipsis">${a.ripayment}원</div></td>
												<td><div class="ellipsis">${a.c_date}</div></td>
											<tr>
										</c:forEach>
									</c:if>
								</table>					
							</div>
						</div>
					<div id="main_myticket_bottom">
						<div id="main_myticket_event">
							<div class="main_myticket_sub_title_set">
								<div class="main_myticket_title_set">댓글 내역</div>
								<a href="<%=request.getContextPath() %>/Manager/comment.do" class="main_myticket_more_set">더보기 ></a>
								<div id="main_myticket_event_list" class="divoverflow">
									<table>
										<c:if test="${empty clist}">
											<td class="null"><h3>댓글 내역을 찾을 수 없습니다.</h3></td>
										</c:if>
										<c:if test="${!empty clist}">
											<tr><th width="35">no</th><th width="285">댓글 내용</th><th width="50">작성일</th></tr>
											<c:forEach var="cc" items="${clist}">
												<tr style="cursor:pointer;" class="trtr" onclick="window.open('<%=request.getContextPath() %>/Manager/CommentView.do?c_idx=${cc.c_idx}','회원정보','width=630, height=600, menubar=no, status=no, toolbar=no, scrollbars=no');">
													<td><div class="ellipsis">${cc.no}</div></td>
													<td><div class="ellipsis">${cc.c_content}</div></td>
													<td><div class="ellipsis">${cc.c_date}</div></td>
												<tr>
											</c:forEach>
										</c:if>	
									</table>		
								</div>
							</div>
						</div>
						<div id="main_myticket_question">
							<div class="main_myticket_title_set">문의 내역</div>
							<a href="<%=request.getContextPath() %>/Customer/AnswerMain.do" class="main_myticket_more_set">더보기 ></a>
							<div id="main_myticket_question_list"class="divoverflow">
								<table>
									<c:if test="${empty qlist}">
										<td class="null"><h3>문의 내역을 찾을 수 없습니다.</h3></td>
									</c:if>
									<c:if test="${!empty qlist}">
									<tr><th width="35">no</th><th width="230">문의 제목</th><th width="55">상태</th><th width="50">작성일</th></tr>
									<c:forEach var="cc" items="${qlist}">
										<tr style="cursor:pointer;" class="trtr" onClick="location.href='링크주소'">
											<td><div class="ellipsis">${cc.no}</div></td>
											<td><div class="ellipsis">${cc.qtitle}</div></td>
											<td>미답변</td>
											<td><div class="ellipsis">${cc.q_date}</div></td>
										<tr>	
									</c:forEach>
									</c:if>
								</table>
							</div>
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
						<span class="f_bottom_tagset">예매문의(1244-1244)</span>
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
						<li><a href="#">고객센터(평일/주말 09:00~18:00): 1244-1244(유료)</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">호스팅제공자: (주)티켓루팡</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">&copy;TicketLupin Corp. All rights reserved.</a>&nbsp;&nbsp;&nbsp;&nbsp;</li><br>
					</ul>
				</span>
			</div>
		</footer>
	</body>
</html>
