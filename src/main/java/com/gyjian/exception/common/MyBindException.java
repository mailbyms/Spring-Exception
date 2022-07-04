
package com.gyjian.exception.common;


import com.gyjian.exception.enums.MyHttpStatus;
import org.springframework.http.HttpStatus;

public class MyBindException extends RuntimeException{
	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -4137688758944857209L;

	/**
	 * http状态码
	 */
	private Integer httpStatusCode;

	private Object object;

	/**
	 * @param httpStatus http状态码
	 */
	public MyBindException(MyHttpStatus httpStatus) {
		super(httpStatus.getMsg());
		this.httpStatusCode = httpStatus.value();
	}

	/**
	 * @param httpStatus http状态码
	 */
	public MyBindException(MyHttpStatus httpStatus, String msg) {
		super(msg);
		this.httpStatusCode = httpStatus.value();
	}


	public MyBindException(String msg) {
		super(msg);
//		super(I18nMessage.getMessage(msg));
		this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
	}

	public MyBindException(String msg, Object object) {
		super(msg);
		this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
		this.object = object;
	}

}
