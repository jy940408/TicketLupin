<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<link rel="stylesheet" href="../css/Concert_list.css">
		<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
		<script src="../js/Concert_list.js"></script>
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
						<li><a href="#">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="#">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/Dibs/MyDibs.do">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="#">할인쿠폰</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					</c:otherwise>
				</c:choose>
			</ul>
			<hr id="nav_bar_sub">
		</div>
<!------------------------------------------------------------------------------------------------------------------------>
		<section>
			<article>
				<div id="main_concert_list_all">
					<div id="main_concert_list_search_all">
						<div id="main_concert_list_search_top_all">
							<div id="main_concert_list_search_textbox" class="main_concert_list_search_top">
								<form action="#">
								<input type="text" name="q" placeholder="검색어를 입력하세요">
								<button type="submit">검색</button>
								</form>
							</div>
							<c:if test="${sessionScope.mgrade eq 'M'}">
							<div id="main_concert_list_search_manage_write" class="main_concert_list_search_top">
								<a href="<%=request.getContextPath()%>/Show/ShowWriteStep1.do"><div>작성하기</div></a>
							</div>
							</c:if>
							<c:if test="${sessionScope.mgrade eq 'G'}">
							<div id="main_concert_list_search_manage_write" class="main_concert_list_search_top">
							</div>
							</c:if>
							<c:if test="${empty sessionScope.mgrade}">
							<div id="main_concert_list_search_manage_write" class="main_concert_list_search_top">
							</div>
							</c:if>
						</div>
						<div id="main_concert_list_search_tag">
							<div id="main_concert_list_search_tag_genre" class="main_concert_list_search_tag_sector">
								<div>
									장르별
								</div>
								<ul>
									<li><input type="checkbox" name="genre" value="genreall" onclick="getCheckboxValue('genre')"><span id="genreall">전체</span></li>
									<li><input type="checkbox" name="genre" value="original" onclick="getCheckboxValue('genre')"><span id="original">오리지널/내한공연</span></li>
									<li><input type="checkbox" name="genre" value="license" onclick="getCheckboxValue('genre')"><span id="license">라이선스</span></li>
									<li><input type="checkbox" name="genre" value="creation" onclick="getCheckboxValue('genre')"><span id="creation">창작뮤지컬</span></li>
									<li><input type="checkbox" name="genre" value="nonverbal" onclick="getCheckboxValue('genre')"><span id="nonverbal">넌버벌 퍼포먼스</span></li>
									<li><input type="checkbox" name="genre" value="package" onclick="getCheckboxValue('genre')"><span id="package">패키지공연</span></li>
								</ul>
							</div>
							<div id="main_concert_list_search_tag_place" class="main_concert_list_search_tag_sector">
								<div>
									지역별
								</div>
								<ul>
									<li><input type="checkbox" name="place" value="placeall" onclick="getCheckboxValue('place')"><span id="placeall">전체</span></li>
									<li><input type="checkbox" name="place" value="seoul" onclick="getCheckboxValue('place')"><span id="seoul">서울</span></li>
									<li><input type="checkbox" name="place" value="incheon" onclick="getCheckboxValue('place')"><span id="incheon">경기/인천</span></li>
									<li><input type="checkbox" name="place" value="daejeon" onclick="getCheckboxValue('place')"><span id="daejeon">대전/충청/강원</span></li>
									<li><input type="checkbox" name="place" value="busan" onclick="getCheckboxValue('place')"><span id="busan">부산/대구/경상</span></li>
									<li><input type="checkbox" name="place" value="gwangju" onclick="getCheckboxValue('place')"><span id="gwangju">광주/전라/제주</span></li>
								</ul>
							</div>
							<div id="main_concert_list_search_tag_sold" class="main_concert_list_search_tag_sector">
								<div>
									판매상태
								</div>
								<ul>
									<li><input type="checkbox" name="sold" value="soldall" onclick="getCheckboxValue('sold')"><span id="soldall">전체</span></li>
									<li><input type="checkbox" name="sold" value="now" onclick="getCheckboxValue('sold')"><span id="now">판매 중</span></li>
									<li><input type="checkbox" name="sold" value="soon" onclick="getCheckboxValue('sold')"><span id="soon">판매 예정</span></li>
								</ul>
							</div>
						</div>
						<div id="main_concert_list_search_tag_checked">
							
						</div>
					</div>
					<div id="main_concert_musical_list_all">
						<div id="main_concert_musical_list_order">
							<a href="?q=&s=shit&=p="><div>인기순</div></a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a href="?q=&s=sopendate&p="><div>공연임박순</div></a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a href="?q=&s=sregdate&p="><div>최신순</div></a>
						</div>
<!------------------------------------------------------------------------------------------------------------------------>
					<c:set var="now_" value="<%=new java.util.Date()%>" />
					<fmt:formatDate value="${now_}" pattern="YYYY.MM.dd(E) HH:ss" var="now"/>
<!------------------------------------------------------------------------------------------------------------------------>
						<div id="main_concert_musical_list">
							<ul>
								<c:forEach var="l" items="${list}">
								<li><a href="<%=request.getContextPath()%>/ConcertView/ConcertView.do?sidx=${l.sidx}">
									<div class="main_concert_musical_detail">
										<img src="<%=request.getContextPath() %>/poster/${l.simage }" class="main_concert_musical_detail_poster">
										<div class="main_concert_musical_detail_title">
											${l.stitle}
										</div>
										<div class="main_concert_musical_detail_date">
											${l.sopendate } - ${l.senddate}
										</div>
										<div class="main_concert_musical_detail_place">
											${l.sroadaddress}
										</div>
										<div class="main_concert_musical_detail_sold">
											판매 중${now}
										</div>
									</div>
								</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				
<!--------------------------------------------------------------------------------------------------------------------->
		
			<c:set var="page" value="${(param.p == null)?1:param.p}"/>
			<c:set var="startNum" value="${page-(page-1)%5}"/>
			<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/12),'.')}"/>

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
								<a href="?p=${startNum-1}&s=&q=">
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
											<a style="color: ${(page==(startNum+i))?'red':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?p=${startNum+i}&s=${param.s}&q=${param.q}" >${startNum+i}</a>
										</div>
									</c:if>
								</c:forEach>
							</div>
<!--------------------------------------------------------------------------------------------------------------------->
							<c:if test="${startNum+4<lastNum}">
								<a href="?p=${startNum+5}&s=&q=">
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
			</article>
		</section>
<!------------------------------------------------------------------------------------------------------------------------>
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