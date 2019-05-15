$(function() {
	ajax_table();
	ajax_getTotalMoney();
	$("#beginTime").datepicker();
	$("#endTime").datepicker();
});

function ajax_table() {
	// 生成用户数据
	var url = path + "/admin/getRecharageList.htm";
	$('#utable').bootstrapTable({
		url : url,
		method : 'post',
		contentType : "application/x-www-form-urlencoded",
		queryParamsType : '',
		queryParams : function(params) {
			return {
				pageSize : params.limit,
				page : params.pageNumber,
				type : $("#type").val(),
				beginTime : $('#beginTime').val(),
				endTime : $('#endTime').val()
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
			title : 'idcard',
			field : 't_idcard',
			align : 'center'
		}, {
			title : '昵称',
			field : 't_nickName',
			align : 'center'
		}, {
			title : '支付金额',
			align : 'center',
			field : 't_recharge_money'
		}, {
			title : '订单号',
			align : 'center',
			field : 't_order_no'
		}, {
			title : '充值类型',
			align : 'center',
			field : 't_recharge_type',
			formatter : function(value) {
				return value == 0 ? 'VIP充值' : '金币充值';
			}
		}, {
			title : '支付类型',
			align : 'center',
			field : 't_payment_type',
			formatter : function(value) {
				return value == 0 ? '支付宝' : '微信';
			}
		}, {
			title : '创建时间',
			align : 'center',
			field : 't_create_time'
		}, {
			title : '状态',
			field : 't_order_state',
			align : 'center',
			formatter : function(value, row, index) {
				var res = '';
				if (value == 0) {
					res = '<span style="color:#D15FEE;">订单已创建</span>';
				}
				if (value == 1) {
					res = '<span style="color:#4169E1;">订单已支付</span>';
				}
				if (value == 2) {
					res = '<span style="color:red;">放弃支付</span>';
				}
				return res;
			}
		}, {
			title : '完成时间',
			align : 'center',
			field : 't_fulfil_time'
		} ]
	});
}

/**
 * 点击弹窗
 * 
 * @param id
 * @param nickName
 */
function on_click_open_update_model(obj) {
	console.log(obj);
	if (null == obj) {
		$("#t_id").val(""), $("#t_setmeal_name").val(""), $("#t_cost_price")
				.val(""), $("#t_money").val(""), $("#t_duration").val(""), $(
				"#t_is_enable").val(0)
	} else {
		$("#t_id").val(obj.t_id);
		$("#t_setmeal_name").val(obj.t_setmeal_name), $("#t_cost_price").val(
				obj.t_cost_price), $("#t_money").val(obj.t_money), $(
				"#t_duration").val(obj.t_duration), $("#t_is_enable").val(
				obj.t_is_enable)
	}

	$("#myModal").modal('show');
}
/**
 * 点击操作
 */
function on_click_operation(id) {
	$("#del_id").val(id);
	$("#myDel").modal('show');
}

/** 点击删除 */
function on_click_del(id, enableOrDisable) {
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
				$("#utable").bootstrapTable('refreshOptions', {
					page : 1
				});
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/**
 * 点击启用或者禁用用户
 */
function on_click_change_res(id, enableOrDisable) {
	$.ajax({
		type : 'POST',
		url : path + '/admin/upEnableOrDisable.htm',
		data : {
			t_id : id,
			state : enableOrDisable
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#utable").bootstrapTable('refreshOptions', {
					page : 1
				});
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/**
 * 点击提交新增或修改
 */
function on_click_submit() {
	$.ajax({
		type : 'POST',
		url : path + '/admin/saveVipSetMeal.htm',
		data : {
			t_id : $("#t_id").val(),
			t_setmeal_name : $("#t_setmeal_name").val(),
			t_cost_price : $("#t_cost_price").val(),
			t_money : $("#t_money").val(),
			t_duration : $("#t_duration").val(),
			t_is_enable : $("#t_is_enable").val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				$("#utable").bootstrapTable('refreshOptions', {
					page : 1
				});
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}

function ajax_getTotalMoney() {
	$.ajax({
		type : 'POST',
		url : path + '/admin/getTotalMoney.htm',
		data : {
			type : $("#type").val(),
			beginTime : $('#beginTime').val(),
			endTime : $('#endTime').val()
		},
		dataType : 'json',
		success : function(data) {
			console.log(data);
			if (data.m_istatus == 1) {
				$("#money").html(data.m_object);
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/** 选择后跳转 */
function on_change() {
	$("#utable").bootstrapTable('destroy');
	ajax_table();
	ajax_getTotalMoney();
}
/** 点击搜索 */
function on_click_search() {

	$("#utable").bootstrapTable('destroy');
	ajax_table();
	ajax_getTotalMoney();
}
