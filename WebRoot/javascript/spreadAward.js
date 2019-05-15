$(function(){
	ajax_load_table();
});

function ajax_load_table(){
	 //生成用户数据
	   var url = path+"/admin/getSpreadAwardList.htm" ;
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
						title:'奖励金币数',
						align:'center',
						field:'t_gold'
					},
					{
						title:'推广等级',
						align:'center',
						field:'t_rank',
						formatter:function(value){
							return value == 1? '一级推广':'二级推广';
						}
					},
	              	{
	              		title:'奖励性别',
	              		align:'center',
	              		field:'t_sex',
	              		formatter:function(value,row,index){
	              			 return value == 0?'女':'男';
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
	console.log(obj)
	if(null == obj  || obj == ""){
		$("#t_gold").val('');
		$("#t_rank").val('1');
		$("#t_sex").val('1');
	}else{
		$("#t_id").val(obj.t_id);
		$("#t_gold").val(obj.t_gold);
		$("#t_rank").val(obj.t_rank);
		$("#t_sex").val(obj.t_sex);
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
		url :path+'/admin/delSpreadAward.htm',
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
	if(null == $("#t_gold").val() || '' == $("#t_gold").val()){
		$("#t_gold").css({ "border-color": "red" });
		$('#t_gold_validate').empty();
		$('#t_gold_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
	    isOk  = false ; 
	}
	 
	if(isOk){
		$.ajax({
			type : 'POST',
			url :path+'/admin/saveOrUpSpreadAward.htm',
			data : {
				t_id:$('#t_id').val(),
				t_gold:$("#t_gold").val(),
				t_rank:$("#t_rank").val(),
				t_sex:$("#t_sex").val()
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
	
}








