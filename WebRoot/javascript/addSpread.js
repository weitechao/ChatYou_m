 $(function(){
	 $.ajax({
			type : 'POST',
			url :path+"/admin/getSpreadUserMsg.htm",
			data : {},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if (data.m_istatus == 1) {
				    $('#loginname').val(data.m_object.t_user_name);
				    $('#loginpwd').val(data.m_object.t_pass_word);
				    $('#phone').val(data.m_object.t_phone);
				    $('#t_qq').val(data.m_object.t_qq);
				    $('#t_weixin').val(data.m_object.t_weixin);
				    $('#t_settlement_type').val(data.m_object.t_settlement_type);
				    $('#t_bank').val(data.m_object.t_bank);
				    $('#t_gold_proportions').val(data.m_object.t_gold_proportions+"%");
				    $('#t_vip_proportions').val(data.m_object.t_vip_proportions+"%");
				    $('#t_spread_id').val(data.m_object.t_spread_id);
				    $('#t_short_url').val(data.m_object.t_short_url);
				    $('#t_create_time').val(data.m_object.t_create_time);
					$('#t_id').val(data.m_object.t_id);
				} else  {
					window.location.href = path + '/error.html';
				}
			}
		});
});
 


/*提交数据*/
function on_click_submit(){
	 $.ajax({
			type : 'POST',
			url :path+"/admin/updateSpreadMesg.htm",
			data : {
				t_id: $('#t_id').val(),
				loginpwd:$('#loginpwd').val(),
				t_phone: $('#phone').val(),
				t_qq: $('#t_qq').val(),
				t_weixin:  $('#t_weixin').val(),
				t_settlement_type:$('#t_settlement_type').val(),
				t_bank: $('#t_bank').val()
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if (data.m_istatus == 1) {
					$('#my_comfirm').empty();
					$('#my_comfirm').append('信息已存储!');
					$('#myComfirm').modal('show');
				} else  {
					window.location.href = path + '/error.html';
				}
			}
		});
}
/**重置短连接*/
function on_click_reset_url(){
	 $.ajax({
			type : 'POST',
			url :path+"/admin/resetUserUrl.htm",
			data : {
				t_id: $('#t_id').val()
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if (data.m_istatus == 1) {
					$('#my_comfirm').empty();
					$('#my_comfirm').append('信息已存储!');
					$('#myComfirm').modal('show');
					$('#t_short_url').val(data.m_object);
				} else  {
					window.location.href = path + '/error.html';
				}
			}
		});
}

