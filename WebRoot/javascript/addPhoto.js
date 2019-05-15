
$(document).ready(function(){
	
	console.log("获取t_id:"+$("#userId").val());
	
	initFileInput("file-0",1);
	initFileInput("file-1",2);
	// 0.初始化fileinput
	var oFileInput = new FileInput();
	oFileInput.Init("file-2");
	
})


function initFileInput(ctrlName, type) {
	var control = $('#' + ctrlName);
	control.fileinput({
		language : 'zh', // 设置语言
		uploadUrl : projectPath+"/admin/uploadFile.htm?type="+type+"&t_id="+$("#userId").val(),
		showUpload : false, // 是否显示上传按钮
		showRemove : true,
		showPreview : true,
		dropZoneEnabled : false,
		showCaption : true,// 是否显示标题
		autoReplace : true,
		allowedPreviewTypes : [ 'image' ],
		allowedFileTypes : [ 'image' ],
		allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
		maxFileSize : 100000,
		maxFilesNum : 1
	}).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data) {
        if(data.response)
        {
           console.log(data.response)
        }
    });
}

// 初始化fileinput
var FileInput = function() {
	var oFile = new Object();
	// 初始化fileinput控件（第一次初始化）
	oFile.Init = function(ctrlName) {
		$("#" + ctrlName).fileinput({
			// 上传的地址
			uploadUrl : projectPath + "/admin/uploadFile.htm?type=3&t_id="+$("#userId").val(),
			uploadAsync : true, // 默认异步上传
			showUpload : false, // 是否显示上传按钮,跟随文本框的那个
			showRemove : false, // 显示移除按钮,跟随文本框的那个
			showCaption : true,// 是否显示标题,就是那个文本框
			showPreview : true, // 是否显示预览,不写默认为true
			showPreview : true,
			dropZoneEnabled : true,// 是否显示拖拽区域，默认不写为true，但是会占用很大区域
			maxFileCount : 10, // 表示允许同时上传的最大文件个数
			enctype : 'multipart/form-data',
			validateInitialCount : true,
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
			// allowedFileTypes : [ 'image' ],//配置允许文件上传的类型
			// allowedPreviewTypes : [ 'image' ],//配置所有的被预览文件类型
			// allowedPreviewMimeTypes : [ 'jpg', 'png', 'gif'
			// ],//控制被预览的所有mime类型
			language : 'zh'
		}).on("filebatchselected", function(event, files) {
            $(this).fileinput("upload");
        }).on("fileuploaded", function(event, data) {
            if(data.response)
            {
                alert('上传成功!');
            }
        });
	 
	}
	return oFile;
};

 
