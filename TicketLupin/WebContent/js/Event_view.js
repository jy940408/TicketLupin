var obj;
function urlClipCopy() {
	obj.select() ;
	document.execCommand("copy");
	alert("클립보드로 URL이 복사되었습니다.");
}
window.onload = function() {
	obj = document.getElementById('url');
	obj.value = location.href;
}