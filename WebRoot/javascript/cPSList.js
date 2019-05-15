$(function() {
	ajax_load_table();
});

function ajax_load_table() {
	// 生成用户数据
	$('#utable').bootstrapTable({
						url : path + "admin/getCPSList.htm",
						method : 'post',
						contentType : "application/x-www-form-urlencoded",
						queryParamsType : '',
						queryParams : function(params){
							return {
								pageSize : params.limit,
								page : params.pageNumber,
								t_cps_name : $('#t_cps_name').val()
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
									title : '渠道名称',
									align : 'center',
									field : 't_cps_name',
									formatter:function(value,row){
										return '<a href="javascript:on_load_cps_admin_modal(\''+row.t_real_name+'\',\''+row.t_phone+'\')">'+value+'</a>';
									}
								},
								{
									title : '网址',
									align : 'center',
									field : 't_cps'
								},
								{
									title : '预估活跃(万)',
									align : 'center',
									field : 't_active_user',
								},
								{
									title : '提成比例(%)',
									align : 'center',
									field : 't_proportions'
								},
								{
									title : '总分成(元)',
									align : 'center',
									field : 'totalVale'
								},
								{
									title : '已结算(元)',
									align : 'center',
									field : 'already',
									formatter:function(value,row){
										return '<a href="javascript:setmm_table('+row.t_id+')">'+value+'</a>';
									}
								},
								{
									title : '剩余分成(元)',
									align : 'center',
									field : 'balance'
								},
								{
									title : '提现方式',
									align : 'center',
									field : 't_settlement_type',
									formatter:function(value,row){
										if(value == 0){
											return '<a href="javascript:on_load_cps_settlement_modal(\''+row.t_real_name+'\',\'支付宝\',\''+row.t_bank+'\')">支付宝</a>';
										}else if(value == 1){
											return '<a href="javascript:on_load_cps_settlement_modal(\''+row.t_real_name+'\',\'中国工商银行\',\''+row.t_bank+'\')">中国工商银行</a>';
										}else if(value == 2){
											return '<a href="javascript:on_load_cps_settlement_modal(\''+row.t_real_name+'\',\'中国农业银行\',\''+row.t_bank+'\')">中国农业银行</a>';
										}else if(value == 3){
											return '<a href="javascript:on_load_cps_settlement_modal(\''+row.t_real_name+'\',\'中国银行\',\''+row.t_bank+'\')">中国银行</a>';
										}else if(value == 4){
											return '<a href="javascript:on_load_cps_settlement_modal(\''+row.t_real_name+'\',\'中国建设银行\',\''+row.t_bank+'\')">中国建设银行</a>';
										}
									}
								},
								{
									title : '状态',
									field : 't_audit_status',
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
										if (row.t_audit_status == 0) {
											return "<a href='javascript:on_load_cps_update_modal("+ valueStr+ ");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>通过审核</a>";
										} else if (row.t_audit_status == 1) {
											var res = "<a href='javascript:on_load_cps_update_modal("+ valueStr+ ");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;";
											    res += "<a href='javascript:on_load_settlement_modal("+ valueStr+ ");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>结算</a>&nbsp;&nbsp;&nbsp;&nbsp;";
											    res += '<a href="javascript:on_load_anchor_list('+ row.t_id + ',2);"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">推广用户</a>&nbsp;&nbsp;&nbsp;&nbsp;';
										return  res += '<a href="javascript:on_examine_error_submit('+ row.t_id+ ');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">下架</a>';
										} else if (row.t_audit_status == 2) {
											return "<a href='javascript:on_load_cps_update_modal("+valueStr+ ");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>启用</a>";
											
										}
									}
								}]
					});
}

 

/** 点击加载cps管理用户*/
function on_load_cps_admin_modal(real_name,phone){
	$('#t_real_name').val(real_name);
	$('#t_phone').val(phone)
	$('#cps_admin_modal').modal('show');
}

function on_load_cps_settlement_modal(settlement_name,settlement_type,bank){
	$('#settlement_name').val(settlement_name);
	$('#t_settlement_type').val(settlement_type);
	$('#t_bank').val(bank);
	$('#cps_settlement_modal').modal('show');
}
/** 点击加载表格 */
function on_click_search() {
	$("#utable").bootstrapTable('destroy');
	ajax_load_table();
}
/** 加载审核CPS信息弹窗 */
function on_load_cps_update_modal(obj) {
	$('#t_cps_id').val(obj.t_id);
	$('#update_name').val(obj.t_real_name);
	$('#update_phone').val(obj.t_phone);
	$('#update_cps_name').val(obj.t_cps_name);
	$('#update_cps').val(obj.t_cps);
	$('#upate_settlement_type').val(obj.t_settlement_type);
	$('#update_bank').val(obj.t_bank);
	$('#update_active_user').val(obj.t_active_user);
	$('#update_proportions').val(obj.t_proportions);
	$('#cps_update_modal').modal('show');
}
/* 修改公会信息 */
function on_examine_success_submit() {
	
	if (null == $("#update_name").val() || '' == $("#update_name").val()) {
		$("#update_name").css({"border-color" : "red"});
		$('#update_name_validate').empty();
		$('#update_name_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		return;
	}
	if (null == $("#update_phone").val() || '' == $("#update_phone").val()) {
		$("#update_phone").css({"border-color" : "red"});
		$('#update_phone_validate').empty();
		$('#update_phone_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		return;
	}
	if (null == $("#update_cps_name").val() || '' == $("#update_cps_name").val()) {
		$("#update_cps_name").css({"border-color" : "red"});
		$('#update_cps_name_validate').empty();
		$('#update_cps_name_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		return;
	}
	if (null == $("#update_bank").val() || '' == $("#update_bank").val()) {
		$("#update_bank").css({"border-color" : "red"});
		$('#update_bank_validate').empty();
		$('#update_bank_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		return;
	}
	if (null == $("#update_active_user").val() || '' == $("#update_active_user").val()) {
		$("#update_active_user").css({"border-color" : "red"});
		$('#update_active_user_validate').empty();
		$('#update_active_user_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		return;
	}
	if (null == $("#update_proportions").val() || '' == $("#update_proportions").val()) {
		$("#update_proportions").css({"border-color" : "red"});
		$('#update_proportions_validate').empty();
		$('#update_proportions_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
		return;
	}
	$.ajax({
		type : 'POST',
		url : path + "admin/examineSuccess.htm",
		data : {
			t_id:$('#t_cps_id').val(),
			t_real_name:$('#update_name').val(),
			t_phone:$('#update_phone').val(),
			t_cps_name:$('#update_cps_name').val(),
			t_cps:$('#update_cps').val(),
			t_settlement_type:$('#upate_settlement_type').val(),
			t_bank:$('#update_bank').val(),
			t_active_user:$('#update_active_user').val(),
			t_proportions:$('#update_proportions').val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus != 0) {
				$('#cps_update_modal').modal('hide');
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

/**审核失败*/
function on_examine_error_submit(id){
	var parmId = id;
	if(parmId == 0 || '' == parmId || undefined == parmId){
		parmId = $('#t_cps_id').val();
	}
	$.ajax({
		type : 'POST',
		url : path + "admin/examineError.htm",
		data : {
			t_id:parmId
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus != 0) {
				$('#cps_update_modal').modal('hide');
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

/**加载结算弹窗**/
function on_load_settlement_modal(obj){
	$('#t_cps_settlement').val(obj.t_id);
	$('#settlement_real_name').val(obj.t_real_name);
	$('#settlement_type').val(obj.t_settlement_type);
	$('#settlement_bank').val(obj.t_bank);
	$('#settlement_amount').val(obj.balance);
	$('#settlement_order_no').val('');
	$('#settlement_modal').modal('show');
}

/**点击结算*/
function on_click_settlement(){
		//获取转账方式  如果非支付宝转账必须传递转账订单号
		if($('#settlement_type').val()!=0 && ($('#settlement_order_no').val()==null || '' ==$('#settlement_order_no').val())){
			$("#settlement_order_no").css({"border-color" : "red"});
			$('#settlement_order_no_validate').empty();
			$('#settlement_order_no_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove"></i>');
			return;
		}
		$.ajax({
			url:path+'admin/settlementCPS.htm',
		    data:{
		    	 t_cps_id:$('#t_cps_settlement').val(),
		    	 t_order_no:$('#settlement_order_no').val(),
		    	 t_settlement_type : $('#settlement_type').val()
		    },
		    dataType:'json',
		    success:function(data){
		    	$('#settlement_modal').modal('hide');
				$('#my_comfirm').empty();
				$('#my_comfirm').append(data.m_strMessage);
				$('#myComfirm').modal('show');
				$("#utable").bootstrapTable('refreshOptions', {
					page : 1
				});
		    }
		});
}

/** 加载 */
function on_load_anchor_list(id) {
	// 刷新table
	$("#user_table").bootstrapTable('destroy');
	$('#user_table').bootstrapTable({
						url : path + "admin/getContributionList.htm",
						method : 'post',
						contentType : "application/x-www-form-urlencoded",
						queryParamsType : '',
						queryParams : function(params) {
							// 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
							var temp = {
								pageSize : params.limit,// 页面大小
								page : params.pageNumber, // 页码
								t_cps_id : id
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
									title : '用户昵称',
									field : 't_nickName',
									align : 'center'
								},
								{
									title : '充值金额(元)',
									field : 'recharge_money',
									align : 'center'
								},
								{
									title : '贡献金额(元)',
									align : 'center',
									field : 't_devote_value'
								},
								{
									title : '分成比例(%)',
									align : 'center',
									field : 't_ratio'
								}]
					});

	$('#user_modal').modal('show');
}


function setmm_table(cpsId){
	// 刷新table
	$("#contribution_table").bootstrapTable('destroy');
	$('#contribution_table').bootstrapTable({
						url : path + "admin/getSetmmList.htm",
						method : 'post',
						contentType : "application/x-www-form-urlencoded",
						queryParamsType : '',
						queryParams : function(params) {
							// 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
							var temp = {
								pageSize : params.limit,// 页面大小
								page : params.pageNumber, // 页码
								t_cps_id : cpsId
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
									title : '用户昵称',
									field : 't_nickName',
									align : 'center'
								},
								{
									title : '充值金额(元)',
									field : 'recharge_money',
									align : 'center'
								},
								{
									title : '贡献金额(元)',
									align : 'center',
									field : 't_devote_value'
								},
								{
									title : '分成比例(%)',
									align : 'center',
									field : 't_ratio'
								}]
					});

	$('#contribution_modal').modal('show');
	
}

