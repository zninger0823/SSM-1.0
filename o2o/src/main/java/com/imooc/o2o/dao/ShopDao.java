package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 分页查询店铺，可输入的条件有：店铺名(模糊)，店铺状态，店铺类别，区域Id,ower
	 *@param的作用是  取值具体到哪个参数（适用于多个参数时）
	 *rowIndex从第几行开始取
	 * */
	List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,@Param("rowIndex")int rowIndex,
			@Param("pageSize")int pageSize);
	
	/**
	 * 返回queryShopList总数
	 * */
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
	
	/**
	 * 通过shop id查询店铺
	 * */
	Shop queryByShopId(long shopId);
	/**
	 * 新增店铺
	 * 返回值为1新增店铺一个
	 * */
	int insertShop(Shop shop);
	
	/**
	 * 更新店铺信息
	 * */
	int updateShop(Shop shop);
	
}
