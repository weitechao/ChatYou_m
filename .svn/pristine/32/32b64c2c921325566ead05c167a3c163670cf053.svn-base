$(function() {
	ajax_load_table();
});

function ajax_load_table() {
	// 生成用户数据
	var url = path + "/admin/getDisableList.htm";
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
								condition : $('#condition').val(),
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
									align : 'center',
									formatter : function(value, row, index) {
										return '<a href="javascript:on_click_open_user_model('
												+ row.t_id
												+ ');">'
												+ value
												+ '</a>'
									}
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
									title : '年龄',
									align : 'center',
									field : 't_age',
								},
								{
									title : '手机号',
									align : 'center',
									field : 't_phone'
								},
								{
									title : '角色',
									field : 't_role',
									align : 'center',
									formatter : function(value) {
										return value == 0 ? '普通用户' : '主播用户';
									}
								},
								{
									title : '推荐人',
									field : 'refeName',
									align : 'center'
								},
								{
									title : '注册时间',
									field : 't_create_time',
									align : 'center'
								},
								{
									title : '状态',
									field : 't_disable',
									align : 'center',
									formatter : function(value, row, index) {
										var res = '';
										if (value == 0) {
											res = '正常';
										}
										if (value == 1) {
											res = '已封号';
										}
										if (value == 2) {
											res = '已禁用';
										}
										return res;
									}
								},
								{
									title : '操作',
									field : '',
									align : 'center',
									formatter : function(row, index) {
										var res = '';
										var nickName = "'" + index.t_nickName
												+ "'";
										if (index.t_disable == 0) {
											res = '<a href="javascript:on_click_open_update_model('
													+ index.t_id
													+ ','
													+ nickName
													+ ');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">封号</a>&nbsp;&nbsp;';
											res = res
													+ '<a href="javascript:on_click_operation('
													+ index.t_id
													+ ',2)" class="btn btn-default" style="height: 25px;line-height: 0.5;color:red;background-color: #87CEFA;">禁用</a>';
										}
										if (index.t_disable == 1) {
											res = '<a href="javascript:on_click_unlock('
													+ index.t_id
													+ ');" class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">解封</a>&nbsp;&nbsp;';
											res = res
													+ '<a href="javascript:on_click_operation('
													+ index.t_id
													+ ',2)" class="btn btn-default" style="height: 25px;line-height: 0.5;color:red;background-color: #87CEFA;">禁用</a>';
										}
										if (index.t_disable == 2) {
											res = '<a href="javascript:on_click_operation('
													+ index.t_id
													+ ',0)" class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">启用</a>&nbsp;&nbsp;';
											res = res
													+ '<a href="javascript:on_click_open_update_model('
													+ index.t_id
													+ ','
													+ nickName
													+ ' );" class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">封号</a>';
										}
										return res;
									}
								} ]
					});
}

// 点击搜索按钮
function on_click_search() {
	$("#utable").bootstrapTable('destroy');
	ajax_load_table();
}
/**
 * 点击封号
 * 
 * @param id
 * @param nickName
 */
function on_click_open_update_model(id, nickName) {
	$("#t_id").val(id);
	$("#nick_name").val(nickName);
	$("#myModal").modal('show');
}
/**
 * 点击操作
 */
function on_click_operation(id, state) {
	$("#del_id").val(id);
	$("#state").val(state);
	$("#myDel").modal('show');
}

/**
 * 点击启用或者禁用用户
 */
function on_click_change_res() {
	$.ajax({
		type : 'POST',
		url : path + '/admin/enableOrDisable.htm',
		data : {
			t_id : $("#del_id").val(),
			state : $("#state").val()
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
function on_click_freeze_ones() {
	$.ajax({
		type : 'POST',
		url : path + '/admin/freezeOnesUser.htm',
		data : {
			t_id : $("#t_id").val(),
			freeze_time : $("#freeze_time").val()
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

/**
 * 手动解封
 * 
 * @param id
 */
function on_click_unlock(id) {
	$.ajax({
		type : 'POST',
		url : path + '/admin/unlock.htm',
		data : {
			t_id : id
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

/**
 * 点击弹出用户详情
 * 
 * @param id
 * @param nickName
 */
function on_click_open_user_model(params) {
	$.ajax({
		type : 'POST',
		url : path + '/admin/getUserById.htm',
		data : {
			t_id : params
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#t_nickName").val(data.m_object.t_nickName);
				$("#t_phone").val(data.m_object.t_phone);
				$("#t_sex").val(data.m_object.t_sex);
				$("#t_age").val(data.m_object.t_age);
				$("#t_height").val(data.m_object.t_height);
				$("#t_weight").val(data.m_object.t_weight);
				$("#t_constellation").val(data.m_object.t_constellation);
				$("#t_city").val(data.m_object.t_city);
				$("#t_vocation").val(data.m_object.t_vocation);
				$("#t_role").val(data.m_object.t_role);
				$("#t_synopsis").val(data.m_object.t_synopsis);
				$("#t_autograph").val(data.m_object.t_autograph);
				$("#userModal").modal('show');
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});

}