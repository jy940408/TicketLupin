<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.TicketLupin.web.service.MemberDao" %>
<%@ page import="com.TicketLupin.web.service.MemberVo" %>
<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>티켓루팡 : 로그인</title>
        
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Login_FI_FP_Join__.css">
        <script>
	        function check(){
	        	if (document.frm.mid.value == ""){
					alert("아이디를 입력해주세요");
				  	document.frm.mid.focus();
				  	return;
			  	}else if(document.frm.mpwd.value ==""){
				  	alert("비밀번호를 입력해주세요");
				  	document.frm.mpwd.focus();
				  	return;
			  	}
			  	
        	
		 	 
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

		<form  name="frm" >
	        <!-- wrapper -->
	        <div id="wrapper">
	
	            <!-- content-->
	            <div id="content">
					
					<%
				    	String err_message = (String)request.getAttribute("err_message");
				    	if(err_message != null){
				    %>
					<p style="padding:5px;">
						<font color="red" size="2"><%=err_message %></font>
					</p>
					<% } %>
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
	                        <input type="password" id="pswd1" class="int" maxlength="20" name="mpwd">
	                        <span id="alertTxt">사용불가</span>
	                  
	                    </span>
	                    <span class="error_next_box"></span>
	                </div>
				
	                <!-- JOIN BTN-->
	                <div class="btn_area">
	                    <button type="submit" id="btnJoin" onclick="check();">
	                        <span>로그인</span>
	                    </button>
	                </div>
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
        </form>
		<script src="<%=request.getContextPath() %>/js/Login.js"></script>
    </body>
</html>