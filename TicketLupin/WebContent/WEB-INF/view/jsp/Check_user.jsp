<%@page import="com.TicketLupin.web.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mid = (String) session.getAttribute("mid");
	String mpwd = (String)session.getAttribute("mpwd");
	MemberDao md = new MemberDao();
	MemberVo mv = md.getMember(mid, mpwd);
%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>티켓루팡 : 회원인증</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Login_FI_FP_Join__.css">
        <script>
        	function suc(){
        		
        		
        		if (document.frm.mpwd.value == ""){
				  	alert("비밀번호를 입력해주세요.");
				  	document.frm.mpwd.focus();
				  	return;
				}else if (document.frm.mpwd.value != document.frm.mpwd2.value){
				  	alert("비밀번호를 맞지 않습니다. 다시 입력해주세요.");
				  	document.frm.mpwd.focus();
				  	return;
			  	}
			  
				alert("수정하러~~~~");
			  	document.frm.action ="<%=request.getContextPath()%>/Member/MemberModifyForm.do?mid=<%=mv.getMid()%>";
			  	document.frm.method = "post";
			  	document.frm.submit(); 
			  	return;
        	}
        </script>
    </head>
    <body>
        <!-- header -->
        <div id="header">
            <a href="<%=request.getContextPath() %>/Main/MainPage.do" target="_blank" title="티켓루팡 페이지 보러가기">
            	<img src="../icon/lupinlogo.png" id="logo">
            </a>
		</div>
		<br/>
		<h2 align="center">회원 인증</h2>
		<h3 align="center">고객님의 회원인증을 위해 비밀번호를 입력해주세요.</h3>
		<br/>

        <!-- wrapper -->
        <div id="wrapper">

            <!-- content-->
            <div id="content">
				<form name="frm">
					<input type="hidden" value="<%=mv.getMidx()%>">
	                <!-- ID -->
	                <div>
	                    <h3 class="join_title">
	                        <label for="id">아이디</label>
	                    </h3>
	                    <span style="padding:10px;">
	                       <strong><%=mv.getMid()%></strong>
	                    </span>
	                    <span class="error_next_box"></span>
	                </div>
	
	                <!-- PW1 -->
	                <div>
	                    <h3 class="join_title"><label for="pswd1">비밀번호</label></h3>
	                    <span class="box int_pass">
	                        <input type="password" id="pswd1" class="int" maxlength="20" name="mpwd">
	                        <input type="hidden" id="pswd2" class="int" maxlength="20" name="mpwd2" value="<%=mv.getMpwd()%>">
	                        <span id="alertTxt">사용불가</span>
	                  
	                    </span>
	                    <span class="error_next_box"></span>
	                </div>
				
	                <!-- JOIN BTN-->
	                <div class="btn_area">
	                    <button type="button" id="btnJoin" onclick="suc()">
	                        <span>확인</span>
	                    </button>
	                </div>
				
				</form>
            </div> 
            <!-- content-->

        </div> 
        <!-- wrapper -->
		<script src="../js/Membercheck.js"></script>
    </body>
</html>