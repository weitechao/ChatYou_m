$(function() {
	$('#utable').bootstrapTable({
		url : path + "admin/getSettlementList.htm",
		method : 'post',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : '',
		queryParams : function(params) {
			// 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			var temp = {
				pageSize : params.limit,// 页面大小
				page : params.pageNumber, // 页码
				t_user_id : $("#t_spread_id").val()
			};
			return temp;
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
					title : '日期',
					field : 'data',
					align : 'center'
				},
				{
					title : '装机量',
					field : 'installNum',
					align : 'center'
				},
				{
					title : '金币充值(元)',
					field : 'gold',
					align : 'center'
				},
				{
					title : '金币提成(元)',
					field : 'goldMoney',
					align : 'center'
				},
				{
					title : 'VIP充值(元)',
					align : 'center',
					field : 'vip'
				},
				{
					title : 'VIP提成(元)',
					align : 'center',
					field : 'vipMoney'
				},
				{
					title : '总提成(元)',
					align : 'center',
					field : 'totalMoney'
				}]
	});

});


