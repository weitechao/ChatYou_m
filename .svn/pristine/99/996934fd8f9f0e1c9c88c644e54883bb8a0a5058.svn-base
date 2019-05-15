$(function(){
    //生成用户数据
   var url = path+"/admin/getAlipaySetUpList.htm" ;
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
              		title:'appId',
              		align:'center',
              		field:'t_alipay_appid',
              		width:150
              	},
              	{
              		title:'支付宝秘钥',
              		align:'center',
              		field:'t_alipay_private_key',
              		formatter:function(value){
              			var res = value;
              			var valength = value.length;
              			for(var i = valength; i >= 0;){
              				var head = res.substring(0,i);
              				var tail = res.substring(i);
              				res = head+"\n"+tail;
              				console.log(res);
              				i = i-65;
              			}
              			console.log(res);
              			return res;
              		}
              	},
              	{
              		title:'支付宝公钥',
              		align:'center',
              		field:'t_alipay_public_key',
              		formatter:function(value){
              			var res = value;
              			var valength = value.length;
              			for(var i = valength; i >= 0;){
              				var head = res.substring(0,i);
              				var tail = res.substring(i);
              				res = head+"\n"+tail;
              				console.log(res);
              				i = i-65;
              			}
              			console.log(res);
              			return res;
              		}
              	},
              	{
              		title:'更新时间',
              		align:'center',
              		field:'t_create_time',
              		width:200
              	},
              	{
              		title:'操作',
              		field:'',
              		align:'center',
              		width:150,
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
 * 指定位置插入字符串
 * @param str
 * @param flg
 * @param sn
 * @returns
 */
function insert_flg(str,flg,sn){
	    var newstr="";
	    for(var i=0;i<str.length;i+=sn){
	        var tmp=str.substring(i, i+sn);
	        newstr+=tmp+flg;
	    }
	    return newstr;
}

/**
 * 新增或者修改
 * @param id
 * @param nickName
 */
function on_click_open_update_model(obj){
	if(null != obj){
		$('#t_id').val(obj.t_id);
		$('#t_alipay_appid').val(obj.t_alipay_appid);
		$('#t_alipay_private_key').val(obj.t_alipay_private_key);
		$('#t_alipay_public_key').val(obj.t_alipay_public_key);
	}else if($('#utable').bootstrapTable('getOptions').totalRows >=1){
		$('#my_comfirm').empty();
		$('#my_comfirm').append('请删除数据后,在添加记录.');
		$("#myComfirm").modal('show');
		return ;
	}
	$("#myModal").modal('show');
}

function on_click_submit(){
	var obj =  [
		   {'name':'t_alipay_appid','type':'string'},
		   {'name':'t_alipay_private_key','type':'string'},
		   {'name':'t_alipay_public_key','type':'string'},
		 ];
	if(vaildate_is_submit(obj)){
		$.ajax({
			url:path + '/admin/setAlipaySetUp.htm',
			data:{
				t_id:$('#t_id').val(),
				t_alipay_appid:$('#t_alipay_appid').val(),
				t_alipay_private_key:$('#t_alipay_private_key').val(),
				t_alipay_public_key:$('#t_alipay_public_key').val()
			},
			dataType : 'json',
			success:function(data){
				if (data.m_istatus == 1) {
					$("#myModal").modal('hide');
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
			url:path + '/admin/delAlipaySetUp.htm',
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






 



