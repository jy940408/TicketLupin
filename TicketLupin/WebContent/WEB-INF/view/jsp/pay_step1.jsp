<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/pay_step1_1.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/pay_step1_2.css">

	<script>
		function getIframeValue(){
			
			var obj = document.frames("seatIframe").document.forms[0];
			obj.submit();
			
		}
	</script>
  	<title>티켓루팡 공연 예매</title>
</head>
<body style="overflow: hidden;">

<div class="section_onestop" id="section_onestop">
    <!--좌석선택  영역-->
    <div class="wrap_select">
        <div class="box_seat_top">
            <h3 class="tit_seat">좌석 선택<span class="tit_s txt_prod_name" title="뮤지컬 〈몬테크리스토〉 10주년 기념 공연">뮤지컬 〈몬테크리스토〉 10주년 기념 공연</span>
                <span class="seat_wrap_sel">
                    <!-- 셀렉트박스 -->
                    <select id="scheduleNo" name="scheduleNo" class="sel_cate" onchange="selectSchedule(this);" onfocus="setOriginNo(this);">
						<option value="100129">2021.02.18 (목) 19:00</option>
						<option value="100121">2021.02.19 (금) 15:00</option>
						<option value="100130" selected="selected">2021.02.19 (금) 20:00</option>
						<option value="100122">2021.02.20 (토) 14:00</option>
						<option value="100131">2021.02.20 (토) 19:00</option>
						<option value="100136">2021.02.21 (일) 15:00</option>
						<option value="100113">2021.02.22 (월) 19:00</option>
						<option value="100123">2021.02.24 (수) 15:00</option>
						<option value="100116">2021.02.24 (수) 20:00</option>
						<option value="100132">2021.02.25 (목) 19:00</option>
						<option value="100133">2021.02.26 (금) 20:00</option>
						<option value="100125">2021.02.27 (토) 14:00</option>
						<option value="100134">2021.02.27 (토) 19:00</option>
						<option value="100137">2021.02.28 (일) 15:00</option>
						<option value="100154">2021.03.01 (월) 14:00</option>
						<option value="100138">2021.03.01 (월) 19:00</option>
						<option value="100140">2021.03.03 (수) 20:00</option>
						<option value="100144">2021.03.04 (목) 19:00</option>
						<option value="100146">2021.03.05 (금) 15:00</option>
						<option value="100141">2021.03.05 (금) 20:00</option>
						<option value="100148">2021.03.06 (토) 14:00</option>
						<option value="100150">2021.03.06 (토) 19:00</option>
						<option value="100152">2021.03.07 (일) 15:00</option>
						<option value="100139">2021.03.08 (월) 19:00</option>
						<option value="100142">2021.03.10 (수) 20:00</option>
						<option value="100145">2021.03.11 (목) 19:00</option>
						<option value="100147">2021.03.12 (금) 15:00</option>
						<option value="100143">2021.03.12 (금) 20:00</option>
						<option value="100149">2021.03.13 (토) 14:00</option>
						<option value="100151">2021.03.13 (토) 19:00</option>
						<option value="100153">2021.03.14 (일) 15:00</option>
					</select>
                </span>
            </h3>
        </div>
        <!-- 좌석영역-->
        <div class="wrap_seat">
            <div class="btn_extension"><a href="#" class="btn_plus" id="btn_map_p">확대</a><a href="#" class="btn_minus" id="btn_map_m">축소</a></div>
			  <iframe src="${pageContext.request.contextPath}/Reservation/ReservationStep1Seat.do" name="seatIframe" style="width:100%; height:90%"></iframe>
			<div class="wrap_seat_box">
				<div class="seat_box "><!-- on class 컨트롤 -->
					<!-- <div class="seat_btn" id="txtSelectSeatInfo" onclick="$(this).parent().toggleClass('open');return false;">좌석을 선택해 주세요.</div> -->
					<div class="seat_choice">
						<ul id="partSeatSelected" class="seat_info"></ul>
					</div><!-- //seat_choice끝 -->
				</div>
            </div>
        </div>
        <!--// 좌석영역-->
    </div>
    <!--// 좌석선택  영역-->
    <!--티켓 정보 영역-->
    <div class="wrap_ticket_info">
        	<h2 class="logo_onestop"><a href="#none"><img src="../icon/lupinlogo.png" style="width:116px; height:22px;" alt="티켓루팡 로고"></a></h2>
        <div class="box_info box_map">
			<div class="minimap_scroller" id="minimap_scroller">
				<div class="map_seat">
				<iframe src="../image/pay_step1_seat.svg" style="width:245px; height:180px;"></iframe>
				</div>
			</div>
			<div>
				<div id="minimap" class="  iScrollLoneScrollbar">
					<div id="minimap-indicator" style="transition-duration: 0ms; display: none; transform: translate(0px, 0px); transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); visibility: visible;"></div>
				</div>
			</div>
        </div>
        <div class="wrap_seat_list ">
            <!-- 기본 좌석등급/잔여석-->
            <div class="seat_list">
                <h3 class="select_tit">좌석등급/잔여석<span class="ico_info" onmouseover="toolTip('layer_ticket');">유의사항</span><a href="#none" id="btnReloadSchedule" class="btn_flexible btn_flexible_ico2 btn_detail"><span>새로고침</span></a></h3>
                    <div class="box_seat">
                        <div class="box_seat_inner box_seat_area">
                        <table class="tbl">
                                <caption class="hide"></caption>
                                <colgroup>
                                    <col style="width:26px;">
                                    <col>
                                    <col style="width:65px;">
                                    <col style="width:60px;">
                                </colgroup>
                                <tbody id="partSeatGrade">
									<tr>
										<th class="seat_color"><em class="seat_color seat_vip" style="background-color:#BEA886"></em></th>
										<td class="seat_name">VIP석(금,토,일,공휴일)</td>
										<td class="price">150,000원</td>
										<td class="seat_remain">0석</td>
									</tr><tr>
										<th class="seat_color"><em class="seat_color seat_vip" style="background-color:#9076FF"></em></th>
										<td class="seat_name">R석(금,토,일,공휴일)</td>
										<td class="price">130,000원</td>
										<td class="seat_remain">2석</td>
									</tr><tr>
										<th class="seat_color"><em class="seat_color seat_vip" style="background-color:#FF8AB4"></em></th>
										<td class="seat_name">S석(금,토,일,공휴일)</td>
										<td class="price">100,000원</td>
										<td class="seat_remain">0석</td>
									</tr><tr>
										<th class="seat_color"><em class="seat_color seat_vip" style="background-color:#70D0EA"></em></th>
										<td class="seat_name">A석(금,토,일,공휴일)</td>
										<td class="price">70,000원</td>
										<td class="seat_remain">0석</td>
									</tr>
								</tbody>
                            </table>
                        </div>
                    </div>
                    <!--유의사항 레이어팝업-->
                    <div class="layer_ticket" id="layer_ticket" style="position: absolute; top: 15px; left: 0px; width: 238px; display: none;">
                        <div class="layer_arr" style="left:114px;"></div>
                            <div class="tk_header">
                                <div class="tk_header1">
                                    <span class="tk_tit"></span>
                                </div>
                            </div>
                            <div class="tk_middle">
                                <div class="tk_middle1">
                                    <div>
                                        <p>좌석선택 이후 5분이내 결제가 완료되지 않을 시 선택하신 좌석의 선점 기회를 잃게 됩니다.</p>
                                    </div>
                                </div>
                        </div>
                        <div class="tk_footer"><div class="tk_footer1"></div></div>
                    </div>
                    <!--// 유의사항 레이어팝업-->
            </div>
            <!--//기본 좌석등급/잔여석-->
        </div>
        <div class="box_info_bm">
            <div class="btn_onestop">
            	<form name="nextTicketSelection" action="${pageContext.request.contextPath}/Reservation/ReservationStep2.do" method="get">
            		<input type="hidden" name="seatHidden" value="">
            		 <span class="button btNext"><button type="submit" id="nextTicketSelection" class="btnOne btnOneB">좌석 선택 완료<em class="one_arr next_ar">다음</em></button></span>
            	</form>
            </div>
        </div>
    <!--// 티켓 정보 영역 -->
    </div>
</div>


</body></html>