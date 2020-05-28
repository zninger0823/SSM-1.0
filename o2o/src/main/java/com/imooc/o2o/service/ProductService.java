 package com.imooc.o2o.service;

import java.io.InputStream;
import java.util.List;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exceptios.ProductOperationException;

public interface ProductService {
	
	
	/**
	 * ��ѯ��Ʒ�б���ҳ��������������У���Ʒ����ģ��������Ʒ״̬������Id,��Ʒ���
	 * */
	ProductExecution getProductList(Product productCondition,int pageIndex,int pageSize);
/**
 * �����Ʒ��Ϣ�Լ�ͼƬ����
 * 1.��������ͼ��������Ʒ����ͼƬ�������Ʒ��Ϣ
 * */
	
	ProductExecution addProduct(Product product,ImageHolder thumbnail,
			List<ImageHolder> productImgList) throws ProductOperationException;
	/**
	 * ͨ����ƷId��ѯΨһ����Ʒ��Ϣ
	 * */
	Product getProductById(long productId);
	
	/**
	 * �޸���Ʒ��Ϣ�Լ�ͼƬ����
	 * */
	ProductExecution modifyProduct(Product product,ImageHolder thumbnail,
			List<ImageHolder> productImgHolderList)
		throws ProductOperationException;
	
	
	
	
	
	
}




