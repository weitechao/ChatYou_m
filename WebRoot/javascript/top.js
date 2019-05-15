/**
 * 
 */
  
$(function(){
	$.ajax({
		url:path + "/admin/loadNotice.htm",
		dataType : 'json',
		success:function(data){
			console.log(data);
			$('#totalNotice').html(data.m_object.totalNotice)
			$('#notice').empty();
			$('#notice').append(data.m_object.detail);
		}
	});
})
  
  function on_click_load_update_password(){
	   $("#update_password").modal('show');
  }
  
  /**修改密码窗口*/
  function update_passwrd_submit(log_name){
	  
	  var newPassWord =  $('#newPassWord').val();
	  var repeatPassWord =  $('#repeatPassWord').val();
	  
	 var isOk = true ; 
	  
	  if(null == $('#originalCipher').val() || '' == $('#originalCipher').val()){
		   $("#originalCipher").css({ "border-color": "red" });
	  	   $('#originalCipher_validate').empty();
	  	   $('#originalCipher_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
	  	   isOk = false ; 
	  }
	  
	  if(null == newPassWord || '' == newPassWord){
		   $("#newPassWord").css({ "border-color": "red" });
	  	   $('#newPassWord_validate').empty();
	  	   $('#newPassWord_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
	  	   isOk = false ; 
	  }
	  
	  if(null == repeatPassWord || '' == repeatPassWord ){
		   $("#repeatPassWord").css({ "border-color": "red" });
	  	   $('#repeatPassWord_validate').empty();
	  	   $('#repeatPassWord_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
	  	   isOk = false ; 
	  }
	  //无法提交
	  if(!isOk){
		  return ;
	  }
	  
	  
	  if(newPassWord != repeatPassWord){
		  $('#error').text('两次密码不一致!请重新输入.');
		  return;
	  }
	  if($('#originalCipher').val() == newPassWord){
		  $('#error').text('新密码与原密码一致.');
		  return;
	  }
	  
	  $.ajax({
			type : 'POST',
			url : path + '/admin/updatePassWord.htm',
			dataType : 'json',
			data:{loginName:log_name,
				  originalCipher:$('#originalCipher').val(),
				  newPassword:$('#newPassWord').val()
				},
			success : function(data) {
				if (data.m_istatus == 1) {
					 $('#error').text(data.m_strMessage);
				} else  {
					 $('#error').text(data.m_strMessage);
				}
			}
		});
  }
  
  /**获的焦点事件*/
  function on_focus(id,msg){
  	var obj = $('#'+id).val();
  	if("" == obj || null==obj){
  		$('#'+id+"_validate").empty();
  		$('#'+id+"_validate").append('<p class="help-block" style="color: red;">'+msg+'</p>');
  	}
  }
  
  
  /**焦点移开*/
function on_blur(id){
		var obj = $('#'+id).val();
		if('phone' == id){
			if((/^1[3|4|5|8][0-9]\d{4,8}$/.test(obj)) && obj.length == 11){
				var  bool = ajax_vaildate(obj);
				if(!bool){
					$("#"+id).css({ "border-color": "red" });
					$('#'+id+"_validate").empty();
					$('#'+id+"_validate").append('<p class="help-block" style="color: red;">手机号码已存在.</p>');
				}else{
					$("#"+id).css({ "border-color": "#008000" });
					$('#'+id+"_validate").empty();
					$('#'+id+"_validate").append('<i style="color: #008000;margin-top: 10px;" class="glyphicon glyphicon-ok"></i>');
				}
			}else if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(obj)) || obj.length < 11){
				$("#"+id).css({ "border-color": "red" });
				$('#'+id+"_validate").empty();
				$('#'+id+"_validate").append('<p class="help-block" style="color: red;">请输入正确的手机号码.</p>');
			} 
			
		}else if(id == 't_platform' || id == 't_gold_proportions' || id == 't_vip_proportions'){
			on_validate_isNaN(id);
		}else if(null!=obj && ''!=obj){
			$("#"+id).css({ "border-color": "#008000" });
			$('#'+id+"_validate").empty();
			$('#'+id+"_validate").append('<i style="color: #008000;margin-top: 10px;" class="glyphicon glyphicon-ok"></i>');
		} else{
			$("#"+id).css({ "border-color": "red" });
			$('#'+id+"_validate").empty();
			$('#'+id+"_validate").append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		}
  }
  
  function isRealNum(val){
	    // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除
	    if(val === "" || val ==null){
	        return false;
	    }
	    if(!isNaN(val)){
	        return true;
	    }else{
	        return false;
	    }
}

 function on_validate_isNaN(id){
	 if(null == $('#'+id).val() || '' == $('#'+id).val()){
		 $("#"+id).css({ "border-color": "red" });
		 $('#'+id+"_validate").empty();
	  	 $('#'+id+"_validate").append('<p class="help-block" style="color: red;">不能为空!</p>');
	 }else if(!isRealNum($('#'+id).val())){
		 $("#"+id).css({ "border-color": "red" });
		 $('#'+id+"_validate").empty();
	  	 $('#'+id+"_validate").append('<p class="help-block" style="color: red;">请输入数字</p>');
	 }else  if($('#'+id).val()<=0){
		 $("#"+id).css({ "border-color": "red" });
		 $('#'+id+"_validate").empty();
	  	 $('#'+id+"_validate").append('<p class="help-block" style="color: red;">不能为负数或0</p>'); 
	 }else{
		 $("#"+id).css({ "border-color": "#008000" });
		 $('#'+id+"_validate").empty();
		 $('#'+id+"_validate").append('<i style="color: #008000;margin-top: 10px;" class="glyphicon glyphicon-ok"></i>');
	 }
 }
 
 
 /**验证手机号码是否存在*/
 function ajax_vaildate(phone){
	 
	 var isOK = null;
	 
 	$.ajax({
 		type : 'POST',
 		url :path+'/admin/getPhoneIsExist.htm',
 		data : {phone :phone},
 		dataType : 'json',
 		async: false,
 		success : function(data) {
 			console.log(data);
 			if (data.m_istatus == 1) {
 				isOK =  data.m_object;
 			} else  {
 				window.location.href = path + 'error.html';
 			}
 		}
 	});
 	
 	return isOK;
 }
 
 /** 验证参数是否可以提交 **/
 function vaildate_is_submit(obj){
	 
	 var isOk  = true ;
	 obj.forEach(v=>{  
		 if(v.type == 'string' && ($('#'+v.name).val() == null || $('#'+v.name).val() == '' )){
			 $("#"+v.name).css({ "border-color": "red" });
			 $('#'+v.name+"_validate").empty();
			 $('#'+v.name+"_validate").append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
			 isOk = false; 
		 }else if(v.type == 'int' && ($('#'+v.name).val() <= 0 || !isRealNum($('#'+v.name).val()) || (null == $('#'+v.name).val() || '' == $('#'+v.name).val()) )){
			 on_validate_isNaN(v.name);
			 isOk = false;
		 }else{
			$("#"+v.name).css({ "border-color": "#008000" });
			$('#'+v.name+"_validate").empty();
			$('#'+v.name+"_validate").append('<i style="color: #008000;margin-top: 10px;" class="glyphicon glyphicon-ok"></i>');
		 }
	});
	 return isOk;
 }