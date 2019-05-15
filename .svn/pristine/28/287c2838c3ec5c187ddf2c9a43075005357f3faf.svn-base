$(function(){
	ajax_load_table();
});

function ajax_load_table(){
	 //生成用户数据
	   var url = path+"/admin/getVersionList.htm" ;
	    $('#utable').bootstrapTable({
	        url: url,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams: function (params){
	        	return {
	        		pageSize: params.limit,
	        		page:params.pageNumber,
	        		condition:$('#condition').val()
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
						title:'链接地址',
						align:'center',
						field:'t_download_url',
					},
					{
						title:'设备类型',
						align:'center',
						field:'t_version_type'
					},
					{
						title:'版本号',
						align:'center',
						field:'t_version'
					},
	              	{
	              		title:'是否最新',
	              		align:'center',
	              		field:'t_is_new',
	              		formatter:function(value,row,index){
	              			 return value == 0?'否':'是';
	              		}
				    },
				    {
				    	title:'版本描述',
				    	align:'center',
				    	field:'t_version_depict'
				    },
	              	{
	              		title:'操作',
	              		field:'',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			return '<a href="javascript:on_click_operation('+row.t_id+');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">删除</a>';
	              		}
	              	}
	        ]
	    });
}

/**加载图片控件*/
function on_load_file_input(id){
	$("#"+id).fileinput('refresh',{
		language: 'zh', //设置语言
	    uploadUrl: path+"/admin/uploadFile.htm?type=-1", // server upload action
	    uploadAsync: true,
		allowedFileExtensions : [ 'apk'],
	    maxFileCount: 1,
	    showBrowse: false,
	    showUpload : false, // 是否显示上传按钮
	    autoReplace : true,
	    browseOnZoneClick: true
	}).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data) {
        if(data.response.m_istatus == 1)
        {
           console.log(data.response)
           $("#t_version_url").val(data.response.m_object);
        }
    });
	
}
/**
 * 新增或者修改
 * @param id
 * @param nickName
 */
function on_click_open_update_model(){
	
	$("#t_version_url").val(''),
	$("#t_is_new").val(0),
	$("#t_version").val('')
	on_load_file_input('file-1');
	$("#myModal").modal('show');
}
/**
 * 点击操作
 */
function on_click_operation(id){
	$("#del_id").val(id);
	$("#myDel").modal('show');
}

/**
 * 删除版本
 */
function on_click_change_res(){
	
	var id =  $("#del_id").val();
	$.ajax({
		type : 'POST',
		url :path+'/admin/delVersion.htm',
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

/**提交*/
function on_click_submit(){
	var isOk = true ;  
	 
	if(null == $("#t_version").val() || '' == $("#t_version").val()){
		$("#t_version").css({ "border-color": "red" });
		$('#t_version_validate').empty();
		$('#t_version_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
	    isOk  = false ; 
	}
	if(null == $("#t_version_depict").val() || '' == $("#t_version_depict").val()){
		$("#t_version_depict").css({ "border-color": "red" });
		$('#t_version_depict_validate').empty();
		$('#t_version_depict_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
	    isOk  = false ; 
	}
	if(!isOk){
		 return ; 
	}
	
	$.ajax({
		type : 'POST',
		url :path+'/admin/addVersion.htm',
		data : {
			t_download_url:$("#t_version_url").val(),
			t_is_new:$("#t_is_new").val(),
			t_version:$("#t_version").val(),
			t_version_depict:$('#t_version_depict').val(),
			t_version_type:$('#t_version_type').val(),
			t_onload_path:$('#t_onload_path').val()
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








