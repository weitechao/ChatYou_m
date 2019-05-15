<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<title>系统设置</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content=""/>
<jsp:include page="/public/title.jsp" />
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/javascript/addSystemSetUp.js"></script>
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
									   <input id="t_id" type="hidden">
										<div class="form-group">
											<label class="col-md-2 control-label">附近地图范围:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-indent-left"></i>
													</span>
													<input type="text" id="t_scope" name="t_scope" class="form-control1" id="exampleInputPassword1" placeholder="地图范围设置(公里)." onfocus="on_focus('age','地图范围设置(公里).');" onblur="on_blur('age')">
												</div>
											</div>
											<div class="col-sm-2" id="t_scope_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">默认文本收费:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-text-color"></i>
													</span>
													<input type="text" id="t_default_text"  class="form-control1" placeholder="默认文本聊天收费...." onfocus="on_focus('t_default_text','默认文本聊天收费....;')" onblur="on_blur('t_default_text')">
												</div>
											</div>
											<div class="col-sm-2" id="t_default_text_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">默认视频收费:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-sd-video"></i>
													</span>
													<input type="text" id="t_default_video" class="form-control1" placeholder="默认视频聊天收费..." onfocus="on_focus('t_default_video','默认视频聊天收费....');" onblur="on_blur('t_default_video')">
												</div>
											</div>
											<div class="col-sm-2" id="t_default_video_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">查看手机号收费:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-phone"></i>
													</span>
													<input type="text" id="t_default_phone" class="form-control1" placeholder="默认查看手机收费...,如不显示在前端输入0" onfocus="on_focus('t_default_phone','默认查看手机收费...,如不显示在前端输入0.');" onblur="on_blur('t_default_phone')">
												</div>
											</div>
											<div class="col-sm-2" id="t_default_phone_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">查看微信收费:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-queen"></i>
													</span>
													<input type="text" id="t_default_weixin"  class="form-control1" placeholder="默认查看微信收费.." onfocus="on_focus('t_default_weixin','默认查看微信收费..');" onblur="on_blur('t_default_weixin')">
												</div>
											</div>
											<div class="col-sm-2" id="t_default_weixin_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">客服QQ:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-queen"></i>
													</span>
													<input type="text" id="t_service_qq"  class="form-control1" placeholder="客服qq" onfocus="on_focus('t_service_qq','客服qq..');" onblur="on_blur('t_service_qq')">
												</div>
											</div>
											<div class="col-sm-2" id="t_service_qq_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">Android分享地址:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-arrow-up"></i>
													</span>
													<input type="text" id="t_android_download" name="t_android_download" class="form-control1" placeholder="Android分享下载地址..." onfocus="on_focus('t_android_download','Android分享下载地址...');" onblur="on_blur('t_android_download')">
												</div>
											</div>
											<div class="col-sm-2" id="t_android_download_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">IOS分享地址:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-th-large"></i>
													</span>
													<input type="text" id="t_ios_download" name="t_ios_download" class="form-control1" placeholder="请输入IOS版本下载地址.." onfocus="on_focus('t_ios_download','请输入IOS版本下载地址..');" onblur="on_blur('t_ios_download')">
												</div>
											</div>
											<div class="col-sm-2"  id="t_ios_download_validate" >
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">欢迎语(男):</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-indent-left"></i>
													</span>
													<textarea id="t_system_lang_male" name="t_system_lang_male" class="form-control" rows="3" placeholder="Enter ..." onfocus="on_focus('t_system_lang_male','请输入系统欢迎语...');" onblur="on_blur('t_system_lang_male')"></textarea>
												</div>
											</div>
											<div class="col-sm-2" id="t_system_lang_male_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">欢迎语(女):</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-indent-left"></i>
													</span>
													<textarea id="t_system_lang_girl" name="t_system_lang_girl" class="form-control" rows="3" placeholder="Enter ..." onfocus="on_focus('t_system_lang_girl','请输入系统欢迎语...');" onblur="on_blur('t_system_lang_girl')"></textarea>
												</div>
											</div>
											<div class="col-sm-2" id="t_system_lang_girl_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">视频提示语:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-indent-left"></i>
													</span>
													<textarea id="t_video_hint" name="t_video_hint" class="form-control" rows="3" placeholder="Enter ..." onfocus="on_focus('t_video_hint','请输入视频警示语...');" onblur="on_blur('t_video_hint')"></textarea>
												</div>
											</div>
											<div class="col-sm-2" id="t_award_rules_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">速配提示语:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-indent-left"></i>
													</span>
													<textarea id="t_spreed_hint" name="t_spreed_hint" class="form-control" rows="3" placeholder="Enter ..." onfocus="on_focus('t_spreed_hint','请输入速配警示语..');" onblur="on_blur('t_spreed_hint')"></textarea>
												</div>
											</div>
											<div class="col-sm-2" id="t_award_rules_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">昵称过滤:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-indent-left"></i>
													</span>
													<textarea id="t_nickname_filter" name="t_nickname_filter" class="form-control" rows="3" placeholder="请输入昵称过滤词(过个关键字以逗号分割,)."></textarea>
												</div>
											</div>
											<div class="col-sm-2">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">奖励规则:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-indent-left"></i>
													</span>
													<textarea id="t_award_rules" name="t_award_rules" class="form-control" rows="8" placeholder="Enter ..." onfocus="on_focus('t_system_lang','请输入系统欢迎语...');" onblur="on_blur('t_system_lang')"></textarea>
												</div>
											</div>
											<div class="col-sm-2" id="t_award_rules_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div  class="grid_3 grid_5 wow fadeInUp animated" data-wow-delay=".5s" style="margin-left: 77%;">
											<h4>
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

