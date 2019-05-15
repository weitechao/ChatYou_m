$(function() {
	ajax_table();
});




function ajax_table(type) {
	// 生成用户数据
	var url = path + "/admin/getRoleList.htm";
	$('#utable').bootstrapTable({
						url : url,
						method : 'post',
						contentType : "application/x-www-form-urlencoded",
						queryParamsType : '',
						queryParams : function (params) {
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
									title : '角色名称',
									field : 't_role_name',
									align : 'center'
								},
								{
									title : '状态',
									align : 'center',
									field : 't_enable',
									formatter : function(value) {
										if (value == 0) {
											return "<span style='color:#00FF00;'>已启用</span>";
										} else if (value == 1) {
											return "<span style='color:red;'>已禁用</span>";
										}
									}
								},
								{
									title : '创建时间',
									align : 'center',
									field : 't_create_time'
								},
								{
									title : '操作',
									align : 'center',
									formatter : function(value, row, index) {
										var valueStr = JSON.stringify(row);
										
										var str = '';
										console.log(row.t_id);
										if (row.t_id !=1){
											console.log('----');
											str = str + "<a href = 'javascript:on_click_open_update_model("
											+ valueStr
											+ ")' class = 'btn btn-default' >修改</a>&nbsp;&nbsp;&nbsp;&nbsp;";
											
											str = str
											+ "<a href = 'javascript:on_click_del("
											+ row.t_id
											+ ")' class = 'btn btn-default' >删除</a>&nbsp;&nbsp;&nbsp;&nbsp;"
										}

									

										str = str
												+ "<a href = 'javascript:on_click_load_treeview("
												+ row.t_id
												+ ")' class = 'btn btn-default' >分配权限</a>"
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
		$("#t_role_name").val('');
		$("#t_enable").val(0);
	} else if (null != obj) {
		$("#t_id").val(obj.t_id);
		$("#t_role_name").val(obj.t_role_name);
		$("#t_enable").val(obj.t_enable);
	}
	$("#myModal").modal('show');
}

/**
 * 点击提交新增或修改
 */
function on_click_submit() {
	$.ajax({
		type : 'POST',
		url : path + '/admin/saveRole.htm',
		data : {
			t_id : $("#t_id").val(),
			t_role_name : $("#t_role_name").val(),
			t_enable : $("#t_enable").val()
		},
		dataType : 'json',
		success : function(data) {
			console.log(data);
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

// 删除信息
function on_click_del(id) {
	$.ajax({
		type : 'POST',
		url : path + '/admin/delRole.htm',
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


function on_click_load_treeview(id) {
	$('#t_role_id').val(id);
	$.ajax({
		type : 'POST',
		url : path + '/admin/getMenuTreeList.htm',
		data : {
			roleId : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				console.log(data.m_object);
				var $checkableTree = $('#treeview-checkable').treeview(
						{
							data : data.m_object,
							showIcon : false,
							showCheckbox : true,
							onNodeChecked : function(event, node) {
								console.log(node);
								var selectNodes = getChildNodeIdArr(node); // 获取所有子节点
								if (selectNodes) { // 子节点不为空，则选中所有子节点
									$('#treeview-checkable').treeview('checkNode',[ selectNodes, {silent : true} ]);
								}
								var parentNode = $("#treeview-checkable").treeview("getNode", node.parentId);
								setParentNodeCheck(node);
							},
							onNodeUnchecked : function(event, node) {
								var selectNodes = getChildNodeIdArr(node); // 获取所有子节点
								if (selectNodes) { // 子节点不为空，则取消选中所有子节点
									$('#treeview-checkable').treeview('uncheckNode',
											[ selectNodes, {
												silent : true
											} ]);
								}
							}
						});

				$('#treeview-checkable').treeview('collapseAll', {
					silent : true
				});
			} else {
				window.location.href = path + '/error.html';
			}
		}
	});

	$("#treeView").modal('show');
}

/**点击获取选中的值**/
function on_click_confirm(){
	var checked=$('#treeview-checkable').treeview('getChecked');
	var meunId = '';
	$.each(checked, function(){
		meunId = meunId+this.t_id+",";
	});
	$.ajax({
		type : 'POST',
		url : path + '/admin/saveAuthority.htm',
		data : {
			t_role_id : $('#t_role_id').val(),
			meunIds:meunId
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#treeView").modal('hide');
				$("#utable").bootstrapTable('refreshOptions', {	page : 1});
			} else {
				window.location.href = path + 'error.html';
			}
		}
	});
}

function getChildNodeIdArr(node) {
	var ts = [];
	if (node.nodes) {
		for (x in node.nodes) {
			ts.push(node.nodes[x].nodeId);
			if (node.nodes[x].nodes) {
				var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
				for (j in getNodeDieDai) {
					ts.push(getNodeDieDai[j]);
				}
			}
		}
	} else {
		ts.push(node.nodeId);
	}
	return ts;
}

function setParentNodeCheck(node) {
	var parentNode = $("#treeview-checkable")
			.treeview("getNode", node.parentId);
	if (parentNode.nodes) {
		var checkedCount = 0;
		for (x in parentNode.nodes) {
			if (parentNode.nodes[x].state.checked) {
				checkedCount++;
			} else {
				break;
			}
		}
		if (checkedCount === parentNode.nodes.length) {
			$("#treeview-checkable").treeview("checkNode", parentNode.nodeId);
			setParentNodeCheck(parentNode);
		}
	}
}
