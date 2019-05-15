$(function(){
    //生成用户数据
   var url = path+"/admin/getSpreedImgList.htm" ;
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
              		title:'渠道推广图片',
              		align:'center',
              		field:'t_img_path',
              		formatter:function(value,row,index){
              			 return '<img src="'+value+'" style="cursor:pointer" title="点击预览" width="60" height="60" onclick="on_click_preview(\''+value+'\');">';
              		}

              	},
              	{
              		title:'链接地址',
              		align:'center',
              		field:'shortUrl',
              	},
              	{
              		title:'上传时间',
              		align:'center',
              		field:'t_create_time',
              	},
              	{
              		title:'操作',
              		field:'',
              		align:'center',
              		formatter:function(value,row,index){
              			
              			var method = path+"/admin/getPreviewImg.htm?t_id="+row.t_id+"&userId="+row.userId;
              			
              			var res ='';
              			res = res+'<a href="javascript:on_click_pre_code(\''+method+'\');"   class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">预览二维码</a>&nbsp;&nbsp;';
              			res = res +'<a href="'+method+'"   class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;">下载二维码</a>&nbsp;&nbsp;';
              			//判断是否需要显示删除按钮
              			if(row.operation){
              				res = res +'<a href="javascript:on_click_del_file('+row.t_id+');" class="btn btn-default" style="height: 25px;line-height: 0.5;background-color: #87CEFA;color:red;">删除文件</a>';
              			}
              			return res;
              		}
              	}
        ]
    });
    
    
});


//path+"/admin/uploadFile.htm?type=-1"
/**加载图片控件*/
function on_load_file_input(){
	$("#file-1").fileinput('refresh',{
		language: 'zh', //设置语言
	    uploadUrl: path+'/admin/uploadSpeedFile.htm', // server upload action
	    uploadAsync: true,
		allowedFileExtensions : [ 'jpg', 'png', 'gif'],
	    maxFileCount: 1,
	    dropZoneEnabled: true,
	    minImageWidth: 750, //图片的最小宽度
        minImageHeight: 1334,//图片的最小高度
        maxImageWidth: 750,//图片的最大宽度
        maxImageHeight: 1334,//图片的最大高度
	    showBrowse: false,
	    showUpload : false, // 是否显示上传按钮
	    autoReplace : true,
	    showRemove:true,
	}).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data) {
        if(data.response.m_istatus == 1)
        {
           console.log(data.response)
           $("#img_url").val(data.response.m_object);
        }
    });
	
}


/**点击预览*/
function on_click_preview(url){
	$("#hand_img").attr("src",url);
	$("#img_preview").modal('show');
}

/**点击预览二维码贴图*/
function on_click_pre_code(url){
	$("#hand_img").attr("src",url);
	$("#img_preview").modal('show');
}

/**
 * 新增或者修改
 * @param id
 * @param nickName
 */
function on_click_open_update_model(id){
	on_load_file_input();
	$("#myModal").modal('show');
}

/**
 * 上传图片
 * @returns
 */
function on_click_submit(){
	$.ajax({
		type : 'POST',
		url :path+'/admin/saveSpreadImg.htm',
		data : {img_url :$('#img_url').val()},
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

/**
 * 点击删除文件
 * @param id
 * @returns
 */
function on_click_del_file(id){
	$.ajax({
		type : 'POST',
		url :path+'/admin/delSpreadImg.htm',
		data : {id :id},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#utable").bootstrapTable('refreshOptions',{page : 1});
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}






