package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.ShopService;
//��������


public class ShopDaoTest extends BaseTest{

	@Autowired
	private ShopDao shopDao;
	@Test

	public void testQueryShopListAndCount(){
		Shop shopCondition=new Shop();
		PersonInfo owner=new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		List<Shop> shopList=shopDao.queryShopList(shopCondition, 0, 5);
		int count=shopDao.queryShopCount(shopCondition);
		System.out.println("�����б�Ĵ�С��"+shopList.size());
		System.out.println("����������"+count);
		ShopCategory sc=new ShopCategory();
		sc.setShopCategoryId(2L);
		shopCondition.setShopCategory(sc);
		shopList=shopDao.queryShopList(shopCondition, 0, 2);
		System.out.println("xin�����б�Ĵ�С��"+shopList.size());
		count=shopDao.queryShopCount(shopCondition);
		System.out.println("xin����������"+count);
		
	}
	
	@Test
	@Ignore
	public void testQueryByShopId(){
		long shopId=1;
		Shop shop=shopDao.queryByShopId(shopId);
		System.out.println("areaId:"+shop.getArea().getAreaId());
		System.out.println("areaName:"+shop.getArea().getAreaName());
	}
	@Test
	@Ignore //��ʾ�÷��������ԣ�����ִ��
	public void testInsertShop(){
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
		shop.setShopName("���Եĵ���");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);//1��ʾ���õ�
		shop.setAdvice("�����");
		//Ӱ������
		int effectedNum=shopDao.insertShop(shop);
		assertEquals(1,effectedNum);
		
	}
	@Test
	@Ignore
	public void testUpdateShop(){
		Shop shop=new Shop();
		shop.setShopId(1L);
		
		shop.setShopDesc("��������");
		shop.setShopAddr("���Ե�ַ");
		shop.setLastEditTime(new Date());
		//Ӱ������
		int effectedNum=shopDao.updateShop(shop);
		assertEquals(1,effectedNum);
	}
}





