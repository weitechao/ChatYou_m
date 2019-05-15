$(function(){
    //生成用户数据
    $('#utable').bootstrapTable({
        url: path+"/admin/getHelpConter.htm",
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
              		title:'标题',
              		align:'center',
              		field:'t_title'
              	},
              	{
              		title:'内容',
              		align:'center',
              		field:'t_content',
              		formatter:function(value){
              			if(value.length>50){
              				return value.substring(0,50)+'...';
              			}else{
              				return value;
              			}
              		}
              	},
              	{
              		title:'排序值',
              		align:'center',
              		field:'t_sort'
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
              			var res ='';
              			var valueStr = JSON.stringify(row);
              			res = res+"<a href='javascript:on_click_open_update_model("+valueStr+");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>修改</a>&nbsp;&nbsp;";
              			res = res +'<a href="javascript:on_click_operation('+row.t_id+');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">删除</a>';
              			return res;
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
	if(null != obj){
		$('#t_id').val(obj.t_id);
		$('#content').val(obj.t_content);
		$('#title').val(obj.t_title);
		$('#t_sort').val(null == obj.t_sort?99:obj.t_sort);
	}else{
		$('#t_id').val('');
		$('#content').val('');
		$('#title').val('');
		$('#t_sort').val('99');
	}
	$("#myModal").modal('show');
}

function on_click_submit(){
	var isOK = vaildate_is_submit([
		   {'name':'content','type':'string'},
		   {'name':'title','type':'string'},
		   {'name':'t_sort','type':'int'},
		 ]);
	if(isOK){
		//保存数据
		$.ajax({
			type:'POST',
			url:path + '/admin/addHelpConter.htm',
			data:{
				t_id:$('#t_id').val(),
				content:$('#content').val(),
				title:$('#title').val(),
				t_sort:$('#t_sort').val()
			},
			dataType : 'json',
			success:function(data){
				if (data.m_istatus >= 1) {
					$("#myModal").modal('hide');
					$('#my_comfirm').empty();
					$('#my_comfirm').append(data.m_strMessage);
					$("#myComfirm").modal('show');
					$("#utable").bootstrapTable('refreshOptions',{page : 1});
				} else  {
					window.location.href = path + '/error.html';
				}
			}
		});
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
			type:'post',
			url:path + '/admin/delHelpContre.htm',
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

 



 



