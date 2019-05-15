$(function(){
	$("#beginTime").datepicker();
	$("#endTime").datepicker();
	ajax_load_table();
});

function ajax_load_table(){
	//生成用户数据
	   var url = path+"admin/getRankingList.htm" ;
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
	              		title:'编号',
	              		field:'t_id',
	              		align:'center'
	              	},
	              	{
	              		title:'魅力榜(加载数量)',
	              		align:'center',
	              		field:'t_charm_number'
	              	},
	              	{
	              		title:'消费榜(加载数量)',
	              		align:'center',
	              		field:'t_consumption_number',
	              	},{
	              		title:'豪礼榜(加载数量)',
	              		align:'center',
	              		field:'t_courtesy_number',
	              	},
	              	{
	              		title:'操作',
	              		field:'',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			var valueStr = JSON.stringify(row);
	              			return "<a href='javascript:on_click_open_update_model("+valueStr+");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>修改</a>&nbsp;&nbsp;";
	              		}
	              	}
	        ]
	    });
}

/**
 * 点击处理
 * @param id
 * @param nickName
 */
function on_click_open_update_model(obj){
	$("#t_id").val(obj.t_id);
	$("#t_charm_number").val(obj.t_charm_number);
	$("#t_consumption_number").val(obj.t_consumption_number);
	$("#t_courtesy_number").val(obj.t_courtesy_number);
	$("#myModal").modal('show');
}

/**
 * 点击启用或者禁用用户
 */
function on_click_uprank(){
	//验证必传参数
	var isOk = true;
	if(null == $("#t_charm_number").val() || '' == $("#t_charm_number").val()){
		$("#t_charm_number").css({ "border-color": "red" });
		$('#t_charm_number_validate').empty();
		$('#t_charm_number_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	
	if(null == $("#t_consumption_number").val() || '' == $("#t_consumption_number").val()){
		$("#t_consumption_number").css({ "border-color": "red" });
		$('#t_consumption_number_validate').empty();
		$('#t_consumption_number_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	if(null == $("#t_courtesy_number").val() || '' == $("#t_courtesy_number").val() || !isRealNum($("#t_courtesy_number").val())){
		$("#t_courtesy_number").css({ "border-color": "red" });
		$('#t_courtesy_number_validate').empty();
		$('#t_courtesy_number_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		isOk = false;
	}
	
	if(!isOk){
		return ;
	}
	
	$.ajax({
		type : 'POST',
		url : path + '/admin/upRankData.htm',
		data : {
			t_id : $("#t_id").val(),
			t_charm_number :$("#t_charm_number").val(),
			t_consumption_number : $("#t_consumption_number").val(),
			t_courtesy_number:$("#t_courtesy_number").val()
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

 


