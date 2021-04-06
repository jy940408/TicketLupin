<%@page import="com.TicketLupin.web.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% MemberVo mv = (MemberVo)request.getAttribute("mv"); %>
<!DOCTYPE html>
<html>
	<head>
        <meta charset="UTF-8">
        <title>티켓루팡</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Login_FI_FP_Join__.css">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Admin_user_view.css">
        <script>
        	function userDelete(){
        		
        		alert("회원정지");
        		
        		location.href="<%=request.getContextPath()%>/Manager/MemberDeleteAction.do?midx=<%=mv.getMidx()%>";
        	}
        </script>
    </head>
	<body>
		<!-- header -->
		<div id="header">
            <a href="https://ticket.melon.com/main/index.htm" target="_blank" title="티켓루팡 회원가입 페이지 보러가기"><img src="../icon/lupinlogo.png" id="logo"></a>
        </div>
		
		<!--wrapper -->
		<div id="wrapper">
		
			<!-- content-->
            <div id="content">
			
				<!-- section -->
				<div>
					
					<!-- table -->
					
					<table class="type06">
						<tr>
							<th scope="row" class="even">아이디</th>
							<td class="even"><%=mv.getMid() %></td>
						</tr>
						<tr>
							<th scope="row">이름</th>
							<td><%=mv.getMname() %></td>
						</tr>
						<tr>
							<th scope="row" class="even">생년월일</th>
							<td class="even"><%=mv.getMssn() %>. <%=mv.getMbirthmonth() %>. <%=mv.getMbirthday() %></td>
						</tr>	
						<tr>
							<th scope="row">전화번호</th>
							<td><%=mv.getMphone() %></td>
						</tr>	
						<tr>
							<th scope="row" class="even">주소</th>
							<td class="even"><%=mv.getMaddress() %></td>
						</tr>	
						<tr>
							<th scope="row">이메일주소</th>
							<td><%=mv.getMemail() %></td>
						</tr>	
					</table>
					
					<!-- JOIN BTN-->
					<div class="btn_area">
						
						<button type="button" id="btnJoin2" onclick="location='<%=request.getContextPath()%>/Manager/UserBuyList.do?midx=<%=mv.getMidx()%>'">
							<span>예매내역</span>
						</button>
						<button type="button" id="btnJoin3" onclick="location='<%=request.getContextPath()%>/Manager/UserQnaList.do?midx=<%=mv.getMidx()%>'">
							<span>문의내역</span>
						</button>
						<button type="button" id="btnJoin4" onclick="location='<%=request.getContextPath()%>/Manager/UserCommentList.do?midx=<%=mv.getMidx()%>'">
							<span>댓글내역</span>
						</button>
					</div>
						
				</div>
			</div>
		</div>
		<!-- footer -->
		<div>
		</div>
	</body>
</html>