 $(function(){
	 getTotalRecharge();
	 getToDayRecharge();
	 getTotalUser();
	 getToDayUser();
	 statisticsSexProportion();
	 statisticsSevenDays();
	 statisticsYearRecharge();
	 statisticsYearMembere();
 });

/**
 * 获取总充值金额
 */
function getTotalRecharge(){
	var res = this.ajaxMethod(path+'/admin/getTotalRecharge.htm');
	$("#totalRecharge").text("￥"+res);
}

/**
 * 今日充值
 */
function getToDayRecharge(){
	var res = this.ajaxMethod(path+'/admin/getToDayRecharge.htm');
	$("#toDayRecharge").text("￥"+res);
}

/**
 * 总用户数
 */
function getTotalUser(){
	var res = this.ajaxMethod(path+'/admin/getTotalUser.htm');
	$("#totalUser").text(res);
}

/**
 * 今日新增
 */
function getToDayUser(){
	var res = this.ajaxMethod(path+'/admin/getToDayUser.htm');
	$("#toDayUser").text(res);
}

/**
 * 获取性别比例
 */
function statisticsSexProportion(){
	
	Morris.Donut({
		  element: 'graph4',
		  data: ajaxMethod(path+"/admin/getCountSexDistribution.htm"),
		  formatter: function (x, data) { return data.formatted; }
	});
}

/**
 * 统计近7天的充值和活跃数据
 */
function statisticsSevenDays(){
	Morris.Line({  
		element: 'graph1',  
		data: ajaxMethod(path+"/admin/getSevenDaysList.htm"),
        xkey: "days",
        ykeys: ["日活跃", "日充值"],
        labels: ["日活跃", "日充值"],
        hideHover: "auto",
        lineWidth: 2,
        pointSize: 4,
        lineColors: ["#a0dcee", "#f1c88e", "#a0e2a0"],
        fillOpacity: 0.5,
        smooth: true
	});
}

/**
 * 统计年度会员
 */
function statisticsYearMembere(){
	//月充值
	Morris.Bar({
	  element: 'graph5',
	  data: ajaxMethod(path+"/admin/getYearMembere.htm"),
	  xkey: 'days',
	  ykeys: ['会员'],
	  labels: ['会员'],
      xLabelAngle: 35,
      barColors: ["#04B404"],
	});
}



/**
 * 统计年度充值数据
 */
function statisticsYearRecharge(){
	//月充值
	Morris.Bar({
	  element: 'graph6',
	  data: ajaxMethod(path+"/admin/statisticsYearRecharge.htm"),
	  xkey: 'days',
	  ykeys: ['金额'],
	  labels: ['金额'],
      xLabelAngle: 35,
	  barColors: function (row, series, type) {
		if (type === 'bar') {
		  var red = Math.ceil(255 * row.y / this.ymax);
		  return 'rgb(' + red + ',0,0)';
		}
		else {
		  return '#000';
		}
	  }
	});
}


/**
 * ajax 公共方法
 * @param url
 */
function ajaxMethod(url,parameter){
	
	var res = 1;
	$.ajax({
	    url:url,
	    type:'POST', //GET
	    async:false,    //或false,是否异步
	    data:parameter,
	    timeout:5000,    //超时时间
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    beforeSend:function(xhr){
//	        console.log(xhr)
//	        console.log('发送前')
	    },
	    //得到返回结果
	    success:function(data,textStatus,jqXHR){
	    	res =  data.m_object;
//	    	console.log(res);
	    },
	    //错误异常
	    error:function(xhr,textStatus){
//	        console.log('错误')
//	        console.log(xhr)
//	        console.log(textStatus)
	    },
	    //请求结束
	    complete:function(){
//	        console.log('结束')
	    }
	});
	
	return res;
}
