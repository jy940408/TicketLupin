$(document).ready(function() {
	$('.list_slider').slick({
		infinite: true,
		slidesToShow: 5,
		slidesToScroll: 5,
		nextArrow:$('.next'),
		prevArrow:$('.prev'),
	});
});

document.getElementById('hot_paging2').style.display = 'none';

function hot(){
	if(document.getElementById('hot_paging2').style.display == 'none'){
		document.getElementById('hot_paging2').style.display = 'block';
	}else{
		document.getElementById('hot_paging').style.display = 'none';
	}
}