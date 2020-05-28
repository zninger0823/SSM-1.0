package com.imooc.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.exceptios.ProductCategoryOperationException;
import com.imooc.o2o.service.ProductCategoryService;
@Service //指实现ProductCategoryService
public class ProductCategoryServiceImp implements ProductCategoryService{
	@Autowired  //指spring动态注入productCategoryDao
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;
	//返回信息
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		
		return productCategoryDao.queryProductCategoryList(shopId);
	}

	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if (effectedNum <= 0) {
					throw new ProductCategoryOperationException("店铺类别失败");
				} else {

					return new ProductCategoryExecution(
							ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException("batchAddProductCategory error: "
						+ e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(
					ProductCategoryStateEnum.EMPTY_LIST);
		}

	}
	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		//解除tb_product里的商品与该productcategoryId的关联
		// TODO 将此商品类别下的商品的类别ID置为空
		try{
			int effectedNum=productDao.updateProductCategoryToNull(productCategoryId);
			if(effectedNum<0){
				throw new ProductCategoryOperationException("商品类别更新失败");
			}
		}catch(Exception e){
			throw new ProductCategoryOperationException("deleteProductCategory erro:"+e.getMessage());
		}
		//删除该productCategory
			try {
				int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
				if (effectedNum < 0) {
					throw new ProductCategoryOperationException("商品类别更新失败");
				}else{
					return new ProductCategoryExecution(
							ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException("deleteProductCategory error: "
						+ e.getMessage());
			}
			
		}

	}
	
	
	


