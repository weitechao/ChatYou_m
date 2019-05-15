<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<head>
<title>用户列表</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<jsp:include page="/public/title.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fileinput.css" media="all"	type="text/css" />
<script src="${pageContext.request.contextPath}/js/fileinput.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/fileinput_locale_zh.js" type="text/javascript"></script>
<script>
	var path = '<%=basePath%>';
</script>
</head>
<body class="dashboard-page">
	<jsp:include page="/public/menu.jsp" />
	<section class="wrapper scrollable">
		<section class="title-bar">
			 <jsp:include page="/public/top.jsp"><jsp:param value="user" name="pageName"/></jsp:include>
		</section>
		<div class="main-grid">
			<div class="agile-grids">
			    <a class="btn btn-default" href="${pageContext.request.contextPath}/menu/addUser.htm">点击添加</a>
			    <div style="float: right;width: 85%">
			     	<button class="btn btn-default" style="float: left;">性别:</button>
			     	<select id="t_sex" onchange="on_sex_onchange();"  style="width: 100px;float: left;height: 36px;margin-right: 2%" class="form-control">
                      <option value="-1" >全部</option>
                      <option value="0">女</option>
                      <option value="1">男</option>
					</select>
					<button class="btn btn-default" style="float: left;">角色:</button>
			     	<select id="t_role" onchange="on_role_onchange();"  style="width: 150px;height: 36px;float: left;margin-right: 2%" class="form-control">
	                      <option value="-1">全部</option>
	                      <option value="0">所有用户</option>
	                      <option value="1">所有主播</option>
	                      <option value="2">推荐主播</option>
	                      <option value="3">免费主播</option>
					</select>
					<button class="btn btn-default" style="float: left;">条件:</button>
				 	<input type="text" class="form-control" id="condition" style="height: 36px; float: left;width: 200px;margin-right: 2%"  placeholder="请输入手机号或者昵称">
			        <span class="btn btn-default" style="float: left;">开始日期:</span>
					<div class="input-group" style="width: 200px;float: left;margin-right: 20px;">
					    <input type="text" class="form-control" id="beginTime" style="height: 36px;">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th "></span>
					    </div>
					</div>
					<span class="btn btn-default" style="float: left;">结束时间:</span>
					<div class="input-group" style="width: 200px;float: left;margin-right: 20px;">
					    <input type="text" class="form-control" id="endTime" style="height: 36px;">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th "></span>
					    </div>
					</div>
					<button class="btn btn-default" onclick="on_click_search();" style="float: right;margin-right: 0;"><i class="fa fa-search" aria-hidden="true"></i></button>
			    </div>
			    <br/>
				<div class="agile-tables">
					<div class="w3l-table-info">
						<table id="utable" class="table table-hover"></table>
					</div>
				</div>
			</div>
		</div>
		<!-- 删除模态框（Modal） -->
		<div class="modal fade" id="myDel" tabindex="-1" role="dialog" aria-labelledby="myDelLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 18%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color:#FFD700;">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title " id="myModalLabel">
						   <span class="glyphicon glyphicon-warning-sign"></span>
							警告
						</h4>
					</div>
					<div class="modal-body">
						确定要进行操作吗？
						<input type="hidden" id="del_id">
						<input type="hidden" id="state">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_change_res();">
							<i class="glyphicon glyphicon-ok"></i>确认
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<!-- 新增或修改模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-pencil"></span> 封号设置
						</h4>
					</div>
					<div class="modal-body">
					<input type="hidden" id="t_id">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">用户昵称:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-grain"></i>
									</span> <input type="text" id="nick_name" name="nick_name"
										class="form-control1" disabled="disabled" placeholder="">
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">封号时间:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-time"></i>
									</span> <input type="text" id="freeze_time" name="freeze_time"
										class="form-control1" placeholder="请输入时间.单位为分钟(-1为永久封号.)">
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">封号说明:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-font"></i>
									</span> <textarea id="pushConnent" class="form-control" rows="2" placeholder="Enter ..."></textarea>
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_freeze_ones();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 头像预览 -->
		<div class="modal fade" id="img_preview" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-picture"></span> 头像预览
						</h4>
					</div>
					<div class="modal-body">
						<div>
							<div class="agile-gallery-grid">
								<div class="agile-gallery">
								   <img id="hand_img" src="${pageContext.request.contextPath }/images/g4.jpg" alt="" width="60%"/>
								</div>
							</div>
						</div>
						<br/>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 预览图 -->
		<div class="modal fade" id="cover_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 56%">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-picture"></span> 封面预览
						</h4>
					</div>
					<div class="modal-body" id="cover_modal_div">
					   
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #87CEFA;">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 标签选择 -->
		<div class="modal fade" id="user_lable" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 30%;" >
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
						  <span class="glyphicon glyphicon-picture"></span> 标签
						</h4>
						<input type="hidden" id="lable_user_id">
					</div>
					<br/>
					<div class="form-group" style="height: 100px;">
						<div class="col-md-10">
							<div class="input-group" style="margin-left: 10%;">
							  <div class="checkbox-inline1">
							  </div>
							</div>
						</div>
					</div>
					<br/>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #87CEFA;">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_lable_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 给主播评价 -->
		<div class="modal fade" id="user_criticism" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 40%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<span class="glyphicon glyphicon-pencil"></span> 评论
						</h4>
						<input type="hidden" id="t_anchor_id">
					</div>
					<div class="modal-body">
						<br/>
						<div class="form-group" style="height: 40px;">
							<label class="col-md-2 control-label">评论昵称:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> 
								    <select id="t_content_user" style="height: 40px;" name="t_sex" class="form-control">
								       <option value="1">男</option>
								       <option value="0">女</option>
							        </select>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div class="form-group" style="height: 40px;">
							<label class="col-md-2 control-label">评论分值:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id = "t_score"
										class="form-control1"  placeholder="请输入评论分值.">
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/> 
						<div class="form-group" style="height: 80px;">
						    <label class="col-md-2 control-label">评论标签:</label>
							<div class="col-md-8">
								<div class="input-group" style="margin-left: 5%;">
								  <div class="checkbox-criticism">
								  </div>
								</div>
							</div>
					    </div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" style="background-color: #87CEFA;" data-dismiss="modal">
								<span class="glyphicon glyphicon-remove"></span>关闭
							</button>
							<button type="button" class="btn btn-primary" onclick="on_click_content_submit();">
							    <span class="glyphicon glyphicon-floppy-disk"></span>提交
						    </button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
			<!-- //footer -->
		</div>
		<!-- 推送（Modal） -->
		<div class="modal fade" id="pushModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-phone"></span> 推送
						</h4>
					</div>
					<div class="modal-body">
					<input type="hidden" id="push_user_id">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">用户昵称:</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="push_nick" 
										class="form-control1" disabled="disabled" placeholder="">
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">推送内容:</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-font"></i>
									</span> <textarea id="push_connent" class="form-control" rows="3"
									 placeholder="Enter ..." onfocus="on_focus('push_connent', '请输入推送内容!')"
										onblur="on_blur('push_connent')"></textarea>
								</div>
							</div>
							<div class="col-sm-3" id="push_connent_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<br>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_push_msg();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 赠送金币 -->
		<div class="modal fade" id="giveModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-phone"></span> 赠送金币
						</h4>
					</div>
					<div class="modal-body">
					<input type="hidden" id="give_user_id">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">用户昵称:</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="give_nick" 
										class="form-control1" disabled="disabled" placeholder="">
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">赠送金币:</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-font"></i>
									</span>
									 <input type="text" id="give_gold" 
										class="form-control1" placeholder="请输入赠送金币数!" onfocus="on_focus('give_gold', '请输入赠送金币数!')"
										onblur="on_validate_isNaN('give_gold')">
								</div>
							</div>
							<div class="col-sm-3" id="give_gold_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<br>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_give_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 推荐设置 -->
		<div class="modal fade" id="nominateModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-phone"></span> 设置推荐
						</h4>
					</div>
					<div class="modal-body">
					<input type="hidden" id="nominate_user_id">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">用户昵称:</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="t_nick_name" 
										class="form-control1" disabled="disabled" placeholder="">
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">主播排序:</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="t_sort" 
										class="form-control1" placeholder="请输入推荐主播排序值.(默认99)">
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">是否推荐:</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-adjust"></i>
									</span> 
									<select id="t_is_nominate"  class="form-control">
										<option value="0">否</option>
										<option value="1">是</option>
									</select>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<br>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_nominate_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 免费主播 -->
		<div class="modal fade" id="freeAnchorModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-yen"></span> 设置免费主播
						</h4>
					</div>
					<div class="modal-body">
					<input type="hidden" id="t_free_user_id">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">用户昵称:</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="t_free_nick_name" 
										class="form-control1" disabled="disabled" placeholder="">
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">是否免费:</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-adjust"></i>
									</span> 
									<select id="t_is_free"  class="form-control">
										<option value="-1">否</option>
										<option value="1">是</option>
									</select>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<br>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_free_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 免费主播 -->
		<div class="modal fade" id="refereeModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 40%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span></span> 重设推广人
						</h4>
					</div>
					<div class="modal-body">
					<input type="hidden" id="t_referee_user_id">
						<div style="height: 40px;">
							<label class="col-md-3 control-label">平台IdCard:</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> 
									<input type="text" id="t_referee_id" 
										class="form-control1" placeholder="请输入推广人平台IdCard号..">
								</div>
							</div>
							<div class="col-sm-2" id="t_referee_id_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<br>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_upRefereeUser();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="anthor_online_time" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 56%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
						  <span class="glyphicon glyphicon-picture"></span> 主播在线
						</h4>
						<input type="hidden" id="anthor_id">
					</div>
					<br/>
					<div style="float: right;width: 68%">
			        <span class="btn btn-default" style="float: left;">开始日期:</span>
					<div class="input-group"  style="width: 200px;float: left;margin-right: 20px;">
					    <input type="text" class="form-control" id="anthor_begin_time" style="z-index:100000" style="height: 36px;">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th"></span>
					    </div>
					</div>
					<span class="btn btn-default" style="float: left;">结束时间:</span>
					<div class="input-group" style="width: 200px;float: left;margin-right: 20px;">
					    <input type="text" class="form-control" id="anthor_end_time" style="z-index:100000" style="height: 36px;">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th "></span>
					    </div>
					</div>
					<button class="btn btn-default" onclick="search_anthor_time();" style="float: right;margin-right:20px;"><i class="fa fa-search" aria-hidden="true"></i></button>
			    </div>
					<div class="agile-tables">
						<div class="w3l-table-info">
							<table id="anthorTable" class="table table-hover" style="width: 98%;margin-left: 10px;"></table>
						</div>
				    </div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #87CEFA;">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<jsp:include page="/public/upload.jsp"/>
		<jsp:include page="/public/userModel.jsp"/>
		<jsp:include page="/public/financialdetails.jsp"/>
		<jsp:include page="/public/lower.jsp" />
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/userList.js"></script>

</body>
</html>
