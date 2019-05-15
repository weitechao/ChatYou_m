$(function(){
    //生成用户数据
   var url = path+"/admin/getEnrollList.htm" ;
    $('#utable').bootstrapTable({
        url: url,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        queryParamsType:'', 
        queryParams: function(params){
        	return {
        		pageSize: params.limit,
        		page:params.pageNumber,
        		Name:$('#search_name').val(),
        		Tel:$('#search_tel').val()
        	}
        }, 
        //【其它设置】
        locale:'zh-CN',//中文支持
        pagination: true,//是否开启分页（*）
        pageNumber:1,//初始化加载第一页，默认第一页
        pageSize: 5,//每页的记录行数（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        //【设置列】
        columns: [
              	{
              		title:'性别',
              		align:'center',
              		field:'t_sex',
              		formatter:function(value,row,index){
              			 return value == 0 ? '女':'男';
              		}

              	},
              	{
              		title:'赠送金币',
              		align:'center',
              		field:'t_gold',
              	},
              	{
              		title:'创建时间',
              		align:'center',
              		field:'t_create_time'
              	},
              	{
              		title:'操作',
              		field:'',
              		align:'center',
              		formatter:function(value,row,index){
              			return '<a href="javascript:on_click_operation('+row.t_id+',2);"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">删除</a>';
              		}
              	}
        ]
    });
    
});


/**
 * 新增或者修改
 * @param id
 * @param nickName
 */
function on_click_open_update_model(){
	if($('#utable').bootstrapTable('getOptions').totalRows >=2){
		$('#my_comfirm').empty();
		$('#my_comfirm').append('请删除数据后,在添加记录.');
		$("#myComfirm").modal('show');
		return ;
	}
	$("#t_gold").val("");
	$("#myModal").modal('show');
}
/**
 * 点击操作
 */
function on_click_operation(id){
	$("#del_id").val(id);
	$("#myDel").modal('show');
}

/**
 * 点击启用或者禁用用户
 */
function on_click_change_res(){
	var id =  $("#del_id").val();
	$.ajax({
		type : 'POST',
		url :path + '/admin/delEnroll.htm',
		data : {t_id :id},
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


function on_click_submit(){
	$.ajax({
		type : 'POST',
		url :path+'/admin/addEnroll.htm',
		data : {
			   t_sex :$("#t_sex").val(),
			   t_gold:$("#t_gold").val()
		},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + 'error.html';
			}
		}
	});
}


 
 




