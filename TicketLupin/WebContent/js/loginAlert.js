
function loginAlert(){
	
	alert("로그인이 필요한 서비스입니다");
	location.href="${pageContext.request.contextPath}/Member/MemberLogin.do";
  	return;
	
}