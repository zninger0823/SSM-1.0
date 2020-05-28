package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * ��ҳ��ѯ���̣�������������У�������(ģ��)������״̬�������������Id,ower
	 *@param��������  ȡֵ���嵽�ĸ������������ڶ������ʱ��
	 *rowIndex�ӵڼ��п�ʼȡ
	 * */
	List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,@Param("rowIndex")int rowIndex,
			@Param("pageSize")int pageSize);
	
	/**
	 * ����queryShopList����
	 * */
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
	
	/**
	 * ͨ��shop id��ѯ����
	 * */
	Shop queryByShopId(long shopId);
	/**
	 * ��������
	 * ����ֵΪ1��������һ��
	 * */
	int insertShop(Shop shop);
	
	/**
	 * ���µ�����Ϣ
	 * */
	int updateShop(Shop shop);
	
}
