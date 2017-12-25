package com.fin.moblibrary.model;

/**
 * @author KatherineJY
 * ResponseWrapper
 * 封装前后端传递的数据信息
 */
public class ResponseWrapper {
	private boolean isSuccessful;
	private String errorMessage;
	private Object body;

	public ResponseWrapper(boolean isSuccessful, String errorMessage, Object body) {
		this.isSuccessful = isSuccessful;
		this.errorMessage = errorMessage;
		this.body = body;
	}
	
	public ResponseWrapper() {};
	
	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		if( isSuccessful )
			return "isSuccesful:" + isSuccessful + "\t returnBody:" + body.toString();
		else
			return "isSuccesful:" + isSuccessful + "\t errorMsg:" + errorMessage;
	}
}
