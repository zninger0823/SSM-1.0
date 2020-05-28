package com.imooc.o2o.enums;

public enum ProductStateEnum {
	/*OFFLINE(-1, "�Ƿ���Ʒ"), SUCCESS(0, "�����ɹ�"), PASS(2, "ͨ����֤"), INNER_ERROR(
			-1001, "����ʧ��"),EMPTY(-1002, "��ƷΪ��");*/
   OFFLINE(-1,"�Ƿ���Ʒ"),SUCCESS(0,"�����ɹ�"),PASS(2,"ͨ����֤"),INNER_ERROR(-1001,"����ʧ��"),EMPTY(-1002,"��ƷΪ��");
	private int state;

	private String stateInfo;

	private ProductStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ProductStateEnum stateOf(int index) {
		for (ProductStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
