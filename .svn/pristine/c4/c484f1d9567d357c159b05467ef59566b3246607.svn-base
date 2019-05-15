$(function() {
	ajax_table();
});

function ajax_table(type) {
	// 生成用户数据
	var url = path + "/admin/getBannedList.htm";
	$('#utable').bootstrapTable(
					{
						url : url,
						method : 'post',
						contentType : "application/x-www-form-urlencoded",
						queryParamsType : '',
						queryParams : function(params){
							return {
								pageSize : params.limit,
								page : params.pageNumber
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
									title : '编号',
									field : 't_id',
									align : 'center'
								},
								{
									title : '封号次数',
									align : 'center',
									field : 't_count'
								},
								{
									title : '封号时间',
									align : 'center',
									field : 't_hours',
									formatter:function(value){
										if(value == -1){
											return '永久封号';
										}else{
											return value+'小时';
										}
									}
								},
								{
									title : '操作',
									align : 'center',
									formatter : function(value, row, index) {
										var valueStr = JSON.stringify(row);
										var str = "<a href = 'javascript:on_click_open_update_model("
												+ valueStr
												+ ")' class = 'btn btn-default' >修改</a>&nbsp;&nbsp;&nbsp;&nbsp;";

										str = str
												+ "<a href = 'javascript:on_click_del("
												+ row.t_id
												+ ")' class = 'btn btn-default' >删除</a>&nbsp;&nbsp;&nbsp;&nbsp;";

										 
										return str;
									}
								}

						]
					});
}

/**
 * 点击弹窗
 * 
 * @param id
 * @param nickName
 */
function on_click_open_update_model(obj) {
	if (null == obj) {
		$("#t_id").val('');
		$("#t_count").val('');
		$("#t_hours").val('');
	} else if (null != obj) {
		$("#t_id").val(obj.t_id);
		$("#t_count").val(obj.t_count);
		$("#t_hours").val(obj.t_hours);
	}
	$("#myModal").modal('show');
}

/**
 * 点击提交新增或修改
 */
function on_click_submit() {
	$.ajax({
		type : 'POST',
		url : path + 'admin/saveOrUpdateBanned.htm',
		data : {
			t_id : $('#t_id').val(),
			t_count:$('#t_count').val(),
			t_hours : $('#t_hours').val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				$("#utable").bootstrapTable('refreshOptions', {page : 1});
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}

// 删除信息
function on_click_del(id) {
	$.ajax({
		type : 'POST',
		url : path + '/admin/delBannedSetUp.htm',
		data : {
			t_id : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#utable").bootstrapTable('refreshOptions', {page : 1
				});
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}


 
 

 
