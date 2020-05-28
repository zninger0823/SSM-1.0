package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptios.ShopOperationException;
//�޸ĵ���
public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	@Test
	public void testGetShopList(){
		Shop shopCondition=new Shop();
		ShopCategory sc=new ShopCategory();
		sc.setShopCategoryId(2L);
		shopCondition.setShopCategory(sc);
		ShopExecution se=shopService.getShopList(shopCondition, 1, 2);
		System.out.println("�����б�Ĵ�С��"+se.getShopList().size());
		System.out.println("����������"+se.getCount());
		
	}
	@Ignore
	@Test
	public void testModifyShop()throws ShopOperationException,FileNotFoundException{
		Shop shop=new Shop();
		shop.setShopId(1L);
		shop.setShopName("�޸ĺ�ĵ�������");
		File shopImg=new File("D:/java/testphoto/dabai.jpg");
		InputStream is=new FileInputStream(shopImg);
		ImageHolder imageHolder=new ImageHolder("dabai.jpg", is);
		ShopExecution shopExecution=shopService.modifyShop(shop,imageHolder);
		System.out.println("�µ�ͼƬ��ַΪ��"+shopExecution.getShop().getShopImg());
		
	}
	
	
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException{
		Shop shop=new Shop();
		PersonInfo owner=new PersonInfo();
		Area area=new Area();
		ShopCategory shopCategory=new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("ƻ��");
		shop.setShopDesc("����");
		shop.setShopAddr("ɽ��");
		shop.setPhone("123111");
		shop.setShopImg("test5");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());//1��ʾ���õ�
		shop.setAdvice("�����");
		File shopImg=new File("D:/java/testphoto/xiaohuangren.jpg");
		InputStream is=new FileInputStream(shopImg);
		ImageHolder imageHolder=new ImageHolder(shopImg.getName(), is);
		ShopExecution se=shopService.addShop(shop,imageHolder);
		assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
	
	
	}
	
	
}
