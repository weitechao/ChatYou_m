$(function(){
    //生成用户数据
   var url = path+"/admin/getSimulationList.htm" ;
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
              		title:'编号',
              		align:'center',
              		field:'t_id'
              	},
              	{
              		title:'消息内容',
              		align:'center',
              		field:'t_centent',
              	},
              	{
              		title:'发送性别',
              		align:'center',
              		field:'t_sex',
              		formatter:function(value){
              			return value == 0?'女':'男';
              		}
              	},
              	{
              		title:'创建时间',
              		align:'center',
              		field:'t_create_time',
              	},
              	{
              		title:'操作',
              		field:'',
              		align:'center',
              		formatter:function(value,row,index){
              			return '<a href="javascript:on_click_operation('+row.t_id+');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">删除</a>';
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
function on_click_open_update_model(obj){
	$("#myModal").modal('show');
}

function on_click_submit(){
  
	if(vaildate_is_submit([{'name':'t_centent','type':'string'}])){
		$.ajax({
			url:path + '/admin/saveSimulation.htm',
			data:{
				t_centent:$('#t_centent').val(),
				sex:$('#sex').val()
			},
			dataType : 'json',
			success:function(data){
				if (data.m_istatus == 1) {
					$("#myModal").modal('hide');
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$("#myComfirm").modal('show');
					$("#utable").bootstrapTable('refreshOptions',{page : 1});
				} else  {
					window.location.href = path + '/error.html';
				}
			}
		})
	}
}
/**
 * 点击操作
 */
function on_click_operation(id){
	$("#del_id").val(id);
	$("#myDel").modal('show');
}

function on_click_del(){
	if($('#del_id').val() != null &&  $('#del_id').val() != ""){
		$.ajax({
			url:path + '/admin/delSimulation.htm',
			data:{
				t_id:$('#del_id').val()
			},
		   dataType : 'json',
		   success:function(data){
			   if (data.m_istatus == 1) {
					$("#myDel").modal('hide');
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$("#myComfirm").modal('show');
					$("#utable").bootstrapTable('refreshOptions',{page : 1});
				} else  {
					window.location.href = path + '/error.html';
				}
		   }
		})
	}
}






 



