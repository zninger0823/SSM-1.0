package com.imooc.o2o.dao;

import java.util.List;

import com.imooc.o2o.entity.Area;
/**
 * һ.DAO��						��.Service��						��.Ctroller��
 * 1.ʵ����       					1.����Service�ӿ�					1.ָ��Ctroller��ǩ��ָ��·��
 * 2.дDao�ӿ�					2.����ӿ�ʵ����					��.ǰ��
 * 3.mapperʵ��dao�ӿ�											1.html
 * 4.������														2.css/js
 * ͨ��shop id��ѯ������Ʒ���										3.��д·��
 * */
public interface AreaDao {
//1.��ѯ���������������б� areaList
	//2.��mapper������AreaDao.xml
	List<Area> queryArea();
}
