package com.imooc.o2o.dao;

import java.util.List;

import com.imooc.o2o.entity.Area;
/**
 * 一.DAO层						二.Service层						三.Ctroller层
 * 1.实体类       					1.创建Service接口					1.指定Ctroller标签，指定路由
 * 2.写Dao接口					2.定义接口实现类					四.前端
 * 3.mapper实现dao接口											1.html
 * 4.做测试														2.css/js
 * 通过shop id查询店铺商品类别										3.编写路由
 * */
public interface AreaDao {
//1.查询操作，返回区域列表 areaList
	//2.在mapper中配置AreaDao.xml
	List<Area> queryArea();
}
