$(function(){
	$("#beginTime").datepicker();
	$("#endTime").datepicker();
	$("#anthor_begin_time").datepicker();
	$("#anthor_end_time").datepicker();
	ajax_load_table();
	 
});

//加载表格
function ajax_load_table(){
	//生成用户数据
    $('#utable').bootstrapTable({
        url: path+"admin/getUserList.htm",
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        queryParamsType:'', 
        queryParams: function(params){
        	return {
        		pageSize: params.limit,
        		page:params.pageNumber,
        		t_sex:$("#t_sex").val(),
        		t_role:$("#t_role").val(),
        		condition:$("#condition").val(),
        		beginTime:$("#beginTime").val(),
        		endTime:$("#endTime").val()
        	}
        }, 
        //【其它设置】
        locale:'zh-CN',//中文支持
        pagination: true,//是否开启分页（*）
        pageNumber:1,//初始化加载第一页，默认第一页
        pageSize: 10,//每页的记录行数（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        //【设置列】
        columns: [
                  {
					title:'IdCard号',
					field:'t_idcard',
					align:'center'
				},{
              		title:'个人信息',
              		field:'t_nickName',
              		align:'center',
              		formatter:function(value,row,index){
              			var obj = "<a href='javascript:on_click_open_user_model("+row.t_id+");'>昵称：[ "+value+" ]</a> </br>";
              			if(row.t_sex==0){
              				obj+="<span>性别：女</span>&nbsp;&nbsp;";
              			}else{
              				obj+="<span>性别：男</span>&nbsp;&nbsp;";
              			}
              			if(row.t_age == null){
              				obj+="<span>年龄：未设置</span></br>";
              			}else{
              				obj = obj + "<span>年龄："+row.t_age+"</span></br>";
              			}
              			if(row.t_phone == null){
              				obj+="<span>电话：未设置</span>";
              			}else{
              				obj+="<span>电话："+row.t_phone+"</span>";
              			}
              			if(row.t_role == 1){
              				obj+='</br><a href="javascript:on_click_totalTime('+row.t_id+')">在线时长:'+row.totalTime+'</a>';
              			}
              			return obj;
              		}
              	},
              	{
              		title:'头像',
              		align:'center',
              		field:'t_handImg',
              		formatter:function(value,row,index){
              			 return '<img src="'+value+'" style="cursor:pointer" title="点击预览" width="60" height="60" onclick="on_click_preview(\''+value+'\');">';
              		}

              	},
              	{
              		title:'微信号',
              		align:'center',
              		field:'t_weixin'
              	},
              	{
              		title:'角色',
              		field:'t_role',
              		align:'center',
              		width:100,
              		formatter:function(value,row){
              			var obj = '';
              			if(value == 0){
              				if(row.t_is_vip == 0){
              					return 'VIP用户';
              				}else{
              					return '普通用户';
              				}
              			
              			}else if(value == 1){
              				obj +="主播用户</br>";
              				if(row.t_is_nominate == 1){
              					obj += '<img alt="推荐主播" src="../images/f92b9fe7e2a958e89f051d54bc25e667.png" width="50%">';
              				}
              				if(row.t_is_free == 1){
              					obj += '<img alt="免费主播" src="../images/free.png" width="40%">';
              				}
              			}else {
              				obj+='代理用户';
              			}
              			return obj;
              		}
              	},
              	{
              		title:'推荐人',
              		field:'refeName',
              		align:'center'
              	},
              	{
              		title:'注册时间',
              		field:'t_create_time',
              		align:'center'
              	},
              	{
              		title:'状态',
              		field:'t_disable',
              		align:'center',
              		formatter:function(value,row,index){
              			var res = '';
              			if(value == 0){
              				res = '正常->';
              				if(row.t_onLine==0){
              					res+='在线';
              				}else{
              					res+='离线';
              				}
              			}
              			if(value == 1){
              				res = '已封号';
              			}
              			if(value == 2){
              				res = '已禁用';
              			}
              			return res;
              		}
              	},
              	{
              		title:'操作',
              		field:'',
              		align:'center',
              		formatter:function(value,row,index){
              			
              			var res ='<a class="btn btn-default" href="javascript:on_click_cover_modal('+row.t_id+')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">封面</a>&nbsp;&nbsp;';
              			 res = res +'<a class="btn btn-default" href="javascript:on_update_head('+row.t_id+')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">更新头像</a>&nbsp;&nbsp;';
              			 res = res +'<a class="btn btn-default" href="jumpPhotoList.htm?userId='+row.t_id+'"; style="height: 25px;line-height: 0.5;background-color: #87CEFA;">相册</a>&nbsp;&nbsp;';
              			 if(row.t_role == 1){
              				 res = res +'<a class="btn btn-default" href="javascript:on_cover_img('+row.t_id+')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">封面上传</a>&nbsp;&nbsp;';
              				 res = res +'<a class="btn btn-default" href="javascript:on_dynamic_img('+row.t_id+')"; style="height: 25px;line-height: 0.5;background-color: #87CEFA;">相冊上传</a>&nbsp;&nbsp;';
              				 res = res +'<a class="btn btn-default" href="javascript:on_click_setUp_charge('+row.t_id+')"; style="height: 25px;line-height: 0.5;background-color: #87CEFA;">收费设置</a>&nbsp;&nbsp;';
              				 res = res +'<a class="btn btn-default" href="javascript:show_lable_html('+row.t_id+')"; style="height: 25px;line-height: 0.5;background-color: #87CEFA;">标签</a>&nbsp;&nbsp;';
              				 res = res +'<a class="btn btn-default" href="javascript:on_show_criticism('+row.t_id+')"; style="height: 25px;line-height: 0.5;background-color: #87CEFA;">评价</a>&nbsp;&nbsp;';
              				 res = res +'<a class="btn btn-default" href="javascript:on_click_show_nominate('+row.t_id+',\''+row.t_nickName+'\')"; style="height: 25px;line-height: 0.5;background-color: #87CEFA;">推荐</a>&nbsp;&nbsp;';
              				 res = res +'<a class="btn btn-default" href="javascript:on_click_show_freeAnchor('+row.t_id+',\''+row.t_nickName+'\')"; style="height: 25px;line-height: 0.5;background-color: #87CEFA;">免费</a>&nbsp;&nbsp;</br></br>';
              				 res = res +'<a class="btn btn-default" href="javascript:on_click_setOnLine('+row.t_id+')"; style="height: 25px;line-height: 0.5;background-color: #87CEFA;">'+(null == row.t_online_setup?'设为在线':0 == row.t_online_setup?'设为在线':'设为离线')+'</a>&nbsp;&nbsp;';
              			 }
              			 //财务相关弹窗
              			 res = res +'<a class="btn btn-default" href="javascript:on_click_finance_popup('+row.t_id+');" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">财务</a>&nbsp;&nbsp;';
              			 var nickName = "'"+row.t_nickName+"'";
              			if(row.t_disable ==0){
              				res = res +'<a href="javascript:on_click_open_update_model('+row.t_id+','+nickName+');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">封号</a>&nbsp;&nbsp;';
              				res = res +'<a href="javascript:on_click_operation('+row.t_id+',2)" class="btn btn-default" style="height: 25px;line-height: 0.5;color:red;background-color: #87CEFA;">禁用</a>&nbsp;&nbsp;';
              			}
              			if(row.t_disable ==1){
              				res =res + '<a href="javascript:on_click_unlock('+row.t_id+');" class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">解封</a>&nbsp;&nbsp;';
              					res = res +'<a href="javascript:on_click_operation('+row.t_id+',2)" class="btn btn-default" style="height: 25px;line-height: 0.5;color:red;background-color: #87CEFA;">禁用</a>&nbsp;&nbsp;';
              			}
              			//赠送金币弹窗
              			if(row.t_role == 0){
              				res = res + '<a href="javascript:on_click_give_modal('+nickName+','+row.t_id+');" class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">赠送金币</a>&nbsp;&nbsp;';
              			}
              			if(row.t_disable ==2){
              				res = res +'<a href="javascript:on_click_operation('+row.t_id+',0)" class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">启用</a>&nbsp;&nbsp;';
              					res = res +'<a href="javascript:on_click_open_update_model('+row.t_id+','+nickName+' );" class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">封号</a>&nbsp;&nbsp;';
              			}
              			//指定推送弹窗
              			res = res + '<a href="javascript:on_click_show_push('+nickName+','+row.t_id+');" class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">推送</a>&nbsp;&nbsp;';
              			res = res + '<a href="javascript:setRefereeUser('+row.t_id+');" class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">推广绑定</a>';
              			return res;
              		}
              	}
        ]
    });
}
/**点击预览头像*/
function on_click_preview(url){
	$("#hand_img").attr("src",url);
	$("#img_preview").modal('show');
}

/**
 * 点击上传图片
 */
function on_update_head(id){
	
	//头像上传
	$('#on_upload_img').modal('show');
	$("#file-1").fileinput('refresh',{
	    language : 'zh', // 设置语言
		uploadUrl : path+"admin/uploadFile.htm?type=1&t_id="+id,
		showUpload : false, // 是否显示上传按钮
		showRemove : true,
		showPreview : true,
		dropZoneEnabled : true,
		showCaption : true,// 是否显示标题
		autoReplace:true,
		maxFileCount:1,
		allowedPreviewTypes : [ 'image' ],
		allowedFileTypes : [ 'image' ],
		allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
	}).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data) {
        if(data.response.m_istatus == 1)
        {
           console.log(data.response)
           $("#hand_img_url").val(data.response.m_object);
        }
    });
}


function on_cover_img(id){
	$('#t_user_id').val(id);
	$.ajax({
		type : 'POST',
		url : path + 'admin/getCoverList.htm',
		data : {t_user_id : id},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				var initialPreviewData=new Array();
				var initialPreviewConfig = new Array();
				$.each(data.m_object,function(index,value){
					initialPreviewData[index] = '<img src="'+this.t_img_url+'" class="file-preview-image">';
					initialPreviewConfig[index] = {caption:this.t_first==0?"主封面":"封面",url:path+'admin/delCoverData.htm?t_id='+this.t_id};
				});
			    console.log(initialPreviewConfig);
				$("#file-2").fileinput('refresh',{
				        theme: 'fa',
				        uploadUrl: path+"/admin/uploadFile.htm?type=2&t_id="+id, // you must set a valid URL here else you will get an error
				        allowedFileExtensions: ['jpg', 'png', 'gif'],
				        //overwriteInitial: false,
				        //maxFileSize: 1000,
				        maxFilesNum: 4,
				        //allowedFileTypes: ['image', 'video', 'flash'],
						showUpload: false,
						fileType: "any",
				        overwriteInitial: false,
				        initialPreviewAsData: true,
				        initialPreview: initialPreviewData,
				        initialPreviewConfig: initialPreviewConfig,
				}).on("filebatchselected", function(event, files) {
			        $(this).fileinput("upload");
			    }).on('filedeleted', function(event, data) {
				    console.log('Key = ' + data);
				}).on("fileuploaded", function(event, data) {
			        if(data.response.m_istatus == 1)
			        {
			           console.log(data.response)
			           var res =  $("#cover_img_url").val();
			           $("#cover_img_url").val(data.response.m_object);
			          
			        }
			    });;
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
	 $('#on_cover_img').modal('show');
	 
}


function on_dynamic_img(id){
	//头像上传
	$('#t_album_user_id').val(id);
	$('#t_title').val('');
	$('#t_money').val('');
	$('#on_dynamic_img').modal('show');
	$("#file-3").fileinput('refresh',{
	    language : 'zh', // 设置语言
		uploadUrl : path+"admin/uploadFile.htm?type=-1",
		showUpload : false, // 是否显示上传按钮
		showRemove : true,
		showPreview : true,
		dropZoneEnabled : true,
		showCaption : true,// 是否显示标题
		autoReplace:true,
		maxFileCount:1,
		allowedFileExtensions : [ 'jpg', 'png', 'gif','mp4'],
	}).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data) {
        if(data.response.m_istatus == 1)
        {
           console.log(data.response)
           var obj = data.response.m_object;
           if(undefined!=obj.imgPath){
        	   $("#dynamic_cover_img_url").val(obj.imgPath);
        	   $("#dynamic_file_url").val(obj.filePath);
           }else{
        	   $("#dynamic_file_url").val(obj);
           }
        }
    });
	
}


function on_dynamic_submit(){
	if('' == $('#dynamic_file_url').val() || null == $('#dynamic_file_url').val()){
		alert('文件正在上传!请稍后.');
		return;
	}
	var goid = $('#t_money').val() == null ? 0: $('#t_money').val() == ''? 0:$('#t_money').val();
	$.ajax({
		type : 'POST',
		url : path + 'admin/savePhoto.htm',
		data : { userId : $('#t_album_user_id').val(),
			     t_title : $('#t_title').val(),
			     video_img: $('#dynamic_cover_img_url').val(),
			     url:$('#dynamic_file_url').val(),
			     gold:goid
			   },
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#on_dynamic_img").modal('hide');
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
	
}

function on_cover_submit(){
	if('' == $('#cover_img_url').val() || null == $('#cover_img_url').val()){
		alert('封面正在上传!请稍后.');
		return;
	}
	$("#on_cover_img").modal('hide');
	$("#utable").bootstrapTable('refreshOptions',{page : 1});
}
/**
 * 点击刷新列表
 */
function on_head_submit(img_id){
	if('' == $('#hand_img_url').val() || null == $('#hand_img_url').val()){
		alert('头像正在上传请稍后.');
		return;
	}
	$("#on_upload_img").modal('hide');
	$("#utable").bootstrapTable('refreshOptions',{page : 1});
}


function on_click_setUp_charge(id){
	
	$.ajax({
		type : 'POST',
		url : path + 'admin/getChargeSetUp.htm',
		data : {userId : id	},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				console.log(data.m_object);
				if(null != data.m_object){
					$('#t_setup_charge_user_id').val(id);
					$('#t_video_gold').val(data.m_object.t_video_gold);
					$('#t_text_gold').val(data.m_object.t_text_gold);
					$('#t_phone_gold').val(data.m_object.t_phone_gold);
					$('#t_weixin_gold').val(data.m_object.t_weixin_gold);
					$("#on_setup_charge").modal('show');
				}
				
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
	
}

function on_setup_submit(){
	$.ajax({
		type : 'POST',
		url : path + 'admin/replaceSetUp.htm',
		data : {userId : $('#t_setup_charge_user_id').val(),
			    t_video_gold:$('#t_video_gold').val(),
			    t_text_gold:$('#t_text_gold').val(),
			    t_phone_gold:$('#t_phone_gold').val(),
			    t_weixin_gold:$('#t_weixin_gold').val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#on_setup_charge").modal('hide');
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}
 

/**点击封面预览 id :用户编号*/
function on_click_cover_modal(id){
	
	$.ajax({
		type : 'POST',
		url : path + 'admin/getUserCoverExamineList.htm',
		data : {
			t_user_id : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				var  html = '' ;
				$.each(data.m_object,function(index,value){
					 html = html+'<div class="show-reel">';
					//第二次循环
					$.each(this,function(index,value){
						 html = html+'<div class="agile-gallery-grid" style="float: left;margin-left: 8px;">';
						 html = html+'<div class="agile-gallery" style="width: 500px;">';
						 html = html+'<img src="'+this.t_img_url+'" width="100%"  />';
						 if(this.t_first != 0){
							 html = html+'<div class="agileits-caption">';
							 html = html+'<a href="javascript:on_click_first('+this.t_id+','+id+');" style="float: right;margin-right: 10%;margin-top: 5.5%;font-size: 1em;color: #ffffff">设为主封面</a>';
							 html = html+'</div>';
						 }
						 html = html+'</div>';
						 html = html+'</div>';
					});
					html = html+'<div class="clearfix"></div></div>';
				});
				
				$("#cover_modal_div").text("");
				$("#cover_modal_div").append(html);
				$("#cover_modal").modal('show');
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
	
	
}

/**点击设置为第一张封面*/
function on_click_first(id,userId){
	$.ajax({
		type : 'POST',
		url : path + 'admin/setUpFirst.htm',
		data : {
			t_id : id,
			t_user_id:userId
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				on_click_cover_modal(userId);
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
	
	
}

 
/**
 * 点击封号
 * @param id
 * @param nickName
 */
function on_click_open_update_model(id,nickName){
	$("#t_id").val(id);
	$("#nick_name").val(nickName);
	$("#myModal").modal('show');
}
/**
 * 点击操作
 */
function on_click_operation(id,state){
	$("#del_id").val(id);
	$("#state").val(state);
	$("#myDel").modal('show');
}

/**
 * 点击启用或者禁用用户
 */
function on_click_change_res(){
	$.ajax({
		type : 'POST',
		url : path + 'admin/enableOrDisable.htm',
		data : {
			t_id : $("#del_id").val(),
			state :$("#state").val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myDel").modal('hide');
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/**
 * 点击启用或者禁用用户
 */
function on_click_freeze_ones(){
	$.ajax({
		type : 'POST',
		url : path + 'admin/freezeOnesUser.htm',
		data : {
			t_id : $("#t_id").val(),
			freeze_time :$("#freeze_time").val(),
			pushConnent:$('#pushConnent').val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/**
 * 手动解封
 * @param id
 */
function on_click_unlock(id){
	$.ajax({
		type : 'POST',
		url : path + 'admin/unlock.htm',
		data : {
			t_id : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}


/**
 * 点击弹出用户详情
 * @param id
 * @param nickName
 */
function on_click_open_user_model(params){
	$.ajax({
		type : 'POST',
		url : path + 'admin/getUserById.htm',
		data : {t_id : params},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#t_user_id").val(params);
				$("#t_nickName").val(null != data.m_object.t_nickName?data.m_object.t_nickName:'聊友:'+data.m_object.t_phone.substring(data.m_object.t_phone.length-4,data.m_object.t_phone.length));
				$("#t_phone").val(data.m_object.t_phone);
				$("#t_modal_sex").val(data.m_object.t_sex);
				$("#t_age").val(data.m_object.t_age);
				$("#t_height").val(data.m_object.t_height);
				$("#t_weight").val(data.m_object.t_weight);
				$("#t_constellation").val(data.m_object.t_constellation);
				$("#t_city").val(data.m_object.t_city);
				$("#t_vocation").val(data.m_object.t_vocation);
				$("#t_user_role").val(data.m_object.t_role);
				$("#t_synopsis").val(data.m_object.t_synopsis);
				$("#t_autograph").val(data.m_object.t_autograph);
				$("#userModal").modal('show');
			}else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}
/** 点击修改用户信息*/
function on_click_up_user(){
	$.ajax({
		type : 'POST',
		url : path + 'admin/upUserData.htm',
		data : {
			t_id : $("#t_user_id").val(),
			t_nickName : $("#t_nickName").val(),
			t_phone : $("#t_phone").val(),
			t_modal_sex : $("#t_modal_sex").val(),
			t_age : $("#t_age").val(),
			t_height : $("#t_height").val(),
			t_weight : $("#t_weight").val(),
			t_constellation : $("#t_constellation").val(),
			t_city : $("#t_city").val(),
			t_vocation : $("#t_vocation").val(),
			t_synopsis : $("#t_synopsis").val(),
			t_autograph : $("#t_autograph").val(),
			t_user_role : $("#t_user_role").val()
			},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#userModal").modal('hide');
				on_click_search();//重新加载表格
			}else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

//性别改变
function on_sex_onchange(){
	$("#utable").bootstrapTable('destroy');
	ajax_load_table();
}
//角色改变
function on_role_onchange(){
	$("#utable").bootstrapTable('destroy');
	ajax_load_table();
}
//条件搜索
function on_click_search(){
	$("#utable").bootstrapTable('destroy');
	ajax_load_table();
}

/**显示标签*/
function show_lable_html(id){
	$.ajax({
		type : 'POST',
		url : path + 'admin/getLableList.htm',
		data : {userId : id},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				var obj = '<ul>';
				$.each(data.m_object,function(index,value){
					obj+='<li style="display:inline;width:20px;">'
					obj = obj + '<label><input type="checkbox" name="lable" value="'+this.t_id+'" ';
					
					if(this.checked == true){
						obj = obj + ' checked="checked" ';
					}
					obj = obj + 'onchange="on_change_lable(this,2,\'lable\');"> '+this.t_label_name+'</label>'
				    if(this.t_label_name.length ==2){
				    	obj = obj + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				    }else if(this.t_label_name.length ==3){
				    	obj = obj + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				    }else{
				    	obj = obj + '&nbsp;';
				    }
					
					obj+='</li>';
				});
				obj+='</ul>';
				$(".checkbox-inline1").text("");
				$(".checkbox-inline1").append(obj);
			}else  {
				window.location.href = path + '/error.html';
			}
		}
	});
	$('#lable_user_id').val(id);
	$('#user_lable').modal('show');
}

function on_click_lable_submit(){
	var obj = '';
	$("input[name='lable']:checked").each(function(){ 
       obj+=$(this).val()+',';
	});
	
	$.ajax({
		type : 'POST',
		url : path + 'admin/addLabel.htm',
		data : {userId : $('#lable_user_id').val(),
			lables : obj},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$('#user_lable').modal('hide');
			}else  {
				window.location.href = path + '/error.html';
			}
		}
	});

}

function on_change_lable(obj,number,name){
	
	if($(obj).is(':checked') && $("input[name='"+name+"']:checked").length > number){
		alert("无法选择"+number+"个以上的标签.");
		$(obj ).attr("checked",false);
	}
}
/** 加载评论数据*/
function on_show_criticism(id){
	$.ajax({
		type : 'POST',
		url : path + 'admin/getLableList.htm',
		data : {userId : id},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				var obj = '';
				$.each(data.m_object,function(index,value){
					obj = obj + '<label><input type="checkbox" name="content" value="'+this.t_id+'" ';
					obj = obj + 'onchange="on_change_lable(this,4,\'content\');"> '+this.t_label_name+'</label>'
					if(this.t_label_name.length ==2){
				    	obj = obj + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				    }else if(this.t_label_name.length ==3){
				    	obj = obj + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				    }else{
				    	obj = obj + '&nbsp;';
				    }
				});
				$(".checkbox-criticism").text("");
				$(".checkbox-criticism").append(obj);
			}else  {
				window.location.href = path + '/error.html';
			}
		}
	});
	$.ajax({
		type : 'POST',
		url : path + 'admin/getRandomUserList.htm',
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				var obj = '';
				$.each(data.m_object,function(index,value){
					obj = obj + '<option value="'+this.t_id+'">'+this.t_nickName+'</option>';
				});
				$("#t_content_user").empty();
				$("#t_content_user").append(obj);
			}else  {
				window.location.href = path + '/error.html';
			}
		}
	});
	
	$('#t_anchor_id').val(id);
	$("#t_content_user").val(0);
	$('#user_criticism').modal('show');
}

/**添加评价*/
function on_click_content_submit(){
	
	var lables =  '';
	$("input[name='content']:checked").each(function(){ 
		lables+=$(this).val()+',';
	});
	$.ajax({
		type : 'POST',
		url : path + 'admin/addUserContent.htm',
		data : {
			     t_anchor_id : $('#t_anchor_id').val(),
			     t_content_user:$("#t_content_user").val(),
			     t_score: $('#t_score').val(),
			     lables : lables},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$('#user_criticism').modal('hide');
			}else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}
/**
 * 推送数据
 * @param nick
 * @param id
 */
function on_click_show_push(nick,id){
	$('#push_user_id').val(id);
	$('#push_nick').val(nick);
	$('#pushModal').modal('show');
}

/**
 * 给用户发送消息
 * 
 */
function on_click_push_msg(){
	$.ajax({
		type : 'POST',
		url : path + 'admin/sendPushMsg.htm',
		data : {
			     t_id : $('#push_user_id').val(),
			     push_connent:$("#push_connent").val()
			   },
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$('#pushModal').modal('hide');
			}else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/**
 * 打开赠送金币弹窗
 * */
function on_click_give_modal(nick , id){
	$('#give_user_id').val(id);
	$('#give_nick').val(nick);
	$('#giveModal').modal('show');
}

/**赠送金币提交方法**/
function on_click_give_submit(){
	if('' == $('#give_gold').val() || null == $('#give_gold').val()){
		$("#give_gold").css({ "border-color": "red" });
		$('#give_gold_validate').empty();
		$('#give_gold_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
		return;
	}
	
	$.ajax({
		type : 'POST',
		url : path + 'admin/giveUserGold.htm',
		data : {
			     t_id : $('#give_user_id').val(),
			     goid:$("#give_gold").val()
			   },
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$('#giveModal').modal('hide');
			}else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/**推荐弹窗显示*/
function on_click_show_nominate(id,nickName){
	//获取该主播用户当前设置
	$.ajax({
		url:path + 'admin/getNominate.htm',
		data:{userId:id},
		dataType:'json',
		success:function(data){
			if(data.m_istatus == 1){
				$('#nominate_user_id').val(id);
				$('#t_nick_name').val(nickName);
				$('#t_is_nominate').val(null == data.m_object?0: data.m_object.t_id);
				$('#t_sort').val(null == data.m_object?99: data.m_object.t_sort);
				$('#nominateModal').modal('show');
			}
		}
	});
}

function on_click_nominate_submit(){
	
	$.ajax({
		url:path + 'admin/saveOrUpdateNominate.htm',
		data:{
			userId:$('#nominate_user_id').val(),
			t_is_nominate:$('#t_is_nominate').val(),
			t_sort:$('#t_sort').val()
			},
		dataType:'json',
		success:function(data){
			if(data.m_istatus == 1){
				$('#nominateModal').modal('hide');
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			}
		}
	});
}


/**免费弹窗显示*/
function on_click_show_freeAnchor(id,nickName){
	//获取该主播用户当前设置
	$.ajax({
		url:path + 'admin/getFreeAnchor.htm',
		data:{userId:id},
		dataType:'json',
		success:function(data){
			if(data.m_istatus == 1){
				$('#t_free_user_id').val(id);
				$('#t_free_nick_name').val(nickName);
				$('#t_is_free').val(null == data.m_object?0: data.m_object.t_id);
				$('#freeAnchorModal').modal('show');
			}
		}
	});
}

function on_click_free_submit(){
	
	$.ajax({
		url:path + 'admin/alterationFreeAnchor.htm',
		data:{
			userId:$('#t_free_user_id').val(),
			t_is_free:$('#t_is_free').val()
			},
		dataType:'json',
		success:function(data){
			if(data.m_istatus == 1){
				$('#freeAnchorModal').modal('hide');
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			}
		}
	});
}

/**查询主播在线记录*/
function on_click_totalTime(id){
	$('#anthor_id').val(id);
	$('#anthor_online_time').modal('show');
	$("#anthorTable").bootstrapTable('destroy');
	loadTable1(id);
}

/**加载主播在线明细*/
function loadTable1(id){
	 $('#anthorTable').bootstrapTable({
	        url: path+ 'admin/getAnchorOnlineTime.htm?userId='+id,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams:function (params){
	        	return {
	                pageSize: params.limit, // 每页要显示的数据条数
	                page: params.pageNumber, // 每页显示数据的开始行号 
	                beginTime:$('#anthor_begin_time').val(),
	                endTime:$('#anthor_end_time').val()
	            }
	        }, 
	        //【其它设置】
	        locale:'zh-CN',//中文支持
	        pagination: true,//是否开启分页（*）
	        pageNumber:1,//初始化加载第一页，默认第一页
	        pageSize: 10,//每页的记录行数（*）
	        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
	        //【设置列】
	        columns: [
	              	{
	              		title:'登入时间',
	              		align:'center',
	              		field:'login_time'
	              	},
	              	{
	              		title:'登出时间',
	              		align:'center',
	              		field:'logout_time'
	              	},
	              	{
	              		title:'在线时长',
	              		align:'center',
	              		field:'t_duration'
	              	}
	        ]
	    });
}


/**验证两个日期的大小*/
function CompareDate(d1,d2){
  return ((new Date(d1.replace(/-/g,"\/"))) < (new Date(d2.replace(/-/g,"\/"))));
}

/** 点击搜索*/
function search_anthor_time(){
	
   var  beginTime = $('#anthor_begin_time').val();
   var  endTime = $('#anthor_end_time').val();
   
   if(CompareDate(beginTime,endTime)){
	   $("#anthorTable").bootstrapTable('destroy');
	   loadTable($('#anthor_id').val());
   }
   
}

function on_click_setOnLine(id){
	$.ajax({
		url:path + 'admin/setUserOnLine.htm',
		data:{userId:id},
		dataType:'json',
		success:function(data){
			if(data.m_istatus == 1){
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			}
		}
	});
}
/**重设推广人**/
function setRefereeUser(id){
	$('#t_referee_user_id').val(id);
	$('#refereeModal').modal('show')
}

function on_click_upRefereeUser(){
	if(vaildate_is_submit([{'name':'t_referee_id','type':'int'}])){
		$.ajax({
			url:path + 'admin/setRefereeUser.htm',
			data:{t_id:$('#t_referee_user_id').val(),
				t_referee_id:$('#t_referee_id').val()},
			dataType:'json',
			success:function(data){
				if(data.m_istatus == 1){
					$('#refereeModal').modal('hide');
					$("#utable").bootstrapTable('refreshOptions',{page : 1});
				}
			}
		});
	}
}


