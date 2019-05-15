$(function(){
	ajax_load_data();
});


function ajax_load_data(){
	 //生成用户数据
	   var url = path+"/admin/getFreezeList.htm" ;
	    $('#utable').bootstrapTable({
	        url: url,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams: function(params){
	        	return {
	        		pageSize: params.limit,
	        		page:params.pageNumber,
	        		condition:$('#condition').val()
	        	}
	        }, 
	        //【其它设置】
	        locale:'zh-CN',//中文支持
	        pagination: true,//是否开启分页（*）
	        pageNumber:1,//初始化加载第一页，默认第一页
	        pageSize: 10,//每页的记录行数（*）
	        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
	        //【设置列】
	        columns: [
	                  {
	              		title:'idcard',
	              		field:'t_idcard',
	              		align:'center'	 
	              	},
	              	{
	              		title:'昵称',
	              		field:'t_nickName',
	              		align:'center'	 
	              	},
	              	{
	              		title:'性别',
	              		field:'t_sex',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			 return value == 0?'女':'男';
	              		}

	              	},
	              	{
	              		title:'电话',
	              		field:'t_phone',
	              		align:'center'
	              	},
	              	{
	              		title:'角色',
	              		field:'t_role',
	              		align:'center',
	              		formatter:function(value){
	              			return value == 0?'普通用户':'主播用户';
	              		}
	              	},
	              	{
	              		title:'封号时间',
	              		field:'t_disable_time',
	              		align:'center',
	              	},
	              	{
	              		title:'开始时间',
	              		field:'t_start_time',
	              		align:'center',
	              	},
	              	{
	              		title:'结束时间',
	              		field:'t_end_time',
	              		align:'center',
	              	},
	              	{
	              		title:'封号原因',
	              		field:'t_context',
	              		align:'center',
	              	},
	              	{
	              		title:'操作人',
	              		field:'t_operate_user',
	              		align:'center',
	              	},
	              	{
	              		title:'操作时间',
	              		field:'t_create_time',
	              		align:'center',
	              	},
	              	{
	              		title:'当前状态',
	              		field:'t_disable',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			return value == 0?'正常':value == 1?'封号中':'已禁用';
	              		}
	              	}
	        ]
	    });
}



/**
 * 点击弹出用户详情
 * @param id
 * @param nickName
 */
function on_click_open_user_model(params){
	$.ajax({
		type : 'POST',
		url : path + '/admin/getUserById.htm',
		data : {t_id : params},
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
			}else  {
				window.location.href = path + '/error.html';
			}
		}
	});
	
}

/**
 * 点击查看详情
 * @param params
 */
function on_click_query_freeze_details(params){
	$.ajax({
		type : 'POST',
		url : path + '/admin/getFreezeTimeList.htm',
		data : {u_id : params},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
			
				var  obj = data.m_object;
				
				var html = '<table class = "table table-hover">';
				html = html+'<tr >';
				html = html+'	<th>封号时间(分钟数)</th>';
				html = html+'	<th>开始时间</th>';
				html = html+'	<th>结束时间</th>';
				html = html+'</tr>';
				$.each(obj, function( key, val) {
				    html = html+'<tr>';
					html = html+' <td>'+this.t_disable_time+'</td>';
					html = html+' <td>'+this.t_start_time+'</td>';
					html = html+' <td>'+this.t_end_time+'</td>';
				    html = html+'</tr>';
				});
				html = html+'</table>';
				$("#myDetail").text("");
				$("#myDetail").append(html);
				$("#myModal").modal('show');
			}else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}
/**
 * 点击操作
 */
function on_click_operation(id,state){
	$("#del_id").val(id);
	$("#state").val(state);
	$("#myDel").modal('show');
}

/**
 * 点击启用或者禁用用户
 */
function on_click_change_res(){
	$.ajax({
		type : 'POST',
		url : path + '/admin/enableOrDisable.htm',
		data : {
			t_id : $("#del_id").val(),
			state :$("#state").val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myDel").modal('hide');
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/**
 * 点击封号
 */
function on_click_freeze_ones(){
	$.ajax({
		type : 'POST',
		url : path + '/admin/freezeOnesUser.htm',
		data : {
			t_id : $("#t_id").val(),
			freeze_time :$("#freeze_time").val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/**
 * 手动解封
 * @param id
 */
function on_click_unlock(id){
	$.ajax({
		type : 'POST',
		url : path + '/admin/unlock.htm',
		data : {
			t_id : id
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

//点击搜索按钮
function on_click_search(){
	$("#utable").bootstrapTable('destroy');
	ajax_load_data();
}