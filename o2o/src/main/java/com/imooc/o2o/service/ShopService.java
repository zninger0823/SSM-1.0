package com.imooc.o2o.service;

import java.io.File;
import java.io.InputStream;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exceptios.ShopOperationException;

public interface ShopService {
/**
*����shopCondition��ҳ������Ӧ�����б� 
* */
	public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
	
/**
 * ͨ������Id��ȡ������Ϣ
 * */
	Shop getByShopId(long shopId);
	/**
	 * ���µ�����Ϣ��������ͼƬ�ô���
	 * */
	ShopExecution modifyShop(Shop shop,ImageHolder thumbnail)throws ShopOperationException;
	
	/**
	 * ע�������Ϣ������ͼƬ����
	 * */
	 ShopExecution addShop(Shop shop,ImageHolder thumbnail)throws ShopOperationException;
	
	
}
