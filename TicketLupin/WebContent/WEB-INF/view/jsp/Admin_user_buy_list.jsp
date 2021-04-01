<%@page import="java.util.*"%>
<%@page import="com.TicketLupin.web.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	MemberVo mv = (MemberVo)request.getAttribute("mv"); 
	ArrayList<ReservationVo> alist = (ArrayList<ReservationVo>) request.getAttribute("alist");
%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓루팡</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Admin_user_buy_list.css">
	</head>
	<body>
		<!--header-->
		<!--section-->
		<div id="hh">
			<div id="aaa">
				<h2>조회하신 회원님 구매 목록입니다.</h2>
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
						<th class="t3">공연날짜</th>
						<th class="t3">예매날짜</th>
						<th class="t2">공연명</th>
						<th class="t3">예매좌석</th>
						<th class="t3">결제금액</th>
						<th class="t1">취소</th>
					</tr>
				<% for(ReservationVo rv : alist){ %>
					
					<tr>
						<td><%=rv.getRidx() %></td>
						<td><%=rv.getSrdate() %></td>
						<td><%=rv.getRregdate() %></td>
						<td><a href="<%=request.getContextPath()%>/Manager/Concert_View.do?sidx=<%=rv.getSidx() %>"><%=rv.getStitle() %></a></td>
						<td><%=rv.getRseat() %></td>
						<td><%=rv.getRprice() %></td>
						<td><button>취소</button></td>
					</tr>
					
				<% } %>		
					
				</table>
				<br/>
				<div id="num">
					
				</div>
				<div>
					<button type="button" id="rainisback" onclick="history.back()">이전</button>
				</div>
		</div>
		<!--footer-->

	</body>
</html>