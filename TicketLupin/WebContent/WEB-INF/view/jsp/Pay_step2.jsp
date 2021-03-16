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
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script>
			$.ajax({
				
				
				
			});
		</script>
	</head>
	<body>
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
							<a href="#" class="step3">배송/결제</a>
						</li>
					</ul>
				</div>
				<div class="box">
					<form id="frm" method="post" name="frm">
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
												<select name="volume">
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
												<select name="volume">
													<c:forEach var="i" begin="0" end="${vip}">
														<option value="${i}">${i}매</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td class="txt_left" >3인 패키지</td>
											<td>18,000원 할인</td>
											<td>72,000원</td>
											<td>
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
													<c:forEach var="i" begin="0" end="${r}">
														<option value="${i}">${i}매</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td class="txt_left" >3인 패키지</td>
											<td>18,000원 할인</td>
											<td>72,000원</td>
											<td>
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
													<c:forEach var="i" begin="0" end="${s}">
														<option value="${i}">${i}매</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td class="txt_left" >3인 패키지</td>
											<td>18,000원 할인</td>
											<td>72,000원</td>
											<td>
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
													<c:forEach var="i" begin="0" end="${a}">
														<option value="${i}">${i}매</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td class="txt_left" >3인 패키지</td>
											<td>18,000원 할인</td>
											<td>72,000원</td>
											<td>
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
												<select name="volume">
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
										<select name="volume">
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
										<select name="volume">
											<option value="0" selected>0매</option>
											<option value="1">1매</option>
										</select>
									</td>
								</tr>
							</table>
						</div>
						
					</form>
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
									<span>0</span>원
								</span>
							</p>
							<ul class="list_tkpay">
								<li>
									<span class="tk_tit">기본가</span>
									<span class="pay">
										<span>0</span>원
									</span>
								</li>
								<li>
									<span class="tk_tit">가격할인</span>
									<span class="pay">
										<span>0</span>원
									</span>
								</li>
								<li>
									<span class="tk_tit">쿠폰할인</span>
									<span class="pay">
										<span>0</span>원
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
							<a href="${pageContext.request.contextPath}/Reservation/ReservationStep3.do" class="btnOne">다음</a>
						</span>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>