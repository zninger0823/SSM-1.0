package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptios.ShopOperationException;

public class ProductServiceTest extends BaseTest{
	
	@Autowired
	private ProductService productService;
	@Test
	@Ignore
	public void testAddProduct() throws ShopOperationException,FileNotFoundException{
		//����shopIdΪ5��productCategoryIdΪ5����Ʒʵ���������Ա������ֵ
		Product product=new Product();
		Shop shop=new Shop();
		shop.setShopId(5L);
		ProductCategory pc=new ProductCategory();
		pc.setProductCategoryId(5L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("������Ʒһ");
		product.setProductDesc("������Ʒһ");
		product.setPriority(20);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		//��������ͼ�ļ���
		File thumbnailFile=new File("D:/java/testphoto/xiaohuangren.jpg");
		InputStream is=new FileInputStream(thumbnailFile);
		ImageHolder thumbnail=new ImageHolder(thumbnailFile.getName(), is);
		//
		File productImg1=new File("D:/java/testphoto/xiaohuangren.jpg");
		InputStream is1=new FileInputStream(productImg1);
		File productImg2=new File("D:/java/testphoto/dabai.jpg");
		InputStream is2=new FileInputStream(productImg2);
		
		List<ImageHolder> productImgList=new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		//�����Ʒ����֤
		ProductExecution pe=productService.addProduct(product, thumbnail, productImgList);
		
		assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
		
	}
	@Test
	
	public void testModifyProduct() throws ShopOperationException,FileNotFoundException{
		//����shopIdΪ5��productCategoryIdΪ5����Ʒʵ���������Ա������ֵ
		Product product=new Product();
		Shop shop=new Shop();
		shop.setShopId(5L);
		ProductCategory pc=new ProductCategory();
		pc.setProductCategoryId(5L);
		product.setProductId(5L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("��ʽ��Ʒһ");
		product.setProductDesc("��ʽ��Ʒһ");
		product.setPriority(20);
		
		//��������ͼ�ļ���
		File thumbnailFile=new File("D:/java/testphoto/dabai.jpg");
		InputStream is=new FileInputStream(thumbnailFile);
		ImageHolder thumbnail=new ImageHolder(thumbnailFile.getName(), is);
		//����������Ʒ����ͼ�ļ�������������ӵ�����ͼ�б���
		File productImg1=new File("D:/java/testphoto/xiaohuangren.jpg");
		InputStream is1=new FileInputStream(productImg1);
		File productImg2=new File("D:/java/testphoto/ercode.jpg");
		InputStream is2=new FileInputStream(productImg2);
		
		List<ImageHolder> productImgList=new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		//�����Ʒ����֤
		ProductExecution pe=productService.modifyProduct(product, thumbnail, productImgList);
		
		assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
		
	}

}
