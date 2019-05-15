$(function(){
    //生成用户数据
   var url = path+"/admin/getVipSetMealList.htm" ;
    $('#utable').bootstrapTable({
        url: url,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        queryParamsType:'', 
        queryParams: function (params){
        	return {
        		pageSize: params.limit,
        		page:params.pageNumber
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
              		title:'套餐名称',
              		field:'t_setmeal_name',
              		align:'center'
              	},
              	{
              		title:'原价',
              		align:'center',
              		field:'t_cost_price'
              	},
              	{
              		title:'优惠价',
              		align:'center',
              		field:'t_money'
              	},
              	{
              		title:'时长(月)',
              		align:'center',
              		field:'t_duration'
              	},
              	{
              		title:'销量',
              		align:'center',
              		field:'total',
              		formatter:function(value,row,index){
              			return '<a href="javascript:on_click_vip_detail_show('+row.t_id+')">'+value+'</a>'
              		}
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
    
});

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
	
	var isOk = true ; 
	
	if(null == $("#t_setmeal_name").val() || '' == $("#t_setmeal_name").val()){
		$("#t_setmeal_name").css({ "border-color": "red" });
  		$('#t_setmeal_name_validate').empty();
  		$('#t_setmeal_name_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
  		isOk = false ;
	}
	if(null == $("#t_cost_price").val() || '' == $("#t_cost_price").val() || !isRealNum($('#t_cost_price').val())){
		$("#t_cost_price").css({ "border-color": "red" });
  		$('#t_cost_price_validate').empty();
  		$('#t_cost_price_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
  		$("#t_cost_price").val('');
		$('#t_cost_price').attr('placeholder','请输入数字.')
		
  		isOk = false ;
	}
	if(null == $("#t_money").val() || '' == $("#t_money").val() || !isRealNum($('#t_money').val())){
		$("#t_money").css({ "border-color": "red" });
  		$('#t_money_validate').empty();
  		$('#t_money_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
  		$("#t_money").val('');
		$('#t_money').attr('placeholder','请输入数字.')
  		isOk = false ;
	}
	if(null == $("#t_duration").val() || '' == $("#t_duration").val() || !isRealNum($('#t_duration').val())){
		$("#t_duration").css({ "border-color": "red" });
  		$('#t_duration_validate').empty();
  		$('#t_duration_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
  		$("#t_duration").val('');
		$('#t_duration').attr('placeholder','请输入数字.')
  		isOk = false ;
	}
	if(null == $("#t_gold").val() || '' == $("#t_gold").val() || !isRealNum($('#t_gold').val())){
		$("#t_gold").css({ "border-color": "red" });
  		$('#t_gold_validate').empty();
  		$('#t_gold_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
  		$("#t_gold").val('');
		$('#t_gold').attr('placeholder','请输入数字.')
  		isOk = false ;
	}
	
	
	if(!isOk){
		return ; 
	}
	
	$.ajax({
		type : 'POST',
		url : path + '/admin/saveVipSetMeal.htm',
		data : {
			t_id : $("#t_id").val(),
			t_setmeal_name :$("#t_setmeal_name").val(),
			t_cost_price :$("#t_cost_price").val(),
			t_money :$("#t_money").val(),
			t_duration :$("#t_duration").val(),
			t_is_enable :$("#t_is_enable").val(),
			t_gold : $('#t_gold').val()
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



function on_click_vip_detail_show(id){
	//表格重新加载
	$("#vip_detail_table").bootstrapTable('destroy');
	$('#vip_detail').modal('show');
	loadTable(id);
}

/**加载赠送明细*/
function loadTable(id){
	
	 $('#vip_detail_table').bootstrapTable({
	        url: path+ '/admin/getVIPConsumeDetail.htm?vipId='+id,
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
	              		title:'昵称',
	              		align:'center',
	              		field:'t_nickName'
	              	},
	              	{
	              		title:'订单号',
	              		align:'center',
	              		field:'t_order_no'
	              	},
	              	{
	              		title:'订单支付时间',
	              		align:'center',
	              		field:'t_fulfil_time'
	              	},
	              	{
	              		title:'VIP到期时间',
	              		field:'t_end_time',
	              		align:'center'
	              	}
	        ]
	    });
}

 


