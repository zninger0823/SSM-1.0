package com.imooc.o2o.service;

import java.util.List;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.exceptios.ProductCategoryOperationException;

public interface ProductCategoryService {
	/**
	 * ��ѯָ��ĳ�������µ�������Ʒ�����Ϣ
	 * */
	
	List<ProductCategory> getProductCategoryList(long shopId);
	/**
	 * ���������Ʒ�����Ϣ
	 * */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
	  throws ProductCategoryOperationException;

	/**
	 * ��������µ���Ʒ������id��Ϊ�գ���ɾ��������Ʒ���
	 * */
	ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId)throws ProductCategoryOperationException;

}
