package com.imooc.o2o.util;

import javax.swing.JPopupMenu.Separator;

public class PathUtil {
	//获取文件分隔符
	private static String seperator=System.getProperty("file.separator");
	//获取操作系统名称
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
	//获取店铺图片的存储路径
	public static String getShopImagePath(long shopId){
		String imagePath="/upload/item/shop/"+shopId+"/";
		return imagePath.replace("/",seperator);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
