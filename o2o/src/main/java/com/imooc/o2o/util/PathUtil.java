package com.imooc.o2o.util;

import javax.swing.JPopupMenu.Separator;

public class PathUtil {
	//��ȡ�ļ��ָ���
	private static String seperator=System.getProperty("file.separator");
	//��ȡ����ϵͳ����
	public static String getImgBasePath(){
		String os=System.getProperty("os.name");
		String basePath="";
		if(os.toLowerCase().startsWith("win")){
			basePath="D:/java/projectdev/image/";
		}else{
			basePath="/home/zhao/image/";
		}
		basePath=basePath.replace("/", seperator);
		return basePath;
	}
	//��ȡ����ͼƬ�Ĵ洢·��
	public static String getShopImagePath(long shopId){
		String imagePath="/upload/item/shop/"+shopId+"/";
		return imagePath.replace("/",seperator);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
