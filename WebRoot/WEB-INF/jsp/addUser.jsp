<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<title>添加用户</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content=""/>
<jsp:include page="/public/title.jsp" />
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/javascript/adduser.js"></script>
<script type="text/javascript">
var path = '${pageContext.request.contextPath}';
</script>
</head>
<body class="dashboard-page">

	<jsp:include page="/public/menu.jsp" />
	<section class="wrapper scrollable">
	    <section class="title-bar">
			 <jsp:include page="/public/top.jsp" />
		</section>
		<div class="main-grid">
			<div class="agile-grids">	
				<!-- input-forms -->
				<div class="grids">
					<div class="panel panel-widget forms-panel w3-last-form">
						<div class="forms">
							<div class="form-three widget-shadow">
								<div class=" panel-body-inputin">
									<form class="form-horizontal" id="myFrom" action="${pageContext.request.contextPath}/admin/saveUser.htm" method="post">
										<div class="form-group">
											<label class="col-md-2 control-label">用户昵称:</label>
											<div class="col-md-8">
												<div class="input-group">							
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-user"></i>
													</span>
													<input type="text" id="nickName" name="t_nickName" class="form-control1" placeholder="请出入用户昵称" onfocus="on_focus('nickName','请输入昵称.');" onblur="on_blur('nickName')">
												</div>
											</div>
											<div class="col-sm-2" id="nickName_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">手机号码:</label>
											<div class="col-md-8">
												<div class="input-group">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-earphone"></i>
													</span>
													<input type="text" id="phone" name="t_phone" class="form-control1"  placeholder="请输入手机号码">
												</div>
											</div>
											<div class="col-sm-2" id="phone_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
											<div class="col-md-8">
												<div class="input-group">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-adjust"></i>
													</span>
													 <select id="sex" style="height: 40px;" name="t_sex" class="form-control">
									                      <option value="1">男</option>
									                      <option value="0">女</option>
							                   		 </select>
												</div>
											</div>
											<div class="col-sm-2">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-indent-left"></i>
													</span>
													<input type="text" id="age" name="t_age" class="form-control1" id="exampleInputPassword1" placeholder="请输入年龄" onfocus="on_focus('age','请输入年龄.');" onblur="on_blur('age')">
												</div>
											</div>
											<div class="col-sm-2" id="age_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">身&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;高:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-arrow-up"></i>
													</span>
													<input type="text" id="t_height" name="t_height" class="form-control1" placeholder="请输入身高(CM)" onfocus="on_focus('t_height','请输入身高(CM).');" onblur="on_blur('t_height')">
												</div>
											</div>
											<div class="col-sm-2" id="t_height_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">体&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-th-large"></i>
													</span>
													<input type="text" id="t_weight" name="t_weight" class="form-control1" placeholder="请输入体重(KG)" onfocus="on_focus('t_weight','请输入体重(KG).');" onblur="on_blur('t_weight')">
												</div>
											</div>
											<div class="col-sm-2"  id="t_weight_validate" >
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">星&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;座:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-star"></i>
													</span>
													 <select id="t_constellation" style="height: 40px;" name="t_constellation" class="form-control">
									                      <option value="白羊座">白羊座</option>
									                      <option value="金牛座">金牛座</option>
									                      <option value="双子座">双子座</option>
									                      <option value="巨蟹座">巨蟹座</option>
									                      <option value="狮子座">狮子座</option>
									                      <option value="处女座">处女座</option>
									                      <option value="天秤座">天秤座</option>
									                      <option value="天蝎座">天蝎座</option>
									                      <option value="射手座">射手座</option>
									                      <option value="摩羯座">摩羯座</option>
									                      <option value="水瓶座">水瓶座</option>
									                      <option value="双鱼座">双鱼座</option>
							                   		 </select>
												</div>
											</div>
											<div class="col-sm-2">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市:</label>
											<div class="col-md-8" >
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-globe"></i>
													</span>
													<input type="text" id="t_city" name="t_city" class="form-control1" placeholder="请输入城市" onfocus="on_focus('t_city','请输入城市.');" onblur="on_blur('t_city')">
												</div>
											</div>
											<div class="col-sm-2" id="t_city_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-oil"></i>
													</span>
												    <select id="t_vocation" style="height: 40px;" name="t_vocation" class="form-control">
									                      <option value="网红">网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;红</option>
									                      <option value="模特">模&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特</option>
									                      <option value="白领">白&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;领</option>
									                      <option value="护士">护&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;士</option>
									                      <option value="空姐">空&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姐</option>
									                      <option value="学生">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生</option>
									                      <option value="健身教练">健身教练</option>
									                      <option value="医生">医&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生</option>
									                      <option value="客服">客&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服</option>
									                      <option value="其他">其&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;他</option>
							                   		 </select>
												</div>
											</div>
											<div class="col-sm-2">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<!-- <div class="form-group">
											<label class="col-md-2 control-label">个人介绍:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-text-width"></i>
													</span>
													<textarea id="t_synopsis" name="t_synopsis" class="form-control" rows="3" placeholder="Enter ..." onfocus="on_focus('t_synopsis','请输入个人介绍.');" onblur="on_blur('t_synopsis')"></textarea>
												</div>
											</div>
											<div class="col-sm-2" id="t_synopsis_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div> -->
										<div class="form-group">
											<label class="col-md-2 control-label">个性签名:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-indent-left"></i>
													</span>
													<textarea id="t_autograph" name="t_autograph" class="form-control" rows="3" placeholder="Enter ..." onfocus="on_focus('t_autograph','请输入个性签名.');" onblur="on_blur('t_autograph')"></textarea>
												</div>
											</div>
											<div class="col-sm-2" id="t_autograph_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">角色类型:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-star"></i>
													</span>
													 <select id="t_role" style="height: 40px;" name="t_role" class="form-control">
									                      <option value="1">主播会员</option>
									                      <option value="0">普通会员</option>
							                   		 </select>
												</div>
											</div>
											<div class="col-sm-2">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
											<div class="form-group">
											<label class="col-md-2 control-label">个人状态:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-star"></i>
													</span>
													 <select id="t_state" style="height: 40px;" name="t_state" class="form-control">
									                      <option value="0">空闲</option>
									                      <option value="1">忙碌</option>
									                      <option value="2">离线</option>
							                   		 </select>
												</div>
											</div>
											<div class="col-sm-2">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div  class="grid_3 grid_5 wow fadeInUp animated" data-wow-delay=".5s" style="margin-left: 70%;">
											<h4>
											    <a href="javascript:void(0);" onclick="on_click_reset();"><span class="label label-warning"><i class="glyphicon glyphicon-refresh"></i>&nbsp;&nbsp;重&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;置</span></a>
												<a href="javascript:void(0);" onclick="on_click_submit();"><span class="label label-success"><i class="glyphicon glyphicon-floppy-save"></i>&nbsp;&nbsp;保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存</span></a>
											</h4>
										</div>
									
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- //input-forms -->
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="/public/lower.jsp" />
		<!-- //footer -->
	</section>
</body>
</html>

