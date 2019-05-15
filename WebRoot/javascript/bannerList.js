$(function(){
    //生成用户数据
   var url = path+"/admin/getBannerList.htm" ;
    $('#utable').bootstrapTable({
        url: url,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        queryParamsType:'', 
        queryParams: function(params){
        	return {
        		pageSize: params.limit,
        		page:params.pageNumber,
        		Name:$('#search_name').val(),
        		Tel:$('#search_tel').val()
        	}
        }, 
        //【其它设置】
        locale:'zh-CN',//中文支持
        pagination: true,//是否开启分页（*）
        pageNumber:1,//初始化加载第一页，默认第一页
        pageSize: 5,//每页的记录行数（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        //【设置列】
        columns: [
              	{
              		title:'banner图',
              		align:'center',
              		field:'t_img_url',
              		formatter:function(value,row,index){
              			 return '<img src="'+value+'" style="cursor:pointer" title="点击预览" width="60" height="60" onclick="on_click_preview(\''+value+'\');">';
              		}

              	},
              	{
              		title:'链接地址',
              		align:'center',
              		field:'t_link_url',
              	},
              	{
              		title:'状态',
              		align:'center',
              		field:'t_is_enable',
              		formatter:function(value,row,index){
              			return value==0?'已启用':'已停用';
              		}
              	},
              	{
              		title:'类型',
              		align:'center',
              		field:'t_type',
              		formatter:function(value,row,index){
              			return value==0?'Android':'IOS';
              		}
              	},
              	{
              		title:'操作',
              		field:'',
              		align:'center',
              		formatter:function(value,row,index){
              			var res ='';
              			var obj = 
              			res = res+'<a href="javascript:on_click_open_update_model('+row.t_id+',\''+row.t_img_url+'\',\''+row.t_link_url+'\',\''+row.t_is_enable+'\');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">修改</a>&nbsp;&nbsp;';
              			if(row.t_is_enable ==0){
              				res = res +'<a href="javascript:on_click_operation('+row.t_id+',1);"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">停用</a>&nbsp;&nbsp;';
              			}else{
              				res = res +'<a href="javascript:on_click_operation('+row.t_id+',1);"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">启用</a>&nbsp;&nbsp;';
              			}
              			res = res +'<a href="javascript:on_click_operation('+row.t_id+',2);"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">删除</a>';
              			return res;
              		}
              	}
        ]
    });
    
    
});


//path+"/admin/uploadFile.htm?type=-1"
/**加载图片控件*/
function on_load_file_input(preview){

	$("#file-1").fileinput('refresh',{
		language: 'zh', //设置语言
	    uploadUrl: path+'/admin/uploadFile.htm?type=-1', // server upload action
	    uploadAsync: true,
		allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
	    maxFileCount: 1,
	    dropZoneEnabled: true,
	    showBrowse: false,
	    showUpload : false, // 是否显示上传按钮
	    autoReplace : true,
	    showRemove:true,
	}).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data) {
        if(data.response.m_istatus == 1)
        {
           console.log(data.response)
           $("#img_url").val(data.response.m_object);
        }
    });
	
}


/**点击预览*/
function on_click_preview(url){
	$("#hand_img").attr("src",url);
	$("#img_preview").modal('show');
}

/**
 * 新增或者修改
 * @param id
 * @param nickName
 */
function on_click_open_update_model(id,img_url,t_link_url,t_is_enable){
	
	if(id == null || id ==0){
		$("#t_id").val("");
		$("#img_url").val("");
		$("#t_link_url").val("");
	}else{
		$("#t_id").val(id);
		$("#img_url").val(img_url);
		$("#t_link_url").val(t_link_url);
		$("#t_is_enable").val(t_is_enable);
	}
	
	on_load_file_input();
//    initFileInput("file-1",path+"/admin/uploadFile.htm?type=-1",img);
	$("#myModal").modal('show');
}
/**
 * 点击操作
 */
function on_click_operation(id,type){
	$("#del_id").val(id);
	$("#type").val(type);
	$("#myDel").modal('show');
}

/**
 * 点击启用或者禁用用户
 */
function on_click_change_res(){
	
	var id =  $("#del_id").val();
	var type = $("#type").val();
	
	var url ;
	if(type == 1){
		url =  path + '/admin/bannerEnableOrDisable.htm';
	}else{
		url =  path + '/admin/delBannerById.htm';
	}
	
	$.ajax({
		type : 'POST',
		url :url,
		data : {t_id :id},
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


function on_click_submit(){
	
	var isOk = true;
	if(null == $("#img_url").val() || '' == $("#img_url").val()){
		$("#file-1").css({ "border-color": "red" });
		$('#file-1_validate').empty();
		$('#file-1_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false ; 
	}
	
	if(null == $("#t_link_url").val() || '' == $("#t_link_url").val()){
		$("#t_link_url").css({ "border-color": "red" });
		$('#t_link_url_validate').empty();
		$('#t_link_url_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false ; 
	}
	
	if(!isOk){
		return ;
	}
	
	$.ajax({
		type : 'POST',
		url :path+'/admin/addOrUpdateBanner.htm',
		data : {
			t_id :$("#t_id").val(),
			   t_img_url :$("#img_url").val(),
			   t_link_url:$("#t_link_url").val(),
			   t_is_enable:$("#t_is_enable").val(),
			   t_type:$('#t_type').val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + 'error.html';
			}
		}
	});
}

function on_change(id){
	$('#'+id+"_validate").empty();
	$('#'+id+"_validate").append('<p class="help-block" style="color: red;">*</p>');
}




