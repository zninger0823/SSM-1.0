package com.imooc.o2o.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptios.ShopOperationException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;
//����ע��
@Service
public class ShopServiceImp implements ShopService{
	@Autowired
	private ShopDao ShopDao;
	//rowIndexǰ��ʶ����<--pageIndexת��
	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex=PageCalculator.calculateRowIndex(pageIndex, pageSize);
		//��ȡ�б�
		List<Shop> shopList=ShopDao.queryShopList(shopCondition, rowIndex, pageSize);
		//����
		int count=ShopDao.queryShopCount(shopCondition);
		ShopExecution se=new ShopExecution();
		if(shopList!=null){
			se.setShopList(shopList);
			se.setCount(count);
		}else{
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}
	
	@Override
	@Transactional  //��������ǩ��ʾ�÷�����Ҫ����֧��
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException{
		//��ֵ�ж�
		if(shop==null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try{
			//��������Ϣ����ʼֵ
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum=ShopDao.insertShop(shop);
			//�жϴ˴ε��������Ƿ�ɹ�
			if(effectedNum<=0){
				throw new ShopOperationException("���̴���ʧ��");
			}else{
				if(thumbnail.getImage()!=null){
					//�洢ͼƬ
					try{
					addShopImg(shop,thumbnail);}catch(Exception e){
						throw new ShopOperationException("addShopImg error:"+e.getMessage());
					}
					//���µ��̵�ַͼƬ
					effectedNum=ShopDao.updateShop(shop);
					if(effectedNum<=0){
						throw new ShopOperationException("����ͼƬ��ַʧ��");
					}
				}
				
			}
			
		}
		catch(Exception e){
			throw new ShopOperationException("addShop error:"+e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}
	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		//��ȡshopͼƬĿ¼�����·��
		String dest=PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr=ImageUtil.generateThumbnail(thumbnail,dest);
		shop.setShopImg(shopImgAddr);
	}
	@Override
	public Shop getByShopId(long shopId) {
		
		return ShopDao.queryByShopId(shopId);
	}
	@Override
	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail)
			throws ShopOperationException {
		if(shop==null||shop.getShopId()==null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}else{//1.�ж��Ƿ���Ҫ����ͼƬ
			try{
			if(thumbnail.getImage()!=null&&thumbnail.getImageName()!=null&&!"".equals(thumbnail.getImageName())){
				Shop tempShop=ShopDao.queryByShopId(shop.getShopId());
				if(tempShop.getShopImg()!=null){
					ImageUtil.deleteFileOrPath(tempShop.getShopImg());
				}
				addShopImg(shop, thumbnail);
			}

			//2.���µ�����Ϣ
			shop.setLastEditTime(new Date());
			int effectedNum=ShopDao.updateShop(shop);
			if(effectedNum<=0){
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			}else{
				shop=ShopDao.queryByShopId(shop.getShopId());
				return new ShopExecution(ShopStateEnum.SUCCESS,shop);
			}
			}catch(Exception e){
				throw new ShopOperationException("modifyShop erro:"+e.getMessage());
			}
			
		}
		
	}
	

	
	
	
	
	
}
