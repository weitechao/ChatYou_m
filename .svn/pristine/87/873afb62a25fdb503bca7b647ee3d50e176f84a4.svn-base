$(function(){

	$("#anthor_begin_time").datepicker();
	$("#anthor_end_time").datepicker();
	ajax_load_table();
	 
});

//加载表格
function ajax_load_table(){
	//生成用户数据
    $('#utable').bootstrapTable({
        url: path+"admin/getUserConsumeList.htm",
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
              		title:'消费金币',
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



