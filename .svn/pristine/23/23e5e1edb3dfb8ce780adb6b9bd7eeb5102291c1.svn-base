$(function(){
	$("#beginTime").datepicker();
	$("#endTime").datepicker();
	$("#anthor_begin_time").datepicker();
	$("#anthor_end_time").datepicker();
	ajax_load_table();
	 
});

//加载表格
function ajax_load_table(){
	//生成用户数据
    $('#utable').bootstrapTable({
        url: path+"admin/getExtractMoney.htm",
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        queryParamsType:'', 
        queryParams: function(params){
        	return {
        		pageSize: params.limit,
        		page:params.pageNumber,
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
              		title:'性别',
              		field:'t_sex',
              		align:'center',
              		formatter:function(value){
              			return  value == 0 ? '女':'男';
              		}
              	},
              	{
              		title:'提现金额',
              		field:'totalMoney',
              		align:'center'
              	},
              	{
              		title:'操作',
              		field:'',
              		align:'center',
              		formatter:function(value,row,index){
              			//指定推送弹窗
              			return '<a href="javascript:on_click_finance_popup('+row.t_id+');" class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">明细</a>';
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

/**查询推广用户列表*/
function on_click_totalTime(id){
	$('#t_referee').val(id);
	$('#spread_user').modal('show');
	$("#anthorTable").bootstrapTable('destroy');
	loadTable1();
}

/**加载主播在线明细*/
function loadTable1(){
	 $('#anthorTable').bootstrapTable({
	        url: path+ 'admin/getSpreadUserList.htm',
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams:function (params){
	        	return {
	                pageSize: params.limit, // 每页要显示的数据条数
	                page: params.pageNumber, // 每页显示数据的开始行号 
	                beginTime:$('#anthor_begin_time').val(),
	                endTime:$('#anthor_end_time').val(),
	                userId:$('#t_referee').val()
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
	              		title:'平台号',
	              		align:'center',
	              		field:'t_idcard'
	              	},
	              	{
	              		title:'用户昵称',
	              		align:'center',
	              		field:'t_nickName',
	              		formatter:function(value,row){
	              			return '<a  href="javascript:on_click_finance_popup('+row.t_id+');" >'+value+'</a>';
	              		}
	              	},{
	              		title:'性别',
	              		field:'t_sex',
	              		align:'center',
	              		formatter:function(value){
	              			return  value == 0 ? '女':'男';
	              		}
	              	},
	              	{
	              		title:'注册时间',
	              		align:'center',
	              		field:'t_create_time'
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
	   loadTable();
   }
   
}

