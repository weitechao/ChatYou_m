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
<title>充值与提现</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<jsp:include page="/public/title.jsp" />
 
<script type="text/javascript">
	var path = '<%=basePath%>';
</script>
<!-- //tables -->
</head>
<body class="dashboard-page">
	<jsp:include page="/public/menu.jsp" />
	<section class="wrapper scrollable">
		<section class="title-bar">
			 <jsp:include page="/public/top.jsp" />
		</section>
		<div class="main-grid">
			<div class="agile-grids">
			    <a class="btn btn-default" style="background-color: #87CEFA" href="javascript:on_click_open_update_model();">点击添加</a>
			    <div style="float: right;width: 25%">
			     	<button class="btn btn-default" style="float: left;">项目类别:</button>
			     	<select id="search_project_type" onchange="on_project_type_onchange();"  style="width: 100px;float: left;height: 36px;margin-right: 2%" class="form-control">
                      <option value="0">全部</option>
                      <option value="1">充值</option>
                      <option value="2">提现</option>
					</select>
					<button class="btn btn-default" style="float: left;">端类型:</button>
			     	<select id="search_end_type" onchange="on_end_type_onchange();"  style="width: 100px;height: 36px;float: left;margin-right: 2%" class="form-control">
	                      <option value="-1">全部</option>
	                      <option value="0">Android</option>
	                      <option value="1">iPhone</option>
					</select>
					<button class="btn btn-default" onclick="on_click_search();" style="float: right;margin-right: 0;"><i class="fa fa-search" aria-hidden="true"></i></button>
			    </div>
			    </br>
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
			<div class="modal-dialog" style="width: 40%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-pencil"></span>新增或修改
						</h4>
					</div>
					<div class="modal-body">
					<input type="hidden" id="t_id">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">套餐名称:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-grain"></i>
									</span>
									 <select id="t_project_type" style="height: 40px;" name="t_sex" class="form-control">
					                      <option value="1">充值</option>
					                      <option value="2">提现</option>
			                   		 </select>
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">金额(元):</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-yen"></i>
									</span> <input type="text" id="t_money" name="t_money"
										class="form-control1"  placeholder="请输入金额"
										onfocus="on_focus('t_money', '请输入金额.')"
										onblur="on_blur('t_money')"
										onkeydown="on_validate_isNaN('t_money')">
								</div>
							</div>
							<div class="col-sm-2" id="t_money_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">金币(个):</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-calendar"></i>
									</span> <input type="text" id="t_gold" name="t_gold"
										class="form-control1"  placeholder="请输入金币数"
										onfocus="on_focus('t_gold', '请输入金币数')"
										onblur="on_blur('t_gold')">
								</div>
							</div>
							<div class="col-sm-2" id="t_gold_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div class="form-group" style="height: 40px;">
							<label class="col-md-2 control-label">端&nbsp;&nbsp;类&nbsp;&nbsp;型:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-phone"></i>
									</span>
									 <select id="t_end_type" style="height: 40px;" class="form-control">
					                      <option value="0">Android</option>
					                      <option value="1">iPhone</option>
			                   		 </select>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<div class="form-group" style="height: 40px;">
							<label class="col-md-2 control-label">描述:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-phone"></i>
									</span>
									 <textarea id="t_describe"  name="t_synopsis" class="form-control"
								      rows="2" placeholder="Enter ..."></textarea>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div class="form-group">
							<label class="col-md-2 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-adjust"></i>
									</span>
									 <select id="t_is_enable" style="height: 40px;" name="t_sex" class="form-control">
					                      <option value="0">启用</option>
					                      <option value="1">禁用</option>
			                   		 </select>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 预览图 -->
		<jsp:include page="/public/lower.jsp" />
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/cashValueMealList.js"></script>

</body>
</html>
