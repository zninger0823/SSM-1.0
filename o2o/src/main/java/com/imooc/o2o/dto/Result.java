package com.imooc.o2o.dto;

/**
* ��װjson�������з��ؽ����ʹ����
*/
public class Result<T> {

	private boolean success;// �Ƿ�ɹ���־

	private T data;// �ɹ�ʱ���ص�����

	private String errorMsg;// ������Ϣ

	private int errorCode;

	public Result() {
	}

	// �ɹ�ʱ�Ĺ�����
	public Result(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	// ����ʱ�Ĺ�����
	public Result(boolean success, int errorCode, String errorMsg) {
		this.success = success;
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}

