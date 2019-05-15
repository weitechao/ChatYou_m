$(function(){
	ajax_load_table();
});

//加载表格
function ajax_load_table(){
	//生成用户数据
    $('#utable').bootstrapTable({
        url: path+"admin/getActivityList.htm",
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
              		title:'活动名称',
              		field:'t_activity_name',
              		align:'center'
              	},
              	{
              		title:'奖励名额',
              		align:'center',
              		field:'t_activity_number'
              	},
              	{
              		title:'参与条件(分享次数)',
              		align:'center',
              		field:'t_join_term'
              	},
              	{
              		title:'开始时间',
              		align:'center',
              		field:'t_begin_time'
              	},
              	{
              		title:'结束时间',
              		align:'center',
              		field:'t_end_time'
              	},
              	{
              		title:'是否启用',
              		align:'center',
              		field:'t_is_enable',
              		formatter:function(value,row,index){
              			 //0.启用 1.禁用
              			 var obj = '';
              			 if(value == 0){
              				 obj = '<span style="color:#0000FF;">启用</span>';
              			 }else{
              				 obj = '<span style="color:red;">禁用</span>';
              			 }
              			 return obj;
              		}

              	},
              	{
              		title:'操作',
              		field:'',
              		align:'center',
              		formatter:function(value,row,index){
              			var valueStr = JSON.stringify(row);
              			
              			var res ="<a class='btn btn-default' href='javascript:on_click_saveOrUpdate("+valueStr+")' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>修改</a>&nbsp;&nbsp;";
              			res =res +"<a class='btn btn-default' href='javascript:on_click_activity_detail("+row.t_id+")' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>奖项明细</a>&nbsp;&nbsp;";
              			res =res +"<a class='btn btn-default' href='javascript:on_click_open_modal("+row.t_id+")' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>删除</a>&nbsp;&nbsp;";
              			 
              			return res;
              		}
              	}
        ]
    });
}
/**
 * 点击弹窗
 * @param id
 */
function on_click_saveOrUpdate(obj){
	if(null == obj || undefined == obj){
		$("#t_activity_name").val('');
		$("#t_activity_number").val('');
		$('#t_join_term').val('')
		$("#t_begin_time").val('');
		$("#t_end_time").val('');
	}else{
		$('#t_id').val(obj.t_id);
		$("#t_activity_name").val(obj.t_activity_name);
		$("#t_activity_number").val(obj.t_activity_number);
		$("#t_begin_time").val(obj.t_begin_time);
		$("#t_end_time").val(obj.t_end_time);
		$("#t_is_enable").val(obj.t_is_enable);
		$('#t_join_term').val(obj.t_join_term);
	}
	$("#t_begin_time").datepicker();
	$("#t_end_time").datepicker();
	$('#myModal').modal('show');
}

/**
 * 提交
 */
function on_click_submit(){
	var isOk = true;
	if(null == $("#t_activity_name").val() || '' == $("#t_activity_name").val()){
		$("#t_activity_name").css({ "border-color": "red" });
		$('#t_activity_name_validate').empty();
		$('#t_activity_name_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(null == $("#t_activity_number").val() || '' == $("#t_activity_number").val()){
		$("#t_activity_number").css({ "border-color": "red" });
		$('#t_activity_number_validate').empty();
		$('#t_activity_number_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(null == $("#t_begin_time").val() || '' == $("#t_begin_time").val()){
		$("#t_begin_time").css({ "border-color": "red" });
		$('#t_begin_time_validate').empty();
		$('#t_begin_time_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(null == $("#t_end_time").val() || '' == $("#t_end_time").val()){
		$("#t_end_time").css({ "border-color": "red" });
		$('#t_end_time_validate').empty();
		$('#t_end_time_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(null == $("#t_join_term").val() || '' == $("#t_join_term").val()){
		$("#t_join_term").css({ "border-color": "red" });
		$('#t_join_term_validate').empty();
		$('#t_join_term_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(isOk){
		$.ajax({
			type : 'POST',
			url :path+"admin/saveOrUpdateActivity.htm",
			data : {
				t_id :$('#t_id').val(),
				t_activity_name:$("#t_activity_name").val(),
				t_activity_number:$("#t_activity_number").val(),
				t_begin_time:$("#t_begin_time").val(),
				t_end_time:$("#t_end_time").val(),
				t_is_enable:$("#t_is_enable").val(),
				t_join_term:$('#t_join_term').val()
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if (data.m_istatus == 1) {
					$("#myModal").modal('hide');
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$('#myComfirm').modal('show');
					$("#utable").bootstrapTable('refreshOptions',{page : 1});
				} else  {
					window.location.href = path + '/error.html';
				}
			}
		});
	}
}
/**打开提示框*/
function on_click_open_modal(id){
	$('#del_id').val(id);
	$('#myDel').modal('show');
	
}
/**删除活动*/
function  on_click_del(){
	$.ajax({
		type : 'POST',
		url :path+"admin/delActivity.htm",
		data : {t_id :$('#del_id').val()},
		dataType : 'json',
		success : function(data) {
			console.log(data);
			if (data.m_istatus !=0) {
				$("#myDel").modal('hide');
				$('#my_comfirm').empty();
				$('#my_comfirm').append(data.m_strMessage);
				$('#myComfirm').modal('show');
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}
/**切换列表*/
function on_click_activity_detail(id){
	//加载活动编号
	$('#t_activity_id').val(id);
	$('#my_activity_detail').modal('show');
	$("#activity_detail").bootstrapTable('destroy');
	loadTable(id);
}

/**加载财务明细*/
function loadTable(id){
	 $('#activity_detail').bootstrapTable({
	        url: path+ 'admin/getActivityDetailList.htm?activityId='+id,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams:function(params){
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
	              		title:'奖品名称',
	              		align:'center',
	              		field:'t_prize_name'
	              	},
	              	{
	              		title:'奖品数量(份)',
	              		field:'t_prize_number',
	              		align:'center'
	              	},{
	              		title:'剩余数量(份)',
	              		field:'t_surplus_number',
	              		align:'center'
	              	},{
	              		title:'中奖数量',
	              		field:'t_prize_size',
	              		align:'center'
	              	},
	              	{
	              		title:'是否参与抽奖',
	              		field:'t_is_join',
	              		align:'center',
	              		formatter:function(value){
	              			return value == 0?'<span style="color:#33FF99;">是</span>':'<span style="color:red;">否</span>';
	              		}
	              	},
	              	{
	              		title:'操作',
	              		field:'',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			var obj = '';
	              			if(row.t_is_join == 0){
	              				obj = '<a class="btn btn-default" href="javascript:on_click_update_is_join('+row.t_id+','+row.t_is_join+')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">下架</a>&nbsp;&nbsp;';
	              			}else{
	              				obj = '<a class="btn btn-default" href="javascript:on_click_update_is_join('+row.t_id+','+row.t_is_join+')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">启用</a>&nbsp;&nbsp;';
	              			}
	              			var valueStr = JSON.stringify(row);
	              			var str =  "<a class='btn btn-default' href='javascript:on_click_save_prize("+valueStr+")' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>修改</a>&nbsp;&nbsp;";
	              			    str = str +  "<a class='btn btn-default' href='javascript:on_click_del_activity_detail("+row.t_id+")' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>删除</a>&nbsp;&nbsp;";
	              			return obj + str;
	              		}
	              	}
	        ]
	    });
}
 

/**点击更新操作*/
function on_click_update_is_join(id,type){
	
	var r = false;
	if(type == 0){
		r = confirm("确定要下架吗?");
	}else{
		r = confirm("确定要启用吗?")
	}
	if (r==true){
		$.ajax({
			type : 'POST',
			url :path+"admin/prizeUpdate.htm",
			data : {t_id : id},
			dataType : 'json',
			success : function(data) {
				if (data.m_istatus !=0) {
					$("#activity_detail").bootstrapTable('refreshOptions',{page : 1});
				} else  {
					window.location.href = path + '/error.html';
				}
			}
		});
	}
	 
}

/**加载奖品添加框*/
function on_click_save_prize(obj){
	if(null != obj &&  undefined != obj){
		 $("#t_detail_id").val(obj.t_id);
		 $("#t_prize_name").val(obj.t_prize_name),
		 $("#t_prize_number").val(obj.t_prize_number),
		 $("#t_is_join").val(obj.t_is_join);
		 $('#t_prize_size').val(obj.t_prize_size);
	}else{
		 $("#t_detail_id").val('');
		 $("#t_prize_name").val(''),
		 $("#t_prize_number").val(''),
		 $("#t_prize_size").val(''),
		 $("#t_is_join").val(0);
	}
	$('#prize_detail').modal('show');
}


/**点击提交*/
function on_click_prize_submit(){
	
	var isOk = true;
	if(null == $("#t_prize_name").val() || '' == $("#t_prize_name").val()){
		$("#t_prize_name").css({ "border-color": "red" });
		$('#t_prize_name_validate').empty();
		$('#t_prize_name_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(null == $("#t_prize_number").val() || '' == $("#t_prize_number").val()){
		$("#t_prize_number").css({ "border-color": "red" });
		$('#t_prize_number_validate').empty();
		$('#t_prize_number_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(isOk){ //提交
		$.ajax({
			type : 'POST',
			url :path+"admin/saveOrUpdateDetail.htm",
			data : {t_id : $("#t_detail_id").val(),
				 t_activity_id:$('#t_activity_id').val(),
				 t_prize_name:$("#t_prize_name").val(),
				 t_prize_number:$("#t_prize_number").val(),
				 t_prize_size:$("#t_prize_size").val(),
				 t_is_join: $("#t_is_join").val()},
			dataType : 'json',
			success : function(data) {
				if (data.m_istatus !=0) {
					$('#prize_detail').modal('hide');
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$('#myComfirm').modal('show');
					$("#activity_detail").bootstrapTable('refreshOptions',{page : 1});
				} else  {
					window.location.href = path + '/error.html';
				}
			}
		});
	}
}
/**删除奖品*/
function on_click_del_activity_detail(id){
	var r = confirm("确定要删除该项吗?");
	if(r){
		$.ajax({
			type : 'POST',
			url :path+"admin/delActivityDetail.htm",
			data : {t_id : id},
			dataType : 'json',
			success : function(data) {
				if (data.m_istatus !=0) {
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$('#myComfirm').modal('show');
					$("#activity_detail").bootstrapTable('refreshOptions',{page : 1});
				} else  {
					window.location.href = path + '/error.html';
				}
			}
		});
	}
}



 


