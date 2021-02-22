<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>./js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>./js/Nav_event.js"></script>
		<script src="<%=request.getContextPath() %>/js/Faq_list.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Faq_list.css">
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
				<a href="#" id="main_nav_admin">마이티켓</a>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		
		
		<div class="wrap_nav" id="wrap_nav" style="display:none;">
			<div id="nav_menu_sub_div">
				<ul id="nav_menu_sub">
					<li><a href="#">마이홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><a href="#">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><a href="#">마이찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><a href="#">할인쿠폰</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
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
				<div class="cont">
					<div class="tit">
						<span class="label">고객센터</span>
						<span class="btn">
							<button type="button" class="qwrite">1:1 문의하기</button>
						</span>
					</div>
					<div class="cont_tb1">
						<table border="1"  class="table1">
							<tr>
								<td width="140px"><a href="#">공지사항</a></td>
								<td width="140px"><a href="#">이용안내</a></td>
								<td width="140px"><a href="#">FAQ</a></td>
								<td width="140px"><a href="#">나의 문의 내역</a></td>
							</tr>
						</table>
					</div>
					<div class="sch" style="display:block">
						<label>자주하는 질문 검색</label>
						<span class="wrap_input">
							<input type="text" name="schText" maxlength="30" class="inputTypefaq">
						</span>
						<span class="green"><button class="schbtn">검색</button>
					</div>
					<div class="categoryBox">
						<table border="1" class="mid">
							<tr>
								<td width="168"><a href="#">전체</a></td>
								<td width="168"><a href="#">티켓예매</a></td>
								<td width="168"><a href="#">취소/환불</a></td>
								<td width="168"><a href="#">티켓수령</a></td>
								<td width="168"><a href="#">회원</a></td>
								<td width="168"><a href="#">이벤트</a></td>
							</tr>
						</table>
					</div>
					<div class="box_customer">
						<div class="box_select">
							<span class="best">자주하는 질문 BEST 10</span>
							<span class="btn">
								<button type="button" class="qwrite">작성하기</button>
							</span>
						</div>
						<table class="tb2" style="width:100%">
							<caption class="hide"></caption>
							<colgroup>
								<col style="width:56px">
								<col style="width:82px">
								<col style="width:600px">
							</colgroup>
							<tbody class="listBox">
								<tr>
									<td>1</td>
									<td>티켓예매</td>
									<td class="txt_left title" style="cursor:pointer">공연 예매 후 좌석을 변경할 수 있나요?</td>
								</tr>
								<tr class="comment color" style="display:none">
									<td class="td_div" style="padding:0px;">
										<div>└<div>
									</td>
									<td class="txt_left" colspan="2" >
										<p style="margin-bottom:0px;">예매 완료 후에는 좌석을 변경할 수 없습니다.</p>
										<p style="margin-bottom:0px;">변경을 원하시는 경우 [마이티켓 > 예매/취소] 메뉴에서 해당 예매 건을 취소하시고 재 예매하셔야 합니다.</p>
										<p style="margin-bottom:16px;">예매 건을 취소하는 경우 예매수수료, 취소수수료는 멜론티켓 수수료 정책에 따라 발생합니다.</p>
										<p style="text-align:right; margin:20px;">
											<button type="button" style="margin-right:10px;">제거하기</button>
											<button type="button">수정하기</button>
										</p>
									</td>
								</tr>
								<tr>
									<td>2</td>
									<td>티켓예매</td>
									<td class="txt_left title"><a href="#">예매 완료 후에도 쿠폰을 적용할 수 있나요?</a></td>
								</tr>
								<tr class="comment color" style="display:none">
									<td class="td_div">
										<div>└<div>
									</td>
									<td class="txt_left" colspan="2">
										<p style="margin-bottom:0px;">이미 예매/결제가 완료된 건에는 쿠폰을 적용할 수 없습니다.</p>
										<p style="margin-bottom:16px;">마이티켓의 예매/취소 내역에서 예매하셨던 내역을 취소한 후 재 예매하실 때에만 적용이 가능합니다.</p>
										<p style="text-align:right; margin:20px;">
											<button type="button" style="margin-right:10px;">제거하기</button>
											<button type="button">수정하기</button>
										</p>
									</td>
								</tr>
								<tr>
									<td>3</td>
									<td>티켓예매</td>
									<td class="txt_left"><a href="#">현금영수증은 어떻게 발행 되나요?</a></td>
								</tr>
								<tr class="comment color" style="display:none">
									<td class="td_div">
										<div>└<div>
									</td>
									<td class="txt_left" colspan="2">
										<p style="margin-bottom:0px;">이미 예매/결제가 완료된 건에는 쿠폰을 적용할 수 없습니다.</p>
										<p style="margin-bottom:16px;">마이티켓의 예매/취소 내역에서 예매하셨던 내역을 취소한 후 재 예매하실 때에만 적용이 가능합니다.</p>
									</td>
								</tr>
								<tr>
									<td>4</td>
									<td>티켓예매</td>
									<td class="txt_left"><a href="#">배송지 변경이 가능한가요?</a></td>
								</tr>
								<tr>
									<td>5</td>
									<td>취소/환불</td>
									<td class="txt_left"><a href="#">티켓 취소를 하려면 어떻게 해야 되나요?</a></td>
								</tr>
								<tr>
									<td>6</td>
									<td>취소/환불</td>
									<td class="txt_left"><a href="#">카드로 결제했어요. 취소면 언제 환불 되나요?</a></td>
								</tr>
								<tr>
									<td>7</td>
									<td>티켓수령</td>
									<td class="txt_left"><a href="#">배송된 티켓은 본인수령만 가능한가요?</a></td>
								</tr>
								<tr>
									<td>8</td>
									<td>티켓수령</td>
									<td class="txt_left"><a href="#">티켓 분실 시 재발권이 가능한가요?</a></td>
								</tr>
								<tr>
									<td>9</td>
									<td>취소/환불</td>
									<td class="txt_left"><a href="#">공연이 취소되었는데 이후 처리는 어떻게 되나요?</a></td>
								</tr>
								<tr>
									<td>10</td>
									<td>이벤트</td>
									<td class="txt_left"><a href="#">현재 진행되고 있는 이벤트는 어디서 확인하나요?</a></td>
								</tr>
							</tbody>
						</table>
						
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