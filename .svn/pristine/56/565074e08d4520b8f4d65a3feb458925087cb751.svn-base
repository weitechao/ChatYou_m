$(function() {
	$("#beginTime").datepicker();
	$("#endTime").datepicker();
	onload_day_table();
});

function onload_day_table(){
	// 生成用户数据
	$('#utable').bootstrapTable(
					{
						url : path + "/admin/getSpeedDatingTotal.htm",
						method : 'post',
						contentType : "application/x-www-form-urlencoded",
						queryParamsType : '',
						queryParams : function(params) {
							return {
								pageSize : params.limit,
								page : params.pageNumber,
								beginTime : $("#beginTime").val(),
								endTime : $("#endTime").val()
							}
						},
						// 【其它设置】
						locale : 'zh-CN',// 中文支持
						pagination : true,// 是否开启分页（*）
						pageNumber : 1,// 初始化加载第一页，默认第一页
						pageSize : 10,// 每页的记录行数（*）
						sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
						// 【设置列】
						columns : [
								{
									title : '统计日期',
									align : 'center',
									field : 'calendar'
								},
								{
									title : '速配主播数',
									align : 'center',
									field : 'total'
								},
								{
									title : '速配累计时长',
									align : 'center',
									field : 'cumulativeTime',
								},
								{
									title : '操作',
									field : '',
									align : 'center',
									formatter : function(value, row, index) {
										return '<a href="javascript:onload_day_detail(\''+row.calendar+'\');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">速配明细</a>&nbsp;&nbsp;';
									}
								} ]
					});
}

/** 点击搜索*/
function on_click_search(){
	$("#utable").bootstrapTable('destroy');
	onload_day_table();
}

/**加载日明细*/
function onload_day_detail(day){
	$('#sp_day_modal').modal('show');
	$("#day").val(day);
	$("#condition").val('');
	onload_table();
}

function onload_table(){
	$("#sp_day_detail").bootstrapTable('destroy');
	$('#sp_day_detail').bootstrapTable({
	        url: path+ '/admin/getSpeedDatingDayDetail.htm',
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams:function(params){
	        	return {
	        		pageSize: params.limit,
	        		page:params.pageNumber,
	        		dayTime:$("#day").val(),
	        		condition:$("#condition").val()
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
	              		title:'idcard',
	              		align:'center',
	              		field:'t_idcard'
	              	},
	              	{
	              		title:'主播昵称',
	              		field:'t_nickName',
	              		align:'center'
	              	},
	              	{
	              		title:'当月累计时长',
	              		field:'monthTime',
	              		align:'center'
	              	},
	              	{
	              		title:'当日累计时长',
	              		field:'summary',
	              		align:'center'
	              	},{
	              		title:'汇总时间',
	              		field:'totalTime',
	              		align:'center'
	              	},
	              	{
	              		title:'操作',
	              		field:'',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			return '<a class="btn btn-default" href="javascript:anchor_sp_day_detail(\''+row.totalTime+'\','+row.t_user_id+')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">日明细</a>&nbsp;&nbsp;';
	              		}
	              	}
	        ]
	    });
}


/**主播速配日明细*/
function anchor_sp_day_detail(day,userId){
	$('#anchor_sp_day_modal').modal('show');
	$("#anchor_sp_day_detail").bootstrapTable('destroy');
	$('#anchor_sp_day_detail').bootstrapTable({
	        url: path+ '/admin/getAnchorSpDayDetail.htm',
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams:function(params){
	        	return {
	        		pageSize: params.limit,
	        		page:params.pageNumber,
	        		dayTime:day,
	        		userId:userId
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
	              		title:'idcard',
	              		align:'center',
	              		field:'t_idcard'
	              	},
	              	{
	              		title:'主播昵称',
	              		field:'t_nickName',
	              		align:'center'
	              	},{
	              		title:'开始时间',
	              		field:'t_begin_time',
	              		align:'center'
	              	},{
	              		title:'结束时间',
	              		field:'t_end_time',
	              		align:'center'
	              	},
	              	{
	              		title:'时长',
	              		field:'t_duration',
	              		align:'center'
	              	}
	        ]
	    });
}

