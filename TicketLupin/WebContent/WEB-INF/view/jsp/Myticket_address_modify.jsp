<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
        <meta charset="UTF-8">
        <title>티켓루팡 : 기본 배송지 관리</title>
        <link rel="stylesheet" href="./css/Login_FI_FP_Join__.css">
	</head>
	<body>
		<!--header-->
		<div id="header">
            <a href="https://ticket.melon.com/main/index.htm" target="_blank" title="티켓루팡 페이지 보러가기"><img src="../icon/lupinlogo.png" id="logo"></a>
        </div>
		
		<!-- wrapper -->
        <div id="wrapper">
			<!-- content-->
            <div id="content">
				
				<!--배송관리 안내문-->
				<div>
					<h2 align="center">기본배송지관리</h2>
					<h4>기본 배송지를 미리 입력해주세요.</h4>
					<h4>예매 진행 시 좀 더 빠르고 편리하게 이용하실 수 있습니다.</h4>
					<h4>-예매 후, 배송지 변경은 예매상세>티켓수령방법>배송지변경을 통해 가능합니다.</h4>
					<br/>
					<h4>배송지 정보를 입력해주세요.</h4>
				</div>
				
				<!-- name -->
				<div>
                    <h3 class="join_title"><label for="name">받는 분 성함</label></h3>
                    <span class="box int_name">
                        <input type="text" id="name" class="int" maxlength="20">
                    </span>
                    <span class="error_next_box"></span>
                </div>
				
				<!-- MOBILE -->
                <div>
                    <h3 class="join_title"><label for="phoneNo">휴대전화</label></h3>
                    <span class="box int_mobile">
                        <input type="tel" id="mobile" class="int" maxlength="16" placeholder="전화번호 입력">
                    </span>
                    <span class="error_next_box"></span>    
                </div>
		
				<!--adress-->
				<div>
					<h3 class="join_title"><label for="address">주소</label ></h3>
					<button type="button" id="btninzung">
						<span>검색</span>
					</button>
					<span class= "box int_adress">
						<input type = "text" id = "adress" class="int" maxlength = "20">
					</span>
					<span class= "box int_adress1">
						<input type = "text" id = "adress2" class="int" maxlength = "20">
					</span>
					<span class= "box int_adress2">
						<input type = "text" id = "adress2" class="int" maxlength = "20">
					</span>
					<span class="error_next_box"></span>  
				</div>
				
				 <!-- JOIN BTN-->
				<div class="btn_area">
					<button type="button" id="btnJoin">
						<span>배송지 정보 입력</span>
					</button>
				</div>
		    </div> 
            <!-- content-->

        </div> 
	    <!-- wrapper -->
		<script src="../js/Myticket_address_modify.js"></script>
	</body>
</html>>