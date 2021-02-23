<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lnag="ko">
    <head>
        <meta charset="UTF-8">
        <title>티켓루팡 : 회원가입 인증</title>
        <link rel="stylesheet" href="./css/Login_FI_FP_Join__.css">
        
    </head>
    <body>
        <!-- header -->
        <div id="header">
            <a href="https://ticket.melon.com/main/index.htm" target="_blank" title="티켓루팡 회원가입 페이지 보러가기"><img src="../icon/lupinlogo.png" id="logo"></a>
        </div>
		
		<!-- guide -->
		<br/>
		<h2 align="center">아이디 찾기</h2>
		<h3 align="center">회원님 성함과 가입하신 이메일 주소를 입력해주세요.</h3>
		<br/>		
		<br/>

        <!-- wrapper -->
        <div id="wrapper">

            <!-- content-->
            <div id="content">
             
                <!-- NAME -->
                <div>
                    <h3 class="join_title"><label for="name">이름</label></h3>
                    <span class="box int_name">
                        <input type="text" id="name" class="int" maxlength="20" placeholder="이름 입력" >
                    </span>
                    <span class="error_next_box"></span>
                </div>
                
                <!-- EMAIL -->
                <div>
                    <h3 class="join_title"><label for="email">본인확인 이메일<span class="optional"></span></label></h3>
                    <button type="button" id="btninzung">
                        <span>인증</span>
					</button>
					<span class="box int_email" id= "emailbox1" >
                        <input type="text" id="email" class="int" maxlength="100" placeholder="이메일 입력">
                    </span>
                    <span class="error_next_box">이메일 주소를 다시 확인해주세요.</span> 
                </div>
			

                <!-- MOBILE -->
                <div>
                    <h3 class="join_title"><label for="phoneNo">인증번호</label></h3>
                    <span class="box int_mobile">
                        <input type="tel" id="mobile" class="int" maxlength="16" placeholder="인증번호 입력">
                    </span>
                    <span class="error_next_box"></span>    
                </div>

				

                <!-- JOIN BTN-->
                <div class="btn_area">
                    <button type="button" id="btnJoin">
                        <span>가입하기</span>
                    </button>
                </div>

                

            </div> 
            <!-- content-->

        </div> 
        <!-- wrapper -->
		<script src="../js/Find_ID_step1.js"></script>
    </body>
</html>