<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
	<head>
		<title>배송/결제</title>
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/pay_step2.css">
		<script>
			$(document).ready(function(){
				
				$("select").on("change",function(){	
					
					var vipBasic = $("#vipBasic").val();
					var vipSpecial = $("#vipSpecial").val();
					var vip3Package = $("#vip3Package").val();
					var vip4Package = $("#vip4Package").val();
					var vipYouth = $("#vipYouth").val();
					var vip4to6 = $("#vip4to6").val();
					var vip1to3 = $("#vip1to3").val();
					var vipVeterans = $("#vipVeterans").val();
					
					var rBasic = $("#rBasic").val();
					var rSpecial = $("#rSpecial").val();
					var r3Package = $("#r3Package").val();
					var r4Package = $("#r4Package").val();
					var rYouth = $("#rYouth").val();
					var r4to6 = $("#r4to6").val();
					var r1to3 = $("#r1to3").val();
					var rVeterans = $("#rVeterans").val();
					
					var sBasic = $("#sBasic").val();
					var sSpecial = $("#sSpecial").val();
					var s3Package = $("#s3Package").val();
					var s4Package = $("#s4Package").val();
					var sYouth = $("#sYouth").val();
					var s4to6 = $("#s4to6").val();
					var s1to3 = $("#s1to3").val();
					var sVeterans = $("#sVeterans").val();
					
					var aBasic = $("#aBasic").val();
					var aSpecial = $("#aSpecial").val();
					var a3Package = $("#a3Package").val();
					var a4Package = $("#a4Package").val();
					var aYouth = $("#aYouth").val();
					var a4to6 = $("#a4to6").val();
					var a1to3 = $("#a1to3").val();
					var aVeterans = $("#aVeterans").val();
					
					var coupon = $("#coupon").val();
					var firstPayment = $("#firstPayment").val();

					var basicSum = (vipBasic*150000) + (rBasic*150000) + (sBasic*150000) + (aBasic*150000); 
					var discountSum = (vipSpecial*27000) + (vip3Package*18000) + (vip4Package*27000) + (vipYouth*18000) + (vip4to6*27000) + (vip1to3*27000) + (vipVeterans*27000) +
										(rSpecial*27000) + (r3Package*18000) + (r4Package*27000) + (rYouth*18000) + (r4to6*27000) + (r1to3*27000) + (rVeterans*27000) +
										(sSpecial*27000) + (s3Package*18000) + (s4Package*27000) + (sYouth*18000) + (s4to6*27000) + (s1to3*27000) + (sVeterans*27000) +
										(aSpecial*27000) + (a3Package*18000) + (a4Package*27000) + (aYouth*18000) + (a4to6*27000) + (a1to3*27000) + (aVeterans*27000);
					var couponSum = (coupon*10000) + (firstPayment*5000);
					var priceSum = basicSum - discountSum - couponSum;
					
					console.log("vipBasic: " + vipBasic);
					console.log("basicSum: " + basicSum);
					console.log("discountSum: " + discountSum);
					console.log("couponSum: " + couponSum);
					console.log("priceSum: " + priceSum);
					console.log("더하기 테스트: " + (vipBasic*150000)+(rBasic*150000) + (sBasic*150000) + (aBasic*150000));
					console.log("rbasic: " + rBasic);
					$("#priceSum").html(priceSum);
					$("#priceSum").html(priceSum);
					$("#basicSum").html(basicSum);
					$("#discountSum").html(discountSum);
					$("#couponSum").html(couponSum);
					$("#paymentAmount").html(priceSum);
					
					$("#priceSumP").val(priceSum);
					$("#priceSumP").val(priceSum);
					$("#basicSumP").val(basicSum);
					$("#discountSumP").val(discountSum);
					$("#couponSumP").val(couponSum);
					$("#paymentAmountP").val(priceSum);
					
				})
			});
		</script>
	</head>
	<body>
		<div class="section">	
		<form id="frm" action="${pageContext.request.contextPath}/Reservation/ReservationStep3.do" method="get" name="frm">
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
							<a href="#" class="step3">배송/결제</a>
						</li>
					</ul>
				</div>
				<div class="box">
					<div class="select_price">
						<h3>티켓 가격을 선택하세요.</h3>
						<c:forEach var="gc" items="${gradeCategory}">
							<c:if test="${gc eq 'VIP'}">
								<table>
									<tr id="tr_color">
										<td colspan="2" class="txt_left"><strong>VIP석</strong></td>
										<c:forEach var="count" items="${gradeCount}">
											<c:if test="${count.key == 'VIP'}">
												<c:set var="vip" value="${count.value}"/>
												<td colspan="2"><span><c:out value="${vip}"/></span>매 중<em>0</em>매 선택</td>
											</c:if>
										</c:forEach>
									</tr>
									<tr>
										<td class="txt_left">기본가</td>
										<td></td>
										<td>150,000원</td>
										<td>
											<select name="vipBasic" id="vipBasic" class="Basic vip">
												<c:forEach var="i" begin="0" end="${vip}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left">공연재개 특별응원 30%</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="vipSpecial" id="vipSpecial" class="Special vip">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >3인 패키지</td>
										<td>18,000원 할인</td>
										<td>72,000원</td>
										<td>
											<select name="vip3Package" id="vip3Package" class="3package vip">
												<c:forEach var="i" begin="0" end="${fn:substringBefore(Math.floor(vip/3),'.')}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >4인 패키지</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="vip4Package" id="vip4Package" class="4package vip">
												<c:forEach var="i" begin="0" end="${fn:substringBefore(Math.floor(vip/4),'.')}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >청소년 할인(본인에 한함)20%</td>
										<td>18,000원 할인</td>
										<td>72,000원</td>
										<td>
											<select name="vipYouth" id="vipYouth" class="Youth vip">
												<c:forEach var="i" begin="0" end="${vip}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(4~6급,경증,본인만)</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="vip4to6" id="vip4to6" class="4to6 vip">
												<c:forEach var="i" begin="0" end="${vip}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(1~3급,중증,1인2매)</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="vip1to3" id="vip1to3" class="1to3 vip">
												<c:forEach var="i" begin="0" end="${vip}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td  class="txt_left">국가유공자(본인만)</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="vipVeterans" id="vipVeterans" class="Veterans vip">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
								</table>
							</c:if>
							<c:if test="${gc eq 'R'}">
								<table>
									<tr id="tr_color">
										<td colspan="2" class="txt_left"><strong>R석</strong></td>
										<c:forEach var="count" items="${gradeCount}">
											<c:if test="${count.key == 'R'}">
												<c:set var="r" value="${count.value}"/>
												<td colspan="2"><span><c:out value="${r}"/></span>매 중<em>0</em>매 선택</td>
											</c:if>
										</c:forEach>
									</tr>
									<tr>
										<td class="txt_left">기본가</td>
										<td></td>
										<td>130,000원</td>
										<td>
											<select name="rBasic" id="rBasic" class="Basic r">
												<c:forEach var="i" begin="0" end="${r}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left">공연재개 특별응원 30%</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="rSpecial" id="rSpecial" class="Special r">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >3인 패키지</td>
										<td>18,000원 할인</td>
										<td>72,000원</td>
										<td>
											<select name="r3Package" id="r3Package" class="3package r">
												<c:forEach var="i" begin="0" end="${fn:substringBefore(Math.floor(r/3),'.')}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >4인 패키지</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="r4Package" id="r4Package" class="4package r">
												<c:forEach var="i" begin="0" end="${fn:substringBefore(Math.floor(r/4),'.')}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >청소년 할인(본인에 한함)20%</td>
										<td>18,000원 할인</td>
										<td>72,000원</td>
										<td>
											<select name="rYouth" id="rYouth" class="Youth r">
												<c:forEach var="i" begin="0" end="${r}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(4~6급,경증,본인만)</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="r4to6" id="r4to6" class="4to6 r">
												<c:forEach var="i" begin="0" end="${r}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(1~3급,중증,1인2매)</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="r1to3" id="r1to3" class="1to3 r">
												<c:forEach var="i" begin="0" end="${r}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td  class="txt_left">국가유공자(본인만)</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="rVeterans" id="rVeterans" class="Veterans r">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
								</table>
							</c:if>
							<c:if test="${gc eq 'S'}">
								<table>
									<tr id="tr_color">
										<td colspan="2" class="txt_left"><strong>S석</strong></td>
										<c:forEach var="count" items="${gradeCount}">
											<c:if test="${count.key == 'S'}">
												<c:set var="s" value="${count.value}"/>
												<td colspan="2"><span><c:out value="${s}"/></span>매 중<em>0</em>매 선택</td>
											</c:if>
										</c:forEach>
									</tr>
									<tr>
										<td class="txt_left">기본가</td>
										<td></td>
										<td>100,000원</td>
										<td>
											<select name="sBasic" id="sBasic" class="Basic s">
												<c:forEach var="i" begin="0" end="${s}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left">공연재개 특별응원 30%</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="sSpecial" id="sSpecial" class="Special s">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >3인 패키지</td>
										<td>18,000원 할인</td>
										<td>72,000원</td>
										<td>
											<select name="s3Package" id="s3Package" class="3package s">
												<c:forEach var="i" begin="0" end="${fn:substringBefore(Math.floor(s/3),'.')}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >4인 패키지</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="s4Package" id="s4Package" class="4package s">
												<c:forEach var="i" begin="0" end="${fn:substringBefore(Math.floor(s/4),'.')}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >청소년 할인(본인에 한함)20%</td>
										<td>18,000원 할인</td>
										<td>72,000원</td>
										<td>
											<select name="sYouth" id="sYouth" class="Youth s">
												<c:forEach var="i" begin="0" end="${s}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(4~6급,경증,본인만)</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="s4to6" id="s4to6" class="4to6 s">
												<c:forEach var="i" begin="0" end="${s}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(1~3급,중증,1인2매)</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="s1to3" id="s1to3" class="1to3 s">
												<c:forEach var="i" begin="0" end="${s}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td  class="txt_left">국가유공자(본인만)</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="sVeterans" id="sVeterans" class="Veterans s">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
								</table>
							</c:if>
							<c:if test="${gc eq 'A'}">
								<table>
									<tr id="tr_color">
										<td colspan="2" class="txt_left"><strong>A석</strong></td>
										<c:forEach var="count" items="${gradeCount}">
											<c:if test="${count.key == 'A'}">
												<c:set var="a" value="${count.value}"/>
												<td colspan="2"><span><c:out value="${a}"/></span>매 중<em>0</em>매 선택</td>
											</c:if>
										</c:forEach>
									</tr>
									<tr>
										<td class="txt_left">기본가</td>
										<td></td>
										<td>80,000원</td>
										<td>
											<select name="aBasic" id="aBasic" class="Basic a">
												<c:forEach var="i" begin="0" end="${a}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left">공연재개 특별응원 30%</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="aSpecial" id="aSpecial" class="Special a">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >3인 패키지</td>
										<td>18,000원 할인</td>
										<td>72,000원</td>
										<td>
											<select name="a3Package" id="a3Package" class="3package a">
												<c:forEach var="i" begin="0" end="${fn:substringBefore(Math.floor(a/3),'.')}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >4인 패키지</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="a4Package" id="a4Package" class="4package a">
												<c:forEach var="i" begin="0" end="${fn:substringBefore(Math.floor(s/4),'.')}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >청소년 할인(본인에 한함)20%</td>
										<td>18,000원 할인</td>
										<td>72,000원</td>
										<td>
											<select name="aYouth" id="aYouth" class="Youth a">
												<c:forEach var="i" begin="0" end="${a}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(4~6급,경증,본인만)</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="a4to6" id="a4to6" class="4to6 a">
												<c:forEach var="i" begin="0" end="${a}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(1~3급,중증,1인2매)</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="a1to3" id="a1to3" class="1to3 a">
												<c:forEach var="i" begin="0" end="${a}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td  class="txt_left">국가유공자(본인만)</td>
										<td>27,000원 할인</td>
										<td>63,000원</td>
										<td>
											<select name="aVeterans" id="aVeterans" class="Veterans a">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
								</table>
							</c:if>
						</c:forEach>
						<table>
							<tr id="tr_color">
								<td colspan="4" class="txt_left">할인수단을 선택하세요</td>
							</tr>
							<tr>
								<td colspan="3" class="txt_left">쿠폰 1개</td>
								<td>
									<select name="coupon" id="coupon">
										<option value="0" selected>0매</option>
										<option value="1">1매</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="txt_left">티켓루팡 첫 구매 5천원 할인</td>
								<td>5,000원 할인</td>
								<td></td>
								<td>
									<select name="firstPayment" id="firstPayment">
										<option value="0" selected>0매</option>
										<option value="1">1매</option>
									</select>
								</td>
							</tr>
						</table>
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
									<span id="priceSum">0</span>원
								</span>
							</p>
							<ul class="list_tkpay">
								<li>
									<span class="tk_tit">기본가</span>
									<span class="pay">
										<span id="basicSum">0</span>원
									</span>
								</li>
								<li>
									<span class="tk_tit">가격할인</span>
									<span class="pay">
										<span id="discountSum">0</span>원
									</span>
								</li>
								<li>
									<span class="tk_tit">쿠폰할인</span>
									<span class="pay">
										<span id="couponSum">0</span>원
									</span>
								</li>
								<li>
									<span class="tk_tit">공연예매권</span>
									<span class="pay">
										<span>0</span>원
									</span>
								</li>
							</ul>
						</div>
						<div class="box_total_inner">
							<p class="tk_b">
								<span class="tk_tit tk_tit_b">예매수수료</span>
								<span class="pay pay_comp">
									<span class="txt_vip" style="display:none">멜론VIP혜택★</span>
									<span>0</span>원
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
								<span id="paymentAmount">0</span>원
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
							<button type="submit" class="btnOne">다음</button>
						</span>
					</div>
				</div>
			</div>
			
			<input type="hidden" name="sidx" value="${sidx}">
			<input type="hidden" name="title" value="${title}">
			<input type="hidden" name="comDate" value="${comDate}">
			<input type="hidden" name="round" value="${round}">
			<input type="hidden" name="priceSum" id="priceSumP" value="">
			<input type="hidden" name="basicSum" id="basicSumP" value="">
			<input type="hidden" name="discountSum" id="discountSumP" value="">
			<input type="hidden" name="couponSum" id="couponSumP" value="">
			<input type="hidden" name="paymentAmount" id="paymentAmountP" value="">
			</form>
		</div>
	</body>
</html>