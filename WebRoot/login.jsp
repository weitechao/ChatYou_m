<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<head>
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<jsp:include page="/public/title.jsp" />
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery1.9.1.min.js" /> --%>
<script type="text/javascript">


/**点击提交*/
function on_click_submit(from){
	 if(document.getElementById("userName").value == "" ||
			 document.getElementById("userName").value == "请输入账号") {
          alert("请输入用户帐号!");
          form.userId.focus();
          return false;
     }
     if(document.getElementById("password").value ==''){
          alert("请输入登录密码!");
          form.password.focus();
          return false;
      }
      document.myform.submit();
}

</script>
</head>
<body class="signup-body">
	<div class="agile-signup">
		<div class="footer-icons">
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
		<div class="footer-icons">
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
		<div class="content2">
			<div class="grids-heading gallery-heading signup-heading">
				<h2>直播系统管理平台</h2>
			</div>
			<form name="myform" action="admin/login.htm" method="post">
				<input type="text" id="userName" name="userName" value="请输入账号"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '请输入账号';}"> 
				<input 	type="password" id="password" name="password" value=""
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '';}">
					
			
				 <input type="button" class="register" value="点击登陆" onclick="return on_click_submit(this.form);">
			</form>
			<div class="signin-text">
				<div class="clearfix"><span style="color: red;">${logerr}</span></div>
			</div>
			
			<div class="footer-icons">
				<ul>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
		</div>
		<div class="footer-icons">
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
		<jsp:include page="/public/lower.jsp" />
	</div>
</body>
</html>
