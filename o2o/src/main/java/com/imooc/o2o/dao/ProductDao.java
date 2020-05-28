package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.Product;

public interface ProductDao {
/**
 * ��ѯ��Ʒ�б���ҳ��������������У���Ʒ����ģ��������Ʒ״̬������ID,
 * ��Ʒ״̬����Ʒ���
 * */
	List<Product> queryProductList(@Param("productCondition")
	Product productCondition,@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);
	/**
	 * ��ѯ��Ӧ����Ʒ����
	 * */
	int queryProductCount(@Param("productCondition")Product productCondition);
	
	/**
	 * ͨ��productId��ѯΨһ����Ʒ��Ϣ��������Ʒ��Ϣ
	 * */
	Product queryProductById(long productId);
	/**
	 * ������Ʒ��Ϣ,���ص���intӰ�������
	 * */
	int updateProduct(Product product);
	/**
	 * ɾ����Ʒ���֮ǰ������Ʒ���ID��Ϊ��,����ֵintӰ�������
	 * */
	int updateProductCategoryToNull(long productCategoryId);
	/**
	 * ������Ʒ
	 * */
	int insertProduct(Product product);
	
}
