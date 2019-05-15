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
<title>活动列表</title>
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
			    <a class="btn btn-default" style="float: right;" href="javascript:on_click_saveOrUpdate(0);">点击添加</a>
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
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_del();">
							<i class="glyphicon glyphicon-ok"></i>确认
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<!-- 新增或修改模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 50%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<span class="glyphicon glyphicon-pencil"></span> 新增或修改
							 <input type="hidden" id="t_id">
						</h4>
					</div>
					<div class="modal-body">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">活动名称:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-list"></i>
									</span> <input type="text" id="t_activity_name"
										class="form-control1" placeholder="请输入活动名称."  onfocus="on_focus('t_activity_name', '请输入活动名称.')"
										onblur="on_blur('t_activity_name')">
								</div>
							</div>
							<div class="col-sm-2" id="t_activity_name_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">奖励名额:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-retweet"></i>
									</span> <input type="text" id="t_activity_number" class="form-control1" placeholder="请输入活动总中奖名额." onfocus="on_focus('t_activity_number', '请输入活动总中奖名额.')"
										onblur="on_blur('t_activity_number')">
								</div>
							</div>
							<div class="col-sm-2" id="t_activity_number_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">参与条件:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-retweet"></i>
									</span> <input type="text" id="t_join_term" class="form-control1" placeholder="请输入活动参与条件(分享次数)" onfocus="on_focus('t_join_term', '请输入活动参与条件(分享次数)')"
										onblur="on_blur('t_join_term')">
								</div>
							</div>
							<div class="col-sm-2" id="t_join_term_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">开始时间:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-th"></i>
									</span>
									 <input type="text" class="form-control" placeholder="请选择活动开始时间." style="z-index:100000" id="t_begin_time" >
								</div>
							</div>
							<div class="col-sm-2" id="t_begin_time_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">结束时间:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-th"></i>
									</span>
									 <input type="text" class="form-control" placeholder="请选择活动结束时间.." style="z-index:100000" id="t_end_time" >
								</div>
							</div>
							<div class="col-sm-2" id="t_end_time_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						 
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">是否启用:</label>
							<div class="col-md-8">
								<div class="input-group input-icon right">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-star"></i>
									</span>
									 <select id="t_is_enable" style="height: 40px;" name="t_is_enable" class="form-control">
					                      <option value="0">启用</option>
					                      <option value="1">禁用</option>
			                   		 </select>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary"
							onclick="on_click_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 加载奖项明细（Modal） -->
		<div class="modal fade" id="my_activity_detail" tabindex="-1" role="dialog" aria-labelledby="myDelLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 50%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title " id="myModalLabel">
						   <span class="glyphicon glyphicon-gift"></span>
							奖项明细
						</h4>
					</div>
					<div class="modal-body">
						<a class="btn btn-default" style="float: right;" href="javascript:on_click_save_prize();">点击添加</a>
						<div class="agile-tables">
							<div class="w3l-table-info">
								<table id="activity_detail" class="table table-hover"></table>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>关闭
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		
		<!-- 添加奖励明细（Modal） -->
		<div class="modal fade" style="z-index:10000" id="prize_detail" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 50%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<span class="glyphicon glyphicon-pencil"></span> 新增或修改
							 <input type="hidden" id="t_activity_id">
							 <input type="hidden" id="t_detail_id">
						</h4>
					</div>
					<div class="modal-body">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">奖品名称:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-list"></i>
									</span> <input type="text" id="t_prize_name"
										class="form-control1" placeholder="请输入奖品名称."  onfocus="on_focus('t_prize_name', '请输入奖品名称.')"
										onblur="on_blur('t_prize_name')">
								</div>
							</div>
							<div class="col-sm-2" id="t_prize_name_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">奖品数量:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-retweet"></i>
									</span> <input type="text" id="t_prize_number" class="form-control1" placeholder="请输入该奖项名额." onfocus="on_focus('t_prize_number', '请输入该奖项名额.')"
										onblur="on_blur('t_prize_number')">
								</div>
							</div>
							<div class="col-sm-2" id="t_prize_number_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">中奖数量:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-retweet"></i>
									</span> <input type="text" id="t_prize_size" class="form-control1" placeholder="请输入中奖数量,区间值已,分割" onfocus="on_focus('t_prize_size', '请输入中奖数量,区间值已,分割')"
										onblur="on_blur('t_prize_size')">
								</div>
							</div>
							<div class="col-sm-2" id="t_prize_size_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">是否启用:</label>
							<div class="col-md-8">
								<div class="input-group input-icon right">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-star"></i>
									</span>
									 <select id="t_is_join" style="height: 40px;" class="form-control">
					                      <option value="0">启用</option>
					                      <option value="1">禁用</option>
			                   		 </select>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary"
							onclick="on_click_prize_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<jsp:include page="/public/lower.jsp" />
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/activityList.js"></script>

</body>
</html>
