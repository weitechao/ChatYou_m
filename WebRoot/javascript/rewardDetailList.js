$(function(){
	on_load_table();
});
 


function on_load_table(){
	 //生成用户数据
	   var url = path+"/admin/getRewardDetailList.htm" ;
	    $('#utable').bootstrapTable({
	        url: url,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams: function (params){
	        	return {
	        		pageSize: params.limit,
	        		page:params.pageNumber,
	        		nickName:$('#search_name').val()
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
	              		title:'用户昵称',
	              		align:'center',
	              		field:'t_nickName'
	              	},
	              	{
	              		title:'活动名称',
	              		align:'center',
	              		field:'t_activity_name'
	              	},
	              	{
	              		title:'中奖名称',
	              		align:'center',
	              		field:'t_prize_name'
	              	},
	              	{
	              		title:'中奖金币',
	              		field:'t_award_gold',
	              		align:'center'
	              	},
	              	{
	              		title:'中奖时间',
	              		field:'t_award_time',
	              		align:'center'
	              	}
	        ]
	    });
}

function on_click_search(){
	$("#utable").bootstrapTable('destroy');
	on_load_table();
}



