<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 루팡</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_event.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Admin_qna_list.css">
		
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
		
		
		<div class="wrap_nav" id="wrap_nav"  style="display:none;">
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
			<div id="nav_menu_sub_div" class="main_nav_event" >
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
				<div class="tit">
					<span class="label">|&nbsp;&nbsp;댓글관리</span>
					<hr/>
				</div>
				<div class="wrap_search">
					<select name="searchType">
						<option value="total" selected>전체</option>
						<option value="id">아이디</option>
						<option value="name">성함</option>
						<option value="contets">내용</option>
					</select>
					<span class="wrap_input">
						<input type="text" name="schText">
						<button type="button">검색</button>
					</span>
				</div>
				<div class="wrap_keyword">
					<span>
						<a href="#">전체</a> &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#">예매/결제</a> &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#">취소/환불</a> &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#">발권/배송</a> &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#">회원 정보</a> &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#">이벤트</a> &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#">기타</a>
					</span>
				</div>
				<div class="listorder">
					<a href="#">전체</a>&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="#">미답변</a>
				</div>
				<div class="list_table">
					<table class="table2">
						<thead>
							<tr>
								<th width="10%">No.</th>
								<th width="15%">성함</th>
								<th width="10%">아이디</th>
								<th width="20%">문의유형</th>
								<th width="45%" colspan="2">댓글내용</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>99</td>
								<td>홍길동</td>
								<td>Hong10</td>
								<td>예매/결제</td>
								<td class="td_"><a href="#">문의합니다!</a></td>
								<td><button type="button">삭제</button></td>
							</tr>
							<tr>
								<td>98</td>
								<td>홍길동</td>
								<td>Hong9</td>
								<td>취소/환불</td>
								<td class="td_"><a href="#">문의합니다!</a></td>
								<td><button type="button">삭제</button></td>
							</tr>
							<tr>
								<td>97</td>
								<td>홍길동8</td>
								<td>Hong9</td>
								<td>발권/배송</td>
								<td class="td_"><a href="#">문의합니다!</a></td>
								<td><button type="button">삭제</button></td>
							</tr>
							<tr>
								<td>96</td>
								<td>홍길동</td>
								<td>Hong9</td>
								<td>회원정보</td>
								<td class="td_"><a href="#">문의합니다!</a></td>
								<td><button type="button">삭제</button></td>
							</tr>
							<tr>
								<td>95</td>
								<td>홍길동</td>
								<td>Hong9</td>
								<td>이벤트</td>
								<td class="td_"><a href="#">문의합니다!</a></td>
								<td><button type="button">삭제</button></td>
							</tr>
							<tr>
								<td>94</td>
								<td>홍길동</td>
								<td>Hong9</td>
								<td>취소/환불</td>
								<td class="td_"><a href="#">문의합니다!</a></td>
								<td><button type="button">삭제</button></td>
							</tr>
							<tr>
								<td>93</td>
								<td>홍길동</td>
								<td>Hong9</td>
								<td>발권/배송</td>
								<td class="td_"><a href="#">문의합니다!</a></td>
								<td><button type="button">삭제</button></td>
							</tr>
							<tr>
								<td>92</td>
								<td>홍길동</td>
								<td>Hong9</td>
								<td>기타</td>
								<td class="td_"><a href="#">문의합니다.</a></td>
								<td><button type="button">삭제</button></td>
							</tr>
							<tr>
								<td>91</td>
								<td>홍길동</td>
								<td>Hong9</td>
								<td>취소/환불</td>
								<td class="td_"><a href="#">문의합니다!</a></td>
								<td><button type="button">삭제</button></td>
							</tr>
							<tr>
								<td>90</td>
								<td>홍길동</td>
								<td>Hong9</td>
								<td>발권/배송</td>
								<td class="td_"><a href="#">문의합니다.</a></td>
								<td><button type="button">삭제</button></td>
							</tr>
							<tr>
								<td>89</td>
								<td>홍길동</td>
								<td>Hong9</td>
								<td>기타</td>
								<td class="td_"><a href="#">문의합니다!</a></td>
								<td><button type="button">삭제</button></td>
							</tr>
						</tbody>
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