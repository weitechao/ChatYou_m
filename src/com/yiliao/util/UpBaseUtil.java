package com.yiliao.util;

import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class UpBaseUtil {
	private static long mmsTransactionId;
	private static long smsMsgId;
	static {
		mmsTransactionId = System.currentTimeMillis() * 2;
		smsMsgId = System.currentTimeMillis() * 2;
	}

	public synchronized static String getTransactionId() {
		return Long.toString(mmsTransactionId++);
	}

	public synchronized static long getMsgId() {
		return smsMsgId++;
	}
	
	
	/**x
	 * 得到项目路径
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getProjectPath() throws Exception {
		// 获取到class的绝对路径
		String urls = this.getClass().getClassLoader().getResource("/") + "";
		// 截取需要的路径
		urls = urls.substring(6, urls.length() - 17);
		// 替换路径中的/为\\
		String newUrl = urls.replace("/", "\\");
		// 当路径中存在空格的时候，tomcat在获取的路径中会生成为%20，这时需要替换为“ ”
		newUrl = newUrl.replace("%20", " ");
		return newUrl;
	}
	/**
	 * 解密
	 */
	public static String DecodeWord(String strImg) {
		String str="";
		// 得到加密内容 默认为strImg
//		System.out.println(strImg);
		// 解密
		sun.misc.BASE64Decoder strDecode = new sun.misc.BASE64Decoder();
		// 容器
		byte[] filebyte;
		try {
			// 去除加密串可能出现的空格问题，并解密
			filebyte = strDecode.decodeBuffer(new String(strImg.getBytes(), "UTF-8"));
			// 输出解密内容，对比
//			System.out.println(new String(filebyte, "UTF-8"));
			str=new String(filebyte, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		return str;
	}
	/**
	 * 判断Map<String,Object>里指定key的value值是否为空
	 * 
	 * @param params
	 * @param key
	 * @return boolean -true:表示value为空;false:表示value为非空
	 */
	public static boolean isEmpty(Map<String, Object> params, String key) {
		if (isEmpty(params))
			return true;
		else {
			if (params.containsKey(key) && !isEmpty(params.get(key)))
				return false;
			return true;
		}
	}
	/**
	 * 得到项目路径
	 * @return
	 * @throws Exception
	 */
	public String getProjectUrl() throws Exception{
		//获取到class的绝对路径
		String urls = this.getClass().getClassLoader().getResource("/")+"";
		//截取需要的路径
		urls = urls.substring(6, urls.length()-17);
		//替换路径中的/为\\
		String newUrl = urls.replace("/", "\\");
		//当路径中存在空格的时候，tomcat在获取的路径中会生成为%20，这时需要替换为“ ”
		newUrl = newUrl.replace("%20", " ");
		return newUrl;
	}
	/**
	 * 文件存放路径
	 * @param kindId
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> saveFilePathInfo(String kindId,String partitionCode,String studentId) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		String path = SystemConfig.getValue("realPath");  	
		// 创建目录文件夹
		File file = new File(path+"\\CloudFile\\"+ partitionCode+"\\"+ kindId + "\\STUDENT\\" + studentId);
		file.mkdirs();
		//文件保存路径
		String saveFilePath = path+"/CloudFile/" + partitionCode + "/"+ kindId + "/STUDENT/" + studentId;
		//保存到数据库的路径
		String saveFileDataBase = "CloudFile/" + partitionCode + "/"+ kindId + "/STUDENT/" + studentId;
		map.put("saveFilePath", saveFilePath);
		map.put("saveFileDataBase", saveFileDataBase);
		return map;
	}
	/**
	 * 创建上传图片的临时文件夹
	 * @return
	 * @throws Exception
	 */
	public String saveFileTemp() throws Exception{
		String path = SystemConfig.getValue("realPath");  	
		// 创建临时文件夹
		File file = new File(path+"\\CloudFile\\StudentTemp");
		file.mkdirs();
		//临时文件夹
		String tempFilePath = path+"/CloudFile/StudentTemp";
		return tempFilePath;
	}
//	/**
//	 * 文件显示网络路径(院所图片)
//	 * @return
//	 * @throws Exception
//	 */
//	public String netUrlSchoolFilePathInfo() throws Exception{
//		String netUrlPath = SystemConfig.getValue("webPath");
//		netUrlPath = netUrlPath  + "/CloudFile/"+this.getKindId()+"/schoolPicture";
//		return netUrlPath;
//	}
	/**
	 * 文件显示网络路径(临时文件夹)
	 * @return
	 * @throws Exception
	 */
	public String netUrlFilePathInfo() throws Exception{
		String netUrlPath = SystemConfig.getValue("webPath");
		netUrlPath = netUrlPath  + "/CloudFile/StudentTemp";
		return netUrlPath;
	}
	/**
	 * 文件存放目标文件夹
	 * @return
	 * @throws Exception
	 */
	public String netUrlPathInfo() throws Exception{
		String netUrlPath = SystemConfig.getValue("realPath");
		return netUrlPath;
	}
	/**
	 * 网络路径
	 */
	public String webUrlPathInfo() throws Exception{
		String webUrlPath = SystemConfig.getValue("webPath");
		return webUrlPath;
	}
	/**
	 * 定义文件名称
	 * @return
	 * @throws Exception
	 */
	public String getFileName(String suffix) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = sdf.format(new Date());
		//随机数
		String number = Math.round((Math.random() * 10000)) + "";
		fileName = fileName+number;
		//获取文件格式
        suffix = suffix.indexOf(".") != -1 ? suffix.substring(suffix.lastIndexOf("."), suffix.length()) : null;
        //新的文件名
        fileName = new SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+fileName+suffix;
		return fileName;
	}
	
	
 
	
	/**
	 * 生成文件的路径
	 * 
	 * @return
	 * @throws Exception
	 */
	public String foundProficientFile(String urls) throws Exception {
		// 创建目录文件夹
		File file = new File(urls+"\\news\\proficient");
		file.mkdirs();
		//文件保存路径
		String str = urls+"/news/proficient";
		//保存到数据库的路径
		String saveUrl = "news/proficient";
		return str+","+saveUrl;
	}
    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String pathFile) {
    	boolean flag = false;
        File file = new File(pathFile);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
	/**
	 * 根据当前登录用户，获取其KindId
	 * @return
	 * @throws Exception
	 */
	public String getKindId() throws Exception{
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String KindId = (String) request.getSession().getAttribute("kindId");
		if(!BaseUtil.isEmpty(KindId)){
			return KindId;
		}
		return "9999@999999";
	}
	/**
	 * 获取年月日路径
	 * @return
	 */
	public static String getTimeUrl(){
		
		Calendar cal=Calendar.getInstance();//使用日历类
		int year=cal.get(Calendar.YEAR);//得到年
		int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
		int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
		
		return year+"/"+month+"/"+day;
	}
	
	/**
	 * 当前登录用户所有的权限
	 * @return
	 * @throws Exception
	 */
	public String findPermissType() throws Exception{
		String perType = "";
		perType = "1";
		return perType;
	}
	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 *            -参数对象
	 * @return boolean -true:表示对象为空;false:表示对象为非空
	 */
	public static boolean isEmpty(Object obj) {
		return obj == null || obj.toString().equalsIgnoreCase("null")
				|| obj.toString().length() == 0;
	}

	

	/**
	 * 去空格
	 * 
	 * @param source
	 * @return
	 */
	public static String trim(String source) {
		if (BaseUtil.isEmpty(source))
			return "";
		else
			return source.trim();
	}

	public static String trimEx(String source) {
		if (BaseUtil.isEmpty(source))
			return "";
		source = source.trim();
		source = source.substring(source.lastIndexOf(" ") < 0 ? 0 : source
				.lastIndexOf(" "));
		return source;
	}

	public static boolean isMobile(String mobile) {
		Pattern pattern = Pattern.compile("(86)?[1]{1}[0-9]{10}");
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}

	public static void object2Xml(Object obj, OutputStream output)
			throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller m = context.createMarshaller();
		m.marshal(obj, output);
	}

	public static String object2XmlSubString(Object obj, int offset,int offApp)
			throws JAXBException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		object2Xml(obj, out);
		byte[] outByte = out.toByteArray();
		return new String(outByte, offset, outByte.length - offset-offApp);
	}

	public String getRealPath() throws Exception{
		String netUrlPath = SystemConfig.getValue("realPath");
		return netUrlPath;
	}
	
	public String getProPath() throws Exception{
		String netUrlPath = SystemConfig.getValue("projectPath");
		return netUrlPath;
	}
	
	public String getWebPath() throws Exception{
		String netUrlPath = SystemConfig.getValue("webPath");
		return netUrlPath;
	}
	/**
	 * 读取excel内容到List<Map<String, Object>>中，这里不取第一行，第一行作为标题
	 * @param fileUrl
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getExcelInfoToList(String fileUrl) throws Exception{
		Workbook workbook = Workbook.getWorkbook(new File(fileUrl));
		Sheet sheet[] = workbook.getSheets();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int a = 0; a < sheet.length; a++) {
			for (int i = 2; i < sheet[a].getRows(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int j = 0; j < sheet[a].getColumns(); j++) {
					map.put("name", sheet[a].getCell(0, i).getContents());
					map.put("age", sheet[a].getCell(1, i).getContents());
					map.put("contact", sheet[a].getCell(2, i).getContents());
					map.put("phone", sheet[a].getCell(3, i).getContents());
					map.put("sex", sheet[a].getCell(4, i).getContents());
					map.put("address", sheet[a].getCell(5, i).getContents());
					System.out.println("remark---->"+sheet[a].getCell(6, i).getContents());
					map.put("remark", sheet[a].getCell(6, i).getContents());
//					String lab = sheet[a].getCell(j, i).getContents();
//					System.out.print(lab + "\t");
				}
				list.add(map);
			}
		}
		return list;
	}
	/**
	 * 创建上传图片的临时文件夹
	 * @return
	 * @throws Exception
	 */
	public String excelTempPath() throws Exception{
		String path = SystemConfig.getValue("realPath");  	
		String kindId = this.getKindId();
		// 创建临时文件夹
		File file = new File(path+"\\CloudFile\\"+kindId+"\\excelTemp");
		file.mkdirs();
		//临时文件夹
		String filePath = path+"/CloudFile/"+kindId+"/excelTemp";
		return filePath;
	}
}
