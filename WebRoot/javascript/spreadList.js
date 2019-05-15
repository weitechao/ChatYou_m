$(function() {
	if($('#authority').val() == 2){
		alert('暂无此权限');
	}else{
		ajax_load_table();
	}
});

function ajax_load_table() {
	// 生成用户数据
	$('#utable').bootstrapTable({
						url : path + "admin/getSpreadChannelList.htm",
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
									title : '代理账号',
									field : 't_nickName',
									align : 'center'
								},
								{
									title : 'idCared',
									align : 'center',
									field : 't_idcard'
								},
								{
									title : '客户QQ',
									align : 'center',
									field : 't_qq',
								},
								{
									title : '客户微信',
									align : 'center',
									field : 't_weixin'
								},
								{
									title : '金币比例',
									align : 'center',
									field : 't_gold_proportions',
									formatter:function(value,row){
										return value+"%";
									}
								},
								{
									title : 'VIP比例',
									align : 'center',
									field : 't_vip_proportions',
									formatter:function(value,row){
										return value+"%";
									}
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
									title : '提现账号',
									align : 'center',
									field : 't_bank'
								},
								{
									title : '状态',
									align : 'center',
									field : 't_spread_type',
									formatter:function(value,row){
										if(value == 0){
											return '正常';
										}else{
											return '禁用';
										}
									}
								},
								{
									title : '操作',
									field : '',
									align : 'center',
									formatter : function(value, row, index) {
										var valueStr = JSON.stringify(row);
										if (row.t_spread_type == 0) {
											var res = "<a href='javascript:on_load_settlement_modal("+ row.t_user_id+ ");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>结算</a>&nbsp;&nbsp;&nbsp;&nbsp;";
											if($('#authority').val() == 0){
												res += '<a href="javascript:onload_next_lower_level('+ row.t_user_id + ');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">下级代理</a>&nbsp;&nbsp;&nbsp;&nbsp;';
											}
										return  res += '<a href="javascript:on_examine_error_submit('+ row.t_id+ ');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">删除</a>';
										} 
//										else if (row.t_spread_type == 1) {
//											return "<a href='javascript:on_click_start("+row.t_user_id+ ");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>启用</a>";
//											
//										}
									}
								}]
					});
}

function on_load_role(){
	$.ajax({
		type : 'POST',
		url : path + '/admin/getRoleEnableList.htm',
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#t_role_id").empty();
				$.each(data.m_object,function(){
					$("#t_role_id").append("<option value='"+this.t_id+"'>"+this.t_role_name+"</option>");
				})
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/** 添加推广渠道 */
function on_add_spread(){
	//获取当前用户是否还存在添加下级的权限
	$.ajax({
		type : 'POST',
		url : path + '/admin/getSpreadUserMesg.htm',
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				 if(data.m_object == 10021){
					 on_load_role();
					 $('#add_spread_channel').modal('show');
				 }else{
					$('#my_comfirm').empty();
					$('#my_comfirm').append('您暂无权限添加下级分销商了!请联系系统管理员.');
					$('#myComfirm').modal('show');
				 }
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
	
}
/** 添加渠道 **/
function on_click_add_spread(){
	
	var obj =  [
		   {'name':'loguser','type':'string'},
		   {'name':'logpwd','type':'string'},
		   {'name':'t_gold_proportions','type':'int'},
		   {'name':'t_vip_proportions','type':'int'}
		 ];
	
	if(vaildate_is_submit(obj)){
		$.ajax({
			type : 'POST',
			url : path + "admin/addSpreadUser.htm",
			data : {
				loguser:$('#loguser').val(),
				logpwd:$('#logpwd').val(),
				t_gold_proportions:$('#t_gold_proportions').val(),
				t_vip_proportions:$('#t_vip_proportions').val(),
				t_role_id:$('#t_role_id').val()
			},
			dataType : 'json',
			success : function(data) {
				if (data.m_istatus != 0) {
					$('#add_spread_channel').modal('hide');
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$('#myComfirm').modal('show');
					$("#utable").bootstrapTable('refreshOptions', {	page : 1 });
				} else {
					window.location.href = path + '/error.html';
				}
			}
		});
	}
	
}

 
/** 启用代理资格 */
function on_click_start(t_id) {
	if(confirm("确定启用次代理吗？")){
		$.ajax({
			type : 'POST',
			url : path + "admin/startSpreed.htm",
			data : {userId:t_id},
			dataType : 'json',
			success : function(data) {
				if (data.m_istatus != 0) {
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$('#myComfirm').modal('show');
					$("#utable").bootstrapTable('refreshOptions', {	page : 1 });
				} else {
					window.location.href = path + '/error.html';
				}
			}
		});
	}
}

/**代理下架失败*/
function on_examine_error_submit(id){
	var parmId = id;
	if(parmId == 0 || '' == parmId || undefined == parmId){
		parmId = $('#t_cps_id').val();
	}
	$.ajax({
		type : 'POST',
		url : path + "admin/cancelSpread.htm",
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
function on_load_settlement_modal(id){
	
	$("#spread_table").bootstrapTable('destroy');
	$('#spread_table').bootstrapTable({
						url : path + "admin/getSettlementList.htm",
						method : 'post',
						contentType : "application/x-www-form-urlencoded",
						queryParamsType : '',
						queryParams : function(params) {
							// 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
							var temp = {
								pageSize : params.limit,// 页面大小
								page : params.pageNumber, // 页码
								t_user_id : id
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
									title : '日期',
									field : 'data',
									align : 'center'
								},
								{
									title : '装机量',
									field : 'installNum',
									align : 'center'
								},
								{
									title : '金币充值(元)',
									field : 'gold',
									align : 'center'
								},
								{
									title : '金币提成(元)',
									field : 'goldMoney',
									align : 'center'
								},
								{
									title : 'VIP充值(元)',
									align : 'center',
									field : 'vip'
								},
								{
									title : 'VIP提成(元)',
									align : 'center',
									field : 'vipMoney'
								},
								{
									title : '总提成(元)',
									align : 'center',
									field : 'totalMoney'
								}]
					});
	$('#spread_settlement_modal').modal('show');
}



/** 加载 */
function  onload_next_lower_level(id) {
	// 刷新table
	$("#user_table").bootstrapTable('destroy');
	$('#user_table').bootstrapTable({
						url : path + "admin/getNextlLowerLevel.htm",
						method : 'post',
						contentType : "application/x-www-form-urlencoded",
						queryParamsType : '',
						queryParams : function(params) {
							// 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
							var temp = {
								pageSize : params.limit,// 页面大小
								page : params.pageNumber, // 页码
								t_spread_id : id
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
									title : '二级代理昵称',
									field : 't_nickName',
									align : 'center'
								},
								{
									title : 'idCard',
									field : 't_idcard',
									align : 'center'
								},
								{
									title : '安装数量',
									field : 'installNum',
									align : 'center'
								},
								{
									title : '总充值(元)',
									align : 'center',
									field : 'money'
								}]
					});

	$('#user_modal').modal('show');
}


