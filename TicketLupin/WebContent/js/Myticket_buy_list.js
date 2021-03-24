$(function(){
	$('.btn_rsrv').click(function(){
		$('.btn_rsrv').css("background-color", "#A6A6A6");
		$('.btn_cancel').css("background-color", "white");
		$('.cancel_view').hide();
		$('.box_customer').show();
	});
	$('.btn_cancel').click(function(){
		$('.btn_rsrv').css("background-color", "white");
		$('.btn_cancel').css("background-color", "#A6A6A6");
		$('.box_customer').hide();
		$('.cancel_view').show();
	});
});

/*--------------------------------------------------------------------------------------------------------------------------------------*/

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

/*--------------------------------------------------------------------------------------------------------------------------------------*/
