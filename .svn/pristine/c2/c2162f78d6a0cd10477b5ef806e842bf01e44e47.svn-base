<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>自适应显示多个点标记</title>
    <jsp:include page="/public/title.jsp" />
    <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css"/>
    <style>
        html, body, #container {
            height: 100%;
            width: 100%;
        }

        .amap-icon img{
            width: 25px;
            height: 34px;
        }
    </style>
</head>
<body>
<jsp:include page="/public/menu.jsp" />
<div id="container"></div>

<!-- <div class="input-card"> -->
<!--     <h4>地图自适应</h4> -->
<!--     <input id="setFitView" type="button" class="btn" value="地图自适应显示" /> -->
<!-- </div> -->
<!-- <div class="info"> -->
<!--     <div id="centerCoord"></div> -->
<!-- </div> -->
<script type="text/javascript"  src="https://webapi.amap.com/maps?v=1.4.10&key=f12f8568bcf14b02ab5cf67cc82be8d9"></script>
<script type="text/javascript">
var path ='${pageContext.request.contextPath}';
	var map = new AMap.Map('container', {
	    resizeEnable: true,
	    center: [116.397428, 39.90923],
	    zoom: 13
	});
	
	map.clearMap();  // 清除地图覆盖物

	$.ajax({
		type : 'POST',
		url :path+"/admin/getOnLineUserMapList.htm",
		dataType : 'json',
		success : function(data) {
			 // 添加一些分布不均的点到地图上,地图上添加三个点标记，作为参照
			    data.m_object.forEach(function(marker) {
			     
// 			    	var obj = eval('(' + marker + ')');
// 			    	console.log(1111);
// 			    	console.log(obj);
			        new AMap.Marker({
			            map: map,
			            icon: '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png',
			            position: [marker.t_lng, marker.t_lat],
			            offset: new AMap.Pixel(-13, -30),
			            title:marker.title
			        });
			    });
			    map.setFitView();
		}
	});
   

    var center = map.getCenter();

//     var centerText = '当前中心点坐标：' + center.getLng() + ',' + center.getLat();
//     document.getElementById('centerCoord').innerHTML = centerText;

    // 添加事件监听, 使地图自适应显示到合适的范围
//     AMap.event.addDomListener(document.getElementById('setFitView'), 'click', function() {
//         var newCenter = 
//         document.getElementById('centerCoord').innerHTML = '当前中心点坐标：' + newCenter.getCenter();
//     });
</script>
</body>
</html>



