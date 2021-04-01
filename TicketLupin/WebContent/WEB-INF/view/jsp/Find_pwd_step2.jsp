<%@page import="com.TicketLupin.web.service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDao md = new MemberDao();
	MemberVo mv = (MemberVo)request.getAttribute("mv");
	
	String mpwd = null;
	
	String mid = request.getParameter("mid");
	String mname = request.getParameter("mname");
	String memail = request.getParameter("memail");
	
	mpwd = md.findPwd(mid, memail, mname);
%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>티켓루팡 : 비밀번호 변경</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Login_FI_FP_Join__.css">
        
        <script type="text/javascript">
       		function pwdChange(){
       			if (document.frm.mpwd.value ==""){
				  	alert("비밀번호를 입력해주세요");
				  	document.frm.mpwd.focus();
				  	return;
			  	}else if (document.frm.mpwd.value != document.frm.mpwd2.value){
				  	alert("비밀번호가 일치하지 않습니다.");
				  	document.frm.mpwd2.focus();
				  	return;
			  	}
			  
			  	alert("전송");
			  	document.frm.action ="<%=request.getContextPath()%>/Member/pwdChangeAction.do?mid=" +mid;
			  	document.frm.method = "GET";
			  	document.frm.submit(); 
			  	return;
		
       		}
       </script>
    </head>
    
    
    
    <body>
        <!-- header -->
        <div id="header">
            <a href="<%=request.getContextPath()%>/Main/MainPage.do" title="티켓루팡 페이지 보러가기"><img src="../icon/lupinlogo.png" id="logo"></a>
        </div>
		<br/>
	<% if(mpwd == null){ %>
		<div align="center">
			<h2>비밀번호 찾기</h2><p/>
			<h3>회원정보가 존재하지 않습니다. 다시 확인해 주세요.</h3><p/><p/>
			<button type="button" id="backbtn" onclick="location='<%=request.getContextPath()%>/Member/MemberPwdCheck.do'">확인</button>
		</div>
	<% }else{ %>
		<h2 align="center">비밀번호 변경</h2>
		<h3 align="center">회원님께서 사용하실 비밀번호를 입력해주세요.</h3>
		<br/>		
		<br/>		
		<form name="frm">
        <!-- wrapper -->
        <div id="wrapper">

            <!-- content-->
            <div id="content">


                <!-- PW1 -->
                <div>
                	<h3 class="join_title"><label>아이디</label></h3>
               		<div style="padding-left:20px;">
               			<input type="text" id="mid" name="mid" value="<%=mid%>" 
               				style="border:none; background:none; font-size:22px; font-weight:700;" readonly/>
               		</div>
                    <h3 class="join_title"><label for="pswd1">비밀번호</label></h3>
                    <span class="box int_pass">
                        <input type="password" id="pswd1" name="mpwd" class="int" maxlength="20">
                        <span id="alertTxt">사용불가</span>
                        <img src="../icon/m_icon_pass.png" id="pswd1_img1" class="pswdImg">
                    </span>
                    <span class="error_next_box"></span>
                </div>
   
                <!-- PW2 -->
                <div>
                    <h3 class="join_title"><label for="pswd2">비밀번호 재확인</label></h3>
                    <span class="box int_pass_check">
                        <input type="password" id="pswd2" name="mpwd2" class="int" maxlength="20">
                        <img src="../icon/m_icon_check_disable.png" id="pswd2_img1" class="pswdImg">
                    </span>
                    <span class="error_next_box"></span>
                </div>

                <!-- JOIN BTN-->
                <div class="btn_area">
                    <button type="button" id="btnJoin"  onclick="pwdChange()">
                        <span>변경하기</span>
                    </button>
                </div>

                

            </div> 
            <!-- content-->

        </div> 
        <!-- wrapper -->
        </form>
        <% } %>
		<script src="<%=request.getContextPath() %>/js/Find_pwd_step2.js"></script>
    </body>
</html>