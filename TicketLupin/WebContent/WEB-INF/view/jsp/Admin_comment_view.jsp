<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Admin_comment_view.css"> 
		<script type = "text/javascript">
		</script>
	</head>
	<body>
	<div id="topBtn">
		<div id="Btn">
		<input type="button" value="창닫기" class="btn">
		<c:if test="${!empty list}">
		<input type="button" value="읽음" class="btn">
		</c:if>
		</div>
	</div>
		<!--header-->
		<!--section-->
		<div id="hh">
			<table class="type05">
			<tr>
				<th>이름</th> <td>${test.mname}</td> <th>아이디</th> <td>${test.mid}</td>
			</tr>
			<tr>
				<th>게시글</th> <td>${test.title}</td><th>댓글 작성일</th> <td>${test.c_regdate}</td>
			</tr>
			<tr >
				<th colspan=1>댓글 내용</th>
				<td colspan=3>${test.c_content}</td>
			</tr>
			</table>
			<c:if test="${empty list}">
			<h2 align="center">신고 내역이 없는 깨끗한 댓글입니다.</h2>
			</c:if>
			<c:if test="${!empty list}">
			<h2>&nbsp; 신고 목록</h2>
			<div class="reportlist">
				<c:forEach var="rep" items="${list}">
					&nbsp;&nbsp;<input type="checkbox">
					<table class="type02">					
						<tr>
							<th class="t1" align="center">no</th><th class="t2">신고 분류</th><th class="t3">신고 날짜</th><th class="t3">신고 회원</th>
						</tr>
						<tr>
							<td align="center">
								${rep.no}
							</td>
							<td>
								<c:choose>
									<c:when test="${rep.crsort eq 'fp' }">영리목적/홍보성</c:when>
									<c:when test="${rep.crsort eq 'il' }">불법정보</c:when>
									<c:when test="${rep.crsort eq 'l' }">음란성/선전성</c:when>
									<c:when test="${rep.crsort eq 'sw' }">욕설/인신공격</c:when>
									<c:when test="${rep.crsort eq 'pil' }">개인정보노출</c:when>
									<c:when test="${rep.crsort eq 'p' }">같은 내용 도배</c:when>
									<c:when test="${rep.crsort eq 'if' }"><span class="if">불법촬영물등 유통 신고</span></c:when>
									<c:when test="${rep.crsort eq 'ior' }">권리침해 신고</c:when>
									<c:when test="${rep.crsort eq 'etc' }">기타</c:when>
								</c:choose>
							</td>
							<td>
								${rep.crregdate}
							</td>
							<td>
								${rep.mid}
							</td>
						</tr>
						<c:choose>
						<c:when test="${rep.crsort eq 'etc' }">
							<tr>
								<th>내용</th>			
								<td colspan=4>
								${rep.crcontent}
								</td>
							</tr>
						</c:when>
						</c:choose>	
					</table>
				</c:forEach>
				</c:if>
				<br>
				<div id="num">
					<c:set var="page" value="${(param.p == null)?1:param.p}"/>
					<c:set var="startNum" value="${page-(page-1)%5}"/>
					<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"/>
					<div class="paging">
						<a href="?p=1&q="> 
							<<
						</a>&nbsp;
					<c:if test= "${startNum>1}">
						<a href= "?p=${startNum-1}&q=">
							< 
						</a>
				
					</c:if>
					<c:if test= "${startNum<=1}">
						<a href= "#" onclick="alert('이전 페이지가 없습니다.');">
							<
						</a>
					</c:if>
						
					<c:forEach var="i" begin="0" end= "4">
						<c:if test ="${(startNum+i) <= lastNum}">
							&nbsp;<a style=" color: ${(page==(startNum+i))?'red':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?p=${startNum+i}&q=${param.q}" >${startNum+i}</a>					
						</c:if>
					</c:forEach>
					&nbsp;
					<c:if test="${startNum+4<lastNum}">
					<a href="?p=${startNum+5}&q=">
							>
						</a>
					</c:if>
					<c:if test="${startNum+4>=lastNum}">
						<a href="#" onclick="alert('다음 페이지가 없습니다.');">
							>
						</a>	
					</c:if>
					&nbsp;
					<a href="?p${lastNum}&q=">>></a>
					</div>
					
				</div>
			</div>
			<div id="blank"></div>		
		</div>
		<!--footer-->

	</body>
</html>