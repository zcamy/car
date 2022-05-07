package com.zhang.carservice.config.exception;



import com.zhang.carservice.config.enums.ResultCode;
import com.zhang.carservice.config.util.RequestContextUtil;
import com.zhang.carservice.config.util.StrUtils;
import lombok.Data;

import java.util.Arrays;

@Data
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 3275057317616326272L;
	
	protected Integer code;

	protected String message;

	private String errorCause;

	// 错误原因
	protected String mainCause;
	
	//堆栈信息
	protected String trace;
	private Long requestId;

	
	public BusinessException (Integer code,String message, Exception e) {
		this.requestId = RequestContextUtil.getRequestId();
		this.code = code;
		this.message = message;
		this.mainCause = e.getMessage();
		this.trace = getStackMsg(e);
	}
	
	public BusinessException (String message) {
		this.requestId = RequestContextUtil.getRequestId();
		this.code = ResultCode.SYSTEM_INNER_ERROR.code();
		this.message = message;
	}

	public BusinessException(Integer code, String format,Exception e, Object... objects) {
		this.requestId = RequestContextUtil.getRequestId();
		this.code = code;
		this.message = StrUtils.formatIfArgs(format, "{}", objects);
		this.mainCause = e.getMessage();
		this.trace = getStackMsg(e);
	}
	
	public BusinessException(ResultCode resultCode) {
		this.requestId = RequestContextUtil.getRequestId();
		this.code = resultCode.code();
		this.message = resultCode.message();
	}
	
	public BusinessException(ResultCode resultCode, Object... objects) {
		this.requestId = RequestContextUtil.getRequestId();
		this.code = resultCode.code();
		this.message = StrUtils.formatIfArgs(resultCode.message(), "{}", objects);
	}

	public BusinessException(ResultCode resultCode,Exception e, Object... objects) {
		this.requestId = RequestContextUtil.getRequestId();
		this.message = StrUtils.formatIfArgs(resultCode.message(), "{}", objects);
		this.mainCause = e.getMessage();
		this.trace = getStackMsg(e);
	}
	
	public BusinessException(ResultCode resultCode,Exception e) {
		this(resultCode);
		this.errorCause=resultCode.errorMsg();
		this.mainCause =e.getMessage();
		this.trace = getStackMsg(e);
	}
	
	 private static String getStackMsg(Exception e) {
		 StackTraceElement[] copyOfRange = Arrays.copyOfRange(e.getStackTrace(), 0, 5);
		 return Arrays.toString(copyOfRange);
     }
}
