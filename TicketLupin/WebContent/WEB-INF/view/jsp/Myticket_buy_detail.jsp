<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<title>배송/결제</title>
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/Pay_step3.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Myticket_buy_detail.css">
		<script>
			function paymentSubmit(){
				
				document.frm.action = "${pageContext.request.contextPath}/Reservation/ReservationStep4.do";
			 	document.frm.method = "get";
			 	document.frm.submit(); 
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
							<h3 class="select_tit">
								수령방법
							</h3>
							<div>모바일 티켓</div>
							<div class="txt">
								<span style="font-size:12px">공연 당일 현장 교부처에서 예매번호 및 본인 확인 후 티켓을 수령하여 입장이 가능합니다.</span>
							</div>
							<div class="box_info_use box_gray">
								<h4 class="tit_receipt">주문자정보</h4>
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
											<th class="txt_gray">이름: </th>
											<td>
												<div class="wrap_form_input">
												<input type="hidden" name="userName" id="userName" class="inputType inp_txt inp_w77" value="홍길동">
													${detail.midx}
												</div>
											</td>
											<th class="txt_gray">연락처: </th>
											<td>
											<div class="wrap_form_input">
												<input type="hidden" name="tel" id="tel" class="inputType inp_txt inp_w150" value="">
												<input type="text" name="tel1" id="tel1" class="inputType inp_txt inp_w52" maxlength="3">
												<input type="text" name="tel2" id="tel2" class="inputType inp_txt inp_w52" maxlength="4">
												<input type="text" name="tel3" id="tel3" class="inputType inp_txt inp_w52" maxlength="4">
												<label for="tel" class="place_holder"></label>
											</div>
											</td>
											<th class="txt_gray">이메일: </th>
											<td>
											<div class="wrap_form_input">
												<input type="text" name="email" id="email" class="inputType inp_txt inp_w150" value="" autocomplete="off">
												<label for="email" class="place_holder"></label>
											</div>
										</td>
										</tr>
									</table>
									<p class="txt_more txt_gray">입력하신 정보는 공연장에서 예매확인을 위해 사용될 수 있습니다.</p>
								</div>
							</div>
						</div>
						<div class="box_payment">
							<h3 class="select_tit">결제수단</h3>
							<ul class="list_receipt_how">
								<li>
									<label>
										<input type="radio" name="payMethodCode" class="radio_pay_metohd_code" title="신용카드" value="credit">
										<span class="txt_lab radio_pay_metohd_label">신용카드</span>
									</label>
								</li>
								<li>
									<label>
										<input type="radio" name="payMethodCode" class="radio_pay_metohd_code" title="무통장입금" value="bank">
										<span class="txt_lab radio_pay_metohd_label">무통장입금</span>
									</label>
								</li>
								<li>
									<label>
										<input type="radio" name="payMethodCode" class="radio_pay_metohd_code" title="휴대폰 결제" value="phone">
										<span class="txt_lab radio_pay_metohd_label">휴대폰 결제</span>
									</label>
								</li>
								<li>
									<label>
										<input type="radio" name="payMethodCode" class="radio_pay_metohd_code" title="카카오페이 머니" value="kakaoM">
										<span class="txt_lab radio_pay_metohd_label">카카오페이 머니</span>
									</label>
								</li>
								<li>
									<label>
										<input type="radio" name="payMethodCode" class="radio_pay_metohd_code" title="카카오페이 카드" value="kakaoP">
										<span class="txt_lab radio_pay_metohd_label">카카오페이 카드</span>
									</label>
								</li>
							</ul>
							<div class="box_card box_gray">
								<div class="box_inp_opt">
									<table class="tb1">
										<colgroup>
											<col style="width:68px;">
											<col style="width:220px;">
											<col>
										</colgroup>
										<tr>
											<th class="txt_gray">카드</th>
											<td>
												<div class="wrap_sel">
													<div class="btn_sel">
														<select name="cardCode" class="sel_cate">
															<option value="" selected="selected">카드를 선택해주세요.</option>
															<option value="SAMSUNG">삼성카드</option>
															<option value="KB">KB국민카드</option>
															<option value="HYUNDAI">현대카드</option>
															<option value="BC">BC카드</option>
															<option value="SHINHAN">신한카드</option>
															<option value="NH">NH농협카드</option>
															<option value="NHMH">NH문화누리카드</option>
															<option value="HANA_SK">하나카드</option>
															<option value="LOTTE">롯데카드</option>
															<option value="CITI">씨티카드</option>
															<option value="KAKAOBANK">카카오뱅크카드</option>
															<option value="BCKA">카카오페이카드</option>
															<option value="KBANK">케이뱅크카드</option>
															<option value="WOORI">우리카드</option>
															<option value="GWANGJU">광주카드</option>
															<option value="JEONBOOK">전북카드</option>
															<option value="SOOHYUP">수협카드</option>
															<option value="KDB">KDB산업은행카드</option>
															<option value="JEJU">제주카드</option>
														</select>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th class="txt_gray">할부</th>
											<td>
												<div class="wrap_sel">
													<div class="btn_sel">
														<select name="quota" class="sel_cate">
															<option value="00" selected="selected">일시불</option>
															<option value="2">2개월</option>
															<option value="3">3개월</option>
															<option value="4">4개월</option>
															<option value="5">5개월</option>
															<option value="6">6개월</option>
															<option value="7">7개월</option>
															<option value="8">8개월</option>
															<option value="9">9개월</option>
															<option value="10">10개월</option>
															<option value="11">11개월</option>
															<option value="12">12개월</option>
														</select>
													</div>
												</div>
											</td>
										</tr>
									</table>
								</div>
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
						<img src="${pageContext.request.contextPath}/poster/${detail.stitleimage}" style="width:245px; height:350px;">
					</div>
					<div class="box_info" style="background-color:#fff; border:1px solid #eeeeee; padding:10px;">
						<div><h2 style="font-weight:bold;">${detail.stitle}</h2></div>
						<hr style="border:1px solid #eeeeee;">
						회차: ${detail.srdate} ${detail.srround }<br>
						좌석: ${detail.rseat}석
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
								<a href="#" class="btnOne">
									확인<em class="one_arr prav_ar">이전</em>
								</a>
							</span>
							<span class="button btNext">
								<button type="button" onclick="paymentSubmit()" class="btnOne">예매 취소</button>
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