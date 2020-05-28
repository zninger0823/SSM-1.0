package com.imooc.o2o.dao;

import java.util.List;

import com.imooc.o2o.entity.ProductImg;
//批量添加商品的详情图片
public interface ProductImgDao {

	List<ProductImg> queryProductImgList(long productId);
	/**
	 * 批量添加商品详情图片
	 * */
	int batchInsertProductImg(List<ProductImg> productImgList);
	/**
	 * 删除指定商品下的所有详情图
	 * */
	int deleteProductImgByProductId(long productId);
	
}
