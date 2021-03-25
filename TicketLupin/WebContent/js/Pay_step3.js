function allCheckFunc( obj ) {
		$("[name=chkAgree1]").prop("checked", $(obj).prop("checked") );
		$("[name=chkAgree2]").prop("checked", $(obj).prop("checked") );
		$("[name=chkAgree3]").prop("checked", $(obj).prop("checked") );
		$("[name=chkAgree4]").prop("checked", $(obj).prop("checked") );
		$("[name=chkAgree5]").prop("checked", $(obj).prop("checked") );
		$("[name=chkAgree6]").prop("checked", $(obj).prop("checked") );
}

/* 체크박스 체크시 전체선택 체크 여부 */
function oneCheckFunc( obj )
{
	var allObj = $("[name=chkAgreeAll]");
	var objName = $(obj).attr("name");

	if( $(obj).prop("checked") )
	{
		checkBoxLength = $("[name="+ objName +"]").length;
		checkedLength = $("[name="+ objName +"]:checked").length;

		if( checkBoxLength == checkedLength ) {
			allObj.prop("checked", true);
		} else {
			allObj.prop("checked", false);
		}
	}
	else
	{
		allObj.prop("checked", false);
	}
}

$(function(){
	$("[name=chkAgreeAll]").click(function(){
		allCheckFunc( this );
	});
	$("[name=chkAgree1]").each(function(){
		$(this).click(function(){
			oneCheckFunc( $(this) );
		});
	});
	$("[name=chkAgree2]").each(function(){
		$(this).click(function(){
			oneCheckFunc( $(this) );
		});
	});
	$("[name=chkAgree3]").each(function(){
		$(this).click(function(){
			oneCheckFunc( $(this) );
		});
	});
	$("[name=chkAgree4]").each(function(){
		$(this).click(function(){
			oneCheckFunc( $(this) );
		});
	});
	$("[name=chkAgree5]").each(function(){
		$(this).click(function(){
			oneCheckFunc( $(this) );
		});
	});
	$("[name=chkAgree6]").each(function(){
		$(this).click(function(){
			oneCheckFunc( $(this) );
		});
	});
});