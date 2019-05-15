$(function(){
    //生成用户数据
   var url = path+"/admin/getDomanList.htm" ;
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
        pageSize: 5,//每页的记录行数（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        //【设置列】
        columns: [
              	{
              		title:'域名',
              		align:'center',
              		field:'t_domain_name'
              	},
              	{
              		title:'作用类型',
              		align:'center',
              		field:'t_effect_type',
              		formatter:function(value){
              			return value == 0? '推广域名':'APP打包域名';
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
              			var res ='';
              			var valueStr = JSON.stringify(row);
              			//res = res+"<a href='javascript:on_click_open_update_model("+valueStr+");'  class='btn btn-default' style='height: 25px;line-height: 0.5;background-color: #87CEFA;'>修改</a>&nbsp;&nbsp;";
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
function on_click_open_update_model(){
	$('#t_domain_name').val("");
	$('#t_effect_type').val(0);
	$("#myModal").modal('show');
}

function on_click_submit(){
	if(vaildate_is_submit([{'name':'t_domain_name','type':'string'}])){
		$.ajax({
			url:path + '/admin/saveDomainName.htm',
			data:{
				domainName:$('#t_domain_name').val(),
				t_effect_type:$('#t_effect_type').val()
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
			url:path + '/admin/delDomainName.htm',
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






 



