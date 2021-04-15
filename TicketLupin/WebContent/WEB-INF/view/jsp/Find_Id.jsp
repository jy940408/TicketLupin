<%@page import="com.TicketLupin.web.service.MemberVo"%>
<%@page import="com.TicketLupin.web.service.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	MemberDao md = new MemberDao();
	MemberVo mv = new MemberVo();
	String mid = null;
	
	String mname = request.getParameter("mname");
	String memail = request.getParameter("memail");
	
	mid = md.findId(mname, memail);
	
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>티켓 루팡 : 아이디 찾기</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Login_FI_FP_Join__.css">
	</head>
	<body>
		<div id="header">
            <a href="<%=request.getContextPath()%>/Main/MainPage.do" >
            	<img src="../icon/lupinlogo.png" id="logo">
            </a>
        </div>
		<div style="text-align:center" id="wrapper">
			<br/>
			<h2 align="center">아이디 찾기</h2>
			<br/>
			<hr size="1" width="100%">
			<% if(mid == null){ %>
				<div>회원정보가 존재하지 않습니다. 다시 확인해 주세요.</div>
			<%}else { %>
			<from method="post">
				<div style="font-size:20px;">
					회원님의 아이디는 <b><%=mid %></b> 입니다.
				</div>
			</from>
			<% } %>
			<br/>
			<input id="backbtn_" type="button" value="돌아가기" onclick="location='<%=request.getContextPath()%>/Member/MemberIdCheck.do'">
			<input id="usebtn" type="button" value="로그인하기" onclick="location='<%=request.getContextPath()%>/Member/MemberLogin.do'">
		</div>
	</body>
</html>