<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 오픈 공지 작성</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Ticketopen_write_admin.css">
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
		<section>
			<article>
				<div class="open_notice">
					<h2>
						티켓 오픈 공지 작성
					</h2>
					<table class="open_notice_table" style="border:1px solid; border-collapse:collapse;">
						<colgroup>
							<col width="50px"/>
							<col width="35px"/>
							<col width="415px">
							<col width="85px">
							<col width="305px">
							<col width="70px">
							<col width="60px">
						</colgroup>
						<tr>
							<th style="border:1px solid;">
								제목
							</th>
							<td colspan="4" style="border:1px solid;">
								<input type="text" name="title" class="title" maxlength="100" style="width:99%; border:0px; font-size:16px;">
							</td>
							<th style="border:1px solid;">
								카테고리
							</th>
							<td style="border:1px solid;">
								<center>
									<select class="category" style="border:0px; font-size:15px;">
										<option>
											티켓오픈일
										</option>
										<option>
											변경
										</option>
										<option>
											삭제
										</option>
									</select>
								</center>
							</td>
						</tr>
						<tr>
							<th colspan="2" style="border:1px solid;">
								오픈일정
							</th>
							<td style="border:1px solid;">
								<center>
									<input type="datetime-local" class="date">
								</center>
							</td>
							<th style="border:1px solid;">
								공연기간
							</th>
							<td colspan="3" style="border:1px solid;">
								<center>
									<input type="datetime-local" class="date">
								</center>
							</td>
						</tr>
						<tr style="border:1px solid;">
							<td	colspan="7">
								<center>
									<textarea class="content" style="width:99%; height:500px; border:0px; font-size:15px; resize:none; overflow-x:hidden;" placeholder="기본 정보를 입력해주세요."></textarea>
								</center>
							</td>
						</tr>
						<tr style="border:1px solid;">
							<td	colspan="7">
								<center>
									<textarea class="content" style="width:99%; height:500px; border:0px; font-size:15px; resize:none; overflow-x:hidden;" placeholder="공연 소개를 입력해주세요."></textarea>
								</center>
							</td>
						</tr>
						<tr style="border:1px solid;">
							<td	colspan="7">
								<center>
									<textarea class="content" style="width:99%; height:500px; border:0px; font-size:15px; resize:none; overflow-x:hidden;" placeholder="할인 정보를 입력해주세요."></textarea>
								</center>
							</td>
						</tr>
						<tr style="border:1px solid;">
							<td	colspan="7">
								<center>
									<textarea class="content" style="width:99%; height:500px; border:0px; font-size:15px; resize:none; overflow-x:hidden;" placeholder="기획사 정보를 입력해주세요."></textarea>
								</center>
							</td>
						</tr>
						<tr>
							<th colspan="2" style="border:1px solid;">
								대표 사진
							</th>
							<td style="border:1px solid;">
								<input type="file" class="file" style="border:0px; font-size:14px;">
							</td>
							<th style="border:1px solid;">
								본문 사진
							</th>
							<td colspan="2" style="border:1px solid;">
								<input type="file" class="file" style="border:0px; font-size:14px;">
							</td>
							<td align="center" style="font-weight:bold;">
								공개여부&nbsp;<input type="checkbox" value="pub">
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