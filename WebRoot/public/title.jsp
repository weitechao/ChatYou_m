<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- bootstrap-css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
<!-- font-awesome icons -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css" type="text/css" />
<!-- //font-awesome icons -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/morris.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ladda.min.css">
<!-- 表格CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap-table/bootstrap-table.min.css"	rel="stylesheet">
<link rel="stylesheet" type="text/css" 	href="${pageContext.request.contextPath}/css/table-style.css" />
<!--jquery.ui.时间控件  -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker3.css"> --%>
<script>
      
        (function () {
          var js;
          if (typeof JSON !== 'undefined' && 'querySelector' in document && 'addEventListener' in window) {
        	  js = '../js/jquery1.9.1.min.js';
          } else {
        	  js = '../js/jquery1.9.1.min.js';
          }
          document.write('<script src="' + js + '"><\/script>');
        }());
 </script>
<script src="${pageContext.request.contextPath}/js/modernizr-1.6.min.js"></script>
<script src="${pageContext.request.contextPath}/js/screenfull.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-zh.js"></script>
<script src="${pageContext.request.contextPath}/javascript/top.js"></script>







