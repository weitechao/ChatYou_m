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
<title>Tables</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<jsp:include page="/public/title.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fileinput.css" media="all" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-2.0.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/fileinput.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/fileinput_locale_zh.js" type="text/javascript"></script>
<!-- tables -->
<script type="text/javascript">
	var path = '${pageContext.request.contextPath}';
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
				<a class="btn btn-default" href="javascript:on_click_open_update_model();">点击添加</a> 
				<div style="float: right;width: 20%">
					<button class="btn btn-default" style="float: left;">条件:</button>
					<input type="text" class="form-control" id="condition" style="height: 36px; float: left;width: 200px;margin-right: 2%"  placeholder="请输入礼物名称.">
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
		<div class="modal fade" id="myDel" tabindex="-1" role="dialog"
			aria-labelledby="myDelLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 18%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color:#FFD700;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title " id="myModalLabel">
						    <input	type="hidden" id="type">
						     <input type="hidden" id="del_id">
							<span class="glyphicon glyphicon-warning-sign"></span> 警告
						</h4>
					</div>
					<div class="modal-body">
						确定要进行操作吗？
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary"
							onclick="on_click_change_res();">
							<i class="glyphicon glyphicon-ok"></i>确认
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
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
							 <input type="hidden" id="t_gift_id">
							 <input type="hidden" id="t_gift_gif_url">
							 <input type="hidden" id="t_gift_still_url">
						</h4>
					</div>
					<div class="modal-body">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">礼物名称:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-time"></i>
									</span> <input type="text" id="t_gift_name" name="t_gift_name"
										class="form-control1" placeholder="请输入礼物名称." onfocus="on_focus('t_gift_name','请输入礼物名称.')" onblur="on_blur('t_gift_name')">
								</div>
							</div>
							<div class="col-sm-2" id="t_gift_name_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">礼物单价:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-time"></i>
									</span> <input type="text" id="t_gift_gold" name="t_gift_gold"
										class="form-control1" placeholder="请输入礼物单价." onfocus="on_focus('t_gift_gold','请输入礼物单价.')" onblur="on_blur('t_gift_gold')">
								</div>
							</div>
							<div class="col-sm-2" id="t_gift_gold_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 340PX;">
							<label class="col-md-2 control-label">礼物静图:</label>
							<div class="col-md-8">
								<div class="input-group">
									<input id="file-1" class="file-loading" type="file" multiple data-min-file-count="1">
								</div>
							</div>
							<div class="col-sm-2" id="file-1_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 340PX;">
							<label class="col-md-2 control-label">礼物动图:</label>
							<div class="col-md-8">
								<div class="input-group">
									<input id="file-2" class="file-loading" type="file" multiple data-min-file-count="1">
								</div>
							</div>
							<div class="col-sm-2" id="file-2_validate">
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
									 <select id="t_is_enable" style="height: 40px;" name="t_is_enable" class="form-control">
					                      <option value="0" selected="selected">启用</option>
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
		<div class="modal fade" id="img_preview" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<span class="glyphicon glyphicon-picture"></span> Banner预览
						</h4>
					</div>
					<div class="modal-body">
						<div>
							<div class="agile-gallery-grid">
								<div class="agile-gallery">
									<img id="hand_img"
										src="${pageContext.request.contextPath }/images/g4.jpg" alt="" />
								</div>
							</div>
						</div>
						</br>
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
					<div class="modal-body" id="cover_modal_div"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							style="background-color: #87CEFA;">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
		<!-- 财务明细 -->
		<div class="modal fade" id="gift_detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 56%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
						  <span class="glyphicon glyphicon-picture"></span> 赠送明细
						</h4>
						<input type="hidden" id="userId">
					</div>
					</br>
					<div class="agile-tables">
						<div class="w3l-table-info">
							<table id="give_deta_table" class="table table-hover" style="width: 98%;margin-left: 10px;"></table>
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

		<jsp:include page="/public/userModel.jsp" />
		<jsp:include page="/public/lower.jsp" />
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/javascript/giftList.js"></script>

</body>
</html>
