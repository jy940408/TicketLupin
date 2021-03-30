<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<title>배송/결제</title>
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Myticket_buy_detail.css">
		<script>
		
			function reservationDelete(){
				
				alert("예매가 취소되었습니다!");
				location.href="${pageContext.request.contextPath}/Reservation/deleteReservation.do?riidx=${detail[0].riidx}";
				
			}
			
		</script>
	</head>
	<body>
		<form name="frm">
			<div class="section">
				<div class="select">
					<div class="step">
						<ul class="step_ul">
							<li>
								<a href="#" class="step1" style="font-weight:bold;">나의 예매내역 확인하기</a>
							</li>
						</ul>
					</div>
					<div class="box">
						<div class="box_how">
							<div class="select_tit" style="font-size:15px; display:inline-block;">수령방법:&nbsp;</div>
							<div style="display:inline-block; font-size:15px;">${detail[0].rpaymethod }</div>
							<div class="txt">
								<span style="font-size:12px">공연 당일 현장 교부처에서 예매번호 및 본인 확인 후 티켓을 수령하여 입장이 가능합니다.</span>
							</div>
							<div class="box_info_use box_gray">
								<h3 class="tit_receipt" style="font-weight:bold;">주문자정보</h3>
								<div class="box_inp_opt">
									<table class="tb1">
										<colgroup>
											<col style="width: 43px;">
											<col style="width: 90px;">
											<col style="width: 50px;">
											<col style="width: 195px;">
											<col style="width: 50px;">
											<col style="width: 150px;">
										</colgroup>
										<tr>
											<th class="txt_gray" style="font-weight:bold;">이름: </th>
											<td>
												<div class="wrap_form_input">
													${detail[0].rname}
												</div>
											</td>
											<th class="txt_gray" style="font-weight:bold;">연락처: </th>
											<td>
											<div class="wrap_form_input">
												${detail[0].rtel}
											</div>
											</td>
											<th class="txt_gray" style="font-weight:bold;">이메일: </th>
											<td>
											<div class="wrap_form_input">
												${detail[0].remail }
											</div>
										</td>
										</tr>
									</table>
									<p class="txt_more txt_gray">입력하신 정보는 공연장에서 예매확인을 위해 사용될 수 있습니다.</p>
								</div>
							</div>
						</div>
						<div class="box_payment">
							<div class="select_tit" style="font-size:15px; display:inline-block; font-weight:bold;">결제 방법:&nbsp;</div>
							<span class="list_receipt_how" style="display:inline-block; font-size:15px;">${detail[0].rpaymethod}</span>
							<c:if test="${detail[0].rpaymethod eq '신용카드'}">
								<div class="box_card box_gray">
									<div class="txt_gray" style="font-weight:bold; display:inline-block;">결제 카드:&nbsp;</div>
									<div class="wrap_form_input" style="display:inline-block;">${detail[0].rcard}</div>
									<div class="txt_gray" style="margin-left:150px; font-weight:bold; display:inline-block;">할부:&nbsp;</div>
									<c:choose>
										<c:when test="${detail[0].rquota eq '00'}">
											<div class="wrap_form_input" style="display:inline-block;">일시불</div>
										</c:when>
										<c:otherwise>
											<div class="wrap_form_input" style="display:inline-block;">${detail[0].rquota}개월</div>
										</c:otherwise>
									</c:choose>
								</div>
							</c:if>
						</div>
						<div class="box_payment">
							<div class="select_tit" style="font-size:15px; display:inline-block; font-weight:bold;">총 결제 금액:&nbsp;</div>
							<span class="list_receipt_how" style="display:inline-block; font-size:15px;"><fmt:formatNumber value="${detail[0].ripayment}" pattern="#,###" />원</span>
							<div class="box_card box_gray">
								<div class="txt_gray" style="font-weight:bold; display:inline-block;">기본가:&nbsp;</div>
								<div class="wrap_form_input" style="display:inline-block;"><fmt:formatNumber value="${detail[0].ribasic}" pattern="#,###" />원</div>
								<div class="txt_gray" style="margin-left:200px; font-weight:bold; display:inline-block;">할인가:&nbsp;</div>
								<div class="wrap_form_input" style="display:inline-block;"><fmt:formatNumber value="${detail[0].ridiscount}" pattern="#,###" />원</div><br>
								<div class="txt_gray" style="font-weight:bold; display:inline-block;">예매 수수료:&nbsp;</div>
								<div class="wrap_form_input" style="display:inline-block;"><fmt:formatNumber value="${detail[0].rivat}" pattern="#,###" />원</div>
								<c:if test="${detail[0].ridelivery == 0}">
									<div class="txt_gray" style="margin-left:177px; font-weight:bold; display:inline-block;">배송료:&nbsp;</div>
									<div class="wrap_form_input" style="display:inline-block;"><fmt:formatNumber value="${detail[0].ridelivery}" pattern="#,###" />원</div><br>
								</c:if>
								<c:if test="${detail[0].ridelivery != 0}"></c:if>
							</div>
						</div>
					</div>
				</div>	
				<div class="wrap_ticket_info">
					<h2 class="logo_onestop">
						<a href="#">
							<img src="../icon/lupinlogo.png" style="width:116px; height:22px;" alt="티켓루팡 로고">
						</a>
					</h2>
					<div class="box_info">
						<img src="${pageContext.request.contextPath}/poster/${detail[0].stitleimage}" style="width:245px; height:350px;">
					</div>
					<div class="box_info" style="background-color:#fff; border:1px solid #eeeeee; padding:10px;">
						<div><h2 style="font-weight:bold;">${detail[0].stitle}</h2></div>
						<hr style="border:1px solid #eeeeee;">
						회차: ${detail[0].srdate} ${detail[0].srround }<br>
						좌석:
						<c:forEach var="d" items="${detail}" varStatus="i" begin="0">
							<c:if test="${!i.last}">
								${detail[i.index].rseat},&nbsp;
							</c:if>
							<c:if test="${i.last}">
								${detail[i.index].rseat}석
							</c:if>
						</c:forEach>
					</div>
					<div class="box_info_bm">
						<div class="box_info_list">
							<ul class="dotlist1x1 one_list">
								<li style="padding:0;">
									취소기한: <span class="txt_og txt_cancel_close_dt">2021년 2월 9일(화) 17:00 까지</span>
								</li>
								<li style="padding:0;">
									취소수수료: <span class="txt_og txt_cancel_fee_info">티켓금액의 0~30%</span>
								</li>
							</ul>
						</div>
						<div class="btn_onestop">
							<span class="button btWhite frt">
								<a href="#" class="btnOne" onclick="self.close();">
									확인<em class="one_arr prav_ar">이전</em>
								</a>
							</span>
							<span class="button btNext">
								<a onclick="reservationDelete()" class="btnOne">
									예매 취소<em class="one_arr prav_ar">이전</em>
								</a>
							</span>
						</div>
					</div>
				</div>
			</div>
			
			<c:forEach var="dp" items="${discountParameter}">
				<input type="hidden" name="discountParameter" value="${dp}">
			</c:forEach>
			<c:forEach var="as" items="${arraySeat}">
				<input type="hidden" name="arraySeat" value="${as}">
			</c:forEach>
			<input type="hidden" name="sidx" value="${sidx}">
			<input type="hidden" name="comDate" value="${comDate}">
			<input type="hidden" name="round" value="${round}">
		</form>
	</body>
</html>