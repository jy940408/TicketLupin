<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			$(".test").click(function(){
				var test = $(this).attr("id");
				var tagName ="report_"+test+"_w";
				$("#reportall ul").each(function(){
					var classV = String($(this).attr("class")).split("_");
					if(classV.length==3 && classV[2] == "w"){
						if($(this).attr("class") == tagName){
							$(this).toggle();
						}else{
							$(this).hide();
						}
					}
					
				});
			 });
		    $("input:radio[name=reporttype]").click(function(){
		        if($("input[name=reporttype]:checked").val() == "etc"){
		          	 $('textarea[class=etc]').attr("disabled",false);
		        }else{
		             $('textarea[class=etc]').attr("disabled",true); 
		        }	
		    });
		    
/* ---------------------------------------------------------------------------------------------------------------- */		    
		    
			$(".reportsend").click(function(){
				var url=window.location.search.replaceAll("?","");
				var paramArray = url.split("&");
				var c_idx = "";
				for(var i=0; i<paramArray.length; i++){
						var param = paramArray[i].split("=");
					if(param[0] == "c_idx"){
						c_idx = param[1];
						break;
					}
				}
				var radioval = $('input[name="reporttype"]:checked').val();
				var etcval="";
				if(radioval =="etc"){
					var etcval= $("textarea[class=etc]").val();	
					if(etcval==""){
						alert("신고하실 내용을 입력해주세요.");
						return;
					}else if(etcval.length<=10){
						alert("조금 더 자세히 입력해주세요.");
						return;
					}
				}
				
				var form = $('<form></form>');
				form.attr('action','<%=request.getContextPath() %>/ConcertView/Commentreportaction.do');
				form.attr('method','post');
				form.appendTo('body');
				form.append($('<input type="hidden" value="'+c_idx+'"name="c_idx">'));
				form.append($('<input type="hidden" value="'+radioval+'"name="radioval">'));
				form.append($('<input type="hidden" value="'+etcval+'"name="etcval">'));
				form.submit();
			});
});
		
		
		</script>
		<style>
		textarea:disabled {
  			background: #ccc;
  			resize: none;
			width:500px;
			height:100px;
		}
		textarea{
			resize: none;
			width:500px;
			height:100px;
		}
		</style>
	</head>
	<body>
		<div id=reportall>
			<h2>신고사유</h2></p>
			<h4>신고는 반대의견을 표시하는 기능이 아닙니다.<br>
			신고 대신 반대의 의견이나 답글을 적어 보시는 것은 어떨까요?<br>
			허위신고일 경우, 신고자의 서비스 활동이 제한될 수 있습니다.<br></h4>  
			<form action="method">
				<input type ="radio" name="reporttype" value="fp"> 영리목적/홍보성
				<input type ="radio" name="reporttype" value="il"> 불법정보<br>
				<input type ="radio" name="reporttype" value="l"> 음란성/선정성
				<input type ="radio" name="reporttype" value="sw"> 욕설/인신공격<br>
				<input type ="radio" name="reporttype" value="pil"> 개인정보노출
				<input type ="radio" name="reporttype" value="p"> 같은 내용 도배<br>
				<input type ="radio" name="reporttype" value="if"> 불법촬영물등 유통신고&nbsp&nbsp<a href="#" id=content class="test" >더보기</a><br>
				<ul class="report_content_w" style="display:none;">
					<li>
						불법촬영물등 유통 신고는 전기통신사업법 시행령에 따라,신고인의 개인정보 수집·이용 동의와 신고 사유 등 신고서 제출이 필요합니다.
					</li>
					<li>
						음란, 청소년 유해 사유로 신고하시면, 신고서 제출이 더 빠르게 조치됩니다.
					</li>
				</ul>	
												
				<input type ="radio" name="reporttype" value="ior"> 권리침해 신고&nbsp&nbsp<a href="#" id=content1 class="test" >더보기</a><br>
				<ul class="report_content1_w"  style="display:none;">
					<li>
						명예훼손, 저작권 등 권리침해 신고는 권리침해신고 사이트에서 증빙서류를 다운받아 작성 후 고객센터로 추가 접수해주세요.	
					</li>
				</ul>
				<input type ="radio" name="reporttype" value="etc"> 기타<br>
				<textarea placeholder="기타 유형을 클릭시 작성 가능합니다." class="etc" disabled ></textarea>
				<br> 
				
				
				<input type="button" value="취소" onclick="javascript:self.close();">
				<input type="button" class="reportsend" id="report" value="확인">
			</form>
		</div>
	</body>
</html>


