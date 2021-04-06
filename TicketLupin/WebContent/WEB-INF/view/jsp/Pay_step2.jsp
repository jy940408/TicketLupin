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
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/pay_step2.css">
		<script>
			$(document).ready(function(){

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/				

				var vipCount = 0;
				$(".vip").on("change", function(){
					vipCount = 0;
					
					for(var j = 0 ; j < 6 ; j++){
						vipCount += Number($(".vip").eq(j).val());
					}
					
					if(vipCount > Number(${requestScope.gradeCount.VIP})){
						alert(${requestScope.gradeCount.VIP} + "매를 넘을 수 없습니다!");
						return $(this).val(0);	
					}
					
					$("#vipSum").contents()[0].textContent = vipCount;
				});
				
				var rCount = 0;
				$(".r").on("change", function(){
					rCount = 0;
					
					for(var j = 0 ; j < 6 ; j++){
						rCount += Number($(".r").eq(j).val());
					}
					
					if(rCount > Number(${requestScope.gradeCount.R})){
						alert(${requestScope.gradeCount.R} + "매를 넘을 수 없습니다!");
						return $(this).val(0);	
					}
					
					$("#rSum").contents()[0].textContent = rCount;
				});
				
				var sCount = 0;
				$(".s").on("change", function(){
					sCount = 0;
					
					for(var j = 0 ; j < 6 ; j++){
						sCount += Number($(".s").eq(j).val());
					}
					
					if(sCount > Number(${requestScope.gradeCount.S})){
						alert(${requestScope.gradeCount.S} + "매를 넘을 수 없습니다!");
						return $(this).val(0);	
					}
					
					$("#sSum").contents()[0].textContent = sCount;
					
				});
				
				var aCount = 0;
				$(".a").on("change", function(){
					aCount = 0;
					for(var j = 0 ; j < 6 ; j++){
						aCount += Number($(".a").eq(j).val());
					}
					
					if(aCount > Number(${requestScope.gradeCount.A})){
						alert(${requestScope.gradeCount.A} + "매를 넘을 수 없습니다!");
						return $(this).val(0);	
					}
					
					$("#aSum").contents()[0].textContent = aCount;
				});
				
/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
				
				$("select").on("change",function(){	
					
					var vipBasic = $("#vipBasic").val(); if($("#vipBasic").val() === undefined){vipBasic = Number(0);}
					var vipSpecial = $("#vipSpecial").val(); if($("#vipSpecial").val() === undefined){vipSpecial = Number(0);}
					var vipYouth = $("#vipYouth").val(); if($("#vipYouth").val() === undefined){vipYouth = Number(0);}
					var vip4to6 = $("#vip4to6").val(); if($("#vip4to6").val() === undefined){vip4to6 = Number(0);}
					var vip1to3 = $("#vip1to3").val(); if($("#vip1to3").val() === undefined){vip1to3 = Number(0);}
					var vipVeterans = $("#vipVeterans").val(); if($("#vipVeterans").val() === undefined){vipVeterans = Number(0);}
					
					var rBasic = $("#rBasic").val(); if($("#rBasic").val() === undefined){rBasic = Number(0);}
					var rSpecial = $("#rSpecial").val(); if($("#rSpecial").val() === undefined){rSpecial = Number(0);}
					var rYouth = $("#rYouth").val(); if($("#rYouth").val() === undefined){rYouth = Number(0);}
					var r4to6 = $("#r4to6").val(); if($("#r4to6").val() === undefined){r4to6 = Number(0);}
					var r1to3 = $("#r1to3").val(); if($("#r1to3").val() === undefined){r1to3 = Number(0);}
					var rVeterans = $("#rVeterans").val(); if($("#rVeterans").val() === undefined){rVeterans = Number(0);}
					
					var sBasic = $("#sBasic").val(); if($("#sBasic").val() === undefined){sBasic = Number(0);}
					var sSpecial = $("#sSpecial").val(); if($("#sSpecial").val() === undefined){sSpecial = Number(0);}
					var sYouth = $("#sYouth").val(); if($("#sYouth").val() === undefined){sYouth = Number(0);}
					var s4to6 = $("#s4to6").val(); if($("#s4to6").val() === undefined){s4to6 = Number(0);}
					var s1to3 = $("#s1to3").val(); if($("#s1to3").val() === undefined){s1to3 = Number(0);}
					var sVeterans = $("#sVeterans").val(); if($("#sVeterans").val() === undefined){sVeterans = Number(0);}
					
					var aBasic = $("#aBasic").val(); if($("#aBasic").val() === undefined){aBasic = Number(0);}
					var aSpecial = $("#aSpecial").val(); if($("#aSpecial").val() === undefined){aSpecial = Number(0);}
					var aYouth = $("#aYouth").val(); if($("#aYouth").val() === undefined){aYouth = Number(0);}
					var a4to6 = $("#a4to6").val(); if($("#a4to6").val() === undefined){a4to6 = Number(0);}
					var a1to3 = $("#a1to3").val(); if($("#a1to3").val() === undefined){a1to3 = Number(0);}
					var aVeterans = $("#aVeterans").val(); if($("#aVeterans").val() === undefined){aVeterans = Number(0);}
					
/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
					
					for(var i = 0 ; i < 32 ; i++){
						if($(".discount").eq(i).val() == "undefined"){
							$(".discount").eq(i).val('0');
						}
					}	
					
/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

					var svipprice = 0;
					if(${not empty detail.svipprice}){
						svipprice = ${detail.svipprice};
					}
					var srprice = 0;
					if(${not empty detail.srprice}){
						srprice = ${detail.srprice};
					}
					var ssprice = 0;
					if(${not empty detail.ssprice}){
						ssprice = ${detail.ssprice};
					}
					var saprice = 0;
					if(${not empty detail.saprice}){
						saprice = ${detail.saprice};
					}
					
					var basicSum_ = (vipBasic*svipprice) + (rBasic*srprice) + (sBasic*ssprice) + (aBasic*saprice);
					var basicSum = basicSum_*0.9;
					var basicSumVAT = basicSum_*0.1;
					var discountSum_ = (vipSpecial*(svipprice*0.7)) + (vipYouth*(svipprice*0.8)) + (vip4to6*(svipprice*0.8)) + (vip1to3*(svipprice*0.8)) + (vipVeterans*(svipprice*0.7)) +
									(rSpecial*(srprice*0.7)) + (rYouth*(srprice*0.8)) + (r4to6*(srprice*0.8)) + (r1to3*(srprice*0.8)) + (rVeterans*(srprice*0.7)) +
									(sSpecial*(ssprice*0.7)) + (sYouth*(ssprice*0.8)) + (s4to6*(ssprice*0.8)) + (s1to3*(ssprice*0.8)) + (sVeterans*(ssprice*0.7)) +
									(aSpecial*(saprice*0.7)) + (aYouth*(saprice*0.8)) + (a4to6*(saprice*0.8)) + (a1to3*(saprice*0.8)) + (aVeterans*(saprice*0.7));
					var discountSum = discountSum_*0.9;
					var discountSumVAT = discountSum_*0.1;
					var VATSum = basicSumVAT + discountSumVAT;
					var priceSum = basicSum + discountSum;
					var paymentAmount = priceSum + VATSum;
					
					$("#priceSum").html(priceSum.toLocaleString());
					$("#basicSum").html(basicSum.toLocaleString());
					$("#discountSum").html(discountSum.toLocaleString());
					$("#VAT").html(VATSum.toLocaleString());
					$("#paymentAmount").html(paymentAmount.toLocaleString());
					
					
					$("#basicSumP").val(basicSum);
					$("#basicSumVATP").val(basicSumVAT);
					$("#discountSumP").val(discountSum);
					$("#discountSumVATP").val(discountSumVAT);
					$("#paymentAmountP").val(paymentAmount);
					$("#priceSumP").val(priceSum);
					
				})
				
			});
			
			function nextSubmit(){
				
				var vipCount = 0;
				for(var i = 0 ; i < 6 ; i++){
					vipCount += Number($(".vip").eq(i).val());
				}
				
				var rCount = 0;	
				for(var j = 0 ; j < 6 ; j++){
					if(isNaN(Number($(".r").eq(j).val()))){
						rCount = 0;
					}else{
						rCount += Number($(".r").eq(j).val());	
					}
				}
				
				
				var sCount = 0;
				for(var j = 0 ; j < 6 ; j++){
					if(isNaN(Number($(".s").eq(j).val()))){
						sCount = 0;
					}else{
						sCount += Number($(".s").eq(j).val());	
					}
				}
	
				var aCount = 0;
				for(var j = 0 ; j < 6 ; j++){
					if(isNaN(Number($(".a").eq(j).val()))){
						aCount = 0;
					}else{
						aCount += Number($(".a").eq(j).val());	
					}
				}
				
				if(vipCount != ${requestScope.gradeCount.VIP}){
					alert("티켓 가격을 모두 선택해주세요!");
					return;
				}else if (rCount != ${requestScope.gradeCount.R}){
					alert("티켓 가격을 모두 선택해주세요!");
					return;
				}else if (sCount != ${requestScope.gradeCount.S}){
					alert("티켓 가격을 모두 선택해주세요!");
					return;
				}else if (aCount != ${requestScope.gradeCount.A}){
					alert("티켓 가격을 모두 선택해주세요!");
					return;
				}
				
				document.frm.action = "${pageContext.request.contextPath}/Reservation/ReservationStep3.do";
			 	document.frm.method = "get";
			 	document.frm.submit(); 
				return;
			
				
			}
		</script>
	</head>
	<body>
		<div class="section">	
		<form id="frm" name="frm">
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
							<a href="#" class="step2" style="font-weight:bold;">가격 선택</a>
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
												<td colspan="2"><span><c:out value="${vip}"/></span>매 중<em><span id="vipSum">0</span></em>매 선택</td>
											</c:if>
										</c:forEach>
									</tr>
									<tr>
										<td class="txt_left">기본가</td>
										<td></td>
										<td><fmt:formatNumber value="${detail.svipprice }" pattern="#,###" />원</td>
										<td>
											<select name="discount1_1" id="vipBasic" class="Basic vip discount">
												<c:forEach var="i" begin="0" end="${vip}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left">공연재개 특별응원 30%</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.3}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.7}" pattern="#,###" />원</td>
										<td>
											<select name="discount1_2" id="vipSpecial" class="Special vip discount">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >청소년 할인(본인에 한함)20%</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.2}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.8}" pattern="#,###" />원</td>
										<td>
											<select name="discount1_3" id="vipYouth" class="Youth vip discount">
												<c:forEach var="i" begin="0" end="${vip}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(4~6급,경증) 20%</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.2}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.8}" pattern="#,###" />원</td>
										<td>
											<select name="discount1_4" id="vip4to6" class="4to6 vip discount">
												<c:forEach var="i" begin="0" end="${vip}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(1~3급,중증) 20%</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.2}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.8}" pattern="#,###" />원</td>
										<td>
											<select name="discount1_5" id="vip1to3" class="1to3 vip discount">
												<c:forEach var="i" begin="0" end="${vip}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td  class="txt_left">국가유공자 30%</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.3}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.7}" pattern="#,###" />원</td>
										<td>
											<select name="discount1_6" id="vipVeterans" class="Veterans vip discount">
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
												<td colspan="2"><span><c:out value="${r}"/></span>매 중<em id="rSum">0</em>매 선택</td>
											</c:if>
										</c:forEach>
									</tr>
									<tr>
										<td class="txt_left">기본가</td>
										<td></td>
										<td><fmt:formatNumber value="${detail.svipprice}" pattern="#,###" />원</td>
										<td>
											<select name="discount2_1" id="rBasic" class="Basic r discount">
												<c:forEach var="i" begin="0" end="${r}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left">공연재개 특별응원 30%</td>
										<td><fmt:formatNumber value="${detail.srprice*0.3}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.srprice*0.7}" pattern="#,###" />원</td>
										<td>
											<select name="discount2_2" id="rSpecial" class="Special r discount">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >청소년 할인 20%</td>
										<td><fmt:formatNumber value="${detail.srprice*0.2}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.srprice*0.8}" pattern="#,###" />원</td>
										<td>
											<select name="discount2_3" id="rYouth" class="Youth r discount">
												<c:forEach var="i" begin="0" end="${r}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(4~6급,경증) 20%</td>
										<td><fmt:formatNumber value="${detail.srprice*0.2}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.srprice*0.8}" pattern="#,###" />원</td>
										<td>
											<select name="discount2_4" id="r4to6" class="4to6 r discount">
												<c:forEach var="i" begin="0" end="${r}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(1~3급,중증) 20%</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.2}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.8}" pattern="#,###" />원</td>
										<td>
											<select name="discount2_5" id="r1to3" class="1to3 r discount">
												<c:forEach var="i" begin="0" end="${r}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td  class="txt_left">국가유공자 30%</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.3}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.svipprice*0.7}" pattern="#,###" />원</td>
										<td>
											<select name="discount2_6" id="rVeterans" class="Veterans r discount">
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
												<td colspan="2"><span><c:out value="${s}"/></span>매 중<em id="sSum">0</em>매 선택</td>
											</c:if>
										</c:forEach>
									</tr>
									<tr>
										<td class="txt_left">기본가</td>
										<td></td>
										<td><fmt:formatNumber value="${detail.ssprice}" pattern="#,###" />원</td>
										<td>
											<select name="discount3_1" id="sBasic" class="Basic s discount">
												<c:forEach var="i" begin="0" end="${s}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left">공연재개 특별응원 30%</td>
										<td><fmt:formatNumber value="${detail.ssprice*0.3}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.ssprice*0.7}" pattern="#,###" />원</td>
										<td>
											<select name="discount3_2" id="sSpecial" class="Special s discount">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >청소년 할인 20%</td>
										<td><fmt:formatNumber value="${detail.ssprice*0.2}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.ssprice*0.8}" pattern="#,###" />원</td>
										<td>
											<select name="discount3_3" id="sYouth" class="Youth s discount">
												<c:forEach var="i" begin="0" end="${s}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(4~6급,경증) 20%</td>
										<td><fmt:formatNumber value="${detail.ssprice*0.2}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.ssprice*0.8}" pattern="#,###" />원</td>
										<td>
											<select name="discount3_4" id="s4to6" class="4to6 s discount">
												<c:forEach var="i" begin="0" end="${s}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(1~3급,중증) 20%</td>
										<td><fmt:formatNumber value="${detail.ssprice*0.2}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.ssprice*0.8}" pattern="#,###" />원</td>
										<td>
											<select name="discount3_5" id="s1to3" class="1to3 s discount">
												<c:forEach var="i" begin="0" end="${s}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td  class="txt_left">국가유공자 30%</td>
										<td><fmt:formatNumber value="${detail.ssprice*0.3}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.ssprice*0.7}" pattern="#,###" />원</td>
										<td>
											<select name="discount3_6" id="sVeterans" class="Veterans s discount">
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
												<td colspan="2"><span><c:out value="${a}"/></span>매 중<em id="aSum">0</em>매 선택</td>
											</c:if>
										</c:forEach>
									</tr>
									<tr>
										<td class="txt_left">기본가</td>
										<td></td>
										<td><fmt:formatNumber value="${detail.saprice}" pattern="#,###" />원</td>
										<td>
											<select name="discount4_1" id="aBasic" class="Basic a discount">
												<c:forEach var="i" begin="0" end="${a}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left">공연재개 특별응원 30%</td>
										<td><fmt:formatNumber value="${detail.saprice*0.3}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.saprice*0.7}" pattern="#,###" />원</td>
										<td>
											<select name="discount4_2" id="aSpecial" class="Special a discount">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >청소년 할인 20%</td>
										<td><fmt:formatNumber value="${detail.saprice*0.2}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.saprice*0.8}" pattern="#,###" />원</td>
										<td>
											<select name="discount4_3" id="aYouth" class="Youth a discount">
												<c:forEach var="i" begin="0" end="${a}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(4~6급,경증) 20%</td>
										<td><fmt:formatNumber value="${detail.saprice*0.2}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.saprice*0.8}" pattern="#,###" />원</td>
										<td>
											<select name="discount4_4" id="a4to6" class="4to6 a discount">
												<c:forEach var="i" begin="0" end="${a}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td class="txt_left" >복지할인(1~3급,중증) 20%</td>
										<td><fmt:formatNumber value="${detail.saprice*0.2}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.saprice*0.8}" pattern="#,###" />원</td>
										<td>
											<select name="discount4_5" id="a1to3" class="1to3 a discount">
												<c:forEach var="i" begin="0" end="${a}">
													<option value="${i}">${i}매</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td  class="txt_left">국가유공자 30%</td>
										<td><fmt:formatNumber value="${detail.saprice*0.3}" pattern="#,###" />원&nbsp;할인</td>
										<td><fmt:formatNumber value="${detail.saprice*0.7}" pattern="#,###" />원</td>
										<td>
											<select name="discount4_6" id="aVeterans" class="Veterans a discount">
												<option value="0" selected>0매</option>
												<option value="1">1매</option>
											</select>
										</td>
									</tr>
								</table>
							</c:if>
						</c:forEach>
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
									<span class="tk_tit">할인가</span>
									<span class="pay">
										<span id="discountSum">0</span>원
									</span>
								</li>
							</ul>
						</div>
						<div class="box_total_inner">
							<p class="tk_b">
								<span class="tk_tit tk_tit_b">예매수수료</span>
								<span class="pay pay_comp">
									<span class="txt_vip" style="display:none">멜론VIP혜택★</span>
									<span id="VAT">0</span>원
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
							
						</ul>
					</div>
					<div class="btn_onestop">
						<span class="button btWhite frt">
							<a href="#" class="btnOne">
								이전<em class="one_arr prav_ar">이전</em>
							</a>
						</span>
						<span class="button btNext">
							<a><div type="button" class="btnOne" onclick="nextSubmit()">다음</a>
						</span>
					</div>
				</div>
			</div>
			
			<c:forEach var="as" items="${arraySeat}">
				<input type="hidden" name="arraySeat" value="${as}">
			</c:forEach>
			<input type="hidden" name="sidx" value="${sidx}">
			<input type="hidden" name="title" value="${title}">
			<input type="hidden" name="comDate" value="${comDate}">
			<input type="hidden" name="round" value="${round}">
			<input type="hidden" name="basicSum" id="basicSumP" value="">
			<input type="hidden" name="basicSumVAT" id="basicSumVATP" value="">
			<input type="hidden" name="discountSum" id="discountSumP" value="">
			<input type="hidden" name="discountSumVAT" id="discountSumVATP" value="">
			<input type="hidden" name="priceSum" id="priceSumP" value="">
			<input type="hidden" name="paymentAmount" id="paymentAmountP" value="">
			</form>
		</div>
	</body>
</html>