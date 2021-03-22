<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_event.js"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_all.js"></script>
		<script src="${pageContext.request.contextPath}/js/loginAlert.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Findconcert_list.css">
	</head>
	<body>
		<header>
			<div id="h_title">
				<div id="h_title_inner">
					<span id="h_top_menu">
						<ul id="h_top_menu_ul">
						<c:if test="${not empty sessionScope.mid}">
							<li>${sessionScope.mid }님 환영합니다!&nbsp;&nbsp;&nbsp;&nbsp;</li>
							<li><a href="${pageContext.request.contextPath}/Member/Memberlogout.do">로그아웃&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
						<c:if test="${empty sessionScope.mid}">
							<li class="login"><a href="${pageContext.request.contextPath}/Member/MemberLogin.do">로그인&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="${pageContext.request.contextPath}/Member/MemberJoin.do">회원가입&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
							<li><a href="${pageContext.request.contextPath}/Notice/NoticeList.do">고객센터&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
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
				<li><a href="#">참여 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		<div id="nav_menu_sub_myticket_div" class="main_nav_all">
			<ul id="nav_menu_sub_myticket" style="margin:0px;">
				<c:choose>
					<c:when test="${sessionScope.mgrade eq 'M' }">
						<li><a href="${pageContext.request.contextPath}/Admin/AdminMain.do">관리자홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/Admin/AdminMember.do">회원관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="#">공연관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="#">댓글관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="#">문의관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${not empty sessionScope.mid}">
								<li><a href="${pageContext.request.contextPath}/Myticket/MyticketMain.do">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a href="${pageContext.request.contextPath}/Myticket/MyticketReservation.do">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a href="${pageContext.request.contextPath}/Dibs/MyDibs.do">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a href="#">할인쿠폰</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							</c:when>
							<c:otherwise>
								<li><a onclick="loginAlert()">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a onclick="loginAlert()">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a onclick="loginAlert()">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a onclick="loginAlert()">할인쿠폰</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		
		
		<section>
			<article>
				<div class="tit">
					<span class="label">|&nbsp;&nbsp;공연관리</span>
					<span class="btn">
						<button type="button" class="qwrite">공연 등록</button>
					</span>
					<hr style="margin:10px 0 20px 0;"/>
				</div>
				<div class="wrap_search">
					<select name="searchType">
						<option value="total" selected>전체</option>
						<option value="title">공연명</option>
						<option value="host">주최명</option>
						<option value="date">날짜</option>
					</select>
					<span class="wrap_input">
						<input type="text" name="schText">
						<button type="button">검색</button>
					</span>
				</div>
				<div>
				<div class="wrap_img">
					<button class="btn_alb"><img src="btn_ctrl_view.jpg"></button>
					<button class="btn_list"><img src="btn_ctrl_view2.jpg"></button>
				</div>
				</div>
				<div class="mid">
					<div class="posterlist">
						<div class="posterlist_ul" style="display:block;">
							<ul>
								<li><a href="#"><img src="./poster/openposter8.jpg"></a></li>
								<li><a href="#"><img class="p2" src="./poster/musicalposter9.jpg"></a></li>
								<li><a href="#"><img src="./poster/musicalposter4.jpg"></a></li>
								<li><a href="#"><img src="./poster/musicalposter8.jpg"></a></li>
							</ul>
							<ul>
								<li><a href="#"><img src="./poster/musicalposter5.jpg"></a></li>
								<li><a href="#"><img src="./poster/musicalposter1.jpg"></a></li>
								<li><a href="#"><img src="./poster/musicalposter10.jpg"></a></li>
								<li><a href="#"><img src="./poster/openposter2.jpg"></a></li>
							</ul>
							<ul>
								<li><a href="#"><img src="./poster/openposter3.jpg"></a></li>
								<li><a href="#"><img src="./poster/musicalposter6.jpg"></a></li>
								<li><a href="#"><img src="./poster/openposter7.jpg"></a></li>
								<li><a href="#"><img src="./poster/openposter1.jpg"></a></li>
							</ul> 
							<ul>
								<li><a href="#"><img src="./poster/openposter8.jpg"></a></li>
								<li><a href="#"><img src="./poster/musicalposter9.jpg"></a></li>
								<li><a href="#"><img src="./poster/musicalposter4.jpg"></a></li>
								<li><a href="#"><img src="./poster/musicalposter8.jpg"></a></li>
							</ul>
						</div>
						<div class="posterlist_ul2" style="display:none;">
							<table class="tb1">
								<thead>
									<tr>
										<th width="275px">공연명</th>
										<th width="145px">공연일시</th>
										<th width="159px">공연장소</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/openposter8.jpg"></a>
												</span>
												<span class="show_title">
													<a href="#">마지막 사건</a>
												</span>
											</div>
										</td>
										<td>
											2021.02.18 ~ 2021.05.09
										</td>
										<td>
											드림아트센터 2관
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/musicalposter9.jpg"></a>
												</span>
												<span class="show_title">
													<a href="#">맨 오브 라만차</a>
												</span>
											</div>
										</td>
										<td>
											2020.12.18 ~ 2021.03.01
										</td>
										<td>
											샤롯데씨어터
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/musicalposter4.jpg"></a>
												</span>
												<span class="show_title">
													<a href="#">에어포트 베이비</a>
												</span>
											</div>
										</td>
										<td>
											2020.11.11 ~ 2021.01.31
										</td>
										<td>
											신한카드 FAN(판)스퀘어<br/>
											라이브홀
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/musicalposter8.jpg"></a>
												</span>
												<span class="show_title">
													<a href="#">위키드</a>
												</span>
											</div>
										</td>
										<td>
											2021.02.12 ~ 2021.05.01
										</td>
										<td>
											블루스퀘어 신한카드홀<br/>
											(구 인터파크홀)
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/musicalposter5.jpg"></a>
												</span>
												<span class="show_title">
													<a href="#">하모니</a>
												</span>
											</div>
										</td>
										<td>
											2021.02.18 ~ 2021.03.1
										</td>
										<td>
											예스24스테이지 1관
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/musicalposter1.jpg"></a>
												</span>
												<span class="show_title"><a href="#">검은 사제들</a></span>
											</div>
										</td>
										<td>
											2021.02.25 ~ 2021.05.30
										</td>
										<td>
											대학로 유니플렉스 1관
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/musicalposter10.jpg"></a>
												</span>
												<span class="show_title"><a href="#">배니싱</a></span>
											</div>
										</td>
										<td>
											2020.11.01 ~ 2021.02.21
										</td>
										<td>
											대학로 아트윈씨어터 1관
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/openposter2.jpg"></a>
												</span>
												<span class="show_title"><a href="#">명성황후</a></span>
											</div>
										</td>
										<td>
											2021.01.19 ~ 2021.03.07
										</td>
										<td>
											예술의전당 오페라극장
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/openposter3.jpg"></a>
												</span>
												<span class="show_title"><a href="#">캣츠</a></span>
											</div>
										</td>
										<td>
											2021.01.22 ~ 2021.02.28
										</td>
										<td>
											세종문화회관 대극장
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/musicalposter6.jpg"></a>
												</span>
												<span class="show_title"><a href="#">향화</a></span>
											</div>
										</td>
										<td>
											2021.02.19 ~ 2021.02.21
										</td>
										<td>
											경기아트센터 대극장
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/openposter7.jpg"></a>
												</span>
												<span class="show_title"><a href="#">나와 나타샤와 흰 당나귀</a></span>
											</div>
										</td>
										<td>
											2020.11.03 ~ 2021.02.14
										</td>
										<td>
											충무아트센터 중극장 블랙
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/openposter1.jpg"></a>
												</span>
												<span class="show_title"><a href="#">고스트</a></span>
											</div>
										</td>
										<td>
											2020.10.06 ~ 2021.03.14
										</td>
										<td>
											디큐브아트센터
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/openposter8.jpg"></a>
												</span>
												<span class="show_title"><a href="#">마지막 사건</a></span>
											</div>
										</td>
										<td>
											2021.02.18 ~ 2021.05.09
										</td>
										<td>
											드림아트센터 2관
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/musicalposter9.jpg"></a>
												</span>
												<span class="show_title"><a href="#">맨 오브 라만차</a></span>
											</div>
										</td>
										<td>
											2020.12.18 ~ 2021.03.01
										</td>
										<td>
											샤롯데씨어터
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/musicalposter4.jpg"></a>
												</span>
												<span class="show_title"><a href="#">에어포트 베이비</a></span>
											</div>
										</td>
										<td>
											2020.11.11 ~ 2021.01.31
										</td>
										<td>
											신한카드 FAN(판)스퀘어<br/>
											라이브홀
										</td>
									</tr>
									<tr>
										<td class="td_">
											<div class="show_info">
												<span class="show_poster">
													<a href="#"><img src="./poster/musicalposter8.jpg"></a>
												</span>
												<span class="show_title"><a href="#">위키드</a></span>
											</div>
										</td>
										<td>
											2021.02.12 ~ 2021.05.01
										</td>
										<td>
											블루스퀘어 신한카드홀<br/>
											(구 인터파크홀)
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="bigposter">
						<div class="bigposter_">
							<div id="p1">
								<img src="./poster/openposter8.jpg" id="imgview">
								<p class="showname"><b>마지막 사건</b></p>
							</div>
							<div id="p2" style="display:none;">
								<img src="./poster/musicalposter9.jpg" id="imgview">
								<p class="showname"><b>맨 오브 라만차</b></p>
							</div>
							<div id="p3" style="display:none;">
								<img src="./poster/musicalposter4.jpg" id="imgview">
								<p class="showname"><b>에어포트 베이비</b></p>
							</div>
							<div id="p4" style="display:none;">
								<img src="./poster/musicalposter8.jpg" id="imgview">
								<p class="showname"><b>위키드</b></p>
							</div>
						</div>
						<div class="posterbtn" style="padding-top:9px;">
							<span><button>바로가기</button></span>
							<span><button>좌석현황</button></span>
							<span><button>공지하기</button></span>
						</div>
					</div>
				</div>
				<div style="margin:5px;"></div>
				<hr style="margin: 20px 0;">
				<div class="wrap_search2">
					<select name="searchType">
						<option value="total" selected>전체</option>
						<option value="id">아이디</option>
						<option value="name">성함</option> 
						<option value="contents">내용</option>
					</select>
					<span class="wrap_input">
						<input type="text" name="schText" class="inputTypefaq">
						<button type="button">검색</button>
					</span>
				</div>
				<div class="wra_tb2">
					<table class="tb2">
						<tr>
							<td>No</td>
							<td>성함</td>
							<td>아이디</td>
							<td>예매내용</td>
						</tr>
						<tr>
							<td>99</td>
							<td>홍길동</td>
							<td>Hong10</td>
							<td></td>
						</tr>
						<tr>
							<td>98</td>
							<td>홍길은</td>
							<td>Hong9</td>
							<td></td>
						</tr>
						<tr>
							<td>97</td>
							<td>홍길금</td>
							<td>Hong8</td>
							<td></td>
						</tr>
						<tr>
							<td>96</td>
							<td>홍길석</td>
							<td>Hong6</td>
							<td></td>
						</tr>
						<tr>
							<td>95</td>
							<td>홍길동</td>
							<td>Hong5</td>
							<td></td>
						</tr>
						<tr>
							<td>94</td>
							<td>홍길동</td>
							<td>Hong4</td>
							<td></td>
						</tr>
					</table>
					<div class="paging">
						<a href="#"> << </a>&nbsp;&nbsp;<a href="#"> < </a>&nbsp;<span>&nbsp;&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;&nbsp;3&nbsp;&nbsp;&nbsp;&nbsp;4&nbsp;&nbsp;&nbsp;&nbsp;5&nbsp;&nbsp;&nbsp;&nbsp;6&nbsp;&nbsp;&nbsp;&nbsp;7&nbsp;&nbsp;&nbsp;&nbsp;8&nbsp;&nbsp;&nbsp;&nbsp;9&nbsp;&nbsp;&nbsp;&nbsp;10&nbsp;&nbsp;&nbsp;&nbsp; </span>&nbsp;<a href="#"> > </a>&nbsp;&nbsp;<a href="#"> >> </a>
					</div>
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