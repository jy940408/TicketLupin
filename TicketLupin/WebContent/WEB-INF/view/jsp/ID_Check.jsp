<%@page import="com.TicketLupin.web.service.MemberVo"%>
<%@page import="com.TicketLupin.web.service.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String mid = request.getParameter("mid");
	System.out.println("mid:"+mid);
	
	MemberDao md = new MemberDao();
	boolean b = md.isExistId(mid);
	
%>
	
	

<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>아이디 중복 체크</title>
		<style>
			button{
				width:60px;
				height:30px;
				border:none;
				background-color:#D9E5FF;
				border-radius:30px;
			}
		</style>
	</head>
	
	
	<body onload="pValue()">
		<div style="text-align:center">
			<h2>아이디 중복 체크</h2>
			<hr size="1" width="100%"><br/>
			<div style="font-size:19px;">
				&quot;<b><%=mid %></b>&quot;
				
					<% if(b){ %>
					
						(은)는 이미 사용중인 아이디입니다. <p/><p/>
					
						<a href="#" onclick = "opener.document.frm.mid.focus(); window.close();">
							<button type="button" >확인</button>
						</a>
					
					<%}else{ %>
					
						는(은) 사용 가능 합니다. <p/><p/>
						
						<a href="#" onclick = "opener.document.frm.mpwd.focus(); window.close();">
							<button type="button" >확인</button>
						</a>
						
					<%   } %>
			</div>
		</div>
		
		
		
	</body>
</html>