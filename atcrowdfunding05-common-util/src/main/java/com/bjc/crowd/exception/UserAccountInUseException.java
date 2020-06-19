package com.bjc.crowd.exception;

/** 用户账号已被使用异常
 * @author Administrator
 *
 */
public class UserAccountInUseException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserAccountInUseException() {
		super();
	}

	public UserAccountInUseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserAccountInUseException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAccountInUseException(String message) {
		super(message);
	}

	public UserAccountInUseException(Throwable cause) {
		super(cause);
	}

}
