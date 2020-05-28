package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;

/**
 * һ.DAO��						��.Service��						��.Ctroller��
 * 1.ʵ����       					1.����Service					1.ָ��Ctroller��ǩ��ָ��·��
 * 2.дDao�ӿ�					2.����ӿ�ʵ����					2.
 * 3.mapperʵ��dao�ӿ�			3.								3.
 * 4.������						4.
 * ͨ��shop id��ѯ������Ʒ���
 * */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest{

	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Test
	public void testBQueryByShopId()throws Exception{
		long shopId=5;
		List<ProductCategory> productCategoryList=productCategoryDao.queryProductCategoryList(shopId);
		System.out.println("�õ����Զ��������Ϊ��"+productCategoryList.size());
	
	}
	
	@Test
	public void testABatchInsertProductCategory(){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("��Ʒ���1");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(5L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("��Ʒ���2");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(5L);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		int effectedNum = productCategoryDao
				.batchInsertProductCategory(productCategoryList);
		assertEquals(2, effectedNum);
	}
	@Test
	public void testCDeleteProductCategory() throws Exception {
		long shopId = 5;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		for(ProductCategory pc:productCategoryList){
			if("��Ʒ���1".equals(pc.getProductCategoryName())||"��Ʒ���2".equals(pc.getProductCategoryName()))
				{
				int effectedNum = productCategoryDao.deleteProductCategory(
				pc.getProductCategoryId(), shopId);
				assertEquals(1, effectedNum);
				}
		}
		
	}
	
}





