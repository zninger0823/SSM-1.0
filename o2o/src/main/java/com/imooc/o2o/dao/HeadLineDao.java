package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.HeadLine;

public interface HeadLineDao {
	/**
	 * ���ݴ���Ĳ�ѯ����(ͷ������ѯͷ��)
	 * */
		List<HeadLine> queryHeadLine(
				@Param("headLineCondition")HeadLine headLineCondition);
}
