package com.imooc.o2o.dao;

import java.util.List;

import com.imooc.o2o.entity.ProductImg;
//���������Ʒ������ͼƬ
public interface ProductImgDao {

	List<ProductImg> queryProductImgList(long productId);
	/**
	 * ���������Ʒ����ͼƬ
	 * */
	int batchInsertProductImg(List<ProductImg> productImgList);
	/**
	 * ɾ��ָ����Ʒ�µ���������ͼ
	 * */
	int deleteProductImgByProductId(long productId);
	
}
