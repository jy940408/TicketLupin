<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<link rel="stylesheet" href="../css/Notice_list.css">
		<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/Nav_event.js"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_all.js"></script>
		<script>
			function search(){
				
				frm2.action="../Customer/NoticeList.do";
				frm2.method="get";
				frm2.submit();
				return;
			}	
			
			function changeSchType(){
				
				frm.action="../Customer/NoticeList.do";
				frm.method="get";
				frm.submit();
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
				<div class="cont">
					<p class="tit">
						<span class="label">고객센터</span>
						<c:if test="${sessionScope.mgrade eq 'M'}">
							<span class="btn">
								<button type="button" class="btn1">
									<a href="../Customer/NoticeWrite.do">공지 작성하기</a>
								</button>
							</span>
						</c:if>
					</p>
					<div class="cont_tbl">
						<table class="table1" border="1">
							<tr>
								<td width="140px">
									<a href="../Customer/NoticeList.do" class="menu">공지사항</a>
								</td>
								<td width="140px">
									<a href="../Customer/Buyguide.do" class="menu">이용안내</a>
								</td>
								<td width="140px">
									<a href="../Customer/FaqList.do" class="menu">FAQ</a>
								</td>
								<td width="140px">
									<c:choose>
										<c:when test="${not empty sessionScope.mid && sessionScope.mgrade eq 'G'}">
											<a href="../Customer/QuestionList.do">나의 문의 내역</a>
										</c:when>
										<c:when test="${not empty sessionScope.mid && sessionScope.mgrade eq 'M'}">
											<a href="../Customer/AnswerMain.do">문의 관리</a>
										</c:when>
										<c:otherwise>
											<a href="../Member/MemberLogin.do">나의 문의 내역</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
					</div>
					<c:set var="searchType" value="${searchType}"/>
					<div class="box_customer">	
						<div class="box_select" style="padding:50px 0 20px;">
							<form name="frm" style="display:inline;">
							<select name="searchType" onchange="changeSchType();">
								<option value="" ${searchType == '' ? 'selected="selected"' : '' }>전체</option>
								<option value="서비스 소식" ${searchType == '서비스 소식' ? 'selected="selected"' : '' }>서비스 소식</option>
								<option value="서비스 오픈" ${searchType == '서비스 오픈' ? 'selected="selected"' : '' }>서비스 오픈</option>
								<option value="서비스 종료" ${searchType == '서비스 종료' ? 'selected="selected"' : '' }>서비스 종료</option>
								<option value="서비스 점검" ${searchType == '서비스 점검' ? 'selected="selected"' : '' }>서비스 점검</option>
								<option value="안내" ${searchType == '안내' ? 'selected="selected"' : '' }>안내</option>
							</select>
							</form>	
							<form name="frm2" style="display:inline;">
							<div style="display:inline; position:relative; left:550px;">
								<input type="text" name="keyword" style="width:220px;height:25px;" autocomplete="off">
								<span class="wrap_input">
									<button type="button" style="height:32px;width:80px; position:relative; top:2px;" onclick="search()">검색</button>
								</span>
							</div>	
							</form>						
						</div>
						<c:set var = "page" value = "${(param.p==null)? 1: param.p}"/>
	          			<c:set var ="startNum" value = "${page-(page-1)%5}"/> 
	           			<c:set var ="lastNum" value = "${fn:substringBefore(Math.ceil(count/10), '.')}"/>					
						<table class="table2" style="width:100%">
							<tbody>
								<tr>
									<th style="width:5%;">No.</th>
									<th style="width:10%;">분류</th>
									<th style="width:45%;">제목</th>
									<th style="width:10%;">등록일</th>
								</tr>
								<c:forEach var="list" items="${list}">
								<tr>
									<td style="width:10%;">${list.num}</td>
									<td style="width:20%;">${list.ncategory}</td>
									<td style="width:50%;" class="td_">
										<a href="../Customer/NoticeView.do?num=${list.num}&keyword=${keyword}&searchType=${searchType}">${list.ntitle}</a>
									</td>
									<td style="width:20%;">${list.nregdate}</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="paging">
							<div>
								<c:choose>
									<c:when test="${count > 0}">
										<c:if test="${startNum == 1}">
											<a href="?p=${startNum}&searchType=${param.searchType}&keyword=${param.keyword}">&lt;&lt;</a>
										</c:if>
										<c:if test="${startNum > 1}">
											<a href="?p=${startNum-1}&searchType=${param.searchType}&keyword=${param.keyword}">&lt;&lt;</a>
										</c:if>
										<c:if test="${page == 1}">
											<a href="?p=${startNum}&searchType=${param.searchType}&keyword=${param.keyword}">&lt</a>
										</c:if>
										<c:if test="${startNum < page && page < (startNum+5)}">
											<a href="?p=${page-1}&searchType=${param.searchType}&keyword=${param.keyword}">&lt</a>                
										</c:if>
										<c:if test="${page == startNum && startNum > 1}">
											<a href="?p=${startNum-1}&searchType=${param.searchType}&keyword=${param.keyword}">&lt</a>                
										</c:if>
										<span>
								   			<c:forEach var = "i" begin= "0" end = "4">
												<c:if test="${(startNum+i) <= lastNum }">
													<a style="color: ${(page==(startNum+i))?'red':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?p=${startNum+i}&searchType=${param.searchType}&keyword=${param.keyword}">${startNum+i}</a>
												</c:if>
											</c:forEach>
										</span>    
										<c:if test="${page < lastNum}">
											<a href="?p=${page+1}&searchType=${param.searchType}&keyword=${param.keyword}">&gt;</a>
										</c:if>
										<c:if test="${page == lastNum}">
											<a href="?p=${lastNum}&searchType=${param.searchType}&keyword=${param.keyword}">&gt;</a>
										</c:if>
										<c:if test="${(startNum+4) < lastNum}">
							               	<a href="?p=${startNum+5}&searchType=${param.searchType}&keyword=${param.keyword}">&gt;&gt;</a>
							           	</c:if>
										<c:if test="${(startNum+4) >= lastNum}">
											<a href="?p=${lastNum}&searchType=${param.searchType}&keyword=${param.keyword}">&gt;&gt;</a>
										</c:if>
									</c:when>
									<c:otherwise>
										<a href="?p=1&searchType=${param.searchType}&keyword=${param.keyword}">&lt;&lt;</a>
										<a href="?p=1&searchType=${param.searchType}&keyword=${param.keyword}">&lt;</a>
										<a style="color:red; font-weight:bold;" href="?p=1&searchType=${param.searchType}&keyword=${param.keyword}">1</a>
										<a href="?p=1&searchType=${param.searchType}&keyword=${param.keyword}">&gt;</a>
										<a href="?p=1&searchType=${param.searchType}&keyword=${param.keyword}">&gt;&gt;</a>
									</c:otherwise>
								</c:choose>
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