package com.imooc.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.imooc.o2o.dto.ImageHolder;

import ch.qos.logback.classic.Logger;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	//设置时间格式
	private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	//随机数对象
	private static final Random r=new Random();
	private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	//处理缩略图---门面照、小图(文件流、保存地址)
	public static  String generateThumbnail(ImageHolder thumbnail,String targetAddr){
		//随机名、扩展名
		String realFileName=getRandomFileName();
		String extension=getFileExtension(thumbnail.getImageName());
	//创建目录
		makeDirPath(targetAddr);
		//获取相对路径
		String relativeAddr=targetAddr+realFileName+extension;
		
		File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
		//创建缩略图
		try{
			Thumbnails.of(thumbnail.getImage()).size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT,
					ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f) //添加水印
			.outputQuality(0.8f).toFile(dest);//指定压缩图片、保存对象路径
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return relativeAddr;
	}
	//创建目标路径所涉及的目录，即
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
		File dirPath=new File(realFileParentPath);//传入路径
		//判断路径是否存在
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
			
	}
	//获取输入文件流的扩展名
	private static String getFileExtension(String fileName) {
		
		
		return fileName.substring(fileName.lastIndexOf("."));
	}
	//生成随机文件、且每个文件名称要求不一样---当前年月日小时分钟秒数+五位随机数
	public static String getRandomFileName() {
		//获取随机五位数
		int rannum=r.nextInt(89999)+10000;
		String nowTimeStr=sDateFormat.format(new Date());
		return nowTimeStr+rannum;
	}
	public static void main(String[] args)throws IOException{

		Thumbnails.of(new File("D:/java/testphoto/xiaohuangren.jpg"))
		.size(200,200).watermark(Positions.BOTTOM_RIGHT,
				ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f).outputQuality(0.8f)
		.toFile("D:/java/testphoto/xiaohuangrennew.jpg");
	}
	/**
	 * storePath是文件的路径还是目录的路径
	 * 若storePath是文件路径则删除该文件
	 * 若storePath是目录路径则删除该目录下的文件
	 * */
	public static void deleteFileOrPath(String storePath){
		File fileOrPath=new File(PathUtil.getImgBasePath()+storePath);
		if(fileOrPath.exists()){
			if(fileOrPath.isDirectory()){
				File files[]=fileOrPath.listFiles();
				for(int i=0;i<files.length;i++){
					files[i].delete();
					}
			}
			fileOrPath.delete();
		}
		
	}
	/**处理详情图，并返回新生成图片的相对值路径
	 * */
	public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
		//获取不重复的随机名
		String realFileName =getRandomFileName();
		//获取文件的扩展名如png,jpg等
		String extension = getFileExtension(thumbnail.getImageName());
		//如果目标路径不存在则自动创建
		makeDirPath(targetAddr);
		//获取文件存储的相对路径（带文件名）
		String relativeAddr = targetAddr + realFileName + extension;
		//logger.debug("current relativeAddr is:"+relativeAddr);
		//获取文件想要保存的目标路径
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		//调用Thumbnail生成有水印的图片
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640)
			.watermark(Positions.BOTTOM_RIGHT,
					ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f) //添加水印
			.outputQuality(0.9f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		//返回图片相对路径
		return relativeAddr;
	}
	
	
}

















