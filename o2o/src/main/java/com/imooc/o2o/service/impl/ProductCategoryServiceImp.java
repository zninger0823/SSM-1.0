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
@Service //ָʵ��ProductCategoryService
public class ProductCategoryServiceImp implements ProductCategoryService{
	@Autowired  //ָspring��̬ע��productCategoryDao
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;
	//������Ϣ
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
					throw new ProductCategoryOperationException("�������ʧ��");
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
		//���tb_product�����Ʒ���productcategoryId�Ĺ���
		// TODO ������Ʒ����µ���Ʒ�����ID��Ϊ��
		try{
			int effectedNum=productDao.updateProductCategoryToNull(productCategoryId);
			if(effectedNum<0){
				throw new ProductCategoryOperationException("��Ʒ������ʧ��");
			}
		}catch(Exception e){
			throw new ProductCategoryOperationException("deleteProductCategory erro:"+e.getMessage());
		}
		//ɾ����productCategory
			try {
				int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
				if (effectedNum < 0) {
					throw new ProductCategoryOperationException("��Ʒ������ʧ��");
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
	
	
	


