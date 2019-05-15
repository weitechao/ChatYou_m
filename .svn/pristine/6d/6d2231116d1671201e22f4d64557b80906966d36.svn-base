package com.yiliao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		//将父接口强制转化为子对象
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		String url=request.getRequestURL().toString();
		System.out.println("请求地址："+url);
		if(url.indexOf("/login.jsp")>0 || url.indexOf("login.htm") >0 || url.indexOf("logout.htm") > 0 ){
//			System.out.println("登录时放行");
			System.out.println("进入了通行证.");
			chain.doFilter(request,response);
            return;
        }
		//获取session对象
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("loginName");
		if(userName != null&&!userName.isEmpty()){
//			logger.info("获取用户信息：------>"+userName);
			//session对象中的值不为空时，过滤器放行
			chain.doFilter(request, response);
		}else{
			//当session中的值为空时，重定向到登录页面
			//response.sendRedirect(request.getContextPath()+"/index.jsp");
//			response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.print("<script>"
//					+ "alert('登录超时，请重新登录！');top.window.location.href= 'http://www.baidu.com'; "
//					+ "</script> ");
//			out.close();
			String aa = request.getContextPath() +"/login.jsp";
			response.sendRedirect(aa);
			
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

