<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>이용안내 상세</title>
		<script src="../js/Buyguide.js"></script>
		<link rel="stylesheet" href="../css/Buyguide.css">
		<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/Nav_event.js"></script>
		<script src="<%=request.getContextPath() %>/js/Nav_all.js"></script>
	</head>
	<body>
		<header>
			<div id="h_title">
				<div id="h_title_inner">
					<span id="h_top_menu">
						<ul id="h_top_menu_ul">
						<c:if test="${not empty sessionScope.mid}">
							<li><a href="${pageContext.request.contextPath}/Member/Member_Modify_PwdCheck.do?mid=${sessionScope.mid}">${sessionScope.mid }님 환영합니다!</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							<li><a href="${pageContext.request.contextPath}/Member/Memberlogout.do">로그아웃&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
						<c:if test="${empty sessionScope.mid}">
							<li class="login"><a href="${pageContext.request.contextPath}/Member/MemberLogin.do">로그인&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="${pageContext.request.contextPath}/Member/MemberJoin.do">회원가입&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
							<li><a href="${pageContext.request.contextPath}/Customer/NoticeList.do">고객센터&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">이용안내&nbsp;&nbsp;&nbsp;&nbsp;</a></li><br/>
						</ul>
						<img src="../ads/musicalads.png" id="h_ads">
					</span>
					<a href=""${pageContext.request.contextPath}/Main/MainPage.do"><img src="../icon/lupinlogo.png" id="h_logo"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<form action="${pageContext.request.contextPath}/Show/ShowList.do" method="get" style="display:inline-block;">
						<input type="text" id="h_search" name="q" placeholder="뮤지컬 〈캣츠〉 40주년 내한공연 앙코르－서울（Musical CATS Encore">
						<button type="submit" id="h_search_button"><img src="../icon/search.png" id="h_search_img"></button>
					</form>
				</div>
			</div>
		</header>
		<hr id="nav_bar_top">
		<div id="n_nav_div">
			<nav id="main_nav">
				<a href="${pageContext.request.contextPath}/Main/MainPage.do" id="main_nav_home">홈</a>
				<a href="${pageContext.request.contextPath}/Show/ShowList.do" id="main_nav_concert">공연</a>
				<a href="${pageContext.request.contextPath}/Show/RankingList.do" id="main_nav_ranking">랭킹</a>
				<a href="${pageContext.request.contextPath}/News/NewsList.do" id="main_nav_news">티켓오픈소식</a>
				<a href="${pageContext.request.contextPath}/Event/EventMain.do" id="main_nav_event">이벤트</a>
				<c:choose>
					<c:when test="${sessionScope.mgrade eq 'M' }">
						<a href="${pageContext.request.contextPath}/Manager/Main.do" id="main_nav_myticket">관리자</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/Myticket/MyticketMain.do" id="main_nav_myticket">마이 티켓</a>
					</c:otherwise>
				</c:choose>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		<div id="nav_menu_sub_event_div" class="main_nav_all">
			<ul id="nav_menu_sub_event" style="margin:0px;">
				<li><a href="${pageContext.request.contextPath}/Event/EventMain.do">전체 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="${pageContext.request.contextPath}/Winner/WinnerList.do">당첨자 발표</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		<div id="nav_menu_sub_myticket_div" class="main_nav_all">
			<ul id="nav_menu_sub_myticket" style="margin:0px;">
				<c:choose>
					<c:when test="${sessionScope.mgrade eq 'M' }">
						<li><a href="${pageContext.request.contextPath}/Manager/MemberList.do">회원관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/Manager/ConcertList.do">공연관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/Manager/comment.do">댓글관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/Customer/AnswerMain.do">문의관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${not empty sessionScope.mid}">
								<li><a href="${pageContext.request.contextPath}/Myticket/MyticketMain.do">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a href="${pageContext.request.contextPath}/Myticket/MyticketReservation.do">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a href="${pageContext.request.contextPath}/Dibs/MyDibs.do">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							</c:when>
							<c:otherwise>
								<li><a onclick="loginAlert()">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a onclick="loginAlert()">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a onclick="loginAlert()">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		
		
		<section>
			<article>
				<div class="guide">
					<p class="tit">
						<span class="label">고객센터</span>
					</p>
					<div class="cont_tbl">
						<table class="table1" border="1">
							<tr>
								<td width="140px"><a href="../Customer/NoticeList.do">공지사항</a></td>
								<td width="140px"><a href="../Customer/Buyguide.do">이용안내</a></td>
								<td width="140px"><a href="../Customer/FaqList.do">FAQ</a></td>
								<td width="140px">
									<c:choose>
										<c:when test="${not empty sessionScope.mid && sessionScope.mgrade eq 'G'}">
											<a href="../Customer/QuestionList.do">나의 문의 내역</a>
										</c:when>
										<c:when test="${not empty sessionScope.mid && sessionScope.mgrade eq 'M'}">
											<a href="../Customer/AnswerMain.do">문의 관리</a>
										</c:when>
										<c:otherwise>
											<a href="../Member/MemberLogin.do">나의 문의 내역</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
					</div>
					<center>
						<div class="sub_btn">
							<a href="javascript:doDisplay1();">
								예매방법
							</a>
							<a href="javascript:doDisplay2();">
								취소/환불
							</a>
							<a href="javascript:doDisplay3();">
								발권/배송
							</a>
						</div>
					</center>
					<div id="guide1" class="inner" style="display: block;">
						<h3>티켓루팡의 예매 방법을 안내드립니다.</h3>
						<div class="use_guide_box">
							<h4>1. <span>회원가입, 로그인, 본인인증</span></h4>
							<ul>
								<li>예매 전, 티켓루팡 회원가입과 로그인을 하셨는 지 확인해 주세요.</li>
								<li>예매를 위해서는 본인인증 절차가 필요하므로, 설정 메뉴에서 본인인증을 하셨는지 확인해 주세요.</li>
							</ul>
						</div>
						<div class="use_guide_box">
							<h4>2. <span>관람하고자 하는 공연 선택</span></h4>
							<ul>
								<li>티켓루팡에서 제공하는 여러 메뉴를 통해 관람하고자 하는 공연을 선택해 주세요.</li>
							</ul>
						</div>
						<div class="use_guide_box">
							<h4>3. <span>공연 날짜 및 시간 선택</span></h4>
							<ul>
								<li>공연 페이지에서 날짜와 시간을 선택해, 예매 가능한 좌석을 확인 후 예매하기 버튼을 눌러주세요.</li>
							</ul>
							<p class="guide_img"><img src="https://cdnticket.melon.co.kr/image/web/customer/guide_img_01.png" alt=""></p>
						</div>
						<div class="use_guide_box">
							<h4>4. <span>원하는 좌석을 선택</span></h4>
							<ul>
								<li>좌석도에서 원하는 좌석을 선택해 주세요.</li>
								<li>선택하신 좌석은 그 시점부터 5분간만 선점되므로, 5분 내 예매/결제를 완료해 주세요.</li>
							</ul>
							<p class="guide_img"><img src="https://cdnticket.melon.co.kr/image/web/customer/guide_img_02.png" alt=""></p>
						</div>
						<div class="use_guide_box">
							<h4>5. <span>티켓의 가격과 할인수단을 선택</span></h4>
							<ul>
								<li>티켓의 가격과 할인받을 수단을 선택해 주세요.</li>
							</ul>
							<p class="guide_img"><img src="https://cdnticket.melon.co.kr/image/web/customer/guide_img_03.png" alt=""></p>
						</div>
						<div class="use_guide_box">
							<h4>6. <span>배송 방법 선택</span></h4>
							<ul>
								<li>상품별로 제공되는 티켓 배송방법을 선택해 주세요. (배송, 현장수령, 모바일 발권)</li>
								<li>'배송'을 선택하시는 경우 배송지 정보를 입력해야 합니다.</li>
							</ul>
						</div>
						<div class="use_guide_box">
							<h4>7. <span>결제수단 선택 및 결제</span></h4>
							<ul>
								<li>결제하실 수단을 선택하고 결제를 진행하세요. (상품별로 신용카드, 무통장입금, 휴대폰결제, 카카오페이 수단 중 일부만 제공될 수도 있습니다.)</li>
							</ul>
							<p class="guide_img"><img src="https://cdnticket.melon.co.kr/image/web/customer/guide_img_04_20170316.png" alt=""></p>
						</div>
						<div class="use_guide_box">
							<h4>8. <span>예매 내역 확인</span></h4>
							<ul>
								<li>'마이티켓 &gt; 예매 확인/취소'에서 예매한 내역을 언제든지 확인하실 수 있습니다.</li>
							</ul>
							<p class="guide_img"><img src="https://cdnticket.melon.co.kr/image/web/customer/guide_img_05.png" alt=""></p>
						</div>
						<div class="use_guide_box">
							<h4>9. <span>배송지 및 결제수단 변경</span></h4>
							<ul>
								<li>예매 시 입력 하셨던 배송지 또는 선택하셨던 결제수단을 변경하고자 할 경우, '마이티켓 &gt; 예매확인/취소' 에서 변경하실 수 있습니다.</li>
								<li>결제수단 변경의 경우, 무통장미입금 예매 건에 한하여 신용카드, 카카오페이로 변경이 가능합니다.<br>
									그 외 결제수단의 변경이나 할부 개월 변경은 불가하며, 예매 건을 취소하고, 원하는 결제수단으로 재예매 하셔야 합니다.</li>
							</ul>
							<p class="guide_img"><img src="https://cdnticket.melon.co.kr/image/web/customer/guide_img_07_20201023.png" alt=""></p>
						</div>
					</div>
					<div id="guide2" class="inner" style="display: none;">
						<h3>티켓루팡의 취소/환불 규정을 안내드립니다.</h3>
						<div class="use_guide_box">
							<h4>1. <span>취소 마감 기한</span></h4>
							<table cellspacing="0" cellpadding="0">
							<caption class="hide"></caption>
								<colgroup>
									<col style="width:168px">
									<col style="width:386px">
								</colgroup>
								<thead>
									<tr>
										<th>공연 관람일</th>
										<th>취소 마감 시간</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>일요일~월요일</td>
										<td class="txt_left">토요일 오전 11시</td>
									</tr>
									<tr>
										<td>화요일~토요일</td>
										<td class="txt_left">공연 관람일 전일 오후 5시</td>
									</tr>
									<tr>
										<td rowspan="2">공휴일 및 공휴일 다음날</td>
										<td class="txt_left">공휴일 전일이 평일인 경우 오후 5시</td>
									</tr>
									<tr>
										<td class="txt_left">공휴일 전일이 토요일, 일요일인 경우 토요일 오전 11시</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="use_guide_box">
							<h4>2. <span>취소 수수료 안내</span></h4>
							<table cellspacing="0" cellpadding="0">
							<caption class="hide"></caption>
								<colgroup>
									<col style="width:277px">
									<col style="width:277px">
								</colgroup>
								<thead>
									<tr>
										<th>취소일</th>
										<th>취소수수료</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="txt_left2">예매 후 7일이내</td>
										<td class="txt_left2">없음</td>
									</tr>
									<tr>
										<td class="txt_left2">예매 후 8일 ~ 관람일 10일이내</td>
										<td class="txt_left2">장당 4,000원(티켓금액의 10% 한도)</td>
									</tr>
									<tr>
										<td class="txt_left2">관람일 9일 전 ~ 7일전</td>
										<td class="txt_left2">티켓금액의 10%</td>
									</tr>
									<tr>
										<td class="txt_left2">관람일 6일 전 ~ 3일전</td>
										<td class="txt_left2">티켓금액의 20%</td>
									</tr>
									<tr>
										<td class="txt_left2">관람일 2일 전 ~ 1일전</td>
										<td class="txt_left2">티켓금액의 30%</td>
									</tr>
								</tbody>
							</table>
							<br>
							<ul>
								<li>취소일자에 따라 위와 같이 취소수수료가 부과됩니다.(예매일 기준보다 관람일 기준 우선 적용)</li>
								<li>취소마감시간 이후 또는 관람일 당일 예매 건은 취소/변경/환불이 불가합니다.</li>
								<li>취소 시 예매수수료는 예매 당일 밤 12시 이전까지 환불되며, 해당기간 이후에는 환불되지 않습니다.</li>
							</ul>
						</div>
						<div class="use_guide_box">
							<h4>3. <span>배송 방법별 취소 안내</span></h4>
							<dl>
								<dt>[우편 배송]</dt>
								<dd>예매 당일에는 티켓 예매서비스 또는 티켓예매콜센터를 통해 직접 취소하실 수 있습니다.</dd>
								<dd>티켓이 발송된 이후에는 구매자가 취소를 원하는 티켓을 취소마감시한 전까지 회사로 반송, 도달하도록 한 경우에만 취소가 가능합니다. </dd>
								<dd>부분취소인 경우에도 취소를 원하는 티켓을 반송해야만 환불을 진행할 수 있습니다.</dd>
								<dd>티켓이 발송된 이후 구매 취소를 원하는 경우, 티켓의 왕복 배송비는 회원이 부담하는 것을 원칙으로 합니다.</dd>
								<dd>티켓 반품접수 시 이용자의 연락처, 반품사유, 무통장입금 고객의 경우 환불받은 본인 명의의 계좌를 반품신청서에 작성해 티켓과 동봉해야 합니다.</dd>
								<dd>수령한 티켓은 현금, 상품권과 같은 유가증권이므로 티켓이 분실되거나 훼손되었을 경우 예매 취소 및 변경이 불가합니다.</dd>
								<dt>[현장 수령]</dt>
								<dd>예매 당일에는 티켓 예매서비스 또는 티켓예매콜센터를 통해 직접 취소하실 수 있습니다.</dd>
								<dt>[모바일 티켓]</dt>
								<dd>티켓을 모바일로 수령 및 발권하신 경우 예매 취소가 불가합니다.</dd>
							</dl>
						</div>
						<div class="use_guide_box">
							<h4>4. <span>결제수단별 환불 방법</span></h4>
							<dl>
								<dt>[신용카드]</dt>
								<dd>취소수수료 및 배송료를 재승인 후 원 승인금액은 취소처리 됩니다.</dd>
								<dd>취소일로부터 영업일 3~5일 정도 후 카드사에서 취소 확인 가능합니다.</dd>
								<dd>환불소요기간은 평일 기준이며, 토/일/공휴일은 제외됩니다.</dd>
								<dd>카드사 관련 문의는 개인정보 확인 절차로 인해 티켓루팡에서 대신 확인해 드릴 수 없어 카드사를 통해 확인하시면 됩니다.</dd>
								<dt>[무통장 입금]</dt>
								<dd>취소수수료를 제외한 나머지 금액이 고객 환불 계좌에 입금됩니다.</dd>
								<dd>환불받으실 은행의 계좌번호와 예금주를 본인 명의로 정확하게 입력해 주세요.</dd>
								<dd>타인의 계좌를 이용하거나 명의를 도용했을 경우 서비스 이용이 제한될 수 있습니다.</dd>
								<dd>취소 처리를 접수한 날로부터 영업일 3~5일 이내 환불받으실 수 있습니다.</dd>
								<dt>[휴대폰]</dt>
								<dd>예매 취소 시, 기존에 결제했던 내역이 승인취소 되며, 취소 시점에 따라 취소수수료가 발생할 수 있습니다.</dd>
								<dd>취소수수료 발생 시, 동일한 결제수단으로 취소수수료 결제가 요청됩니다.</dd>
								<dt>[카카오페이]</dt>
								<dd>취소수수료 및 배송료를 재승인 후 원 승인금액은 취소처리 됩니다.</dd>
								<dd>취소일로부터 영업일 0~7일 정도 후 카카오페이(PG)사에서 취소 확인 가능합니다.</dd>
								<dd>환불소요기간은 평일 기준이며, 토/일/공휴일은 제외됩니다.</dd>
							</dl>
						</div>
					</div>
					<div id="guide3" class="inner" style="display: none;">
						<h3>티켓루팡의 발권/배송 관련한 안내드립니다.</h3>
						<div class="use_guide_box">
							<h4>1. <span>배송</span></h4>
							<ul>
								<li>티켓 예매 결제 확인 후, 인편 배송으로 배송해 드립니다.</li>
								<li>일반적으로 예매 후 7일 이내 수령이 가능하며, 상품별로 티켓 배송일이 다를 수 있습니다.</li>
								<ul>
									<li>- 지역 및 배송서비스 사정에 따라 배송사가 변경될 수 있으며, 배송일이 추가적으로 소요될 수 있습니다. (CJ대한통운, 우체국 외 1개 업체, 주말/공휴일은 배송하지 않습니다.)</li>
									 <li>- 일괄배송의 경우 공연 별로 배송일자가 상이하며 지정된 배송일자 기준으로 배송이 시작됩니다. (지정된 배송일자는 상세정보 및 예매공지사항에서 확인할 수 있습니다.)</li>
									<li>- 티켓 인도 장소에 수령자가 없는 경우 배송기간이 더 소요될 수 있습니다.</li>
									<li>- 행사 또는 행사일에 따라 현장 수령 방법 선택이 제한될 수 있습니다.</li>
								</ul>
								
							</ul>
						</div>
						<div class="use_guide_box">
							<h4>2. <span>현장 수령</span></h4>
							<ul>
								<li>행사 당일, 공연 시작시간 1시간 전 ~ 30분 전까지 행사장 매표소에서 직접 수령하셔야 합니다.</li>
								<li>예매번호가 포함되어 있는 예매확인서와 예매자의 실물 신분증(복사본 및 사진 불가) 을 매표소에 제출하시면 편리하게 티켓을 수령하실 수 있습니다.
								<ul>
									<li>- 예매확인서, 예매번호, 예매자의 주민등록상 생년월일 정보를 반드시 가져오셔야 합니다.</li>
									<li>- 학생, 장애인, 국가유공자 등의 할인 혜택을 받아 예매하신 경우 관련 증빙 서류를 꼭 지참하셔야 합니다.</li>
									<li>- 행사 또는 행사일에 따라 현장 수령 방법의 선택이 제한될 수 있습니다.</li>
									<li>- 공연별 정책이 상이하니 자세한 내용은 예매페이지 내 상세정보 확인 부탁드립니다.</li>
								</ul>
								</li>
							</ul>
						</div>
						<div class="use_guide_box">
							<h4>3. <span>모바일 티켓</span></h4>
							<ul>
								<li>예매하신 모바일티켓은 결제완료(입금완료) 기준으로 최대 1일 이내 카카오콘으로 발급됩니다. (모바일티켓 발급경로: 카카오톡 모바일앱 &gt; 더보기탭 &gt; 카카오콘) </li><li>모바일티켓으로 예매시 종이티켓은 별도로 제공되지 않습니다.</li>
								<li>내 아이디와 연결된 카카오계정으로 모바일티켓이 발급됩니다. 연결된 카카오계정이 없으면 모바일티켓을 예매할 수 없으니, 모바일티켓 예매 전 연결 여부를 확인해주세요.</li>
								<li>학생, 장애인, 국가유공자 등의 할인혜택을 받아 예매하신 경우 관련 증빙 서류를 꼭 지참하셔야 합니다.
									<ul>
										<li>- 공연마다 입장을 위한 검표방식이 달라질 수 있습니다.</li>
										<li>- 검표 가능시간이 되면 모바일티켓 화면에 검표를 위한 ‘코드 스캔하기’ 버튼이 활성화됩니다.</li>
										<li>- 검표가 완료된 모바일티켓은 재검표가 불가능하며, 검표 이후에 는 모바일티켓 전달 및 예매취소 또한 불가능합니다.</li>
										<li>- QR직접스캔을 통해 검표하는 경우, 불법양도 및 캡쳐 이용을 제한하기 위해 최종 입장 전 공연장 직원이 모바일티켓 화면을 확 인할 수 있습니다.</li>
									</ul>
								</li>
							</ul>
						</div>
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