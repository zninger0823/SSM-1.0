package com.imooc.o2o.service;

import java.io.IOException;
import java.util.List;

import com.imooc.o2o.entity.HeadLine;

public interface HeadLineService {


	/**
	 * ���ݴ������������ָ����ͷ���б�
	 * */
  List<HeadLine> getHeadLineList(HeadLine headLineCondition)throws IOException;	
}
