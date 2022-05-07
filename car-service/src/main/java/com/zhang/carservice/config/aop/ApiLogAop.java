package com.zhang.carservice.config.aop;


import com.alibaba.fastjson.JSONObject;
import com.zhang.carservice.config.handle.GlobalExceptionHandle;
import com.zhang.carservice.config.util.RequestContextUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.context.annotation.Configuration;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Configuration
@Log4j2
public class ApiLogAop {

	 //切入点
     //@Pointcut("execution(* com.zhang.carservice.controller.*.*(..))")
     @Pointcut("@within(com.api.carapi.annotations.Log)")
     public void webLog(){}

	@Around("webLog()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		GlobalExceptionHandle globalExceptionHandle = new GlobalExceptionHandle();
		Object result;
		Map<String, Object> map = new HashMap<>();
		//请求方法名
		String[] names = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
		//请求类名
		Object target = joinPoint.getTarget().getClass().getSimpleName();
		//请求方法值
		Object[] values = joinPoint.getArgs();
		for (int i = 0; i < names.length; i++) {
			map.put(names[i], values[i]);
		}
		String value = JSONObject.toJSONString(map);
		try {
			 result = joinPoint.proceed();
		} catch (Throwable e) {
			logInfo("",false ,value);
			if (value.equals("{}")){
				return globalExceptionHandle.requestValueError(new RuntimeException());
			}
			return null;
		}
		logInfo(result,true,value);
		return result;
	}

     //定义打印规则
	 public void logInfo (Object result,boolean status,String value) {
		 HttpServletRequest request = RequestContextUtil.getRequest();
		 //url
		 String requestURI = request.getRequestURI();
		 //状态
		 String state = status ? "success" : "error";
		 //地址
		 String address = request.getRemoteAddr();

		 if (address.equals("0:0:0:0:0:0:0:1")){
			 try {
				 address = InetAddress.getLocalHost().getHostAddress();
			 } catch (UnknownHostException e) {
				 address = request.getRemoteAddr();
			 }
		 }


		 String s ="request "+ state +":{'requestValues': "+value+"}";
		 String j ="request "+ state +":{'requestUrl': "+requestURI+"; 'remoteAddress: '"+address +"; 'date':{"+result+"}}";
		 log.info(s);
		 log.info(j);
	 }
}
