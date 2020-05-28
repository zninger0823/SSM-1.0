package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.entity.Shop;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest{
	@Autowired
	private ProductImgDao productImgDao;

	@Test
	public void testABatchInsertProductImg() throws Exception {
		
		//prodcutIdΪ5����Ʒ�������������ͼƬ��¼
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("ͼƬ1");
		productImg1.setImgDesc("����ͼƬ1");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(2L);
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("ͼƬ2");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(2L);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
	}
	@Test
	public void testBQueryProductImgList(){
		//���productIdΪ2����Ʒ�Ƿ����ҽ�����������ͼƬ
		List<ProductImg> productImgList=productImgDao.queryProductImgList(2L);
		assertEquals(2, productImgList.size());
	}
	
	@Test
	public void testCDeleteProductImgByProductId() throws Exception {
		//ɾ��������������Ʒ����ͼƬ��¼
		long productId = 2;
		int effectedNum = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2, effectedNum);
	}
}
