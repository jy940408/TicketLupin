<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>문의 글 상세</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Myask_view.css">
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
				<div class="notice">
					<p class="tit">
						<span class="label">고객센터</span>
					</p>
					<div class="cont_tbl">
						<table class="table1" border="1">
							<tr>
								<td width="140px"><a href="#">공지사항</a></td>
								<td width="140px"><a href="#">이용안내</a></td>
								<td width="140px"><a href="#">FAQ</a></td>
								<td width="140px"><a href="#">나의 문의 내역</a></td>
							</tr>
						</table>
					</div>
					<div class="notice_info">
						<table class="notice_table1">
							<colgroup>
								<col style="width:60px">
								<col style="width:90px">
								<col style="width:800px">
								<col style="width:140px">							
							</colgroup>
							<tr class="notice_table1_tr">
								<td style="text-align:center">
									252
								</td>
								<td style="text-align:center">
									대기중
								</td>
								<td>
									&nbsp; 결제 관련해서 문의합니다.
								</td>
								<td style="text-align:center">
									2020.12.21
								</td>
							</tr>
							<tr class="notice_table1_view1">
								<td colspan="4" style="border-bottom:1px solid #ebebeb">
									<div class="box_view">
										<p><span style="font-size: 11pt;">안녕하세요. 결제가 잘 되었는지 보려면 어디 들어가서 확인해야되나요?</span></p>
										<br/>
										<br/>
										<br/>
										<div class="modify">
											<button class="modify_btn">
												수정하기
											</button>
											<button class="remove_btn">
												제거하기
											</button>
										</div>
									</div>
								</td>
							</tr>
							<tr class="notice_table1_view2">
								<td colspan="4" style="border-bottom:1px solid #ebebeb">
									<div class="box_view" style="height:100px">
										<br/>
										<br/>
										<br/>
									</div>
								</td>
							</tr>
						</table>
						<table class="notice_table2">
							<colgroup>
								<col style="width:80px">
								<col style="width:90px">
								<col style="width:620px">
								<col style="width:70px">
								<col style="width:140px">												
							</colgroup>
							<tr>
								<td style="text-align:center">
									이전글
								</td>
								<td style="text-align:center">
									대기중
								</td>
								<td>
									<a href="#" style="text-decoration:none; color:black;">
										&nbsp; 공연 언제하나요?
									</a>
								</td>
								<td>
								</td>
								<td style="text-align:center">
									2020.12.24
								</td>
							</tr>
							<tr>
								<td style="text-align:center">
									다음글
								</td>
								<td style="text-align:center">
									답변완료
								</td>
								<td>
									<a href="#" style="text-decoration:none; color:black;">
										&nbsp; 티켓 마감 언제까지인가요?
									</a>
								</td>
								<td>
								</td>
								<td style="text-align:center">
									2020.12.10
								</td>
							</tr>
						</table>
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