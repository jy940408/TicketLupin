<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String tab = (String)request.getAttribute("tab"); %>

<!DOCTYPE html>
<html>
	<head>
	<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
	<script type="text/javascript"></script>
	<script>
	function insertDibs(){
		
		alert("보내기 시작");
	
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/Dibs/DibsAction.do",
			data:{"sidx": '${detail.sidx}'},
			success: function(data){
				if(data.result == 1){
					$("#main_concert_process_dibs").html("<a onclick='deleteDibs()'><div>✓찜하기 취소</div></a>");
				}else if(data.result == 0){
					$("#main_concert_process_dibs").html("<a onclick='insertDibs()'><div>✓찜하기 목록 담기</div></a>");
				}
			}
		});
	}
	
	function deleteDibs(){
		
		alert("삭제 시작");
		
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/Dibs/DibsDeleteAction.do",
			data:{"sidx": "${detail.sidx}"},
			success: function(data){
				if(data.result == 1){
					$("#main_concert_process_dibs").html("<a onclick='deleteDibs()'><div>✓찜하기 취소</div></a>");
				}else if(data.result == 0){
					$("#main_concert_process_dibs").html("<a onclick='insertDibs()'><div>✓찜하기 목록 담기</div></a>");
				}
			}
		});
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------//
	function selectDate(i){
		
		$.ajax({
			
			type:"get",
			url:"${pageContext.request.contextPath}/ConcertView/ConcertViewDateAJAX.do",
			data:{"sidx": "${detail.sidx}", "month": "${month}", "year": "${year}", "date": i},
			success: function(data){
				
				
				var output = "";
				
				output += "<div style='display:none;'>";
				for(var j = 1 ; j <= 4 ; j++){
					if(eval("data.srround" + j) != '' && eval("data.srround" + j) != null){
						if(eval("data.srround" + j) != 'SOLDOUT'){
							output += "	<input type='radio' name='round' id='round1' value='" + eval("data.srround" + j) + "'>";
						}
					}
				}
				output += "</div>";
				for(var j = 1 ; j <= 4 ; j++){
					if(eval("data.srround" + j) != '' && eval("data.srround" + j) != null){
						if(eval("data.srround" + j) != 'SOLDOUT'){
							output += "<label for='round1'><div class='round_all' tabindex='1' style='text-align:center; width:235px; padding:15px; font-size:20px;'>" + eval("data.srround" + j) + "</div></label><br>";
						}else{
							output += "<div class='round_all' tabindex='1' style='text-align:center; width:235px; padding:15px; font-size:20px;'>" + eval("data.srround" + j) + "</div><br>";
						}
					}
				}
				
				var output2 = "";
				
				output += "<input type='hidden' name='sidx' value='${detail.sidx}'>";
				output += "<input type='hidden' name='year' value='${year}'>";
				output += "<input type='hidden' name='month' value='${month}'>";
				output += "<input type='hidden' name='date' value='" + i + "'>";
				
				$("#main_concert_process_time_calender").html(output);
				$("#dateRedirect").html(output2);
			}
		});
	
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------//
	function submitReservation(){
		
		if(${empty sessionScope.mid}){
			alert("로그인이 필요한 서비스입니다");
			location.href="${pageContext.request.contextPath}/Member/MemberLogin.do";
		  	return;
		}
		
		var checkRadio = document.getElementsByName("round");
		var radioLength = $("input:radio[name='round']").length;
		var radioChecked = $("input:radio[name='round']").is(':checked');
		var checkCount = 0;
		if(radioChecked == true){
			checkCount = 1;
		}
		
		if(checkCount < 1){
			alert("회차를 선택해주세요");
			return;
		}

		var srWin = window.open("about:blank", "winName", "width=955px, height=647px");
		document.concertReservation.action = "${pageContext.request.contextPath}/Reservation/ReservationStep1.do";
		document.concertReservation.method = "get";
		document.concertReservation.target = "winName";
		document.concertReservation.submit();
		return;
	}
//-------------------------------------------------------------------------------------------------------------------------------------//
	function deleteAction(){
		
		alert("공연이 삭제되었습니다");

	}


/*---------------댓글 삭제---------------------------------------------------------------------------*/
			
		    function removeCheck(test) {

	        	 if (confirm("정말 삭제하시겠습니까??") == true){    //확인
						var url=window.location.search.replaceAll("?","");
						var paramArray = url.split("&");
						var tab = "";
						for(var i=0; i<paramArray.length; i++){
	 						var param = paramArray[i].split("=");
							if(param[0] == "tab"){
								tab = param[1];
								break;
							}
						}
						var page="";
						for(var i=0; i<paramArray.length; i++){
	 						var param = paramArray[i].split("=");
							if(param[0] == "p"){
								page = param[1];
								break;
							}
						}
						var form = $('<form></form>');
						form.attr('action','<%=request.getContextPath() %>/ConcertView/ExpectDeleteAction.do');
						form.attr('method','post');
						form.appendTo('body');
						form.append($('<input type="hidden" value="'+test+'"name="origin_c_idx">'));
						form.append($('<input type="hidden" value="'+tab+'"name="tab">'));
						form.append($('<input type="hidden" value="'+${sidx}+'"name="sidx">'));
						form.append($('<input type="hidden" value="'+page+'"name="p">'));
						form.submit();

	        	 }else{   //취소

	        	     return false;
	        	 }
			}
		    function removeCheck2(test) {

	        	 if (confirm("정말 삭제하시겠습니까??") == true){    //확인
						var url=window.location.search.replaceAll("?","");
						var paramArray = url.split("&");
						var tab = "";
						for(var i=0; i<paramArray.length; i++){
	 						var param = paramArray[i].split("=");
							if(param[0] == "tab"){
								tab = param[1];
								break;
							}
						}
						var page="";
						for(var i=0; i<paramArray.length; i++){
	 						var param = paramArray[i].split("=");
							if(param[0] == "p"){
								page = param[1];
								break;
							}
						}
						var form = $('<form></form>');
						form.attr('action','<%=request.getContextPath() %>/ConcertView/ExpectDeleteAction.do');
						form.attr('method','post');
						form.appendTo('body');
						form.append($('<input type="hidden" value="'+test+'"name="c_idx">'));
						form.append($('<input type="hidden" value="'+tab+'"name="tab">'));
						form.append($('<input type="hidden" value="'+${sidx}+'"name="sidx">'));
						form.append($('<input type="hidden" value="'+page+'"name="p">'));
						form.submit();

	        	 }else{   //취소

	        	     return false;
	        	 }
			}
/*---------------댓글 보기---------------------------------------------------------------------------*/	  

		$(document).ready(function(){
			 $(".test").click(function(){
				var test = $(this).attr("id");
				var tagName = "reypleR_"+test+"_W";
				$("#main_concert_expect_content_list li").each(function(){
					var classV = String($(this).attr("class")).split("_");
					console.log(classV);	
					
					if(classV.length==3 && classV[2] == "W"){
						if($(this).attr("class") == tagName){
							$(this).toggle();
						}else{
							$(this).hide();
						}
					}
					
				});
			 });

			 $(".test1").click(function(){
				var test = $(this).attr("id");
				var tagName = "reypleR1_"+test+"_W";
				$("#main_concert_review_content_list li").each(function(){
					var classV = String($(this).attr("class")).split("_");
					console.log(classV);	
					
					if(classV.length==3 && classV[2] == "W"){
						if($(this).attr("class") == tagName){
							$(this).toggle();
						}else{
							$(this).hide();
						}
					}
					
				});
			 });
			 $(".test2").click(function(){
					var test = $(this).attr("id");
					var tagName = "reypleR2_"+test+"_W";
					$("#main_concert_question_content_list li").each(function(){
						var classV = String($(this).attr("class")).split("_");
						console.log(classV);	
						
						if(classV.length==3 && classV[2] == "W"){
							if($(this).attr("class") == tagName){
								$(this).toggle();
							}else{
								$(this).hide();
							}
						}
						
					});
				 });	


 			
/*-------------댓글입력-----------------------------------------------------------------------------*/
			$(".writeaction").click(function(){
					var midx ='<%=session.getAttribute("midx")%>';
					var test = $(this).attr("id");
					var value= $("#"+test+"_action").val();
					var url=window.location.search.replaceAll("?","");
					var paramArray = url.split("&");
					var tab = "";
					for(var i=0; i<paramArray.length; i++){
 						var param = paramArray[i].split("=");
						if(param[0] == "tab"){
							tab = param[1];
							break;
						}
					}

					if (midx == 'null') {
						alert("로그인 후 입력 가능합니다.");
						return false;
					}
					
					var form = $('<form></form>');
					form.attr('action','<%=request.getContextPath() %>/ConcertView/ExpectWriteAction.do');
					form.attr('method','post');
					form.appendTo('body');
					form.append($('<input type="hidden" value="'+value+'"name="content">'));
					form.append($('<input type="hidden" value="'+tab+'"name="tab">'));
					form.append($('<input type="hidden" value="'+${sidx}+'"name="sidx">'));
					form.submit();
					
			 }); 
			 
/*---------------대댓글 입력---------------------------------------------------------------------------*/	 			 
 			$(".comment").click(function(){
 					var midx ='<%=session.getAttribute("midx")%>';
					var test = $(this).attr("id");
					var value= $(".comment_"+test).val();
					var url=window.location.search.replaceAll("?","");
					var paramArray = url.split("&");
					var tab = "";
					for(var i=0; i<paramArray.length; i++){
 						var param = paramArray[i].split("=");
						if(param[0] == "tab"){
							tab = param[1];
							break;
						}
					}
					var page="";
					for(var i=0; i<paramArray.length; i++){
 						var param = paramArray[i].split("=");
						if(param[0] == "p"){
							page = param[1];
							break;
						}
					}
					if (midx == 'null') {
						alert("로그인 후 입력 가능합니다.");
						return false;
					}
					
					var form = $('<form></form>');
					form.attr('action','<%=request.getContextPath() %>/ConcertView/ExpectCommentWriteAction.do');
					form.attr('method','post');
					form.appendTo('body');
					form.append($('<input type="hidden" value="'+value+'"name="content">'));
					form.append($('<input type="hidden" value="'+test+'"name="origin_c_idx">'));
					form.append($('<input type="hidden" value="'+tab+'"name="tab">'));
					form.append($('<input type="hidden" value="'+${sidx}+'"name="sidx">'));
					form.append($('<input type="hidden" value="'+page+'"name="p">'));
					form.submit();
					
			 });
 			$(".comment1").click(function(){
				var midx ='<%=session.getAttribute("midx")%>';
				var test = $(this).attr("id");
				var value= $(".comment1_"+test).val();
				var url=window.location.search.replaceAll("?","");
				var paramArray = url.split("&");
				var tab = "";
				for(var i=0; i<paramArray.length; i++){
						var param = paramArray[i].split("=");
					if(param[0] == "tab"){
						tab = param[1];
						break;
					}
				}
				var page="";
				for(var i=0; i<paramArray.length; i++){
						var param = paramArray[i].split("=");
					if(param[0] == "p"){
						page = param[1];
						break;
					}
				}
				if (midx == 'null') {
					alert("로그인 후 입력 가능합니다.");
					return false;
				}
				
				var form = $('<form></form>');
				form.attr('action','<%=request.getContextPath() %>/ConcertView/ExpectCommentWriteAction.do');
				form.attr('method','post');
				form.appendTo('body');
				form.append($('<input type="hidden" value="'+value+'"name="content">'));
				form.append($('<input type="hidden" value="'+test+'"name="origin_c_idx">'));
				form.append($('<input type="hidden" value="'+tab+'"name="tab">'));
				form.append($('<input type="hidden" value="'+${sidx}+'"name="sidx">'));
				form.append($('<input type="hidden" value="'+page+'"name="p">'));
				form.submit();
				
			 });
 			$(".comment2").click(function(){
					var midx ='<%=session.getAttribute("midx")%>';
				var test = $(this).attr("id");
				var value= $(".comment2_"+test).val();
				var url=window.location.search.replaceAll("?","");
				var paramArray = url.split("&");
				var tab = "";
				for(var i=0; i<paramArray.length; i++){
						var param = paramArray[i].split("=");
					if(param[0] == "tab"){
						tab = param[1];
						break;
					}
				}
				var page="";
				for(var i=0; i<paramArray.length; i++){
						var param = paramArray[i].split("=");
					if(param[0] == "p"){
						page = param[1];
						break;
					}
				}
				if (midx == 'null') {
					alert("로그인 후 입력 가능합니다.");
					return false;
				}
				var form = $('<form></form>');
				form.attr('action','<%=request.getContextPath() %>/ConcertView/ExpectCommentWriteAction.do');
				form.attr('method','post');
				form.appendTo('body');
				form.append($('<input type="hidden" value="'+value+'"name="content">'));
				form.append($('<input type="hidden" value="'+test+'"name="origin_c_idx">'));
				form.append($('<input type="hidden" value="'+tab+'"name="tab">'));
				form.append($('<input type="hidden" value="'+${sidx}+'"name="sidx">'));
				form.append($('<input type="hidden" value="'+page+'"name="p">'));
				form.submit();
			
			 });
/*---------------댓글 수정---------------------------------------------------------------------------*/	
 			 $(".expecptmodify").click(function(){
					var test = $(this).attr("id");
					var value= $(".modfiy_"+test).val();
					var value2= (".modfiy_"+test);
					var url=window.location.search.replaceAll("?","");
					var paramArray = url.split("&");
					var tab = "";
					for(var i=0; i<paramArray.length; i++){
 						var param = paramArray[i].split("=");
						if(param[0] == "tab"){
							tab = param[1];
							break;
						}
					}
					var page="";
					for(var i=0; i<paramArray.length; i++){
 						var param = paramArray[i].split("=");
						if(param[0] == "p"){
							page = param[1];
							break;
						}
					}
					var form = $('<form></form>');
					form.attr('action','<%=request.getContextPath() %>/ConcertView/ExpectModifyWriteAction.do');
					form.attr('method','post');
					form.appendTo('body');
					form.append($('<input type="hidden" value="'+value+'"name="content">'));
					form.append($('<input type="hidden" value="'+test+'"name="c_idx">'));
					form.append($('<input type="hidden" value="'+tab+'"name="tab">'));
					form.append($('<input type="hidden" value="'+${sidx}+'"name="sidx">'));
					form.append($('<input type="hidden" value="'+page+'"name="p">'));
					form.submit();
			 });
 			 $(".expecptmodify1").click(function(){
					var test = $(this).attr("id");
					var value= $(".modify1_"+test).val();
					var url=window.location.search.replaceAll("?","");
					var paramArray = url.split("&");
					var tab = "";
					for(var i=0; i<paramArray.length; i++){
 						var param = paramArray[i].split("=");
						if(param[0] == "tab"){
							tab = param[1];
							break;
						}
					}
					var page="";
					for(var i=0; i<paramArray.length; i++){
 						var param = paramArray[i].split("=");
						if(param[0] == "p"){
							page = param[1];
							break;
						}
					}
					var form = $('<form></form>');
					form.attr('action','<%=request.getContextPath() %>/ConcertView/ExpectModifyWriteAction.do');
					form.attr('method','post');
					form.appendTo('body');
					form.append($('<input type="hidden" value="'+value+'"name="content">'));
					form.append($('<input type="hidden" value="'+test+'"name="c_idx">'));
					form.append($('<input type="hidden" value="'+tab+'"name="tab">'));
					form.append($('<input type="hidden" value="'+${sidx}+'"name="sidx">'));
					form.append($('<input type="hidden" value="'+page+'"name="p">'));
					form.submit();
			 });
 			 $(".expecptmodify2").click(function(){
					var test = $(this).attr("id");
					var value= $(".modify2_"+test).val();
					var url=window.location.search.replaceAll("?","");
					var paramArray = url.split("&");
					var tab = "";
					for(var i=0; i<paramArray.length; i++){
 						var param = paramArray[i].split("=");
						if(param[0] == "tab"){
							tab = param[1];
							break;
						}
					}
					var page="";
					for(var i=0; i<paramArray.length; i++){
 						var param = paramArray[i].split("=");
						if(param[0] == "p"){
							page = param[1];
							break;
						}
					}
					var form = $('<form></form>');
					form.attr('action','<%=request.getContextPath() %>/ConcertView/ExpectModifyWriteAction.do');
					form.attr('method','post');
					form.appendTo('body');
					form.append($('<input type="hidden" value="'+value+'"name="content">'));
					form.append($('<input type="hidden" value="'+test+'"name="c_idx">'));
					form.append($('<input type="hidden" value="'+tab+'"name="tab">'));
					form.append($('<input type="hidden" value="'+${sidx}+'"name="sidx">'));
					form.append($('<input type="hidden" value="'+page+'"name="p">'));
					form.submit();
			 });
/*---------------댓글 수정페이지 이동---------------------------------------------------------------------------*/	
 			$(".modify").click(function(){
				var test = $(this).attr("id");
				var tagName = "reypleR_"+test+"_W";
				var modify = "modify_"+test+"_e";
				var modifyc = "modifyc_"+test+"_c";
				$("#main_concert_expect_content_list li").each(function(){
					var classV = String($(this).attr("class")).split("_");
					if(classV.length==3 && classV[2] == "e"){
						if($(this).attr("class") == modify){
							$(this).toggle();	
					
						}else{
							$(this).hide();
						}
					}else if(classV.length==3 && classV[2] == "c"){
						if($(this).attr("class") == modifyc){
							$(this).toggle();
						}
					}else if(classV.length==3 && classV[2] == "W"){
						if($(this).attr("class") == tagName){
							$(this).hide();
						}else{
							$(this).hide();
							
						}
					}			
				});
			});
 			$(".modify1").click(function(){
				var test = $(this).attr("id");
				var tagName = "reypleR1_"+test+"_W";
				var modify = "modify1_"+test+"_e";
				var modifyc = "modifyc1_"+test+"_c";
				$("#main_concert_review_content_list li").each(function(){
					var classV = String($(this).attr("class")).split("_");
					if(classV.length==3 && classV[2] == "e"){
						if($(this).attr("class") == modify){
							$(this).toggle();	
					
						}else{
							$(this).hide();
						}
					}else if(classV.length==3 && classV[2] == "c"){
						if($(this).attr("class") == modifyc){
							$(this).toggle();
						}
					}else if(classV.length==3 && classV[2] == "W"){
						if($(this).attr("class") == tagName){
							$(this).hide();
						}else{
							$(this).hide();
							
						}
					}			
				});
			});
 			$(".modify2").click(function(){
				var test = $(this).attr("id");
				var tagName = "reypleR2_"+test+"_W";
				var modify = "modify2_"+test+"_e";
				var modifyc = "modifyc2_"+test+"_c";
				$("#main_concert_question_content_list li").each(function(){
					var classV = String($(this).attr("class")).split("_");
					if(classV.length==3 && classV[2] == "e"){
						if($(this).attr("class") == modify){
							$(this).toggle();	
					
						}else{
							$(this).hide();
						}
					}else if(classV.length==3 && classV[2] == "c"){
						if($(this).attr("class") == modifyc){
							$(this).toggle();
						}
					}else if(classV.length==3 && classV[2] == "W"){
						if($(this).attr("class") == tagName){
							$(this).hide();
						}else{
							$(this).hide();
							
						}
					}			
				});
			});
 			
/*---------------탭---------------------------------------------------------------------------*/	   			
			var tab = '<%= tab%>';
			var obj1 = document.getElementById("main_concert_detail_content_all");
			var obj2 = document.getElementById("main_concert_expect_all");
			var obj3 = document.getElementById("main_concert_review_all");
			var obj4 = document.getElementById("main_concert_question_all");
			var obj5 = document.getElementById("main_concert_place_all");
			var obj6 = document.getElementById("main_concert_info_all");
			
			obj1.style.display="none";
			obj2.style.display="none";
			obj3.style.display="none";
			obj4.style.display="none";
			obj5.style.display="none";
			obj6.style.display="none";
			
			if(tab == "main_concert_detail_content_all"){
				$("#main_concert_detail_content_all").show();
			    $("#main_concert_detail_menu_div1").css("background-color","#adadad");
			}else if(tab == "main_concert_expect_all"){
				$("#main_concert_expect_all").show();
				$("#main_concert_detail_menu_div2").css("background-color","#adadad");
			}else if(tab == "main_concert_review_all"){
				$("#main_concert_review_all").show();
				$("#main_concert_detail_menu_div3").css("background-color","#adadad");
			}else if(tab == "main_concert_question_all"){
				$("#main_concert_question_all").show();
				$("#main_concert_detail_menu_div4").css("background-color","#adadad");
			}else if(tab == "main_concert_place_all"){
				$("#main_concert_place_all").show();
				$("#main_concert_detail_menu_div5").css("background-color","#adadad");
			}else if(tab == "main_concert_info_all"){
				$("#main_concert_info_all").show();
				$("#main_concert_detail_menu_div6").css("background-color","#adadad");
			}
			
		});
		function showTab(val){
			var obj1 = document.getElementById("main_concert_detail_content_all");
			var obj2 = document.getElementById("main_concert_expect_all");
			var obj3 = document.getElementById("main_concert_review_all");
			var obj4 = document.getElementById("main_concert_question_all");
			var obj5 = document.getElementById("main_concert_place_all");
			var obj6 = document.getElementById("main_concert_info_all");
			
			obj1.style.display="none";
			obj2.style.display="none";
			obj3.style.display="none";
			obj4.style.display="none";
			obj5.style.display="none";
			obj6.style.display="none";
			
			var obj = document.getElementById(val);
			obj.style.display="";
			
			param = val;
			window.location = window.location.href.split("?")[0]+"?tab="+param+"&sidx="+${detail.sidx}+"#hold2";

		}

/*---------------신고 팝업---------------------------------------------------------------------------*/	  	
	
		function reportCheck(c_idx){
			var url ='<%=request.getContextPath() %>';
			var url = url+"/ConcertView/Commentreport.do?c_idx="+c_idx;
            var name = "popup test";
            var option = "location = no"
            window.open(url, name, option);
            return;
		}

/*---------------구분선---------------------------------------------------------------------------*/
/*---------------구분선---------------------------------------------------------------------------*/	 
/*---------------구분선---------------------------------------------------------------------------*/	 
/*---------------구분선---------------------------------------------------------------------------*/	 
/*---------------구분선---------------------------------------------------------------------------*/	 

	</script>
		<title>티켓 루팡</title>
		<link rel="stylesheet" type"text/css" href="<%=request.getContextPath() %>/css/Concert_view.css">
		<script src="<%=request.getContextPath() %>/js/Concert_view.js"></script>
		<script src="${pageContext.request.contextPath}/js/Nav_all.js"></script>

	</head>
	<body onload="build();">
		
		<input type="hidden" name="sidx" id="sidx" value="${detail.sidx}">
		<input type="hidden" name="year" id="year" value="${year}">
		<input type="hidden" name="month" id="month" value="${month}">

		<header>
			<div id="h_title">
				<div id="h_title_inner">
					<span id="h_top_menu">
						<ul id="h_top_menu_ul">
						<c:if test="${not empty sessionScope.mid}">
							<li><a href="${pageContext.request.contextPath}/Member/Member_Modify_PwdCheck.do?mid=${sessionScope.mid}">${sessionScope.mid }님 환영합니다!</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							<li><a href="${pageContext.request.contextPath}/Member/Memberlogout.do">로그아웃&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
						<c:if test="${empty sessionScope.mid}">
							<li class="login"><a href="${pageContext.request.contextPath}/Member/MemberLogin.do">로그인&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="${pageContext.request.contextPath}/Member/MemberJoin.do">회원가입&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						</c:if>
							<li><a href="${pageContext.request.contextPath}/Customer/NoticeList.do">고객센터&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">이용안내&nbsp;&nbsp;&nbsp;&nbsp;</a></li><br/>
						</ul>
						<img src="../ads/musicalads.png" id="h_ads">
					</span>
					<a href=""${pageContext.request.contextPath}/Main/MainPage.do"><img src="../icon/lupinlogo.png" id="h_logo"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<form action="${pageContext.request.contextPath}/Show/ShowList.do" method="get" style="display:inline-block;">
						<input type="text" id="h_search" name="q" placeholder="뮤지컬 〈캣츠〉 40주년 내한공연 앙코르－서울（Musical CATS Encore">
						<button type="submit" id="h_search_button"><img src="../icon/search.png" id="h_search_img"></button>
					</form>
				</div>
			</div>
		</header>
		<hr id="nav_bar_top">
		<div id="n_nav_div">
			<nav id="main_nav">
				<a href="${pageContext.request.contextPath}/Main/MainPage.do" id="main_nav_home">홈</a>
				<a href="${pageContext.request.contextPath}/Show/ShowList.do" id="main_nav_concert">공연</a>
				<a href="${pageContext.request.contextPath}/Show/RankingList.do" id="main_nav_ranking">랭킹</a>
				<a href="${pageContext.request.contextPath}/News/NewsList.do" id="main_nav_news">티켓오픈소식</a>
				<a href="${pageContext.request.contextPath}/Event/EventMain.do" id="main_nav_event">이벤트</a>
				<c:choose>
					<c:when test="${sessionScope.mgrade eq 'M' }">
						<a href="${pageContext.request.contextPath}/Manager/Main.do" id="main_nav_myticket">관리자</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/Myticket/MyticketMain.do" id="main_nav_myticket">마이 티켓</a>
					</c:otherwise>
				</c:choose>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		<div id="nav_menu_sub_event_div" class="main_nav_all">
			<ul id="nav_menu_sub_event" style="margin:0px;">
				<li><a href="${pageContext.request.contextPath}/Event/EventMain.do">전체 이벤트</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="${pageContext.request.contextPath}/Winner/WinnerList.do">당첨자 발표</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		<div id="nav_menu_sub_myticket_div" class="main_nav_all">
			<ul id="nav_menu_sub_myticket" style="margin:0px;">
				<c:choose>
					<c:when test="${sessionScope.mgrade eq 'M' }">
						<li><a href="${pageContext.request.contextPath}/Manager/MemberList.do">회원관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/Manager/ConcertList.do">공연관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/Manager/comment.do">댓글관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/Customer/AnswerMain.do">문의관리</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${not empty sessionScope.mid}">
								<li><a href="${pageContext.request.contextPath}/Myticket/MyticketMain.do">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a href="${pageContext.request.contextPath}/Myticket/MyticketReservation.do">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a href="${pageContext.request.contextPath}/Dibs/MyDibs.do">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							</c:when>
							<c:otherwise>
								<li><a onclick="loginAlert()">마이티켓 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a onclick="loginAlert()">예매확인/취소</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li><a onclick="loginAlert()">마이 찜</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</ul>
			<hr id="nav_bar_sub">
		</div>
		
		
		<section>
			<article>
				<div id="main_concert_detail_div">
					<div id="main_concert_product">
						<div id="main_concert_cont">
							<div id="main_concert_cont_poster_div">
								<img src="<%=request.getContextPath()%>/poster/${detail.stitleimage}" id="main_concert_cont_poster">
							</div>
							<div id="main_concert_cont_text_div">
								<div id="main_concert_cont_text_dibs">
									<p id="main_concert_process_title">${detail.stitle}</p>
									<div id="main_concert_process_dibs">
										<c:if test="${didx eq 0}">
											<a onclick="insertDibs()"><div>✓찜하기 목록 담기</div></a>
										</c:if>
										<c:if test="${didx eq 1}">
											<a onclick="deleteDibs()"><div>✓찜하기 취소</div></a>
										</c:if>
									</div>
								</div>
								<div id="main_concert_process_info">
									<dl id="main_concert_process_info_left" class="main_concert_process_info_all">
										<dt>공연기간<dt>
										<dd><fmt:formatDate value="${detail.sopendate}" type="both" pattern="YYYY.MM.dd"/>-<fmt:formatDate value="${detail.senddate}" type="both" pattern="YYYY.MM.dd"/></dd>
										<dt>관람시간</dt>
										<dd>90분</dd>
										<dt>장르</dt>
										<dd>${detail.sgenre }</dd>
									</dl>
									<dl id="main_concert_process_info_right" class="main_concert_process_info_all">
										<dt>공연장<dt>
										<dd>${detail.sdetailaddress }</dd>
										<dt>관람등급</dt>
										<dd>${detail.srating }</dd>
										<dt>할인혜택</dt>
										<dd>무이자</dd>
									</dl>
								</div>
								<c:if test="${sessionScope.mgrade eq 'M'}">
								<a href="${pageContext.request.contextPath}/Show/ShowModifyStep1.do?sidx=${detail.sidx}" style="color:black"><div id="modify">수정하기</div></a><br>
								<a href="${pageContext.request.contextPath}/Show/ShowDelete.do?sidx=${detail.sidx}" onclick="deleteAction()" style="color:black"><div id="delete">삭제하기</div></a>
								</c:if>
							</div>
						</div>
						<form name="concertReservation">
						<div id="main_concert_process_box">
							<div id="main_concert_process">
								<div id="main_concert_process_ticketing_set" class="main_concert_process_all_set">
									<div id="main_concert_process_ticketing">
										<div id="main_concert_process_date_choice">
											<div>날짜 선택</div>
										</div>
										<div id="main_concert_process_date_calender">
											<table id="calendar" style="text-decoration:none; color:black;">
												<tr>
													<c:choose>
														<c:when test="${month eq '0' }">
															<td colspan="2" align="center" id="preMonth"><a href="?sidx=${detail.sidx}&month=11&year=${year-1}" style="text-decoration:none; font-weight:bold; color:black;">12월</a></td>
														</c:when>
														<c:otherwise>
															<td colspan="2" align="center" id="preMonth"><a href="?sidx=${detail.sidx}&month=${month-1}&year=${year}" style="text-decoration:none; font-weight:bold; color:black;">${month}월</a></td>
														</c:otherwise>
													</c:choose>
													<td colspan="3" align="center" id="nowMonth"><a href="?sidx=${detail.sidx}&month=${month}&year=${year}" style="text-decoration:none; font-weight:bold; color:black;">${year}년 ${month+1}월</a></td>
													<c:choose>
														<c:when test="${month eq '11' }">
															<td colspan="2" align="center" id="postMonth"><a href="?sidx=${detail.sidx}&month=0&year=${year+1}" style="text-decoration:none; font-weight:bold; color:black;">1월</a></td>
														</c:when>
														<c:otherwise>
															<td colspan="2" align="center" id="postMonth"><a href="?sidx=${detail.sidx}&month=${month+1}&year=${year}" style="text-decoration:none; font-weight:bold; color:black;">${month+2}월</a></td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr>
													<td align="center" style="font-weight:bold;"> <font color="#FF9090">일</font></td>
													<td align="center" style="font-weight:bold;"> 월 </td>
													<td align="center" style="font-weight:bold;"> 화 </td>
													<td align="center" style="font-weight:bold;"> 수 </td>
													<td align="center" style="font-weight:bold;"> 목 </td>
													<td align="center" style="font-weight:bold;"> 금 </td>
													<td align="center" style="font-weight:bold;"><font color=#7ED5E4>토</font></td>
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
													<c:choose>
														<c:when test="${roundCheck[i-1] eq 1}">
															<td><a onclick="selectDate(${i})" style="cursor:pointer; font-weight:bold; text-decoration:none; color:black;">${i}</a></td>
														</c:when>
														<c:otherwise>
															<td><a onclick="" style="text-decoration:none; color:grey;">${i}</a></td>
														</c:otherwise>
													</c:choose>
													<input type="hidden" value="${newLine = newLine + 1}">
													<c:if test="${newLine eq 7}">
														</tr>
														<c:if test="${i <= endDay}"><tr></c:if>
														<input type="hidden" value="${newLine = 0}">
													</c:if>
												</c:forEach>
												</tr>
											</table>
										</div>
									</div>
								</div>
								<div id="main_concert_process_time_choice_set" class="main_concert_process_all_set">
									<div id="main_concert_process_time_choice">
										<div>시간 선택</div>
									</div>
									<div id="main_concert_process_time_calender">
										<div id="main_concert_process_time_calender_sub" style="font-weight:bold;">날짜를 선택해주세요!</div>
									</div>
								</div>
							</div>
							<div id="dateRedirect">
								<input type="hidden" name="sidx" value="${detail.sidx}">
								<input type="hidden" name="year" value="${year}">
								<input type="hidden" name="month" value="${month}">
								<input type="hidden" name="date" value="${date}">
							</div>
							<div id="main_concert_process_choice_button_div">
								<button type="button" id="main_concert_process_choice_button" onclick="submitReservation()"><div style="font-size:16px;">예매하기</div></button>
							</div>
						</div>
						</form>
					</div>
					<div id="main_concert_detail_menu_div">
						<ul>
							<li class="main_concert_detail_menu_set"><a href="javascript:void(0);" onclick="showTab('main_concert_detail_content_all'); tabMenuColor('main_concert_detail_menu_div1');"><div id="main_concert_detail_menu_div1" >상세정보</div></a></li>
							<li class="main_concert_detail_menu_set"><a href="javascript:void(0);" onclick="showTab('main_concert_expect_all'); tabMenuColor('main_concert_detail_menu_div2');"><div id="main_concert_detail_menu_div2">기대평</div></a></li>
							<li class="main_concert_detail_menu_set"><a href="javascript:void(0);" onclick="showTab('main_concert_review_all'); tabMenuColor('main_concert_detail_menu_div3');"><div id="main_concert_detail_menu_div3">관람평</div></a></li>
							<li class="main_concert_detail_menu_set"><a href="javascript:void(0);" onclick="showTab('main_concert_question_all'); tabMenuColor('main_concert_detail_menu_div4');"><div id="main_concert_detail_menu_div4">Q&amp;A</div></a></li>
							<li class="main_concert_detail_menu_set"><a href="javascript:void(0);" onclick="showTab('main_concert_place_all'); tabMenuColor('main_concert_detail_menu_div5');"><div id="main_concert_detail_menu_div5">공연장 정보</div></a></li>
							<li class="main_concert_detail_menu_set"><a href="javascript:void(0);" onclick="showTab('main_concert_info_all'); tabMenuColor('main_concert_detail_menu_div6');"><div id="main_concert_detail_menu_div6">예매안내</div></a></li>
						</ul>
					</div>
				</div>
				<a name="hold2"></a>
				<!---------------------------------상세페이지--------------------------------------->
				<div id="main_concert_detail_content_all">
					<div id="main_concert_detail_content_div">
						<div id="main_concert_detail_playtime">  
							<c:choose>
								<c:when test="${empty contentsDetail.sroundimage and empty contentsDetail.sround}"></c:when>
								<c:otherwise>
								<p>공연시간</p>
								<c:if test="${empty contentsDetail.sroundimage }"></c:if>
								<c:if test="${not empty contentsDetail.sroundimage }">
									<img src="<%=request.getContextPath()%>/poster/${contentsDetail.sroundimage}"><br>
								</c:if>
								<c:if test="${empty contentsDetail.sround }"></c:if>
								<c:if test="${not empty contentsDetail.sround }">
									${contentsDetail.sround}
								</c:if>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="main_concert_detail_pay" class="main_concert_detail_all">
							<c:choose>
								<c:when test="${empty contentsDetail.spriceimage and empty contentsDetail.sprice}"></c:when>
								<c:otherwise>
								<p>가격정보</p>
								<c:if test="${empty contentsDetail.spriceimage }"></c:if>
								<c:if test="${not empty contentsDetail.spriceimage }">
									<img src="<%=request.getContextPath()%>/poster/${contentsDetail.spriceimage}"><br>
								</c:if>
								<c:if test="${empty contentsDetail.sprice }"></c:if>
								<c:if test="${not empty contentsDetail.sprice }">
									${contentsDetail.sprice}
								</c:if>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="main_concert_detail_notice" class="main_concert_detail_all">
							<c:choose>
								<c:when test="${empty contentsDetail.snoticeimage and empty contentsDetail.snotice}"></c:when>
								<c:otherwise>
								<p>예매 공지사항</p>
								<c:if test="${empty contentsDetail.snoticeimage }"></c:if>
								<c:if test="${not empty contentsDetail.snoticeimage }">
									<img src="<%=request.getContextPath()%>/poster/${contentsDetail.snoticeimage}"><br>
								</c:if>
								<c:if test="${empty contentsDetail.snotice }"></c:if>
								<c:if test="${not empty contentsDetail.snotice }">
									${contentsDetail.snotice}
								</c:if>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="main_concert_detail_discount" class="main_concert_detail_all">
							<c:choose>
								<c:when test="${empty contentsDetail.sdiscountimage and empty contentsDetail.sdiscount}"></c:when>
								<c:otherwise>
								<p>할인정보</p>
								<c:if test="${empty contentsDetail.sdiscountimage }"></c:if>
								<c:if test="${not empty contentsDetail.sdiscountimage }">
									<img src="<%=request.getContextPath()%>/poster/${contentsDetail.sdiscountimage}"><br>
								</c:if>
								<c:if test="${empty contentsDetail.sdiscount }"></c:if>
								<c:if test="${not empty contentsDetail.sdiscount }">
									${contentsDetail.sdiscount}
								</c:if>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="main_concert_detail_content" class="main_concert_detail_all">
							<c:choose>
								<c:when test="${empty contentsDetail.sinfoimage and empty contentsDetail.sdiscount}"></c:when>
								<c:otherwise>
								<p>작품정보</p>
								<c:if test="${empty contentsDetail.sinfoimage }"></c:if>
								<c:if test="${not empty contentsDetail.sinfoimage }">
									<img src="<%=request.getContextPath()%>/poster/${contentsDetail.sinfoimage}"><br>
								</c:if>
								<c:if test="${empty contentsDetail.sinfo }"></c:if>
								<c:if test="${not empty contentsDetail.sinfo }">
									${contentsDetail.sinfo}
								</c:if>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="main_concert_detail_company" class="main_concert_detail_all">
							<c:choose>
								<c:when test="${empty contentsDetail.scompanyimage and empty contentsDetail.scompany}"></c:when>
								<c:otherwise>
								<p>기획사정보</p>
								<c:if test="${empty contentsDetail.scompanyimage }"></c:if>
								<c:if test="${not empty contentsDetail.scompanyimage }">
									<img src="<%=request.getContextPath()%>/poster/${contentsDetail.scompanyimage}"><br>
								</c:if>
								<c:if test="${empty contentsDetail.scompany }"></c:if>
								<c:if test="${not empty contentsDetail.scompany }">
									${contentsDetail.scompany}
								</c:if>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="main_concert_detail_info" class="main_concert_detail_all">
							<p>상품정보제공 고시</p>
							<div>
								<table style="border:1px solid black;">
									<tr>
										<td style="background-color:rgba(0,0,0,0.1);">예매 관련 문의</td>
										<td>1899-0042</td>
									</tr>
									<tr>
										<td style="background-color:rgba(0,0,0,0.1);">유효기간(이용조건)</td>
										<td>${detail.sopendate} ~ ${detail.senddate} (예매한 공연 회차에 한해 이용가능)</td>
									</tr>
									<tr>
										<td style="background-color:rgba(0,0,0,0.1);">취소/환불조건</td>
										<td>
											- 취소마감시간 이후 또는 관람일 당일 예매하신 건에 대해서는 취소/변경/환불이 불가합니다.<br>
											- 예매수수료는 예매 당일 밤 12시 이전까지 취소 시 환불 가능합니다.<br>
											- 배송이 시작된 경우 취소마감시간 이전까지 멜론티켓 고객센터로 티켓을 반환해주셔야 환불이 가능하며, 도착한 일자 기준으로 취소수수료가 부과됩니다.<br>
											(* 단, 반환된 티켓의 배송료는 환불되지 않으며 일괄배송 상품의 경우 취소에 대한 자세한 문의는 고객센터로 문의해 주시기 바랍니다.)<br>
											- 예매취소 시점과 결제 시 사용하신 신용카드사의 환불 처리기준에 따라 취소금액의 환급방법과 환급일은 다소 차이가 있을 수 있습니다.<br>
											- 티켓 부분 취소 시 신용카드 할부 결제는 티켓 예매 시점으로 적용됩니다. (무이자할부 행사기간이 지날 경우 혜택 받지 못하실 수 있으니 유의하시기 바랍니다.)<br>
											- 취소일자에 따라 아래와 같이 취소수수료가 부과됩니다.<br>
											(예매 후 7일 이내라도 취소시점이 관람일로부터 10일 이내라면 관람일 기준의 취소수수료가 부과됩니다.)<br>
											<br>
											<table style="border:1px solid black;">
												<tr>
													<td style="background-color:rgba(0,0,0,0.1);">취소일</td>
													<td style="background-color:rgba(0,0,0,0.1);">취소수수료</td>
												</tr>
												<tr>
													<td>예매 후 7일 이내</td>
													<td>없음</td>
												</tr>
												<tr>
													<td>예매 후 8일 ~ 관림 일 10일 이내</td>
													<td>장당 2000원 (티켓 금액의 10% 한도)</td>
												</tr>
												<tr>
													<td>관람 일 9일 전 ~ 7일 전</td>
													<td>티켓 금액의 10%</td>
												</tr>
												<tr>
													<td>관람 일 6일 전 ~ 3일 전</td>
													<td>티켓 금액의 20%</td>
												</tr>
												<tr>
													<td>관람 일 2일 전 ~ 1일 전</td>
													<td>티켓 금액의 30%</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!---------------------------------기대평--------------------------------------->
				<div id="main_concert_expect_all" style="display:none;">
					<div id="main_concert_expect_div">
						<div id="main_concert_expect_notice">
							<div id="main_concert_expect_notice_ment">
								ⓘ 티켓 매매 및 양도, 교환의 글은 사전 통보 없이 삭제될 수 있습니다.
							</div>
							<div id="main_concert_expect_notice_button">
								<a href="javascript:void(0);">
									<div id="main_concert_expect_notice_butto_div">
									게시판 운영규칙
									</div>
								</a>
							</div>
							<div id="main_concert_expect_notice_button_popup" style="display:none;">
								대충 게시판 운영규칙이라는 뜻
							</div>
						</div>
					</div>
					<div id="main_concert_expect_content">
						<div id="main_concert_expect_content_write_div">
							<img src="../icon/person.png" class="main_concert_expect_content_write_all">
							<div class="main_concert_expect_content_write_all">
								<textarea name="content" id="main_concert_expect_content_write_button_action" placeholder="
   * 게시된 글의 저작권을 글을 작성한 회원에게 있으며 게시물로 인해 발생하는 문제는 게시자 본인에게 책임이 있습니다."></textarea>
							</div>
							<input type="button" class="writeaction" id="main_concert_expect_content_write_button" value="등록">
						</div>
						<div id="main_concert_expect_content_number_all">
							<div id="main_concert_expect_content_number_order">
								<div id="main_concert_expect_content_number">총 ${count}개</div>
								<div id="main_concert_expect_content_list_order">
									<ul>
										<li><a href="?sidx=${sidx}&od=latest&tab=main_concert_expect_all#hold2">최신순</a></li>&nbsp;&nbsp;|&nbsp;
										<li><a href="?sidx=${sidx}&od=recommended&tab=main_concert_expect_all#hold2">추천순</a></li></li>&nbsp;&nbsp;|&nbsp;
										<li><a href="?sidx=${sidx}&od=comments&tab=main_concert_expect_all#hold2">댓글순</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div id="main_concert_expect_content_list">
						<ul>
							<c:if test="${empty elist}">
								<hr class="hrbar">
								<il><h2 align ="center">해당 글이 비어 있습니다. 멋진 댓글을 남겨보세요.</h2></il>
							</c:if>
							<c:forEach var="cc" items="${elist}">
							<c:choose>
							<c:when test="${cc.c_depth == 0}">
								<hr class="hrbar">
								<li class="modifyc_${cc.origin_c_idx}_c" >
									<div>
										<div class="main_concert_question_content_list_id_div">
											<img src="../icon/person.png" class="main_concert_question_content_list_id_all">
											<div class="main_concert_question_content_list_id">
												${cc.mid}
											</div>
										</div>
										<div class="main_concert_question_content_list_text_div">
											<div class="main_concert_question_content_list_text">
												${cc.c_content}	<a name="a${cc.c_idx}"></a>
											</div>
											<div class="main_concert_question_content_list_date">
												${cc.c_regdate}
											</div>
											<div class="main_concert_question_content_list_content_good">
												<ul>
												<c:choose>
													<c:when test ="${sessionScope.midx == null}">
														<li><a href=# onclick="alert('로그인 후 이용 가능합니다.');return false;">👍</a>${cc.origin_good}</li>	
														<li><a href=# onclick="alert('로그인 후 이용 가능합니다.');return false;">👎</a>${cc.origin_bad}</li>	
													</c:when>
													<c:when test ="${sessionScope.midx == cc.midx}">
														<li><a href=# onclick="alert('본인이 작성하신 글에는 이용 불가합니다.');return false;">👍</a>${cc.origin_good}</li>	
														<li><a href=# onclick="alert('본인이 작성하신 글에는 이용 불가합니다.');return false;">👎</a>${cc.origin_bad}</li>	
													</c:when>
													<c:when test ="${sessionScope.midx != null}">
														<li><a href="<%=request.getContextPath() %>/ConcertView/GoodAction.do?sidx=${sidx}&origin_c_idx=${cc.origin_c_idx}&good=G&tab=main_concert_expect_all">👍</a>${cc.origin_good}</li>
														<li><a href="<%=request.getContextPath() %>/ConcertView/GoodAction.do?sidx=${sidx}&origin_c_idx=${cc.origin_c_idx}&good=B&tab=main_concert_expect_all">👎</a>${cc.origin_bad}</li>	
													</c:when>
												</c:choose>
												<c:choose>
													<c:when test="${cc.midx == sessionScope.midx}">							                     
							                   			<li>|</li>
							                    		<li><a href="javascript:click()" id="${cc.origin_c_idx}" class="modify">수정</a></li>

							                   			<li>|</li>
							                   			<li><a href="javascript:click()" onclick="removeCheck('${cc.origin_c_idx}')">삭제</a></li>
							                   		</c:when>
	                								<c:when test="${cc.midx != sessionScope.midx}">							                     
							                   			<li>|</li>
							                   			<li><a href="javascript:click()" onclick="reportCheck('${cc.c_idx}')">신고</a></li>
	                								</c:when>
												</c:choose>                  							
                  								</ul>
											</div>
											<a href="javascript:void(0);" id="${cc.origin_c_idx}" class="main_concert_question_content_list_more test">댓글 ${cc.cnt}개</a>
										</div>
									</div>
								</li>
								<li class="modify_${cc.origin_c_idx}_e" >
									<img src="../icon/person.png" class="main_concert_question_content_list_id_all">
									<div class="main_concert_question_content_list_id">
												${cc.mid}
									</div>
									<div class="modifybox">	
										<textarea class="modfiy_${cc.c_idx}">${cc.c_content}</textarea>
										<input type="button"  id="${cc.c_idx}"  class="expecptmodify" value="등록">
										<input type="button" id="${cc.origin_c_idx}" class="modify"value="취소">
									</div>
								</li>
								<li class="reypleR_${cc.origin_c_idx}_W">
									<div class="commentbox">							
									<input type="text" name="content" class="comment_${cc.origin_c_idx}">
									<input type="button"  id="${cc.origin_c_idx}" class="comment" value="등록">		
									</div>
								</li>
								</c:when>
								<c:when test="${cc.c_depth == 1}">
									<li class="reypleR_${cc.origin_c_idx}_W">
										<div class= "commentrow">		
											<div class="commentid">
													${cc.mid}
											</div>
											<div class="commentcontent">
												<div class="commentview">${cc.c_content}</div>
												<div class="likeanddate">
													<div class="main_concert_question_content_list_date commentdate">
														${cc.c_regdate}
													</div>
													<div class="commentlike">
														<ul>
															<c:choose>
																<c:when test ="${sessionScope.midx == null}">
																<li><a href=# onclick="alert('로그인 후 이용 가능합니다.');return false;">👍</a>${cc.good}</li>	
																<li><a href=# onclick="alert('로그인 후 이용 가능합니다.');return false;">👎</a>${cc.bad}</li>	
																</c:when>
																<c:when test ="${sessionScope.midx == cc.midx}">
																<li><a href=# onclick="alert('본인이 작성하신 글에는 이용 불가합니다.');return false;">👍</a>${cc.good}</li>	
																<li><a href=# onclick="alert('본인이 작성하신 글에는 이용 불가합니다.');return false;">👎</a>${cc.bad}</li>	
																</c:when>
																<c:when test ="${sessionScope.midx != null}">
																<li><a href="<%=request.getContextPath() %>/ConcertView/GoodAction.do?sidx=${sidx}&c_idx=${cc.c_idx}&good=G&tab=main_concert_expect_all">👍</a>${cc.good}</li>
																<li><a href="<%=request.getContextPath() %>/ConcertView/GoodAction.do?sidx=${sidx}&c_idx=${cc.c_idx}&good=B&tab=main_concert_expect_all">👎</a>${cc.bad}</li>	
																</c:when>
															</c:choose>
															
															<c:choose>
																<c:when test="${cc.midx == sessionScope.midx}">
										                   			<li>|</li>
										                   			<li><a href="javascript:click()" onclick="removeCheck2('${cc.c_idx}')">삭제</a></li>
										                   		</c:when>
				                								<c:when test="${cc.midx != sessionScope.midx}">							                     
										                   			<li>|</li>
										                   			<li><a href="javascript:click()" onclick="reportCheck('${cc.c_idx}')">신고</a></li>
				                								</c:when>
															</c:choose>                  							
			                  							</ul>
		                  							</div>
	                  							</div>	
											</div>
										</div>
									</li>
									</c:when>
							</c:choose>
							</c:forEach>
							<hr class="hrbar">
						</ul>	
			
						<c:set var="page" value="${(param.p == null)?1:param.p}"/>
						<c:set var="startNum" value="${page-(page-1)%5}"/>
						<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"/>
						<div id="main_page_button_set">
							<a href="?p=1&tab=<%=tab%>&sidx=${detail.sidx}#hold">
								<div class="main_page_button main_event_page_bn">
								<div class="main_page_button_lg">&lt;&lt;</div>
								</div>
							</a>
							<c:if test= "${startNum>1}">
								<a href= "?p=${startNum-1}&tab=<%=tab%>&sidx=${detail.sidx}#hold">
									<div class="main_page_button main_page_bn">
										<div class="main_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
							<c:if test= "${startNum<=1}">
								<a href= "#" onclick="alert('이전 페이지가 없습니다.');return false;">
									<div class="main_page_button main_page_bn">
										<div class="main_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
								
								<div class="main_page_bn">
									<c:if test="${empty elist}">
									<div class="main_page_button_page">
										<a>0</a>
									</div>
									</c:if>
									<c:forEach var="i" begin="0" end= "4">
										<c:if test ="${(startNum+i) <= lastNum}">
											<div class="main_page_button_page">
												<a style="color: ${(page==(startNum+i))?'red':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?p=${startNum+i}&tab=<%=tab%>&sidx=${detail.sidx}#hold" >${startNum+i}</a>
											</div>
										</c:if>
									</c:forEach>
								</div>
								<c:if test="${startNum+4<lastNum}">
									<a href="?p=${startNum+5}&tab=<%=tab%>&sidx=${detail.sidx}#hold">
										<div class="main_page_button main_event_page_bn">
											<div class="main_page_button_lg">&gt;</div>
										</div>	
									</a>
								</c:if>
								<c:if test="${startNum+4>=lastNum}">
									<a href="#" onclick="alert('다음 페이지가 없습니다.');return false;">
										<div class="main_page_button main_event_page_bn">
											<div class="main_page_button_lg">&gt;</div>
										</div>
									</a>	
								</c:if>
										
								<div class="main_page_button main_event_page_bn">
									<a href="?p=${lastNum}&tab=<%=tab%>&sidx=${sidx}#hold"><div class="main_page_button_llgg">&gt;&gt;</div></a>
								</div>
							</div>
						</div>
					</div>
				</div>
					
					<!---------------------------------리뷰평--------------------------------------->
				<div id="main_concert_review_all" style="display:none;">
					<div id="main_concert_review_div">
						<div id="main_concert_review_notice">
							<div id="main_concert_review_notice_ment">
								ⓘ 티켓 매매 및 양도, 교환의 글은 사전 통보 없이 삭제될 수 있습니다.
							</div>
							<div id="main_concert_review_notice_button">
								<a href="javascript:void(0);">
									<div id="main_concert_review_notice_butto_div">
									게시판 운영규칙
									</div>
								</a>
							</div>
						</div>
					</div>
					<div id="main_concert_review_content">
						<form name="rev">
						<div id="main_concert_review_content_write_div">
								<img src="../icon/person.png" class="main_concert_review_content_write_all">
							<div class="main_concert_review_content_write_all">
								<textarea name="content" id="main_concert_review_content_write_button_action" placeholder="
   * 게시된 글의 저작권을 글을 작성한 회원에게 있으며 게시물로 인해 발생하는 문제는 게시자 본인에게 책임이 있습니다."></textarea>	
							</div>
							<input type="button" class="writeaction" id="main_concert_review_content_write_button" value="등록">
						</div>
						</form>
						<div id="main_concert_review_content_number_all">
							<div id="main_concert_review_content_number_order">
								<div id="main_concert_review_content_number">총 ${count}개</div>
								<div id="main_concert_review_content_list_order">
									<ul>
										<li><a href="?sidx=${sidx}&od=latest&tab=main_concert_review_all#hold2">최신순</a></li>&nbsp;&nbsp;|&nbsp;
										<li><a href="?sidx=${sidx}&od=recommended&tab=main_concert_review_all#hold2">추천순</a></li></li>&nbsp;&nbsp;|&nbsp;
										<li><a href="?sidx=${sidx}&od=comments&tab=main_concert_review_all#hold2">댓글순</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div id="main_concert_review_content_list">
							<ul>
								<c:if test="${empty elist}">
									<hr class="hrbar">
									<il><h2 align ="center">해당 글이 비어 있습니다. 멋진 댓글을 남겨보세요.</h2></il>
								</c:if>
								<c:forEach var="aa" items="${elist}">
								<c:choose>
								<c:when test="${aa.c_depth==0 }">
								<hr class="hrbar">
								<li class="modifyc1_${aa.origin_c_idx}_c" >
									<div>
										<div class="main_concert_question_content_list_id_div">
											<img src="../icon/person.png" class="main_concert_question_content_list_id_all">
											<div class="main_concert_question_content_list_id">
												${aa.mid}
											</div>
										</div>
										<div class="main_concert_question_content_list_text_div">
											<div class="main_concert_question_content_list_text">
												${aa.c_content}
											</div>
											<div class="main_concert_question_content_list_date">
												${aa.c_regdate}
											</div>
											<div class="main_concert_question_content_list_content_good">
												<ul>
												<c:choose>
													<c:when test ="${sessionScope.midx == null}">
														<li><a href=# onclick="alert('로그인 후 이용 가능합니다.');return false;">👍</a>${aa.origin_good}</li>	
														<li><a href=# onclick="alert('로그인 후 이용 가능합니다.');return false;">👎</a>${aa.origin_bad}</li>	
													</c:when>
													<c:when test ="${sessionScope.midx == aa.midx}">
														<li><a href=# onclick="alert('본인이 작성하신 글에는 이용 불가합니다.');return false;">👍</a>${aa.origin_good}</li>	
														<li><a href=# onclick="alert('본인이 작성하신 글에는 이용 불가합니다.');return false;">👎</a>${aa.origin_bad}</li>	
													</c:when>
													<c:when test ="${sessionScope.midx != null}">
														<li><a href="<%=request.getContextPath() %>/ConcertView/GoodAction.do?sidx=${sidx}&origin_c_idx=${aa.origin_c_idx}&good=G&tab=main_concert_review_all">👍</a>${aa.origin_good}</li>
														<li><a href="<%=request.getContextPath() %>/ConcertView/GoodAction.do?sidx=${sidx}&origin_c_idx=${aa.origin_c_idx}&good=B&tab=main_concert_review_all">👎</a>${aa.origin_bad}</li>	
													</c:when>
												</c:choose>
												<c:choose>
													<c:when test="${aa.midx == sessionScope.midx}">							                     
							                   			<li>|</li>
							                    		<li><a href="javascript:click()" id="${aa.origin_c_idx}" class="modify1">수정</a></li>

							                   			<li>|</li>
							                   			<li><a href="javascript:click()" onclick="removeCheck('${aa.origin_c_idx}')">삭제</a></li>
							                   		</c:when>
	                								<c:when test="${aa.midx != sessionScope.midx}">							                     
							                   			<li>|</li>
							                   			<li><a href="javascript:click()" onclick="reportCheck('${aa.c_idx}')">신고</a></li>
	                								</c:when>
												</c:choose>                  							
                  								</ul>
											</div>
											<a href="javascript:void(0);" id="${aa.origin_c_idx}" class="main_concert_question_content_list_more test1">댓글 ${aa.cnt}개</a>
										</div>
									</div>
								</li>
							<li class="modify1_${aa.origin_c_idx}_e">
								<img src="../icon/person.png" class="main_concert_question_content_list_id_all">
								<div class="main_concert_question_content_list_id">${aa.mid}</div>
								<div class="modifybox">	
									<textarea class="modify1_${aa.c_idx}">${aa.c_content}</textarea>
									<input type="button"  id="${aa.c_idx}"  class="expecptmodify1" value="등록">
									<input type="button" id="${aa.origin_c_idx}" class="modify1"value="취소">
								</div>
							</li>
							<li class="reypleR1_${aa.origin_c_idx}_W">	
								<div class="commentbox">					
									<input type="text" size="100" name="content" class="comment1_${aa.origin_c_idx}">
									<input type="button"  id="${aa.origin_c_idx}" class="comment1" value="등록">		
								</div>
							</li>
						</c:when>
						<c:when test="${aa.c_depth == 1}">	
							<li class="reypleR1_${aa.origin_c_idx}_W">
								<div class= "commentrow">		
									<div class="commentid">
										${aa.mid}
									</div>
									<div class="commentcontent">
										<div class="commentview">${aa.c_content}</div>
										<div class="likeanddate">
											<div class="main_concert_question_content_list_date commentdate">
												${aa.c_regdate}
											</div>
											<div class="commentlike">
												<ul>
													<c:choose>
														<c:when test ="${sessionScope.midx == null}">
														<li><a href=# onclick="alert('로그인 후 이용 가능합니다.');return false;">👍</a>${aa.good}</li>	
														<li><a href=# onclick="alert('로그인 후 이용 가능합니다.');return false;">👎</a>${aa.bad}</li>	
														</c:when>
														<c:when test ="${sessionScope.midx == aa.midx}">
														<li><a href=# onclick="alert('본인이 작성하신 글에는 이용 불가합니다.');return false;">👍</a>${aa.good}</li>	
														<li><a href=# onclick="alert('본인이 작성하신 글에는 이용 불가합니다.');return false;">👎</a>${aa.bad}</li>	
														</c:when>
														<c:when test ="${sessionScope.midx != null}">
														<li><a href="<%=request.getContextPath() %>/ConcertView/GoodAction.do?sidx=${sidx}&c_idx=${aa.c_idx}&good=G&tab=main_concert_review_all">👍</a>${aa.good}</li>
														<li><a href="<%=request.getContextPath() %>/ConcertView/GoodAction.do?sidx=${sidx}&c_idx=${aa.c_idx}&good=B&tab=main_concert_review_all">👎</a>${aa.bad}</li>	
														</c:when>
													</c:choose>
													
													<c:choose>
														<c:when test="${aa.midx == sessionScope.midx}">	
								                   			<li>|</li>
								                   			<li><a href="javascript:click()" onclick="removeCheck2('${aa.c_idx}')">삭제</a></li>
								                   		</c:when>
				              								<c:when test="${aa.midx != sessionScope.midx}">							                     
								                   			<li>|</li>
								                   			<li><a href="javascript:click()" onclick="reportCheck('${aa.c_idx}')">신고</a></li>
				              								</c:when>
													</c:choose>                  							
			             						</ul>
                  							</div>
            							</div>	
									</div>
								</div>
							</li>
							</c:when>
						</c:choose>
						</c:forEach>
						<hr class="hrbar">
					</ul>
								
					<c:set var="page" value="${(param.p == null)?1:param.p}"/>
					<c:set var="startNum" value="${page-(page-1)%5}"/>
					<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"/>
						
							<div id="main_page_button_set">
							<a href="?p=1&q=&tab=<%=tab%>&sidx=${sidx}">
								<div class="main_page_button main_event_page_bn">
								<div class="main_page_button_lg">&lt;&lt;</div>
								</div>
							</a>
							<c:if test= "${startNum>1}">
								<a href= "?p=${startNum-1}&tab=<%=tab%>&sidx=${sidx}#hold">
									<div class="main_page_button main_page_bn">
										<div class="main_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
							<c:if test= "${startNum<=1}">
								<a href= "#" onclick="alert('이전 페이지가 없습니다.');return false;">
									<div class="main_page_button main_page_bn">
										<div class="main_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
								
								<div class="main_page_bn">
									<c:if test="${empty elist}">
									<div class="main_page_button_page">
										<a>0</a>
									</div>
									</c:if>
									<c:forEach var="i" begin="0" end= "4">
										<c:if test ="${(startNum+i) <= lastNum}">
											<div class="main_page_button_page">
												<a style="color: ${(page==(startNum+i))?'red':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?p=${startNum+i}&tab=<%=tab%>&sidx=${sidx}#hold" >${startNum+i}</a>
											</div>
										</c:if>
									</c:forEach>
								</div>
								<c:if test="${startNum+4<lastNum}">
									<a href="?p=${startNum+5}&tab=<%=tab%>&sidx=${sidx}#hold">
										<div class="main_page_button main_event_page_bn">
											<div class="main_page_button_lg">&gt;</div>
										</div>	
									</a>
								</c:if>
								<c:if test="${startNum+4>=lastNum}">
									<a href="#" onclick="alert('다음 페이지가 없습니다.');return false;">
										<div class="main_page_button main_event_page_bn">
											<div class="main_page_button_lg">&gt;</div>
										</div>
									</a>	
								</c:if>
										
								<div class="main_page_button main_event_page_bn">
									<a href="?p=${lastNum}&tab=<%=tab%>&sidx=${sidx}#hold"><div class="main_page_button_llgg">&gt;&gt;</div></a>
								</div>
							</div>
						</div>
					</div>
				</div>
					
				
				
					<!---------------------------------QNA--------------------------------------->
				<div id="main_concert_question_all" style="display:none;">
					<div id="main_concert_question_div">
						<div id="main_concert_question_notice">
							<div id="main_concert_question_notice_ment">
								Q&amp;A게시판은 모든 질문과 답변에 참여할 수 있는 공간입니다.<br>
							</div>
							<div id="main_concert_question_notice_ment_sub">
								예매/배송 관련 문의사항은 고객센터><a href="<%=request.getContextPath() %>/Customer/QuestionList.do"class="hrefquestion">1:1문의</a> 또는 <a href="<%=request.getContextPath() %>/Customer/FaqList.do" class="hrefquestion">FAQ</a>나 이용안내를 이용해주세요.
							</div>
						</div>
					</div>
					<div id="main_concert_question_content">
						<form name="qna">
						<div id="main_concert_question_content_write_div">
							<div class="main_concert_question_content_write_all">
								<textarea name="content" id="main_concert_question_content_write_button_action" placeholder="
	* 게시된 글의 저작권을 글을 작성한 회원에게 있으며 게시물로 인해 발생하는 문제는 게시자 본인에게 책임이 있습니다.
	* 게시판에 고객님의 연락처, 주소 등의 개인정보가 포함된 글을 올리실 경우에는 타인에게 해당 정보가 노출될 수 있으니 
	  게재를 삼가하여 주시기 바랍니다."></textarea> <!-- 정리한다고 바꾸면 글씨 날라가니깐 그냥 두삼 -->
							</div>
							<input type="button" class="writeaction" id="main_concert_question_content_write_button" value="등록">
						</div>
						</form>
						<div id="main_concert_question_content_number_all">
							<div id="main_concert_question_content_number_order">
								<div id="main_concert_question_content_number">총 ${count}개</div>
								<div id="main_concert_question_content_list_order">
									<ul>
										<li><a href="?sidx=${sidx}&od=latest&tab=main_concert_question_all#hold2">최신순</a></li>&nbsp;&nbsp;|&nbsp;
										<li><a href="?sidx=${sidx}&od=recommended&tab=main_concert_question_all#hold2">추천순</a></li></li>&nbsp;&nbsp;|&nbsp;
										<li><a href="?sidx=${sidx}&od=comments&tab=main_concert_question_all#hold2">댓글순</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div id="main_concert_question_content_list">
							<ul>
								<c:if test="${empty elist}">
									<hr class="hrbar">
									<il><h2 align ="center">해당 글이 비어 있습니다. 멋진 댓글을 남겨보세요.</h2></il>
								</c:if>
								<c:forEach var="cc" items="${elist}">
								<c:choose>
								<c:when test="${cc.c_depth == 0}">
								<hr class="hrbar">
								<li class="modifyc2_${cc.origin_c_idx}_c">
									<div>
										<div class="main_concert_question_content_list_id_div">
											<img src="../icon/person.png" class="main_concert_question_content_list_id_all">
											<div class="main_concert_question_content_list_id">
												${cc.mid}
											</div>
										</div>
										<div class="main_concert_question_content_list_text_div">
											<div class="main_concert_question_content_list_text">
												${cc.c_content}
											</div>
											<div class="main_concert_question_content_list_date">
												${cc.c_regdate}
											</div>
											<div class="main_concert_question_content_list_content_good">
												<ul>
													<c:choose>
														<c:when test ="${sessionScope.midx == null}">
															<li><a href=# onclick="alert('로그인 후 이용 가능합니다.');return false;">👍</a>${cc.origin_good}</li>	
															<li><a href=# onclick="alert('로그인 후 이용 가능합니다.');return false;">👎</a>${cc.origin_bad}</li>	
														</c:when>
														<c:when test ="${sessionScope.midx == cc.midx}">
															<li><a href=# onclick="alert('본인이 작성하신 글에는 이용 불가합니다.');return false;">👍</a>${cc.origin_good}</li>	
															<li><a href=# onclick="alert('본인이 작성하신 글에는 이용 불가합니다.');return false;">👎</a>${cc.origin_bad}</li>	
														</c:when>
														<c:when test ="${sessionScope.midx != null}">
															<li><a href="<%=request.getContextPath() %>/ConcertView/GoodAction.do?sidx=${sidx}&origin_c_idx=${cc.origin_c_idx}&good=G&tab=main_concert_question_all">👍</a>${cc.origin_good}</li>
															<li><a href="<%=request.getContextPath() %>/ConcertView/GoodAction.do?sidx=${sidx}&origin_c_idx=${cc.origin_c_idx}&good=B&tab=main_concert_question_all">👎</a>${cc.origin_bad}</li>	
														</c:when>
													</c:choose>
													<c:choose>
														<c:when test="${cc.midx == sessionScope.midx}">							                     
								                   			<li>|</li>
								                    		<li><a href="javascript:click()" id="${cc.origin_c_idx}" class="modify2">수정</a></li>
								                   			<li>|</li>
								                   			<li><a href="javascript:click()" onclick="removeCheck('${cc.origin_c_idx}')">삭제</a></li>
								                   		</c:when>
		                								<c:when test="${cc.midx != sessionScope.midx}">							                     
								                   			<li>|</li>
								                   			<li><a href="javascript:click()" onclick="reportCheck('${cc.c_idx}')">신고</a></li>
		                								</c:when>
													</c:choose>   
												</ul>
											</div>
											<a href="javascript:void(0);" id="${cc.origin_c_idx}" class="main_concert_question_content_list_more test2">댓글 ${cc.cnt}개</a>
										</div>
									</div>
								</li>
								<li class="modify2_${cc.origin_c_idx}_e" >
									<img src="../icon/person.png" class="main_concert_question_content_list_id_all">
									<div class="main_concert_question_content_list_id">${cc.mid}</div>
									<div class="modifybox">	
										<textarea class="modify2_${cc.c_idx}" value>${cc.c_content}</textarea>
										<input type="button"  id="${cc.c_idx}"  class="expecptmodify2" value="등록">
										<input type="button" id="${cc.origin_c_idx}" class="modify2"value="취소">
									</div>
								</li>
								<li class="reypleR2_${cc.origin_c_idx}_W">							
									<div class="commentbox">	
										<input type="text" size="100" name="content" class="comment2_${cc.origin_c_idx}">
										<input type="button"  id="${cc.origin_c_idx}" class="comment2" value="등록">		
									</div>
								</li>
							</c:when>
							<c:when test="${cc.c_depth == 1}">
								<li class="reypleR2_${cc.origin_c_idx}_W">
									<div class= "commentrow">		
										<div class="commentid">
												${cc.mid}
										</div>
										<div class="commentcontent">
											<div class="commentview">${cc.c_content}</div>
											<div class="likeanddate">
												<div class="main_concert_question_content_list_date commentdate">
													${cc.c_regdate}
												</div>
												<div class="commentlike">
													<ul>
														<c:choose>
															<c:when test ="${sessionScope.midx == null}">
															<li><a href=# onclick="alert('로그인 후 이용 가능합니다.');return false;">👍</a>${cc.good}</li>	
															<li><a href=# onclick="alert('로그인 후 이용 가능합니다.');return false;">👎</a>${cc.bad}</li>	
															</c:when>
															<c:when test ="${sessionScope.midx == cc.midx}">
															<li><a href=# onclick="alert('본인이 작성하신 글에는 이용 불가합니다.');return false;">👍</a>${cc.good}</li>	
															<li><a href=# onclick="alert('본인이 작성하신 글에는 이용 불가합니다.');return false;">👎</a>${cc.bad}</li>	
															</c:when>
															<c:when test ="${sessionScope.midx != null}">
																<li><a href="<%=request.getContextPath() %>/ConcertView/GoodAction.do?sidx=${sidx}&c_idx=${cc.c_idx}&good=G&tab=main_concert_question_all">👍</a>${cc.good}</li>
																<li><a href="<%=request.getContextPath() %>/ConcertView/GoodAction.do?sidx=${sidx}&c_idx=${cc.c_idx}&good=B&tab=main_concert_question_all">👎</a>${cc.bad}</li>	
															</c:when>
														</c:choose>
														<c:choose>
															<c:when test="${cc.midx == sessionScope.midx}">	
									                   			<li>|</li>
									                   			<li><a href="javascript:click()" onclick="removeCheck2('${cc.c_idx}')">삭제</a></li>
									                   		</c:when>
			                								<c:when test="${cc.midx != sessionScope.midx}">							                     
									                   			<li>|</li>
									                   			<li><a href="javascript:click()" onclick="reportCheck('${cc.c_idx}')">신고</a></li>
			                								</c:when>
														</c:choose>                  							
			                  							</ul>
		                  							</div>
	                  							</div>	
											</div>
										</div>
									</li>
								</c:when>
							</c:choose>
							</c:forEach>
							<hr class="hrbar">
						</ul>	
					<c:set var="page" value="${(param.p == null)?1:param.p}"/>
					<c:set var="startNum" value="${page-(page-1)%5}"/>
					<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"/>
							<div id="main_page_button_set">
							<a href="?p=1&q=&tab=<%=tab%>&sidx=${sidx}#hold">
								<div class="main_page_button main_event_page_bn">
								<div class="main_page_button_lg">&lt;&lt;</div>
								</div>
							</a>
							<c:if test= "${startNum>1}">
								<a href= "?p=${startNum-1}&tab=<%=tab%>&sidx=${sidx}#hold">
									<div class="main_page_button main_page_bn">
										<div class="main_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
							<c:if test= "${startNum<=1}">
								<a href= "#" onclick="alert('이전 페이지가 없습니다.');return false;">
									<div class="main_page_button main_page_bn">
										<div class="main_page_button_lg">&lt;</div>
									</div>
								</a>
							</c:if>
								
								<div class="main_page_bn">
									<c:if test="${empty elist}">
									<div class="main_page_button_page">
										<a>0</a>
									</div>
									</c:if>
									<c:forEach var="i" begin="0" end= "4">
										<c:if test ="${(startNum+i) <= lastNum}">
											<div class="main_page_button_page">
												<a style="color: ${(page==(startNum+i))?'red':''}; font-weight:${(page==(startNum+i))?'bold':''};" href="?p=${startNum+i}&tab=<%=tab%>&sidx=${sidx}#hold" >${startNum+i}</a>
											</div>
										</c:if>
									</c:forEach>
								</div>
								<c:if test="${startNum+4<lastNum}">
									<a href="?p=${startNum+5}&tab=<%=tab%>&sidx=${sidx}#hold">
										<div class="main_page_button main_event_page_bn">
											<div class="main_page_button_lg">&gt;</div>
										</div>	
									</a>
								</c:if>
								<c:if test="${startNum+4>=lastNum}">
									<a href="#" onclick="alert('다음 페이지가 없습니다.');return false;">
										<div class="main_page_button main_event_page_bn"> 
											<div class="main_page_button_lg">&gt;</div>
										</div>
									</a>	
								</c:if>
										
								<div class="main_page_button main_event_page_bn">
									<a href="?p=${lastNum}&tab=<%=tab%>&sidx=${sidx}#hold"><div class="main_page_button_llgg">&gt;&gt;</div></a>
								</div>
							</div>
						</div>
					</div>
				</div>
					
					
					<!---------------------------------공연장위치--------------------------------------->
				<div id="main_concert_place_all" style="display:none;">
					<div id="main_concert_place_div">
						<img src="../image/seoyeonmain.jpg" class="main_concert_place_set">
						<div id="main_concert_place_content" class="main_concert_place_set">
							<div id="main_concert_place_name">
								서연아트홀
							</div>
							<div id="main_concert_place_address">
								서울 종로구 창경궁로 258-9(명륜2가)
							</div>
						</div>
					</div>
				</div>
					<!---------------------------------예매안내-------------------------------------->
				<div id="main_concert_info_all" style="display:none;">
					<div>티켓 수령 방법 안내</div>
					<div>
						현장수령<br>
						- 예매번호가 포함되어 있는 예매확인서와 예매자의 실물 신분증(복사본 및 사진 불가) 을 매표소에 제출하시면 편리하게 티켓을 수령하실 수 있습니다.<br>
						※ 공연별 정책이 상이하니 자세한 내용은 예매페이지 내 상세정보 확인 부탁드립니다.<p/>
						배송<br>
						- 배송을 선택하신 경우 예매완료(결제익일) 기준 4~5일 이내에 예매 시 입력하신 주소로 배송됩니다. (주말/공휴일 제외한 영업일 기준)<br>
						- 일괄배송의 경우 공연 별로 배송일자가 상이하며 지정된 배송일자 기준으로 배송이 시작됩니다. (지정된 배송일자는 상세정보 및 예매공지사항에서 확인할 수 있습니다.)<br>
						- 지역 및 배송서비스 사정에 따라 배송사가 변경될 수 있으며, 배송일이 추가적으로 소요될 수 있습니다. (CJ대한통운, 우체국 외 1개 업체)<br>
					</div>
				</div>
			</article>
		</section>
		<footer>
				<hr class="f_bar" id="f_bar_bottom">
				<div id="f_last">
					<span class="f_bottom_ment"><img src="../icon/lupinlogo.png" id="f_logo"></span>
					<span class="f_bottom_ment">
						<span class="f_bottom_tagset">예매문의(1234-1234)</span>
						<a href="#" class="f_bottom_tagset">티켓판매제휴&nbsp;&nbsp;&nbsp;&nbsp;</a>
						<a href="#" class="f_bottom_tagset">예매가이드&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</span>
				</div>
				<hr class="f_bar" id="f_bar_bottom">
			<div class="f_title_inner">
				<span id="f_menu_contract_span">
					<ul id="f_menu_contract">
						<li><a href="#">회사소개</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">이용약관</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">전자금융거래약관</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">위치기반서비스 이용약관&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						<li><a href="#" style="font-weight:bold;">개인정보처리방침</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">청소년보호정책</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">파트너센터</a>&nbsp;&nbsp;&nbsp;&nbsp;</li><br>
					</ul>
				</span>
			</div>
			<div class="f_title_inner">
				<span id="f_menu_produce">
					<ul id="f_menu_produce">
						<li><a href="#" style="font-weight:bold;">(주)티켓루팡</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">전북 전주시 덕진구 백제대로 572 4층</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">대표이사:최민우</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">사업자등록번호: 111-11-11111&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
						<li><a href="#">통신판매업 신고번호: 제1111-이젠이젠-1111호 <a href="#" style="font-weight:bold;">&nbsp;&nbsp;사업자정보확인>&nbsp;&nbsp;</a></a>&nbsp;&nbsp;&nbsp;&nbsp;</li><br>
						<li><a href="#">고객센터(평일/주말 09:00~18:00): 1234-1234(유료)</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">호스팅제공자: (주)티켓루팡</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
						<li><a href="#">&copy;TicketLupin Corp. All rights reserved.</a>&nbsp;&nbsp;&nbsp;&nbsp;</li><br>
					</ul>
				</span>
			</div>
				<a name="hold"></a>
		</footer>
	</body>
</html>