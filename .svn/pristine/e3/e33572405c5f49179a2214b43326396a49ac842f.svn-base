$(function(){
    //生成用户数据
   var url = path+"/admin/getPushMsgList.htm" ;
    $('#utable').bootstrapTable({
        url: url,
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        queryParamsType:'', 
        queryParams: function (params){
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
        pageSize: 10,//每页的记录行数（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        //【设置列】
        columns: [
              	{
              		title:'接收人昵称',
              		align:'center',
              		field:'t_nickName'
              	},
              	{
              		title:'推送内容',
              		align:'center',
              		field:'t_message_content',
              	},
              	{
              		title:'推送时间',
              		align:'center',
              		field:'t_create_time'
              	}
        ]
    });
});


/**
 * 新增或者修改
 * @param id
 * @param nickName
 */
function on_click_open_update_model(id,img_url,t_link_url,t_is_enable){
	$("#myModal").modal('show');
}

var isOk = true;

function on_click_submit(){
	
	$('#my_comfirm').empty();
	$('#my_comfirm').append("推送发送中!请稍后...");
	$('#myComfirm').modal('show');
	
	//控制点击次数
	if(isOk){
		isOk = false;
		$.ajax({
			type : 'POST',
			url :path+'/admin/addWholeServicePush.htm',
			data : {
				push_msg :$("#push_msg").val(),
				t_user_role :$("#t_user_role").val(),
			},
			dataType : 'json',
			success : function(data) {
				isOk = true;
				if (data.m_istatus == 1) {
					$("#myModal").modal('hide');
					$("#utable").bootstrapTable('refreshOptions',{page : 1});
				} else  {
					window.location.href = path + 'error.html';
				}
			}
		});
	}else{
		$('#my_comfirm').empty();
		$('#my_comfirm').append("推送发送中!请稍后...");
		$('#myComfirm').modal('show');
	}
}


 



