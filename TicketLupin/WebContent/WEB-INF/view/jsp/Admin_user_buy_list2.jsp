<%@page import="java.util.*"%>
<%@page import="com.TicketLupin.web.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% 
	MemberVo mv = (MemberVo)request.getAttribute("mv"); 
	ArrayList<ReservationVo> alist = (ArrayList<ReservationVo>) request.getAttribute("alist");
%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓루팡</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Admin_user_buy_list.css">
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script>
			$(document).ready(function(){ 
				$("button.cancelbtn").click(function(){
					var ridx = $(this).next().val();
					var removeTarget = $(this).parent().parent();
					
					alert("취소합니다.");
					
					$.ajax({
						type:"get",
						url: "UserBuyDelete.do",
						data: {"ridx" : ridx},
						success : function(data){
							console.log( removeTarget);
							removeTarget.remove();
						}
						
					});
				});
	
			});

		</script>
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

			<table class="type02" style="width:100%">
				<tr style="text-align:center">
					<th>공연날짜</th>
					<th>공연회차</th>
					<th>예매좌석</th>
					<th>결제금액</th>
					<th>취소</th>
				</tr>
				<% 	
					for(ReservationVo rv : alist){ 	
				%>
				<tr>
					<td><%=rv.getSrdate() %></td>
					<td><%=rv.getSrround() %></td>
					<td><%=rv.getRseat() %></td>
					<td><%=rv.getRprice() %></td>
					<td>
						<button type="button" class="cancelbtn">취소</button>
						<input type="hidden" value="<%=rv.getRidx() %>"> 
					</td>
				</tr>
				<% } 
					
				%>
			</table>
			<br/>
			
			<div>
				<button type="button" id="rainisback" onclick="history.back()">이전</button>
			</div>
		</div>
		<!--footer-->

	</body>
</html>