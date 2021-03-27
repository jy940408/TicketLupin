<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>이벤트 공지 작성</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Event_write_admin.css">
		
		<script type="text/javascript">
			function regist(){
				if(document.frm.etitle.value == ""){
					alert("제목을 입력해주세요");
					document.frm.etitle.focus();
					return;
				}else if(document.frm.econtent.value == ""){
					alert("내용을 입력해주세요");
					document.frm.econtent.focus();
					return;
				}
				
				var url = '<%=request.getContextPath()%>';
				 document.frm.action =url+"/Event/EventWriteAction.do";
				 document.frm.method = "POST";
				 document.frm.enctype="multipart/form-data";
				 document.frm.submit(); 
				 
			}
		</script>
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
				<li><a href="<%=request.getContextPath()%>/Event/EventMain.do">전체 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">당첨자 발표</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="#">참여 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
		</div>
		<hr id="nav_bar_sub">
		<section>
			<article>
				<div class="event">
					<h2>티켓 루팡</h2>
					<form name="frm">
					<table class="event_table" style="border:1px solid; border-collapse:collapse;">
						<colgroup>
							<col width="50px"/>
							<col width="35px"/>
							<col width="33px"/>
							<col width="60px"/>
							<col width="300px"/>
							<col width="60px"/>
							<col width="300px"/>
						</colgroup>
						<tr>
							<th style="border:1px solid;">유형</th>
							<td colspan="2" style="border:1px solid;">
								<center>
									<select class="type" style="border:0px; font-size:15px;" name="ecategory">
										<option name="ecategory" selected>list</option>
										<option name="ecategory">banner1</option>
										<option name="ecategory">banner2</option>
									</select>
								</center>
							</td>
							<th style="border:1px solid;" >시작일</th>
							<td style="border:1px solid;">
								<input type="date" name="estart" class="start_date" style="border:0px; font-size:15px; width:100%; text-align:center;">
							</td>
							<th style="border:1px solid; width:60px;" >종료일</th>
							<td	style="border:1px solid;">
								<input type="date" name="eend" class="end_date" style="border:0px; font-size:15px; width:100%; text-align:center;">
							</td>
						</tr>
						<tr>
							<th style="border:1px solid">제목</th>
							<td colspan="6" style="border:1px solid">
								<center>
									<input type="text" name="etitle" class="title" maxlength="100" style="border:0px; font-size:16.1px; width:99%">
								</center>
							</td>
						</tr>
						<tr>
							<td colspan="7">
								<center>
									<textarea class="content" name="econtent" style="width:99%; height:500px; border:0px; font-size:15px; resize:none; overflow-x:hidden;" placeholder="내용을 입력해주세요."></textarea>
								</center>
							</td>
						</tr>
						<tr>
							<th colspan="2" style="border:1px solid">썸네일</th>
							<td colspan="5" style="border:1px solid">
								<input type="file" NAME="ethumbnail" class="file" style="border:0px; font-size:14px;">
							</td>
						</tr>
						<tr>
							<th colspan="2" style="border:1px solid">첨부파일</th>
							<td colspan="5" style="border:1px solid">
								<input type="file" NAME="efiles" class="file" style="border:0px; font-size:14px;">
							</td>
						</tr>
						
					</table>
					</form>
					<div class="modify">
						<button class="remove_btn">제거하기</button>
						<button class="modify_btn" onclick="regist();">등록</button>
					</div>
					<div class="list">
						<button class="list_btn">목록으로</button>
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