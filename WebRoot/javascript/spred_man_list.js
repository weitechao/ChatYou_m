$(function(){
    //生成用户数据
   var url = path+"/admin/getSpredManList.htm" ;
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
              		title:'idcard',
              		align:'center',
              		field:'t_idcard'
              	},
              	{
              		title:'主播昵称',
              		align:'center',
              		field:'t_nickName',
              	},
              	{
              		title:'开始时间(每日)',
              		align:'center',
              		field:'t_begin_time',
              	},
              	{
              		title:'结束时间(每日)',
              		align:'center',
              		field:'t_end_time',
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
		$('#anthorId').val(obj.t_idcard);
		$('#t_begin_time').val(obj.t_begin_time);
		$('#t_end_time').val(obj.t_end_time);
	}else{
		$('#anthorId').val('');
		$('#t_begin_time').val('');
		$('#t_end_time').val('');
	}
	$("#myModal").modal('show');
}

function on_click_submit(){
	var isOK = vaildate_is_submit([
		   {'name':'anthorId','type':'int'},
		   {'name':'t_begin_time','type':'string'},
		   {'name':'t_end_time','type':'string'},
		 ]);
 
	
	if(isOK){
		var begin_time = $('#t_begin_time').val();
		var end_time = $('#t_end_time').val();
		//判断是否存在：
		if(begin_time.indexOf(":") <=0){
			 $("#t_begin_time").css({ "border-color": "red" });
			 $('#t_begin_time_validate').empty();
			 $('#t_begin_time_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove">请输入正确的开始时间</i>');
			 return;
		}
		var str = begin_time.split(":");
		if(!isRealNum(str[0])){
			 $("#t_begin_time").css({ "border-color": "red" });
			 $('#t_begin_time_validate').empty();
			 $('#t_begin_time_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove">请输入正确的开始时间</i>');
			 return;
		}
		//判断是否存在：
		if(end_time.indexOf(":") <=0){
			 $("#t_end_time").css({ "border-color": "red" });
			 $('#t_end_time_validate').empty();
			 $('#t_end_time_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove">请输入正确的结束时间.</i>');
			 return;
		}
		var str1 = end_time.split(":");
		if(!isRealNum(str1[0])){
			 $("#t_end_time").css({ "border-color": "red" });
			 $('#t_end_time_validate').empty();
			 $('#t_end_time_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove">请输入正确的结束时间.</i>');
			 return;
		}
		if(parseInt(str[0]) > parseInt(str1[0])){
			 $("#t_end_time").css({ "border-color": "red" });
			 $('#t_end_time_validate').empty();
			 $('#t_end_time_validate').append('<i style="margin-top: 10px;" class="glyphicon glyphicon-remove">结束时间不能小于开始时间.</i>');
			 return;
		}
		//保存数据
		$.ajax({
			type:'POST',
			url:path + '/admin/saveSpeedManData.htm',
			data:{
				t_id:$('#t_id').val(),
				anthorId:$('#anthorId').val(),
				t_begin_time:$('#t_begin_time').val(),
				t_end_time:$('#t_end_time').val()
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
			url:path + '/admin/delWeiXinPaySetUp.htm',
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

function isRealNum(val){
    // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除
    if(val === "" || val ==null){
        return false;
    }
    if(!isNaN(val)){
        return true;
    }else{
        return false;
    }
}




 



