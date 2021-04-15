<%@page import="domain.PageMaker"%>
<%@page import="com.TicketLupin.web.service.Show1Vo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.TicketLupin.web.service.ShowDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_event.js"></script>
		<script src="<%=request.getContextPath() %>/js/Ranking_list.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Ranking_list.css">
		<script>
			$(document).ready(function(){
				var order = "";
				$("input:radio[name='order']").on("change", function(){
					var order = "";
					if($(".order:checked").val() == "now"){
						order = "now";
					}else if($(".order:checked").val() == "week"){
						order = "week";
					}else if($(".order:checked").val() == "month"){
						order = "month";
					}
					
					$.ajax({
						type:"get",
						url:"${pageContext.request.contextPath}/Show/RankingListAJAX.do",
						data:{"order": order},
						success: function(data){
							
							var output = "";
							if(jQuery.isEmptyObject(data) == false){
								for(var i = 0 ; i < data.length ; i++){
									
									output += "<tr>";
									output += "	<td class='num' style='font-weight: bold;'>" + (i+1) + "</td>";
									output += "	<td class='td_'>";
									output += "		<div class='show_info'>";
									output += "			<span class='show_poster'>";
									output += "				<a href='${pageContext.request.contextPath}/ConcertView/ConcertView.do?sidx=" + data[i].sidx + "'>";
									output += "				<img src='${pageContext.request.contextPath}/poster/" + data[i].sstitleimage + "' class='rank_poster'>";
									output += "				</a>";
									output += "			</span>";
									output += "			<span class='show_title'>";
									output += "				<a href='#'>" + data[i].stitle +"</a>";
									output += "			</span>";
									output += "		</div>";
									output += "	</td>";
									output += "	<td>" + data[i].sopendate + "-" + data[i].senddate + "</td>";
									output += "	<td>" + data[i].ssdetailaddress + "</td>";
									output += "	<td>";
									output += "		<button type='button' class='btn_rank'>예매하기</button>";
									output += "	</td>";
									output += "</tr>";
									
								}
								
								$("#tablebody").html(output);
								
								if(order == "now"){
									$("#rankingOrderTitle").contents()[0].textContent="실시간 랭킹     ▼";
								}else if(order == "week"){
									$("#rankingOrderTitle").contents()[0].textContent="주간 랭킹    ▼";
								}else if(order == "month"){
									$("#rankingOrderTitle").contents()[0].textContent="월간 랭킹    ▼";
								}
								
							}else{
								alert("현재 순위가 제공되지 않고 있습니다");
								return;
							}
						},
						error:function(request,status,error){
					        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					    }

					});
				});
				
			});
			
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
		
		<section>
			<article>
				<div class="wrap_ranking">
					<div class="wrap_rank_date">
						<div id="wrap_select_drop_on">
							<div class="tit_rank_date">
								<button type="button" value="realtime" onclick="nowranking()" id="rankingOrderTitle" class="rankbtn">
									실시간 랭킹     ▼
								</button>
							</div>
							<ul class="select_list" id="ul_list" style="display:none">
								<li><label for="now"><input type="radio" name="order" id="now" class="order" value="now">실시간</label></li>
								<li><label for="week"><input type="radio" name="order" class="order" id="week" value="week">주간</label></li>
								<li><label for="month"><input type="radio" name="order" class="order" id="month" value="month">월간</label></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="ranking_list">
					<table class="top10">
						<thead>
							<tr>
								<th width="138">랭킹</th>
								<th width="424">공연명</th>
								<th width="128">공연일시</th>
								<th width="168">공연장소</th>
								<th width="150">자세히 보기</th>
							</tr>
						</thead>
						<tbody id="tablebody">
							<c:forEach var="l" items="${list}" varStatus="status" begin="0" end="10" step="1">
							<tr>
								<td class="num" style="font-weight: bold;">${status.count }</td>
								<td class="td_">
									<div class="show_info">
										<span class="show_poster">
											<a href="${pageContext.request.contextPath}/ConcertView/ConcertView.do?sidx=${l.sidx}">
												<img src="${pageContext.request.contextPath}/poster/${l.stitleimage }" class="rank_poster">
											</a>
										</span>
										<span class="show_title">
											<a href="${pageContext.request.contextPath}/ConcertView/ConcertView.do?sidx=${l.sidx}">${l.stitle}</a>
										</span>
									</div>
								</td>
								<td>${l.sopendate} - ${l.senddate}</td>
								<td>${l.sdetailaddress}</td>
								<td>
									<button type="button" class="btn_rank" onclick="location.href='${pageContext.request.contextPath}/ConcertView/ConcertView.do?sidx=${l.sidx}'">보러가기</button>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
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