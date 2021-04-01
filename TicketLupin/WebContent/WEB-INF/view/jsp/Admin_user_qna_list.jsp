<%@page import="java.util.ArrayList"%>
<%@page import="com.TicketLupin.web.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	MemberVo mv = (MemberVo)request.getAttribute("mv"); 
	ArrayList<FaqVo> alist = (ArrayList<FaqVo>) request.getAttribute("alist");
%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓루팡</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Admin_user_qna_list.css">
	</head>
	<body>
		<!--header-->
		<!--section-->
		<div id="hh">
			<div id="aaa">
				<h2>조회하신 회원님 문의 내역입니다.</h2>
			</div>
			<table class="type05">
			<tr>
				<th>이름</th> <td><%=mv.getMname() %></td>
			</tr>
			<tr>
				<th>아이디</th> <td><%=mv.getMid() %></td>
			</tr>
			</table>

				<table class="type02">
					<tr style="text-align:center">
						<th class="t1">no</th>
						<th class="t3">문의유형</th>
						<th class="t3">문의내용</th>
						<th class="t1">취소</th>
					</tr>
				<% for (FaqVo fv : alist){ %>
					<tr>
						<td><%=fv.getFidx() %></td>
						<td><%=fv.getFtype() %></td>
						<td><a href="#"><%=fv.getFtitle() %></a></td>
						<td><button>삭제</button></td>
					</tr>
				<% } %>	
					
				</table>
				<br>
				<div id="num">
					
				</div>
				<div>
				<button type="button" id="rainisback" onclick="history.back()">뒤로가기</button>
				</div>
		</div>
		<!--footer-->

	</body>
</html>