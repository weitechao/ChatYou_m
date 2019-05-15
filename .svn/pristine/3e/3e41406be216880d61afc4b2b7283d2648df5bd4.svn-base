$(function(){
	load_table();
});

//加载表格
function load_table(){
	  //生成用户数据
	   var url = path+"admin/getCashValueList.htm" ;
	    $('#utable').bootstrapTable({
	        url: url,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams: function(params){
	        	return {
	        		pageSize: params.limit,
	        		page:params.pageNumber,
	        		t_project_type:$('#search_project_type').val(),
	        		t_end_type:$('#search_end_type').val()
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
	              		title:'项目类别',
	              		field:'t_project_type',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			return value==1?'充值':'提现';
	              		}
	              	},
	              	{
	              		title:'金额(元)',
	              		align:'center',
	              		field:'t_money'
	              	},
	              	{
	              		title:'金币',
	              		align:'center',
	              		field:'t_gold'
	              	},
	              	{
	              		title:'端类型',
	              		align:'center',
	              		field:'t_end_type',
	              		formatter:function(value){
	              			return value == null?"无":value == 0?"Android":"iPhone";
	              		}
	              	},
	              	{
	              		title:'描述',
	              		align:'center',
	              		field:'t_describe'
	              	},
	              	{
	              		title:'创建时间',
	              		align:'center',
	              		field:'t_create_time'
	              	},
	              	{
	              		title:'状态',
	              		field:'t_is_enable',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			var res = '';
	              			if(value == 0){
	              				res = '已启用';
	              			}
	              			if(value == 1){
	              				res = '已禁用';
	              			}
	              			return res;
	              		}
	              	},
	              	{
	              		title:'操作',
	              		field:'',
	              		align:'center',
	              		formatter:function(row,index){
	              	 
	              			var valueStr = JSON.stringify(index);
	              			
	              			var res ="<a class='btn btn-default' href='javascript:on_click_open_update_model("+valueStr+");' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>修改</a>&nbsp;&nbsp;";
	              			if(index.t_is_enable ==0){
	              				res = res +'<a href="javascript:on_click_change_res('+index.t_id+',1)" class="btn btn-default" style="height: 25px;line-height: 0.5;color:red;background-color: #87CEFA;">禁用</a>&nbsp;&nbsp;';
	              			}
	              			if(index.t_is_enable ==1){
	              				res =res + '<a href="javascript:on_click_change_res('+index.t_id+',0);" class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">启用</a>&nbsp;&nbsp;';
	              			}
	              		    res = res +'<a class="btn btn-default" href="javascript:on_click_operation('+index.t_id+');"; style="height: 25px;line-height: 0.5;background-color: #87CEFA;">删除</a>&nbsp;&nbsp;';
	              			return res;
	              		}
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
		$("#t_describe").val(""),
		$("#t_is_enable").val(0)
	}else{
		$("#t_id").val(obj.t_id);
		$("#t_project_type").val(obj.t_project_type),
		$("#t_gold").val(obj.t_gold),
		$("#t_money").val(obj.t_money),
		$("#t_describe").val(obj.t_describe)
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
function on_click_del(id){
	$.ajax({
		type : 'POST',
		url : path + '/admin/delCashValue.htm',
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
 * 点击提交新增或修改
 */
function on_click_submit(){
	
	var isOk = true ;
	
	if(null == $("#t_money").val() || '' == $("#t_money").val()){
		$("#t_money").css({ "border-color": "red" });
		$('#t_money_validate').empty();
		$('#t_money_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(null == $("#t_gold").val() || '' == $("#t_gold").val()){
		$("#t_gold").css({ "border-color": "red" });
		$('#t_gold_validate').empty();
		$('#t_gold_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(!isOk){
		return ;
	}
	$.ajax({
		type : 'POST',
		url : path + 'admin/addOrUpdateCashValue.htm',
		data : {
			t_id : $("#t_id").val(),
			t_project_type :$("#t_project_type").val(),
			t_gold :$("#t_gold").val(),
			t_money :$("#t_money").val(),
			t_end_type : $('#t_end_type').val(),
			t_is_enable :$("#t_is_enable").val(),
			t_describe :$('#t_describe').val()
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


function on_project_type_onchange(){
	$("#utable").bootstrapTable('destroy');
	load_table();
}

function on_end_type_onchange(){
	$("#utable").bootstrapTable('destroy');
	load_table();
}
 


