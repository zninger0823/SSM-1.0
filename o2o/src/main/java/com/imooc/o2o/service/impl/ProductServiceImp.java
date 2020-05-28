package com.imooc.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptios.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;


@Service
public class ProductServiceImp implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;
	
	@Override
	@Transactional
	//1.��������ͼ����ȡ����ͼ���·������ֵ��product
	//2.��tb_productд����Ʒ��Ϣ����ȡproductId
	//3.���productId����������Ʒ����ͼ����mapper������useGeneratedKeys="true"�ɴ���ID
	//4.����Ʒ����ͼ�б���������tb_prodcut_img��
	public ProductExecution addProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgHolderList) throws ProductOperationException {
		//��ֵ�ж�
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			//����Ʒ����Ĭ������
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			//Ĭ���ϼ�״̬
			product.setEnableStatus(1);
			//����Ʒ����ͼ��Ϊ��ֵʱ�����
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				//������Ʒ��Ϣ
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("������Ʒʧ��");
				}
			} catch (Exception e) {
				throw new ProductOperationException("������Ʒʧ��:" + e.toString());
			}
			//����Ʒ���鲻Ϊ�������
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				addProductImgList(product, productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			//����Ϊ���򷵻ؿ�ֵ������Ϣ
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
	/**�������ͼƬ
	 * */
	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		// ��ȡͼƬ�洢·��������ֱ�Ӵ�ŵ���Ӧ���̵��ļ��е���
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList =new ArrayList<ProductImg>();
		//����ͼƬһ��ȥ��������ӽ�prodcutImgʵ������
		
			for (ImageHolder productImgHolder : productImgHolderList) {
				String imgAddr=ImageUtil.generateNormalImg(productImgHolder,dest);
				ProductImg productImg = new ProductImg();
				productImg.setImgAddr(imgAddr);
				productImg.setProductId(product.getProductId());
				productImg.setCreateTime(new Date());
				productImgList.add(productImg);
			}
			//���ȷʵ����ͼƬ��Ҫ��ӵģ���ִ��������Ӳ���
			if(productImgList.size()>0){
				try{
					int effectedNum=productImgDao.batchInsertProductImg(productImgList);
					if(effectedNum<=0){
						throw new ProductOperationException("������Ʒ����ͼƬʧ��");
					}
				}catch(Exception e){
					throw new ProductOperationException("������Ʒ����ͼƬʧ�ܣ�"+e.toString());
				}
			}
		}
	
	/**�������ͼ
	 * */
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}
	@Override
	public Product getProductById(long productId) {
		
		return productDao.queryProductById(productId);
	}
	@Override
	@Transactional
	//1.������ͼ������ֵ����������ͼ
	//���Ѵ�������ͼ���Ƚ���ɾ���������ͼ��֮���ȡ����ͼ���·������ֵ��product
	//2.����Ʒ����ͼ�б������ֵ������Ʒ����ͼƬ�б����ͬ���Ĳ���
	//3.��tb_product_img����ĸ���Ʒԭ�ȵ���Ʒ����ͼ��¼ȫ�����
	//4.����tb_product����Ϣ
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgHolderList) throws ProductOperationException {
		//��ֵ�ж�
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			//������ƷĬ������
			product.setLastEditTime(new Date());
			//����Ʒ����ͼ��Ϊ����ԭ������ͼ��Ϊ����ɾ��ԭ������ͼ�����
			if (thumbnail != null) {
				//�Ȼ�ȡһ��ԭ����Ϣ��ԭ����Ϣ��ԭͼƬ��ַ
				Product tempProduct = productDao.queryProductById(product.getProductId());
				if (tempProduct.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
				}
				addThumbnail(product, thumbnail);
			}
			//������´������Ʒ����ͼ����ԭ��ɾ����������µ�ͼƬ
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				deleteProductImgList(product.getProductId());
				addProductImgList(product, productImgHolderList);
			}
			try {
				//������Ʒ��Ϣ
				int effectedNum = productDao.updateProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("������Ʒ��Ϣʧ��");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new ProductOperationException("������Ʒ��Ϣʧ��:" + e.toString());
			}
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
	/**
	 * ɾ��ĳ����Ʒ�µ���������ͼ
	 * */
	private void deleteProductImgList(Long productId) {
		//����productId��ȡԭ��ͼƬ
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		//�ɵ�ԭ��ͼƬ
		for (ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		//ɾ�����ݿ���ԭ��ͼƬ��Ϣ
		productImgDao.deleteProductImgByProductId(productId);
		
	}
	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		//ҳ��ת�������ݿ�����룬������dao��ȡ��ָ��ҳ�����Ʒ�б�
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		//����ͬ���Ĳ�ѯ�������ز�ѯ�����µ���Ʒ����
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		return pe;
		
	}

	
}














