
/**弹出财务明细 */
function on_click_finance_popup(id){
	//表格重新加载
	$("#tinancialdetails").bootstrapTable('destroy');
	$("#userId").val(id);
	loadTable(id,-1);
	$("#user_money").modal('show');
	get_total_money(id)
}

/**加载财务明细*/
function loadTable(id,type){
	 $('#tinancialdetails').bootstrapTable({
	        url: path+ 'admin/getUserFinancialDetails.htm?userId='+id+'&type='+type,
	        method: 'post',
	        contentType: "application/x-www-form-urlencoded",
	        queryParamsType:'', 
	        queryParams:function (params){
	        	return {
	                pageSize: params.limit, // 每页要显示的数据条数
	                page: params.pageNumber, // 每页显示数据的开始行号  
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
	              		title:'项目类别',
	              		align:'center',
	              		field:'t_change_type',
	              		formatter:function(value){
	              			return  value == 0?'收入':'支出'
	              		}
	              	},
	              	{
	              		title:'项目类型',
	              		align:'center',
	              		field:'t_change_category',
	              		formatter:function(value,row,index){
	              			if(value == 0){
	              			  return '充值';
	              			}else if(value == 1){
	              				return '私密聊天';
	              			}else if(value == 2){
	              				return '视频聊天';
	              			}else if(value == 3){
	              				return '私密照片';
	              			}else if(value == 4){
	              				return '私密视频';
	              			}else if(value == 5){
	              				return '查看手机';
	              			}else if(value == 6){
	              				return '查看微信';
	              			}else if(value == 7){
	              				if(row.t_redpacket_type == 0){
	              					return '用户赠送红包';
	              				}else if(row.t_redpacket_type == 2) {
	              					return '主播认证红包';
	              				}else if(row.t_redpacket_type == 3) {
	              					return '后台赠送红包';
	              				}

	              			}else if(value == 8){
	              				return 'VIP';
	              			}else if(value == 9){
	              				return '礼物';
	              			}else if(value == 10){
	              				return '提现';
	              			}else if(value == 11){
	              				return '推荐分成';
	              			}else if(value == 12){
	              				return '提现失败返回';
	              			}else if(value == 13){
	              				return '注册赠送';
	              			}else if(value == 14){
	              				return '公会分成';
	              			}
	              			
	              		}
	              	},
	              	{
	              		title:'描述',
	              		field:'detail',
	              		align:'center'
	              	},{
	              		title:'变动前(金币)',
	              		field:'t_change_front',
	              		align:'center'
	              	},
	              	{
	              		title:'变动值(金币)',
	              		field:'t_value',
	              		align:'center',
	              		formatter:function(value,row,index){
	              			if(row.t_change_type == 1){
	              				value = '<span style="color:red;">-'+value+'</span>';
	              			}
	              			return value;
	              		}
	              	},{
	              		title:'变动后(金币)',
	              		field:'t_change_after',
	              		align:'center'
	              	},
	              	{
	              		title:'时间',
	              		field:'t_change_time',
	              		align:'center'              		
	              	}
	        ]
	    });
}

/**获取用户的可使用金币*/
function get_total_money(id){
	$.ajax({
		type : 'POST',
		url : path + 'admin/getUserTotalMoney.htm',
		data : {userId : id	},
		dataType : 'json',
		success : function(data) {
			if (data.m_istatus == 1) {
				if(data.m_object == null){
					$("#totalMoney").val("0 (金币)");
				}else{
					$("#totalMoney").val(data.m_object+" (金币)");
				}
			} else  {
				window.location.href = path + '/error.html';
			}
		}
	});
}
/**切换列表*/
function on_click_switch(type){
	var id = $("#userId").val();
	$("#tinancialdetails").bootstrapTable('destroy');
	loadTable(id, type);
}