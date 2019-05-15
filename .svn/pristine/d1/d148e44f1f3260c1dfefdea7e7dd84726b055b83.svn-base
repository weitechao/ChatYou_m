$(function() {
	$("#beginTime").datepicker();
	$("#endTime").datepicker();
	$("#comment_begin_time").datepicker();
	$("#comment_end_time").datepicker();
	ajax_load_table();

});

// 加载表格
function ajax_load_table() {
	// 生成用户数据
	$('#utable')
			.bootstrapTable(
					{
						url : path + "admin/getDynamicList.htm",
						method : 'post',
						contentType : "application/x-www-form-urlencoded",
						queryParamsType : '',
						queryParams : function(params) {
							return {
								pageSize : params.limit,
								page : params.pageNumber,
								condition : $("#condition").val(),
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
									title : '动态内容',
									align : 'center',
									field : 't_content',
									formatter : function(value) {
										if (value.length < 20)
											return value;
										else
											return value.substring(0, 20)
													+ "....";
									}
								},
								{
									title : '点赞数量',
									align : 'center',
									field : 'praiseCount'
								},
								{
									title : '评论数量',
									align : 'center',
									field : 'commentCount'
								},
								{
									title : '是否删除',
									field : 't_is_del',
									align : 'center',
									formatter : function(value) {
										if (value == 0)
											return '否';
										else
											return '是';
									}
								},
								{
									title : '是否审核',
									field : 't_auditing_type',
									align : 'center',
									formatter : function(value) {
										if (value == 0)
											return '待审核';
										else if (value == 1)
											return '审核通过';
										else
											return '审核失败';
									}
								},
								{
									title : '操作',
									field : '',
									align : 'center',
									formatter : function(value, row, index) {

										var res = '<a class="btn btn-default" href="javascript:on_click_loading_dynamic_details(\''
												+ row.t_content
												+ '\')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">文本内容</a>&nbsp;&nbsp;';
										res += '<a class="btn btn-default" href="javascript:on_click_cover_modal('
												+ row.t_id
												+ ')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">照片/视频</a>&nbsp;&nbsp;';

										if (row.t_auditing_type == 0) {
											res += '<a class="btn btn-default" href="javascript:on_click_auditing_dynamic('
													+ row.t_id
													+ ',1)" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">审核通过</a>&nbsp;&nbsp;';
											res += '<a class="btn btn-default" href="javascript:on_click_auditing_dynamic('
													+ row.t_id
													+ ',2)" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">审核失败</a>&nbsp;&nbsp;';
										} else if (row.t_auditing_type == 1) {
											res += '<a class="btn btn-default" href="javascript:on_click_del_dynamic('
													+ row.t_id
													+ ')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">删除动态</a>&nbsp;&nbsp;';
										}
										// 评论详情
										res += '<a class="btn btn-default" href="javascript:on_click_comment_list('
												+ row.t_id
												+ ')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">评论列表</a>&nbsp;&nbsp;';

										return res;
									}
								} ]
					});
}

/** 点击查看动态文件 Id:动态编号 */
function on_click_cover_modal(id) {
	$
			.ajax({
				type : 'POST',
				url : path + 'admin/getDynamicFile.htm',
				data : {
					dynamicId : id
				},
				dataType : 'json',
				success : function(data) {
					console.log(data);
					if (data.m_istatus == 1) {
						var html = '';
						html = html + '<div class="show-reel">';
						$
								.each(
										data.m_object,
										function(index, value) {
											console.log(value);
											// 第二次循环
											html = html
													+ '<div class="agile-gallery-grid">';
											html = html
													+ '<div class="agile-gallery" style="width: 200px;float: left;margin-left: 10px;margin-top: 25px;">';
											if (this.t_file_type == 0) {
												html = html + '<img src="'
														+ this.t_file_url
														+ '" width="100%"  />';
											} else {
												html = html
														+ '<video width="100%" controls autoplay><source src="'
														+ this.t_file_url
														+ '"  type="video/mp4"  controls="controls"></video>';
											}
											html = html
													+ '<div class="agileits-caption"><span style="float: left;margin-left: 10%;margin-top: 5.5%;font-size: 1em;color: #ffffff">收费:'+this.t_gold+'</span>';
											html = html
													+ '<a href="javascript:on_click_del_file('
													+ this.t_id
													+ ');" style="float: right;margin-right: 10%;margin-top: 5.5%;font-size: 1em;color: #ffffff">删除文件</a>';
											html = html + '</div>';
											html = html + '</div>';
											html = html + '</div>';
										});
						html = html + '</div>';
						console.log(html);
						$("#cover_modal_div").text("");
						$("#cover_modal_div").append(html);
						$("#cover_modal").modal('show');
					} else {
						window.location.href = path + '/error.html';
					}
				}
			});
}

function on_click_loading_dynamic_details(content) {
	var html = '<div>' + content + '</div>';
	$("#dynamicDetails").text("");
	$("#dynamicDetails").append(html);
	$("#dynamic_content").modal('show');
}

/**
 * 点击审核动态
 * 
 * @param id
 * @param type
 * @returns
 */
function on_click_auditing_dynamic(id, type) {
	$.ajax({
		type : 'POST',
		url : path + 'admin/examineDynamic.htm',
		data : {
			dynamicId : id,
			type : type
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
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

/**
 * 点击删除动态
 * 
 * @param id
 * @returns
 */
function on_click_del_dynamic(id) {
	$.ajax({
		type : 'POST',
		url : path + 'admin/delDynamic.htm',
		data : {
			dynamicId : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				if (data.m_istatus == 1) {
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$('#myComfirm').modal('show');
					$("#utable").bootstrapTable('refreshOptions', {
						page : 1
					});
				} else {
					window.location.href = path + '/error.html';
				}
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/** 加载已审核的评论列表* */
function on_click_comment_list(id) {
	$("#commentTable").bootstrapTable('destroy');
	// 生成用户数据
	$('#commentTable')
			.bootstrapTable(
					{
						url : path + "admin/getExamineComment.htm",
						method : 'post',
						contentType : "application/x-www-form-urlencoded",
						queryParamsType : '',
						queryParams : function(params) {
							return {
								pageSize : params.limit,
								page : params.pageNumber,
								dynamicId : id,
								beginTime : $("#comment_begin_time").val(),
								endTime : $("#comment_end_time").val()
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
									title : '评论人',
									field : 't_nickName',
									align : 'center'
								},
								{
									title : '评论内容',
									align : 'center',
									field : 't_comment',
									formatter : function(value) {
										if (value.length < 20)
											return value;
										else
											return value.substring(0, 20)
													+ "....";
									}
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

										return res = '<a class="btn btn-default" href="javascript:on_click_del_comment('
												+ row.t_id
												+ ')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">删除评论</a>&nbsp;&nbsp;';
									}
								} ]
					});
	$('#dynamic_comment_list').modal('show');
}

/**
 * 删除评论
 * 
 * @param id
 * @returns
 */
function on_click_del_comment(id) {
	$.ajax({
		type : 'POST',
		url : path + 'admin/delComment.htm',
		data : {
			comId : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				if (data.m_istatus == 1) {
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$('#myComfirm').modal('show');
					$("#commentTable").bootstrapTable('refreshOptions', {
						page : 1
					});
				} else {
					window.location.href = path + '/error.html';
				}
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}
/** 根据编号删除文件*/
function on_click_del_file(id){
	$.ajax({
		type : 'POST',
		url : path + 'admin/setFileAbnormal.htm',
		data : {
			fileId : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				if (data.m_istatus == 1) {
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$('#myComfirm').modal('show');
					$("#commentTable").bootstrapTable('refreshOptions', {
						page : 1
					});
				} else {
					window.location.href = path + '/error.html';
				}
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});
}