$(function(){
	$("#beginTime").datepicker();
	$("#endTime").datepicker();
	ajax_load_table();
});

function ajax_load_table(){
	 //生成用户数据
	   var url = path+"/admin/getImLogList.htm" ;
	    $('#utable').bootstrapTable({
	        url: url,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams: function (params){
	        	return {
	        		pageSize: params.limit,
	        		page:params.pageNumber,
	        		condition:$('#condition').val(),
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
						title:'发送者',
						align:'center',
						field:'t_nickName'
					},
					{
						title:'接收者',
						align:'center',
						field:'coveNickName'
						 
					},
	              	{
	              		title:'发送内容',
	              		align:'center',
	              		field:'t_content',
	              		formatter:function(value){
	              			return JSON.stringify(value);
	              		}
				    },
					{
						title:'发送时间',
						align:'center',
						field:'t_create_time'
					},{
						title:'操作',
						align:'center',
						field:'t_id',
						formatter:function(value,row){
							if(row.t_disable == 0)
								return '<a class="btn btn-default" href="javascript:on_click_stop_user('+value+')"; style="height: 25px;line-height: 0.5;background-color: #87CEFA;">封号</a>&nbsp;&nbsp;</br></br>';
							else
								return '';
						}
						
					}
	        ]
	    });
}


/**
 * 删除版本
 */
function on_click_search(){
	//表格重新加载
	$("#utable").bootstrapTable('destroy');
	ajax_load_table();
}

/** 用户永久封号*/
function on_click_stop_user(id){
	$.ajax({
		url:path+"/admin/enableOrDisable.htm",
		data:{t_id:id,state:2},
		dataType:'json',
		success:function(data){
			if (data.m_istatus == 1) {
				$("#myDel").modal('hide');
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}








