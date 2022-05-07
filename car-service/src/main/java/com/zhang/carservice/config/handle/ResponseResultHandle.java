package com.zhang.carservice.config.handle;


import com.zhang.carservice.config.constants.DBConstants;
import com.zhang.carservice.config.msg.response.UnitizeResponse;
import com.zhang.carservice.config.util.JsonUtil;
import com.zhang.carservice.config.util.RequestContextUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理返回数据
 */
@Log4j2
@ControllerAdvice
public class ResponseResultHandle implements ResponseBodyAdvice<Object>{

	@Override
	//判断是否要执行beforeBodyWrite方法，true为执行，false不执行
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		HttpServletRequest request = RequestContextUtil.getRequest();
		String flag = (String) request.getAttribute(DBConstants.RESPONSE_RESULT);
		return flag != null && flag.equals(DBConstants.RESPONSE_RESULT);
	}

	@Override
	//对response处理的执行方法
	public Object beforeBodyWrite(
			Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if (body instanceof UnitizeResponse) {
			return body;
		} else if (body instanceof String) {
			return JsonUtil.object2Json(UnitizeResponse.success(body));
		} else {
			return UnitizeResponse.success(body);
		} 
	}
}
