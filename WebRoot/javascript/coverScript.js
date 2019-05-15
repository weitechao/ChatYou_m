$(function() {
	ajax_load_table();
});

function ajax_load_table() {
	// 生成用户数据
	var url = path + "/admin/getCoverExamineList.htm";
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
						pageSize : 5,// 每页的记录行数（*）
						sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
						// 【设置列】
						columns : [
								{
									title : 'idcard',
									align : 'center',
									field : 't_idcard'
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
									title : '封面1',
									align : 'center',
									width : '350',
									formatter : function(value, row, index) {
										var data = row.img_0;
										console.log(data);
										return '<img src="'
												+ data.t_img_url
												+ '" style="cursor:pointer" title="点击预览" width="150" height="150" onclick="on_click_preview(\''
												+ data.t_img_url + '\','
												+ data.t_first + ','
												+ data.t_id + ','
												+ data.t_is_examine + ','
												+ row.t_id + ');">';
									}
								},
								{
									title : '封面2',
									align : 'center',
									width : '350',
									formatter : function(value, row, index) {
										var img_1 = row.img_1;
										if (null != img_1 && "" != img_1) {
											console.log(img_1);
											return '<img src="'
													+ img_1.t_img_url
													+ '" style="cursor:pointer" title="点击预览" width="150" height="150" onclick="on_click_preview(\''
													+ img_1.t_img_url + '\','
													+ img_1.t_first + ','
													+ img_1.t_id + ','
													+ img_1.t_is_examine + ','
													+ row.t_id + ');">';
										}
									}
								},
								{
									title : '封面3',
									align : 'center',
									width : '350',
									formatter : function(value, row, index) {
										var img_2 = row.img_2;
										if (null != img_2 && "" != img_2) {
											console.log(img_2);
											return '<img src="'
													+ img_2.t_img_url
													+ '" style="cursor:pointer" title="点击预览" width="150" height="150" onclick="on_click_preview(\''
													+ img_2.t_img_url + '\','
													+ img_2.t_first + ','
													+ img_2.t_id + ','
													+ img_2.t_is_examine + ','
													+ row.t_id + ');">';
										}
									}
								},
								{
									title : '封面4',
									align : 'center',
									width : '350',
									formatter : function(value, row, index) {
										var img_3 = row.img_3;
										if (null != img_3 && "" != img_3) {
											console.log(img_3);
											return '<img src="'
													+ img_3.t_img_url
													+ '" style="cursor:pointer" title="点击预览" width="150" height="150" onclick="on_click_preview(\''
													+ img_3.t_img_url + '\','
													+ img_3.t_first + ','
													+ img_3.t_id + ','
													+ img_3.t_is_examine + ','
													+ row.t_id + ');">';
										}
									}
								}

						]
					});
}

/** 点击预览头像 */
function on_click_preview(url, t_first, t_id, t_is_examine, userId) {

	$("#hand_img").attr("src", url);
	var html = '';
	if (t_first == 1) {
		html = html
				+ '<button type="button" class="btn btn-default" onclick="on_click_first('
				+ t_id + ',' + userId + ')">';
		html = html
				+ '<span class="glyphicon glyphicon-fast-backward"></span>&nbsp;设为主封面';
		html = html + '</button>';
	}
	/** 封面已经通过了审核 */
	if (t_is_examine == 1) {
		html = html
				+ '<button type="button" class="btn btn-default" onclick="on_click_delCover('
				+ t_id + ')" >';
		html = html
				+ '<span class="glyphicon glyphicon-alert"></span>&nbsp;封面异常';
		html = html + '</button>';
	} else {
		/** 还未进行审核 */
		html = html
				+ '<button type="button" class="btn btn-default" onclick="on_click_throughAudit('
				+ t_id + ')">';
		html = html + '<span class="glyphicon glyphicon-ok"></span>&nbsp;审核通过';
		html = html + '</button>';
		html = html
				+ '<button type="button" class="btn btn-default" onclick="on_click_delCover('
				+ t_id + ')">';
		html = html
				+ '<span class="glyphicon glyphicon-alert"></span>&nbsp;封面异常';
		html = html + '</button>';
	}
	/** 关闭按钮 */
	html = html
			+ '<button type="button" class="btn btn-default" data-dismiss="modal">';
	html = html + '<span class="glyphicon glyphicon-remove"></span>&nbsp;关闭';
	html = html + '</button>';

	$("#div_button").text("");
	$("#div_button").append(html);
	$("#img_preview").modal('show');
}

/** 点击设置为第一张封面 */
function on_click_first(id, userId) {

	$.ajax({
		type : 'POST',
		url : path + '/admin/setUpFirst.htm',
		data : {
			t_id : id,
			t_user_id : userId
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#img_preview").modal('hide');
				$("#utable").bootstrapTable('refreshOptions', {
					page : 1
				});
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/** 点击通过审核 */
function on_click_throughAudit(id) {
	$.ajax({
		type : 'POST',
		url : path + '/admin/throughAudit.htm',
		data : {
			t_id : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#img_preview").modal('hide');
				$("#utable").bootstrapTable('refreshOptions', {
					page : 1
				});
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/** 审核失败 */
function on_click_delCover(id) {
	$.ajax({
		type : 'POST',
		url : path + '/admin/delCoverData.htm',
		data : {
			t_id : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#img_preview").modal('hide');
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
 * 点击操作
 */
function on_click_operation(id, state) {
	$("#del_id").val(id);
	$("#state").val(state);
	$("#myDel").modal('show');
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

/** 点击加载表格 */
function on_click_search() {
	$("#utable").bootstrapTable('destroy');
	ajax_load_table();
}