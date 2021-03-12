<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		
		<table id="calendar" style="text-decoration:none; color:black;">
			<tr>
				<c:choose>
					<c:when test="${month eq '0' }">
						<td colspan="2" align="center"><a href="?sidx=${detail.sidx}&month=11&year=${year-1}" style="text-decoration:none; color:black;">12월</a></td>
					</c:when>
					<c:otherwise>
						<td colspan="2" align="center"><a href="?sidx=${detail.sidx}&month=${month-1}&year=${year}" style="text-decoration:none; color:black;">${month}월</a></td>
					</c:otherwise>
				</c:choose>
				<td colspan="3" align="center"><a href="?sidx=${detail.sidx}&month=${month}&year=${year}" style="text-decoration:none; color:black;">${year}년 ${month+1}월</a></td>
				<c:choose>
					<c:when test="${month eq '11' }">
						<td colspan="2" align="center"><a href="?sidx=${detail.sidx}&month=0&year=${year+1}" style="text-decoration:none; color:black;">1월</a></td>
					</c:when>
					<c:otherwise>
						<td colspan="2" align="center"><a href="?sidx=${detail.sidx}&month=${month+1}&year=${year}" style="text-decoration:none; color:black;">${month+2}월</a></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td align="center"> <font color="#FF9090">일</font></td>
				<td align="center"> 월 </td>
				<td align="center"> 화 </td>
				<td align="center"> 수 </td>
				<td align="center"> 목 </td>
				<td align="center"> 금 </td>
				<td align="center"><font color=#7ED5E4>토</font></td>
			</tr>
			<tr>
			<c:forEach var="i" begin="1" end="${start-1}" step="1">
				<td></td>
				<input type="hidden" value="${newLine = newLine + 1}">
			</c:forEach>
			<c:forEach var="i" begin="1" end="${endDay}" step="1">
				<c:choose>
					<c:when test="${newLine eq 0}">
						<input type="hidden" value="${color = 'red'}">
					</c:when>
					<c:when test="${newLine eq 6 }">
						<input type="hidden" value="${color = 'blue'}">
					</c:when>
					<c:otherwise>
						<input type="hidden" value="${color = 'black'}">
					</c:otherwise>
				</c:choose>
				<td><a href="?sidx=${detail.sidx}&month=${month}&year=${year}&date=${i}" style="text-decoration:none; color:black;">${i}</a></td>
				<input type="hidden" value="${newLine = newLine + 1}">
				<c:if test="${newLine eq 7}">
					</tr>
					<c:if test="${i <= endDay}"><tr></c:if>
					<input type="hidden" value="${newLine = 0}">
				</c:if>
			</c:forEach>
			</tr>
		</table>
	</body>
</html>