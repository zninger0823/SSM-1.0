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
//店铺注册
@Service
public class ShopServiceImp implements ShopService{
	@Autowired
	private ShopDao ShopDao;
	//rowIndex前端识别函数<--pageIndex转换
	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex=PageCalculator.calculateRowIndex(pageIndex, pageSize);
		//获取列表
		List<Shop> shopList=ShopDao.queryShopList(shopCondition, rowIndex, pageSize);
		//总数
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
	@Transactional  //添加事务标签表示该方法需要事务支持
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException{
		//空值判断
		if(shop==null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try{
			//给店铺信息赋初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum=ShopDao.insertShop(shop);
			//判断此次店铺增加是否成功
			if(effectedNum<=0){
				throw new ShopOperationException("店铺创建失败");
			}else{
				if(thumbnail.getImage()!=null){
					//存储图片
					try{
					addShopImg(shop,thumbnail);}catch(Exception e){
						throw new ShopOperationException("addShopImg error:"+e.getMessage());
					}
					//更新店铺地址图片
					effectedNum=ShopDao.updateShop(shop);
					if(effectedNum<=0){
						throw new ShopOperationException("更新图片地址失败");
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
		//获取shop图片目录的相对路径
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
		}else{//1.判断是否需要处理图片
			try{
			if(thumbnail.getImage()!=null&&thumbnail.getImageName()!=null&&!"".equals(thumbnail.getImageName())){
				Shop tempShop=ShopDao.queryByShopId(shop.getShopId());
				if(tempShop.getShopImg()!=null){
					ImageUtil.deleteFileOrPath(tempShop.getShopImg());
				}
				addShopImg(shop, thumbnail);
			}

			//2.更新店铺信息
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
