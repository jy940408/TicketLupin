<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
		<script src="../js/Faq_list.js"></script>
		<link rel="stylesheet" href="../css/Faq_list.css">
		<script src="<%=request.getContextPath()%>/js/Nav_event.js"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_all.js"></script>
		<script>
			function search(){
				
				document.frm.action = "../Customer/FaqList.do";
				document.frm.method = "get";
				document.frm.submit();
				return;
			}
			
			function faqDelete(e){
				
				$(document).ready(function(){
					
					var fidx = $(e).prev().prev().val();
					
					var conf = confirm("삭제하시겠습니까?");
					
					if(conf == true){
											
						location.href = "../Customer/FaqDeleteAction.do?fidx="+fidx;
						
					}else{
						
					}
				});
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
					<div class="tit">
						<span class="label">고객센터</span>
						<c:if test="${sessionScope.mgrade eq 'M'}">
							<span class="btn">
								<button type="button" class="qwrite" onclick="location.href='../Customer/FaqWrite.do'">FAQ 작성하기</button>
							</span>	
						</c:if>							
					</div>
					<div class="cont_tb1">
						<table border="1"  class="table1">
							<tr>
								<td width="140px"><a href="../Customer/NoticeList.do">공지사항</a></td>
								<td width="140px"><a href="../Customer/Buyguide.do">이용안내</a></td>
								<td width="140px"><a href="../Customer/FaqList.do">FAQ</a></td>
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
					<form name="frm">
					<div class="sch" style="display:block">
						<label>자주하는 질문 검색</label>
						<span class="wrap_input">
							<input type="text" name="keyword" maxlength="100" class="inputTypefaq" autocomplete="off">
						</span>
						<span class="green">
							<button type="button" class="schbtn" onclick="search();">검색</button>
						</span>
					</div>
					</form>
					<div class="categoryBox">
						<table border="1" class="mid">
							<tr>
								<td width="168"><a href="../Customer/FaqList.do?p=1&type=">전체</a></td>
								<td width="168"><a href="../Customer/FaqList.do?p=1&type=티켓예매">티켓예매</a></td>
								<td width="168"><a href="../Customer/FaqList.do?p=1&type=취소/환불">취소/환불</a></td>
								<td width="168"><a href="../Customer/FaqList.do?p=1&type=티켓수령">티켓수령</a></td>
								<td width="168"><a href="../Customer/FaqList.do?p=1&type=회원">회원</a></td>
								<td width="168"><a href="../Customer/FaqList.do?p=1&type=이벤트">이벤트</a></td>
							</tr>
						</table>
					</div>
					<c:set var = "page" value = "${(param.p==null)? 1: param.p}"/>
          			<c:set var ="startNum" value = "${page-(page-1)%5}"/>    
           			<c:set var ="lastNum" value = "${fn:substringBefore(Math.ceil(count/10), '.')}"/>
					<div class="box_customer">
						<table class="tb2" style="width:100%">
							<caption class="hide"></caption>
							<colgroup>
								<col style="width:56px">
								<col style="width:82px">
								<col style="width:600px">
							</colgroup>
							<tbody class="listBox">
								<c:forEach var="list" items="${list}">
									<tr>
										<td>${list.num}</td>
										<td name="type">${list.ftype}</td>
										<td class="txt_left title" style="cursor:pointer">${list.ftitle}</td>
									</tr>
									<tr class="comment color" style="display:none">
										<td class="td_div" style="padding:0px;">
											<div>└<div>
										</td>
										<td class="txt_left" colspan="2" >
											<pre style="font-size:14px; margin-bottom:16px;">${list.fcontent}</pre>
											<c:if test="${sessionScope.mgrade eq 'M'}">
												<p style="text-align:right; margin:20px;">
													<input type="text" name="fidx" value="${list.fidx}" style="display:none;">
													<button type="button" style="margin-right:10px;" onclick="location.href='../Customer/FaqModify.do?fidx=${list.fidx}'">수정하기</button>
													<button type="button" onclick="faqDelete(this); return false;">삭제하기</button>
												</p>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="paging">
							<div>
								<c:choose>
									<c:when test="${count > 0}">
										<c:if test="${startNum == 1}">
											<a href="?p=${startNum}&type=${type}&keyword=${keyword}">&lt;&lt;</a>
										</c:if>
										<c:if test="${startNum > 1}">
											<a href="?p=${startNum-1}&type=${type}&keyword=${keyword}">&lt;&lt;</a>
										</c:if>
										<c:if test="${page == 1}">
											<a href="?p=${startNum}&type=${type}&keyword=${keyword}">&lt</a>
										</c:if>
										<c:if test="${startNum < page && page < (startNum+5)}">
											<a href="?p=${page-1}&type=${type}&keyword=${keyword}">&lt</a>                
										</c:if>
										<c:if test="${page == startNum && startNum > 1}">
											<a href="?p=${startNum-1}&type=${type}&keyword=${keyword}">&lt</a>                
										</c:if>
										<span>
								   			<c:forEach var = "i" begin= "0" end = "4">
												<c:if test="${(startNum+i) <= lastNum }">
													<a style="color: ${(page==(startNum+i))?'red':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?p=${startNum+i}&type=${type}&keyword=${keyword}">${startNum+i}</a>
												</c:if>
											</c:forEach>
										</span>    
										<c:if test="${page < lastNum}">
											<a href="?p=${page+1}&type=${type}&keyword=${keyword}">&gt;</a>
										</c:if>
										<c:if test="${page == lastNum}">
											<a href="?p=${lastNum}&type=${type}&keyword=${keyword}">&gt;</a>
										</c:if>
										<c:if test="${(startNum+4) < lastNum}">
							               	<a href="?p=${startNum+5}&type=${type}&keyword=${keyword}">&gt;&gt;</a>
							           	</c:if>
										<c:if test="${(startNum+4) >= lastNum}">
											<a href="?p=${lastNum}&type=${type}&keyword=${keyword}">&gt;&gt;</a>
										</c:if>
									</c:when>
									<c:otherwise>
										<a href="?p=1&type=${type}&keyword=${keyword}">&lt;&lt;</a>
										<a href="?p=1&type=${type}&keyword=${keyword}">&lt;</a>
										<a style="color:red; font-weight:bold;" href="?p=1&type=${type}&keyword=${keyword}">1</a>
										<a href="?p=1&type=${type}&keyword=${keyword}">&gt;</a>
										<a href="?p=1&type=${type}&keyword=${keyword}">&gt;&gt;</a>
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