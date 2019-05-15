$(function() {
	ajax_load_table();
});

function ajax_load_table() {
	// 生成用户数据
	var url = path + "admin/getGuildList.htm";
	$('#utable').bootstrapTable({
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
									title : '昵称',
									field : 't_nickName',
									align : 'center'
								},
								{
									title : '管理员',
									align : 'center',
									field : 't_admin_name',
									formatter:function(value,row,index){
										return '<a href="javascript:on_load_admin_data('+row.t_id+');">'+value+'</a>'
									}
								},
								{
									title : '联系电话',
									align : 'center',
									field : 't_admin_phone'
								},
								{
									title : '公会名称',
									align : 'center',
									field : 't_guild_name',
								},
								{
									title : '申请主播数',
									align : 'center',
									field : 't_anchor_number'
								},
								{
									title : '实际主播数',
									align : 'center',
									field : 'anchorNumber'
								},
								{
									title : '提成费率(%)',
									align : 'center',
									field : 't_extract'
								},
								{
									title : '状态',
									field : 't_examine',
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
											res = '已失效';
										}
										return res;
									}
								},
								{
									title : '申请时间',
									field : 't_create_time',
									align : 'center'
								},
								{
									title : '操作',
									field : '',
									align : 'center',
									formatter : function(value, row, index) {
										var valueStr = JSON.stringify(row);
										if (row.t_examine == 0) {
											return "<a href='javascript:on_load_examineModal("
													+ valueStr
													+ ");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>通过审核</a>";
										} else if (row.t_examine == 1) {
											var res = "<a href='javascript:on_load_examineModal("
													+ valueStr
													+ ");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;";
											res += '<a href="javascript:on_load_anchor_list('
													+ row.t_id
													+ ',2);"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">旗下主播</a>&nbsp;&nbsp;&nbsp;&nbsp;';
											return res += '<a href="javascript:guild_lower_frame('
													+ row.t_id
													+ ');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">删除</a>';
										} else if (row.t_examine == 2) {
											return '<a href="javascript:on_enable_guild('
													+ row.t_id
													+ ');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">启用</a>';
										}
									}
								} ]
					});
}


/** 点击加载表格 */
function on_click_search() {
	$("#utable").bootstrapTable('destroy');
	ajax_load_table();
}
/** 加载公会弹窗 */
function on_load_examineModal(obj) {
	console.log(obj);
	$('#t_ex_id').val(obj.t_id);
	$('#t_guild_name').val(obj.t_guild_name);
	$('#t_admin_name').val(obj.t_admin_name);
	$('#t_admin_phone').val(obj.t_admin_phone);
	if (obj.t_extract == 0 || obj.t_extract == null) {
		$('#t_extract').val(10);
	} else {
		$('#t_extract').val(obj.t_extract);
	}
	$('#examineModal').modal('show');
}
/* 修改公会信息 */
function on_modal_submit() {

	if (null == $("#t_guild_name").val() || '' == $("#t_guild_name").val()) {
		$("#t_guild_name").css({
			"border-color" : "red"
		});
		$('#t_guild_name_validate').empty();
		$('#t_guild_name_validate')
				.append(
						'<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		return;
	}
	if (null == $("#t_admin_name").val() || '' == $("#t_admin_name").val()) {
		$("#t_admin_name").css({
			"border-color" : "red"
		});
		$('#t_admin_name_validate').empty();
		$('#t_admin_name_validate')
				.append(
						'<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		return;
	}
	if (null == $("#t_admin_phone").val() || '' == $("#t_admin_phone").val()) {
		$("#t_admin_phone").css({
			"border-color" : "red"
		});
		$('#t_admin_phone_validate').empty();
		$('#t_admin_phone_validate')
				.append(
						'<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		return;
	}
	if (null == $("#t_extract").val() || '' == $("#t_extract").val()) {
		$("#t_extract").css({
			"border-color" : "red"
		});
		$('#t_extract_validate').empty();
		$('#t_extract_validate')
				.append(
						'<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		return;
	}

	$.ajax({
		type : 'POST',
		url : path + "admin/addOrUpdateGuild.htm",
		data : {
			t_id : $('#t_ex_id').val(),
			t_guild_name : $('#t_guild_name').val(),
			t_admin_name : $("#t_admin_name").val(),
			t_admin_phone : $("#t_admin_phone").val(),
			t_extract : $("#t_extract").val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus != 0) {
				$('#examineModal').modal('hide');
				$('#my_comfirm').empty();
				$('#my_comfirm').append(data.m_strMessage);
				$('#myComfirm').modal('show');
				$("#utable").bootstrapTable('refreshOptions', {
					page : 1
				});
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}
// 公会下架
function guild_lower_frame(id) {
	var r = confirm("确定要将此公会下架吗？");
	if (r) {
		$.ajax({
			url : path + 'admin/delGuild.htm',
			data : {
				t_guild_id : id
			},
			dataType : 'json',
			success : function(data) {
				if (data.m_istatus != 0) {
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$('#myComfirm').modal('show');
					$("#utable").bootstrapTable('refreshOptions', {
						page : 1
					});
				} else {
					window.location.href = path + '/error.html';
				}
			}
		});
	}
}

/** 其用户公会 */
function on_enable_guild(id) {
	var r = confirm("确定要将此公会启用吗？");
	if (r) {
		$.ajax({
			url : path + 'admin/guildEnableOrDisable.htm',
			data : {
				t_id : id
			},
			dataType : 'json',
			success : function(data) {
				if (data.m_istatus != 0) {
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$('#myComfirm').modal('show');
					$("#utable").bootstrapTable('refreshOptions', {
						page : 1
					});
				} else {
					window.location.href = path + '/error.html';
				}
			}
		});
	}
}

/** 加载主播列表 */
function on_load_anchor_list(id) {

	$('#t_guild_id').val(id);
	// 刷新table
	$("#anchor_table").bootstrapTable('destroy');
	$('#anchor_table')
			.bootstrapTable(
					{
						url : path + "admin/getGuildAnchorList.htm",
						method : 'post',
						contentType : "application/x-www-form-urlencoded",
						queryParamsType : '',
						queryParams : function(params) {
							// 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
							var temp = {
								pageSize : params.limit,// 页面大小
								page : params.pageNumber, // 页码
								guild_id : id
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
									title : '主播idCard',
									field : 't_idcard',
									align : 'center'
								},
								{
									title : '主播昵称',
									field : 't_nickName',
									align : 'center'
								},
								{
									title : '公会名称',
									align : 'center',
									field : 't_guild_name'
								},
								{
									title : '加入时间',
									align : 'center',
									field : 't_create_time'
								},
								{
									title : '操作',
									field : '',
									align : 'center',
									formatter : function(value, row, index) {
										return "<a href='javascript:on_signout_guild("
												+ row.t_id
												+ ");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>退出公会</a>";
									}
								} ]
					});

	$('#anchor_modal').modal('show');
}

/** 退出公会 */
function on_signout_guild(t_id) {

	var r = confirm("确定要将此用户设置为退出公会吗？");
	if (r) {
		$.ajax({
			url : path + 'admin/delAnchor.htm',
			data : {
				t_id : t_id
			},
			dataType : 'json',
			success : function(data) {
				if (data.m_istatus != 0) {
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$('#myComfirm').modal('show');
					$("#anchor_table").bootstrapTable('refreshOptions', {
						page : 1
					});
				} else {
					window.location.href = path + '/error.html';
				}
			}
		});
	}

}

/** 点击加入公会 */
function on_click_join() {
	$('#anchor_join_Modal').modal('show');
}

function on_anchor_join_submit() {
	$.ajax({
		url : path + 'admin/addGuild.htm',
		data : {
			t_guild_id : $('#t_guild_id').val(),
			idCards : $('#t_anchors').val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus != 0) {
				$('#anchor_join_Modal').modal('hide');
				$('#my_comfirm').empty();
				$('#my_comfirm').append(data.m_strMessage);
				$('#myComfirm').modal('show');
				$("#anchor_table").bootstrapTable('refreshOptions', {
					page : 1
				});
			} else {
				window.location.href = path + '/error.html';
			}
		}

	});
}

/**弹出公会管理者资料*/
function on_load_admin_data(id){
	$.ajax({
		url:path+'admin/loadGuidAdminData.htm',
		data:{t_id:id},
		dataType:'json',
		success:function(data){
			if (data.m_istatus != 0) {
				 $('#load_admin_name').val(data.m_object.t_admin_name);
				 $('#load_idcard').val(data.m_object.t_idcard);
				 $("#load_hand_img").attr("src",data.m_object.t_hand_img);
				$('#load_admin_data').modal('show');
			} else {
				window.location.href = path + '/error.html';
			}
		}
	})
}