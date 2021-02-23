<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lnag="ko">
    <head>
        <meta charset="UTF-8">
        <title>티켓루팡 : 회원인증</title>
        <link rel="stylesheet" href="./css/Login_FI_FP_Join__.css">
        
    </head>
    <body>
        <!-- header -->
        <div id="header">
            <a href="https://ticket.melon.com/main/index.htm" target="_blank" title="티켓루팡 페이지 보러가기"><img src="../icon/lupinlogo.png" id="logo"></a>
		</div>
		<br/>
		<h2 align="center">회원 인증</h2>
		<h3 align="center">고객님의 회원인증을 위해 아이디와 비밀번호를 입력해주세요.</h3>
		<br/>

        <!-- wrapper -->
        <div id="wrapper">

            <!-- content-->
            <div id="content">

                <!-- ID -->
                <div>
                    <h3 class="join_title">
                        <label for="id">아이디</label>
                    </h3>
                    <span class="box int_id">
                        <input type="text" id="id" class="int" maxlength="20">
           
                    </span>
                    <span class="error_next_box"></span>
                </div>

                <!-- PW1 -->
                <div>
                    <h3 class="join_title"><label for="pswd1">비밀번호</label></h3>
                    <span class="box int_pass">
                        <input type="text" id="pswd1" class="int" maxlength="20">
                        <span id="alertTxt">사용불가</span>
                  
                    </span>
                    <span class="error_next_box"></span>
                </div>
			
                <!-- JOIN BTN-->
                <div class="btn_area">
                    <button type="button" id="btnJoin">
                        <span>로그인</span>
                    </button>
                </div>
			

            </div> 
            <!-- content-->

        </div> 
        <!-- wrapper -->
		<script src="../js/Membercheck.js"></script>
    </body>
</html>