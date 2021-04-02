<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.*"%>
<%@ page import="domain.*"%>
<%@ page import="com.TicketLupin.web.service.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%                                                  
	ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist");
	PageMaker pm = (PageMaker)request.getAttribute("pm");
	MemberDao md = new MemberDao();
%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_event.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Admin_user_list.css">
		<script>
			$(document).ready(function(){
				/* var mname = $('.mname').find('input').val();
				var midx = $('.midx').val();
				 */
				/* $(".mname").click(function(){
					var text = $(this).text();
					var phone = $(this).next().next(".td_").find('.mphone').val();
					var mssn = $(this).next().next(".td_").find('.mssn').val();
					var idx = $(this).prev().text();
					
					$(".div").html(text+", "+phone+", "+mssn);
					$("#chMID").val(idx);
				}); */
					


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
		
<!------------------------------------------------------------------------------------------------------------------------------------->		
	
		
		<script type="text/javascript">
			function search(){
				frm.action="<%=request.getContextPath()%>/Manager/MemberList.do";
				frm.method="get";
				frm.submit();
			}
			
			function memberinfo(chMID){
				
				var midx = $('#chMID').val();
				location.href ="<%=request.getContextPath()%>/Manager/Memberinfo.do?midx="+midx;
			}
			
			function memberbuylist(chMID){
				
				var midx = $('#chMID').val();
				var mid = $('')
				location.href ="<%=request.getContextPath()%>/Manager/Memberinfo.do?midx="+midx;
			}
		</script>
		
<!-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
		
		<section>
			<article>
				<div class="tit">
					<span class="label">|&nbsp;&nbsp;회원관리</span>
					<hr/>
				</div>
				<div>
					<div class="wrap_keyword">
						
						<div class="userinfo">
							<form name="frm">
								<div class="wrap_search">
									
									<input type="text" name="keyword" style="width:210px;height:25px;">
									<span class="wrap_input">
										<button type="button" style="height:36px;" onclick="search()">검색</button>
									</span>
								</div>
							</form>
							<input type="hidden" class="userinfo_input midx">
							<div class="div"></div>
							<input type="hidden" id="chMID">
						</div>
					</div>
				</div>
				<hr/>
				<div class="list_table">
					<table class="table2">
						<thead>
							<tr>
								<th width="10%">No.</th>
								<th width="15%">성함</th>
								<th width="20%">아이디</th>
								<th width="45%" colspan="2">최근 예매 내용</th>
							</tr>
						</thead>
						
						<form name="userFrm">
							<% for(MemberVo mv : alist){%>
								<tbody>
									<tr>
										<td class="midx">
											<%=mv.getMidx()%>
										</td>
										<td id="memberInfo" class="mname" style="cursor:pointer">
											<%=mv.getMname()%>
										</td>
										<td style="cursor:pointer" class="mid">
											<a class="mid" href="<%=request.getContextPath()%>/Manager/Memberinfo.do?midx=<%=mv.getMidx()%>">
												<%=mv.getMid()%>
											</a>
										</td>
										<td class="td_">
											
											<input type="hidden" class="mphone" value="<%=mv.getMphone()%>">
											<input type="hidden" class="mssn" value="<%=mv.getMssn()%>">
										</td>
										
									</tr>
								</tbody>
							<% } %>
						</form>
						
					</table>
					
					<div class="paging">
						<div id="main_event_page">
							<div id="main_event_page_set">
								<div class="main_event_page_button main_event_page_bn">
									<div class="main_event_page_button_llgg">&lt;&lt;</div>
								</div>
								
								<div class="main_event_page_button main_event_page_bn">
									<%if (pm.isPrev() == true) { %>
									<div class="main_event_page_button_lg">
										<a href="<%=request.getContextPath()%>/Manager/MemberList.do?page=<%=pm.getStartPage()-1%>&keyword=<%=pm.encoding(pm.getScri().getKeyword())%>">
											&lt;
										</a>
									</div>
									<% } %>
								</div>
							
								<a href="#">
									<div class="main_event_page_bn">
										<div class="main_event_page_button_page">
											<% for (int i=pm.getStartPage(); i<=pm.getEndPage(); i++){ %>
												<a href="<%=request.getContextPath()%>/Manager/MemberList.do?page=<%=i%>&keyword=<%=pm.encoding(pm.getScri().getKeyword())%>"><%=i%></a>	
											<% } %>
										</div>
									</div>
								</a>
							
								<div class="main_event_page_button main_event_page_bn">
									<%if (pm.isNext() && pm.getEndPage()>0) { %>
									<div class="main_event_page_button_lg">
										<a href="<%=request.getContextPath()%>/Manager/MemberList.do?page=<%=pm.getEndPage()+1%>&keyword=<%=pm.encoding(pm.getScri().getKeyword())%>">
											&gt;
										</a>
									</div>
									<% } %>
								</div>
								
								<div class="main_event_page_button main_event_page_bn">
									<div class="main_event_page_button_llgg">&gt;&gt;</div>
								</div>
								
							</div>
						</div>
					</div>
					
				</div>
			</article>
		</section>
		
<!-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

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