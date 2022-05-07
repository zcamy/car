package com.zhang.carservice.config.handle;


import com.zhang.carservice.config.enums.ResultCode;
import com.zhang.carservice.config.exception.BusinessException;
import com.zhang.carservice.config.msg.response.UnitizeResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandle {

	//错误信息
    @ExceptionHandler(value = BusinessException.class)
    public UnitizeResponse errorHandler(BusinessException e) {
		log.error( "错误原因 ：" + e.getMainCause(),e);
        return UnitizeResponse.error(e);
    }

    //请求值错误
    @ExceptionHandler(value = RuntimeException.class)
    public UnitizeResponse requestValueError(RuntimeException e) {
		BusinessException ex = new BusinessException(ResultCode.REQUEST_VALUE_ERROR,e);
		log.error("错误原因 ：" + ex.getMainCause(),ex);
		return UnitizeResponse.error(ex);
    }

    //映射错误
    @ExceptionHandler(value = Exception.class)
    public UnitizeResponse mappingValueError(Exception e,String s) {
        BusinessException ex = new BusinessException(ResultCode.MAPPING_VALUE_ERROR,e,s);
        log.error(s +"\n" + ex.getMainCause(),ex);
        return UnitizeResponse.error(ex);
    }
}
