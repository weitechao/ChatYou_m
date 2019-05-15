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
<title>pay</title>
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
			    <button class="btn btn-default" >提现记录</button>
			    <div style="float: right;width: 53%">
				    <button class="btn btn-default" style="float: left;">提现状态:</button>
				    <select id="type"  style="width: 150px;float: left;margin-right: 20px;height: 36px;" onchange="on_change();" class="form-control">
	                      <option value="-1">全部类型</option>
	                      <option value="0">待审核</option>
	                      <option value="1">已审核</option>
	                      <option value="2">已打款</option>
	                      <option value="3">打款失败</option>
					</select>
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
			    </br>
				<div class="agile-tables">
					<div class="w3l-table-info">
						<table id="utable" class="table table-hover"></table>
					</div>
				</div>
				<div class="agile-tables">
					<div class="w3l-table-info">
						 <span class="btn btn-default" style="float: right;margin-right: 3%;background-color: #2ec9de">已失败额度:￥<span id="fail">0</span></span>
					</div>
				</div>
				<div class="agile-tables">
					<div class="w3l-table-info">
						 <span class="btn btn-default" style="float: right;margin-right: 3%;background-color: #2ec9de">已提现额度:￥<span id="alreadyPresented">0</span></span>
					</div>
				</div>
				<div class="agile-tables">
					<div class="w3l-table-info">
						 <span class="btn btn-default" style="float: right;margin-right: 3%;background-color: #2ec9de">未审核额度:￥<span id="unaudited">0</span></span>
					</div>
				</div>
				<div class="agile-tables">
					<div class="w3l-table-info">
						 <span class="btn btn-default" style="float: right;margin-right: 3%;background-color: #2ec9de">提现总额:￥<span id="money">0</span></span>
					</div>
				</div>
			</div>
		</div>
		<!-- 删除模态框（Modal） -->
		<div class="modal fade" id="myMoal" tabindex="-1" role="dialog" aria-labelledby="myDelLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color:#FFD700;">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title " id="myModalLabel">
						   <span class="glyphicon glyphicon-warning-sign">提现明细</span>
							
						</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" id="t_id">
						<input type="hidden" id="userId">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">用户真名:</label>
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
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">提现账号:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-sort-by-alphabet"></i>
									</span> <input type="text" id="t_account_number" name="freeze_time"
										class="form-control1" disabled="disabled" >
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">提现金额:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-usd"></i>
									</span> <input type="text" id="t_money" 
										class="form-control1" disabled="disabled">
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div class="form-group" style="height: 40px;">
							<label class="col-md-2 control-label">提现描述:</label>
							<div class="col-md-8">
								<div class="input-group input-icon right">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-text-width"></i>
									</span>
									<textarea id="message" class="form-control"
										rows="2" placeholder="Enter ..."></textarea>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</br>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-remove"></span>关闭窗口
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_check_submit(2);">
							<i class="glyphicon glyphicon-ok"></i>审核打款
						</button>
						<button type="button" class="btn btn-default" onclick="on_click_check_submit(3);">
							<i class="glyphicon glyphicon-remove"></i>审核失败
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<!--打款成功 -->
		<div class="modal fade" id="myMoal_success" tabindex="-1" role="dialog" aria-labelledby="myDelLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 18%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title " id="myModalLabel">
						   <span class="glyphicon glyphicon-ok"></span>提示
						</h4>
					</div>
					<div class="modal-body">
						操作成功!
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>关闭
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<!--打款失败 -->
		<div class="modal fade" id="myMoal_error" tabindex="-1" role="dialog" aria-labelledby="myDelLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 18%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color:#FFD700;">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title " id="myModalLabel">
						   <span class="glyphicon glyphicon-remove"></span>
							警告
						</h4>
					</div>
					<div class="modal-body">
						<span id = "mgs"></span>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>关闭
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<!-- 财务明细 -->
		<jsp:include page="/public/financialdetails.jsp"/>
		<jsp:include page="/public/lower.jsp" />
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/putForwardList.js"></script>

</body>
</html>
