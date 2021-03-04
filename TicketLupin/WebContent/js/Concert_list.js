$(document).ready(function(){
	$(".main_nav_all").hide();
	
	$("#main_nav_event").mouseover(function(){
		$(".main_nav_all").hide();
		$("#nav_menu_sub_event_div").slideDown(500)
	});
	$("#nav_menu_sub_event_div").click(function(){
		$("#nav_menu_sub_event_div").slideUp(500)
	});
	$("#main_nav_myticket").mouseover(function(){
		$(".main_nav_all").hide();
		$("#nav_menu_sub_myticket_div").slideDown(500)
	});
	$("#nav_menu_sub_myticket_div").click(function(){
		$("#nav_menu_sub_myticket_div").slideUp(500)
	});
	$("#main_nav_home").mouseover(function(){
		$(".main_nav_all").slideUp(500)
	});
	$("#main_nav_concert").mouseover(function(){
		$(".main_nav_all").slideUp(500)
	});
	$("#main_nav_ranking").mouseover(function(){
		$(".main_nav_all").slideUp(500)
	});
	$("#main_nav_news").mouseover(function(){
		$(".main_nav_all").slideUp(500)
	});
});

function getCheckboxValue(name){
				
	if(name="genre"){
		var query = "input[name='genre']:checked";
		var selectedTags = document.querySelectorAll(query);
		
		var result = "";
		
		selectedTags.forEach(
		(tag) => {result += document.getElementById([tag.value]).innerText+" "}
		);
		
		document.getElementById("main_concert_list_search_tag_checked").innerText = result;
	}else if(name="place"){
		var query = "input[name='place']:checked";
		var selectedTags = document.querySelectorAll(query);
		
		var result = "";
		
		selectedTags.forEach(
		(tag) => {result += document.getElementById([tag.value]).innerText+" "}
		);
		
		document.getElementById("main_concert_list_search_tag_checked").innerText = result;
	}else{
		var query = "input[name='sold']:checked";
		var selectedTags = document.querySelectorAll(query);
		
		var result = "";
		
		selectedTags.forEach(
		(tag) => {result += document.getElementById([tag.value]).innerText+" "}
		);
		
		document.getElementById("main_concert_list_search_tag_checked").innerText = result;
	}
}