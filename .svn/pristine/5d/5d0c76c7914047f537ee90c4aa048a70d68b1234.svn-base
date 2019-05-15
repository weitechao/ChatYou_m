<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	 
	<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-datepicker3.css">
</head>
<body>
	<div class="htmleaf-container">
		 
		<div class="container">
			
			<div class="row" style="min-height: 500px;margin-top: 10px;">
				<div class="col-md-5">
					<h3>请选择一个日期：</h3>
					<div class="input-group date datepicker">
					    <input type="text" class="form-control">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th "></span>
					    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="js/jquery-2.0.3.min.js" type="text/javascript"></script>
	<!-- <script>window.jQuery || document.write('<script src="js/jquery-2.0.3.min.js"><\/script>');</script> -->
	<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
	<script type="text/javascript">
		$(function(){
			$('.datepicker').datepicker({
				language: 'zh-CN',
				format: "yyyy-mm-dd"
			});
		});
	</script>
</body>
</html>


