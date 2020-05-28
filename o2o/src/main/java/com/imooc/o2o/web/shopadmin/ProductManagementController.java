package com.imooc.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptios.ProductOperationException;
import com.imooc.o2o.service.ProductCategoryService;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;

	//֧���ϴ���Ʒ����ͼ�������
	private static final int IMAGEMAXCOUNT = 6;
	/**
	 * ͨ������id��ȡ�õ����µ���Ʒ�б�
	 * */
	@RequestMapping(value = "/getproductlistbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getProductListByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//��ȡǰ̨��������ҳ��
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		//��ȡǰ̨��������ÿҳҪ�󷵻ص���Ʒ������
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		//�ӵ�ǰsession�л�ȡ������Ϣ����Ҫ�ǻ�ȡshopId
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		//��ֵ�ж�
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null)
				&& (currentShop.getShopId() != null)) {
			//��ȡ�������Ҫ�����������������Ƿ���Ҫ��ĳ����Ʒ����Լ�ģ��������Ʒ��ȥɸѡĳ�����̵���Ʒ�б�
			//ɸѡ���������Խ����������
			long productCategoryId = HttpServletRequestUtil.getLong(request,
					"productCategoryId");
			String productName = HttpServletRequestUtil.getString(request,
					"productName");
			Product productCondition = compactProductCondition(
					currentShop.getShopId(), productCategoryId, productName);
			//�����ѯ�����Լ���ҳ��Ϣ���в�ѯ��������Ӧ��Ʒ�б��Լ�����
			ProductExecution pe = productService.getProductList(
					productCondition, pageIndex, pageSize);
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}
	
	
	
	
	private Product compactProductCondition(Long shopId, long productCategoryId, String productName) {
		Product productCondition = new Product();
		Shop shop = new Shop();
		shop.setShopId(shopId);
		productCondition.setShop(shop);
		//����ָ������Ҫ������ӽ�ȥ
		if (productCategoryId != -1L) {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		//������Ʒ��ģ����ѯ��Ҫ������ӽ�ȥ
		if (productName != null) {
			productCondition.setProductName(productName);
		}
		return productCondition;
	}




	/**
	 * ͨ����Ʒid��ȡ��Ʒ��Ϣ
	 * */
	@RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getProductById(@RequestParam Long productId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//�ǿ��ж�
		if (productId > -1) {
			Product product = productService.getProductById(productId);
			//��ȡ�õ����µ���Ʒ����б�
			List<ProductCategory> productCategoryList = productCategoryService
					.getProductCategoryList(product.getShop().getShopId());
			modelMap.put("product", product);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty productId");
		}
		return modelMap;
	}
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//��֤��У��
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "�����˴������֤��");
			return modelMap;
		}
		//����ǰ�˲����ı����ĳ�ʼ����������Ʒ������ͼ������ͼ�б�ʵ����
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		String productStr = HttpServletRequestUtil.getString(request,
				"productStr");
		MultipartHttpServletRequest multipartRequest = null;
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		try {
			//�������д����ļ�������ȡ������ļ�����������ͼ������ͼ��
			if (multipartResolver.isMultipart(request)) {
				thumbnail = handleImage(request, productImgList);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "�ϴ�ͼƬ����Ϊ��");
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		try {
			//���Ի�ȡǰ�˴������ı�string��������ת����productʵ����
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		//��Product��Ϣ������ͼ������ͼ�б�ǿգ��������Ʒ��Ӳ���
		if (product != null && thumbnail != null && productImgList.size() > 0) {
			try {
				//��session�л�ȡ��ǰ����ID����ֵ��product,���ٶ�ǰ�����ݵ�����
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				product.setShop(currentShop);
				//ִ����Ӳ���
				ProductExecution pe = productService.addProduct(product,
						thumbnail, productImgList);
				if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (ProductOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "��������Ʒ��Ϣ");
		}
		return modelMap;
	}
	private ImageHolder handleImage(HttpServletRequest request, List<ImageHolder> productImgList) throws IOException {
		MultipartHttpServletRequest multipartRequest;
		ImageHolder thumbnail;
		multipartRequest = (MultipartHttpServletRequest) request;
		//ȡ������ͼ������ImageHolder����
		CommonsMultipartFile thumbnailFile=(CommonsMultipartFile) multipartRequest.getFile("thumbnail");
		thumbnail=new ImageHolder(thumbnailFile.getOriginalFilename(),thumbnailFile.getInputStream()); 
				
		//ȡ������ͼ�б�����List<ImageHolder>�б�������֧��ͼƬ6��
		for (int i = 0; i < IMAGEMAXCOUNT; i++) {
			CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest
					.getFile("productImg" + i);
			if (productImgFile != null) {
				//
				ImageHolder productImg=new ImageHolder(productImgFile.getOriginalFilename(), productImgFile.getInputStream());
				productImgList.add(productImg);
			}else{
				//
				break;
			}
		}
		return thumbnail;
	}
	/**
	 * ��Ʒ�༭
	 * */
	@RequestMapping(value = "/modifyproduct", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyProduct(HttpServletRequest request) {
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//����Ʒ�༭ʱ����û������¼ܲ�����ʱ�����
		//��Ϊǰ���������֤���жϣ�������������֤���ж�
		boolean statusChange = HttpServletRequestUtil.getBoolean(request,
				"statusChange");
		//��֤���ж�
		if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "�����˴������֤��");
			return modelMap;
		}
		//����ǰ�˲����ı����ĳ�ʼ����������Ʒ������ͼ������ͼ�б�ʵ����
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		ImageHolder thumbnail=null;
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		//�������д����ļ�������ȡ����ص��ļ�����������ͼ������ͼ��
	try{
		if (multipartResolver.isMultipart(request)) {
			thumbnail = handleImage(request, thumbnail, productImgList);
		}
	}catch(Exception e){
		modelMap.put("success", false);
		modelMap.put("errMsg", e.toString());
		return modelMap;
		
	}
		try {
			String productStr=HttpServletRequestUtil.getString(request, "productStr");
			//���Ի�ȡǰ�˴������ı�string����ת����Productʵ����
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		//--------�ǿ��ж�
		if (product != null) {
			try {
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				
				product.setShop(currentShop);
				ProductExecution pe = productService.modifyProduct(product,
						thumbnail, productImgList);
				if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "��������Ʒ��Ϣ");
		}
		return modelMap;
	}
	private ImageHolder handleImage(HttpServletRequest request, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//
		CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest
				.getFile("thumbnail");
		if(thumbnailFile!=null){
			thumbnail=new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
		}
		//
		for (int i = 0; i < IMAGEMAXCOUNT; i++) {
			CommonsMultipartFile productImgFile= (CommonsMultipartFile) multipartRequest
					.getFile("productImg" + i);
			if (productImgFile != null) {
				ImageHolder productImg=new ImageHolder(productImgFile.getOriginalFilename(), productImgFile.getInputStream());
			
				productImgList.add(productImg);
			}else{
				break;
			}
		}
		return thumbnail;
	}
	
}
