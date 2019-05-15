$(function(){
   
	ajax_load_table();
	$("#beginTime").datepicker();
	$("#endTime").datepicker();
});

function ajax_load_table(){
	 //生成用户数据
	   var url = path+"/admin/getFeedbackList.htm" ;
	    $('#utable').bootstrapTable({
	        url: url,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams: function(params){
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
	              		title:'反馈人',
	              		field:'t_nickName',
	              		align:'center'
	              		 
	              	},
	              	{
	              		title:'联系方式',
	              		align:'center',
	              		field:'t_phone'

	              	},
	              	{
	              		title:'反馈内容',
	              		align:'center',
	              		field:'t_content',
	              	},{
	              		title:'反馈图片',
	              		align:'center',
	              		field:'t_img_url',
	              		formatter:function(value,row,index){
	              			 var obj = '';
	              			  if(value !=null){
	              				  
	              				  var imgs = value.split(",");
	                   			 
	                  			  for(var i = 0; i < imgs.length ; i++){
	                  				  if(null != imgs[i] && ""!=imgs[i])
	                  				   obj = obj + '<img src="'+imgs[i]+'" style="cursor:pointer" title="点击预览" width="40" height="40" onclick="on_click_preview(\''+imgs[i]+'\');"> &nbsp;&nbsp;';
	                  			  }
	              			  }
	              			 return obj;
	              		}
	              	},
	              	{
	              		title:'反馈时间',
	              		align:'center',
	              		field:'t_create_time'
	              	},
	              	{
	              		title:'处理结果',
	              		align:'center',
	              		field:'t_handle_res'
	              	},{
	              		title:'处理图片',
	              		align:'center',
	              		field:'t_handle_img',
	              		formatter:function(value){
	              			 var obj = '';
	             			  if(value !=null){
	             				  
	             				  var imgs = value.split(",");
	                  			 
	                 			  for(var i = 0; i < imgs.length ; i++){
	                 				  if(null != imgs[i] && ""!=imgs[i])
	                 				   obj = obj + '<img src="'+imgs[i]+'" style="cursor:pointer" title="点击预览" width="40" height="40" onclick="on_click_preview(\''+imgs[i]+'\');"> &nbsp;&nbsp;';
	                 			  }
	             			  }
	             			 return obj;
	              		}
	              	},{
	              		title:'处理时间',
	              		align:'center',
	              		field:'t_handle_time'
	              	},{
	              		title:'操作',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			var res ='';
	             			 if(row.t_is_handle == 0){
	             				 res = res +'<a class="btn btn-default" href="javascript:on_click_reply('+row.t_id+')"; style="height: 25px;line-height: 0.5;background-color: #87CEFA;">回复</a>';
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
 * 点击启用或者禁用用户
 */
function on_click_submit(){
	$.ajax({
		type : 'POST',
		url : path + '/admin/upateFeedBack.htm',
		data : {
			t_id : $("#t_id").val(),
			t_handle_comment :$("#t_handle_comment").val(),
			img_url : $("#img_url").val()
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


/**加载图片控件*/
function on_load_file_input(preview){

	$("#file-1").fileinput({
		language: 'zh', //设置语言
	    uploadUrl: path+"/admin/uploadFile.htm?type=-1", // server upload action
	    uploadAsync: true,
		allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
	    maxFileCount: 1,
	    showBrowse: false,
	    showUpload : false, // 是否显示上传按钮
	    autoReplace : true,
	    showRemove:true,
	    browseOnZoneClick: true
	}).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data) {
        if(data.response.m_istatus == 1)
        {
           console.log(data.response)
           var url = $("#img_url").val();
           $("#img_url").val(url+','+data.response.m_object);
        }
    });
	
}

function on_click_reply(id){
	$("#t_id").val(id);
	on_load_file_input();
	$("#myModal").modal('show');
}
 


