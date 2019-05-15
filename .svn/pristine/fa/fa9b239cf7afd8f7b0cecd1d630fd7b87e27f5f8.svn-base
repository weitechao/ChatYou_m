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
<title>VIP</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<jsp:include page="/public/title.jsp" />
<!-- //font-awesome icons -->
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
			    <button class="btn btn-default">VIP优惠列表</button>
			    <a class="btn btn-default"                               
						style="float: right;" href="javascript:on_click_open_update_model();">点击添加</a>
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
			<div class="modal-dialog" style="width: 45%;">
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
									</span> <input type="text" id="t_setmeal_name" name="t_setmeal_name"
										class="form-control1"  placeholder="套餐名称" onfocus="on_focus('t_setmeal_name', '请输入套餐名称.')"
										onblur="on_blur('t_setmeal_name')">
								</div>
							</div>
							<div class="col-sm-2" id="t_setmeal_name_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">原&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-usd"></i>
									</span> <input type="text" id="t_cost_price" name="t_cost_price"
										class="form-control1"  placeholder="请输入价格." onfocus="on_focus('t_cost_price', '请输入价格.')"
										onblur="on_blur('t_cost_price')">
								</div>
							</div>
							<div class="col-sm-2" id="t_cost_price_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">优&nbsp;&nbsp;惠&nbsp;&nbsp;&nbsp;价:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-yen"></i>
									</span> <input type="text" id="t_money" name="t_money"
										class="form-control1"  placeholder="请输入优惠价." 
										onfocus="on_focus('t_money', '请输入优惠价.')"
										onblur="on_blur('t_money')">
								</div>
							</div>
							<div class="col-sm-2" id="t_money_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">金币兑换:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-yen"></i>
									</span> <input type="text" id="t_gold" name="t_gold"
										class="form-control1"  placeholder="请输入金币数." 
										onfocus="on_focus('t_gold', '请输入金币数.')"
										onblur="on_blur('t_gold')">
								</div>
							</div>
							<div class="col-sm-2" id="t_gold_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">时&nbsp;长&nbsp;&nbsp;(月):</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-calendar"></i>
									</span> <input type="text" id="t_duration" name="t_duration"
										class="form-control1"  placeholder="单位:月"
										onfocus="on_focus('t_duration', '请输入有效期(月)')"
										onblur="on_blur('t_duration')">
								</div>
							</div>
							<div class="col-sm-2" id="t_duration_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
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
						</br>
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
		<!-- VIP消费明细 -->
		<div class="modal fade" id="vip_detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 56%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
						  <span class="glyphicon glyphicon-picture"></span> VIP明细
						</h4>
					</div>
					</br>
					<div class="agile-tables">
						<div class="w3l-table-info">
							<table id="vip_detail_table" class="table table-hover" style="width: 98%;margin-left: 10px;"></table>
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
		<jsp:include page="/public/lower.jsp" />
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/vipSetMealList.js"></script>

</body>
</html>
