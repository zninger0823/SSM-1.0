package com.imooc.o2o.web.frontend;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;

@Controller

@RequestMapping(value = "/frontend")
public class ShopListController {
	@Autowired 
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	/**
	 * ������Ʒ�б����ShopCategory�б���������һ������
	 * �Լ�������Ϣ�б�
	 * */
	@RequestMapping(value="/listshopspageinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShopsPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//���Ŵ�ǰ�������л�ȡparentId
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		List<ShopCategory> shopCategoryList = null;
		if (parentId != -1) {
			//���parentId���ڣ���ȡ����һ��SHopCategory�µĶ���ShopCategory�б�
			try {
				ShopCategory shopCategoryCondition=new ShopCategory();
				ShopCategory parent=new ShopCategory();
				parent.setShopCategoryId(parentId);
				shopCategoryCondition.setParent(parent);
				shopCategoryList = shopCategoryService
						.getShopCategoryList(shopCategoryCondition);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		} else {
			try {
				//���parentId�����ڣ���ȡ������һ��ShopCategory
				shopCategoryList = shopCategoryService
						.getShopCategoryList(null);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		modelMap.put("shopCategoryList", shopCategoryList);
		List<Area> areaList = null;
		try {
			//��ȡ�����б���Ϣ
			areaList = areaService.getAreaList();
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
			return modelMap;

		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	/**
	 * ��ȡָ����ѯ�����µĵ����б�
	 * */
	@RequestMapping(value = "/listshops",method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShops(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//��ȡҳ��
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		//��ȡһҳ��Ҫ��ʾ����������
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		//�ǿ��ж�
		
		if ((pageIndex > -1) && (pageSize > -1)) {
			//���Ż�ȡһ�����Id
			long parentId = HttpServletRequestUtil.getLong(request, "parentId");			
			//���Ż�ȡ�ض��������Id
			long shopCategoryId = HttpServletRequestUtil.getLong(request,"shopCategoryId");
			//��ȡ����Id
			int areaId = HttpServletRequestUtil.getInt(request, "areaId");
			//��ȡģ����ѯ����
			String shopName = HttpServletRequestUtil.getString(request,"shopName");
			
			//shopName=URLDecoder.decode(shopName, "UTF-8");
			System.out.println("-------111199999999999------"+shopName);
			//��ȡ���֮��Ĳ�ѯ����
			Shop shopCondition = compactShopCondition4Search(parentId,shopCategoryId, areaId, shopName);
			//���ݲ�ѯ�����ͷ�ҳ��Ϣ��ȡ�����б�����������
			ShopExecution se = shopService.getShopList(shopCondition,pageIndex, pageSize);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("count", se.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}

		return modelMap;
	}
	
	private Shop compactShopCondition4Search(long parentId,
			long shopCategoryId, int areaId, String shopName) {
		
		Shop shopCondition = new Shop();
		if (parentId != -1L) {
			//��ѯĳ��һ��ShopCategory��������ж���ShopCategory����ĵ����б�
			ShopCategory childCategory=new ShopCategory();
			ShopCategory parentCategory = new ShopCategory();
			
			parentCategory.setShopCategoryId(parentId);
			childCategory.setParent(parentCategory);
			shopCondition.setShopCategory(childCategory);;
		}
		if (shopCategoryId != -1L) {
			//��ѯĳ������ShopCategory����ĵ����б�
			ShopCategory shopCategory = new ShopCategory();
			shopCategory.setShopCategoryId(shopCategoryId);
			shopCondition.setShopCategory(shopCategory);
		}
		if (areaId != -1L) {
			//��ѯλ��ĳ������Id�µĵ����б�
			Area area = new Area();
			area.setAreaId(areaId);
			shopCondition.setArea(area);
		}

		if (shopName != null) {
			//��ѯ���������shopName�ĵ����б�
			
				//shopName=URLDecoder.decode(shopName, "UTF-8");
				shopCondition.setShopName(shopName);
				System.out.println("��������������������"+shopName);
			
			
		}
		//ǰ��չʾ�ĵ��̶�����˳ɹ��ĵ���
		shopCondition.setEnableStatus(1);
		return shopCondition;
	}
	
}
