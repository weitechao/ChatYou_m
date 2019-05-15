<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- 财务明细 -->
<div class="modal fade" id="user_money" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 56%;">
		<div class="modal-content">
			<div class="modal-header" style="background-color: #87CEFA;">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
				  <span class="glyphicon glyphicon-picture"></span> 财务相关
				</h4>
				<input type="hidden" id="userId">
			</div>
			</br>
			<div class="form-group" style="height: 40px;">
				<label class="col-md-2 control-label" style="margin-top: 10px;margin-left: 10px;">用户余额:</label>
				<div class="col-md-8">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> 
						  <input type="text" id = "totalMoney"  class="form-control1" disabled="disabled" value="500金币">
					</div>
				</div>
			</div>
			</br>
			<a class="btn btn-default" href="javascript:on_click_switch(-1);" style="background-color: #2ec9de;margin-left: 10px;">全部明细</a>
			 <a class="btn btn-default"         
				style="float: right;background-color: #87CEFA;margin-right: 12px;" href="javascript:on_click_switch(0);">收入明细</a>
				
		     <a class="btn btn-default"         
				style="float: right;background-color: #87CEFA;margin-right: 10px;" href="javascript:on_click_switch(1);">支出明细</a>
			<div class="agile-tables">
				<div class="w3l-table-info">
					<table id="tinancialdetails" class="table table-hover" style="width: 98%;margin-left: 10px;"></table>
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
<script src="${pageContext.request.contextPath}/javascript/financialdetails.js"></script>

