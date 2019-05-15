$(function() {
	// 生成用户数据
	var url = path + "/admin/getDivideIntoList.htm";
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
								Name : $('#search_name').val(),
								Tel : $('#search_tel').val()
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
									title : '项目类别',
									field : 't_project_type',
									align : 'center',
									formatter : function(value) {
										if (value == 0) {
											return '平台分成比例';
										} else if (value == 1) {
											return '一级主播推广分成';
										} else if (value == 2) {
											return '二级主播推广分成';
										} else if (value == 3) {
											return '一级用户推广分成';
										} else if (value == 4) {
											return '二级用户推广分成';
										} else if (value == 5) {
											return '视频聊天';
										} else if (value == 6) {
											return '文本聊天';
										} else if (value == 7) {
											return '查看手机号';
										} else if (value == 8) {
											return '查看微信号';
										} else if (value == 9) {
											return '查看私密照片';
										} else if (value == 10) {
											return '查看私密视频';
										} else if (value == 11) {
											return '认证微信号';
										}
									}
								},
								{
									title : '分配比例',
									align : 'center',
									field : 't_extract_ratio',
									formatter : function(value, row) {
										if (row.t_project_type < 5) {
											return value + "%";
										}
										return value;
									}
								},
								{
									title : '创建时间',
									align : 'center',
									field : 't_create_time'
								},
								{
									title : '操作',
									field : '',
									align : 'center',
									formatter : function(value, row, index) {
										var res = "<a class='btn btn-default' href='javascript:on_click_open_update_model("
												+ row.t_id
												+ ","+row.t_project_type+");' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>修改</a>&nbsp;&nbsp;";
										return res;
									}
								} ]
					});

});

/**
 * 点击弹窗
 * 
 * @param id
 * @param nickName
 */
function on_click_open_update_model(obj, exType) {
	$("#t_id").val(obj);
	$('#myModal_update').empty();
	if (exType < 5) {
		var obj = '	<div style="height: 40px;">';
		obj += '<label class="col-md-2 control-label">分成比例:</label>';
		obj += '<div class="col-md-8">';
		obj += '<div class="input-group">';
		obj += '<span class="input-group-addon"> <i';
		obj += 'class="glyphicon glyphicon-grain"></i>';
		obj += '</span> <input type="text" id="t_extract_ratio" name="t_extract_ratio"';
		obj += 'class="form-control1"  placeholder="请输入分成比例">';
		obj += '</div>';
		obj += '</div>';
		obj += '<div class="col-sm-1">';
		obj += '<p class="help-block" style="color: red;">*</p>';
		obj += '</div>';
		obj += '</div>';
		obj += '</br>';
		$('#myModal_update').append(obj);
	} else {
		var obj = '	<div style="height: 40px;">';
		
		if(exType == 5){
			obj += '<label class="col-md-3 control-label">视频聊天收费:</label>';
		}else if(exType == 6){
			obj += '<label class="col-md-3 control-label">文本聊天收费:</label>';
		}else if(exType == 7){
			obj += '<label class="col-md-3 control-label">查看手机号收费:</label>';
		}else if(exType == 8){
			obj += '<label class="col-md-3 control-label">查看微信号收费:</label>';
		}else if(exType == 9){
			obj += '<label class="col-md-3 control-label">查看私密照片收费:</label>';
		}else if(exType == 10){
			obj += '<label class="col-md-3 control-label">查看私密视频收费:</label>';
		}else if(exType == 11){
			obj += '<label class="col-md-3 control-label">认证微信号:</label>';
		}
		
		obj += '<div class="col-md-8">';
		obj += '<div class="input-group">';
		obj += '<span class="input-group-addon"> <i';
		obj += 'class="glyphicon glyphicon-grain"></i>';
		obj += '</span> <input type="text" id="t_extract_ratio" name="t_extract_ratio"';
		obj += 'class="form-control1"  placeholder="请输入收费值,多个值请在英文输入法下已逗号分割.">';
		obj += '</div>';
		obj += '</div>';
		obj += '<div class="col-sm-1">';
		obj += '<p class="help-block" style="color: red;">*</p>';
		obj += '</div>';
		obj += '</div>';
		obj += '</br>';
		
		$('#myModal_update').append(obj);
	}

	$("#myModal").modal('show');
}

/**
 * 点击提交新增或修改
 */
function on_click_submit() {
	$.ajax({
		type : 'POST',
		url : path + '/admin/updateSystem.htm',
		data : {
			t_id : $("#t_id").val(),
			t_extract_ratio : $("#t_extract_ratio").val()
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
