/**
 * 点击显示弹出框
 * 
 * @param id
 */
function on_click_open_model(modelId, id, controlId) {
	$("#" + controlId).val(id);
	$('#' + modelId).modal('show');
}

/**
 * 点击启停用设置
 * 
 * @param controlId
 * @param dataId
 */
function on_click_update_state(controlId, dataId) {

	$.ajax({
		type : 'POST',
		url : path + '/admin/updateLoginState.htm',
		data : {
			t_id : dataId
		},
		dataType : 'json',
		success : function(data) {
			if ('禁用' == $(controlId).html() && data.m_istatus == 1) {
				$(controlId).html('启用');
				$(controlId).css('color', '#00EC00');
			} else if ('启用' == $(controlId).html() && data.m_istatus == 1) {
				$(controlId).html('禁用');
				$(controlId).css('color', 'red');
			}
		}
	});
}

/**
 * 删除信息
 * 
 * @param smsId
 */
function on_click_del() {

	$.ajax({
		type : 'POST',
		url : path + '/admin/delLoginSteup.htm',
		data : {
			t_id : $("#del_id").val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				window.location.href = path + '/admin/loginSetUp.htm';
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/**
 * 打开修改模拟框
 * @param id
 */
function on_click_open_updae_model(id){
	$.ajax({
		type : 'POST',
		url : path + '/admin/getLoginSetUpById.htm',
		data : {t_id : id},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				console.log(data.m_object);
				$("#t_id").val(data.m_object.t_id);
				$("#t_app_id").val(data.m_object.t_app_id);
				$("#t_app_secret").val(data.m_object.t_app_secret);
				$("#t_type").val(data.m_object.t_type);
				$("#t_state").val(data.m_object.t_state);
				$('#myModal').modal('show');
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}


/**点击提交*/
function on_click_submit(){
	var data = {
			 t_id:$("#t_id").val(),
			 t_app_id:$("#t_app_id").val(),
			 t_app_secret:$("#t_app_secret").val(),
			 t_type:$("#t_type").val(),
			 t_state:$("#t_state").val()};
	
	console.log(data);
	
	$.ajax({
		type : 'POST',
		url : path + '/admin/saveLoginSetUp.htm',
		data : data,
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				window.location.href = path + '/admin/loginSetUp.htm';
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
	
}
