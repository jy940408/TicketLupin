<%@page import="com.TicketLupin.web.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mid = (String) session.getAttribute("mid");
	String mpwd = (String) session.getAttribute("mpwd");
%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>티켓루팡</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Login_FI_FP_Join__.css">
    </head>
    <script>
	    function userdel(){
			if (document.frm.mpwd.value == ""){
			  	alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			  	document.frm.mpwd.focus();
			  	return;
		  	}
			 
			alert("탈퇴합니다.");
		  	document.frm.action ="<%=request.getContextPath()%>/Member/UserDeleteAction.do";
		  	document.frm.method = "post";
		  	document.frm.submit(); 
		  	return;
		}
	</script>
    <body>
        <!-- header -->
        <div id="header">
            <a href="<%=request.getContextPath()%>/Main/MainPage.do" >
            	<img src="../icon/lupinlogo.png" id="logo">
            </a>
        </div>
		
		<!-- guide -->
		<br/>
		<h2 align="center">비밀번호 확인</h2>
		<h3 align="center">본인확인을 위해 비밀번호를 한 번 더 입력해 주세요. 비밀번호는 타인에게 노출되지 않도록 주의해 주세요.</h3>
		<br/>		
		<br/>

        <!-- wrapper -->
        <div id="wrapper">

            <!-- content-->
            <div id="content">
             	<form name="frm">
	                <div>
	                    <h3 class="join_title"><label for="name">아이디</label></h3>
	                    <div style="margin-left:20px">
	                    	<span style="font-size:20px;"><b><%=mid%></b></span>
	                    </div>
	                </div>
	                <div>
	                    <h3 class="join_title"><label for="email">비밀번호<span class="optional"></span></label></h3>
						<span class="box int_email" id= "emailbox1" >
	                        <input type="password" id="email" class="int" maxlength="100" placeholder="비밀번호 입력" name="mpwd">
	                        
	                    </span>
	                </div>
	                <div class="btn_area">
	                    <button type="button" id="btnJoin" onclick="userdel()">
	                        <span>확인</span>
	                    </button>
	                </div>
                </form>

            </div> 
            <!-- content-->
        </div> 
        <!-- wrapper -->
		<script src="<%=request.getContextPath()%>/js/Find_ID_step1.js"></script>
    </body>
</html>