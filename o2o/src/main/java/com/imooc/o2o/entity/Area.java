package com.imooc.o2o.entity;

import java.util.Date;

//�����ʵ���ࣺ
public class Area {

	// ID
	private Integer areaId;
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	// ����
	private String areaName;
	// Ȩ��
	private Integer priority;
	// ����ʱ��
	private Date createTime;
	// ����ʱ��
	private Date lastEditTime;

}
