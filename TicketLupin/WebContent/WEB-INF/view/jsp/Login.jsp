<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.TicketLupin.web.service.MemberDao" %>
<%@ page import="com.TicketLupin.web.service.MemberVo" %>
<%@ page import="java.io.PrintWriter" %>
<%
	
%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>티켓루팡 : 로그인</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Login_FI_FP_Join__.css">
        <script type="text/javascript">
	        function check(){
	        	if (document.frm.mid.value == ""){
					alert("아이디를 입력해주세요");
				  	document.frm.mid.focus();
				  	return;
			  	}else if (document.frm.mpwd.value ==""){
				  	alert("비밀번호를 입력해주세요");
				  	document.frm.mpwd.focus();
				  	return;
			  	}
			  
			 	 alert("로그인합니다");
			 	 
			 	 document.frm.action = "<%=request.getContextPath()%>/Member/MemberLoginAction.do";
			 	 document.frm.method = "post";
			 	 document.frm.submit(); 
				 return;
			} 
        </script>
    </head>
    <body>
        <!-- header -->
        <div id="header">
            <a href="<%=request.getContextPath()%>/Main/MainPage.do"  title="티켓루팡 페이지 보러가기"><img src="../icon/lupinlogo.png" id="logo"></a>
        </div>
	        <!-- wrapper -->
	        <div id="wrapper">
	
	            <!-- content-->
	            <div id="content">
	
				<form name="frm" >
		                <!-- ID -->
		                <div>
		                    <h3 class="join_title">
		                        <label for="id">아이디</label>
		                    </h3>
		                    <span class="box int_id">
		                        <input type="text" id="id" class="int" maxlength="20" name="mid">
		               
		                    </span>
		                    <span class="error_next_box"></span>
		                </div>
		
		                <!-- PW1 -->
		                <div>
		                    <h3 class="join_title"><label for="pswd1">비밀번호</label></h3>
		                    <span class="box int_pass">
		                        <input type="text" id="pswd1" class="int" maxlength="20" name="mpwd">
		                        <span id="alertTxt">사용불가</span>
		                  
		                    </span>
		                    <span class="error_next_box"></span>
		                </div>
					
		                <!-- JOIN BTN-->
		                <div class="btn_area">
		                    <button type="button" id="btnJoin" onclick="check()">
		                        <span>로그인</span>
		                    </button>
		                </div>
		        	</form>
				<br/>
				<br/>
	
				<h4 align="center"> 
					<a href="<%=request.getContextPath()%>/Member/MemberIdCheck.do">아이디찾기</a> | 
					<a href="<%=request.getContextPath()%>/Member/MemberPwdCheck.do">비밀번호 찾기</a> | 
					<a href="<%=request.getContextPath()%>/Member/MemberJoin.do">회원가입</a> 
					
				</h4>
	                
	
	            </div> 
	            <!-- content-->
	
	        </div> 
	        <!-- wrapper -->
    </body>
</html>