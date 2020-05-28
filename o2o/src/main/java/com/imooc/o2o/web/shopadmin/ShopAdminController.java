package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="shopadmin",method={RequestMethod.GET})
/**
 * ��Ҫ��������·�ɲ�ת������Ӧ��html��
 * */
public class ShopAdminController {
	
	@RequestMapping(value="/shopoperation")
	public String shopOperation(){
		//ת��������ע��/�༭ҳ��
		return "shop/shopoperation";
	}

	@RequestMapping(value="/shoplist")
	public String shopList(){
		//ת���������б�ҳ��
		return "shop/shoplist";
	}
	@RequestMapping(value="/shopmanagement")
	public String shopManagement(){
		//ת�������̹���ҳ��
		return "shop/shopmanagement";
	}
	@RequestMapping(value="/productcategorymanagement",method=RequestMethod.GET)
	private String productCategoryManage(){
		//ת������Ʒ������ҳ��
		return "shop/productcategorymanagement";
	}
	@RequestMapping(value="/productoperation")
	public String productOperation(){
		//ת������Ʒ���/�༭ҳ��
		return "shop/productoperation";
	}
	@RequestMapping(value="/productmanagement")
	public String productManagement(){
		//ת������Ʒ����ҳ��
		return "shop/productmanagement";
	}
	
	
}
