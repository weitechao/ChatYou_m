package com.yiliao.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yiliao.service.LoginService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
* CopyRright (c)2016-版权所有: 
* @项目工程名 WXManage                                      
* @Module ID   <(模块)类编号，可以引用系统设计中的类编号>    
    * Comments  <对此类的描述，可以引用系统设计中的描述>                                           
* @JDK 版本(version) JDK1.6.45                           
* @命名空间  com.yiliao.control               
* @作者(Author) 石德文           
* @创建日期  2016年3月8日  下午4:22:35 
* @修改人                                            
* @修改时间  <修改日期，格式:YYYY-MM-DD>                                    
    * 修改原因描述：  
* @Version 版本号 V1.0   
* @类名称 LoginControl
* @描述  (登陆)
 */
@Controller
@RequestMapping(value="/admin")
public class LoginControl{
	
	
	@Autowired
	private LoginService loginRoomService;//登录业务层
	
	/**
	 * @说明  TODO(登录)
	 */
	@RequestMapping(value="login")
	public ModelAndView login(String userName,String password,HttpServletRequest request,ModelAndView model){
		
		request.getSession().removeAttribute("logerr");
		MessageUtil mu = loginRoomService.login(userName,password,request);
		if(mu.getM_istatus() ==1){
			return new ModelAndView("redirect:"+mu.getM_object());
		}else{
			request.getSession().setAttribute("logerr", "用户名或者密码错误!");
			return  new ModelAndView("redirect:/login.jsp");
		}
	}
	
	/**
	 * 登陆相关配置
	 * @return
	 */
	@RequestMapping("loginSetUp")
	public ModelAndView weixinLogin(){
		
		List<Map<String,Object>> loginSetUpList = this.loginRoomService.loginSetUpList();
		
		ModelAndView  mv = new ModelAndView("loginSetUp");
		
		mv.addObject("data", loginSetUpList);
		
		return mv;
	}
	
	/**
	 * 新增或者修改登陆设置
	 * @param t_id
	 * @param t_app_id
	 * @param t_app_secret
	 * @param t_type
	 * @param t_state
	 * @param response
	 */
	@RequestMapping("saveLoginSetUp")
	@ResponseBody
	public void saveLoginSetUp(Integer t_id,String t_app_id,String t_app_secret,String t_type,String t_state,HttpServletResponse response){
		
	 
		MessageUtil mu = this.loginRoomService.saveLoginSetUp(t_id, t_app_id, t_app_secret, t_type, t_state);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 根据编号获取登陆设置信息
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("getLoginSetUpById")
	@ResponseBody
	public void getLoginSetUpById(int t_id, HttpServletResponse response){
		
		 MessageUtil mu = this.loginRoomService.getLoginSetUpById(t_id);
		 
		 PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 删除设置
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delLoginSteup")
	@ResponseBody
	public void delLoginSteup(int t_id,HttpServletResponse response){
		 
		MessageUtil mu = this.loginRoomService.delLoginSteup(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	
	/**
	 * 修改启用或者停用
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("updateLoginState")
	@ResponseBody
	public void updateLoginState(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.loginRoomService.updateLoginState(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 登出
	 * @param userName
	 * @param request
	 * @return
	 */
	@RequestMapping("logout")
	public ModelAndView logout(String userName,HttpServletRequest request){
		
		request.getSession().removeAttribute(userName);
		request.getSession().removeAttribute("roleId");
		request.getSession().removeAttribute("spreadLog");
		request.getSession().removeAttribute("authority");
		return new ModelAndView("redirect:/login.jsp");
	}
	
	/**
	 * 修改密码
	 * @param loginName
	 * @param originalCipher
	 * @param newPassword
	 * @param request
	 * @param response
	 */
	@RequestMapping("updatePassWord")
	@ResponseBody
	public void updatePassWord(String loginName,String originalCipher,String newPassword,HttpServletResponse response){
	
		 MessageUtil mu = this.loginRoomService.updatePassWord(loginName, originalCipher, newPassword);
		 
		 PrintUtil.printWri(mu, response);
	}
}
