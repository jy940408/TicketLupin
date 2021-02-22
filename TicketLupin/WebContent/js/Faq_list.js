$(document).ready(function(){
	$(".title").click(function(){
		$(this).parent().next(".comment").toggle();
	});
});