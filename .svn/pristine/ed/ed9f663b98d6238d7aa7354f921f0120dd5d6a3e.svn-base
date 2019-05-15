<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- 上传图片的Modal -->
<div class="modal fade" id="on_upload_img" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 50%;">
		<div class="modal-content">
			<div class="modal-header" style="background-color: #87CEFA;">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">
					<span class="glyphicon glyphicon-pencil"></span>头像
					 <input type="hidden" id="hand_img_url">
				</h4>
			</div>
			<div class="modal-body">
				<div style="height: 320PX;">
					<label class="col-md-2 control-label">头像上传:</label>
					<div class="col-md-8">
						<div class="input-group">
							<input id="file-1" name="file-1" class="file-loading" type="file" multiple
								data-min-file-count="1">
						</div>
					</div>
					<div class="col-sm-2" id="file-1_validate">
						<p class="help-block" style="color: red;">*</p>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
				<button type="button" class="btn btn-primary" onclick="on_head_submit();">
					<span class="glyphicon glyphicon-floppy-disk"></span>提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

<!-- 封面图片的Modal -->
<div class="modal fade" id="on_cover_img" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 70%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<span class="glyphicon glyphicon-pencil"></span> 封面
							 <input type="hidden" id="t_user_id">
							 <input type="hidden" id="cover_img_url">
						</h4>
					</div>
					<div class="modal-body">
						<div style="height: 340PX;">
							<label class="col-md-2 control-label">封面图片:</label>
							<div class="col-md-8">
								<div class="input-group">
									<input id="file-2" name="file-1" class="file-loading" type="file" multiple
										data-min-file-count="1" onchange="on_change('file-2')">
								</div>
							</div>
							<div class="col-sm-2" id="file-1_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary"
							onclick="on_cover_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
</div>


<!-- 相册Modal -->
<div class="modal fade" id="on_dynamic_img" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 50%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<span class="glyphicon glyphicon-pencil"></span> 相册
							 <input type="hidden" id="t_album_user_id">
							 <input type="hidden" id="dynamic_cover_img_url">
							 <input type="hidden" id="dynamic_file_url">
						</h4>
					</div>
					<div class="modal-body">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">设置标题:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-time"></i>
									</span> <input type="text" id="t_title" name="t_title"
										class="form-control1" placeholder="请输入标题.">
								</div>
							</div>
							<div class="col-sm-2" id="t_title_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">设置收费:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-yen"></i>
									</span> <input type="text" id="t_money" name="t_money"
										class="form-control1" value="0" placeholder="请输入查看金额.0为公开视频" onfocus="on_focus('t_money', '请输入查看金额.0为公开视频')"
										onblur="on_blur('t_money')">
								</div>
							</div>
							<div class="col-sm-2" id="t_money_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 340PX;">
							<label class="col-md-2 control-label">相册文件:</label>
							<div class="col-md-8">
								<div class="input-group">
									<input id="file-3"  class="file-loading" type="file" multiple
										data-min-file-count="1">
								</div>
							</div>
							<div class="col-sm-2" id="file-1_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						 
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary"
							onclick="on_dynamic_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
</div>



<!-- 相册Modal -->
<div class="modal fade" id="on_setup_charge" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 50%;">
		<div class="modal-content">
			<div class="modal-header" style="background-color: #87CEFA;">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">
					<span class="glyphicon glyphicon-pencil"></span> 相册
					 <input type="hidden" id="t_setup_charge_user_id">
				</h4>
			</div>
			<div class="modal-body">
				<div style="height: 40px;">
					<label class="col-md-2 control-label">视频聊天:</label>
					<div class="col-md-8">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hd-video"></i>
							</span> <input type="text" id="t_video_gold" 
								class="form-control1" placeholder="请输入视频聊天费用(每分钟消耗金币)." onfocus="on_focus('t_video_gold', '请输入视频聊天费用(每分钟消耗金币).')"
								onblur="on_blur('t_video_gold')">
						</div>
					</div>
					<div class="col-sm-2" id="t_video_gold_validate">
						<p class="help-block" style="color: red;">*</p>
					</div>
				</div>
				</br>
				<div style="height: 40px;">
					<label class="col-md-2 control-label">文本聊天:</label>
					<div class="col-md-8">
						<div class="input-group">
							<span class="input-group-addon"> <i	class="glyphicon glyphicon-text-color"></i>
							</span> <input type="text" id="t_text_gold" 
								class="form-control1"  placeholder="请输入文本聊天费用(每条文本消耗金币)." onfocus="on_focus('t_text_gold', '请输入文本聊天费用(每条文本消耗金币).')"
								onblur="on_blur('t_text_gold')">
						</div>
					</div>
					<div class="col-sm-2" id="t_text_gold_validate">
						<p class="help-block" style="color: red;">*</p>
					</div>
				</div>
				</br>
				<div style="height: 40px;">
					<label class="col-md-2 control-label">查看手机:</label>
					<div class="col-md-8">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-earphone"></i>
							</span> <input type="text" id="t_phone_gold"
								class="form-control1"  placeholder="请输入查看手机号码消耗金币." onfocus="on_focus('t_phone_gold', '请输入查看手机号码消耗金币.')"
								onblur="on_blur('t_phone_gold')">
						</div>
					</div>
					<div class="col-sm-2" id="t_phone_gold_validate">
						<p class="help-block" style="color: red;">*</p>
					</div>
				</div>
				</br>
				<div style="height: 40px;">
					<label class="col-md-2 control-label">查看微信:</label>
					<div class="col-md-8">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-phone"></i>
							</span> <input type="text" id="t_weixin_gold" 
								class="form-control1"  placeholder="请输入查看微信号消耗金币." onfocus="on_focus('t_weixin_gold', '请输入查看微信号消耗金币.')"
								onblur="on_blur('t_weixin_gold')">
						</div>
					</div>
					<div class="col-sm-2" id="t_weixin_gold_validate">
						<p class="help-block" style="color: red;">*</p>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<span class="glyphicon glyphicon-remove"></span>关闭
				</button>
				<button type="button" class="btn btn-primary"
					onclick="on_setup_submit();">
					<span class="glyphicon glyphicon-floppy-disk"></span>提交
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
			<!-- /.modal -->
</div>