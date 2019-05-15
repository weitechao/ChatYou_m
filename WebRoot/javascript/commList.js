$(function() {
	// 生成用户数据
	var url = path + "/admin/getCommList.htm";
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
									title : 'idcard',
									align : 'center',
									field : 't_idcard'
								},
								{
									title : '评论人',
									align : 'center',
									field : 't_nickName'
								},
								{
									title : '动态内容',
									align : 'center',
									field : 't_content',
									formatter : function(value) {

										return value.length < 20 ? value
												: value.substring(0, 20)
														+ '...';
									}
								},
								{
									title : '评论内容',
									align : 'center',
									field : 't_comment'
								},
								{
									title : '评论时间',
									align : 'center',
									field : 't_create_time'
								},
								{
									title : '操作',
									field : '',
									align : 'center',
									formatter : function(value, row, index) {
										var res = '';
										var valueStr = JSON.stringify(row);
										res = res
												+ "<a href='javascript:on_click_via_examine("
												+ row.t_id
												+ ");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>通过审核</a>&nbsp;&nbsp;";
										res = res
												+ '<a href="javascript:on_click_reject_comm('
												+ row.t_id
												+ ');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">驳回评论</a>';
										return res;
									}
								} ]
					});

});

/**
 * 通过审核
 * 
 * @param id
 * @returns
 */
function on_click_via_examine(id) {
	$.ajax({
		url : path + '/admin/viaExamine.htm',
		data : {
			comId : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				$('#my_comfirm').empty();
				$('#my_comfirm').append(data.m_strMessage);
				$("#myComfirm").modal('show');
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
 * 驳回评论
 * 
 * @param id
 * @returns
 */
function on_click_reject_comm(id) {
	$.ajax({
		url : path + '/admin/rejectComm.htm',
		data : {
			comId : id,
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				$('#my_comfirm').empty();
				$('#my_comfirm').append(data.m_strMessage);
				$("#myComfirm").modal('show');
				$("#utable").bootstrapTable('refreshOptions', {
					page : 1
				});
			} else {
				window.location.href = path + '/error.html';
			}
		}
	})
}
