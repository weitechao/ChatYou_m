package com.yiliao.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.yiliao.exception.BusinessException;

/**
 * 公共的业务接口
 * @author ZhouShuhua
 * @datetime 2013.5.15
 */
@Service
public interface ICommService {

	public Serializable add(Object obj) throws BusinessException;
	
	public void update(Object obj);
}
