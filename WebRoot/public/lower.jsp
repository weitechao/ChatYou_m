<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 删除模态框（Modal） -->
<div class="modal fade" style="z-index:100000" id="myComfirm" tabindex="-1" role="dialog" aria-labelledby="myDelLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 18%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title " id="myModalLabel">
				   <span class="glyphicon glyphicon-warning-sign" style="color: #FFCC00;"></span>
					提    示
				</h4>
			</div>
			<div id="my_comfirm" class="modal-body">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- footer -->
<div class="copyright">
	<p>
		Copyright &copy; 2018.版权所有
	</p>
</div>
<!-- //footer -->
