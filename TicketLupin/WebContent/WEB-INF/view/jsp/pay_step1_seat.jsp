<%@page import="java.util.List"%>
<%@page import="com.TicketLupin.web.service.ReservationShowVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	List<ReservationShowVo> list = (List<ReservationShowVo>) request.getAttribute("list");
	System.out.println("확인: " + list);
%>
<!DOCTYPE html>
<html>
	<head>
		<title>
		</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/pay_step1_seat.css">
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script>
			var checked = "";
			$(document).ready(function(){
				$("input[type='checkbox']").on("click", function(){
					var count = $("input:checked[type='checkbox']").length;
					if(count>5){
						$(this).prop("checked", false);
						alert("5개의 좌석까지 선택할 수 있습니다");
					}
				});
				
				var arraySeat = new Array();
				$("input[name=seat]").on("change", function(){
			
					if($(this).is(":checked")){
					
						checked = "";
						$("input[name=seat]:checked").each(function(){
							checked += $(this).val() + "/";
				
						})
					}else{
				
						checked = "";
						$("input[name=seat]:checked").each(function(){
							checked += $(this).val() + "/";
	
						})
					}
					$("#checkInput").val(checked);
				});
			});
		</script>
	 
	</head>
	<body>
		<form name="seat">
		<input type="hidden" id="checkInput">
		<div id="container">
			<ul id="background">
				<%
				for(int j = 1 ; j <= 1 ; j++) {
					for(int k = 3 ; k <= 28 ; k++) {
						String seat = null;
						if(j < 10){
							seat = "0" + j + "-" + k;
						}else{
							seat = j + "-" + k;
						}
						if(list.size() == 0){
				%>
				<li class="row_<%=j%> column_<%=k%>"><input type="checkbox" name="seat" id="0<%=j%>-<%=k%>" value="0<%=j%>-<%=k%>" class="seats"></li>
				<%
						}else{
							for(int i = 0 ; i < list.size() ; i++){
								if((list.get(i).getRseat()).equals(seat)){
				%>
				<li class="row_<%=j%> column_<%=k%>"><input type="checkbox" name="seat" id="0<%=j%>-<%=k%>" value="0<%=j%>-<%=k%>" class="seats" disabled></li>
				<%	
								break;
								}else{
				%>
				<li class="row_<%=j%> column_<%=k%>"><input type="checkbox" name="seat" id="0<%=j%>-<%=k%>" value="0<%=j%>-<%=k%>" class="seats"></li>
				<%
								}
							}
						}
					}
				}
				%>
				
				<%
				for(int j = 2 ; j <= 2 ; j++) {
					for(int k = 2 ; k <= 29 ; k++) {
						String seat = null;
						if(j < 10){
							seat = "0" + j + "-" + k;
						}else{
							seat = j + "-" + k;
						}
						if(list.size() == 0){
				%>
							<li class="row_<%=j%> column_<%=k%>"><input type="checkbox" name="seat" id="0<%=j%>-<%=k%>" value="0<%=j%>-<%=k%>" class="seats"></li>
				<%
						}else{
						for(int i = 0 ; i < list.size() ; i++){
							if((list.get(i).getRseat()).equals(seat)){
				%>
				<li class="row_<%=j%> column_<%=k%>"><input type="checkbox" name="seat" id="0<%=j%>-<%=k%>" value="0<%=j%>-<%=k%>" class="seats" disabled></li>
				<%	
								break;
								}else{
				%>
				<li class="row_<%=j%> column_<%=k%>"><input type="checkbox" name="seat" id="0<%=j%>-<%=k%>" value="0<%=j%>-<%=k%>" class="seats"></li>
				<%
								}
							}
						}
					}
				}
				%>
				
				<%				
				for(int j = 3 ; j <= 37 ; j++) {
					for(int k = 1 ; k <= 30 ; k++) {
						String seat = null;
						if(j < 10){
							seat = "0" + j + "-" + k;
						}else{
							seat = j + "-" + k;
						}
						if(list.size() == 0){
							if(j < 10){
				%>
				<li class="row_<%=j%> column_<%=k%>"><input type="checkbox" name="seat" id="0<%=j%>-<%=k%>" value="0<%=j%>-<%=k%>" class="seats"></li>
				<%
							}else{
				%>
				<li class="row_<%=j%> column_<%=k%>"><input type="checkbox" name="seat" id="0<%=j%>-<%=k%>" value="<%=j%>-<%=k%>" class="seats"></li>
				<%
							}
						}else{
							for(int i = 0 ; i < list.size() ; i++){
								if((list.get(i).getRseat()).equals(seat)){
									if(j < 10){
				%>
				<li class="row_<%=j%> column_<%=k%>"><input type="checkbox" name="seat" id="0<%=j%>-<%=k%>" value="0<%=j%>-<%=k%>" class="seats" disabled></li>
				<%	
									break;
									}else{
				%>
				<li class="row_<%=j%> column_<%=k%>"><input type="checkbox" name="seat" id="0<%=j%>-<%=k%>" value="<%=j%>-<%=k%>" class="seats" disabled></li>
				<%	
									break;
									}
								}else{
									if(j < 10){
				%>
				<li class="row_<%=j%> column_<%=k%>"><input type="checkbox" name="seat" id="0<%=j%>-<%=k%>" value="0<%=j%>-<%=k%>" class="seats"></li>
				<%	
									break;
									}else{
				%>
				<li class="row_<%=j%> column_<%=k%>"><input type="checkbox" name="seat" id="0<%=j%>-<%=k%>" value="<%=j%>-<%=k%>" class="seats"></li>
				<%
									}
								}
							}
						}
					}
				}
				%>
			</ul>
		</div>
		</form>			
	</body>
</html>