<%@page import="java.util.*"%>
<%@page import="com.TicketLupin.web.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	MemberVo mv = (MemberVo)request.getAttribute("mv"); 
	ArrayList<CommentAVo> alist = (ArrayList<CommentAVo>) request.getAttribute("alist");
%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓루팡</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Admin_user_comment_list.css">
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script>
			function userList(c_idx, midx){
				
				
				location.href="<%=request.getContextPath()%>/Manager/UserCommentDelete.do?midx="+midx+"&c_idx="+c_idx;
			}
			
			
			$(document).ready(function(){ 
				$("button.deletebtn").click(function(){
					var c_idx = $(this).next().val();
					var midx = $(this).next().next().val();
					var removeTarget = $(this).parent().parent();
					//console.log( $(this).parent().parent());
					alert("삭제합니다.");
					
					$.ajax({
						type:"get",
						url: "UserCommentDelete.do",
						data: {"midx" : midx, "c_idx" : c_idx},
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
				<h2>조회하신 회원님 댓글 목록입니다.</h2>
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
						<th class="t3">유형</th>
						<th class="t2">댓글내용</th>
						<th class="t3">날짜</th>
						<th class="t1">삭제</th>
					</tr>
					<form nam="frm" class="table">
				<% for(CommentAVo cv : alist){ %>
					
					<tr class="bbb">
						<td><%=cv.getC_idx() %></td>
						<td>
							<%
								String c_sort = (String)request.getAttribute("c_sort");
								if(cv.getC_sort().equals("V")){
							%>
									이벤트
							<%} else if(cv.getC_sort().equals("E")){ %>
									기대
							<%} else if(cv.getC_sort().equals("R")){ %>
									리뷰
							<%} else{ %>
									문의
							<%} %>
						</td>
						<td><a href="#"><%=cv.getC_content()%></a></td>
						<td><%=cv.getC_regdate() %></td>
						<td>
							
							<button type="button" class="deletebtn">
								삭제
							</button>
							<input type="hidden" name="c_idx" value="<%=cv.getC_idx() %>">
							<input type="hidden" name="m_idx" value="<%=cv.getMidx() %>">
						</td>
					</tr>
					
				<% } %>		
					</form>
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