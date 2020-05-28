package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.ProductCategory;

public interface ProductCategoryDao {

	/**1.实体类
	 * 2.写Dao接口
	 * 3.mapper实现dao接口
	 * 通过shop id查询店铺商品类别
	 * 4.做测试
	 * */
	List<ProductCategory> queryProductCategoryList(long shopId);
	/**
	 * 批量新增商品类别
	 * */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	
	/**删除指定商品类别
	 * */
	
	int deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId);        
	
}
