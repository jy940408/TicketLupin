<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<link rel="stylesheet" type"text/css" href="<%=request.getContextPath() %>/css/Myticket_main.css">
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Myticket_main.js"></script>
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
				<a href="#" id="main_nav_home">홈</a>
				<a href="#" id="main_nav_concert">공연</a>
				<a href="#" id="main_nav_ranking">랭킹</a>
				<a href="#" id="main_nav_news">티켓오픈소식</a>
				<a href="#" id="main_nav_event">이벤트</a>
				<a href="#" id="main_nav_myticket">마이 티켓</a>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		<div id="nav_menu_sub_event_div" class="main_nav_all">
			<ul id="nav_menu_sub_event" style="margin:0px;">
				<li><a href="#">전체 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">당첨자 발표</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
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
<!------------------------------------------------------------------------------------------------------------------------------------->

		<section>
			<article>
				<div id="main_myticket_all">
					<div id="main_myticket_set">
						<div id="main_myticket_member" class="main_myticket_member_set">
							<img src="../icon/person.png" id="main_myticket_picture" class="main_myticket_member_all">
							<div id="main_myticket_id_set" class="main_myticket_member_all">
								<div id="main_myticket_id">cmw9474@naver.com</div><br>
								<a href="#" class="main_myticket_button"><div class="main_myticekt_button_div">기본정보 관리</div></a>
								<a href="#" class="main_myticket_button"><div class="main_myticekt_button_div">배송지 관리</div></a>
							</div>
						</div>
						<div id="main_myticket_amount" class="main_myticket_member_set">
							<a href="#" id="main_myticket_amount_number1_set">
								<div>
									<div class="main_myticket_amount_number">0</div><br>
									<div>예매내역</div>
								</div>
							</a>
							<a href="#" id="main_myticket_amount_number2_set">
								<div class="main_myticket_amount_number">0</div><br>
								<div>할인쿠폰</div>
							</a>
							<a href="#" id="main_myticket_amount_number3_set">
								<div class="main_myticket_amount_number">0</div><br>
								<div>공연예매권</div>
							</a>
						</div>
					</div>
					<div id="main_myticket_ticketing">
						<a href="#" class="main_myticket_title_set">최근 예매/취소</a>
						<a href="#" class="main_myticket_more_set">더보기 ></a>
						<div id="main_myticket_ticketing_list">
							<ul id="main_myticket_ticketing_list_ul">
								<li>
									<div class="main_myticket_ticketing_list_num">1</div>
									<div class="main_myticket_ticketing_list_title"><a href="#">대충 뮤지컬 제목이라는 뜻</a></div>
									<div class="main_myticket_ticketing_list_res">예매&nbsp;&nbsp;&nbsp;2021-02-24</div>
									<div class="main_myticket_ticketing_list_date">공연 날짜&nbsp;&nbsp;&nbsp;2021-02-25</div>
								</li>
								<li>
									<div class="main_myticket_ticketing_list_num">1</div>
									<div class="main_myticket_ticketing_list_title"><a href="#">대충 뮤지컬 제목이라는 뜻</a></div>
									<div class="main_myticket_ticketing_list_res">예매&nbsp;&nbsp;&nbsp;2021-02-24</div>
									<div class="main_myticket_ticketing_list_date">공연 날짜&nbsp;&nbsp;&nbsp;2021-02-25</div>
								</li>
								<li>
									<div class="main_myticket_ticketing_list_num">1</div>
									<div class="main_myticket_ticketing_list_title"><a href="#">대충 뮤지컬 제목이라는 뜻</a></div>
									<div class="main_myticket_ticketing_list_res">예매&nbsp;&nbsp;&nbsp;2021-02-24</div>
									<div class="main_myticket_ticketing_list_date">공연 날짜&nbsp;&nbsp;&nbsp;2021-02-25</div>
								</li>
								<li>
									<div class="main_myticket_ticketing_list_num">1</div>
									<div class="main_myticket_ticketing_list_title"><a href="#">대충 뮤지컬 제목이라는 뜻</a></div>
									<div class="main_myticket_ticketing_list_res">예매&nbsp;&nbsp;&nbsp;2021-02-24</div>
									<div class="main_myticket_ticketing_list_date">공연 날짜&nbsp;&nbsp;&nbsp;2021-02-25</div>
								</li>
								<li>
									<div class="main_myticket_ticketing_list_num">1</div>
									<div class="main_myticket_ticketing_list_title"><a href="#">대충 뮤지컬 제목이라는 뜻</a></div>
									<div class="main_myticket_ticketing_list_res">예매&nbsp;&nbsp;&nbsp;2021-02-24</div>
									<div class="main_myticket_ticketing_list_date">공연 날짜&nbsp;&nbsp;&nbsp;2021-02-25</div>
								</li>
							</ul>
						</div>
					</div>
					<div id="main_myticket_bottom">
						<div id="main_myticket_event">
							<div class="main_myticket_sub_title_set">
								<div class="main_myticket_title_set">최근 참여 이벤트</div>
								<a href="#" class="main_myticket_more_set">더보기 ></a>
								<div id="main_myticket_event_list">
									<ul class="main_myticket_sub_content_set">
										
										<li>
											<div class="main_myticket_sub_content_num">9</div>
											<div class="main_myticket_sub_content_title">[카카오 시청권] 카카오 계정으로 구매...</div>
											<div class="main_myticket_sub_content_date">2021.02.03</div>
										</li>
										<li>
											<div class="main_myticket_sub_content_num">9</div>
											<div class="main_myticket_sub_content_title">[카카오 시청권] 카카오 계정으로 구매...</div>
											<div class="main_myticket_sub_content_date">2021.02.03</div>
										</li>
										<li>
											<div class="main_myticket_sub_content_num">9</div>
											<div class="main_myticket_sub_content_title">[카카오 시청권] 카카오 계정으로 구매...</div>
											<div class="main_myticket_sub_content_date">2021.02.03</div>
										</li>
										<li>
											<div class="main_myticket_sub_content_num">9</div>
											<div class="main_myticket_sub_content_title">[카카오 시청권] 카카오 계정으로 구매...</div>
											<div class="main_myticket_sub_content_date">2021.02.03</div>
										</li>
										<li>
											<div class="main_myticket_sub_content_num">9</div>
											<div class="main_myticket_sub_content_title">[카카오 시청권] 카카오 계정으로 구매...</div>
											<div class="main_myticket_sub_content_date">2021.02.03</div>
										</li>
										<li>
											<div class="main_myticket_sub_content_num">9</div>
											<div class="main_myticket_sub_content_title">[카카오 시청권] 카카오 계정으로 구매...</div>
											<div class="main_myticket_sub_content_date">2021.02.03</div>
										</li>
										<li>
											<div class="main_myticket_sub_content_num">9</div>
											<div class="main_myticket_sub_content_title">[카카오 시청권] 카카오 계정으로 구매...</div>
											<div class="main_myticket_sub_content_date">2021.02.03</div>
										</li>
										
									</ul>
								</div>
							</div>
						</div>
						<div id="main_myticket_question">
							<div class="main_myticket_title_set">최근 1:1문의</div>
							<a href="#" class="main_myticket_more_set">더보기 ></a>
							<div id="main_myticket_question_list">
								<ul class="main_myticket_sub_content_set">
									
									<li>
										<div class="main_myticket_question_list_num">9</div>
										<div class="main_myticket_question_list_title">[카카오 시청권] 카카오 계정으로 구매...</div>
										<div class="main_myticket_question_list_date">2021.02.03</div>
									</li>
									<li>
										<div class="main_myticket_question_list_num">9</div>
										<div class="main_myticket_question_list_title">[카카오 시청권] 카카오 계정으로 구매...</div>
										<div class="main_myticket_question_list_date">2021.02.03</div>
									</li>
									<li>
										<div class="main_myticket_question_list_num">9</div>
										<div class="main_myticket_question_list_title">[카카오 시청권] 카카오 계정으로 구매...</div>
										<div class="main_myticket_question_list_date">2021.02.03</div>
									</li>
									<li>
										<div class="main_myticket_question_list_num">9</div>
										<div class="main_myticket_question_list_title">[카카오 시청권] 카카오 계정으로 구매...</div>
										<div class="main_myticket_question_list_date">2021.02.03</div>
									</li>
									<li>
										<div class="main_myticket_question_list_num">9</div>
										<div class="main_myticket_question_list_title">[카카오 시청권] 카카오 계정으로 구매...</div>
										<div class="main_myticket_question_list_date">2021.02.03</div>
									</li>
									<li>
										<div class="main_myticket_question_list_num">9</div>
										<div class="main_myticket_question_list_title">[카카오 시청권] 카카오 계정으로 구매...</div>
										<div class="main_myticket_question_list_date">2021.02.03</div>
									</li>
									<li>
										<div class="main_myticket_question_list_num">9</div>
										<div class="main_myticket_question_list_title">[카카오 시청권] 카카오 계정으로 구매...</div>
										<div class="main_myticket_question_list_date">2021.02.03</div>
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
