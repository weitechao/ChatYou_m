$(function(){
	ajax_table();
});

function ajax_table(type){
	  //生成用户数据
	   var url = path+"/admin/getMenuList.htm";
	    $('#utable').bootstrapTable({
	        url: url,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams: function (params){
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
	              		title:'菜单名称',
	              		field:'t_menu_name',
	              		align:'center'
	              	},
	              	{
	              		title:'菜单链接',
	              		align:'center',
	              		field:'t_menu_url'
	              	},
	              	{
	              		title:'父级名称',
	              		align:'center',
	              		field:'father_anme' 
	              	},
	              	{
	              		title:'创建时间',
	              		align:'center',
	              		field:'t_create_time'
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
	console.log(obj);
	if(undefined == obj || null == obj || '' == obj){
		$("#t_id").val('');
		$("#t_menu_name").val('');
		$("#t_menu_url").val('');
		on_load_role();
	}else if(null != obj){
		$("#t_id").val(obj.t_id);
		$("#t_menu_name").val(obj.t_menu_name);
		$("#t_menu_url").val(obj.t_menu_url);
		$('#t_icon').val(obj.t_icon);
		on_load_role(obj.t_role_id);
	}
	$("#myModal").modal('show');
}
  

function on_load_role(id){
	$.ajax({
		type : 'POST',
		url : path + '/admin/getFatherList.htm',
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#t_father_id").empty();
				$("#t_father_id").append("<option value='0'>请选择父级菜单</option>");
				$.each(data.m_object,function(){
					$("#t_father_id").append("<option value='"+this.t_id+"'>"+this.t_menu_name+"</option>");
				})
				$("#t_father_id").val(id);
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
		url : path + '/admin/saveMenu.htm',
		data : {
			t_id : $("#t_id").val(),
			t_menu_name :$("#t_menu_name").val(),
			t_menu_url :$("#t_menu_url").val(),
			t_father_id :$("#t_father_id").val(),
			t_icon : $('#t_icon').val()
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

//删除信息
function on_click_del(id){
	$.ajax({
		type : 'POST',
		url : path + '/admin/delMenu.htm',
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


 
 


