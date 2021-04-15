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
		<script src="${pageContext.request.contextPath}/js/Nav_all.js"></script>
		<script>
			var checkList = '';
			var orderWord = '';
			var pageNum = 1;
			$(document).ready(function(){
				$("input:checkbox[class ~='searchCheck']").on("change", function(){
					if($("input:checkbox[class ~= 'searchCheck']:checked").length > 5){
						alert("5개 이하의 태그만 선택할 수 있습니다");
						$(this).prop("checked", false);
						return;
					}
					
					if($(this).is(":checked")){
						checkList = "";
						$("input:checkbox[class ~= 'searchCheck']:checked").each(function(){
							checkList += $(this).val() + "/";
						})
					}else{
						checkList = "";
						$("input:checkbox[class ~= 'searchCheck']:checked").each(function(){
							checkList += $(this).val() + "/";
						})
					}
					
					pageNum = 1;
					ajaxStart();
				})
				
				$(".order").click(function(){
					orderWord = $(this).data("order");
					
					pageNum = 1;
					ajaxStart();
				})
				
				$(document).on("click",".pageSelect",function(){
					pageNum = $(this).data("page");
					
					ajaxStart();
				});
				
				
				function ajaxStart(){
					$.ajax({
						
						type:"get",
						url:"${pageContext.request.contextPath}/Show/ShowListAJAX.do",
						data:{"checkList": checkList, "s": orderWord, "p": pageNum},
						success:function(data){
							var date = new Date();
							var year = date.getFullYear();

							var month = date.getMonth() + 1;
							if (month < 10)  {
							    month = '0' + month;
							}

							var date = date.getDate();
							if (date < 10) {
							    date = '0' + date;
							}
							   
							var now =  year + '-' + month + '-' + date;
							
							var output = "";
							var output2 = "";
							
							output += "<ul>";
							for(var i = 0 ; i < data.length ; i++){
								if(now <= data[i].senddate){
									output += "	<li><a href='<%=request.getContextPath()%>/ConcertView/ConcertView.do?sidx=" + data[i].sidx + "'>";
									output += "		<div class='main_concert_musical_detail'>";
									output += "			<img src='<%=request.getContextPath() %>/poster/" + data[i].stitleimage + "' class='main_concert_musical_detail_poster'>";
									output += "			<div class='main_concert_musical_detail_title'>";
									output += 				data[i].stitle;
									output += "			</div>";
									output += "			<div class='main_concert_musical_detail_date'>";
									output += 				data[i].sopendate + " ~ " + data[i].senddate;
									output += "			</div>";
									output += "			<div class='main_concert_musical_detail_place'>";
									output += 				data[i].sdetailaddress;
									output += "			</div>";
									if(now >= data[i].sticketingdate && now <= data[i].senddate){
										output += "			<div class='main_concert_musical_detail_sold'>";
										output += "				<b style='color:red;'>판매 중</b>&nbsp;" + data[i].sticketingdate + "&nbsp;오픈";
										output += "			</div>";
									}else if(now < data[i].sticketingdate){
										output += "			<div class='main_concert_musical_detail_sold'>";
										output += "				<b style='color:blue;'>판매 예정</b>&nbsp;" + data[i].sticketingdate + "&nbsp;오픈";
										output += "			</div>";
									}
									output += "		</div>";
									output += "	</a></li>";
								}else if(now > data[i].senddate){	
								}
							}
							
							for(var i = 0 ; i < data.length ; i++){
								if(now > data[i].senddate){
									output += "	<li><a href='<%=request.getContextPath()%>/ConcertView/ConcertView.do?sidx=" + data[i].sidx + "'>";
									output += "		<div class='main_concert_musical_detail'>";
									output += "			<img src='<%=request.getContextPath() %>/poster/" + data[i].stitleimage + "' class='main_concert_musical_detail_poster'>";
									output += "			<div class='main_concert_musical_detail_title'>";
									output += 				data[i].stitle;
									output += "			</div>";
									output += "			<div class='main_concert_musical_detail_date'>";
									output += 				data[i].sopendate + " ~ " + data[i].senddate;
									output += "			</div>";
									output += "			<div class='main_concert_musical_detail_place'>";
									output += 				data[i].sdetailaddress;
									output += "			</div>";
									output += "			<div class='main_concert_musical_detail_sold'>";
									output += "				<b style='color:grey;'>판매 종료</b>&nbsp;" + data[i].sticketingdate + "&nbsp;오픈";
									output += "			</div>";
								}else if(now <= data[i].senddate){
								}
							}
							output += "</ul>";
							
							var page = 1;
							
							if(data.length != 0){
								page = data[0].page;
							}else{
								page = 1;
							}
							
							var startNum = (page-(page-1)%5);
							var lastNum = 1;
							if(data.length != 0){
								lastNum = Math.ceil(data[0].count/12);
							}else{
								lastNum = 1;
							}
							
							if(startNum > 1){
								output2 += "<a data-page='" + (startNum-1) + "' class='pageSelect' style='cursor:pointer;'>";
								output2 += "	<div class='main_news_page_button main_news_page_bn'>";
								output2 += "		<div class='main_news_page_button_lg'>&lt;</div>";
								output2 += "	</div>";
								output2 += "</a>";
							}else if(startNum <= 1){
								output2 += "<a onclick='alert(\"이전 페이지가 없습니다.\")' style='cursor:pointer;'>";
								output2 += "	<div class='main_news_page_button main_news_page_bn'>";
								output2 += "		<div class='main_news_page_button_lg'>&lt;</div>";
								output2 += "	</div>";
								output2 += "</a>";
							}
							
							output2 += "<div class='main_news_page_bn'>";
							for(var i = 0 ; i < 5 ; i++){
								var numColor = "";
								if(page == (startNum + i)){
									numColor = "red";
								}else{
									numColor = "";
								}
								var numWeight = "";
								if(page == (startNum + i)){
									numWeight = "bold";
								}else{
									numWeight = "";
								}
								if((startNum + i) <= lastNum ){
									output2 += "	<div class='main_news_page_button_page'>";
									output2 += "		<a style='cursor:pointer; color: " + numColor + "; font-weight:"+ numWeight + ";' data-page='" + (startNum + i) + "' class='pageSelect'>" + (startNum + i) + "</a>";
									output2 += "	</div>";
								}
							}
							output2 += "</div>"
							
							if((startNum + 4) < lastNum){
								output2 += "<a data-page='" + (startNum + 5) + "' class='pageSelect' style='cursor:pointer;'>";
								output2 += "	<div class='main_news_page_button main_news_page_bn'>";
								output2 += "		<div class='main_news_page_button_lg'>&gt;</div>";
								output2 += "	</div>";
								output2 += "</a>";
							}else if((startNum + 4) >= lastNum){
								output2 += "<a onclick='alert(\"다음 페이지가 없습니다.\")' style='cursor:pointer;'>";
								output2 += "	<div class='main_news_page_button main_news_page_bn'>";
								output2 += "		<div class='main_news_page_button_lg'>&gt;</div>";
								output2 += "	</div>";
								output2 += "</a>";
							}
							
							
							$("#main_concert_musical_list").html(output);
							$("#main_news_page_set").html(output2);
							
						}
					});
				}
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
					</div>
					<div id="main_concert_list_search_tag">
						<div id="main_concert_list_search_tag_genre" class="main_concert_list_search_tag_sector">
							<div>
								장르별
							</div>
							<ul>
								<div class="checkBox">
									<div>
										<li><input type="checkbox" name="genre" value="genreall" class="searchCheck ajaxClick"><span id="genreall">전체</span></li>
										<li><input type="checkbox" name="genre" value="original" class="searchCheck ajaxClick"><span id="original">오리지널/내한공연</span></li>
										<li><input type="checkbox" name="genre" value="license" class="searchCheck ajaxClick"><span id="license">라이선스</span></li>
									</div>
									<div class="checkRight">
										<li><input type="checkbox" name="genre" value="creation" class="searchCheck ajaxClick"><span id="creation">창작뮤지컬</span></li>
										<li><input type="checkbox" name="genre" value="nonverbal" class="searchCheck ajaxClick"><span id="nonverbal">넌버벌 퍼포먼스</span></li>
										<li><input type="checkbox" name="genre" value="package" class="searchCheck ajaxClick"><span id="package">패키지공연</span></li>
									</div>
								</div>
							</ul>
						</div>
						<div id="main_concert_list_search_tag_place" class="main_concert_list_search_tag_sector">
							<div>
								지역별
							</div>
							<ul>
								<div class="checkBox">
									<div>
										<li><input type="checkbox" name="place" value="placeall" class="searchCheck ajaxClick"><span id="placeall">전체</span></li>
										<li><input type="checkbox" name="place" value="seoul" class="searchCheck ajaxClick"><span id="seoul">서울</span></li>
										<li><input type="checkbox" name="place" value="incheon" class="searchCheck ajaxClick"><span id="incheon">경기/인천</span></li>
									</div>
									<div class="checkRight">
										<li><input type="checkbox" name="place" value="daejeon" class="searchCheck ajaxClick"><span id="daejeon">대전/충청/강원</span></li>
										<li><input type="checkbox" name="place" value="busan" class="searchCheck ajaxClick"><span id="busan">부산/대구/경상</span></li>
										<li><input type="checkbox" name="place" value="gwangju" class="searchCheck ajaxClick"><span id="gwangju">광주/전라/제주</span></li>
									</div>
								</div>
							</ul>
						</div>
					</div>
					<div id="main_concert_list_search_tag_checked" style="margin-bottom:20px;">

					</div>
					<div id="main_concert_musical_list_all">
						<div id="main_concert_musical_list_order">
							<a data-order="sopendate" class="order ajaxClick" style="cursor:pointer;"><div>공연임박순</div></a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a data-order="sregdate" class="order ajaxClick" style="cursor:pointer;"><div>최신순</div></a>
						</div>
<!------------------------------------------------------------------------------------------------------------------------>
					<c:set var="now_" value="<%=new java.util.Date()%>" />
					<fmt:formatDate value="${now_}" pattern="YYYY-MM-dd" var="now"/>
<!------------------------------------------------------------------------------------------------------------------------>
						<div id="main_concert_musical_list">
							<ul>
								<c:forEach var="l" items="${list}">
									<c:if test="${not (now > l.senddate)}">
										<li><a href="<%=request.getContextPath()%>/ConcertView/ConcertView.do?sidx=${l.sidx}">
											<div class="main_concert_musical_detail">
												<img src="<%=request.getContextPath() %>/poster/${l.stitleimage}" class="main_concert_musical_detail_poster">
												<div class="main_concert_musical_detail_title">
													${l.stitle}
												</div>
												<div class="main_concert_musical_detail_date">
													${l.sopendate } ~ ${l.senddate}
												</div>
												<div class="main_concert_musical_detail_place">
													${l.sdetailaddress}
												</div>
												<c:if test="${now >= l.sticketingdate and now <= l.senddate}">
													<div class="main_concert_musical_detail_sold">
														<b style="color:red;">판매 중</b>&nbsp;${l.sticketingdate}&nbsp;오픈
													</div>
												</c:if>
												<c:if test="${now < l.sticketingdate}">
													<div class="main_concert_musical_detail_sold">
														<b style="color:blue;">판매 예정</b>&nbsp;${l.sticketingdate}&nbsp;오픈
													</div>
												</c:if>
											</div>
										</a></li>
									</c:if>
									<c:if test="${now > l.senddate}"></c:if>
								</c:forEach>
								<c:forEach var="l" items="${list}">
									<c:if test="${now > l.senddate}">
										<li><a href="<%=request.getContextPath()%>/ConcertView/ConcertView.do?sidx=${l.sidx}">
											<div class="main_concert_musical_detail">
												<img src="<%=request.getContextPath() %>/poster/${l.stitleimage}" class="main_concert_musical_detail_poster">
												<div class="main_concert_musical_detail_title">
													${l.stitle}
												</div>
												<div class="main_concert_musical_detail_date">
													${l.sopendate } ~ ${l.senddate}
												</div>
												<div class="main_concert_musical_detail_place">
													${l.sdetailaddress}
												</div>
												<div class="main_concert_musical_detail_sold">
													<b>판매 종료</b>&nbsp;${l.sticketingdate}&nbsp;오픈
												</div>
											</div>
										</a></li>
									</c:if>
									<c:if test="${not(now > l.senddate)}"></c:if>
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
							<c:if test="${startNum>1}">
								<a data-page="${startNum-1}" class="pageSelect" style="cursor:pointer;">
									<div class="main_news_page_button main_news_page_bn">
										<div class="main_news_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
							<c:if test="${startNum<=1}">
								<a onclick="alert('이전 페이지가 없습니다.');" style="cursor:pointer;">
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
											<a style="cursor:pointer; color: ${(page==(startNum+i))?'red':''}; font-weight:${(page==(startNum+i))?'bold':''};" class="pageSelect" data-page="${startNum+i}" >${startNum+i}</a>
										</div>
									</c:if>
								</c:forEach>
							</div>
<!--------------------------------------------------------------------------------------------------------------------->
							<c:if test="${startNum+4<lastNum}">
								<a data-page="${startNum+5}" class="pageSelect" style="cursor:pointer;">
									<div class="main_news_page_button main_news_page_bn">
										<div class="main_news_page_button_lg">&gt;</div>
									</div>
								</a>
							</c:if>
							<c:if test="${startNum+4>=lastNum}">
								<a onclick="alert('다음 페이지가 없습니다.');" style="cursor:pointer;">
									<div class="main_news_page_button main_news_page_bn">
										<div class="main_news_page_button_lg">&gt;</div>
									</div>
								</a>
							</c:if>
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