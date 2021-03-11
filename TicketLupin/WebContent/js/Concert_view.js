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