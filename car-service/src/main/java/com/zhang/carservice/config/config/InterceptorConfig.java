package com.zhang.carservice.config.config;


import com.zhang.carservice.config.interceptor.ResponseResultInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截请求
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	//定义规则
	public static String ALLPATH = "/**";

	@Autowired
	private ResponseResultInterceptor responseResultInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有的请求
        registry.addInterceptor(responseResultInterceptor).addPathPatterns(ALLPATH);
    }
}
