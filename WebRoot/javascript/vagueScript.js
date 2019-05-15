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
		url : path + '/admin/getVagueList.htm',
		data : {page : param},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				console.log(data.m_object);
				var obj = data.m_object;
				$("#page").val(param+1);
				
				var html = '' ;
				
				$.each(obj.data,function(index,value){
					 html = html+'<div class="show-reel">';
					//第二次循环
					$.each(this,function(index,value){
						 html = html+'<div class="col-md-3 agile-gallery-grid">';
						 html = html+'<div class="agile-gallery">';
						
						 html = html+'<a href="javascript:on_click_preview('+this.t_id+',\''+this.t_img_url+'\')">';
						 html = html+'<img src="'+this.t_img_url+'" alt="" width="400" height="265"/>';
						 html = html+'</a>';
						 
						 html = html+'<div class="agileits-caption">';
						 html = html+'<a href="javascript:on_click_adopt('+this.t_id+')" style="float: left;margin-left: 10%;margin-top: 5.5%;font-size: 1em;color: #ffffff">审核通过</a>';
						 html = html+'<a href="javascript:on_click_disable('+this.t_id+')" style="float: right;margin-right: 10%;margin-top: 5.5%;font-size: 1em;color: red">审核失败</a>';
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
				window.location.href = path + '/error.html';
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

/**点击禁用*/
function on_click_disable(id){
	var param = id;
	if(null == id || "" == id){
		param = $("#t_id").val();
	}
	$.ajax({
		type : 'POST',
		url : path + '/admin/delVagueById.htm',
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

/**点击通过*/
function on_click_adopt(id){
	var param = id;
	if(null == id || "" == id){
		param = $("#t_id").val();
	}
	$.ajax({
		type : 'POST',
		url : path + '/admin/hasVerified.htm',
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