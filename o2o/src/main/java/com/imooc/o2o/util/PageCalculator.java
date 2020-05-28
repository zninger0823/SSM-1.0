package com.imooc.o2o.util;

public class PageCalculator {
	public static int calculateRowIndex(int pageIndex,int pageSize){
		//pageIndex:页码数
		//pageSize:几条数据[如5：数据为0-4]
		return (pageIndex>0)?(pageIndex-1)*pageSize:0;
	}
}
