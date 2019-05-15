$(function() {
	ajax_table();
	ajax_getTotalMoney();
	$("#beginTime").datepicker();
	$("#endTime").datepicker();
});

function ajax_table(type) {
	// 生成用户数据
	var url = path + "/admin/getPutForwardList.htm";
	$('#utable')
			.bootstrapTable(
					{
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
						columns : [
								{
									title : 'idcard',
									field : 't_idcard',
									align : 'center'

								},
								{

									title : '用户昵称',
									field : 't_nickName',
									align : 'center',
									formatter : function(value, row, index) {
										return '<a href="javascript:on_click_finance_popup('
												+ row.t_user_id
												+ ')">'
												+ value
												+ '</a>';
									}
								},
								{
									title : '提现名称',
									field : 't_real_name',
									align : 'center'
								},
								{
									title : '提现方式',
									align : 'center',
									field : 't_type',
									formatter : function(value) {
										return value == 0 ? '支付宝' : '微信';
									}
								},
								{
									title : '提现账号',
									align : 'center',
									formatter : function(value, row, index) {
										return row.t_account_number;
									}
								},
								{
									title : '兑换金币(个)',
									align : 'center',
									field : 'gold'

								},
								{
									title : '提现金额(元)',
									align : 'center',
									field : 't_money'
								},
								{
									title : '套餐数据(金币:金额)',
									align : 'center',
									formatter : function(value, row) {

										var obj = '';
										if (row.review == 1) {
											obj = obj
													+ '<span style = "color:#00FF00;">'
													+ row.t_gold + ':'
													+ row.amount + '</span>';
										} else {
											obj = obj
													+ '<span style = "color:red;">'
													+ row.t_gold + ':'
													+ row.amount + '</span>';
										}

										return obj;
									}
								},
								{
									title : '申请时间',
									align : 'center',
									field : 't_create_time'
								},
								{
									title : '描述',
									align : 'center',
									field : 't_describe',
									formatter : function(value) {
										return '<span>' + value + '</span>';
									}
								},
								{
									title : '处理时间',
									align : 'center',
									field : 't_handle_time'
								},
								{
									title : '提现状态',
									align : 'center',
									field : 't_order_state',
									formatter : function(value) {
										if (value == 0) {
											return "<span style='color:#FF00FF;'>未审核</span>";
										} else if (value == 1) {
											return "<span style='color:#0000FF;'>已审核,待打款</span>";
										} else if (value == 2) {
											return "<span style='color:#00FF00;'>已打款</span>";
										} else if (value == 3) {
											return "<span style='color:red;'>提现失败</span>";
										}
									}
								},
								{
									title : '操作',
									align : 'center',
									formatter : function(value, row, index) {
										var str = '';
										if (row.t_order_state < 2
												&& row.t_disable == 0) {
											// str = str + "<a href =
											// 'javascript:on_click_check("+row.t_id+","+row.t_user_id+")'
											// class = 'btn btn-default'
											// >审核并打款</a>&nbsp;&nbsp;&nbsp;";
											str = str + "<a href = 'javascript:on_click_check("
													+ row.t_id
													+ ","
													+ row.t_user_id
													+ ",\""
													+ row.t_real_name
													+ "\",\""
													+ row.t_account_number
													+ "\","
													+ row.t_money
													+ ")' class = 'btn btn-default' >审核并打款</a>&nbsp;&nbsp;&nbsp;";
											str += "<a href = 'javascript:on_click_stop_user("
													+ row.t_user_id
													+ ")' class = 'btn btn-default' >封号</a>";
										}
										return str;
									}
								}

						]
					});
}
/** 点击提醒 */
function on_click_check(id, userId, nick_name, t_account_number, t_money) {
	$('#t_id').val(id);
	$('#userId').val(userId);
	$('#nick_name').val(nick_name);
	$('#t_account_number').val(t_account_number);
	$('#t_money').val(t_money);
	$('#myMoal').modal('show');
}
/** 点击审核并打款 */
function on_click_check_submit(type) {
	$("#myMoal").modal('hide');
	$.ajax({
		type : 'POST',
		url : path + '/admin/madeMoney.htm',
		data : {
			t_id : $("#t_id").val(),
			userId : $("#userId").val(),
			handleType : type,
			message : $('#message').val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$('#myMoal_success').modal('show');
				$("#utable").bootstrapTable('refreshOptions', {
					page : 1
				});
			} else {
				$('#mgs').text(data.m_strMessage);
				$('#myMoal_error').modal('show');
				$("#utable").bootstrapTable('refreshOptions', {
					page : 1
				});
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
			t_duration : $("#t_duration").val(),
			t_state : $("#t_state").val()
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
		url : path + '/admin/getPutForwardTotal.htm',
		data : {
			type : $("#type").val(),
			beginTime : $('#beginTime').val(),
			endTime : $('#endTime').val()
		},
		dataType : 'json',
		success : function(data) {
			console.log(data);
			if (data.m_istatus == 1) {
				$("#money").html(data.m_object.total);
				$("#unaudited").html(
						null == data.m_object.unaudited ? 0
								: data.m_object.unaudited);
				$("#alreadyPresented").html(
						null == data.m_object.alreadyPresented ? 0
								: data.m_object.alreadyPresented);
				$("#fail").html(
						null == data.m_object.fail ? 0 : data.m_object.fail);
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

/** 点击搜索按钮* */
function on_click_search() {
	$("#utable").bootstrapTable('destroy');
	ajax_table();
	ajax_getTotalMoney();
}

/** 用户永久封号 */
function on_click_stop_user(id) {
	$.ajax({
		url : path + "admin/enableOrDisable.htm",
		data : {
			t_id : id,
			state : 2
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
