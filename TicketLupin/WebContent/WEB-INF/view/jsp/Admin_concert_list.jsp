<%@page import="java.util.List"%>
<%@page import="com.TicketLupin.web.service.Show1Vo"%>
<%@page import="domain.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	List<Show1Vo> list2 = (List<Show1Vo>) request.getAttribute("list2");
	Show1Vo sv = (Show1Vo) request.getAttribute("sv");
%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_event.js"></script>
		<script src="<%=request.getContextPath() %>/js/Admin_concert_list.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Admin_concert_list.css">
		<script>
			$(document).ready(function(){
				
				$(".td_").click(function(){
					var text = $(this).find('.show_title').text();
					var sidx = $(this).find('.sidx').val();
					var src_ = $(this).find('.clickposter').attr("src");
					console.log(src_);
					
					$("#imgview").attr("src",src_);
					$(".showname").html(text);
					$(".sidx_").val(sidx);
				});
				
				$(".clickposter_").click(function(){
					var src_ = $(this).attr("src");
					$("#imgview").attr("src",src_);
				});
				
				
				$(".clickposter").click(function(){
					var src_ = $(this).attr("src");
					var title = $(this).next().val();
					var sidx = $(".sidx").val();
					
					$(".sidx_").val(sidx);
					$(".showname").html(title);
					$("#imgview").attr("src",src_);
					
				});
			
				$(".hi").click(function(){
					var title = $(this).find(".show_title").val();
					var sidx = $(this).find(".sidx").val();
					
					$(".showname").html(title);
					$(".sidx_").val(sidx);
				});
				
			});
			
			
			function userList(sidx){
			
				$.ajax({
					url:"${pageContext.request.contextPath}/Manager/UserList.do",
					type:"get",
					data: {"sidx" : sidx},
					dataType:"json",
					success:function(data){
						
						var output = "";
						var output2 = "";
					
						
						for(var i = 0 ; i < (Number)(data.length-1) ; i++){
		                     output += " <tr>";
		                     output += "      <td>"+data[i].num+"</td>";
		                     output += "      <td><a href='UserBuyList2.do?midx="+data[i].midx+"&sidx="+data[i].sidx+"'>"+data[i].mname+"</a></td>";
		                     output += "      <td><a href='UserBuyList2.do?midx="+data[i].midx+"&sidx="+data[i].sidx+"'>"+data[i].mid+"</a></td>";
		                     output += "      <td><input type='hidden' value='"+data[i].sidx+"'><input type='hidden' value='"+data[i].midx+"'></td>";
		                     output += "   </tr>"; 
		                  }   
						
						
						if(data[0].count > 0){
							output2 += " 	<a href='?page="+data[0].prev+"&sidx="+data[0].sidx+"'> < &nbsp;&nbsp;</a>";
							
							for(var i = data[0].startNum ; i <= data[0].lastNum; i++){
								output2 += "<a href='?page="+data[0].startNum+"&sidx="+data[0].sidx+"'>"+ i +" &nbsp;&nbsp;</a>";
							}
							output2 += "	<a href='?page=" +data[0].next+ "&sidx=" +data[0].sidx+ "'> > </a>";
						}else if(data[0].count == 0 || data[0].count ==  null){
							output2 += "";
						}
						
						$(".userList_").html(output);
						$(".paging").html(output2);
						
					}
						
					});
						
				}
						
					
			
			
			
			function showdetail(){
				var sidx = $(".sidx_").val();
				
				location.href="<%=request.getContextPath()%>/Manager/Concert_View.do?sidx=" + sidx;
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
<!-- ------------------------------------------------------------------------------------------------------------------------------- -->		
		<section>
			<article>
				<div class="tit">
					<span class="label">|&nbsp;&nbsp;공연관리</span>
					<span class="btn">
						<button type="button" class="qwrite" onclick="location.href='<%=request.getContextPath()%>/Show/ShowWriteStep1.do'">공연 등록</button>
					</span>
					<hr style="margin:10px 0 20px 0;"/>
				</div>
				<div class="wrap_search">
					<span class="wrap_input">
						<form>
							<input type="text" name="q">
							<button type="submit">검색</button>
						</form>	
					</span>
				</div>
				<div>
				<div class="wrap_img">
					<button class="btn_list"><img src="../icon/btn_ctrl_view2.jpg"></button>
					<button class="btn_alb"><img src="../icon/btn_ctrl_view.jpg"></button>
				</div>
				</div>
				<div class="mid">
					<div class="posterlist">
						<div class="posterlist_ul" style="display:none;">
							<c:forEach var = "s" items ="${alist}">
								<ul style="display:inline;">
									<li class="hi">
										<a class="li_img">
											<img onclick="userList(${s.sidx})" class="clickposter_" src="<%=request.getContextPath() %>/poster/${s.stitleimage}">
										</a>
										<input type="hidden" class="sidx" value="${s.sidx }">
										<input type="hidden" class="show_title" value="${s.stitle }">
									</li>
								</ul>
							</c:forEach>
						</div>
						<div class="posterlist_ul2" style="display:block;">
							<table class="tb1">
								<thead>
									<tr>
										<th width="275px">공연명</th>
										<th width="145px">공연일시</th>
										<th width="159px" colspan="2">공연장소</th>
									</tr>
								</thead>
								<tbody id="listBody">
								
								<c:forEach var = "s" items ="${alist}">
									<tr>
										<td class="td_" onclick="userList(${s.sidx})">
											<div class="show_info">
												<span class="show_poster">
													<a onclick="userList(${s.sidx})" >
														<img class="clickposter" src="<%=request.getContextPath() %>/poster/${s.stitleimage}">
														<input type="hidden" value="${s.stitle }" class="h_stitle">
													</a>
												</span>
												<span class="show_title">
													<a onclick="userList(${s.sidx})" class="show_title_">${s.stitle}</a>
												</span>
												<input type="hidden" value="${s.sidx}" class="sidx">
											</div>
										</td>
										<td>
											${s.sopendate} ~ ${s.senddate }
										</td>
										<td>
										 	${s.sdetailaddress}  ${s.sextraaddress }
										</td>
										<td  colspan="2"></td>
									</tr>
								</c:forEach>
								
								</tbody>
							</table>
						</div>
					</div>
					<div class="bigposter">
						<div class="bigposter_">
							<div id="p1">
								
							<% 	if(sv != null){
									 %>	
								<div style="border:0.5px solid gray; width:184px; height:255px; position:center;" >
									<img id="imgview" src="<%=request.getContextPath() %>/image/<%=sv.getStitleimage() %>">
								</div>
								<p class="showname" style="height:24px;"><%=sv.getStitle() %></p>
								<input type="hidden" class="sidx_" value="<%=sv.getSidx()%>">
							<% 		
								}else{%>
								<div style="border:0.5px solid gray; width:184px; height:255px; position:center;" >
									<img id="imgview" >
								</div>
								<p class="showname" style="height:24px;"></p>
								<input type="hidden" class="sidx_">
							<%}	%>
							</div>
						</div>
						<div class="posterbtn" style="padding-top:9px;">
							<span><button onclick="showdetail()">바로가기</button></span>
						</div>
					</div>
				</div>
				<div style="margin:5px; position:float;"></div>
				<hr style="margin: 20px 0;">
				<div class="wrap_search2">
					
				</div>
				<div class="wra_tb2">
					<table class="tb2">
						<thead>
							<tr>
								<th>No</th>
								<th>성함</th>
								<th>아이디</th>
								<th></th>
							</tr>
						</thead>
						<tbody class="userList_">
							<c:forEach var = "m" items ="${list}">
								<tr>
									<td>${m.num}</td>
									<td><a href="${pageContext.request.contextPath}/Manager/UserBuyList.do?midx=${m.midx}">${m.mname }</a></td>
									<td><a href="${pageContext.request.contextPath}/Manager/UserBuyList.do?midx=${m.midx}">${m.mid }</a></td>
									<td><input type="hidden" value="${m.sidx}"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="paging">
	<!--------------------------------------------------------------------------------------------------------------------->
			
			<c:set var="page" value="${(param.page == null)?1:param.page}"/>
			<c:set var="startNum" value="${page-(page-1)%5}"/>
			<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"/>
			<c:set var="sidx" value="${param.sidx}"/>	
	<!--------------------------------------------------------------------------------------------------------------------->
						
						<div id="main_winner_page">
							<div id="main_winner_page_set">
	<!--------------------------------------------------------------------------------------------------------------------->
								<a href="?page=1&sidx=${sidx}">&lt; &nbsp;&nbsp;</a>	
	<!--------------------------------------------------------------------------------------------------------------------->
													
								<c:forEach var="i" begin="0" end="4">
									<c:if test="${(startNum+i) <= lastNum}">
										<a style="color: ${(page==(startNum+i))?'black':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?page=${startNum+i}&sidx=${sidx}" >
											${startNum+i} &nbsp;&nbsp;
										</a>
									</c:if>
								</c:forEach>
						
	<!--------------------------------------------------------------------------------------------------------------------->
								
								<a href="?page=${lastNum }&sidx=${sidx}">&gt; &nbsp;&nbsp;</a>
	<!--------------------------------------------------------------------------------------------------------------------->
							</div>
						</div>
					</div>
				</div>
				<div style="height:200px;"></div>
			</article>
		</section>
		
<!-- ------------------------------------------------------------------------------------------------------------------------------------------- -->

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