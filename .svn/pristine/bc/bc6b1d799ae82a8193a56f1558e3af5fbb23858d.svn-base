 
 /**
  * 重置表格
  */
function on_click_reset(){
	$("#nickName").val('');
	$("#phone").val('');
	$("#sex").val('1');
	$("#age").val('');
	$("#t_height").val('');
	$("#t_weight").val('');
	$("#t_constellation").val('白羊座');
	$("#t_city").val('');
	$("#t_vocation").val('网红');
	$("#t_synopsis").val('');
	$("#t_autograph").val('');
	$("#t_role").val('1');
}



/*提交数据*/
function on_click_submit(){
	
	var isOk = true;
	if(null == $("#nickName").val() || '' == $("#nickName").val()){
		$("#nickName").css({ "border-color": "red" });
		$('#nickName_validate').empty();
		$('#nickName_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	
	if(null == $("#age").val() || '' == $("#age").val() || !isRealNum($("#age").val())){
		$("#age").css({ "border-color": "red" });
		$('#age_validate').empty();
		$('#age_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		$("#age").val('');
		$('#age').attr('placeholder','请输入正确的年龄.')
		isOk = false;
	}
	if(null == $("#t_height").val() || '' == $("#t_height").val() || !isRealNum($("#t_height").val())){
		$("#t_height").css({ "border-color": "red" });
		$('#t_height_validate').empty();
		$('#t_height_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		$("#t_height").val('');
		$('#t_height').attr('placeholder','请输入正确的身高.')
		isOk = false;
	}
	if(null == $("#t_weight").val() || '' == $("#t_weight").val() || !isRealNum($("#t_weight").val())){
		$("#t_weight").css({ "border-color": "red" });
		$('#t_weight_validate').empty();
		$('#t_weight_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		$("#t_weight").val('');
		$('#t_weight').attr('placeholder','请输入正确的体重.')
		isOk = false;
	}
	if(null == $("#t_city").val() || '' == $("#t_city").val()){
		$("#t_city").css({ "border-color": "red" });
		$('#t_city_validate').empty();
		$('#t_city_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
//	if(null == $("#t_synopsis").val() || '' == $("#t_synopsis").val()){
//		$("#t_synopsis").css({ "border-color": "red" });
//		$('#t_synopsis_validate').empty();
//		$('#t_synopsis_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
//		isOk = false;
//	}
	if(null == $("#t_autograph").val() || '' == $("#t_autograph").val()){
		$("#t_autograph").css({ "border-color": "red" });
		$('#t_autograph_validate').empty();
		$('#t_autograph_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	
	if(!isOk){
		return;
	}
	$('#myFrom').submit();
}

