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
		<script src="<%=request.getContextPath() %>/js/Pay_step3.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Pay_step3.css">
		<script>
			
			$(document).ready(function(){
				$("input:radio[name='payMethodCode']").on("change", function(){
					if($(this).val() == "credit"){
						$(".box_card").show();
					}else{
						$(".box_card").hide();
					}
				});
				
				var deliverySum = 0;
				var paymentSum = 0;
				$("#paymentSum").val(${paymentAmount});
				$("input:radio[name='pick']").on("change", function(){
					if($(this).val() == "delivery"){
						$("#deliveryCost").html((2800).toLocaleString());
						$("#paymentAmount").html((${paymentAmount}+2800).toLocaleString());
						deliverySum = 2800;
						paymentSum = ${paymentAmount} + 2800;
						$("#deliverySum").val(deliverySum);
						$("#paymentSum").val(paymentSum);
						alert(paymentSum);
					}else{
						$("#deliveryCost").html(0);
						$("#paymentAmount").html((${paymentAmount}).toLocaleString());
						deliverySum = 0;
						paymentSum = ${paymentAmount};
						$("#deliverySum").val(deliverySum);
						$("#paymentSum").val(paymentSum);
						alert(paymentSum);
					}
				});
				
				
			});
		
			function paymentSubmit(){
	        	if (document.frm.name.value == ""){
					alert("주문자 이름을 입력해주세요");
				  	document.frm.userName.focus();
				  	return;
			  	}else if (document.frm.tel1.value == ""){
				  	alert("주문자 연락처를 입력해주세요");
				  	document.frm.tel1.focus();
				  	return;
			  	}else if (document.frm.tel2.value == ""){
				  	alert("주문자 연락처를 입력해주세요");
				  	document.frm.tel2.focus();
				  	return;
			  	}else if (document.frm.tel3.value == ""){
				  	alert("주문자 연락처를 입력해주세요");
				  	document.frm.tel3.focus();
				  	return;
			  	}else if (document.frm.email.value == ""){
				  	alert("주문자 이메일을 입력해주세요");
				  	document.frm.email.focus();
				  	return;
			  	}else if (document.frm.payMethodCode.value == ""){
				  	alert("주문자 결제방법을 선택해주세요");
				  	document.frm.payMethodCode.focus();
				  	return;
			  	}else if (!document.frm.chkAgree1.checked){
			  		alert("필수 동의사항을 체크해주세요");
				  	document.frm.chkAgree1.focus();
				  	return;
			  	}else if (!document.frm.chkAgree2.checked){
			  		alert("필수 동의사항을 체크해주세요");
				  	document.frm.chkAgree1.focus();
				  	return;
			  	}else if (!document.frm.chkAgree3.checked){
			  		alert("필수 동의사항을 체크해주세요");
				  	document.frm.chkAgree1.focus();
				  	return;
			  	}else if (!document.frm.chkAgree4.checked){
			  		alert("필수 동의사항을 체크해주세요");
				  	document.frm.chkAgree1.focus();
				  	return;
			  	}else if (!document.frm.chkAgree5.checked){
			  		alert("필수 동의사항을 체크해주세요");
				  	document.frm.chkAgree1.focus();
				  	return;
			  	}else if (!document.frm.chkAgree6.checked){
			  		alert("필수 동의사항을 체크해주세요");
				  	document.frm.chkAgree1.focus();
				  	return;
			  	}
	        	
	        	if(document.frm.payMethodCode.value == "credit"){
	        		if (document.frm.cardCode.value == "none"){
					  	alert("결제카드를 선택해주세요");
					  	document.frm.cardCode.focus();
					  	return;
				  	}
	        	}
	        	
	        	alert("결제가 완료되었습니다!");
	        	
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
								<a href="#" class="step1">좌석 선택</a>
							</li>
							<li>
								<span style="position:absolute;	top:0; left:150px; margin-top:16px; margin-left:60px;">▶</span>
							</li>
							<li>
								<a href="#" class="step2">가격 선택</a>
							</li>
							<li>
								<span style="position:absolute;	top:0; left:390px; margin-top:16px; margin-left:60px;">▶</span>
							</li>
							<li>
								<a href="#" class="step3" style="font-weight:bold;">배송/결제</a>
							</li>
						</ul>
					</div>
					<div class="box">
						<div class="box_how">
							<h3 class="select_tit">
								수령방법을 선택하세요
							</h3>
							<ul class="list_receipt_how">
								<li>
									<label>
										<input type="radio" value="mobile" name="pick" class="radio_delvy_type" title="모바일 티켓" checked>
										<span class="txt_lab">&nbsp;모바일 티켓</span>
									</label>
								</li>
								<li>
									<label>
										<input type="radio" value="pickup" name="pick" class="radio_delvy_type"  title="현장수령">
										<span class="txt_lab">&nbsp;현장수령</span>
									</label>
								</li>
								<li>
									<label>
										<input type="radio" value="delivery" name="pick" class="radio_delvy_type"  title="배송">
										<span class="txt_lab">&nbsp;배송(2,800원)</span>
									</label>
								</li>
							</ul>
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
											<th class="txt_gray">이름<span class="require">*</span></th>
											<td>
												<div class="wrap_form_input">
												<input type="hidden" name="userName" id="userName" class="inputType inp_txt inp_w77" value="홍길동">
													<input type="text" name="name" class="inputType inp_txt inp_w51" maxlength="6">
												</div>
											</td>
											<th class="txt_gray">연락처<span class="require">*</span></th>
											<td>
											<div class="wrap_form_input">
												<input type="hidden" name="tel" id="tel" class="inputType inp_txt inp_w150" value="">
												<input type="text" name="tel1" id="tel1" class="inputType inp_txt inp_w52" maxlength="3">
												<input type="text" name="tel2" id="tel2" class="inputType inp_txt inp_w52" maxlength="4">
												<input type="text" name="tel3" id="tel3" class="inputType inp_txt inp_w52" maxlength="4">
												<label for="tel" class="place_holder"></label>
											</div>
											</td>
											<th class="txt_gray">이메일<span class="require">*</span></th>
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
							<h3 class="select_tit">결제수단을 선택하세요</h3>
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
															<option value="none" selected="selected">카드를 선택해주세요.</option>
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
						<div class="box_cont box_receipt">
							<ul class="box_agree">
								<li class="list_agree frt">
									<div class="select_tit">
										예매자동의
										<div class="tit_check_r">
											<input type="checkbox" name="chkAgreeAll" id="allCheck" title="전체 동의합니다.">
											<label for="chkAgreeAll"><span class="txt_lab">전체동의</span></label>
										</div>
									</div>
								</li>
								<li class="list_agree">
									<div class="tit_check">
										<input type="checkbox" name="chkAgree1" title="[필수] 예매 및 취소 수수료/ 취소기한을 확인하였으며 동의합니다.">
										<label class="chkAgree01">
											<span class="txt_lab">[필수] 예매 및 취소 수수료/ 취소기한을 확인하였으며 동의합니다.</span>
										</label>
									</div>
									<div class="box_ar_info">
										<div class="tb_date">
											<table class="tbl ">
												<colgroup>
													<col style="width: 226px;">
													<col style="width: 326px;">
												</colgroup>
												<thead class="tb_date_th">
													<tr>
														<th class="txt_gray">취소일</th>
														<th class="txt_gray">취소수수료</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>2021.02.08</td>
														<td>없음</td>
													</tr>
													<tr>
														<td>2021.02.09</td>
														<td>티켓금액의 30%</td>
													</tr>
												</tbody>
											</table>
										</div>
										<ul class="list_ds">
											<li>- 예매 당일 취소할 경우에는 예매수수료 및 취소수수료는 환불가능 합니다. (취소수수료는 취소 기한 내에 한함)</li>
										</ul>
									</div>
								</li>
								<li class="list_agree">
									<ul>
										<li>
											<div class="tit_check">
												<input type="checkbox" name="chkAgree2" id="check1" title="[필수] 결제대행 서비스 표준이용약관">
												<label class="chkAgree05">
													<span class="txt_lab">[필수] 결제대행 서비스 표준이용약관</span>
												</label>
												<a href="#" class="btn_flexible btn_flexible_ico2 btn_detail">
													<span>상세보기</span>
												</a>
											</div>
										</li>
									</ul>
								</li>
								<li class="list_agree">
									<ul>
										<li>
											<div class="tit_check">
												<input type="checkbox"  id="check2" name="chkAgree3" title="[필수] 카카오 전자금융 이용약관 동의">
												<label for="chkAgree_kakao">
													<span class="txt_lab">[필수] 카카오 전자금융 이용약관 동의</span>
												</label>
												<a href="#" class="btn_flexible btn_flexible_ico2 btn_detail">
													<span>상세보기</span>
												</a>
											</div>
										</li>
									</ul>
								</li>
								<li class="list_agree agree_terms_approval">
									<ul>
										<li>
											<div class="tit_check">
												<input type="checkbox" name="chkAgree4"  id="check3" title="[필수] 개인정보 수집/이용에 동의합니다.">
												<label>
													<span class="txt_lab">[필수] 개인정보 수집/이용에 동의합니다.</span>
												</label>
												<a href="#" class="btn_flexible btn_flexible_ico2 btn_detail">
													<span>상세보기</span>
												</a>
											</div>
										</li>
									</ul>
								</li>
								<li class="list_agree">
									<ul>
										<li>
											<div class="tit_check">
												<input type="checkbox" name="chkAgree5" id="check4" title="[필수] 개인정보 제3자 제공 동의 및 주의사항">
												<label for="chkAgree04">
													<span class="txt_lab">[필수] 개인정보 제3자 제공 동의 및 주의사항</span>
												</label>
												<a href="#" class="btn_flexible btn_flexible_ico2 btn_detail">
													<span>상세보기</span>
												</a>
											</div>
										</li>
									</ul>
								</li>
								<li class="list_agree">
									<ul>
										<li>
											<div class="tit_check">
												<input type="checkbox" name="chkAgree6" id="check5" title="[필수] 멜론티켓 이용약관 동의합니다.">
												<label>
													<span class="txt_lab">[필수] 멜론티켓 이용약관 동의합니다.</span>
												</label>
												<a href="#" class="btn_flexible btn_flexible_ico2 btn_detail">
													<span>상세보기</span>
												</a>
											</div>
										</li>
									</ul>
								</li>
							</ul>
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
						<h3 class="select_tit select_t txt_prod_name" title="뮤지컬 〈몬테크리스토〉 10주년 기념 공연">
							${title}
						</h3>
						<div class="box_ticket">
							<ul class="box_ticket_list">
								<li class="nth nth1 txt_prod_schedule">
									${comDate} ${round}
								</li>
								<li class="nth nth2 txt_ticket_info">
									총 ${fn:length(arraySeat)}석 선택
									<br>
								</li>
							</ul>
						</div>
					</div>
					<div class="box_info">
						<h3 class="select_tit">결제금액</h3>
						<div class="box_ticket">
							<div class="box_total_inner">
								<p class="tk_b">
									<span class="tk_tit">티켓금액</span>
									<span class="pay pay_comp">
										<span class="paySum"><fmt:formatNumber value="${priceSum}" pattern="#,###" /></span>원
									</span>
								</p>
								<ul class="list_tkpay">
									<li>
										<span class="tk_tit">기본가</span>
										<span class="pay">
											<span><fmt:formatNumber value="${basicSum }" pattern="#,###" /></span>원
										</span>
									</li>
									<li>
										<span class="tk_tit">할인가</span>
										<span class="pay">
											<span><fmt:formatNumber value="${discountSum }" pattern="#,###" /></span>원
										</span>
									</li>
								</ul>
							</div>
							<div class="box_total_inner">
								<p class="tk_b">
									<span class="tk_tit tk_tit_b">예매수수료</span>
									<span class="pay pay_comp">
										<span class="txt_vip" style="display:none">멜론VIP혜택★</span>
										<span><fmt:formatNumber value="${basicSumVAT + discountSumVAT }" pattern="#,###" /></span>원
									</span>
								</p>
							</div>
							<div class="box_total_inner lst">
								<p class="tk_b">
									<span class="tk_tit tk_tit_b">배송료</span>
									<span class="pay pay_comp">
										<span id="deliveryCost">0</span>원
									</span>
								</p>
							</div>
							<div class="box_total_inner box_result">
								<span class="tk_tit tot_tit">총 결제금액</span>
								<strong class="pay tot_pay">
									<span id="paymentAmount"><fmt:formatNumber value="${paymentAmount}" pattern="#,###" /></span>원
								</strong>
							</div>
						</div>
					</div>
					<div class="box_info_bm">
						<div class="box_info_list">
							<ul class="dotlist1x1 one_list">
								<li>
									취소기한: 
									<span class="txt_og txt_cancel_close_dt">2021년 2월 9일(화) 17:00 까지</span>
								</li>
								<li>
									취소수수료:
									<span class="txt_og txt_cancel_fee_info">티켓금액의 0~30%</span>
									<a href="#" class="btn_flexible btn_flexible_ico2 btn_detail specCancel">
										<span>상세보기</span>
									</a>
								</li>
							</ul>
						</div>
						<div class="btn_onestop">
							<span class="button btWhite frt">
								<a href="#" class="btnOne">
									이전<em class="one_arr prav_ar">이전</em>
								</a>
							</span>
							<span class="button btNext">
								<div type="button" onclick="paymentSubmit()" class="btnOne">결제하기</div>
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
			<input type="hidden" name="basicSum" value="${basicSum}">
			<input type="hidden" name="basicSumVAT" value="${basicSumVAT}">
			<input type="hidden" name="discountSum" value="${discountSum}">
			<input type="hidden" name="discountSumVAT" value="${discountSumVAT}">
			<input type="hidden" name="priceSum" value="${priceSum}">
			<input type="hidden" name="deliverySum" id="deliverySum" value="">
			<input type="hidden" name="paymentSum" id="paymentSum" value="">
		</form>
	</body>
</html>