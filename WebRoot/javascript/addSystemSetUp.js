 $(function(){
	 $.ajax({
			type : 'POST',
			url :path+"/admin/getSystemSetUpDateil.htm",
			data : {},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if (data.m_istatus == 1) {
					$('#t_id').val(data.m_object.t_id);
				    $('#t_scope').val(data.m_object.t_scope);
				    $('#t_android_download').val(data.m_object.t_android_download);
				    $('#t_ios_download').val(data.m_object.t_ios_download);
				    $('#t_system_lang_girl').val(data.m_object.t_system_lang_girl);
				    $('#t_system_lang_male').val(data.m_object.t_system_lang_male);
				    $('#t_default_phone').val(data.m_object.t_default_phone);
				    $('#t_default_text').val(data.m_object.t_default_text);
				    $('#t_default_video').val(data.m_object.t_default_video);
				    $('#t_default_weixin').val(data.m_object.t_default_weixin);
				    $('#t_award_rules').val(data.m_object.t_award_rules);
				    $('#t_service_qq').val(data.m_object.t_service_qq);
				    $('#t_video_hint').val(data.m_object.t_video_hint);
				    $('#t_spreed_hint').val(data.m_object.t_spreed_hint);
				    $('#t_nickname_filter').val(data.m_object.t_nickname_filter);
				} else  {
					window.location.href = path + '/error.html';
				}
			}
		});
});
 


/*提交数据*/
function on_click_submit(){
	var obj =  [
		   {'name':'t_scope','type':'int'},
		   {'name':'t_android_download','type':'string'},
		   {'name':'t_ios_download','type':'string'},
		   {'name':'t_system_lang_girl','type':'string'},
		   {'name':'t_system_lang_male','type':'string'},
		   {'name':'t_default_text','type':'int'},
		   {'name':'t_default_video','type':'int'},
		   {'name':'t_default_weixin','type':'int'},
		   {'name':'t_award_rules','type':'string'},
		   {'name':'t_service_qq','type':'string'},
		 ];
	if($('#t_default_phone').val() == null){
		$("#t_default_phone").css({ "border-color": "red" });
		$('#t_default_phone_validate').empty();
		$('#t_default_phone_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove">请输入查看手机号的默认值</i>');
		return;
	}
	
	if(vaildate_is_submit(obj)){
		$.ajax({
			type : 'POST',
			url : path + "/admin/setSystemSetUp.htm",
			data : {
				t_id:$('#t_id').val(),
				t_scope:$('#t_scope').val(),
				t_android_download:$('#t_android_download').val(),
				t_ios_download:$('#t_ios_download').val(),
				t_system_lang_girl:  $('#t_system_lang_girl').val(),
		        t_system_lang_male:$('#t_system_lang_male').val(),
				t_default_text:$('#t_default_text').val(),
				t_default_video:$('#t_default_video').val(),
				t_default_phone:$('#t_default_phone').val(),
				t_default_weixin:$('#t_default_weixin').val(),
				t_award_rules:$('#t_award_rules').val(),
				t_service_qq:$('#t_service_qq').val(),
				t_nickname_filter:$('#t_nickname_filter').val(),
				t_video_hint:$('#t_video_hint').val(),
				t_spreed_hint:$('#t_spreed_hint').val()
			},
			dataType : 'json',
			success : function(data) {
				if (data.m_istatus != 0) {
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$('#myComfirm').modal('show');
				} else {
					window.location.href = path + '/error.html';
				}
			}
		});
	}
}

