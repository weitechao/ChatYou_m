$(function() {
	ajax_table();
	ajax_getTotalMoney();
	$("#beginTime").datepicker();
	$("#endTime").datepicker();
});
 

function ajax_table() {
	// 生成用户数据
	var url = path + "/admin/getFinancialDetailsList.htm";
	$('#utable').bootstrapTable({
		url : url,
		method : 'post',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : '',
		queryParams : function(params){
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
		columns : [ {
			title : '统计日期',
			field : 'time',
			align : 'center'
		}, {
			title : '入账金额',
			align : 'center',
			field : 'income'
		}, {
			title : '支出金额',
			align : 'center',
			field : 'expenditure'
		}, {
			title : '营业收入',
			align : 'center',
			field : 'profit',
			formatter : function(value) {
				if (value < 0) {
					return '<span style="color:#00FF00;">' + value + '</span>';
				} else if (value > 0) {
					return '<span style="color:	red;">' + value + '</span>';
				}
			}
		}

		]
	});
}

function ajax_getTotalMoney() {
	$.ajax({
		type : 'POST',
		url : path + '/admin/getCollectPayTotal.htm',
		data : {
			beginTime : $("#beginTime").val(),
			endTime : $("#endTime").val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#profit").html(
						null == data.m_object.totalMoney ? 0
								: data.m_object.totalMoney);
				$("#pay")
						.html(
								null == data.m_object.putPay ? 0
										: data.m_object.putPay);
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/** 选择后跳转 */
function on_click_search() {
	$("#utable").bootstrapTable('destroy');
	ajax_table();
	ajax_getTotalMoney();
}
