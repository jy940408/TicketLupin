<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/Main.css">
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Main.js"></script>
	</head>
	<body>
		<header>
			<div id="h_title">
				<div id="h_title_inner">
					<span id="h_top_menu">
						<ul id="h_top_menu_ul">
							<li><a href="<%=request.getContextPath()%>/Member/MemberLogin.do">로그인&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
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
				<a href="<%=request.getContextPath()%>/Main/MainPage.do" id="main_nav_home">홈</a>
				<a href="#" id="main_nav_concert">공연</a>
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
				<div id="main_poster_all">
					<div id="main_poster_div">
						<div class="main_poster_content">
							<ul>
								<li><a href="#"><img src="../poster/musicalposter1.jpg" id="main_poster_slider_poster1"></a></li>
								<li><a href="#"><img src="../poster/musicalposter2.jpg" id="main_poster_slider_poster2"></a></li>
								<li><a href="#"><img src="../poster/musicalposter3.jpg" id="main_poster_slider_poster3"></a></li>
								<li><a href="#"><img src="../poster/musicalposter4.jpg" id="main_poster_slider_poster4"></a></li>
								<li><a href="#"><img src="../poster/musicalposter5.jpg" id="main_poster_slider_poster5"></a></li>
								<li><a href="#"><img src="../poster/musicalposter6.jpg" id="main_poster_slider_poster6"></a></li>
								<li><a href="#"><img src="../poster/musicalposter7.jpg" id="main_poster_slider_poster7"></a></li>
								<li><a href="#"><img src="../poster/musicalposter8.jpg" id="main_poster_slider_poster8"></a></li>
								<li><a href="#"><img src="../poster/musicalposter9.jpg" id="main_poster_slider_poster9"></a></li>
								<li><a href="#"><img src="../poster/musicalposter10.jpg" id="main_poster_slider_poster10"></a></li>
							</ul>
						</div>
					</div>
				</div>
				<br>
				
				<div id="main_comingsoon_div">
					<span id="main_comingsoon_ment">잠시 후, 오픈 예정!</span>
					<a href="#" id="main_comingsoon_more">더보기 ></a><br>
					<div id="main_comingsoon">
						<img src="../poster/musicalposter1.jpg" id="main_open_poster1">
						<img src="../poster/musicalposter2.jpg" id="main_open_poster2">
						<img src="../poster/musicalposter3.jpg" id="main_open_poster3">
						<img src="../poster/musicalposter4.jpg" id="main_open_poster4">
						<div id="main_comingsoon_time" style="font-size:25px;"></div>
					</div>
				</div>
				<br>
				
				<div id="main_ads">
					<img src="../ads/musicalads2.png" id="main_musicalads2">
				</div>
				<br>
				<div id="main_notice_set">
					<div id="main_opennews" class="main_open_ranking">
						<span id="main_opennews_ment">티켓 오픈 소식</span>
						<a href="#" id="main_opennews_more">더보기></a>
						<div class="main_notice_class" id="main_opennews_set">
							<div id="main_opennews1">
								<span class="main_notice_title">뮤지컬 〈위키드〉－서울（Musical Wicked）설 연휴...</span><br>
								<span class="main_notice_date">[오픈]21.01.26(목) 20:00</span>
							</div>
							<hr class="main_opennews_bar">
							<div id="main_opennews2">
								<span class="main_notice_title">티켓 오픈 소식 테스트용 공지</span><br>
								<span class="main_notice_date">[오픈]21.01.26(목) 20:00</span>
							</div>
							<hr class="main_opennews_bar">
							<div id="main_opennews3">
								<span class="main_notice_title">티켓 오픈 소식 테스트용 공지</span><br>
								<span class="main_notice_date">[오픈]21.01.26(목) 20:00</span>
							</div>
							<hr class="main_opennews_bar">
							<div id="main_opennews4">
								<span class="main_notice_title">티켓 오픈 소식 테스트용 공지</span><br>
								<span class="main_notice_date">[오픈]21.01.26(목) 20:00</span>
							</div>
							<hr class="main_opennews_bar">
							<div id="main_opennews5">
								<span class="main_notice_title">티켓 오픈 소식 테스트용 공지</span><br>
								<span class="main_notice_date">[오픈]21.01.26(목) 20:00</span>
							</div>
						</div>
					</div>
					<div id="main_ranking" class="main_open_ranking">
						<span id="main_ranking_ment">랭킹</span>
						<a href="#" id="main_ranking_more">더보기></a>
						<div class="main_notice_class" id="main_ranking_set">
							<div class="main_ranking_class" id="main_ranking1">
								<span class="main_ranking_number">1</span>
								<img src="../poster/musicalposter1.jpg" id="main_ranking_poster1">
								<div class="main_ranking_text">
									<div class="main_ranking_name">검은 사제들</div><br>
									<div class="main_ranking_date">2020.01.01</div><br>
									<div class="main_ranking_place">대학로 유니플렉스 1관</div>
								</div>
							</div>
							<hr class="main_ranking_bar">
							<div class="main_ranking_class" id="main_ranking2">
								<span class="main_ranking_number">2</span>
								<img src="../poster/musicalposter2.jpg" id="main_ranking_poster2">
								<div class="main_ranking_text">
									<div class="main_ranking_name">쿠로이 저택엔 누가 살고 있을까?</div><br>
									<div class="main_ranking_date">2020.01.01</div><br>
									<div class="main_ranking_place">플러스씨어터(구. 컬처스페이스 엔유)</div>
								</div>
							</div>
							<hr class="main_ranking_bar">
							<div class="main_ranking_class" id="main_ranking3">
								<span class="main_ranking_number">3</span>
								<img src="../poster/musicalposter3.jpg" id="main_ranking_poster3">
								<div class="main_ranking_text">
									<div class="main_ranking_name">유월</div><br>
									<div class="main_ranking_date">2020.01.01</div><br>
									<div class="main_ranking_place" id="main_ranking_place3">경기아트센터 대극장</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<br>
				<div id="main_pick">
					<div id="main_pick_ment">티켓루팡 PICK!</div>
					<div class="main_pick_class" id="main_pick1">
						<img src="../poster/musicalposter1.jpg" id="main_pick_poster1">
						<div class="main_pick_name">검은 사제들</div><br>
						<div class="main_pick_place">대학로 유니플렉스 1관</div>
						<div class="main_pick_date">2021.02.18-2021.03.01</div>
					</div>
					<div class="main_pick_class" id="main_pick2">
						<img src="../poster/musicalposter2.jpg" id="main_pick_poster2">
						<div class="main_pick_name">쿠로이 저택엔 누가 살...</div><br>
						<div class="main_pick_place">플러스씨어터(구. 컬처스페...</div>
						<div class="main_pick_date">2021.02.18-2021.03.01</div>
					</div>
					<div class="main_pick_class" id="main_pick3">
						<img src="../poster/musicalposter3.jpg" id="main_pick_poster3">
						<div class="main_pick_name">유월</div><br>
						<div class="main_pick_place">경기아트센터 대극장</div>
						<div class="main_pick_date">2021.02.18-2021.03.01</div>
					</div>
					<div class="main_pick_class" id="main_pick4">
						<img src="../poster/musicalposter4.jpg" id="main_pick_poster4">
						<div class="main_pick_name">에어포트 베이비</div><br>
						<div class="main_pick_place">신한카드 FAN(판)스퀘어 라...</div>
						<div class="main_pick_date">2020.11.11-2021.01.31</div>
					</div>
				</div>
				<hr class="main_bar" id="main_bar_top">
				<div id="main_notice">
					<span class="main_notice_ment">공지사항</span>
					<span class="main_notice_ment"><a href="#" id="main_notice_title">[시스템작업] 01.24(일) 현대카드 전산시스템 작업 안내</a></span>
					<span class="main_notice_ment">2021.01.22</span>
				</div>
				<hr class="main_bar" id="main_bar_bottom">
				<div id="main_last">
					<span class="main_bottom_ment"><img src="../icon/lupinlogo.png" id="main_logo"></span>
					<span class="main_bottom_ment">
						<span class="main_bottom_tagset">예매문의(1234-1234)</span>
						<a href="#" class="main_bottom_tagset">티켓판매제휴&nbsp;&nbsp;&nbsp;&nbsp;</a>
						<a href="#" class="main_bottom_tagset">예매가이드&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</span>
				</div>
				<hr class="main_bar" id="main_bar_bottom">
			</article>
		</section>
		<footer>
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
		<script>
			function showClock(){
				var currentDate = new Date();
				var main_comingsoon_time = document.getElementById("main_comingsoon_time");
				var apm = currentDate.getHours();
				if(apm < 12){
					apm="오전 ";
				}
				else{
					apm="오후 ";
				}
				
				var msg = "현재시각 <br>";
				msg += new Date().getFullYear() + "년 "; 
				msg += new Date().getMonth() + "월 ";
				msg += new Date().getDate() + "일<br>";
				msg += apm + (currentDate.getHours()) + "시 ";
				msg += currentDate.getMinutes() + "분 ";
				msg += currentDate.getSeconds() + "초";
				
				main_comingsoon_time.innerHTML = msg;
				
			}
			showClock();
			setInterval(showClock,1000);
		</script>
	</body>
</html>