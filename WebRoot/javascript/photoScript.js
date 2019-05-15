/**
 * 
 */

$(function(){
	load_hand_img($("#page").val());
});

/**
 * 加载用户头像
 */
function load_hand_img(param){
	$.ajax({
		type : 'POST',
		url : path + '/admin/getPhotoList.htm',
		data : {page : param,t_user_id:$("#t_user_id").val()},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				console.log(data.m_object);
				var obj = data.m_object;
				$("#page").val(obj.pageCount);
				
				var html = '' ;
				
				$.each(obj.data,function(index,value){
					 html = html+'<div class="show-reel">';
					//第二次循环
					$.each(this,function(index,value){
						 html = html+'<div class="col-md-3 agile-gallery-grid">';
						 html = html+'<div class="agile-gallery">';
						 if(this.t_file_type == 0){
							 html = html+'<a href="javascript:on_click_preview('+this.t_id+',\''+this.t_addres_url+'\')">';
							 html = html+'<img src="'+this.t_addres_url+'" alt="" width="400" height="265"/>';
							 html = html+'</a>';
						 }else{
							 html = html+'<video width="320" height="259" controls autoplay>';
							 html = html+'<source src="'+this.t_addres_url+'"  type="video/mp4" controls="controls">';
							 html = html+'</video>';
						 }
						 html = html+'<div class="agileits-caption">';
						 if(this.t_is_private==0){
							 html = html+'<h4>公开</h4>';
						 }else{
							 html = html+'<h4>私密</h4>';
						 }
						 html = html+'<a href="javascript:download(\''+this.t_addres_url+'\') " style="float: left;margin-left:28%;margin-top:  5%;font-size: 1em;color: #ffffff" >点击下载</a>';
						 html = html+'<a href="javascript:on_click_disable('+this.t_id+')" style="float: right;margin-right: 5%;margin-top: 5%;font-size: 1em;color: #ffffff">点击禁用</a>';
						 html = html+'</div>';
						 html = html+'</div>';
						 html = html+'</div>';
					});
					html = html+'<div class="clearfix"></div></div>';
				});
				
				$("#myHandImg").text("");
				$("#myHandImg").append(html);
			
				//加载动态分页控件
				var pageHtml = '';
				
				var begin = parseInt(param) -2 < 1 ? 1 : (parseInt(param) -2)>(obj.pageCount-4)?(obj.pageCount-4):parseInt(param) -2;
				var end   = parseInt(param) + 2 > obj.pageCount?obj.pageCount :parseInt(param) + 2<5?5:parseInt(param) + 2;
				
				 pageHtml = pageHtml+ '<li><a href="javascript:load_hand_img(1)">&laquo;</a></li>';
						
				for (var i=begin;i<=end;i++)
				{ 
					pageHtml = pageHtml+'<li ';
					 if(i == param){
						 pageHtml = pageHtml+'class="active"';
					 }
					 
					 pageHtml = pageHtml+'><a href="javascript:load_hand_img('+i+')"">'+i+'</a></li>';
				}
				
				 pageHtml = pageHtml+'<li><a href="javascript:load_hand_img('+obj.pageCount+')">&raquo;</a></li>';
				
				$("#pageHtml").text("");
				$("#pageHtml").append(pageHtml);
			} else  {
				window.location.href = path + 'error.html';
			}
		}
	});
}

/**点击预览*/
function on_click_preview(id,url){
	$("#t_id").val(id);
	$("#preview").attr("src",url);
	$("#myModal").modal('show');
}

/**点击头像禁用*/
function on_click_disable(id){
	var param = id;
	if(null == id || "" == id){
		param = $("#t_id").val();
	}
	$.ajax({
		type : 'POST',
		url : path + '/admin/setUpPhotoEisable.htm',
		data : {t_id : param},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				$("#myModal").modal('hide');
				load_hand_img($("#page").val());
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}

function download(obj){
	window.open(obj);
}