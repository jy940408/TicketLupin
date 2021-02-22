<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/slick-1.8.1/slick-1.8.1/slick/slick.css" />
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/js/slick-1.8.1/slick-1.8.1/slick/slick.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_event.js"></script>
		<script src="<%=request.getContextPath() %>/js/Ticketopen_list.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Ticketopen_list.css">
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
		
		
		<div class="wrap_nav" id="wrap_nav" style="display:none;">
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
			<div id="nav_menu_sub_div" class="main_nav_event">
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
				<div style="padding:30px;"></div>
				<div class="ticketopen">
					<div class="hot">
						<p class="tit">
							<span class="hot_tit"><strong>HOT 공연 오픈 소식</strong></span>
							<span class="hot_paging" style="display:block;" id="hot_paging">
								<a href="#" class="prev"> < </a> &nbsp;&nbsp; 1 &nbsp; / &nbsp; 2 &nbsp;&nbsp; <a href="#" class="next" onclick="hot()"> > </a>
							</span>
						</p>
						<div class="slider">
							
							<span class="slider_1">
								<ul class="list_slider">
									<li class="li">
										<a href="#">
											<img src="./poster/openposter1.jpg" class="posimg">
										</a>
									</li>
									<li class="li">
										<a href="#">
											<img src="./poster/openposter2.jpg" class="posimg">
										</a>
									</li>
									<li class="li">
										<a href="#">
											<img src="./poster/openposter3.jpg" class="posimg">
										</a>
									</li>
									<li class="li">
										<a href="#">
											<img src="./poster/openposter4.jpg" class="posimg">
										</a>
									</li>
									<li class="li">
										<a href="#">
											<img src="./poster/openposter5.jpg" class="posimg">
										</a>
									</li>
									<li class="li">
										<a href="#">
											<img src="./poster/musicalposter4.jpg" class="posimg">
										</a>
									</li>
									<li class="li">
										<a href="#">
											<img src="./poster/musicalposter9.jpg" class="posimg">
										</a>
									</li>
									<li class="li">
										<a href="#">
											<img src="./poster/openposter7.jpg" class="posimg">
										</a>
									</li>
									<li class="li">
										<a href="#">
											<img src="./poster/musicalposter6.jpg" class="posimg">
										</a>
									</li>
									<li class="li">
										<a href="#">
											<img src="./poster/musicalposter10.jpg" class="posimg">
										</a>
									</li>
								</ul>
							</span>
							<div class="slick_btns">
								<span class="radio_input">
									<input type="radio" name="dots" value="prev" class="prev" checked="checked"/>
									<input type="radio" name="dots" value="next" class="next"/>
								</span>
							</div>
						</div>
						
					</div>
				</div>
				<div class="btn1">
					<button type="button" class="write" id="Ticketopen_write">작성하기</button>
				</div>
				<div class="subject">
					<div class="box_select">
						<select name="searchType">
							<option value="total" selected>전체</option>
							<option value="modify">변경</option>
							<option value="cancel">취소</option>
						</select>
						<input type="text" name="schtext" placeholder="티켓오픈소식 검색"></input>
						<span>
							<button type="button" class="btn2">검색</button>
						</span>
						<div class="menu">
							<ul >
								<li><a href="#">등록순</a> &nbsp;&nbsp; |</li>
								<li>&nbsp;&nbsp;<a href="#">오픈일순</a> &nbsp;&nbsp; |</li>
								<li>&nbsp;&nbsp;<a href="#">조회순</a> </li>
							</ul>
						</div>
					</div>
					<div class="openlist">
						<table class="table1" width="100%">
							<tr>
								<td width="20%">
									<strong>티켓오픈일</strong><p/>
									2021.02.09(화) 14:00
								</td width="70%">
								<td>
									<span class="span1"><a href="#"> <캣츠> 40주년 내한공연 - 부산 (Musical CATS) 티켓 오픈 안내</a></span><p/>
									<span class="span2">등록일 2021.02.02</span>
								</td>
								<td width="10%" >
									<a href="#"><img src="./poster/openposter3.jpg" class="img"></a>
								</td>
							</tr>
							<tr>
								<td>
									<strong>티켓오픈일</strong><p/>
									2021.02.04(목) 14:00
								</td>
								<td>
									<span class="span1"><a href="#"> <캣츠> 40주년 내한공연 앙코르 2/4 (목) 2시 서울 마지막 오픈!</a></span><p/>
									<span class="span2">등록일 2021.02.01</span>
								</td>
								<td>
									<a href="#"><img src="./poster/openposter3.jpg" class="img"></a>
								</td>
							</tr>
							<tr>
								<td>
									<strong>티켓오픈일</strong><p/>
									2021.02.02(목) 14:00
								</td>
								<td>
									<span class="span1"><a href="#"> <위키드> - 서울 (Musical Wicked) 설 연휴 티켓 오픈 안내</a></span><p/>
									<span class="span2">등록일 2021.01.27</span>
								</td>
								<td>
									<img src="./poster/musicalposter8.jpg" class="img">
								</td>
							</tr>
							<tr>
								<td>
									<strong>티켓오픈일</strong><p/>
									2021.01.14(목) 16:00
								</td>
								<td>
									<span class="span1"><a href="#"> <몬테크리스토> 10주년 기념 공연 2차 티켓 오픈 안내</a></span><p/>
									<span class="span2">등록일 2021.01.20</span>
								</td>
								<td>
									<a href="#"><img src="./poster/openposter6.jpg" class="img"></a>
								</td>
							</tr>
							<tr>
								<td>
									<strong>티켓오픈일</strong><p/>
									2021.01.07(목) 16:00
								</td>
								<td>
									<span class="span1"><a href="#"> <나와 나타샤와 흰 당나귀> 일부 회차 좌석 추가 오픈 안내</a></span><p/>
									<span class="span2">등록일 2021.01.05</span>
								</td>
								<td>
									<img src="./poster/openposter7.jpg" class="img"></a>
								</td>
							</tr>
							<tr>
								<td>
									<strong>취소</strong><p/>
									2021.01.07(목) 16:00
								</td>
								<td>
									<span class="span1"><a href="#"> [공연취소] <마지막 사건> 월드투어 - 대구 9월 6일(일) 조기 종연</a> </span><p/>
									<span class="span2">등록일 2021.01.05</span>
								</td>
								<td>
									<a href="#"><img src="./poster/openposter8.jpg" class="img"></a>
								</td>
							</tr>
							<tr>
								<td>
									<strong>취소</strong><p/>
									2021.01.07(목) 16:00
								</td>
								<td>
									<span class="span1"><a href="#"> [공연취소] <그날들> 월드투어 - 전주 9월 6일(일) 조기 종연</a> </span><p/>
									<span class="span2">등록일 2021.01.05</span>
								</td>
								<td>
									<a href="#"><img src="./poster/openposter5.jpg" class="img"></a>
								</td>
							</tr>
						</table>
						<div class="paging">
							<a href="#"> << </a>&nbsp;&nbsp;<a href="#"> < </a>&nbsp;<span>&nbsp;&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;&nbsp;3&nbsp;&nbsp;&nbsp;&nbsp;4&nbsp;&nbsp;&nbsp;&nbsp;5&nbsp;&nbsp;&nbsp;&nbsp;6&nbsp;&nbsp;&nbsp;&nbsp;7&nbsp;&nbsp;&nbsp;&nbsp;8&nbsp;&nbsp;&nbsp;&nbsp;9&nbsp;&nbsp;&nbsp;&nbsp;10&nbsp;&nbsp;&nbsp;&nbsp; </span>&nbsp;<a href="#"> > </a>&nbsp;&nbsp;<a href="#"> >> </a>
						</div>
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