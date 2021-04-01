<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Admin_comment_list.css">
		<script src="<%=request.getContextPath() %>/js/Nav_event.js"></script>
		<script type = "text/javascript">
		
		
		$(document).ready(function(){
			$(".delete").click(function(){
				var testV = 1;
				var test = $(this).attr("id");
				var form = $('<form></form>');
				form.attr('action','<%=request.getContextPath() %>/Manager/CommentDeleteAction.do');
				form.attr('method','post');	
				form.appendTo('body');
				form.append($('<input type="hidden" value="'+test+'"name="c_idx">'));
				form.submit();
				alert('해당 댓글을 삭제하였습니다.');
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
							<li><a href="#">로그인&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">회원가입&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">고객센터&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">이용안내&nbsp;&nbsp;&nbsp;&nbsp;</a></li><br>
						</ul>
						<img src="musicalads.png" id="h_ads">
					</span>
					<img src="lupinlogo.png" id="h_logo">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" id="h_search" placeholder="뮤지컬 〈캣츠〉 40주년 내한공연 앙코르－서울（Musical CATS Encore">
					<button type="submit" id="h_search_button"><img src="search.png" id="h_search_img"></button>
				</div>
			</div>
		</header>
		<hr id="nav_bar_top">
		<div id="n_nav_div">
			<nav id="main_nav">
				<a href="#" id="main_nav_home">홈</a>
				<a href="#" id="main_nav_concert">공연</a>
				<a href="#" id="main_nav_ranking">랭킹</a>
				<a href="#" id="main_nav_news">티켓오픈소식</a>
				<a href="#" id="main_nav_event">이벤트</a>
				<a href="#" id="main_nav_admin">관리자</a>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		
		
		<div class="wrap_nav" id="wrap_nav"  style="display:none;">
			<div id="nav_menu_sub_div" class="main_nav_all">
				<ul id="nav_menu_sub">
					<li><a href="#">관리자홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><a href="#">회원관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><a href="#">공연관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><a href="#">댓글관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><a href="#">문의관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				</ul>
			</div>
			<hr id="nav_bar_sub"/>
		</div>
		<div class="wrap_nav"  id="wrap_nav2" style="display:none;">
			<div id="nav_menu_sub_div" class="main_nav_event" >
				<ul id="nav_menu_sub2">
					<li><a href="#">전체 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><a href="#">당첨자 발표</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><a href="#">참여이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				</ul>
			</div>
			<hr id="nav_bar_sub"/>
		</div>
		
		<section>
			<article>
				<div class="tit">
					<span class="label">|&nbsp;&nbsp;댓글관리</span>
					<hr/>
				</div>
				<div class="wrap_search">
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
				<div class="wrap_keyword">
					<span class="keyword_label">부적절한 키워드</span>
					<span class="wrap_input2">
						<input type="text" name="keyText">
						<button type="button" class="body_button" style="margin-left:10px;">등록</button>
						<button type="button" class="body_button">해제</button>
						<button type="button" class="body_button">목록</button>
						<button type="button" class="body_button">글삭제</button>			
					</span>
				</div>
				<div class="listorder">
					<a href="#">최근순</a>&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="#">추천순</a>
				</div>
				<div class="list_table">
					<table class="table2">
						<thead>
							<tr>
								<th width="5%">No.</th>
								<th width="10%">성함</th>
								<th width="10%">아이디</th>
								<th width="40%">댓글내용</th>
								<th width="15%">게시물</th>
								<th width="10%">삭제</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="aa" items="${clist}">
								<c:choose>
									<c:when test="${aa.c_depth==0 and aa.c_sort eq 'E' and  aa.c_content.length()>=27}">
										<tr>
											<td>${aa.num}</td>
											<td>${aa.mname}</td>
											<td>${aa.mid}</td>
											<td class="td_">
												<a href="#">
													[기대평]&nbsp;&nbsp;${aa.c_content}...
												</a>
											</td>
											<td>${aa.stitle}</td>
											<td><button type="button" id="${aa.c_idx}" class="body_button delete">삭제</button></td>
										</tr>
									</c:when>
										<c:when test="${aa.c_depth==0 and aa.c_sort eq 'E' and  aa.c_content.length()<27}">
										<tr>
											<td>${aa.num}</td>
											<td>${aa.mname}</td>
											<td>${aa.mid}</td>
											<td class="td_">
												<a href="#">
													[기대평]&nbsp;&nbsp;${aa.c_content}
												</a>
											</td>
											<td>${aa.stitle}</td>
											<td><button type="button" id="${aa.c_idx}" class="body_button delete">삭제</button></td>
										</tr>
									</c:when>
									
									<c:when test="${aa.c_depth==0 and aa.c_sort eq 'R'}">
										<tr>
											<td>${aa.num}</td>
											<td>${aa.mname}</td>
											<td>${aa.mid}</td>
											<td class="td_">
												<a href="#">
													[리뷰평]&nbsp;&nbsp;${aa.c_content}
												</a>
											</td>
											<td>${aa.stitle}</td>
											<td><button type="button" id="${aa.c_idx}"class="body_button delete">삭제</button></td>
										</tr>
									</c:when>
									<c:when test="${aa.c_depth==0 and aa.c_sort eq 'Q'}">
										<tr>
											<td>${aa.num}</td>
											<td>${aa.mname}</td>
											<td>${aa.mid}</td>
											<td class="td_">
												<a href="#">
													[QNA]&nbsp;&nbsp;${aa.c_content}
												</a>
											</td>
											<td>${aa.stitle}</td>
											<td><button type="button" id="${aa.c_idx}"class="body_button delete">삭제</button></td>
										</tr>
									</c:when>
									<c:when test="${aa.c_depth==1 and aa.c_sort eq 'E'}">
										<tr>
											<td>${aa.num}</td>
											<td>${aa.mname}</td>
											<td>${aa.mid}</td>
											<td class="td_">
												<a href="#">
													[기대평 대댓글]&nbsp;&nbsp;${aa.c_content}
												</a>
											</td>
											<td>${aa.stitle}</td>
											<td><button type="button" id="${aa.c_idx}"class="body_button delete">삭제</button></td>
										</tr>
									</c:when>
									<c:when test="${aa.c_depth==1 and aa.c_sort eq 'R'}">
										<tr>
											<td>${aa.num}</td>
											<td>${aa.mname}</td>
											<td>${aa.mid}</td>
											<td class="td_">
												<a href="#">
													[리뷰평 대댓글]&nbsp;&nbsp;${aa.c_content}
												</a>
											</td>
											<td>${aa.stitle}</td>
											<td><button type="button" id="${aa.c_idx}"class="body_button delete">삭제</button></td>
										</tr>
									</c:when>
									<c:when test="${aa.c_depth==1 and aa.c_sort eq 'Q'}">
										<tr>
											<td>${aa.num}</td>
											<td>${aa.mname}</td>
											<td>${aa.mid}</td>
											<td class="td_">
												<a href="#">
													[QNA 대댓글]&nbsp;&nbsp;${aa.c_content}
												</a>
											</td>
											<td>${aa.stitle}</td>
											<td><button type="button" id="${aa.c_idx}"class="body_button delete">삭제</button></td>
										</tr>
									</c:when>
									
									
								
								</c:choose>
							</c:forEach>
						</tbody>
					</table>
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