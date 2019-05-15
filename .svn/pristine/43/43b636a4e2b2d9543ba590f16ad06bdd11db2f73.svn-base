$(function(){
	$("#anthor_begin_time").datepicker();
	$("#anthor_end_time").datepicker();
	ajax_load_table();
	 
});

//加载表格
function ajax_load_table(){
	//生成用户数据
    $('#utable').bootstrapTable({
        url: path+"admin/getOnLineTimeList.htm",
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
					title:'IdCard',
					field:'t_idcard',
					align:'center'
				},{
              		title:'昵称',
              		field:'t_nickName',
              		align:'center'
              	},
              	{
              		title:'头像',
              		align:'center',
              		field:'t_handImg',
              		formatter:function(value,row,index){
              			 return '<img src="'+value+'" style="cursor:pointer" title="点击预览" width="60" height="60" onclick="on_click_preview(\''+value+'\');">';
              		}

              	},
              	{
              		title:'在线时长',
              		align:'center',
              		field:'totalTime'
              	},
              	{
              		title:'操作',
              		field:'',
              		align:'center',
              		formatter:function(value,row,index){
              			
              			var res ='<a class="btn btn-default" href="javascript:on_click_totalTime('+row.t_id+')" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">明细</a>&nbsp;&nbsp;';
              			//指定推送弹窗
              			return res;
              		}
              	}
        ]
    });
}
/**点击预览头像*/
function on_click_preview(url){
	$("#hand_img").attr("src",url);
	$("#img_preview").modal('show');
}

/**查询主播在线记录*/
function on_click_totalTime(id){
	$('#anthor_id').val(id);
	$('#anthor_online_time').modal('show');
	$("#anthorTable").bootstrapTable('destroy');
	loadTable1(id);
}

/**加载主播在线明细*/
function loadTable1(id){
	 $('#anthorTable').bootstrapTable({
	        url: path+ 'admin/getAnchorOnlineTime.htm?userId='+id,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams:function (params){
	        	return {
	                pageSize: params.limit, // 每页要显示的数据条数
	                page: params.pageNumber, // 每页显示数据的开始行号 
	                beginTime:$('#anthor_begin_time').val(),
	                endTime:$('#anthor_end_time').val()
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
	              		title:'登入时间',
	              		align:'center',
	              		field:'login_time'
	              	},
	              	{
	              		title:'登出时间',
	              		align:'center',
	              		field:'logout_time'
	              	},
	              	{
	              		title:'在线时长',
	              		align:'center',
	              		field:'t_duration'
	              	}
	        ]
	    });
}


/**验证两个日期的大小*/
function CompareDate(d1,d2){
  return ((new Date(d1.replace(/-/g,"\/"))) < (new Date(d2.replace(/-/g,"\/"))));
}

/** 点击搜索*/
function search_anthor_time(){
	
   var  beginTime = $('#anthor_begin_time').val();
   var  endTime = $('#anthor_end_time').val();
   
   if(CompareDate(beginTime,endTime)){
	   $("#anthorTable").bootstrapTable('destroy');
	   loadTable($('#anthor_id').val());
   }
   
}

