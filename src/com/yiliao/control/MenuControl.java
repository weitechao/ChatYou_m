package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.MenuService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 菜单管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class MenuControl {

	@Autowired
	private MenuService menuService;
	
	/**
	 * 获取菜单列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getMenuList")
	@ResponseBody
	public void getMenuList(int page,HttpServletResponse response){
		
		PrintUtil.printWri(this.menuService.getMenuList(page), response);
	}
	
	
	/**
	 * 获取父级菜单列表
	 * @param response
	 */
	@RequestMapping("getFatherList")
	@ResponseBody
	public void getFatherList(HttpServletResponse response){
		
		MessageUtil mu = this.menuService.getFatherList();
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 新增或者修改菜单
	 * @param t_id
	 * @param t_menu_name
	 * @param t_menu_url
	 * @param t_father_id
	 * @param response
	 */
	@RequestMapping("saveMenu")
	@ResponseBody
	public void saveMenu(Integer t_id,String t_menu_name,String t_menu_url,int t_father_id,String t_icon,HttpServletResponse response){
		
		MessageUtil mu = this.menuService.saveMenu(t_id, t_menu_name, t_menu_url, t_father_id,t_icon);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 删除菜单
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delMenu")
	@ResponseBody
	public void delMenu(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.menuService.delMenu(t_id);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 获取菜单列别 且标记出已经选中的菜单
	 * @param roleId
	 * @param response
	 */
	@RequestMapping("getMenuTreeList")
	@ResponseBody
	public void getMenuTreeList(int roleId,HttpServletResponse response){
	
		MessageUtil mu = this.menuService.getMenuTreeList(roleId);
		
		PrintUtil.printWri(mu, response);
	}
	
	
	
}
