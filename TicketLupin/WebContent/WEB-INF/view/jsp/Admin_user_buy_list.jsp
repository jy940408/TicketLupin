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
					//console.log( $(this).parent().parent());
					alert("삭제합니다.");
					
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
				<% 	
					for(ReservationVo rv : alist){ 	
				%>
				<tr>
					<td><%=rv.getRidx() %></td>
					<td><%=rv.getSrdate() %></td>
					<td><%=rv.getRregdate() %></td>
					<td><a href="<%=request.getContextPath()%>/Manager/Concert_View.do?sidx=<%=rv.getSidx() %>"><%=rv.getStitle() %></a></td>
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
			<div id="num">
<!--------------------------------------------------------------------------------------------------------------------->
		
			<c:set var="page" value="${(param.page == null)?1:param.page}"/>
			<c:set var="startNum" value="${page-(page-1)%5}"/>
			<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"/>
			<c:set var="midx" value="${param.midx }"/>

<!--------------------------------------------------------------------------------------------------------------------->
					
					<div id="main_news_page">
						<div id="main_news_page_set">
<!--------------------------------------------------------------------------------------------------------------------->					
							<%-- <c:if test="${startNum>1}">
								<a href="?page=${startNum-1}&midx=${midx}">
									&lt;
								</a>
							</c:if>
							<c:if test="${startNum<=1}">
								<a href="#" onclick="alert('이전 페이지가 없습니다.');">
									&lt;
								</a>
							</c:if> --%>
<!--------------------------------------------------------------------------------------------------------------------->
							
							<c:forEach var="i" begin="0" end="1">
								<c:if test="${(startNum+i) <= lastNum}">
									<a style="color: ${(page==(startNum+i))?'black':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?midx=${midx}&page=${startNum+i}" >
										${startNum +i}
									</a>
								</c:if>
							</c:forEach>
							
<!--------------------------------------------------------------------------------------------------------------------->
							<%-- <c:if test="${startNum+4<lastNum}">
								<a href="?page=${page}+1&midx=${midx}">
									&gt;
								</a>
							</c:if>
							<c:if test="${startNum+4>=lastNum}">
								<a href="#" onclick="alert('다음 페이지가 없습니다.');">
									&gt;
								</a>
							</c:if> --%>
<!--------------------------------------------------------------------------------------------------------------------->
					</div>
				</div>					
				
			</div>
			<div>
				<button type="button" id="rainisback" onclick="history.back()">이전</button>
			</div>
		</div>
		<!--footer-->

	</body>
</html>