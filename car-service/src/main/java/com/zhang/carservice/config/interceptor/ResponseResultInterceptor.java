package com.zhang.carservice.config.interceptor;

import com.api.carapi.annotations.ResponseNature;
import com.zhang.carservice.config.constants.DBConstants;
import com.zhang.carservice.config.util.RequestContextUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器配置
 */

@Component
public class ResponseResultInterceptor implements HandlerInterceptor {

	/**
	 * 拦截请求，如果请求的方法或者类上有ResponseResult注解，那么就在request的attr中加入一个属性标识
	 */
	@Override
	//在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		RequestContextUtil.setRequestId();
		//接受任意域名请求
		response.setHeader("Access-Control-Allow-Origin", "*");
		//response.setHeader("Access-Control-Allow-Origin", "http://192.168.10.100:8080");
		response.setHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET");
		//指定请求头
		response.setHeader("Access-Control-Allow-Headers", "accept,x-requested-with,Content-Type,X-Custom-Header");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		//超时时间
		response.setHeader("Access-Control-Max-Age", "5000");
		if (handler instanceof HandlerMethod) {
			final HandlerMethod handlerMethod = (HandlerMethod) handler;
			final Class<?> clazz = handlerMethod.getBeanType();
			final Method method = handlerMethod.getMethod();
			//判断类上是否存在该注释
			if (clazz.isAnnotationPresent(ResponseNature.class) ||  method.isAnnotationPresent(ResponseNature.class)) {
				return true;
			}
			request.setAttribute(DBConstants.RESPONSE_RESULT, DBConstants.RESPONSE_RESULT);
		}
		return true;
	}

	@Override
	// 在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  {
	}

	@Override
	//在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
	}


}
