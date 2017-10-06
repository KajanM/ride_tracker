package com.pluralsight.util;

/**
 * Created by k4j4n on 10/6/17.
 */
public class ServiceError {

	private int code;
	private String message;

	public ServiceError() {
	}

	public ServiceError(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
