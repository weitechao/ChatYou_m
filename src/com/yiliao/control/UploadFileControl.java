package com.yiliao.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import com.yiliao.service.UploadService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;
import com.yiliao.util.SystemConfig;
import com.yiliao.util.UpBaseUtil;

@Controller
@RequestMapping(value = "/admin")
public class UploadFileControl {

	private UpBaseUtil baseUtil = new UpBaseUtil();

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UploadService uploadService;

	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public String UploadFileInfo(HttpServletRequest request,
			HttpServletResponse response) {

		MessageUtil mu = null;

		try {
			long startTime = System.currentTimeMillis();
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator iter = multiRequest.getFileNames();

				String id = request.getParameter("t_id");
				int type = Integer.parseInt(request.getParameter("type"));

				while (iter.hasNext()) {
					// 一次遍历所有文件
					MultipartFile mf = multiRequest.getFile(iter.next()
							.toString());
					if (mf != null) {

						String fileName = mf.getOriginalFilename(); // 原始文件名

						String newName = baseUtil.getFileName(fileName);// 重新生成文件名

						CommonsMultipartFile cf = (CommonsMultipartFile) mf;

						DiskFileItem fi = (DiskFileItem) cf.getFileItem();

						File uploadFile = fi.getStoreLocation();

						JSONObject json = new JSONObject();

						String filePath = this.ossUpload(newName, uploadFile);

						// 判断用户上传的是图片还是视频
						if (fileName.substring(fileName.lastIndexOf(".") + 1).equals("mp4")) {

							String imgPath =null ;
							if(Config().indexOf("Windows") > -1){
								imgPath = this.makeScreenCutWindos(uploadFile.getPath(),SystemConfig.getValue("windowsfileUrl"));
							}else{
								imgPath = this.makeScreenCutLinux(uploadFile.getPath(), 1);
							}

							logger.info("视频截图路径-->{}", imgPath);

							json.put("imgPath", imgPath);
							json.put("filePath", filePath + newName);
						}

						if (type > 0) {
							this.uploadService.uploadFile(Integer.parseInt(id),
									type, filePath + newName);
						}
						mu = new MessageUtil(1, "上传成功!");
						if (fileName.substring(fileName.lastIndexOf(".") + 1)
								.equals("mp4")) {
							mu.setM_object(json);
						} else {
							mu.setM_object(filePath + newName);
						}
					}
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("文件上传耗时：" + String.valueOf(endTime - startTime)
					+ "ms");

			PrintUtil.printWri(mu, response);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("文件上传异常!", e);
		}
		return null;

	}
	
	/**
	 * 判断操作系统
	 */
	public static String  Config() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString(); // 获取本机ip
			String hostName = addr.getHostName().toString(); // 获取本机计算机名称
			System.out.println("本机IP：" + ip + "\n本机名称:" + hostName);
			Properties props = System.getProperties();
			System.out.println("操作系统的名称：" + props.getProperty("os.name"));
			System.out.println("操作系统的版本号：" + props.getProperty("os.version"));
			
			return props.getProperty("os.name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Centos";
	}

	/**
	 * 上传至OSS对象
	 * 
	 * @param newName
	 * @param uploadFile
	 * @return
	 */
	public String ossUpload(String newName, File uploadFile) {

		Map<String, Object> map = this.uploadService.getOssData();

		// 1 初始化用户身份信息(secretId, secretKey)
		COSCredentials cred = new BasicCOSCredentials(map.get("t_secret_id")
				.toString(), map.get("t_secret_key").toString());
		// 2 设置bucket的区域, COS地域的简称请参照
		// https://cloud.tencent.com/document/product/436/6224
		ClientConfig clientConfig = new ClientConfig(new Region(map.get("t_region").toString()));
		// 3 生成cos客户端
		COSClient cosclient = new COSClient(cred, clientConfig);
		// bucket名需包含appid

		PutObjectRequest putObjectRequest = new PutObjectRequest(map.get(
				"t_bucket").toString(), "backstage/" + newName, uploadFile);
		// 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
		putObjectRequest.setStorageClass(StorageClass.Standard);
		try {
			PutObjectResult putObjectResult = cosclient
					.putObject(putObjectRequest);
			// putobjectResult会返回文件的etag
			String etag = putObjectResult.getETag();
			System.out.println(etag);
		} catch (CosServiceException e) {
			e.printStackTrace();
		} catch (CosClientException e) {
			e.printStackTrace();
		}
		// 关闭客户端
		cosclient.shutdown();

		return map.get("t_img_url").toString() + "backstage/";
	}

	/**
	 * 参数 veido_path : 视频位置 ffmpeg_path : 转换程序 picPath : 图片位置
	 * 
	 * @throws Exception
	 * */
	// cmd:
	// c:\ffmpeg -i c:\abc.mp4 e:\sample.jpg -ss 00:00:05 -r 1 -vframes 1 -an
	// -vcodec mjpeg
	public String makeScreenCutWindos(String videoRealPath, String imageRealName)
			throws Exception {
		
		 List<String> commands = new ArrayList<String>();
         commands.add(SystemConfig.getValue("winffmpeg"));
         commands.add("-ss");//偏移量
         commands.add("2");//这个参数是设置截取视频多少秒时的画面
         commands.add("-i");// 输入
         commands.add(videoRealPath);
//	        commands.add("-y");
         commands.add("-f");//格式化，要输出什么格式的截图
//	        commands.add("image2");
         commands.add("mjpeg");
//	        commands.add("-t");
//	        commands.add("0.001");
//	        commands.add("-s"); //-s表示截图的的大小
//	        commands.add("960*240"); //这个参数是设置截取图片的大小，但不可任意填写
         commands.add("-vframes");
         commands.add("1");//截取1帧
 
         commands.add(imageRealName);


		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.directory(new File("F://"));
			builder.command(commands);
			builder.redirectErrorStream(true);
			System.out.println("视频截图开始...");

			Process process = builder.start();
			InputStream in = process.getInputStream();
			byte[] bytes = new byte[1024];
			System.out.print("正在进行截图，请稍候");
			while (in.read(bytes) != -1) {
				System.out.println(".");
			}

			String fileName = imageRealName.substring(imageRealName
					.lastIndexOf("/") + 1);

			String newName = baseUtil.getFileName(fileName);

			 String urlPath = ossUpload(newName, new File(imageRealName));

			 return urlPath + newName;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("视频截图失败！");
		}
		return null;
	}

	public static void main(String[] args) {

	  UploadFileControl u = new UploadFileControl();
     
      try {
		u.makeScreenCutWindos("d://1123.mp4", "F://1234.jpg");
	  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	  }

	}

	// 视频缩略图截取
	// inFile 输入文件(包括完整路径)
	// outFile 输出文件(可包括完整路径)
	public String makeScreenCutLinux(String inFile, int startTime) {
		String command = "ffmpeg -i " + inFile + " -y -f image2 -ss "
				+ startTime + " -t 00:00:01 -s  540x960 "
				+ SystemConfig.getValue("centosfileUrl");
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(command);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

			String newName = baseUtil.getFileName(SystemConfig
					.getValue("centosfileUrl"));

			String urlPath = ossUpload(newName,
					new File(SystemConfig.getValue("centosfileUrl")));

			return urlPath + newName;
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}
	
	/**
	 *  渠道图片上传
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "uploadSpeedFile", method = RequestMethod.POST)
	public String UploadSpeedImg(HttpServletRequest request,HttpServletResponse response) {

		MessageUtil mu = null;

		try {
			long startTime = System.currentTimeMillis();
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator iter = multiRequest.getFileNames(); 
				while (iter.hasNext()) {
					// 一次遍历所有文件
					MultipartFile mf = multiRequest.getFile(iter.next()
							.toString());
					if (mf != null) {
						
						// 原始文件名
						String fileName = mf.getOriginalFilename(); 
						
						CommonsMultipartFile cf = (CommonsMultipartFile) mf;
						
						String route = "";
						//获取当前系统是否什么类型的 
						if(Config().indexOf("Windows") > -1){
							route =SystemConfig.getValue("windows_speed_fileUrl");
						}else {
							route =SystemConfig.getValue("centos_speed_fileUrl");
						}
						// 重新生成文件名
						
						String filePath = baseUtil.getFileName(fileName);

						File uploadFile = new File(route + filePath);  //文件保存路径
						
						if(!uploadFile.exists()) {
							uploadFile.getParentFile().mkdir();
//							uploadFile.createNewFile();
						}
						
						cf.transferTo(uploadFile);
                        
						//上传到
						mu = new MessageUtil(1, "上传成功!");
						mu.setM_object(filePath);
					}
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("文件上传耗时：" + String.valueOf(endTime - startTime)
					+ "ms");

			PrintUtil.printWri(mu, response);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("文件上传异常!", e);
		}
		return null;

	}
	
	

}
