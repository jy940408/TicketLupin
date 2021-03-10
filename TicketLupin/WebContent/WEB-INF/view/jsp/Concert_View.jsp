<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">

</script>
		<title>티켓 루팡</title>
		<link rel="stylesheet" type"text/css" href="<%=request.getContextPath() %>/css/Concert_view.css">
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Concert_view.js"></script>

	</head>
	<body onload="build();">
		<header>
			<div id="h_title">
				<div id="h_title_inner">
					<span id="h_top_menu">
						<ul id="h_top_menu_ul">
						<c:if test="${not empty sessionScope.mid}">
							<li>${sessionScope.mid }님 환영합니다!&nbsp;&nbsp;&nbsp;&nbsp;</li>
							<li><a href="<%=request.getContextPath()%>/Member/Memberlogout.do">로그아웃&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
						<c:if test="${empty sessionScope.mid}">
							<li class="login"><a href="<%=request.getContextPath()%>/Member/MemberLogin.do">로그인&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="<%=request.getContextPath()%>/Member/MemberJoin.do">회원가입&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
							<li><a href="<%=request.getContextPath()%>/Notice/NoticeList.do">고객센터&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
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
				<a href="<%=request.getContextPath()%>/Main/MainPage.do" id="main_nav_home">홈</a>
				<a href="<%=request.getContextPath()%>/Show/ShowList.do" id="main_nav_concert">공연</a>
				<a href="#" id="main_nav_ranking">랭킹</a>
				<a href="<%=request.getContextPath()%>/News/NewsList.do" id="main_nav_news">티켓오픈소식</a>
				<a href="#" id="main_nav_event">이벤트</a>
				<a href="#" id="main_nav_myticket">마이 티켓</a>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		<div id="nav_menu_sub_event_div" class="main_nav_all">
			<ul id="nav_menu_sub_event" style="margin:0px;">
				<li><a href="#">전체 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="<%=request.getContextPath()%>/Winner/WinnerList.do">당첨자 발표</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">참여 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		<div id="nav_menu_sub_myticket_div" class="main_nav_all">
			<ul id="nav_menu_sub_myticket" style="margin:0px;">
				<li><a href="#">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">할인쿠폰</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		<section>
			<article>
				<div id="main_concert_detail_div">
					<div id="main_concert_product">
						<div id="main_concert_cont">
							<div id="main_concert_cont_poster_div">
								<img src="<%=request.getContextPath()%>/poster/${detail.simage}" id="main_concert_cont_poster">
							</div>
							<div id="main_concert_cont_text_div">
								<div id="main_concert_cont_text_dibs">
									<p id="main_concert_process_title">${detail.stitle}</p>
									<div id="main_concert_process_dibs">
										<c:if test="${didx eq 0}">
											<a href="<%=request.getContextPath()%>/Dibs/DibsAction.do?sidx=${detail.sidx}"><div>✓찜하기 목록 담기</div></a>
										</c:if>
										<c:if test="${didx eq 1}">
											<a href="<%=request.getContextPath()%>/Dibs/DibsDeleteAction.do?sidx=${detail.sidx}"><div>✓찜하기 취소</div></a>
										</c:if>
									</div>
								</div>
								<div id="main_concert_process_info">
									<dl id="main_concert_process_info_left" class="main_concert_process_info_all">
										<dt>공연기간<dt>
										<dd><fmt:formatDate value="${detail.sopendate}" type="both" pattern="YYYY.MM.dd"/>-<fmt:formatDate value="${detail.senddate}" type="both" pattern="YYYY.MM.dd"/></dd>
										<dt>관람시간</dt>
										<dd>90분</dd>
										<dt>장르</dt>
										<dd>${detail.sgenre }</dd>
									</dl>
									<dl id="main_concert_process_info_right" class="main_concert_process_info_all">
										<dt>공연장<dt>
										<dd>${detail.sdetailaddress }</dd>
										<dt>관람등급</dt>
										<dd>${detail.srating }</dd>
										<dt>할인혜택</dt>
										<dd>무이자</dd>
									</dl>
								</div>
							</div>
						</div>
						<div id="main_concert_process_box">
							<div id="main_concert_process">
								<div id="main_concert_process_ticketing_set" class="main_concert_process_all_set">
									<div id="main_concert_process_ticketing">
										<div id="main_concert_process_date_choice">
											<div>날짜 선택</div>
										</div>
										<div id="main_concert_process_date_calender">
											<table id="calendar" style="text-decoration:none; color:black;">
												<tr>
													<c:choose>
														<c:when test="${month eq '0' }">
															<td colspan="2" align="center"><a href="?sidx=${detail.sidx}&month=11&year=${year-1}" style="text-decoration:none; color:black;">12월</a></td>
														</c:when>
														<c:otherwise>
															<td colspan="2" align="center"><a href="?sidx=${detail.sidx}&month=${month-1}&year=${year}" style="text-decoration:none; color:black;">${month}월</a></td>
														</c:otherwise>
													</c:choose>
													<td colspan="3" align="center"><a href="?sidx=${detail.sidx}&month=${month}&year=${year}" style="text-decoration:none; color:black;">${year}년 ${month+1}월</a></td>
													<c:choose>
														<c:when test="${month eq '11' }">
															<td colspan="2" align="center"><a href="?sidx=${detail.sidx}&month=0&year=${year+1}" style="text-decoration:none; color:black;">1월</a></td>
														</c:when>
														<c:otherwise>
															<td colspan="2" align="center"><a href="?sidx=${detail.sidx}&month=${month+1}&year=${year}" style="text-decoration:none; color:black;">${month+2}월</a></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td align="center"> <font color="#FF9090">일</font></td>
													<td align="center"> 월 </td>
													<td align="center"> 화 </td>
													<td align="center"> 수 </td>
													<td align="center"> 목 </td>
													<td align="center"> 금 </td>
													<td align="center"><font color=#7ED5E4>토</font></td>
												</tr>
												<tr>
												<c:forEach var="i" begin="1" end="${start-1}" step="1">
													<td></td>
													<input type="hidden" value="${newLine = newLine + 1}">
												</c:forEach>
												<c:forEach var="i" begin="1" end="${endDay}" step="1">
													<c:choose>
														<c:when test="${newLine eq 0}">
															<input type="hidden" value="${color = 'red'}">
														</c:when>
														<c:when test="${newLine eq 6 }">
															<input type="hidden" value="${color = 'blue'}">
														</c:when>
														<c:otherwise>
															<input type="hidden" value="${color = 'black'}">
														</c:otherwise>
													</c:choose>
													<td><a href="#" style="text-decoration:none; color:black;">${i}</a></td>
													<input type="hidden" value="${newLine = newLine + 1}">
													<c:if test="${newLine eq 7}">
														</tr>
														<c:if test="${i <= endDay}"><tr></c:if>
														<input type="hidden" value="${newLine = 0}">
													</c:if>
												</c:forEach>
												</tr>
											</table>
										</div>
									</div>
								</div>
								<div id="main_concert_process_time_choice_set" class="main_concert_process_all_set">
									<div id="main_concert_process_time_choice">
										<div>시간 선택</div>
									</div>
									<div id="main_concert_process_time_calender">
										대충 여기 날짜 회차 들어가는 곳이라는 뜻
									</div>
								</div>
							</div>
							<div id="main_concert_process_choice_button_div">
								<a href="<%=request.getContextPath()%>/Reservation/ReservationStep1.do"><div id="main_concert_process_choice_button">예매하기</div></a>
							</div>
						</div>
					</div>
					<div id="main_concert_detail_menu_div">
						<ul>
							<li class="main_concert_detail_menu_set"><a href="javascript:void(0);" onclick="showTab('main_concert_detail_content_all'); tabMenuColor('main_concert_detail_menu_div1');"><div id="main_concert_detail_menu_div1" style="background-color:rgba(0,0,0,0.2);">상세정보</div></a></li>
							<li class="main_concert_detail_menu_set"><a href="javascript:void(0);" onclick="showTab('main_concert_expect_all'); tabMenuColor('main_concert_detail_menu_div2');"><div id="main_concert_detail_menu_div2">기대평</div></a></li>
							<li class="main_concert_detail_menu_set"><a href="javascript:void(0);" onclick="showTab('main_concert_review_all'); tabMenuColor('main_concert_detail_menu_div3');"><div id="main_concert_detail_menu_div3">관람평</div></a></li>
							<li class="main_concert_detail_menu_set"><a href="javascript:void(0);" onclick="showTab('main_concert_question_all'); tabMenuColor('main_concert_detail_menu_div4');"><div id="main_concert_detail_menu_div4">Q&amp;A</div></a></li>
							<li class="main_concert_detail_menu_set"><a href="javascript:void(0);" onclick="showTab('main_concert_place_all'); tabMenuColor('main_concert_detail_menu_div5');"><div id="main_concert_detail_menu_div5">공연장 정보</div></a></li>
							<li class="main_concert_detail_menu_set"><a href="javascript:void(0);" onclick="showTab('main_concert_info_all'); tabMenuColor('main_concert_detail_menu_div6');"><div id="main_concert_detail_menu_div6">예매안내</div></a></li>
						</ul>
					</div>
				</div>
				<!---------------------------------상세페이지--------------------------------------->
				<div id="main_concert_detail_content_all">
					<div id="main_concert_detail_content_div">
						<div id="main_concert_detail_playtime">  
							<p>공연시간</p>
							<div>
								월, 화, 목, 금 오후 5시/토, 일 오후 3시<br>
								*수요일 공연 없음<br>
								*2월 11일 3시<br>
								*2월 12일 공연 없음
							</div>
						</div>
						<div id="main_concert_detail_pay" class="main_concert_detail_all">
							<p>가격정보</p>
						</div>
						<div id="main_concert_detail_notice" class="main_concert_detail_all">
							<p>예매 공지사항</p>
							<img src="../content/content1.jpg">
							<img src="../content/content2.jpg">
						</div>
						<div id="main_concert_detail_discount" class="main_concert_detail_all">
							<p>할인정보</p>
							<img src="../content/content3.jpg">
							<img src="../content/content4.jpg">
							<img src="../content/content5.jpg">
						</div>
						<div id="main_concert_detail_content" class="main_concert_detail_all">
							<p>작품정보</p>
							<img src="../content/content6.jpg">
						</div>
						<div id="main_concert_detail_company" class="main_concert_detail_all">
							<p>기획사 정보</p>
							<div>
								주최/주관: 위로컴퍼니<br>
								문의: 070-4792-8342
							</div>
						</div>
						<div id="main_concert_detail_info" class="main_concert_detail_all">
							<p>상품정보제공 고시</p>
							<div>
								<table style="border:1px solid black;">
									<tr>
										<td style="background-color:rgba(0,0,0,0.1);">주최/기획</td>
										<td>위로컴퍼니</td>
									</tr>
									<tr>
										<td style="background-color:rgba(0,0,0,0.1);">공연 관련 문의</td>
										<td>070-4792-8342</td>
									</tr>
									<tr>
										<td style="background-color:rgba(0,0,0,0.1);">예매 관련 문의</td>
										<td>1899-0042</td>
									</tr>
									<tr>
										<td style="background-color:rgba(0,0,0,0.1);">유효기간(이용조건)</td>
										<td>2020.10.21 - 2021.02.28 (예매한 공연 회차에 한해 이용가능)</td>
									</tr>
									<tr>
										<td style="background-color:rgba(0,0,0,0.1);">취소/환불조건</td>
										<td>
											- 취소마감시간 이후 또는 관람일 당일 예매하신 건에 대해서는 취소/변경/환불이 불가합니다.<br>
											- 예매수수료는 예매 당일 밤 12시 이전까지 취소 시 환불 가능합니다.<br>
											- 배송이 시작된 경우 취소마감시간 이전까지 멜론티켓 고객센터로 티켓을 반환해주셔야 환불이 가능하며, 도착한 일자 기준으로 취소수수료가 부과됩니다.<br>
											(* 단, 반환된 티켓의 배송료는 환불되지 않으며 일괄배송 상품의 경우 취소에 대한 자세한 문의는 고객센터로 문의해 주시기 바랍니다.)<br>
											- 예매취소 시점과 결제 시 사용하신 신용카드사의 환불 처리기준에 따라 취소금액의 환급방법과 환급일은 다소 차이가 있을 수 있습니다.<br>
											- 티켓 부분 취소 시 신용카드 할부 결제는 티켓 예매 시점으로 적용됩니다. (무이자할부 행사기간이 지날 경우 혜택 받지 못하실 수 있으니 유의하시기 바랍니다.)<br>
											- 취소일자에 따라 아래와 같이 취소수수료가 부과됩니다.<br>
											(예매 후 7일 이내라도 취소시점이 관람일로부터 10일 이내라면 관람일 기준의 취소수수료가 부과됩니다.)<br>
											<br>
											<table style="border:1px solid black;">
												<tr>
													<td style="background-color:rgba(0,0,0,0.1);">취소일</td>
													<td style="background-color:rgba(0,0,0,0.1);">취소수수료</td>
												</tr>
												<tr>
													<td>예매 후 7일 이내</td>
													<td>없음</td>
												</tr>
												<tr>
													<td>예매 후 8일 ~ 관림 일 10일 이내</td>
													<td>장당 2000원 (티켓 금액의 10% 한도)</td>
												</tr>
												<tr>
													<td>관람 일 9일 전 ~ 7일 전</td>
													<td>티켓 금액의 10%</td>
												</tr>
												<tr>
													<td>관람 일 6일 전 ~ 3일 전</td>
													<td>티켓 금액의 20%</td>
												</tr>
												<tr>
													<td>관람 일 2일 전 ~ 1일 전</td>
													<td>티켓 금액의 30%</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!---------------------------------기대평--------------------------------------->
				<div id="main_concert_expect_all" style="display:none;">
					<div id="main_concert_expect_div">
						<div id="main_concert_expect_notice">
							<div id="main_concert_expect_notice_ment">
								ⓘ 티켓 매매 및 양도, 교환의 글은 사전 통보 없이 삭제될 수 있습니다.
							</div>
							<div id="main_concert_expect_notice_button">
								<a href="#">
									<div id="main_concert_expect_notice_butto_div">
									게시판 운영규칙
									</div>
								</a>
							</div>
							<div id="main_concert_expect_notice_button_popup" style="display:none;">
								대충 게시판 운영규칙이라는 뜻
							</div>
						</div>
					</div>
					<div id="main_concert_expect_content">
						<form action="<%=request.getContextPath()%>/Review/ExpectWriteAction.do" method="post">
						<div id="main_concert_expect_content_write_div">
							<img src="../icon/person.png" class="main_concert_expect_content_write_all">
							<div class="main_concert_expect_content_write_all">
								<textarea name="content" placeholder="* 게시된 글의 저작권을 글을 작성한 회원에게 있으며 게시물로 인해 발생하는 문제는 게시자 본인에게 책임이 있습니다"></textarea>
							</div>
							<button type="submit" id="main_concert_expect_content_write_button">등록</button>
						</div>
						</form>
						<div id="main_concert_expect_content_number_all">
							<div id="main_concert_expect_content_number_order">
								<div id="main_concert_expect_content_number">총 ${ecount}개</div>
								<div id="main_concert_expect_content_list_order">
									<ul>
										<li><a href="#">최신순</a></li>&nbsp;&nbsp;|&nbsp;
										<li><a href="#">추천순</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div id="main_concert_expect_content_list">
							<ul>
								<hr id="main_concert_expect_content_list_bar_first">
								<c:forEach var="bb" items="${elist}">
								<li>
									<div id="main_concert_expect_content_list_id_set" class="main_concert_expect_content_list_set_all">
										<img src="../icon/person.png" class="main_concert_expect_content_list_id_all">
										<div id="main_concert_expect_content_list_id" class="main_concert_expect_content_list_id_all">
											${bb.midx}
										</div>
									</div>
									<div id="main_concert_expect_content_list_content_set" class="main_concert_expect_content_list_set_all">
										<div id="main_concert_expect_content_list_content_main" class="main_concert_expect_content_list_content_all">
											${bb.xcontent}
										</div>
										<br>
										<div id="main_concert_expect_content_list_content_date" class="main_concert_expect_content_list_content_all">
											${bb.xregdate}
										</div>
										<br>
										<div id="main_concert_expect_content_list_content_good" class="main_concert_expect_content_list_content_all">
											<ul>
												<li>👍${bb.xgood}</li>
												<li>👎20</li>
												<li>신고</li>
											</ul>
										</div>
									</div>
									<hr class="main_concert_expect_content_list_bar_bottom">
								</li>
								</c:forEach>
							</ul>
				
					<c:set var="page" value="${(param.ep == null)?1:param.ep}"/>
					<c:set var="startNum" value="${page-(page-1)%5}"/>
					<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(ecount/10),'.')}"/>
							<div id="main_page_button_set">
								<a href="?ep=1&q=">
								<div class="main_page_button main_page_bn">
								<div class="main_page_button_llgg">&lt;&lt;</div>
								</div>
							</a>
							<c:if test= "${startNum>1}">
								<a href= "?ep=${startNum-1}&q=">
									<div class="main_page_button main_page_bn">
										<div class="main_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
							<c:if test= "${startNum<=1}">
								<a href= "#" onclick="alert('이전 페이지가 없습니다.');">
									<div class="main_page_button main_page_bn">
										<div class="main_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
								
								<div class="main_page_bn">
									<c:forEach var="i" begin="0" end= "4">
										<c:if test ="${(startNum+i) <= lastNum}">
											<div class="main_page_button_page">
												<a style="color: ${(page==(startNum+i))?'red':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?ep=${startNum+i}&q=${param.q}" >${startNum+i}</a>
											</div>
										</c:if>
									</c:forEach>
								</div>
								<c:if test="${startNum+4<lastNum}">
									<a href="?ep=${startNum+5}&q=">
										<div class="main_page_button main_event_page_bn">
											<div class="main_page_button_lg">&gt;</div>
										</div>	
									</a>
								</c:if>
								<c:if test="${startNum+4>=lastNum}">
									<a href="#" onclick="alert('다음 페이지가 없습니다.');">
										<div class="main_page_button main_event_page_bn">
											<div class="main_page_button_lg">&gt;</div>
										</div>
									</a>	
								</c:if>
										
								<div class="main_page_button main_event_page_bn">
									<a href="?ep${lastNum}&q="><div class="main_page_button_llgg">&gt;&gt;</div></a>
								</div>
							</div>
						</div>
					</div>
				</div>
					
					<!---------------------------------리뷰평--------------------------------------->
				<div id="main_concert_review_all" style="display:none;">
					<div id="main_concert_review_div">
						<div id="main_concert_review_notice">
							<div id="main_concert_review_notice_ment">
								ⓘ 티켓 매매 및 양도, 교환의 글은 사전 통보 없이 삭제될 수 있습니다.
							</div>
							<div id="main_concert_review_notice_button">
								<a href="#">
									<div id="main_concert_review_notice_butto_div">
									게시판 운영규칙
									</div>
								</a>
							</div>
						</div>
					</div>
					<div id="main_concert_review_content">
						<form action="<%=request.getContextPath()%>/Review/ReviewWriteAction.do" method="post">
						<div id="main_concert_review_content_write_div">
								<img src="../icon/person.png" class="main_concert_review_content_write_all">
							<div class="main_concert_review_content_write_all">
								<textarea name="content"  placeholder="* 게시된 글의 저작권을 글을 작성한 회원에게 있으며 게시물로 인해 발생하는 문제는 게시자 본인에게 책임이 있습니다"></textarea>	
							</div>
							<button type="submit" id="main_concert_review_content_write_button" value="submit">등록</button>
						</div>
						</form>
						<div id="main_concert_review_content_number_all">
							<div id="main_concert_review_content_number_order">
								<div id="main_concert_review_content_number">총 ${count}개</div>
								<div id="main_concert_review_content_list_order">
									<ul>
										<li><a href="#">최신순</a></li>&nbsp;&nbsp;|&nbsp;
										<li><a href="#">추천순</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div id="main_concert_review_content_list">
							<ul>
								<hr id="main_concert_review_content_list_bar_first">
								<c:forEach var="aa" items="${list}">
								<li>
									<div id="main_concert_review_content_list_id_set" class="main_concert_review_content_list_set_all">
										<img src="../icon/person.png" class="main_concert_review_content_list_id_all">
										<div id="main_concert_review_content_list_id" class="main_concert_review_content_list_id_all">
											${aa.midx}
										</div>
									</div>
									<div id="main_concert_review_content_list_content_set" class="main_concert_review_content_list_set_all">
										<div id="main_concert_review_content_list_content_main" class="main_concert_review_content_list_content_all">
											${aa.vcontent}
										</div>
										<br>
										<div id="main_concert_review_content_list_content_date" class="main_concert_review_content_list_content_all">
											${aa.vregdate}
										</div>
										<br>
										<div id="main_concert_review_content_list_content_good" class="main_concert_review_content_list_content_all">
											<ul>
												<li>👍${aa.vgood}</li>
												<li>👎20</li>
												<li>신고</li>
											</ul>
										</div>
									</div>
									<hr class="main_concert_review_content_list_bar_bottom">
								</li>
								</c:forEach>
								</ul>
			
					<c:set var="page" value="${(param.p == null)?1:param.p}"/>
					<c:set var="startNum" value="${page-(page-1)%5}"/>
					<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"/>
						
							<div id="main_page_button_set">
							
								<a href="?p=1&q=">
								<div class="main_page_button main_page_bn">
								<div class="main_page_button_llgg">&lt;&lt;</div>
								</div>
							</a>
							<c:if test= "${startNum>1}">
								<a href= "?p=${startNum-1}&q=">
									<div class="main_page_button main_page_bn">
										<div class="main_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
							<c:if test= "${startNum<=1}">
								<a href= "#" onclick="alert('이전 페이지가 없습니다.');">
									<div class="main_page_button main_page_bn">
										<div class="main_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
								
								<div class="main_page_bn">
									<c:forEach var="i" begin="0" end= "4">
										<c:if test ="${(startNum+i) <= lastNum}">
											<div class="main_page_button_page">
												<a style="color: ${(page==(startNum+i))?'red':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?p=${startNum+i}&q=${param.q}" >${startNum+i}</a>
											</div>
										</c:if>
									</c:forEach>
								</div>
								<c:if test="${startNum+4<lastNum}">
									<a href="?p=${startNum+5}&q=">
										<div class="main_page_button main_event_page_bn">
											<div class="main_page_button_lg">&gt;</div>
										</div>	
									</a>
								</c:if>
								<c:if test="${startNum+4>=lastNum}">
									<a href="#" onclick="alert('다음 페이지가 없습니다.');">
										<div class="main_page_button main_event_page_bn">
											<div class="main_page_button_lg">&gt;</div>
										</div>
									</a>	
								</c:if>
										
								<div class="main_page_button main_event_page_bn">
									<a href="?p${lastNum}&q="><div class="main_page_button_llgg">&gt;&gt;</div></a>
								</div>
							</div>
						</div>
					</div>
				</div>
					
				
				
					<!---------------------------------QNA--------------------------------------->
				<div id="main_concert_question_all" style="display:none;">
					<div id="main_concert_question_div">
						<div id="main_concert_question_notice">
							<div id="main_concert_question_notice_ment">
								Q&amp;A게시판은 모든 질문과 답변에 참여할 수 있는 공간입니다.<br>
							</div>
							<div id="main_concert_question_notice_ment_sub">
								예매/배송 관련 문의사항은 고객센터>1:1문의 또는 FAQ나 이용안내를 이용해주세요.
							</div>
						</div>
					</div>
					<div id="main_concert_question_content">
						<form action="<%=request.getContextPath()%>/Review/CquestionWriteAction.do" method="post">
						<div id="main_concert_question_content_write_div">
							<div class="main_concert_question_content_write_all">
								<textarea name="content" placeholder="* 게시된 글의 저작권을 글을 작성한 회원에게 있으며 게시물로 인해 발생하는 문제는 게시자 본인에게 책임이 있습니다
* 게시판에 고객님의 연락처, 주소 등의 개인정보가 포함된 글을 올리실 경우에는 타인에게 해당 정보가 노출될 수 있으니 게재를 삼가하여 주시기 바랍니다."></textarea>
							</div>
							<button type="submit" id="main_concert_question_content_write_button">등록</button>
						</div>
						</form>
						<div id="main_concert_question_content_number_all">
							<div id="main_concert_question_content_number_order">
								<div id="main_concert_question_content_number">총 ${cqcount}개</div>
								<div id="main_concert_question_content_list_order">
									<ul>
										<li><a href="#">최신순</a></li>&nbsp;&nbsp;|&nbsp;
										<li><a href="#">추천순</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div id="main_concert_question_content_list">
							<ul>
								<hr id="main_concert_question_content_list_bar_first">
								<c:forEach var="aa" items="${cqlist}">
								<li>
									<div>
										<div class="main_concert_question_content_list_id_div">
											<img src="../icon/person.png" class="main_concert_question_content_list_id_all">
											<div class="main_concert_question_content_list_id">
												${aa.midx}
											</div>
										</div>
										<div class="main_concert_question_content_list_text_div">
											<div class="main_concert_question_content_list_text">
												${aa.cqcontent}
											</div>
											<div class="main_concert_question_content_list_date">
												${aa.cqregdate}
											</div>
											<div class="main_concert_question_content_list_content_good">
												<ul>
													<li>👍${aa.cqgood}</li>
													<li>👎20</li>
													<li>신고</li>
												</ul>
											</div>
											<a href="javascript:void(0);" onclick="showAnswerTab('main_concert_question_content_list_answer_1');" class="main_concert_question_content_list_more">답변 보기</a>
											<div class="main_concert_question_content_list_answer_all" id="main_concert_question_content_list_answer_1" style="display:none;">
												<div class="main_concert_question_content_list_answer">
													대충 여기 답변 올라오는 곳이라는 뜻
												</div>
												<div class="main_concert_question_content_list_answer_date">
													2020.02.08
												</div>
											</div>
										</div>
									</div>
									<hr class="main_concert_question_content_list_bar_bottom">
								</li>
								</c:forEach>
							</ul>
					<c:set var="page" value="${(param.cqp == null)?1:param.cqp}"/>
					<c:set var="startNum" value="${page-(page-1)%5}"/>
					<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(cqcount/10),'.')}"/>
							<div id="main_page_button_set">
								<a href="?cqp=1&q=">
								<div class="main_page_button main_page_bn">
								<div class="main_page_button_llgg">&lt;&lt;</div>
								</div>
							</a>
							<c:if test= "${startNum>1}">
								<a href= "?cqp=${startNum-1}&q=">
									<div class="main_page_button main_page_bn">
										<div class="main_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
							<c:if test= "${startNum<=1}">
								<a href= "#" onclick="alert('이전 페이지가 없습니다.');">
									<div class="main_page_button main_page_bn">
										<div class="main_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
								
								<div class="main_page_bn">
									<c:forEach var="i" begin="0" end= "4">
										<c:if test ="${(startNum+i) <= lastNum}">
											<div class="main_page_button_page">
												<a style="color: ${(page==(startNum+i))?'red':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?cqp=${startNum+i}&q=${param.q}" >${startNum+i}</a>
											</div>
										</c:if>
									</c:forEach>
								</div>
								<c:if test="${startNum+4<lastNum}">
									<a href="?cqp=${startNum+5}&q=">
										<div class="main_page_button main_event_page_bn">
											<div class="main_page_button_lg">&gt;</div>
										</div>	
									</a>
								</c:if>
								<c:if test="${startNum+4>=lastNum}">
									<a href="#" onclick="alert('다음 페이지가 없습니다.');">
										<div class="main_page_button main_event_page_bn"> 
											<div class="main_page_button_lg">&gt;</div>
										</div>
									</a>	
								</c:if>
										
								<div class="main_page_button main_event_page_bn">
									<a href="?cqp${lastNum}&q="><div class="main_page_button_llgg">&gt;&gt;</div></a>
								</div>
							</div>
						</div>
					</div>
				</div>
					
					<!---------------------------------공연장위치--------------------------------------->
				<div id="main_concert_place_all" style="display:none;">
					<div id="main_concert_place_div">
						<img src="../image/seoyeonmain.jpg" class="main_concert_place_set">
						<div id="main_concert_place_content" class="main_concert_place_set">
							<div id="main_concert_place_name">
								서연아트홀
							</div>
							<div id="main_concert_place_address">
								서울 종로구 창경궁로 258-9(명륜2가)
							</div>
						</div>
					</div>
				</div>
					<!---------------------------------예매안내-------------------------------------->
				<div id="main_concert_info_all" style="display:none;">
					<div>티켓 수령 방법 안내</div>
					<div>
						현장수령<br>
						- 예매번호가 포함되어 있는 예매확인서와 예매자의 실물 신분증(복사본 및 사진 불가) 을 매표소에 제출하시면 편리하게 티켓을 수령하실 수 있습니다.<br>
						※ 공연별 정책이 상이하니 자세한 내용은 예매페이지 내 상세정보 확인 부탁드립니다.<p/>
						배송<br>
						- 배송을 선택하신 경우 예매완료(결제익일) 기준 4~5일 이내에 예매 시 입력하신 주소로 배송됩니다. (주말/공휴일 제외한 영업일 기준)<br>
						- 일괄배송의 경우 공연 별로 배송일자가 상이하며 지정된 배송일자 기준으로 배송이 시작됩니다. (지정된 배송일자는 상세정보 및 예매공지사항에서 확인할 수 있습니다.)<br>
						- 지역 및 배송서비스 사정에 따라 배송사가 변경될 수 있으며, 배송일이 추가적으로 소요될 수 있습니다. (CJ대한통운, 우체국 외 1개 업체)<br>
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