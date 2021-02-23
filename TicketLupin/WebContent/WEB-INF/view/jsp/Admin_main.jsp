<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
        <link rel="stylesheet" href="./css/Admin_main.css">

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
				<a href="#" id="n_home">홈</a>
				<a href="#">공연</a>
				<a href="#">랭킹</a>
				<a href="#">티켓오픈소식</a>
				<a href="#">이벤트</a>
				<a href="#">마이 티켓</a>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		<div id="nav_menu_sub_div">
			<ul id="nav_menu_sub">
				<li><a href="#">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">할인쿠폰</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
		</div>
		<hr id="nav_bar_sub">

<!------------------------------------------------------------------------------------------------------------------------------------->

		<section>
			<article>
				<div id="main_myticket_all">
					<div id="main_myticket_set">
						<div id="main_myticket_member" class="main_myticket_member_set">
							<img src="../icon/person.png" id="main_myticket_picture" class="main_myticket_member_all">
							<div id="main_myticket_id_set" class="main_myticket_member_all">
								<div id="main_myticket_id">관리자님 반갑습니다.</div><br>
								<a href="#" class="main_myticket_button"><div class="main_myticekt_button_div">기본정보 관리</div></a>
								<!--<a href="#" class="main_myticket_button"><div class="main_myticekt_button_div">배송지 관리</div></a> -->
							</div>
						</div>
						<div id="main_myticket_amount" class="main_myticket_member_set">
							<a href="#" id="main_myticket_amount_number1_set">
								<div>
									<div class="main_myticket_amount_number">0</div><br>
									<div>댓글내역</div>
								</div>
							</a>
							<a href="#" id="main_myticket_amount_number2_set">
								<div class="main_myticket_amount_number">0</div><br>
								<div>문의내역</div>
							</a>

						</div>
					</div>
					<div id="main_myticket_ticketing">
						<a href="#" class="main_myticket_title_set">회원내역</a>
						<a href="#" class="main_myticket_more_set">더보기 ></a>
						<div id="main_myticket_event_list">
									<ul class="main_myticket_sub_content_set">
										<li>
											<div>10</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
										<li>
											<div>9</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
										<li>
											<div>8</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
										<li>
											<div>7</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
										<li>
											<div>6</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
									</ul>
								</div>
							
					</div>
					<div id="main_myticket_ticketing2">
						<a href="#" class="main_myticket_title_set">공연내역</a>
						<a href="#" class="main_myticket_more_set">더보기 ></a>
						<div id="main_myticket_event_list2">
									<ul class="main_myticket_sub_content_set">
										<li>
											<div>10</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
										<li>
											<div>9</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
										<li>
											<div>8</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
										<li>
											<div>7</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
										<li>
											<div>6</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
									</ul>
								</div>
							
					</div>
					<div id="main_myticket_bottom">
						<div id="main_myticket_event">
							<div class="main_myticket_sub_title_set">
								<div class="main_myticket_title_set">댓글내역</div>
								<a href="#" class="main_myticket_more_set">더보기 ></a>
								<div id="main_myticket_event_list">
									<ul class="main_myticket_sub_content_set">
										<li>
											<div>10</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
										<li>
											<div>9</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
										<li>
											<div>8</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
										<li>
											<div>7</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
										<li>
											<div>6</div>
											<div>[카카오 시청권] 카카오 계정으로 구매...</div>
											<div>2021.02.03</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div id="main_myticket_question">
							<div class="main_myticket_title_set">문의내역</div>
							<a href="#" class="main_myticket_more_set">더보기 ></a>
							<div id="main_myticket_question_list">
								<ul class="main_myticket_sub_content_set">
									<li>
										<div>10</div>
										<div>[카카오 시청권] 카카오 계정으로 구매...</div>
										<div>2021.02.03</div>
									</li>
									<li>
										<div>9</div>
										<div>[카카오 시청권] 카카오 계정으로 구매...</div>
										<div>2021.02.03</div>
									</li>
									<li>
										<div>8</div>
										<div>[카카오 시청권] 카카오 계정으로 구매...</div>
										<div>2021.02.03</div>
									</li>
									<li>
										<div>7</div>
										<div>[카카오 시청권] 카카오 계정으로 구매...</div>
										<div>2021.02.03</div>
									</li>
									<li>
										<div>6</div>
										<div>[카카오 시청권] 카카오 계정으로 구매...</div>
										<div>2021.02.03</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</article>
		</section>

<!------------------------------------------------------------------------------------------------------------------------------------->

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
