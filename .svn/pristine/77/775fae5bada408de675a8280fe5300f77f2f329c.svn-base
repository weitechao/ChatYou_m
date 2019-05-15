$(function(){
	ajax_table();
//    ajax_getTotalMoney();
    $("#beginTime").datepicker();
	$("#endTime").datepicker();
});
 

function ajax_table(){
	  //生成用户数据
	   var url = path+"/admin/getGiveList.htm";
	    $('#utable').bootstrapTable({
	        url: url,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams: function(params){
	        	return {
	        		pageSize: params.limit,
	        		page:params.pageNumber,
	        		beginTime:$('#beginTime').val(),
	        		endTime:$('#endTime').val()
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
	              		title:'操作人',
	              		field:'giveUser',
	              		align:'center'
	              	},
	              	{
	              		title:'受赠人',
	              		align:'center',
	              		field:'t_nickName',
	              		formatter:function(value,row){
	              			return value+'&nbsp;&nbsp;(IdCard：'+row.t_idcard+')'
	              		}
	              	},
	              	{
	              		title:'赠送金币',
	              		align:'center',
	              		field:'t_redpacket_gold'
	              	},
	              	{
	              		title:'是否领取',
	              		align:'center',
	              		field:'t_redpacket_draw',
	              		formatter:function(value){
	              			if(value == 0){
	              				return '未领取';
	              			}
	              			return '已领取'
	              		}
	              	},
	              	{
	              		title:'赠送时间',
	              		align:'center',
	              		field:'t_create_time'
	              	}
	        ]
	    });
}

/**
 * 点击弹窗
 * @param id
 * @param nickName
 */
function on_click_open_update_model(obj){
	console.log(obj);
	if(null == obj){
		$("#t_id").val(""),
		$("#t_setmeal_name").val(""),
		$("#t_cost_price").val(""),
		$("#t_money").val(""),
		$("#t_duration").val(""),
		$("#t_is_enable").val(0)
	}else{
		$("#t_id").val(obj.t_id);
		$("#t_setmeal_name").val(obj.t_setmeal_name),
		$("#t_cost_price").val(obj.t_cost_price),
		$("#t_money").val(obj.t_money),
		$("#t_duration").val(obj.t_duration),
		$("#t_is_enable").val(obj.t_is_enable)
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

/**点击删除*/
function on_click_del(id,enableOrDisable){
	$.ajax({
		type : 'POST',
		url : path + '/admin/delVipSetMeal.htm',
		data : {
			t_id : $("#del_id").val()
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
function on_click_change_res(id,enableOrDisable){
	$.ajax({
		type : 'POST',
		url : path + '/admin/upEnableOrDisable.htm',
		data : {
			t_id : id,
			state :enableOrDisable
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/**
 * 点击提交新增或修改
 */
function on_click_submit(){
	$.ajax({
		type : 'POST',
		url : path + '/admin/saveVipSetMeal.htm',
		data : {
			t_id : $("#t_id").val(),
			t_setmeal_name :$("#t_setmeal_name").val(),
			t_cost_price :$("#t_cost_price").val(),
			t_money :$("#t_money").val(),
			t_duration :$("#t_duration").val(),
			t_is_enable :$("#t_is_enable").val()
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


//function ajax_getTotalMoney(){
//	$.ajax({
//		type : 'POST',
//		url : path + '/admin/getConsumeTotal.htm',
//		data : {type:$("#type").val(),
//				beginTime:$('#beginTime').val(),
//				endTime:$('#endTime').val()},
//		dataType : 'json',
//		success : function(data) {
//			console.log(data);
//			if (data.m_istatus == 1) {
//				$("#money").html(data.m_object+'(金币)');
//			} else  {
//				window.location.href = path + '/error.html';
//			}
//		}
//	});
//}

/**选择后跳转*/
function on_change(){
	$("#utable").bootstrapTable('destroy');
	ajax_table();
    ajax_getTotalMoney();
}
/**搜索按钮*/
function on_click_search(){
	$("#utable").bootstrapTable('destroy');
	ajax_table();
    ajax_getTotalMoney();
}


