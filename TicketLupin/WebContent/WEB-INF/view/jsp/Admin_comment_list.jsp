<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Admin_comment_list.css">
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_event.js"></script>
		<script src="<%=request.getContextPath() %>/js/Myticket_main.js"></script>
		<script type = "text/javascript">
			$(document).ready(function(){
				$(".delete").click(function(){
					var url=window.location.search.replaceAll("?","");
					var paramArray = url.split("&");
					var od="";
					for(var i=0; i<paramArray.length; i++){
 						var param = paramArray[i].split("=");
						if(param[0] == "od"){
							od = param[1];
							break;
						}
					}
					var test = $(this).attr("id");
					var form = $('<form></form>');
					form.attr('action','<%=request.getContextPath() %>/Manager/CommentDeleteAction.do');
					form.attr('method','post');	
					form.appendTo('body');
					form.append($('<input type="hidden" value="'+test+'"name="c_idx">'));
					form.append($('<input type="hidden" value="Y" name="delyn">'));
					form.append($('<input type="hidden" value="'+od+'" name="od">'));
					form.submit();
					alert('해당 댓글을 삭제하였습니다.');
				});
				

				$(".restoration").click(function(){
					var url=window.location.search.replaceAll("?","");
					var paramArray = url.split("&");
					var od="";
					for(var i=0; i<paramArray.length; i++){
 						var param = paramArray[i].split("=");
						if(param[0] == "od"){
							od = param[1];
							break;
						}
					}
					var test = $(this).attr("id");
					var form = $('<form></form>');
					form.attr('action','<%=request.getContextPath() %>/Manager/CommentDeleteAction.do');
					form.attr('method','post');	
					form.appendTo('body');
					form.append($('<input type="hidden" value="'+test+'"name="c_idx">'));
					form.append($('<input type="hidden" value="N" name="delyn">'));
					form.append($('<input type="hidden" value="'+od+'" name="od">'));
					form.submit();
					alert('해당 댓글을 복구하였습니다.');
				});
			/* -- */
		
				$(".checkedupdate").click(function(){
					var checkBoxArr=[];
					//checkBoxArr.push($("input[name=c_idx]:checked").val());
					var flag = false;
					var checkbox = $("input[name=c_idx]");
					for(var i =0 ; i<checkbox.length; i++){
						if(checkbox.eq(i).is(":checked")){
							flag = true;
							break;
						}
					}
					if(!flag){
						alert("선택하세요");
					}
					alert('전송하였습니다.');
					document.forms['checkcomment'].submit();
					
				});
			
			});	

	
	
			function showcontent(c_idx){
				var url ='<%=request.getContextPath() %>';
				var url = url+"/Manager/CommentView.do?c_idx="+c_idx;
	            var name = "popup test";
	            var option = "width = 630, height = 600, top = 100, left = 200, location = no"
	            window.open(url, name, option);
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
				<div class="tit">
					<span class="label">|&nbsp;&nbsp;댓글관리</span>
					<hr/>
				</div>
		
				<div class="wrap_keyword">
						<form action="<%=request.getContextPath() %>/Manager/comment.do">
						<select name="s">
							<option value="a.c_content" selected>내용</option>
							<option value="c.stitle" >공연</option>
							<option value="b.mid">아이디</option>
							<option value="b.mname">성함</option>
							
						</select>
						<span class="wrap_input">
							<input type="text" name="q">
							<button type="submit" class="body_button find">검색</button>
						</span>
					</form>
				</div>
				<div class="listorder">
					<a href="?od=latest">최근순</a>&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="?od=report">신고순</a>&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="?od=delete">삭제 댓글</a>
					<button type="button" class="check_button check_delete body_button checkedupdate">선택 삭제</button>
				</div>
				<form name="checkcomment" method="post"action="CommentCheckUpdate.do">
					<table class="table2">
						<thead>
							<tr>
								<th ><input type="checkbox" name="c_idx"></th>
								<th >No.</th>
								<th >성함</th>
								<th >아이디</th>
								<th >댓글내용</th>
								<th>게시물</th>
								<th >삭제</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="aa" items="${clist}">
										<tr>
											<td><input type="checkbox" name="c_idx" value="${aa.c_idx}"></td>
											<td>${aa.no}</td>
											<td><div class="info">${aa.mname}</div></td>
											<td><div class="info">${aa.mid}</div></td>
											<td class="td_">
												<div class="Content">
													<a href="#" onclick="showcontent(${aa.c_idx})" class="a">
														<c:choose>
															<c:when test="${aa.c_depth==0 and aa.c_sort eq 'E'}"> 
																[기대평]
															</c:when>
															<c:when test="${aa.c_depth==1 and aa.c_sort eq 'E'}"> 
																[기대평 대댓글]
															</c:when>
															<c:when test="${aa.c_depth==0 and aa.c_sort eq 'R'}"> 
																[리뷰평]
															</c:when>
															<c:when test="${aa.c_depth==1 and aa.c_sort eq 'R'}"> 
																[리뷰평 대댓글]
															</c:when>
															<c:when test="${aa.c_depth==0 and aa.c_sort eq 'Q'}"> 
																[QNA]
															</c:when>
															<c:when test="${aa.c_depth==1 and aa.c_sort eq 'Q'}"> 
																[QNA 대댓글]
															</c:when>
															<c:when test="${aa.c_depth==0 and aa.c_sort eq 'V'}"> 
																[이벤트]
															</c:when>
															<c:when test="${aa.c_depth==1 and aa.c_sort eq 'V'}"> 
																[이벤트 대댓글]
															</c:when>
														</c:choose>
														&nbsp;&nbsp;${aa.c_content}
														<c:if test="${aa.rcnt>'0'}">
														<span class="report">${aa.rcnt}</span>
														</c:if>	
													</a>
												</div>
											</td>
											<td class="td_">
												<div class="Boardtitle">
													<c:choose>
														<c:when test="${aa.eidx==0 and aa.sidx > 0}"> 
															<span>${aa.stitle}</span>
														</c:when>
														<c:when test="${aa.eidx > 0 and aa.sidx==0}"> 
															<span>${aa.etitle}</span>
														</c:when>
													</c:choose>
												</div>
											</td>
											<td>
											<c:if test="${aa.c_delyn eq 'N'}">
												<button type="button" id="${aa.c_idx}" class="body_button delete">삭제</button>
											</c:if>
											<c:if test="${aa.c_delyn eq 'Y'}">
												<button type="button" id="${aa.c_idx}" class="body_button restoration">복구</button>
											</c:if>
											</td>
										</tr>
							</c:forEach>
						</tbody>
					</table>
					</form>
					<c:set var="page" value="${(param.p == null)?1:param.p}"/>
					<c:set var="startNum" value="${page-(page-1)%5}"/>
					<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"/>
					<div class="paging">
						<a href="?p=1&q="> 
							<<
						</a>&nbsp;&nbsp;
					<c:if test= "${startNum>1}">
						<a href= "?p=${startNum-1}&q=">
							< 
						</a>
					</c:if>
					<c:if test= "${startNum<=1}">
						<a href= "#" onclick="alert('이전 페이지가 없습니다.');">
							<
						</a>
					</c:if>
					&nbsp;&nbsp;	
					<c:forEach var="i" begin="0" end= "4">
						<c:if test ="${(startNum+i) <= lastNum}">
							<a style="color: ${(page==(startNum+i))?'red':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?p=${startNum+i}&q=${param.q}" >${startNum+i}</a>					
						</c:if>
						&nbsp;&nbsp;
					</c:forEach>
					<c:if test="${startNum+4<lastNum}">
						<a href="?p=${startNum+5}&q=">
							>
						</a>
					</c:if>
					<c:if test="${startNum+4>=lastNum}">
						<a href="#" onclick="alert('다음 페이지가 없습니다.');">
							>
						</a>	
					</c:if>
					&nbsp;&nbsp;
					<a href="?p${lastNum}&q=">>></a>
					
					</div>
			</article>
		</section>
		<footer>
				<hr class="f_bar" id="f_bar_bottom">
				<div id="f_last">
					<span class="f_bottom_ment"><img src="lupinlogo.png" id="f_logo"></span>
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