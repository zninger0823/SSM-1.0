package com.imooc.o2o.dao;





import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{
	@Autowired
	private ShopCategoryDao shopCategoryDao;//����Dao
	
	@Test
	public void testQueryShopCategory(){
		List<ShopCategory> shopCategoryList=shopCategoryDao.queryShopCategory(null);
		/*assertEquals(2,shopCategoryList.size());
		ShopCategory testCategory=new ShopCategory();
		ShopCategory parentCategory=new ShopCategory();
		parentCategory.setShopCategoryId(1L);
		testCategory.setParent(parentCategory);
		shopCategoryList=shopCategoryDao.queryShopCategory(testCategory);
		assertEquals(2,shopCategoryList.size());
		System.out.println(shopCategoryList.get(1).getShopCategoryName());*/
		System.out.println(shopCategoryList.size());}

}
