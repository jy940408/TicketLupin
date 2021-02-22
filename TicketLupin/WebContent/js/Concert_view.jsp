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
}

/*--------------------------------------------------------------------------------------------------------------------------------------*/

function tabMenuColor(id){
	var obj1 = document.getElementById("main_concert_detail_menu_div1");
	var obj2 = document.getElementById("main_concert_detail_menu_div2");
	var obj3 = document.getElementById("main_concert_detail_menu_div3");
	var obj4 = document.getElementById("main_concert_detail_menu_div4");
	var obj5 = document.getElementById("main_concert_detail_menu_div5");
	var obj6 = document.getElementById("main_concert_detail_menu_div6");
	
	obj1.style.backgroundColor="";
	obj2.style.backgroundColor="";
	obj3.style.backgroundColor="";
	obj4.style.backgroundColor="";
	obj5.style.backgroundColor="";
	obj6.style.backgroundColor="";
	
	if(id=="main_concert_detail_menu_div1"){
		obj1.style.backgroundColor = "rgba(0,0,0,0.2)";
	}else if(id=="main_concert_detail_menu_div2"){
		obj2.style.backgroundColor = "rgba(0,0,0,0.2)";
	}else if(id=="main_concert_detail_menu_div3"){
		obj3.style.backgroundColor = "rgba(0,0,0,0.2)";
	}else if(id=="main_concert_detail_menu_div4"){
		obj4.style.backgroundColor = "rgba(0,0,0,0.2)";
	}else if(id=="main_concert_detail_menu_div5"){
		obj5.style.backgroundColor = "rgba(0,0,0,0.2)";
	}else{
		obj6.style.backgroundColor = "rgba(0,0,0,0.2)";
	}

}

/*--------------------------------------------------------------------------------------------------------------------------------------*/

function showAnswerTab(val){
	var obj = document.getElementById(val);
	
	if(obj.style.display=="none"){
		obj.style.display="";
	}else{
		obj.style.display="none";
	}
}

/*--------------------------------------------------------------------------------------------------------------------------------------*/

$(document).ready(function(){
$(".main_nav_all").hide();
	$("#main_nav_event").mouseover(function(){
		$(".main_nav_all").hide();
		$("#nav_menu_sub_event_div").slideDown(500);
	});
	$("#nav_menu_sub_event_div").click(function(){
		$("#nav_menu_sub_event_div").slideUp(500);
	});
	$("#main_nav_myticket").mouseover(function(){
		$(".main_nav_all").hide();
		$("#nav_menu_sub_myticket_div").slideDown(500);
	});
	$("#nav_menu_sub_myticket_div").click(function(){
		$("#nav_menu_sub_myticket_div").slideUp(500);
	});
	$("#main_nav_home").mouseover(function(){
		$(".main_nav_all").slideUp(500);
	});
	$("#main_nav_concert").mouseover(function(){
		$(".main_nav_all").slideUp(500);
	});
	$("#main_nav_ranking").mouseover(function(){
		$(".main_nav_all").slideUp(500);
	});
	$("#main_nav_news").mouseover(function(){
		$(".main_nav_all").slideUp(500);
	});
});

/*--------------------------------------------------------------------------------------------------------------------------------------*/

var today = new Date(); // 오늘 날짜
var date = new Date();

function beforem(){ //이전 달을 today에 값을 저장
	today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
	build(); //만들기
}

function nextm(){  //다음 달을 today에 저장
	today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
	build();
}

function build(){

	var nMonth = new Date(today.getFullYear(), today.getMonth(), 1); //현재달의 첫째 날
	var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0); //현재 달의 마지막 날, 일자에 0을 넣으면 전달 말일을 리턴
	var tbcal = document.getElementById("calendar"); // 테이블 달력을 만들 테이블
	var yearmonth = document.getElementById("yearmonth"); //  년도와 월 출력할곳
	
	yearmonth.innerHTML = today.getFullYear() + "년 "+ (today.getMonth() + 1) + "월"; //년도와 월 출력
	
	if(today.getMonth()+1==12){ //  눌렀을 때 월이 넘어가는 곳
		before.innerHTML=(today.getMonth())+"월";
		next.innerHTML="1월";
	}else if(today.getMonth()+1==1){ //  1월 일 때
		before.innerHTML="12월";
		next.innerHTML=(today.getMonth()+2)+"월";
		
	}else{ //   12월 일 때
		before.innerHTML=(today.getMonth())+"월";
		next.innerHTML=(today.getMonth()+2)+"월";
	}
	
   
	// 남은 테이블 줄 삭제
	while (tbcal.rows.length > 2){
		tbcal.deleteRow(tbcal.rows.length - 1);
	}

	var row = null;
	row = tbcal.insertRow();
	var cnt = 0;

	// 1일 시작칸 찾기
	for (i = 0; i < nMonth.getDay(); i++){
		cell = row.insertCell();
		cnt = cnt + 1;
	}

	// 달력 출력
	for (i = 1; i <= lastDate.getDate(); i++){ // 1일부터 마지막 일까지
		cell = row.insertCell();
		cell.innerHTML = i;
		cnt = cnt + 1;
		if (cnt % 7 == 1){//일요일 계산
			cell.innerHTML = "<font color=#FF9090>" + i//일요일에 색
		}
		if (cnt % 7 == 0){ // 1주일이 7일 이므로 토요일 계산
			cell.innerHTML = "<font color=#7ED5E4>" + i//토요일에 색
			row = calendar.insertRow();// 줄 추가
		}
		if(today.getFullYear()==date.getFullYear()&&today.getMonth()==date.getMonth()&&i==date.getDate()){
			cell.bgColor = "#BCF1B1"; //오늘날짜배경색
			cell.style.borderRadius = "1em";
		}
	}
}
    