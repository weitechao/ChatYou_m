$(function() {
	ajax_load_table();
});

function ajax_load_table() {
	// 生成用户数据
	var url = path + "admin/getCertificationList.htm";
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
								condition : $('#condition').val()
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
									title : '昵称',
									field : 't_nickName',
									align : 'center'
								},
								{
									title : '性别',
									align : 'center',
									field : 't_sex',
									formatter : function(value, row, index) {
										if (0 == value) {
											return '女';
										} else {
											return '男';
										}
									}

								},
								{
									title : '认证图片',
									align : 'center',
									field : 't_user_photo',
									formatter : function(value, row, index) {
										return '<img src="'
												+ value
												+ '" style="cursor:pointer" title="点击预览" width="40" height="40" onclick="on_click_preview(\''
												+ value
												+ ');">';
									}

								},
								{
									title : '身份图片',
									align : 'center',
									field : 't_user_video',
									formatter : function(value, row, index) {
										return '<img src="'
												+ value
												+ '" style="cursor:pointer" title="点击预览" width="40" height="40" onclick="on_click_preview(\''
												+ value + '\');">';
									}
								},
								// {
								// title:'身份证号',
								// align:'center',
								// field:'t_id_card'
								// },
								{
									title : '认证时间',
									field : 't_create_time',
									align : 'center'
								},
								{
									title : '状态',
									field : 't_certification_type',
									align : 'center',
									formatter : function(value, row, index) {
										var res = '';
										if (value == 0) {
											res = '未审核';
										}
										if (value == 1) {
											res = '审核成功';
										}
										if (value == 2) {
											res = '审核失败';
										}
										return res;
									}
								},
								{
									title : '账号状态',
									field : 't_disable',
									align : 'center',
									formatter : function(value, row, index) {
										var res = '';
										if (value == 0) {
											res = '正常';
										}
										if (value == 1) {
											res = '封号';
										}
										if (value == 2) {
											res = '禁用';
										}
										return res;
									}
								},
								{
									title : '操作',
									field : 't_certification_type',
									align : 'center',
									formatter : function(value, row, index) {

										var obj = '';

										if (row.t_disable == 0) {

											if (row.t_certification_type == 0) {
												obj += '<a class="btn btn-default" href="javascript:on_click_verify_success('
														+ row.t_id
														+ ')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">审核通过</a>&nbsp;&nbsp;';
												obj += '<a class="btn btn-default" href="javascript:on_click_verify_fail('
														+ row.t_id
														+ ')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">审核失败</a>&nbsp;&nbsp;';
											}

											obj += '<a class="btn btn-default" href="javascript:on_click_disable('
													+ row.t_id
													+ ')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">禁用</a>&nbsp;&nbsp;';
										}

										return obj;
									}
								} ]
					});
}

/** 点击预览头像 */
function on_click_preview(url) {
	$("#hand_img").attr("src", url);
	$("#img_preview").modal('show');
}

/** 点击加载表格 */
function on_click_search() {
	$("#utable").bootstrapTable('destroy');
	ajax_load_table();
}

function on_click_disable(id) {
	var r = confirm("确定要禁用此用户吗？");
	if (r) {
		$.ajax({
			type : 'POST',
			url : path + 'admin/updateDisable.htm',
			data : {
				t_id : id
			},
			dataType : 'json',
			success : function(data) {
				if (data.m_istatus == 1) {
					on_click_search();
				} else {
					window.location.href = path + '/error.html';
				}
			}
		});
	}
}

/** 审核成功 */
function on_click_verify_success(id) {
	if (confirm(" 确定审核通过吗？")) {
		$.ajax({
			type : 'POST',
			url : path + 'admin/verifySuccess.htm',
			data : {
				t_id : id
			},
			dataType : 'json',
			success : function(data) {
				if (data.m_istatus == 1) {
					on_click_search();
				} else {
					window.location.href = path + '/error.html';
				}
			}
		});
	}
}

/** 审核成功!* */
function on_click_verify_fail(id) {
	if (confirm(" 确定审核失败吗？")) {
		$.ajax({
			type : 'POST',
			url : path + 'admin/verifyFail.htm',
			data : {
				t_id : id
			},
			dataType : 'json',
			success : function(data) {
				if (data.m_istatus == 1) {
					on_click_search();
				} else {
					window.location.href = path + '/error.html';
				}
			}
		});
	}
}
