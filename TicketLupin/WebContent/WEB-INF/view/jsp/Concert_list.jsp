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
<!------------------------------------------------------------------------------------------------------------------------>
		<section>
			<article>
				<div id="main_concert_list_all">
					<div id="main_concert_list_search_all">
						<div id="main_concert_list_search_top_all">
							<div id="main_concert_list_search_textbox" class="main_concert_list_search_top">
								<form action="<%request.getContextPath()%>/Show/ShowSearch.do">
								<input type="text" name="q" placeholder="검색어를 입력하세요">
								<button type="submit">검색</button>
								</form>
							</div>
							<div id="main_concert_list_search_manage_write" class="main_concert_list_search_top">
								<a href="<%=request.getContextPath()%>/Show/ShowWrite.do"><div>작성하기</div></a>
							</div>
						</div>
						<div id="main_concert_list_search_tag">
							<div id="main_concert_list_search_tag_genre" class="main_concert_list_search_tag_sector">
								<div>
									장르별
								</div>
								<ul>
									<li><input type="checkbox" name="genre" value="genreall" onclick="getCheckboxValue('genre')"><span id="genreall">전체</span></li>
									<li><input type="checkbox" name="genre" value="original" onclick="getCheckboxValue('genre')"><span id="original">오리지널/내한공연</span></li>
									<li><input type="checkbox" name="genre" value="license" onclick="getCheckboxValue('genre')"><span id="license">라이선스</span></li>
									<li><input type="checkbox" name="genre" value="creation" onclick="getCheckboxValue('genre')"><span id="creation">창작뮤지컬</span></li>
									<li><input type="checkbox" name="genre" value="nonverbal" onclick="getCheckboxValue('genre')"><span id="nonverbal">넌버벌 퍼포먼스</span></li>
									<li><input type="checkbox" name="genre" value="package" onclick="getCheckboxValue('genre')"><span id="package">패키지공연</span></li>
								</ul>
							</div>
							<div id="main_concert_list_search_tag_place" class="main_concert_list_search_tag_sector">
								<div>
									지역별
								</div>
								<ul>
									<li><input type="checkbox" name="place" value="placeall" onclick="getCheckboxValue('place')"><span id="placeall">전체</span></li>
									<li><input type="checkbox" name="place" value="seoul" onclick="getCheckboxValue('place')"><span id="seoul">서울</span></li>
									<li><input type="checkbox" name="place" value="incheon" onclick="getCheckboxValue('place')"><span id="incheon">경기/인천</span></li>
									<li><input type="checkbox" name="place" value="daejeon" onclick="getCheckboxValue('place')"><span id="daejeon">대전/충청/강원</span></li>
									<li><input type="checkbox" name="place" value="busan" onclick="getCheckboxValue('place')"><span id="busan">부산/대구/경상</span></li>
									<li><input type="checkbox" name="place" value="gwangju" onclick="getCheckboxValue('place')"><span id="gwangju">광주/전라/제주</span></li>
								</ul>
							</div>
							<div id="main_concert_list_search_tag_sold" class="main_concert_list_search_tag_sector">
								<div>
									판매상태
								</div>
								<ul>
									<li><input type="checkbox" name="sold" value="soldall" onclick="getCheckboxValue('sold')"><span id="soldall">전체</span></li>
									<li><input type="checkbox" name="sold" value="now" onclick="getCheckboxValue('sold')"><span id="now">판매 중</span></li>
									<li><input type="checkbox" name="sold" value="soon" onclick="getCheckboxValue('sold')"><span id="soon">판매 예정</span></li>
								</ul>
							</div>
						</div>
						<div id="main_concert_list_search_tag_checked">
							
						</div>
					</div>
					<div id="main_concert_musical_list_all">
						<div id="main_concert_musical_list_order">
							<a href="#"><div>인기순</div></a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a href="?q=&s=sopendate&p="><div>공연임박순</div></a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a href="?q=&s=sregdate&p="><div>최신순</div></a>
						</div>
						<div id="main_concert_musical_list">
							<ul>
								<c:forEach var="l" items="${list}">
								<li><a href="#">
									<div class="main_concert_musical_detail">
										<img src="../poster/musicalposter1.jpg" class="main_concert_musical_detail_poster">
										<div class="main_concert_musical_detail_title">
											${l.stitle }
										</div>
										<div class="main_concert_musical_detail_date">
											${l.sopendate } - ${l.senddate}
										</div>
										<div class="main_concert_musical_detail_place">
											${l.sroadaddress}
										</div>
										<div class="main_concert_musical_detail_sold">
											판매중
										</div>
									</div>
								</a></li>
								</c:forEach>
							</ul>
						</div>
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