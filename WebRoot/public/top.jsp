<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="logo" style="width: 500px;">
	<a href="index.html"><h1 style="color: #01A9DB;">直播系统管理平台</h1></a>
</div>
<div class="header-right">
	<div class="profile_details_left">
		<div class="header-right-left">
			<ul>
				<li class="dropdown head-dpdn"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"><i class="fa fa-bell"></i><span
						class="badge blue" id="totalNotice"></span></a>
					<ul id="notice" class="dropdown-menu anti-dropdown-menu agile-notification">
					</ul>
				</li>
			</ul>
		</div>
		<div class="profile_details">
			<ul>
				<li class="dropdown profile_details_drop"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false">
						<div class="profile_img">
							<span class="prfil-img">${loginName} &nbsp;<i
								class="fa fa-user" aria-hidden="true"></i></span>
							<div class="clearfix"></div>
						</div>
				</a>
					<ul class="dropdown-menu drp-mnu">
						<li><a href="javascript:on_click_load_update_password();"><i
								class="fa fa-user"></i> 修改密码</a></li>
						<li><a
							href="${pageContext.request.contextPath}/admin/logout.htm" ><i
								class="fa fa-sign-out"></i> 退出登陆</a></li>
					</ul></li>
			</ul>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<!-- 新增或修改模态框（Modal） -->
<div class="modal fade" id="update_password" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 40%;">
		<div class="modal-content">
			<div class="modal-header" style="background-color: #87CEFA;">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">
					<span class="glyphicon glyphicon-pencil"></span>修改密码
				</h4>
			</div>
			<div class="modal-body">
				<input type="hidden" id="t_id">
				<div style="height: 40px;">
					<label class="col-md-2 control-label">原密码:</label>
					<div class="col-md-8">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span> <input type="text" id="originalCipher" class="form-control1"
								onfocus="on_focus('originalCipher','请输入原密码.')"
								onblur="on_blur('originalCipher')">
						</div>
					</div>
					<div class="col-sm-2" id="originalCipher_validate">
						<p class="help-block" style="color: red;">*</p>
					</div>
				</div>
				</br>
				<div style="height: 40px;">
					<label class="col-md-2 control-label">新密码:</label>
					<div class="col-md-8">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-edit"></i>
							</span> <input type="password" id="newPassWord" class="form-control1"
								onfocus="on_focus('newPassWord','请输入新密码.')"
								onblur="on_blur('newPassWord')">
						</div>
					</div>
					<div class="col-sm-2" id="newPassWord_validate">
						<p class="help-block" style="color: red;">*</p>
					</div>
				</div>
				</br>
				<div style="height: 40px;">
					<label class="col-md-2 control-label">重复密码:</label>
					<div class="col-md-8">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-edit"></i>
							</span> <input type="password" id="repeatPassWord" class="form-control1"
								onfocus="on_focus('repeatPassWord','请重复新密码.')"
								onblur="on_blur('repeatPassWord')">
						</div>
					</div>
					<div class="col-sm-2" id="repeatPassWord_validate">
						<p class="help-block" style="color: red;">*</p>
					</div>
				</div>
				</br> <span id="error" style="margin-left: 30%; color: red;"></span>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
				<button type="button" class="btn btn-primary"
					onclick="update_passwrd_submit('${loginName}');">
					<span class="glyphicon glyphicon-floppy-disk"></span>提交
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<div class="clearfix"></div>
