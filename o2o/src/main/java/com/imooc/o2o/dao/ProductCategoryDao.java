package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.ProductCategory;

public interface ProductCategoryDao {

	/**1.ʵ����
	 * 2.дDao�ӿ�
	 * 3.mapperʵ��dao�ӿ�
	 * ͨ��shop id��ѯ������Ʒ���
	 * 4.������
	 * */
	List<ProductCategory> queryProductCategoryList(long shopId);
	/**
	 * ����������Ʒ���
	 * */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	
	/**ɾ��ָ����Ʒ���
	 * */
	
	int deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId);        
	
}
