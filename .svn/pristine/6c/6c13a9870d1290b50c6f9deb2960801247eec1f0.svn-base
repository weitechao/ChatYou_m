package com.yiliao.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.RoleService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 角色控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class RoleControl {

	@Autowired
	private RoleService roleService;
	
	
	/**
	 * 获取角色列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getRoleList")
	@ResponseBody
	public void getRoleList(int page ,HttpServletResponse response){
		
		PrintUtil.printWri(this.roleService.getRoleList(page), response);
	}
	
	/**
	 * 添加或者修改角色
	 * @param t_id
	 * @param t_role_name
	 * @param t_enable
	 * @param response
	 */
	@RequestMapping("saveRole")
	@ResponseBody
	public void saveRole(Integer t_id,String t_role_name,int t_enable,HttpServletResponse response){
		
		PrintUtil.printWri(this.roleService.saveRole(t_id, t_role_name, t_enable), response);
	}
	
	/**
	 * 删除角色
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delRole")
	@ResponseBody
	public void delRole(int t_id,HttpServletResponse response){
		
		PrintUtil.printWri(this.roleService.delRole(t_id), response);
		
	}
	
	/**
	 * 获取启用角色列表
	 * @param response
	 */
	@RequestMapping("getRoleEnableList")
	@ResponseBody
	public void getRoleEnableList(HttpServletRequest request,HttpServletResponse response){
		Object attribute = request.getSession().getAttribute("spread_role_id");
		if(null != attribute) {
			PrintUtil.printWri(this.roleService.getRoleEnableList(Integer.parseInt(attribute.toString())), response);
		}else {
			PrintUtil.printWri(this.roleService.getRoleEnableList(1), response);
		}
	}
	
	/**
	 * 给角色分配权限
	 * @param t_role_id
	 * @param meunIds
	 * @param response
	 */
	@RequestMapping("saveAuthority")
	@ResponseBody
	public void saveAuthority(int t_role_id,String meunIds,HttpServletResponse response){
		
		MessageUtil mu = this.roleService.saveAuthority(t_role_id, meunIds);
		
		PrintUtil.printWri(mu, response);
	}
}
