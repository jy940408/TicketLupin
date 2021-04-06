/*
 * @Javascript Name : timePicker.js
 * @Description : timePicker
 * @Modification Information
 * @since 2018. 6. 5
 * @Program By PLTS_G
 */

function fn_timePicker(obj) {
	var id = $(obj).attr("id");
	$("#" + id).timepicker({
		timeFormat : "HH:mm",
		interval : 10,
		dynamic : false,
		dropdown : true,
		scrollbar : true
	});
	$("#" + id).timepicker("open");
}