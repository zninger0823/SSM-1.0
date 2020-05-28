 package com.imooc.o2o.service;

import java.io.InputStream;
import java.util.List;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exceptios.ProductOperationException;

public interface ProductService {
	
	
	/**
	 * 查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺Id,商品类别
	 * */
	ProductExecution getProductList(Product productCondition,int pageIndex,int pageSize);
/**
 * 添加商品信息以及图片处理
 * 1.处理缩略图、处理商品详情图片、添加商品信息
 * */
	
	ProductExecution addProduct(Product product,ImageHolder thumbnail,
			List<ImageHolder> productImgList) throws ProductOperationException;
	/**
	 * 通过商品Id查询唯一的商品信息
	 * */
	Product getProductById(long productId);
	
	/**
	 * 修改商品信息以及图片处理
	 * */
	ProductExecution modifyProduct(Product product,ImageHolder thumbnail,
			List<ImageHolder> productImgHolderList)
		throws ProductOperationException;
	
	
	
	
	
	
}




