package com.imooc.o2o.util;

public class PageCalculator {
	public static int calculateRowIndex(int pageIndex,int pageSize){
		//pageIndex:ҳ����
		//pageSize:��������[��5������Ϊ0-4]
		return (pageIndex>0)?(pageIndex-1)*pageSize:0;
	}
}
