<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<link rel="stylesheet" type"text/css" href="<%=request.getContextPath() %>/css/Myticket_main.css">
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Myticket_main.js"></script>
		<script>
			function memberModify(){
				
				document.frm.action = "../Member/MemberModifyForm.do";
				document.frm.method = "post";
				document.frm.submit();
				return;
			}
		</script>
	</head>
	<body>
		<header>
			<div id="h_title">
				<div id="h_title_inner">
					<span id="h_top_menu">
						<ul id="h_top_menu_ul">
						<c:if test="${not empty sessionScope.mid}">
							<li><a href="${pageContext.request.contextPath}/Member/Member_Modify_PwdCheck.do?mid=${sessionScope.mid}">${sessionScope.mid }님 환영합니다!</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							<li><a href="${pageContext.request.contextPath}/Member/Memberlogout.do">로그아웃&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
						<c:if test="${empty sessionScope.mid}">
							<li class="login"><a href="${pageContext.request.contextPath}/Member/MemberLogin.do">로그인&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="${pageContext.request.contextPath}/Member/MemberJoin.do">회원가입&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
							<li><a href="${pageContext.request.contextPath}/Customer/NoticeList.do">고객센터&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">이용안내&nbsp;&nbsp;&nbsp;&nbsp;</a></li><br/>
						</ul>
						<img src="../ads/musicalads.png" id="h_ads">
					</span>
					<a href=""${pageContext.request.contextPath}/Main/MainPage.do"><img src="../icon/lupinlogo.png" id="h_logo"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<form action="${pageContext.request.contextPath}/Show/ShowList.do" method="get" style="display:inline-block;">
						<input type="text" id="h_search" name="q" placeholder="뮤지컬 〈캣츠〉 40주년 내한공연 앙코르－서울（Musical CATS Encore">
						<button type="submit" id="h_search_button"><img src="../icon/search.png" id="h_search_img"></button>
					</form>
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
				<a href="${pageContext.request.contextPath}/Event/EventMain.do" id="main_nav_event">이벤트</a>
				<c:choose>
					<c:when test="${sessionScope.mgrade eq 'M' }">
						<a href="${pageContext.request.contextPath}/Manager/Main.do" id="main_nav_myticket">관리자</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/Myticket/MyticketMain.do" id="main_nav_myticket">마이 티켓</a>
					</c:otherwise>
				</c:choose>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		<div id="nav_menu_sub_event_div" class="main_nav_all">
			<ul id="nav_menu_sub_event" style="margin:0px;">
				<li><a href="${pageContext.request.contextPath}/Event/EventMain.do">전체 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="${pageContext.request.contextPath}/Winner/WinnerList.do">당첨자 발표</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
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
								<div id="main_myticket_id">${mv.mname}</div><br>
								<form name="frm">
									<a href="javascript:" onclick="memberModify();" class="main_myticket_button"><div class="main_myticekt_button_div">기본정보 관리</div></a>
								</form>
							</div>
						</div>
						<div id="main_myticket_amount" class="main_myticket_member_set">
							<a href="../Myticket/MyticketReservation.do" id="main_myticket_amount_number1_set">
								<div>
									<div class="main_myticket_amount_number">${count}</div><br>
									<div>예매내역</div>
								</div>
							</a>
						</div>			
					</div>
					<div id="main_myticket_ticketing" style="margin-bottom:50px;">
						<a href="../Myticket/MyticketReservation.do" class="main_myticket_title_set">최근 예매/취소</a>
						<a href="../Myticket/MyticketReservation.do" class="main_myticket_more_set" style="font-size:14px;">더보기 ></a>
						<table class="tb1" style="width:100%; margin-top:20px; table-layout: fixed;">
							<colgroup>
								<col width="60%">
								<col width="20%">
								<col width="20%">
							</colgroup>
							<tr>
								<th style="border-top:1px solid #dbdbdb; border-bottom:1px solid #dbdbdb; padding:10px;">공연 정보</th>
								<th style="border-top:1px solid #dbdbdb; border-bottom:1px solid #dbdbdb; padding:10px;">관람일</th>
								<th style="border-top:1px solid #dbdbdb; border-bottom:1px solid #dbdbdb; padding:10px;">예매일</th>
							</tr>
							<c:forEach var="rlist" items="${rlist}" begin="0" end="4" step="1">
								<tr>
									<td style="border-bottom:1px solid #dbdbdb; padding:10px; overflow:hidden; white-space : nowrap; text-overflow: ellipsis;">&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/Myticket/MyticketDetail.do?riidx=${rlist.riidx}" onclick="window.open(this.href,'popup','width=955px, height=654px'); return false;">${rlist.stitle}</a></td>
									<td style="text-align:center; border-bottom:1px solid #dbdbdb; padding:10px;">${rlist.srdate}&nbsp;&nbsp;/&nbsp;&nbsp;${rlist.srround}</td>
									<td style="text-align:center; border-bottom:1px solid #dbdbdb; padding:10px;">${rlist.rregdate}</td>
								</tr>
							</c:forEach>
							<c:if test="${count eq 0}">
								<tr>
									<td colspan="3" style="text-align:center; border-bottom:1px solid #dbdbdb; padding:10px;">예매내역이 없습니다.</td>
								</tr>
							</c:if>
						</table>
					</div>
					<div id="main_myticket_ticketing" style="margin-bottom:50px;">
						<a href="../Customer/QuestionList.do" class="main_myticket_title_set">최근 1:1문의</a>
						<a href="../Customer/QuestionList.do" class="main_myticket_more_set" style="font-size:14px;">더보기 ></a>
						<table class="tb2" style="width:100%; margin-top:20px; table-layout: fixed;">
							<colgroup>
								<col width="10%">
								<col width="70%">
								<col width="20%">
							</colgroup>
							<tr>
								<th style="border-top:1px solid #dbdbdb; border-bottom:1px solid #dbdbdb; padding:10px;">답변 여부</th>
								<th style="border-top:1px solid #dbdbdb; border-bottom:1px solid #dbdbdb; padding:10px;">문의 정보</th>
								<th style="border-top:1px solid #dbdbdb; border-bottom:1px solid #dbdbdb; padding:10px;">작성일</th>
							</tr>
							<c:forEach var="qlist" items="${qlist}" begin="0" end="4" step="1">
								<tr>
									<td style="text-align:center; border-bottom:1px solid #dbdbdb; padding:10px;">${qlist.qstate}</td>
									<td style="border-bottom:1px solid #dbdbdb; padding:10px; overflow:hidden; white-space : nowrap; text-overflow: ellipsis;">
										<c:choose>
											<c:when test="${qlist.qstate == '대기'}">
												<a href="../Customer/QuestionView.do?num=${qlist.num}&qidx=${qlist.qidx}">&nbsp;&nbsp;&nbsp;${qlist.qtitle}</a>
											</c:when>
											<c:otherwise>
												<a href="../Customer/QuestionView2.do?num=${qlist.num}&qidx=${qlist.qidx}">&nbsp;&nbsp;&nbsp;${qlist.qtitle}</a>									
											</c:otherwise>
										</c:choose>
									</td>
									<td style="text-align:center; border-bottom:1px solid #dbdbdb; padding:10px;">${qlist.qregdate}</td>
								</tr>
							</c:forEach>
							<c:if test="${empty qlist}">
								<tr>
									<td colspan="3" style="text-align:center; border-bottom:1px solid #dbdbdb; padding:10px;">문의내역이 없습니다.</td>
								</tr>
							</c:if>
						</table>		
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
