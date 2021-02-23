<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lnag="ko">
    <head>
        <meta charset="UTF-8">
        <title>티켓루팡 : 비밀번호 변경</title>
        <link rel="stylesheet" href="./css/Login_FI_FP_Join__.css">
        
    </head>
    <body>
        <!-- header -->
        <div id="header">
            <a href="https://ticket.melon.com/main/index.htm" target="_blank" title="티켓루팡 페이지 보러가기"><img src="../icon/lupinlogo.png" id="logo"></a>
        </div>
        </div>
		<br/>
		<h2 align="center">비밀번호 변경</h2>
		<h3 align="center">회원님께서 사용하실 비밀번호를 입력해주세요.</h3>
		<br/>		
		<br/>		

        <!-- wrapper -->
        <div id="wrapper">

            <!-- content-->
            <div id="content">


                <!-- PW1 -->
                <div>
                    <h3 class="join_title"><label for="pswd1">비밀번호</label></h3>
                    <span class="box int_pass">
                        <input type="text" id="pswd1" class="int" maxlength="20">
                        <span id="alertTxt">사용불가</span>
                        <img src="../icon/m_icon_pass.png" id="pswd1_img1" class="pswdImg">
                    </span>
                    <span class="error_next_box"></span>
                </div>

                <!-- PW2 -->
                <div>
                    <h3 class="join_title"><label for="pswd2">비밀번호 재확인</label></h3>
                    <span class="box int_pass_check">
                        <input type="text" id="pswd2" class="int" maxlength="20">
                        <img src="../icon/m_icon_check_disable.png" id="pswd2_img1" class="pswdImg">
                    </span>
                    <span class="error_next_box"></span>
                </div>

                <!-- JOIN BTN-->
                <div class="btn_area">
                    <button type="button" id="btnJoin">
                        <span>변경하기</span>
                    </button>
                </div>

                

            </div> 
            <!-- content-->

        </div> 
        <!-- wrapper -->
		<script src="../js/Find_pwd_step2.js"></script>
    </body>
</html>