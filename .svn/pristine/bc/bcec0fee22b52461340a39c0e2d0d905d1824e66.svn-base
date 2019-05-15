package com.yiliao.control;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.SimulationService;
import com.yiliao.util.MessageUtil;


@Controller
@RequestMapping("admin")
public class SimulationControl {

	@Autowired
	private SimulationService simulationService;
	
	/**
	 * 获取模拟消息列表
	 * @param page
	 * @return
	 */
	@RequestMapping("getSimulationList")
	@ResponseBody
	public Map<String, Object> getSimulationList(int page){
		
		return this.simulationService.getSimulationList(page);
	}
	/**
	 * 添加模拟消息
	 * @return
	 */
	@RequestMapping("saveSimulation")
	@ResponseBody
	public MessageUtil saveSimulation(String t_centent,int sex) {
		
		return this.simulationService.saveSimulation(t_centent, sex);
	}
	
	/**
	 * 删除模拟消息
	 * @param t_id
	 * @return
	 */
	@RequestMapping("delSimulation")
	@ResponseBody
	public MessageUtil delSimulation(int t_id) {
		
		return this.simulationService.delSimulation(t_id);
	}
}
