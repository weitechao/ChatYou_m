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
		url : path + '/admin/enableOrDisableSmsSteup.htm',
		data : {
			smsId : dataId
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
function on_click_del(contr) {

	$.ajax({
		type : 'POST',
		url : path + '/admin/delSmsSteup.htm',
		data : {
			smsId : $("#del_id").val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				window.location.href = path + '/admin/jumpSmsList.htm';
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
		url : path + '/admin/getDataById.htm',
		data : {
			smsId : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				console.log(data.m_object);
				$("#t_id").val(data.m_object.t_id);
				$("#appid").val(data.m_object.appid);
				$("#appkey").val(data.m_object.appkey);
				$("#templateId").val(data.m_object.templateId);
				$("#smsSign").val(data.m_object.smsSign);
				$("#t_is_enable").val(data.m_object.t_is_enable);
				$("#t_platform_type").val(data.m_object.t_platform_type);
				$('#myModal').modal('show');
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}

function on_click_submit(){
	var data = {
			 t_id:$("#t_id").val(),
			 appid:$("#appid").val(),
			 appkey:$("#appkey").val(),
			 templateId:$("#templateId").val(),
			 smsSign:$("#smsSign").val(),
			 t_is_enable:$("#t_is_enable").val(),
			 t_platform_type:$("#t_platform_type").val()};
	
	console.log(data);
	
	$.ajax({
		type : 'POST',
		url : path + '/admin/saveOrUpdate.htm',
		data : data,
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				window.location.href = path + '/admin/jumpSmsList.htm';
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
	
}
