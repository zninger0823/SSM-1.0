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
	//����ʱ���ʽ
	private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	//���������
	private static final Random r=new Random();
	private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	//��������ͼ---�����ա�Сͼ(�ļ����������ַ)
	public static  String generateThumbnail(ImageHolder thumbnail,String targetAddr){
		//���������չ��
		String realFileName=getRandomFileName();
		String extension=getFileExtension(thumbnail.getImageName());
	//����Ŀ¼
		makeDirPath(targetAddr);
		//��ȡ���·��
		String relativeAddr=targetAddr+realFileName+extension;
		
		File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
		//��������ͼ
		try{
			Thumbnails.of(thumbnail.getImage()).size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT,
					ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f) //���ˮӡ
			.outputQuality(0.8f).toFile(dest);//ָ��ѹ��ͼƬ���������·��
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return relativeAddr;
	}
	//����Ŀ��·�����漰��Ŀ¼����
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
		File dirPath=new File(realFileParentPath);//����·��
		//�ж�·���Ƿ����
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
			
	}
	//��ȡ�����ļ�������չ��
	private static String getFileExtension(String fileName) {
		
		
		return fileName.substring(fileName.lastIndexOf("."));
	}
	//��������ļ�����ÿ���ļ�����Ҫ��һ��---��ǰ������Сʱ��������+��λ�����
	public static String getRandomFileName() {
		//��ȡ�����λ��
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
	 * storePath���ļ���·������Ŀ¼��·��
	 * ��storePath���ļ�·����ɾ�����ļ�
	 * ��storePath��Ŀ¼·����ɾ����Ŀ¼�µ��ļ�
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
	/**��������ͼ��������������ͼƬ�����ֵ·��
	 * */
	public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
		//��ȡ���ظ��������
		String realFileName =getRandomFileName();
		//��ȡ�ļ�����չ����png,jpg��
		String extension = getFileExtension(thumbnail.getImageName());
		//���Ŀ��·�����������Զ�����
		makeDirPath(targetAddr);
		//��ȡ�ļ��洢�����·�������ļ�����
		String relativeAddr = targetAddr + realFileName + extension;
		//logger.debug("current relativeAddr is:"+relativeAddr);
		//��ȡ�ļ���Ҫ�����Ŀ��·��
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		//����Thumbnail������ˮӡ��ͼƬ
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640)
			.watermark(Positions.BOTTOM_RIGHT,
					ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f) //���ˮӡ
			.outputQuality(0.9f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("��������ͼʧ�ܣ�" + e.toString());
		}
		//����ͼƬ���·��
		return relativeAddr;
	}
	
	
}

















