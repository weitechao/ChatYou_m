$(function(){
	$("#beginTime").datepicker();
	$("#endTime").datepicker();
	ajax_load_table();
});

function ajax_load_table(){
	//生成用户数据
	   var url = path+"/admin/getReportList.htm" ;
	    $('#utable').bootstrapTable({
	        url: url,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams: function (params){
	        	return {
	        		pageSize: params.limit,
	        		page:params.pageNumber,
	        		condition:$('#condition').val(),
	        		beginTime:$('#beginTime').val(),
	        		endTime:$('#endTime').val()
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
	              		title:'举报人',
	              		field:'t_nickName',
	              		align:'center'
	              		 
	              	},
	              	{
	              		title:'被举报人',
	              		align:'center',
	              		field:'coverName'

	              	},
	              	{
	              		title:'举报原因',
	              		align:'center',
	              		field:'t_comment',
	              	},{
	              		title:'图片',
	              		align:'center',
	              		field:'t_img',
	              		formatter:function(value,row,index){
	              			  console.log(value);
	              			  var imgs = value.split(",");
	              			  var obj = '';
	              			  for(var i = 0; i < imgs.length ; i++){
	              				  if(null != imgs[i] && ""!=imgs[i])
	              				   obj = obj + '<img src="'+imgs[i]+'" style="cursor:pointer" title="点击预览" width="40" height="40" onclick="on_click_preview(\''+imgs[i]+'\');"> &nbsp;&nbsp;';
	              			  }
	              			 return obj;
	              		}

	              	},
	              	{
	              		title:'举报时间',
	              		align:'center',
	              		field:'t_create_time'
	              	},
	              	{
	              		title:'是否处理',
	              		field:'t_is_handle',
	              		align:'center',
	              		formatter:function(value){
	              			return value == null ?'未处理':'已处理';
	              		}
	              	},
	              	{
	              		title:'处理描述',
	              		field:'t_handle_comment',
	              		align:'center'
	              	},
	              	{
	              		title:'处理时间',
	              		field:'t_handle_time',
	              		align:'center'
	              	},
	              	{
	              		title:'操作',
	              		field:'',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			var res ='';
	              			if(row.t_is_handle ==null){
	              				res = res +'<a href="javascript:on_click_open_update_model('+row.t_id+');"  class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">处理</a>&nbsp;&nbsp;';
	              			}
	              			 
	              			return res;
	              		}
	              	}
	        ]
	    });
}


/**点击预览图片*/
function on_click_preview(url){
	$("#hand_img").attr("src",url);
	$("#img_preview").modal('show');
}



/**点击加载表格*/
function on_click_search(){
	$("#utable").bootstrapTable('destroy');
	ajax_load_table();
}

/**
 * 点击处理
 * @param id
 * @param nickName
 */
function on_click_open_update_model(id){
	$("#t_id").val(id);
	$("#t_handle_comment").val("");
	$("#myModal").modal('show');
}

/**
 * 点击启用或者禁用用户
 */
function on_click_handle_report(){
	$.ajax({
		type : 'POST',
		url : path + '/admin/handleReport.htm',
		data : {
			t_id : $("#t_id").val(),
			t_handle_comment :$("#t_handle_comment").val()
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

 


