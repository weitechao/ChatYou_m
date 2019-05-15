$(function(){
	ajax_load_table();
});

function ajax_load_table(){
	 //生成用户数据
	   var url = path+"/admin/getGiftList.htm" ;
	    $('#utable').bootstrapTable({
	        url: url,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams: function(params){
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
	        pageSize: 10,//每页的记录行数（*）
	        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
	        //【设置列】
	        columns: [
	                  {
						title:'礼物名称',
						align:'center',
						field:'t_gift_name',
					},
	              	{
	              		title:'静态图',
	              		align:'center',
	              		field:'t_gift_still_url',
	              		formatter:function(value,row,index){
	              			 return '<img src="'+value+'" style="cursor:pointer" title="点击预览" width="60" height="60" onclick="on_click_preview(\''+value+'\');">';
	              		}

	              	},
	              	{
	              		title:'动态图',
	              		align:'center',
	              		field:'t_gift_gif_url',
	              		formatter:function(value,row,index){
	              			 return '<img src="'+value+'" style="cursor:pointer" title="点击预览" width="60" height="60" onclick="on_click_preview(\''+value+'\');">';
	              		}

	              	},
	              	{
	              		title:'单价',
	              		align:'center',
	              		field:'t_gift_gold',
	              	},
	              	{
	              		title:'销量',
	              		align:'center',
	              		field:'totalCount',
	              		formatter:function(value,row,index){
	              			return '<a href="javascript:give_detail_show('+row.t_gift_id+');">'+value+'</a>'
	              		}
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
	              		title:'操作',
	              		field:'',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			var res ='';
	              			res = res+'<a href="javascript:on_click_open_update_model('+row.t_gift_id+',\''+row.t_gift_name+'\',\''+row.t_gift_gif_url+'\',\''+row.t_gift_still_url+'\',\''+row.t_gift_gold+'\',\''+row.t_is_enable+'\');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">修改</a>&nbsp;&nbsp;';
	              			if(row.t_is_enable ==0){
	              				res = res +'<a href="javascript:on_click_operation('+row.t_gift_id+',1);"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">停用</a>&nbsp;&nbsp;';
	              			}else{
	              				res = res +'<a href="javascript:on_click_operation('+row.t_gift_id+',1);"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">启用</a>&nbsp;&nbsp;';
	              			}
	              			/*res = res +'<a href="javascript:on_click_operation('+row.t_gift_id+',2);"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">删除</a>';*/
	              			return res;
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
		allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
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
           if(id == 'file-1'){
        	   $("#t_gift_still_url").val(data.response.m_object);
           }else{
        	   $("#t_gift_gif_url").val(data.response.m_object);
           }
        
        }
    });
	
}


/**点击预览*/
function on_click_preview(url){
	$("#hand_img").attr("src",url);
	$("#img_preview").modal('show');
}

 
/**点击加载表格*/
function on_click_search(){
	$("#utable").bootstrapTable('destroy');
	ajax_load_table();
}
/**
 * 新增或者修改
 * @param id
 * @param nickName
 */
function on_click_open_update_model(id,t_gift_name,t_gift_gif_url,t_gift_still_url,t_gift_gold,t_is_enable){
	
	if(id == null || id ==0){
		$("#t_gift_id").val("");
		$("#t_gift_name").val("");
		$("#t_gift_gif_url").val("");
		$("#t_gift_still_url").val("");
		$("#t_gift_gold").val("");
	}else{
		$("#t_gift_id").val(id);
		$("#t_gift_name").val(t_gift_name);
		$("#t_gift_gif_url").val(t_gift_gif_url);
		$("#t_gift_still_url").val(t_gift_still_url);
		$("#t_gift_gold").val(t_gift_gold);
		$("#t_is_enable").val(t_is_enable);
	}
	on_load_file_input('file-1');
	on_load_file_input('file-2');
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
		url =  path + '/admin/isEnableGift.htm';
	}else{
		url =  path + '/admin/delGiftById.htm';
	}
	$.ajax({
		type : 'POST',
		url :url,
		data : {t_gift_id :id},
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
	if(null == $("#t_gift_name").val() || '' == $("#t_gift_name").val()){
		$("#t_gift_name").css({ "border-color": "red" });
		$('#t_gift_name_validate').empty();
		$('#t_gift_name_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
	    isOk  = false ; 
	}
	if(null == $("#t_gift_gif_url").val() || '' == $("#t_gift_gif_url").val()){
		$("#file-1").css({ "border-color": "red" });
		$('#file-1_validate').empty();
		$('#file-1_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
	    isOk  = false ; 
	}
	if(null == $("#t_gift_still_url").val() || '' == $("#t_gift_still_url").val()){
		$("#file-2").css({ "border-color": "red" });
		$('#file-2_validate').empty();
		$('#file-2_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
	    isOk  = false ; 
	}
	if(null == $("#t_gift_gold").val() || '' == $("#t_gift_gold").val()){
		$("#t_gift_gold").css({ "border-color": "red" });
		$('#t_gift_gold_validate').empty();
		$('#t_gift_gold_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
	    isOk  = false ; 
	}
	if(!isOk){
		 return ; 
	}
	
	$.ajax({
		type : 'POST',
		url :path+'/admin/addOrUpdateGift.htm',
		data : {
			t_gift_id:$("#t_gift_id").val(),
			t_gift_name:$("#t_gift_name").val(),
			t_gift_gif_url:$("#t_gift_gif_url").val(),
			t_gift_still_url:$("#t_gift_still_url").val(),
			t_gift_gold:$("#t_gift_gold").val(),
			t_is_enable:$("#t_is_enable").val(),
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


/**打开礼物赠送明细弹窗*/
function give_detail_show(giftId){
	//表格重新加载
	$("#give_deta_table").bootstrapTable('destroy');
	$('#gift_detail').modal('show');
	loadTable(giftId);
}


/**加载赠送明细*/
function loadTable(id){
	 $('#give_deta_table').bootstrapTable({
	        url: path+ '/admin/getGiveDetail.htm?giftId='+id,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams:function (params){
	        	return {
	                pageSize: params.limit, // 每页要显示的数据条数
	                page: params.pageNumber, // 每页显示数据的开始行号
	         
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
	              		title:'赠送人',
	              		align:'center',
	              		field:'nick'
	              	},
	              	{
	              		title:'收礼人',
	              		align:'center',
	              		field:'cNick'
	              	},
	              	{
	              		title:'金币数',
	              		field:'t_amount',
	              		align:'center'
	              	},
	              	{
	              		title:'时间',
	              		field:'t_create_time',
	              		align:'center'              		
	              	}
	        ]
	    });
}
