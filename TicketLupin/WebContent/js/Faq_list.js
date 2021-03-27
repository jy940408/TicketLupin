$(document).ready(function(){
	
	$(".title").click(function(){
		
		$('.comment').not($(this).parent().next(".comment")).hide();
		
		$(this).parent().next(".comment").toggle();
	});
});