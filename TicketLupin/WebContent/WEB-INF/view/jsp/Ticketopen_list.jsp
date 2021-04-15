<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/slick-1.8.1/slick-1.8.1/slick/slick.css" />
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/js/slick-1.8.1/slick-1.8.1/slick/slick.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_all.js"></script>
		<script src="<%=request.getContextPath() %>/js/Ticketopen_list.js"></script>
		<script src="${pageContext.request.contextPath}/js/loginAlert.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Ticketopen_list.css">
		
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
		
		
		<section>
			<article>
				<div style="padding:30px;"></div>
				<div class="ticketopen">
					<div class="hot">
						<p class="tit">
							<span class="hot_tit"><strong>HOT 공연 오픈 소식</strong></span>
							<span class="hot_paging" style="display:block;" id="hot_paging">
								<a href="#" class="prev"> < </a> &nbsp;&nbsp; 1 &nbsp; / &nbsp; 2 &nbsp;&nbsp; <a href="#" class="next" onclick="hot()"> > </a>
							</span>
						</p>
						<div class="slider">
							
							
							<span class="slider_1">
								<ul class="list_slider">
								<c:forEach var="l" items="${posterList}">
									<li class="li">
										<a href="<%=request.getContextPath()%>/News/NewsDetail.do?widx=${l.widx}">
											<img src="<%=request.getContextPath()%>/poster/${l.wtitleposter}" class="posimg">
										</a>
									</li>
								</c:forEach>
								</ul>
							</span>
							<div class="slick_btns">
								<span class="radio_input">
									<input type="radio" name="dots" value="prev" class="prev" checked="checked"/>
									<input type="radio" name="dots" value="next" class="next"/>
								</span>
							</div>
						</div>
						
					</div>
				</div>
				<c:if test="${sessionScope.mgrade eq 'M'}">
				<div class="btn1">
					<button type="button" class="write" id="Ticketopen_write" onclick="location.href='<%=request.getContextPath()%>/News/NewsWrite.do'">작성하기</button>
				</div>
				</c:if>
				<c:if test="${sessionScope.mgrade eq 'G'}">
				</div>
				</c:if>
				<c:if test="${empty sessionScope.mgrade}">
				</c:if>
				<div class="subject">
					
					<div class="box_select">
					<form action="<%=request.getContextPath()%>/News/NewsList.do" method="get">
						<select name="s">
							<option value="total" selected>전체</option>
							<option value="open">티켓오픈일</option>
							<option value="modify">변경</option>
							<option value="cancel">취소</option>
						</select>
						<input type="text" name="q" placeholder="티켓오픈소식 검색" style="height:27px;">
						<span>
							<button type="submit" class="btn2">검색</button>
						</span>
					</form>
						<div class="menu">
							<ul>
								<li><a href="?q=&o=wregdate&p=">등록순</a> &nbsp;&nbsp; |</li>
								<li>&nbsp;&nbsp;<a href="?q=&o=wopendate&p=">오픈일순</a> &nbsp;&nbsp; |</li>
								<li>&nbsp;&nbsp;<a href="?q=&o=whit&p=">조회순</a> </li>
							</ul>
						</div>
					</div>
					<div class="openlist">
						<table class="table1" width="100%">
							<c:forEach var="l" items="${list}">
							<tr>
								<td width="20%">
									<c:if test="${l.wcategory eq '티켓오픈일'}">
										<strong>
										${l.wcategory}
										</strong><p/>
										<fmt:formatDate value="${l.wopendate}" type="both" pattern="YYYY.MM.dd(E) HH:ss"/>
									</c:if>
									<c:if test="${l.wcategory eq '변경'}">
										<strong>
										${l.wcategory}
										</strong><p/>
									</c:if>
									<c:if test="${l.wcategory eq '취소'}">
										<strong>
										${l.wcategory}
										</strong><p/>
									</c:if>
								</td width="70%">
								<td>
									<span class="span1"><a href="<%=request.getContextPath()%>/News/NewsDetail.do?widx=${l.widx}"> ${l.wtitle} </a></span><p/>
									<span class="span2">등록일 ${l.wregdate}</span>
								</td>
								<td width="10%" >
									<a href="#"><img src="<%=request.getContextPath()%>/poster/${l.wtitleposter}" class="img"></a>
								</td>
							</tr>
							</c:forEach>
						</table>
<!--------------------------------------------------------------------------------------------------------------------->
		
		<c:set var="page" value="${(param.p == null)?1:param.p}"/>
		<c:set var="startNum" value="${page-(page-1)%5}"/>
		<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"/>

<!--------------------------------------------------------------------------------------------------------------------->
					
					<div id="main_news_page">
						<div id="main_news_page_set">
<!--------------------------------------------------------------------------------------------------------------------->
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
											<a style="color: ${(page==(startNum+i))?'red':'black'}; font-weight:${(page==(startNum+i))?'bold':''};" href="?p=${startNum+i}&s=${param.s}&q=${param.q}" >${startNum+i}</a>
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
								</a>
							</c:if>
							<c:if test="${startNum+4>=lastNum}">
								<a href="#" onclick="alert('다음 페이지가 없습니다.');">
									<div class="main_news_page_button main_news_page_bn">
										<div class="main_news_page_button_lg">&gt;</div>
									</div>
								</a>
							</c:if>
<!--------------------------------------------------------------------------------------------------------------------->

						</div>
					</div>
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