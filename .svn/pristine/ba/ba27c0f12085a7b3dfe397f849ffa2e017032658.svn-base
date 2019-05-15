$(function(){
	ajax_load_table();
});

function ajax_load_table(){
	 //生成用户数据
	   var url = path+"/admin/getStyleSetUp.htm" ;
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
						title:'编号',
						align:'center',
						field:'t_id',
					},
					{
						title:'风格名称',
						align:'center',
						field:'t_style_name'
					},
					{
						title:'风格标示',
						align:'center',
						field:'t_mark'
					},
	              	{
	              		title:'状态',
	              		align:'center',
	              		field:'t_state',
	              		formatter:function(value,row,index){
	              			 return value == 0?'未启用':'启用';
	              		}
				    },
	              	{
	              		title:'操作',
	              		field:'',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			var valueStr = JSON.stringify(row);
	              			var obj = "<a href='javascript:on_click_open_update_model("+valueStr+")' class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>修改</a> &nbsp;&nbsp;&nbsp;";
	              			return obj +='<a href="javascript:on_click_operation('+row.t_id+');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">删除</a>';
	              		}
	              	}
	        ]
	    });
}

/**
 * 新增或者修改
 * @param id
 * @param nickName
 */
function on_click_open_update_model(obj){
	if(null == obj  || obj == ""){
		$("#t_style_name").val('');
		$("#t_mark").val('');
		$("#t_id").val('');
	}else{
		$("#t_style_name").val(obj.t_style_name);
		$("#t_mark").val(obj.t_mark);
		$("#t_id").val(obj.t_id);
	}
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
		url :path+'/admin/delStyleSetUp.htm',
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
	if(null == $("#t_style_name").val() || '' == $("#t_style_name").val()){
		$("#t_style_name").css({ "border-color": "red" });
		$('#t_style_name_validate').empty();
		$('#t_style_name_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
	    isOk  = false ; 
	}
	if(null == $("#t_mark").val() || '' == $("#t_mark").val()){
		$("#t_mark").css({ "border-color": "red" });
		$('#t_mark_validate').empty();
		$('#t_mark_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
	    isOk  = false ; 
	}
	if(!isOk){
		 return ; 
	}
	
	$.ajax({
		type : 'POST',
		url :path+'/admin/saveStyleSetUp.htm',
		data : {
			t_id:$('#t_id').val(),
			t_style_name:$("#t_style_name").val(),
			t_mark:$("#t_mark").val(),
			t_state:$("#t_state").val()
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








