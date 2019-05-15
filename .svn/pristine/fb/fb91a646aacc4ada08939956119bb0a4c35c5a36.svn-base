$(function(){
	ajax_table();
});
 

function ajax_table(type){
	  //生成用户数据
	   var url = path+"/admin/getAdminList.htm";
	    $('#utable').bootstrapTable({
	        url: url,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams: function(params){
	        	return {
	        		pageSize: params.limit,
	        		page:params.pageNumber
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
	              		title:'账号',
	              		field:'t_user_name',
	              		align:'center'
	              	},
	              	{
	              		title:'密码',
	              		align:'center',
	              		field:'t_pass_word'
	              	},
	              	{
	              		title:'角色',
	              		align:'center',
	              		field:'t_role_name'
	              	},
	              	{
	              		title:'状态',
	              		align:'center',
	              		field:'t_is_disable',
	              		formatter:function(value){
	              			if(value == 0){
	              				return "<span style='color:#00FF00;'>已启用</span>";
	              			}else if(value == 1){
	              				return "<span style='color:red;'>已禁用</span>";
	              			}
	              		}
	              	},{
	              		title:'操作',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			var valueStr = JSON.stringify(row);
	              			var str = "<a href = 'javascript:on_click_open_update_model("+valueStr+")' class = 'btn btn-default' >修改</a>&nbsp;&nbsp;&nbsp;&nbsp;"; 
	              			
	              			str = str + "<a href = 'javascript:on_click_del("+row.t_id+")' class = 'btn btn-default' >删除</a>"
	              			return str;
	              		}
	              	}
	              	
	        ]
	    });
}

/**
 * 点击弹窗
 * @param id
 * @param nickName
 */
function on_click_open_update_model(obj){
	
	if(null == obj){
		$("#t_id").val('');
		$("#t_user_name").val('');
		$("#t_pass_word").val('');
		$("#t_is_disable").val(0);
		on_load_role();
	}else if(null != obj){
		$("#t_id").val(obj.t_id);
		$("#t_user_name").val(obj.t_user_name);
		$("#t_pass_word").val(obj.t_pass_word);
		$("#t_is_disable").val(obj.t_is_disable);
		on_load_role(obj.t_role_id);
	}
	$("#myModal").modal('show');
}
  

function on_load_role(id){
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
				$("#t_role_id").val(id);
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

/**
 * 点击提交新增或修改
 */
function on_click_submit(){
	$.ajax({
		type : 'POST',
		url : path + '/admin/saveAdmin.htm',
		data : {
			t_id : $("#t_id").val(),
			t_user_name :$("#t_user_name").val(),
			t_pass_word :$("#t_pass_word").val(),
			t_is_disable :$("#t_is_disable").val(),
			t_role_id : $("#t_role_id").val()
		},
		dataType : 'json',
		success : function(data) {
			console.log(data);
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

//删除信息
function on_click_del(id){
	$.ajax({
		type : 'POST',
		url : path + '/admin/delAdmin.htm',
		data : {
			t_id : id
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


 
 


