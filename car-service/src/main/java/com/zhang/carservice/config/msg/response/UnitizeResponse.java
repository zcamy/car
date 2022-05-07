
package com.zhang.carservice.config.msg.response;

import com.zhang.carservice.config.enums.ResultCode;
import com.zhang.carservice.config.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 格式化返回数据
 *
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UnitizeResponse {

	private Integer code;
	private String msg;

	private Object data;

	private String errorCause;

	protected String mainCause;

	protected String trace;

	private Long requestId;


	
	public static UnitizeResponse success() {
		UnitizeResponse result = new UnitizeResponse();
		result.setResultCode(ResultCode.SUCCESS);
		return result;
	}

	public static UnitizeResponse success(Object data) {
		UnitizeResponse result = new UnitizeResponse();
		result.setResultCode(ResultCode.SUCCESS);
		result.setData(data);
		return result;
	}

	private void setResultCode(ResultCode code) {
		this.code = code.code();
		this.msg = code.message();
	}
	
	public static UnitizeResponse error(BusinessException e) {
		UnitizeResponse u = new UnitizeResponse();
		u.setCode(e.getCode());
		u.setErrorCause(e.getErrorCause());
		u.setMainCause(e.getMainCause());
		u.setMsg(e.getMessage());
		u.setTrace(e.getTrace());
		u.setRequestId(e.getRequestId());
		return u;
	}
}
