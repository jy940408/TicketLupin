<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>공연 작성</title>
		<!-- jQuery -->
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.timepicker.js"></script>

		<!-- JS -->
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/timePicker.js"></script>

		<!-- core CSS -->
		<link rel="stylesheet" href="../css/jquery.timepicker.css">
		<link rel="stylesheet" href="../css/Concert_write_admin.css">
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
				<a href="#">관리자</a>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		<section>
			<article>
				<div class="open_notice">
					<h2>
						공연 작성
					</h2>
					<table class="open_notice_table" style="border:1px solid; border-collapse:collapse;">
						<colgroup>
							<col width="50px">
							<col width="35px">
							<col width="20px">
							<col width="395px">
							<col width="85px">
							<col width="10px">
							<col width="50px">
							<col width="5px">
							<col width="85px">
							<col width="60px">
							<col width="40px">
							<col width="45px">
							<col width="50px">
							<col width="70px">
							
						</colgroup>
						<tr>
							<th style="border:1px solid;">
								제목
							</th>
							<td colspan="5" style="border:1px solid;">
								<input type="text" class="title" maxlength="100" style="width:99%; border:0px; font-size:16px;">
							</td>
							<th style="border:1px solid;">
								장르
							</th>
							<td colspan="3" style="border:1px solid;">
								<center>
									<select class="genre" style="border:0px; font-size:15px; text-align-last:center;">
										<option>
											오리지널/내한공연
										</option>
										<option>
											라이선스
										</option>
										<option>
											창작뮤지컬
										</option>
										<option>
											넌버벌 퍼포먼스
										</option>
										<option>
											패키지공연
										</option>
									</select>
								</center>
							</td>
							<th colspan="2" style="border:1px solid;">
								관람등급
							</th>
							<td colspan="2" style="border:1px solid;">
								<select class="rating" style="border:0px; font-size:15px; text-align-last:center;">
									<option>
										전체관람가
									</option>
									<option>
										12세 관람가
									</option>
									<option>
										15세 관람가
									</option>
									<option>
										청소년 관람불가
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th colspan="3" style="border:1px solid;">
								예매가능 날짜
							</th>
							<td colspan="5" style="border:1px solid;">
								<center>
									<input type="date" class="date">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="date" class="date">
								</center>
							</td>
							<th style="border:1px solid;">
								공연회차
							</th>
							<td  colspan="2" style="border:1px solid;">
								<div id="platanusBox">
									<input id="platanusTime" class="round" name="platanusTime" onclick="javascript:fn_timePicker(this);" style="border:0px; width:100px; font-size:15px; text-align:center;">
								</div>
							</td>
							<th colspan="2" style="border:1px solid;">
								공연장 위치
							</th>
							<td style="border:1px solid;">
								<div class="map">
									<center>
										<a href="https://map.kakao.com/" onclick="window.open(this.href, '_blank', 'width=850px,height=800px,toolbars=no,scrollbars=no');return false;">
											<img src="../img/map_btn.png" style="width:20px">
										</a>
									</center>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="14">
								<center>
									<textarea class="content" style="width:99%; height:500px; border:0px; font-size:15px; resize:none; overflow-x:hidden;" placeholder="내용을 입력해주세요."></textarea>
								</center>
							</td>
						</tr>
						<tr>
							<th colspan="2" style="border:1px solid;">
								제목 사진
							</th>
							<td colspan="2" style="border:1px solid;">
								<input type="file" class="file" style="border:0px; font-size:14px;">
							</td>
							<th style="border:1px solid;">
								본문 사진
							</th>
							<td colspan="9" style="border:1px solid;">
								<input type="file" class="file" style="border:0px; font-size:14px;">
							</td>
						</tr>
					</table>
					<div class="reg">
						<button class="reg_btn">
							등록
						</button>
					</div>
					<div class="list">
						<button class="list_btn">
							목록으로
						</button>
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