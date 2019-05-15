 
$(function(){
	load_table();
});


function load_table(){
	 var url = path+"admin/getPrivatePhotoList.htm" ;
	    $('#utable').bootstrapTable({
	        url: url,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams: function (params){
	        	return {
	        		pageSize: params.limit,
	        		page:params.pageNumber,
	        		search_name:$('#t_auditing').val(),
	        		fileType:$('#t_file_type').val()
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
	              		title:'用户昵称',
	              		align:'center',
	              		field:'t_nickName'
	              	},
	              	{
	              		title:'idcard',
	              		align:'center',
	              		field:'t_idcard'
	              	},
	              	{
	              		title:'动态标题',
	              		align:'center',
	              		field:'t_title',
	              	},
	              	{
	              		title:'文件类型',
	              		align:'center',
	              		field:'t_file_type',
	              		formatter:function(value,row,index){
	              			return value==0?'照片':'视频';
	              		}
	              	},
	              	{
	              		title:'是否私密',
	              		align:'center',
	              		field:'t_is_private',
	              		formatter:function(value,row,index){
	              			return value==0?'公开':'私密';
	              		}
	              	},
	              	{
	              		title:'文件封面',
	              		align:'center',
	              		field:'t_is_enable',
	              		formatter:function(value,row,index){
	              			
	              			var valueStr = JSON.stringify(row);
	              			var obj = '';
	              			if(row.t_file_type == 0){
	              				obj = "<img src='"+row.t_addres_url+"' style='cursor:pointer' title='点击预览' width='60' height='60' onclick='on_click_preview("+valueStr+")'>";
	              			}else{
	              				obj = "<img src='"+row.t_video_img+"' style='cursor:pointer' title='点击预览' width='60' height='60' onclick='on_click_preview("+valueStr+")'>";
	              			}
	              			
	              			return obj;
	              		}
	              	},
	              	{
	              		title:'收费金额(金币)',
	              		align:'center',
	              		field:'t_money',
	              	},
	              	{
	              		title:'相册状态',
	              		align:'center',
	              		field:'t_auditing_type',
	              		formatter:function(value){
	              			return value == 0 ?'未审核':value == 1? '已审核':'标记异常';
	              		}
	              	}
	        ]
	    });
}


/**点击预览*/
function on_click_preview(obj){
	$("#t_photo_id").val(obj.t_id);
	var html = '' ;
	if(obj.t_file_type == 0){
		html ='<img id="preview" src="'+obj.t_addres_url+'" alt="" width="100%" align="center"/>';
	}else{
		html = '<video width="100%" controls autoplay><source src="'+obj.t_addres_url+'"  type="video/mp4"  controls="controls"></video>';
	}
	$(".modal-body").empty();
	$(".modal-body").append(html);
	$("#myModal").modal('show');
}

/**点击禁用*/
function on_click_disable(id){
	var param = id;
	if(null == id || "" == id){
		param = $("#t_photo_id").val();
	}
	$.ajax({
		type : 'POST',
		url : path + '/admin/clickSetUpEisable.htm',
		data : {t_id : param},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				$("#utable").bootstrapTable('destroy');
				load_table();
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/**点击通过审核*/
function on_click_has_verified(id){
	var param = id;
	if(null == id || "" == id){
		param = $("#t_photo_id").val();
	}
	$.ajax({
		type : 'POST',
		url : path + 'admin/onclickHasVerified.htm',
		data : {t_id : param},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				$("#utable").bootstrapTable('destroy');
				load_table();
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}


function on_auditing_onchange(){
	$("#utable").bootstrapTable('destroy');
	load_table();
}